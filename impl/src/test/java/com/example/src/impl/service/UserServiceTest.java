package com.example.src.impl.service;

import com.example.src.impl.repository.UserRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.example.src.stubs.UserEntityStub.generationUserEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void createUser() {
        when(userRepository.save(any())).thenReturn(generationUserEntity());
        userService.create(generationUserEntity());
        verify(userRepository).save(generationUserEntity());
    }

    @Test
    public void userFindById() {
        when(userRepository.findById(any())).thenReturn(Optional.of(generationUserEntity()));
        userService.findById("someid");
        verify(userRepository).findById("someid");
    }

    @Test
    public void deleteUserById() {
        userService.delete("someid");
        verify(userRepository).deleteById("someid");
    }

    @Test
    public void userUpdate() {
        when(userRepository.save(any())).thenReturn(generationUserEntity());
        userService.update(generationUserEntity(), "someid");
        verify(userRepository).save(generationUserEntity());
    }
}