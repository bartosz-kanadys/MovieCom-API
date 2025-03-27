package com.movie.restApi.mappers

import com.movie.restApi.dto.UserDTO
import com.movie.restApi.model.User
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface UserMapper {
    companion object {
        val INSTANCE: UserMapper = Mappers.getMapper(UserMapper::class.java)
    }

    fun toDTO(user: User): UserDTO
    fun toEntity(dto: UserDTO): User
}