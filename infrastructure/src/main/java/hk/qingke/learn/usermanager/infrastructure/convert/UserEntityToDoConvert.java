package hk.qingke.learn.usermanager.infrastructure.convert;

import hk.qingke.learn.usermanager.domain.UserEntity;
import hk.qingke.learn.usermanager.infrastructure.dos.UserDo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserEntityToDoConvert {
    UserEntityToDoConvert INSTANCE = Mappers.getMapper(UserEntityToDoConvert.class);

    UserDo entityToDo(UserEntity userEntity);
}
