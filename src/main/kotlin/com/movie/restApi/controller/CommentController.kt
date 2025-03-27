package com.movie.restApi.controller

import com.movie.restApi.dto.CommentDTO
import com.movie.restApi.dto.CommentUpdateDTO
import com.movie.restApi.mappers.CommentMapper
import com.movie.restApi.model.Comment
import com.movie.restApi.service.CommentService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/comments")
class CommentController(
    private val commentService: CommentService
) {
    @GetMapping
    fun getAllComments(): ResponseEntity<List<CommentDTO>> {
        val comments: List<Comment> = commentService.getAllComments()
        return commentsRespond(comments)
    }

    @GetMapping("login/{login}")
    fun getCommentsByUser(@PathVariable login: String): ResponseEntity<List<CommentDTO>> {
        val comments = commentService.getCommentByUser(login)
        return commentsRespond(comments)
    }

    @GetMapping("movieId/{movieId}")
    fun getCommentsByMovieId(@PathVariable id: String): ResponseEntity<List<CommentDTO>> {
        val comments = commentService.getCommentByMovieId(id)
        return commentsRespond(comments)
    }

    @PostMapping
    fun createComment(@RequestBody @Valid commentDTO: CommentDTO, bindingResult: BindingResult): ResponseEntity<Any> {
       //obsluga bledow z validatora dto
        if (bindingResult.hasErrors()) {
            val errorMessage = bindingResult.allErrors.joinToString(", ") { it.defaultMessage ?: "Invalid data" }
            return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
        }
        val newComment = commentService.createComment(
            CommentMapper.INSTANCE.toEntity(commentDTO)
        )

        return if (newComment != null) {
            ResponseEntity(newComment, HttpStatus.CREATED)
        } else {
            ResponseEntity("Not able to create comment!", HttpStatus.CONFLICT)
        }
    }

    @PutMapping("/{id}")
    fun updateComment(@PathVariable id: String, @Valid @RequestBody commentDetails: CommentUpdateDTO): ResponseEntity<Any> {
        //Client can edit only content and rating, other changes will not be saved in the database
        val existingComment = commentService.getCommentById(id)

        if (existingComment.isPresent) {
            val oldComment = existingComment.get()
            oldComment.content = commentDetails.content
            oldComment.rating = commentDetails.rating

            val updatedComment = commentService.updateComment(id,oldComment)
            val updatedCommentDTO = CommentMapper.INSTANCE.toDTO(updatedComment.get())
            return ResponseEntity(updatedCommentDTO, HttpStatus.OK)
        } else {
            return ResponseEntity("Comment with id $id not found", HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("{id}")
    fun deleteComment(@PathVariable id: String): ResponseEntity<String> {
        return if (commentService.deleteComment(id)) {
            ResponseEntity("Comment deleted successfully", HttpStatus.OK)
        } else {
            ResponseEntity("Comment with id: $id not found", HttpStatus.NOT_FOUND)
        }
    }

    private fun commentsRespond(comments: List<Comment>): ResponseEntity<List<CommentDTO>> {
        return if (!comments.isNullOrEmpty()) {
            val commentsDTO = comments.map { comment -> CommentMapper.INSTANCE.toDTO(comment) }
            ResponseEntity(commentsDTO, HttpStatus.OK)
        } else {
            ResponseEntity(emptyList(), HttpStatus.NOT_FOUND)
        }
    }
}

