package Reddit.Prototype.Backend.controller;

import Reddit.Prototype.Backend.converter.CommunityConverter;
import Reddit.Prototype.Backend.models.Community;
import Reddit.Prototype.Backend.models.CommunityDto;
import Reddit.Prototype.Backend.models.Content;
import Reddit.Prototype.Backend.models.ContentDto;
import Reddit.Prototype.Backend.repository.CommunityRepository;
import Reddit.Prototype.Backend.service.CommunityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        public CommunityDto getCommunityById(@PathVariable("id") Long communityId){
            return communityConverter.entityToDto(this.communityRepository.findById(communityId)
                    .orElseThrow( () -> new RuntimeException("User not found with id : "+communityId)));
        }

        @PostMapping("/create")
        public CommunityDto createCommunity(@RequestBody Community community){
            return communityConverter.entityToDto(this.communityRepository.save(community));
        }

        @PostMapping("/follow/user/{userId}/community/{communityId}")
        public ResponseEntity<CommunityDto> followCommunity(@PathVariable("userId") Long userId, @PathVariable("communityId") Long communityId){
            String result = this.communityServices.followCommunity(userId,communityId);
            if( result == "Success" )
                return new ResponseEntity<>(HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        @PostMapping("/vote/user/{userId}/community/{communityId}")
        public String voteCommunity(@PathVariable Long userId, @PathVariable Long communityId, @RequestParam boolean add){
            return this.communityServices.voteCommunity(userId, communityId, add);
        }

        @GetMapping("/sorted")
        public ResponseEntity<List<CommunityDto>> getCommunities(@RequestParam(defaultValue = "votes,desc") String[] sort, @RequestParam int count){
            List<Community> communities = communityServices.getCommunities(sort, count);
            if( communities.isEmpty() )
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(communityConverter.entityToDto(communities), HttpStatus.OK);
        }

        @GetMapping("/trending")
        public ResponseEntity<List<CommunityDto>> TrendingCommunity(@RequestParam int count) {
            List<Community> communities = communityServices.TrendingCommunities(count);
            if( communities.isEmpty() )
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(communityConverter.entityToDto(communities), HttpStatus.OK);
        }
    }

