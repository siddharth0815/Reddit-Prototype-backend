package Reddit.Prototype.Backend.controller;

import java.util.*;

import Reddit.Prototype.Backend.models.Community;
import Reddit.Prototype.Backend.models.Content;
import Reddit.Prototype.Backend.repository.ContentRepository;
import Reddit.Prototype.Backend.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/content")
public class ContentController{

    @Autowired
    private ContentService contentService;

    @Autowired
    private ContentRepository contentRepository;

    @GetMapping
    public List<Content> getAllContent(){
        return this.contentRepository.findAll();
    }

    @PostMapping("/create/{userId}/{communityId}")
    public String createContent(@RequestBody Content content, @PathVariable("userId") Long userId, @PathVariable("communityId") Long communityId){
        return this.contentService.createContent(content,userId,communityId);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteContent(@PathVariable("id") Long id) {
        return this.contentService.deleteContent(id);
    }

    @PutMapping("/edit/{id}")
    public String editContent(@RequestBody Content content, @PathVariable("id") Long id) {
        return this.contentService.editContent(content, id);
    }

    @PostMapping("/upvote/{contentId}")
    public String upvoteContent(@PathVariable("contentId") Long contentId){
        return this.contentService.upvoteContent(contentId);
    }

    @PostMapping("/downvote/{contentId}")
    public String downvoteContent(@PathVariable("contentId") Long contentId){
        return this.contentService.downvoteContent(contentId);
    }

    @GetMapping("/topFive")
    public List<Content> TopContent(){
        return this.contentService.TopContent(5);
    }

}