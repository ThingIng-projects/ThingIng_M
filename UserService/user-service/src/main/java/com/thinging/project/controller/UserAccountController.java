package com.thinging.project.controller;


import com.thinging.project.model.dto.UserAccountDto;
import com.thinging.project.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/user")
public class UserAccountController {

    public UserAccountService userService;

    public UserAccountController(UserAccountService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody UserAccountDto userReq){
        return userService.createUser(userReq);
    }

   @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserAccountDto userReq, @PathVariable("id") Long id){
        return userService.updateUser(userReq, id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        return userService.deleteUser(id);
    }


    @GetMapping("/{email}")
    public ResponseEntity<UserAccountDto> getUserByEmail(@PathVariable("email") String email){
        return userService.getUserByEmail(email);
    }

    @GetMapping()
    public ResponseEntity<?> getUserList(){
        return userService.getUserList();
    }




}
