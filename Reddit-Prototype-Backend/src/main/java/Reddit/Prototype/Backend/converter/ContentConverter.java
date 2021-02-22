package Reddit.Prototype.Backend.converter;

import Reddit.Prototype.Backend.models.Content;
import Reddit.Prototype.Backend.models.ContentDto;
import Reddit.Prototype.Backend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContentConverter  {
    public ContentDto entityToDto(Content content){
        ContentDto dto = new ContentDto();
        dto.setId(content.getId());
        dto.setParentId(content.getParentId());
        dto.setVotes(content.getVotes());
        dto.setContentBody(content.getContentBody());
        dto.setImageURL(content.getImageURL());
        dto.setUserName(content.getUser().getUserName());
        dto.setCommunityName(content.getCommunity().getTitle());
        dto.setImageURL(content.getImageURL());
        return dto;
    }
    public List<ContentDto> entityToDto(List<Content> content){
        return content.stream().map(x->entityToDto(x)).collect(Collectors.toList());
    }
    public Content dtoToEntity( ContentDto dto ) {
        Content content = new Content();
        content.setId(dto.getId());
        content.setParentId(dto.getParentId());
        content.setVotes(dto.getVotes());
        content.setContentBody(dto.getContentBody());
        content.setImageURL(dto.getImageURL());
        return content;
    }
    public List<Content> dtoToEntity( List<ContentDto> dtos ) {
        return dtos.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
    }
}
