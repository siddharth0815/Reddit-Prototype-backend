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


    public User addUser(User user) {
        try {
           String password = user.getPassword();
           String hashedPassword = generateHash(password);
           user.setPassword(hashedPassword);
            userRepository.save(user);
        } catch (Exception e) {
            return null;
        }
        return user;
    }

    public String generateHash(String password)
    {
        //adding salt and computing hashcode
        final String SALT = "#0*5#&";
        String str = SALT + password;
        return Integer.toString(str.hashCode());
    }
}
