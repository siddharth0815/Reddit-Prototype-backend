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

        public String followCommunity(Long userId,Long communityId){

            System.out.println("Hello follow");
            Community community = this.communityRepository.findById(communityId)
                    .orElseThrow( () -> new RuntimeException("User not found with id : "+communityId));

            System.out.println(community.getTitle());

            User user = this.userRepository.findById(userId)
                    .orElseThrow( () -> new RuntimeException("User not found with id : "+userId));

            System.out.println(user.getUserName());


            community.getCommunityUsers().add(user);
            user.getUserCommunities().add(community);
            this.communityRepository.save(community);
            this.userRepository.save(user);
            return "Following Community Successful";

        }

        public String upvoteCommunity(Long communityId){


            Community community = this.communityRepository.findById(communityId)
                    .orElseThrow( () -> new RuntimeException("User not found with id : "+communityId));


            community.setUpvotes(community.getUpvotes()+1);
            communityRepository.save(community);
            return "Upvoting Successful";

        }

        public String downvoteCommunity(Long communityId){

            Community community = this.communityRepository.findById(communityId)
                    .orElseThrow( () -> new RuntimeException("User not found with id : "+communityId));

            community.setDownvotes(community.getDownvotes()+1);
            communityRepository.save(community);

            return "Downvoting Successful";

        }

        public List<Community> TopCommunities(int count){


            List<Community> list = this.communityRepository.findAll();

            Collections.sort(list, new Comparator<Community>() {
                public int compare(Community c1, Community c2) {


                    return c1.getUpvotes()>c2.getUpvotes() ? -1 : 1;
                }});


            List<Community>  resultList = new ArrayList<>('0');

            for(int i=0;i<count && i<list.size();i++){
                resultList.add(list.get(i));
            }

            return resultList;

        }


    }

