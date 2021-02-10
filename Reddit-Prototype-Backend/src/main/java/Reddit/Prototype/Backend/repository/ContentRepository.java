package Reddit.Prototype.Backend.repository;

import Reddit.Prototype.Backend.models.Content;
import Reddit.Prototype.Backend.models.ContentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {

    List<Content> findByParentId(Long parentId);
}
