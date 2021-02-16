package Reddit.Prototype.Backend.service;

import Reddit.Prototype.Backend.models.User;
import Reddit.Prototype.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLoginService {

    @Autowired
    UserRepository userRepository;

    public User login(String userName, String password){
//        System.out.println("Login service");
//        System.out.println(password);
//        System.out.println(userName);
        User user = userRepository.findByUserName(userName);
//        System.out.println(user.getUserName());
//        System.out.println(user.getPassword());
        if(user!=null && user.getPassword().equals(password))
        {
            //System.out.println("done");
            return user;
        }
        else{return null;}
    }
}
