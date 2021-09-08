package hk.qingke.kotlin.usermanager.api

import hk.qingke.kotlin.usermanager.handler.user.UserHandler
import hk.qingke.kotlin.usermanager.handler.user.vo.CreateUserRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(private val userHandler: UserHandler) {
    @PostMapping
    fun createUser(createUserRequest: CreateUserRequest) {
        this.userHandler.createUser(createUserRequest)
    }
}