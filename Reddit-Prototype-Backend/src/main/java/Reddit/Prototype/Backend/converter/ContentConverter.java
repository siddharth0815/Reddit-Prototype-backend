package Reddit.Prototype.Backend.converter;

import Reddit.Prototype.Backend.models.Content;
import Reddit.Prototype.Backend.models.ContentDto;
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
        return dto;
    }

    public List<ContentDto> entityToDto(List<Content> content){
        return content.stream().map(x->entityToDto(x)).collect(Collectors.toList());
    }

}
