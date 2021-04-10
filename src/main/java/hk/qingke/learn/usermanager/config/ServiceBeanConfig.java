package hk.qingke.learn.usermanager.config;

import hk.qingke.learn.usermanager.service.UserCreateService;
import hk.qingke.learn.usermanager.service.gateway.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeanConfig {
    @Bean
    public UserCreateService userCreateService(UserRepository userRepository) {
        return new UserCreateService(userRepository);
    }
}