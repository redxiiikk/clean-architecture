package hk.qingke.kotlin.usermanager.service

import hk.qingke.kotlin.usermanager.domain.UserEntity
import hk.qingke.kotlin.usermanager.service.adapter.repository.UserRepository
import hk.qingke.kotlin.usermanager.service.exception.EmailDuplicationException
import hk.qingke.kotlin.usermanager.service.exception.UsernameDuplicationException

class UserCreateService(private val userRepository: UserRepository) {

    fun create(userEntity: UserEntity): UserEntity {
        this.userRepository.queryUserByUsername(userEntity.username)?.run { throw UsernameDuplicationException() }
        this.userRepository.queryUserByEmail(userEntity.email)?.run { throw EmailDuplicationException() }

        return this.userRepository.save(userEntity)
    }

}