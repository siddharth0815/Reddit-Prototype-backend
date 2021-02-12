package Reddit.Prototype.Backend.controller;

import Reddit.Prototype.Backend.converter.CommunityConverter;
import Reddit.Prototype.Backend.models.Community;
import Reddit.Prototype.Backend.models.CommunityDto;
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

        @Autowired
        private CommunityConverter communityConverter;

        // get all communities
        @GetMapping
        public List<CommunityDto> getAllCommunities(){
            return communityConverter.entityToDto(this.communityRepository.findAll());
        }

        // get by id
        @GetMapping("/{id}")
        public CommunityDto getCommunityById(@PathVariable(value = "id") Long communityId){
            return communityConverter.entityToDto(this.communityRepository.findById(communityId)
                    .orElseThrow( () -> new RuntimeException("User not found with id : "+communityId)));
        }

        @PostMapping("/create")
        public CommunityDto createCommunity(@RequestBody Community community){
            return communityConverter.entityToDto(this.communityRepository.save(community));
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

        @GetMapping("/top")
        public List<CommunityDto> TopCommunity(@RequestParam int count){
            return communityConverter.entityToDto(this.communityServices.TopCommunities(count));
        }

        @GetMapping("/trending")
        public List<CommunityDto> TrendingCommunity(@RequestParam int count) {
            return communityConverter.entityToDto(this.communityServices.TrendingCommunities(count));
        }
    }

