package Reddit.Prototype.Backend.repository;

import Reddit.Prototype.Backend.models.Content;
import Reddit.Prototype.Backend.models.ContentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findByParentId(Long parentId);

    @Query( value = "SELECT * from Content c "
        + " ORDER BY "
        + " CASE WHEN :order = 'DESC' THEN c.votes END DESC, "
        + " CASE WHEN :order = 'ASC' THEN c.votes END ASC ",
        nativeQuery = true
    )
    List<Content> findCustom(@Param("order") String order);
}
