package hk.qingke.learn.usermanager.config.infrastructure;

import hk.qingke.learn.usermanager.infrastructure.mapper.UserMapper;
import hk.qingke.learn.usermanager.infrastructure.repository.UserRepositoryImpl;
import hk.qingke.learn.usermanager.service.gateway.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryBeanConfig {
    @Bean
    public UserRepository userRepository(UserMapper userMapper) {
        return new UserRepositoryImpl(userMapper);
    }
}
