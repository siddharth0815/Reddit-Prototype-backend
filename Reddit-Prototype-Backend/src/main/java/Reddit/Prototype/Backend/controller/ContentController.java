package Reddit.Prototype.Backend.controller;

import java.util.*;

import Reddit.Prototype.Backend.models.Content;
import Reddit.Prototype.Backend.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/content")
public class ContentController{

    private ContentRepository contentRepository;
    @Autowired(required = true)
    public ContentController(ContentRepository contentRepository){
        this.contentRepository = contentRepository;
    }

    @GetMapping
    public List<Content> getAllContent(){
        return this.contentRepository.findAll();
    }

    @PostMapping
    public Content createContent(@RequestBody Content content){
        return this.contentRepository.save(content);
    }

    @GetMapping("/test")
    public String test(){
        return "Content api endpoint works!";
    }

}