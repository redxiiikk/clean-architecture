package hk.qingke.learn.usermanager.infrastructure.mapper;

import hk.qingke.learn.usermanager.infrastructure.dos.UserDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper {
    void updateById(@Param("userDo") UserDo userDo);

    Optional<UserDo> queryByUsername(@Param("username") String username);

    Optional<UserDo> queryByEmail(@Param("email") String email);

    Long create(@Param("userDo") UserDo userDo);
}
