package Reddit.Prototype.Backend.converter;

import Reddit.Prototype.Backend.models.Community;
import Reddit.Prototype.Backend.models.CommunityDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommunityConverter {
    public CommunityDto entityToDto( Community community ) {
        CommunityDto dto = new CommunityDto();
        dto.setId(community.getId());
        dto.setTitle(community.getTitle());
        dto.setDescription(community.getDescription());
        dto.setVotes(community.getVotes());
        if( community.getCommunityUsers() != null )
            dto.setMembers(community.getCommunityUsers().size());
        if( community.getCommunityPosts() != null )
            dto.setPosts(community.getCommunityPosts().size());
        return dto;
    }
    public List<CommunityDto> entityToDto(List<Community> commumities ) {
        return commumities.stream().map(x->entityToDto(x)).collect(Collectors.toList());
    }
    public Community dtoToEntity( CommunityDto dto ) {
        Community community = new Community();
        community.setId(dto.getId());
        community.setTitle(dto.getTitle());
        community.setDescription(dto.getDescription());
        community.setVotes(dto.getVotes());
        return community;
    }
    public List<Community> dtoToEntity(List<CommunityDto> dtos ) {
        return dtos.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
    }
}
