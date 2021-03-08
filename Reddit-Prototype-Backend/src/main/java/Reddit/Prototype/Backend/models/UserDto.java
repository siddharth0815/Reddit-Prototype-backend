package Reddit.Prototype.Backend.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class UserDto {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String iconURL;
}
