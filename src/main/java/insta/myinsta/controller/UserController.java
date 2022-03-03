package insta.myinsta.controller;

import insta.myinsta.dto.RegisterUserDto;
import insta.myinsta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/user/register")
    public ResponseEntity registerUser(final @Valid @RequestBody RegisterUserDto registerUserDto) {
        userService.registerUser(registerUserDto);
        return ResponseEntity.status(HttpStatus.OK).body("회원가입 성공!");
    }

}
