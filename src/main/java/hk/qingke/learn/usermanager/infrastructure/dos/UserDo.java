package hk.qingke.learn.usermanager.infrastructure.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDo {
    private Long id;
    private String username;
    private String password;
    private String email;
}
