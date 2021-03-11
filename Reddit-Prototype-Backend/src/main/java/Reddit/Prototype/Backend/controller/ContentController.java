package Reddit.Prototype.Backend.controller;

import java.util.*;

import Reddit.Prototype.Backend.converter.ContentConverter;
import Reddit.Prototype.Backend.models.Content;
import Reddit.Prototype.Backend.models.ContentDto;
import Reddit.Prototype.Backend.repository.ContentRepository;
import Reddit.Prototype.Backend.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/content")
public class ContentController{

    @Autowired
    private ContentService contentService;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ContentConverter contentConverter;

    @GetMapping("/sorted")
    public ResponseEntity<List<ContentDto>> getContent(@RequestParam(defaultValue = "votes,desc") String[] sort, @RequestParam int count){
        List<Content> contents = contentService.getContent(sort, count);
        if( contents.isEmpty() )
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(contentConverter.entityToDto(contents), HttpStatus.OK);
    }

    @GetMapping("/{parentId}")
    public ResponseEntity<List<ContentDto>> findAll(@PathVariable("parentId") Long parentId){
        List<Content> contents = contentRepository.findByParentId(parentId);
        if( contents.isEmpty() )
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(contentConverter.entityToDto(contents), HttpStatus.OK);
    }

    @PostMapping("/create/user/{userId}/community/{communityId}")
    public String createContent(@RequestBody Content content, @PathVariable Long userId, @PathVariable Long communityId){
        return this.contentService.createContent(content,userId,communityId);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteContent(@PathVariable Long id) {
        return this.contentService.deleteContent(id);
    }

    @PutMapping("/edit/{id}")
    public String editContent(@RequestBody Content content, @PathVariable("id") Long id) {
        return this.contentService.editContent(content, id);
    }

    @PostMapping("/vote/user/{userId}/content/{contentId}")
    public int voteContent(@PathVariable Long userId, @PathVariable Long contentId, @RequestParam boolean add){
        return this.contentService.voteContent(userId, contentId, add);
    }
}