package hk.qingke.learn.usermanager.entity;

import hk.qingke.learn.usermanager.entity.exception.PasswordIllegalException;
import hk.qingke.learn.usermanager.entity.exception.PasswordIsBlankException;
import hk.qingke.learn.usermanager.entity.exception.UsernameIsBlankException;
import hk.qingke.learn.usermanager.entity.exception.UsernameThanMaxLengthException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    public static final int MAX_NAME_LENGTH = 20;

    /**
     * 用户密码校验正则：
     * 1. 数字必须出现一次
     * 2. 小写字母必须出现一次
     * 3. 大写字母必须出现一次
     * 4. 必须使用一次特殊字符
     * 5. 不允许使用空格
     * 6. 至少 8 个字符，至多 20 个字符
     */
    public static final Pattern PASSWORD_PATTER =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$");


    private Long id;
    private String username;
    private String password;
    private String email;

    public UserEntity(String username, String password, String email) {
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
    }

    public void setUsername(String username) {
        if (StringUtils.isBlank(username)) {
            throw new UsernameIsBlankException();
        }

        if (username.length() > MAX_NAME_LENGTH) {
            throw new UsernameThanMaxLengthException();
        }

        this.username = username;
    }

    public void setPassword(String password) {
        if (StringUtils.isBlank(password)) {
            throw new PasswordIsBlankException();
        }

        Matcher matcher = PASSWORD_PATTER.matcher(password);
        if (!matcher.matches()) {
            throw new PasswordIllegalException();
        }

        this.password = password;
    }
}
