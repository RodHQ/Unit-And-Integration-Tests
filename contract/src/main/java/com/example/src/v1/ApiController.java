package com.example.src.v1;

import com.example.src.impl.repository.UserEntity;
import com.example.src.impl.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/testes/v1")
@RestController
@AllArgsConstructor
public class ApiController {

    private final UserService userService;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity create(@RequestBody UserEntity user) {
        return userService.create(user);
    }

    @GetMapping("/user/{id}")
    public UserEntity searchForId(@PathVariable String id) {
        return userService.findById(id);
    }

    @PutMapping("/user/{id}")
    public UserEntity update(@RequestBody UserEntity user, @PathVariable String id) {
        return userService.update(user, id);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        userService.delete(id);
    }
}