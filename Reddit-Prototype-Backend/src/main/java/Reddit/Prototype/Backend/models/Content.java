package Reddit.Prototype.Backend.models;

import javax.persistence.*;
import org.hibernate.annotations.Parent;


@Entity
@Table(name = "Content")
public class Content {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "parentId")
    private Long parentId;

    @Column(name = "contentBody")
    private String contentBody;

    @Column(name = "imageURL")
    private String imageURL;

    @Column(name = "Reactions")
    private String reactions;

    @Column(name = "upvotes")
    private Long upvotes;

    @Column(name = "downvotes")
    private Long downvotes;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Content() {
    }

    public Content(Long parentId, String contentBody, String imageURL, String reactions, Long upvotes, Long downvotes, Community community, User user) {
        this.parentId = parentId;
        this.contentBody = contentBody;
        this.imageURL = imageURL;
        this.reactions = reactions;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.community = community;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getContentBody() {
        return contentBody;
    }

    public void setContentBody(String contentBody) {
        this.contentBody = contentBody;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getReactions() {
        return reactions;
    }

    public void setReactions(String reactions) {
        this.reactions = reactions;
    }

    public Long getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Long upvotes) {
        this.upvotes = upvotes;
    }

    public Long getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(Long downvotes) {
        this.downvotes = downvotes;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
