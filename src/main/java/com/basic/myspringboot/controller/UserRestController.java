package com.basic.myspringboot.controller;

import com.basic.myspringboot.Entity.User;
import com.basic.myspringboot.exception.BusinessException;
import com.basic.myspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {

    private final UserRepository userRepository;

//    //Contructor Injection
//    public UserRestController(UserRepository userRepository) {
//        System.out.println("UserRepository 구현객체명 = " + userRepository.getClass().getName());
//        this.userRepository = userRepository;
//    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
//    public User getUserById(@PathVariable Long id){
    public User getUserById(@PathVariable("id") Long userId){
        return userRepository.findById(userId) //Optional<User>
                // orElseThrow(supplier) Supplier 의 추상메서드 T get()
                .orElseThrow(()-> new BusinessException("데이터가 없습니다.", HttpStatus.NOT_FOUND));
    }
}
