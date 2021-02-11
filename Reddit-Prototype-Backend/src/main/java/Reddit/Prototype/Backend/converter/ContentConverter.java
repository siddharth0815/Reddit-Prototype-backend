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
        dto.setUpvotes(content.getUpvotes());
        dto.setDownvotes(content.getDownvotes());
        dto.setContentBody(content.getContentBody());
        dto.setUserName(content.getUser().getUserName());
        dto.setCommunityName(content.getCommunity().getTitle());
        return dto;
    }
    public List<ContentDto> entityToDto(List<Content> content){
        return content.stream().map(x->entityToDto(x)).collect(Collectors.toList());
    }
    public Content dtoToEntity( ContentDto dto ) {
        Content content = new Content();
        content.setId(dto.getId());
        content.setParentId(dto.getParentId());
        content.setUpvotes(dto.getUpvotes());
        content.setDownvotes(dto.getDownvotes());
        content.setContentBody(dto.getContentBody());
        return content;
    }
    public List<Content> dtoToEntity( List<ContentDto> dtos ) {
        return dtos.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
    }
}
