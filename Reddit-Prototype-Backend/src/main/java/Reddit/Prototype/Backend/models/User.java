package Reddit.Prototype.Backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Content> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(List<Content> userPosts) {
        this.userPosts = userPosts;
    }

    public List<Community> getUserCommunities() {
        return userCommunities;
    }

    public void setUserCommunities(List<Community> userCommunities) {
        this.userCommunities = userCommunities;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }
}