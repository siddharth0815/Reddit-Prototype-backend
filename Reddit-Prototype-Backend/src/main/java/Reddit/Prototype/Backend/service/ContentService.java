package Reddit.Prototype.Backend.service;

import Reddit.Prototype.Backend.models.Community;
import Reddit.Prototype.Backend.models.Content;
import Reddit.Prototype.Backend.models.User;
import Reddit.Prototype.Backend.repository.CommunityRepository;
import Reddit.Prototype.Backend.repository.ContentRepository;
import Reddit.Prototype.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommunityRepository communityRepository;

    public String createContent(Content content,Long userId,Long communityId){
        User user = this.userRepository.findById(userId)
               .orElseThrow( () -> new RuntimeException("User not found with id : "+userId));
        user.getUserPosts().add(content);
        content.setUser(user);
        Community community =this.communityRepository.findById(communityId)
                .orElseThrow( () -> new RuntimeException("Community not found with id : "+communityId));
        community.getCommunityPosts().add(content);
        content.setCommunity(community);
//        this.communityRepository.save(community);
//        this.userRepository.save(user);
        this.contentRepository.save(content);
        return "post successfully created";
    }

    public String editContent(Content content, Long id){
        Content original = this.contentRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Content not found with id : "+id));
        if( content.getContentBody() != null )
            original.setContentBody(content.getContentBody());
        if( content.getImageURL() != null )
            original.setImageURL(content.getImageURL());
        this.contentRepository.save(original);
        return "post successfully edited";
    }

    public String deleteContent(Long contentId){
        Content content =this.contentRepository.findById(contentId)
                .orElseThrow( () -> new RuntimeException("Content not found with id : "+contentId));
        content.getUser().getUserPosts().remove(content);
        content.getCommunity().getCommunityPosts().remove(content);
        this.contentRepository.delete(content);
        return "post successfully deleted";
    }

    public String upvoteContent(Long contentId){
        Content content = this.contentRepository.findById(contentId)
                .orElseThrow( () -> new RuntimeException("Content not found with id : "+contentId));
        content.setUpvotes(content.getUpvotes()+1);
        contentRepository.save(content);
        return "Upvoting Successful";

    }

    public String downvoteContent(Long contentId){
        Content content = this.contentRepository.findById(contentId)
                .orElseThrow( () -> new RuntimeException("Content not found with id : "+contentId));
        content.setDownvotes(content.getDownvotes()+1);
        contentRepository.save(content);
        return "Downvoting Successful";
    }

    public List<Content> TopContent(int count){
        List<Content> list = this.contentRepository.findAll();
        Collections.sort(list, new Comparator<Content>() {
            public int compare(Content c1, Content c2) {
                return c1.getUpvotes()>c2.getUpvotes() ? -1 : 1;
            }});
        List<Content>  resultList = new ArrayList<>('0');
        for(int i=0;i<count && i<list.size();i++){
            resultList.add(list.get(i));
        }
        return resultList;
    }

}
