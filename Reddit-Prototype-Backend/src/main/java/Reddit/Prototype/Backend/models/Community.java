package Reddit.Prototype.Backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Parent;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
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
}
