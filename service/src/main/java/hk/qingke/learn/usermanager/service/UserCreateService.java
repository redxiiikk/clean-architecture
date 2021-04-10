package hk.qingke.learn.usermanager.service;

import hk.qingke.learn.usermanager.service.exception.UsernameDuplicateException;
import hk.qingke.learn.usermanager.service.gateway.repository.UserRepository;
import hk.qingke.learn.usermanager.domain.UserEntity;

public class UserCreateService {
    private final UserRepository userRepository;

    public UserCreateService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserEntity create(UserEntity userEntity) {
        if (this.userRepository.queryByUsername(userEntity.getUsername()).isPresent()) {
            throw new UsernameDuplicateException();
        }
        return this.userRepository.save(userEntity);
    }
}
