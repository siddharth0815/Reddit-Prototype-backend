package Reddit.Prototype.Backend.repository;

import Reddit.Prototype.Backend.models.UserContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserContentRepository extends JpaRepository<UserContent, Long> {
    Optional<UserContent> findByUserIdAndContentId(Long userId, Long contentId);
    List<UserContent> findByContentId(Long contentId);
    List<UserContent> findByUserId(Long userId);


}
