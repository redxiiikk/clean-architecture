package hk.qingke.learn.usermanager.adapter.api.convert;

import hk.qingke.learn.usermanager.adapter.api.vo.response.CreateUserResponse;
import hk.qingke.learn.usermanager.domain.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserEntityToVoConvert {
    UserEntityToVoConvert INSTANCE = Mappers.getMapper(UserEntityToVoConvert.class);

    CreateUserResponse entityToVo(UserEntity userEntity);
}
