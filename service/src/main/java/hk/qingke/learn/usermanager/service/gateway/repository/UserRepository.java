package hk.qingke.learn.usermanager.service.gateway.repository;

import hk.qingke.learn.usermanager.domain.UserEntity;

import java.util.Optional;

public interface UserRepository {
    UserEntity save(UserEntity userEntity);

    Optional<UserEntity> queryByUsername(String username);
}
