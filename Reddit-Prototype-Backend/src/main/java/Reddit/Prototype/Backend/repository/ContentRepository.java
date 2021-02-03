package Reddit.Prototype.Backend.repository;

import Reddit.Prototype.Backend.models.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
