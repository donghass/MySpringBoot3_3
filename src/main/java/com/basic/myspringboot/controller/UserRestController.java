package com.basic.myspringboot.controller;

import com.basic.myspringboot.Entity.User;
import com.basic.myspringboot.exception.BusinessException;
import com.basic.myspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // 관리자 Role 권한만 가능
    public List<User> getUsers() {
        return userRepository.findAll();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")  // 일반 사용자 Role 권한이 있으면 가능
//    public User getUserById(@PathVariable Long id){
    public User getUserById(@PathVariable("id") Long userId){
        return getUser(userId);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId) //Optional<User>
                // orElseThrow(supplier) Supplier 의 추상메서드 T get()
                .orElseThrow(() -> new BusinessException("데이터가 없습니다.", HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{email}/")
    public User updateUserByEmail(@PathVariable String email, @RequestBody User userDetail){
        // email 존재유무 조회
        User existUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("데이터가 없습니다.", HttpStatus.NOT_FOUND));

        existUser.setName(userDetail.getName());
        return userRepository.save(existUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));
        userRepository.delete(user);
//return ResponseEntity.ok(user);
        return getOk(id);
    }

    private static ResponseEntity<String> getOk(Long id) {
        return ResponseEntity.ok("ID = " + id + " User Deleted OK!");
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }
}
