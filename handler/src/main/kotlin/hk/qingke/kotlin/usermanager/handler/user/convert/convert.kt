package hk.qingke.kotlin.usermanager.handler.user.convert

import hk.qingke.kotlin.usermanager.domain.UserEntity
import hk.qingke.kotlin.usermanager.handler.user.vo.CreateUserRequest
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers


fun userVo2Entity(createUserRequest: CreateUserRequest): UserEntity {
    val userVoConvert = Mappers.getMapper(UserVoConvert::class.java)
    return userVoConvert.toEntity(createUserRequest)
}


@Mapper
interface UserVoConvert {
    fun toEntity(createUserRequest: CreateUserRequest): UserEntity
}