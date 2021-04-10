package hk.qingke.learn.usermanager.adapter.api.convert;

import hk.qingke.learn.usermanager.adapter.api.vo.request.CreateUserRequest;
import hk.qingke.learn.usermanager.domain.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapperConvert {
    UserMapperConvert INSTANCE = Mappers.getMapper( UserMapperConvert.class );
    
    UserEntity requestToEntity(CreateUserRequest request);
}
