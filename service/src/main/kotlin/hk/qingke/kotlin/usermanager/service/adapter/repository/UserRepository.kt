package hk.qingke.kotlin.usermanager.service.adapter.repository

import hk.qingke.kotlin.usermanager.domain.UserEntity

interface UserRepository {
    fun queryUserByUsername(username: String): UserEntity?
    fun queryUserByEmail(email: String): UserEntity?

    fun save(userEntity: UserEntity): UserEntity
}