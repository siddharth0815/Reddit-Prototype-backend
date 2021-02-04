package Reddit.Prototype.Backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Parent;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Community")
@JsonIgnoreProperties({"hibernateLazyIntializer", "handler","communityUsers"})
public class Community {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "Title")
    private String title;

    @CreationTimestamp
    private Date createdAt;

    @Column(name = "Description")
    private String description;


    @Column(columnDefinition = "integer default 0",name = "upvotes")
    private Long upvotes;

    @Column(columnDefinition = "integer default 0",name = "downvotes")
    private Long downvotes;

    @ManyToMany
    @JoinTable(
            name = "user_community",
            joinColumns = @JoinColumn(name = "community_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> communityUsers;

    @OneToMany(mappedBy = "community", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Content> communityPosts;

    public Community() {
        this.upvotes =  Long.valueOf(0);
        this.downvotes = Long.valueOf(0);
    }

    public Community(String title, Date createdAt, String description, Long upvotes, List<User> communityUsers, List<Content> communityPosts, Long downvotes) {
        this.title = title;
        this.createdAt = createdAt;
        this.description = description;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.communityUsers = communityUsers;
        this.communityPosts = communityPosts;
    }

    public Long getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(Long downvotes) {
        this.downvotes = downvotes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Long upvotes) {
        this.upvotes = upvotes;
    }

    public List<User> getCommunityUsers() {
        return communityUsers;
    }

    public void setCommunityUsers(List<User> communityUsers) {
        this.communityUsers = communityUsers;
    }

    public List<Content> getCommunityPosts() {
        return communityPosts;
    }

    public void setCommunityPosts(List<Content> communityPosts) {
        this.communityPosts = communityPosts;
    }
}
