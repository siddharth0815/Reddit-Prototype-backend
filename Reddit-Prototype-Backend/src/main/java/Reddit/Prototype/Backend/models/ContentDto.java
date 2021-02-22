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
    private String imageURL;
    private Long parentId;
    private Long votes;
    private String userName;
    private String communityName;
}
