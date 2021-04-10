package hk.qingke.learn.usermanager.infrastructure.repository;

import hk.qingke.learn.usermanager.domain.UserEntity;
import hk.qingke.learn.usermanager.infrastructure.convert.UserEntityToDoConvert;
import hk.qingke.learn.usermanager.infrastructure.mapper.UserMapper;
import hk.qingke.learn.usermanager.service.gateway.repository.UserRepository;

import java.util.Objects;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final UserMapper userMapper;

    public UserRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public UserEntity save(UserEntity userEntity) {
        if (Objects.nonNull(userEntity.getId())) {
            this.userMapper.updateById(UserEntityToDoConvert.INSTANCE.entityToDo(userEntity));
        }
        return null;
    }

    @Override
    public Optional<UserEntity> queryByUsername(String username) {
        return this.userMapper.queryByUsername(username);
    }

    @Override
    public Optional<UserEntity> queryByEmail(String email) {
        return this.userMapper.queryByEmail(email);
    }
}
