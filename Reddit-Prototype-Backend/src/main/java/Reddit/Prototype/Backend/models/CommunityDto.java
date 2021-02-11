package Reddit.Prototype.Backend.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class CommunityDto {
    private Long id;
    private String title;
    private String description;
    private Long upvotes;
    private Long downvotes;
}
