package hk.qingke.learn.usermanager.infrastructure.repository;

import hk.qingke.learn.usermanager.domain.UserEntity;
import hk.qingke.learn.usermanager.infrastructure.convert.UserDoToEntityConvert;
import hk.qingke.learn.usermanager.infrastructure.convert.UserEntityToDoConvert;
import hk.qingke.learn.usermanager.infrastructure.dos.UserDo;
import hk.qingke.learn.usermanager.infrastructure.mapper.UserMapper;
import hk.qingke.learn.usermanager.service.gateway.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserMapper userMapper;

    public UserRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public UserEntity save(UserEntity userEntity) {
        if (Objects.nonNull(userEntity.getId())) {
            this.userMapper.updateById(UserEntityToDoConvert.INSTANCE.entityToDo(userEntity));
        } else {
            Long id = this.userMapper.create(UserEntityToDoConvert.INSTANCE.entityToDo(userEntity));
            userEntity.setId(id);
        }
        return userEntity;
    }

    @Override
    public Optional<UserEntity> queryByUsername(String username) {
        Optional<UserDo> userDo = this.userMapper.queryByUsername(username);

        if (userDo.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(UserDoToEntityConvert.INSTANCE.doToEntity(userDo.get()));
    }

    @Override
    public Optional<UserEntity> queryByEmail(String email) {
        Optional<UserDo> userDo = this.userMapper.queryByEmail(email);

        if (userDo.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(UserDoToEntityConvert.INSTANCE.doToEntity(userDo.get()));
    }
}
