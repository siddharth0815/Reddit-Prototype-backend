package Reddit.Prototype.Backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "Users", uniqueConstraints={@UniqueConstraint(columnNames = "userName")})
//@JsonIgnoreProperties({"hibernateLazyIntializer", "handler","userCommunities"})
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column (unique = true)
    private String userName;
    @Column (unique = true)
    private String email;
    private String password;
    private boolean active;
    private String roles;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Content> userPosts;

    @ManyToMany(mappedBy = "communityUsers")
    private List<Community> userCommunities;

    @ManyToMany
    @JoinTable(
            name = "user_follower",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private List<User> following;
    @ManyToMany(mappedBy = "following")
    private List<User> followers;
}