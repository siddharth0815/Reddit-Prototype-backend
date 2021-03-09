package Reddit.Prototype.Backend.service;

import Reddit.Prototype.Backend.models.Community;
import Reddit.Prototype.Backend.models.Content;
import Reddit.Prototype.Backend.models.User;
import Reddit.Prototype.Backend.models.UserContent;
import Reddit.Prototype.Backend.repository.CommunityRepository;
import Reddit.Prototype.Backend.repository.ContentRepository;
import Reddit.Prototype.Backend.repository.UserContentRepository;
import Reddit.Prototype.Backend.repository.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private UserContentRepository userContentRepository;

    public String createContent(Content content,Long userId,Long communityId){
        User user = this.userRepository.findById(userId)
               .orElseThrow( () -> new RuntimeException("User not found with id : "+userId));
//        user.getUserPosts().add(content);
        content.setUser(user);
        Community community = this.communityRepository.findById(communityId)
                .orElseThrow( () -> new RuntimeException("Community not found with id : "+communityId));
//        community.getCommunityPosts().add(content);
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
        Content content = this.contentRepository.findById(contentId)
                .orElseThrow( () -> new RuntimeException("Content not found with id : "+contentId));
        content.getUser().getUserPosts().remove(content);
        content.getCommunity().getCommunityPosts().remove(content);
        this.contentRepository.delete(content);
        return "post successfully deleted";
    }

    public int voteContent(Long userId, Long contentId, boolean add){
        User user = this.userRepository.findById(userId)
                .orElseThrow( () -> new RuntimeException("User not found with id : "+userId));
        Content content = this.contentRepository.findById(contentId)
                .orElseThrow( () -> new RuntimeException("Content not found with id : "+contentId));
        Optional<UserContent> userContentOptional = this.userContentRepository.findByUserIdAndContentId(userId, contentId);
        UserContent userContent;
        if( userContentOptional.isPresent() ) {
            userContent = userContentOptional.get();
        }
        else {
            userContent = new UserContent();
            userContent.setUser(user);
            userContent.setContent(content);
        }
        int oldVote = userContent.getVotes();
        int newVote = oldVote;
        switch(oldVote) {
            case 0:
                newVote = ( add ? 1 : -1 );
                break;
            case 1:
                newVote = ( add ? 0 : -1 );
                break;
            case -1:
                newVote = ( add ? 1 : 0 );
                break;
        }
        userContent.setVotes(newVote);
        content.setVotes( content.getVotes() - oldVote + newVote );
        this.userContentRepository.save(userContent);
        this.contentRepository.save(content);
        return newVote - oldVote;
    }

    public List<Content> TopContent(int count){
        List<Content> list = this.contentRepository.findByParentId((long)-1);
        Collections.sort(list, new Comparator<Content>() {
            public int compare(Content c1, Content c2) {
                if(c1.getVotes()==c2.getVotes()){return 0;}
                return c1.getVotes()>c2.getVotes() ? -1 : 1;
            }});
        List<Content>  resultList = new ArrayList<>('0');
        for(int i=0;i<count && i<list.size();i++){
            System.out.println(list.get(i));
            resultList.add(list.get(i));
        }
        return resultList;
    }

    public String reactContent(Long userId, Long contentId, Long reactId){
        User user = this.userRepository.findById(userId)
                .orElseThrow( () -> new RuntimeException("User not found with id : "+userId));
        Content content = this.contentRepository.findById(contentId)
                .orElseThrow( () -> new RuntimeException("Content not found with id : "+contentId));
        Optional<UserContent> userContentOptional = this.userContentRepository.findByUserIdAndContentId(userId, contentId);
        UserContent userContent;
        if( userContentOptional.isPresent() ) {
            userContent = userContentOptional.get();
        }
        else {
            userContent = new UserContent();
            userContent.setUser(user);
            userContent.setContent(content);
        }
        userContent.setReaction(reactId.intValue());
        this.userContentRepository.save(userContent);
        System.out.println(userContent.getReaction());
        System.out.println(userContent.getUser().getId());
        return "Reacted Successfully";
    }

}
