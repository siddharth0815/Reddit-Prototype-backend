package Reddit.Prototype.Backend.service;

import Reddit.Prototype.Backend.models.User;
import Reddit.Prototype.Backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class UserRegisterService {
    @Autowired
    private UserRepository userRepository;
    private User emptyUser;


    public User addUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return emptyUser;
        }
        return user;
    }
}
