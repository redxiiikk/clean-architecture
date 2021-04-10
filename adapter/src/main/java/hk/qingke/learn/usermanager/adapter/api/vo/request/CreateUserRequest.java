package hk.qingke.learn.usermanager.adapter.api.vo.request;


import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String password;
    private String email;
}
