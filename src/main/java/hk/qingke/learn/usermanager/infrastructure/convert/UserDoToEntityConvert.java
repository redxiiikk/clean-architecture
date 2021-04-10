package hk.qingke.learn.usermanager.infrastructure.convert;

import hk.qingke.learn.usermanager.domain.UserEntity;
import hk.qingke.learn.usermanager.infrastructure.dos.UserDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserDoToEntityConvert {
    UserDoToEntityConvert INSTANCE = Mappers.getMapper(UserDoToEntityConvert.class);

    UserEntity doToEntity(UserDo userDo);
}
