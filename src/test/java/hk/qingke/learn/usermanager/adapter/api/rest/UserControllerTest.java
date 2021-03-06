package hk.qingke.learn.usermanager.adapter.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import hk.qingke.learn.usermanager.adapter.api.vo.request.CreateUserRequest;
import hk.qingke.learn.usermanager.domain.UserEntity;
import hk.qingke.learn.usermanager.service.UserCreateService;
import hk.qingke.learn.usermanager.service.exception.UsernameDuplicateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.servlet.server.Encoding;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Locale;

@Slf4j
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private AutoCloseable closeable;
    private ObjectMapper objectMapper;

    @MockBean
    private UserCreateService userCreateService;


    @BeforeEach
    void init() {
        this.closeable = MockitoAnnotations.openMocks(this);
        this.objectMapper = new ObjectMapper();

    }

    @AfterEach
    void closeService() throws Exception {
        this.closeable.close();
    }

    @Test
    void always_true() {
        Assertions.assertTrue(true);
    }

    @Test
    void create_user() throws Exception {
        CreateUserRequest requestBody = new CreateUserRequest("Tom", "1@aB2345678", "test@test.hk");

        long generateUserId = RandomUtils.nextLong();
        Mockito.doAnswer(invocation -> {
            UserEntity argument = invocation.getArgument(0, UserEntity.class);

            log.info(argument.toString());

            Assertions.assertEquals(requestBody.getUsername(), argument.getUsername());
            Assertions.assertEquals(requestBody.getEmail(), argument.getEmail());

            argument.setId(generateUserId);
            return argument;
        }).when(this.userCreateService).create(Mockito.any(UserEntity.class));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user")
                .content(this.objectMapper.writeValueAsString(requestBody))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(generateUserId)));

        Mockito.verify(this.userCreateService).create(Mockito.any(UserEntity.class));
    }
    @Test
    void create_user_fail_when_throw_user_name_duplication_exception_given_simple_chinese() throws Exception {
        Mockito.when(this.userCreateService.create(Mockito.any(UserEntity.class)))
                .thenThrow(new UsernameDuplicateException());

        CreateUserRequest requestBody = new CreateUserRequest("Tom", "1@aB2345678", "test@test.hk");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user")
                .header(HttpHeaders.ACCEPT_LANGUAGE, Locale.SIMPLIFIED_CHINESE.toLanguageTag())
                .header(HttpHeaders.ACCEPT_ENCODING, Encoding.DEFAULT_CHARSET)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(requestBody));

        this.mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("???????????????"));
    }

    @Test
    void create_user_fail_when_throw_user_name_duplication_exception_given_en_us() throws Exception {
        Mockito.when(this.userCreateService.create(Mockito.any(UserEntity.class)))
                .thenThrow(new UsernameDuplicateException());

        CreateUserRequest requestBody = new CreateUserRequest("Tom", "1@aB2345678", "test@test.hk");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/user")
                .header(HttpHeaders.ACCEPT_LANGUAGE, Locale.US.toLanguageTag())
                .header(HttpHeaders.ACCEPT_ENCODING, Encoding.DEFAULT_CHARSET)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(requestBody));

        this.mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Duplicate username"));
    }
}