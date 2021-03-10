package Reddit.Prototype.Backend.service;

import Reddit.Prototype.Backend.models.Community;
import Reddit.Prototype.Backend.models.User;
import Reddit.Prototype.Backend.repository.CommunityRepository;
import Reddit.Prototype.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CommunityServices {

    @Autowired
    private CommunityRepository communityRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SortService sortService;

    public String followCommunity(Long userId,Long communityId){
        Community community = this.communityRepository.findById(communityId)
                .orElseThrow( () -> new RuntimeException("User not found with id : "+communityId));
        User user = this.userRepository.findById(userId)
                .orElseThrow( () -> new RuntimeException("User not found with id : "+userId));

        if( community.getCommunityUsers().contains(user) )
            return "Bad Request";

        community.getCommunityUsers().add(user);
        user.getUserCommunities().add(community);
        this.communityRepository.save(community);
        this.userRepository.save(user);
        return "Success";
    }

    public String voteCommunity(Long userId, Long communityId, boolean add){
        Community community = this.communityRepository.findById(communityId)
                .orElseThrow( () -> new RuntimeException("Community not found with id : "+communityId));
        User user = this.userRepository.findById(userId)
                .orElseThrow( () -> new RuntimeException("User not found with id : "+userId));
        if( add )
            community.setVotes(community.getVotes() + 1);
        else
            community.setVotes(Math.max(0, community.getVotes() - 1));
        communityRepository.save(community);
        return "Voting Successful";
    }

    public List<Community> getCommunities(String[] sort, int count){
        return sortService.get(sort, count, communityRepository);
    }

    public List<Community> TopCommunities(int count){
        List<Community> list = this.communityRepository.findAll();
        Collections.sort(list, new Comparator<Community>() {
            public int compare(Community c1, Community c2) {
                if(c1.getVotes()==c2.getVotes())
                    return 0;
                return c1.getVotes()>c2.getVotes() ? -1 : 1;
            }
        });
        List<Community>  resultList = new ArrayList<>(0);
        for(int i=0;i<count && i<list.size();i++){
            resultList.add(list.get(i));
        }
        return resultList;
    }

    public List<Community> TrendingCommunities(int count) {
        List<Community> list = this.communityRepository.findAll();
        Collections.sort(list, new Comparator<Community>() {
            public int compare(Community c1, Community c2) {
                Long a = c1.getCommunityPosts().stream().map(x->x.getVotes()).reduce(Long.valueOf(0), Long::sum);
                Long b = c2.getCommunityPosts().stream().map(x->x.getVotes()).reduce(Long.valueOf(0), Long::sum);
                if( a == b ) return 0;
                return a>b ? -1 : 1;
            }
        });
        List<Community> resultList = new ArrayList<>(0);
        for(int i=0;i<count && i<list.size();i++)
            resultList.add(list.get(i));
        return resultList;
    }
}