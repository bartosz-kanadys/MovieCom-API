package com.movie.restApi.controller

import com.movie.restApi.dto.UserLoginDTO
import com.movie.restApi.model.User
import com.movie.restApi.service.AuthorizationService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(
    private val authService: AuthorizationService
) {
    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    fun login(@RequestBody user: UserLoginDTO): ResponseEntity<Map<String, Any>> {
        val response: MutableMap<String, Any> = HashMap()
        response["success"] = true
        response["message"] = "login success"
        response["login"] = user.login
        response["token"] = authService.verify(user)

        return ResponseEntity.ok(response)
    }
}
