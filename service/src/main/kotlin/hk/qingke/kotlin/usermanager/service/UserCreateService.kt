package hk.qingke.kotlin.usermanager.service

import hk.qingke.kotlin.usermanager.domain.UserEntity
import hk.qingke.kotlin.usermanager.service.adapter.repository.UserRepository
import hk.qingke.kotlin.usermanager.service.exception.EmailDuplicationException
import hk.qingke.kotlin.usermanager.service.exception.UsernameDuplicationException

class UserCreateService(private val userRepository: UserRepository) {

    fun create(userEntity: UserEntity): UserEntity {
        if (this.userRepository.queryUserByUsername(userEntity.username) != null) {
            throw UsernameDuplicationException()
        }

        if (this.userRepository.queryUserByEmail(userEntity.email) != null) {
            throw EmailDuplicationException()
        }

        return this.userRepository.save(userEntity)
    }

}