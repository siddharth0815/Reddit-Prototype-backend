package Reddit.Prototype.Backend.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Parent;

@Getter
@Setter
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

    @Column(columnDefinition = "integer default 0",name = "upvotes")
    private Long upvotes;

    @Column(columnDefinition = "integer default 0",name = "downvotes")
    private Long downvotes;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Content() {
        this.upvotes = Long.valueOf(0);
        this.downvotes = Long.valueOf(0);
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
}

