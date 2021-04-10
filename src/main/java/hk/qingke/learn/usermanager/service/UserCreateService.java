package hk.qingke.learn.usermanager.service;

import hk.qingke.learn.usermanager.domain.UserEntity;
import hk.qingke.learn.usermanager.service.check.UserDuplicationCheck;
import hk.qingke.learn.usermanager.service.gateway.repository.UserRepository;

public class UserCreateService {
    private final UserRepository userRepository;
    private final UserDuplicationCheck userDuplicationCheck;

    public UserCreateService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userDuplicationCheck = new UserDuplicationCheck(userRepository);
    }


    public UserEntity create(UserEntity userEntity) {
        this.userDuplicationCheck.checkWhenCreateUser(userEntity);

        return this.userRepository.save(userEntity);
    }
}
