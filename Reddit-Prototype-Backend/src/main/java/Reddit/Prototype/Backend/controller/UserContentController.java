package Reddit.Prototype.Backend.controller;

import Reddit.Prototype.Backend.converter.UserContentConverter;
import Reddit.Prototype.Backend.models.User;
import Reddit.Prototype.Backend.models.UserContent;
import Reddit.Prototype.Backend.models.UserContentDto;
import Reddit.Prototype.Backend.models.UserDto;
import Reddit.Prototype.Backend.repository.UserContentRepository;
import Reddit.Prototype.Backend.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/UserContent")
public class UserContentController {

    @Autowired
    UserContentRepository userContentRepository;
    @Autowired
    UserContentConverter userContentConverter;
    @Autowired
    ContentService contentService;

    @GetMapping("/{id}")
    public List<UserContentDto> getAllUserContent(@PathVariable(value = "id") Long userId){
        return userContentConverter.entityToDto(userContentRepository.findByUserId(userId));
    }

    @PostMapping("/react/{userId}/{contentId}/{reactId}")
    public String react(@PathVariable(value = "userId") Long userId, @PathVariable(value="contentId") Long contentId, @PathVariable(value="reactId") Long reactId){
     return contentService.reactContent(userId,contentId,reactId);
    }



}
