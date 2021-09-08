package hk.qingke.kotlin.usermanager.repository

import hk.qingke.kotlin.usermanager.domain.UserEntity
import hk.qingke.kotlin.usermanager.service.adapter.repository.UserRepository

class UserRepositoryImpl: UserRepository {
    override fun queryUserByUsername(username: String): UserEntity? {
        TODO("Not yet implemented")
    }

    override fun queryUserByEmail(email: String): UserEntity? {
        TODO("Not yet implemented")
    }

    override fun save(userEntity: UserEntity): UserEntity {
        TODO("Not yet implemented")
    }

}