package Reddit.Prototype.Backend.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class ContentDto {
    private Long id;
    private String contentBody;
    private Long parentId;
    private Long upvotes;
    private Long downvotes;
    private String userName;
    private String communityName;
    private String imageURL;
}
