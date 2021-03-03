package Reddit.Prototype.Backend.controller;


import Reddit.Prototype.Backend.converter.UserConverter;
import Reddit.Prototype.Backend.models.User;
import Reddit.Prototype.Backend.models.UserDto;
import Reddit.Prototype.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Reddit.Prototype.Backend.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private UserService userService;

        @Autowired
        private UserConverter userConverter;

        // get all
        @GetMapping
        public List<UserDto> getAllUsers(){
            return userConverter.entityToDto(this.userRepository.findAll());
        }

        // get by id
        @GetMapping("/{id}")
        public UserDto getUserById(@PathVariable(value = "id") Long userId){
            return userConverter.entityToDto(this.userRepository.findById(userId)
                    .orElseThrow( () -> new RuntimeException("User not found with id : "+userId)));
        }

        @PostMapping("/create")
        public UserDto createUser(@RequestBody User user){
            return userConverter.entityToDto(this.userRepository.save(user));
        }

        @PostMapping("/follow/user1/{userId1}/user2/{userId2}")
        public String followUser(@PathVariable("userId1") Long userId1,@PathVariable("userId2") Long userId2){
            return this.userService.FollowUser(userId1,userId2);
        }

    }


