package Reddit.Prototype.Backend.models;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserContentDto {
    private Long userId;
    private Long contentId;
    private Long votes;
    private Long reaction;
}
