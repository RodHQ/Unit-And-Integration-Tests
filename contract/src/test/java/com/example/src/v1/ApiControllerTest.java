package com.example.src.v1;

import com.example.src.impl.repository.UserRepository;
import com.example.src.impl.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.src.stubs.UserEntityStub.generationUserEntity2;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ApiController.class)
@ContextConfiguration(classes = {ApiController.class,
    UserService.class})
@AutoConfigureMockMvc
public class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void findById_ReturnCode_OK() throws Exception {
        given(userRepository.findById("someid")).willReturn(java.util.Optional.of(generationUserEntity2()));
        this.mockMvc.perform(get("/testes/v1/user/someid"))
            .andExpect(status().isOk());
    }

    @Test
    public void createUser_ReturnCode_Created() throws Exception {
        given(userRepository.save(any())).willReturn(generationUserEntity2());
        this.mockMvc.perform(post("/testes/v1/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper()
                .writeValueAsBytes(generationUserEntity2())))
            .andExpect(status().isCreated());
    }

    @Test
    public void deleteFindById_ReturnCode_NoContent() throws Exception {
        given(userRepository.findById("someid")).willReturn(java.util.Optional.of(generationUserEntity2()));
        this.mockMvc.perform(delete("/testes/v1/user/someid")).andExpect(status().isNoContent());
    }

    @Test
    public void updateUser_ReturnCode_OK() throws Exception {
        given(userRepository.findById("someid")).willReturn(java.util.Optional.of(generationUserEntity2()));
        this.mockMvc.perform(put("/testes/v1/user/someid")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper()
                .writeValueAsString(generationUserEntity2()))).andExpect(status().isOk());
    }
}