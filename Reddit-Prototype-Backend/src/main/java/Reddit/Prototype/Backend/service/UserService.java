package Reddit.Prototype.Backend.service;

import Reddit.Prototype.Backend.models.User;
import Reddit.Prototype.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String FollowUser(Long userId1,Long userId2){

        User user1 = this.userRepository.findById(userId1)
                .orElseThrow( () -> new RuntimeException("User not found with id : "+userId1));

        User user2 = this.userRepository.findById(userId2)
                .orElseThrow( () -> new RuntimeException("User not found with id : "+userId2));

        user2.getFollowers().add(user1);
        user1.getFollowing().add(user2);

        userRepository.save(user1);
        userRepository.save(user2);

        return "User following successful";

    }

}
