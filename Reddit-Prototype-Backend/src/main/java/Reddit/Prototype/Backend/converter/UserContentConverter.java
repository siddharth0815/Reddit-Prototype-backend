package Reddit.Prototype.Backend.converter;

import Reddit.Prototype.Backend.models.User;
import Reddit.Prototype.Backend.models.UserContent;
import Reddit.Prototype.Backend.models.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import Reddit.Prototype.Backend.models.UserContentDto;

@Component
public class UserContentConverter {
    public UserContentDto entityToDto(UserContent userContent ) {
        UserContentDto dto = new UserContentDto();
        dto.setUserId(userContent.getUser().getId());
        dto.setContentId(userContent.getContent().getId());
        dto.setVotes((long)userContent.getVotes());
        return dto;
    }
    public List<UserContentDto> entityToDto(List<UserContent> users ) {
        return users.stream().map( x -> entityToDto(x) ).collect(Collectors.toList());
    }
}
