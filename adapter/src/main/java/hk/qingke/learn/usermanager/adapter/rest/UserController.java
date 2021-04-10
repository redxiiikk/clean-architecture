package hk.qingke.learn.usermanager.adapter.rest;

import hk.qingke.learn.usermanager.adapter.convert.UserMapperConvert;
import hk.qingke.learn.usermanager.adapter.rest.vo.request.CreateUserRequest;
import hk.qingke.learn.usermanager.service.UserCreateService;
import org.springframework.web.bind.annotation.PostMapping;
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
    public void register(CreateUserRequest request) {
        this.userCreateService.create(UserMapperConvert.INSTANCE.requestToEntity(request));
    }
}
