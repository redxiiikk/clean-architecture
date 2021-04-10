package hk.qingke.learn.usermanager.adapter.api.convert;

import hk.qingke.learn.usermanager.adapter.api.vo.request.CreateUserRequest;
import hk.qingke.learn.usermanager.domain.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserVoToEntityConvert {
    UserVoToEntityConvert INSTANCE = Mappers.getMapper(UserVoToEntityConvert.class);

    UserEntity requestToEntity(CreateUserRequest request);
}
