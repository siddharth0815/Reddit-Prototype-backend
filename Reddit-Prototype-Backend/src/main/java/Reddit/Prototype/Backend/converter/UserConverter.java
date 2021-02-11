package Reddit.Prototype.Backend.converter;

import Reddit.Prototype.Backend.models.User;
import Reddit.Prototype.Backend.models.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {
    public UserDto entityToDto( User user ) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }
    public List<UserDto> entityToDto(List<User> users ) {
        return users.stream().map( x -> entityToDto(x) ).collect(Collectors.toList());
    }
    public User dtoToEntity( UserDto dto ) {
        User user = new User();
        user.setId(dto.getId());
        user.setUserName(dto.getUserName());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        return user;
    }
    public List<User> dtoToEntity( List<UserDto> dtos ) {
        return dtos.stream().map( x -> dtoToEntity(x) ).collect(Collectors.toList());
    }
}
