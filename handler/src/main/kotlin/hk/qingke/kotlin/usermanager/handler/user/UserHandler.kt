package hk.qingke.kotlin.usermanager.handler.user

import hk.qingke.kotlin.usermanager.handler.adpater.ServiceFactory
import hk.qingke.kotlin.usermanager.handler.user.convert.userVo2Entity
import hk.qingke.kotlin.usermanager.handler.user.vo.CreateUserRequest
import hk.qingke.kotlin.usermanager.service.UserCreateService

class UserHandler(private val serviceFactory: ServiceFactory) {

    fun createUser(createUserRequest: CreateUserRequest) {
        val userCreateService: UserCreateService = this.serviceFactory.getService(UserCreateService::class)

        userCreateService.create(userVo2Entity(createUserRequest))
    }
}
