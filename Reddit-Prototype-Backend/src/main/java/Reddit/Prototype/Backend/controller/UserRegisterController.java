package Reddit.Prototype.Backend.controller;

import Reddit.Prototype.Backend.models.User;
import Reddit.Prototype.Backend.repository.UserRepository;
import Reddit.Prototype.Backend.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class UserRegisterController {
    @Autowired
    private UserRegisterService userRegisterService;
    @PostMapping
    public User registerUser(@RequestBody User user){
        System.out.println(user);
        //send http response

        return userRegisterService.addUser(user) ;
    }


}





