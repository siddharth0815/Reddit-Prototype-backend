package Reddit.Prototype.Backend.controller;

import Reddit.Prototype.Backend.models.Community;
import Reddit.Prototype.Backend.repository.CommunityRepository;
import Reddit.Prototype.Backend.service.CommunityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/community")
public class CommunityController {

        @Autowired
        private CommunityRepository communityRepository;

        @Autowired
        private CommunityServices communityServices;

        // get all communities
        @GetMapping
        public List<Community> getAllCommunities(){
            return this.communityRepository.findAll();
        }

        // get by id
        @GetMapping("/{id}")
        public Community getCommunityById(@PathVariable(value = "id") Long communityId){
            return this.communityRepository.findById(communityId)
                    .orElseThrow( () -> new RuntimeException("User not found with id : "+communityId));
        }

        @PostMapping("/create")
        public Community createCommunity(@RequestBody Community community){
            return this.communityRepository.save(community);
        }

        @GetMapping("/test")
        public String test(){
            return "Community api endpoint works!";
        }

        @PostMapping("/follow/{userId}/{communityId}")
        public String followCommunity(@PathVariable("userId") Long userId,@PathVariable("communityId") Long communityId){
            return this.communityServices.followCommunity(userId,communityId);
        }

        @PostMapping("/upvote/{communityId}")
        public String upvoteCommunity(@PathVariable("communityId") Long communityId){
            return this.communityServices.upvoteCommunity(communityId);
        }


        @PostMapping("/downvote/{communityId}")
        public String downvoteCommunity(@PathVariable("communityId") Long communityId){
            return this.communityServices.downvoteCommunity(communityId);
        }

        @GetMapping("/topFive")
        public List<Community> TopCommunity(){
        return this.communityServices.TopCommunities(5);
       }

    }

