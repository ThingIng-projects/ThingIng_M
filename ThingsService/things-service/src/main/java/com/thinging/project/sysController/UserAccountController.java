package com.thinging.project.sysController;


import com.thinging.project.controller.AbstractController;
import com.thinging.project.response.UserAccountDto;
import com.thinging.project.service.sys.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;

@RestController()
@RequestMapping("/user")
public class UserAccountController extends AbstractController {

    public UserAccountService userService;

    public UserAccountController(Validator validator, UserAccountService userService) {
        super(validator);
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserAccountDto userReq){
        return respondCreated(userService.createUser(userReq));
    }

   @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserAccountDto userReq, @PathVariable("id") Long id){
        return respondOK(userService.updateUser(userReq, id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return respondEmpty();
    }


    @GetMapping("/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email){
        return respondOK(userService.getUserByEmail(email));
    }

    @GetMapping()
    public ResponseEntity<?> getUserList(){
        return respondOK(userService.getUserList());
    }

}
