package Reddit.Prototype.Backend.controller;

import Reddit.Prototype.Backend.converter.UserContentConverter;
import Reddit.Prototype.Backend.models.UserContent;
import Reddit.Prototype.Backend.models.UserContentDto;
import Reddit.Prototype.Backend.models.UserDto;
import Reddit.Prototype.Backend.repository.UserContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/UserContent")
public class UserContentController {

    @Autowired
    UserContentRepository userContentRepository;
    @Autowired
    UserContentConverter userContentConverter;

    @GetMapping("/{id}")
    public List<UserContentDto> getAllUserContent(@PathVariable(value = "id") Long userId){

        return userContentConverter.entityToDto(userContentRepository.findByUserId(userId));
    }

}
