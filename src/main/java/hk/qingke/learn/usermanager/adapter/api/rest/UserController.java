package hk.qingke.learn.usermanager.adapter.api.rest;

import hk.qingke.learn.usermanager.adapter.api.convert.UserEntityToVoConvert;
import hk.qingke.learn.usermanager.adapter.api.convert.UserVoToEntityConvert;
import hk.qingke.learn.usermanager.adapter.api.vo.request.CreateUserRequest;
import hk.qingke.learn.usermanager.adapter.api.vo.response.CreateUserResponse;
import hk.qingke.learn.usermanager.domain.UserEntity;
import hk.qingke.learn.usermanager.service.UserCreateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserCreateService userCreateService;

    public UserController(UserCreateService userCreateService) {
        this.userCreateService = userCreateService;
    }

    @PostMapping
    public CreateUserResponse register(@RequestBody CreateUserRequest request) {
        UserEntity userEntity = this.userCreateService.create(UserVoToEntityConvert.INSTANCE.requestToEntity(request));

        return UserEntityToVoConvert.INSTANCE.entityToVo(userEntity);
    }
}
