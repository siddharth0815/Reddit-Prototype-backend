package Reddit.Prototype.Backend.repository;

import Reddit.Prototype.Backend.models.Content;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findByParentId(Long parentId);

    List<Content> findByParentId(Long parentId, Pageable pageable);
}
