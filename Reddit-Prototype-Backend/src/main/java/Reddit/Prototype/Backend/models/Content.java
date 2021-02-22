package Reddit.Prototype.Backend.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Parent;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "content")
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

    @Column(columnDefinition = "integer default 0", name = "votes")
    private Long votes;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy="content")
    List<UserContent> userContents;

    public Content() {
        this.votes = Long.valueOf(0);
    }
}

