package hk.qingke.learn.usermanager.service.check;

import hk.qingke.learn.usermanager.domain.UserEntity;
import hk.qingke.learn.usermanager.service.exception.UserEmailDuplicationException;
import hk.qingke.learn.usermanager.service.exception.UsernameDuplicateException;
import hk.qingke.learn.usermanager.service.gateway.repository.UserRepository;

public class UserDuplicationCheck {
    public final UserRepository userRepository;

    public UserDuplicationCheck(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void checkWhenCreateUser(UserEntity userEntity) {
        if (this.isUsernameDuplication(userEntity.getUsername())) {
            throw new UsernameDuplicateException();
        }
        if (this.isUserEmailDuplication(userEntity.getEmail())) {
            throw new UserEmailDuplicationException();
        }
    }

    public boolean isUsernameDuplication(String username) {
        return this.userRepository.queryByUsername(username).isPresent();
    }

    public boolean isUserEmailDuplication(String email) {
        return this.userRepository.queryByEmail(email).isPresent();
    }
}
