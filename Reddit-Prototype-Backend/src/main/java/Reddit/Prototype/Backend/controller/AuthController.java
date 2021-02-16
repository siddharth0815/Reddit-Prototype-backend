package Reddit.Prototype.Backend.controller;

import Reddit.Prototype.Backend.converter.UserConverter;
import Reddit.Prototype.Backend.models.User;
import Reddit.Prototype.Backend.models.UserDto;
import Reddit.Prototype.Backend.repository.UserRepository;
import Reddit.Prototype.Backend.service.UserLoginService;
import Reddit.Prototype.Backend.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("user/auth")
public class AuthController {
    @Autowired
    private UserRegisterService userRegisterService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private UserConverter userConverter;

    @PostMapping
    @RequestMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userRegisterService.addUser(user);
    }

    @RequestMapping("/login")
    @GetMapping
    public ResponseEntity<UserDto> loginUser(@RequestBody Map<String, String> credentials) {
        System.out.println("hello login");
        String userName = credentials.get("userName");
        String password = credentials.get("password");
        UserDto dto = userConverter.entityToDto(userLoginService.login(userName, password));
        if(dto!=null){return new ResponseEntity<>(dto, HttpStatus.OK);}
        else{return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
    }
}