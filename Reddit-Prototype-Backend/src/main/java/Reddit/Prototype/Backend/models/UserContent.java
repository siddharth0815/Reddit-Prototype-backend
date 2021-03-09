package Reddit.Prototype.Backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class UserContent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="content_id")
    private Content content;

    private int votes;

    private int reaction;

    public UserContent() {
        votes = 0;
        reaction = 0;
    }

    public void setReaction(int reaction) {
        this.reaction = reaction;
    }
}
