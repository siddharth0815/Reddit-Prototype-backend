package Reddit.Prototype.Backend.repository;

import Reddit.Prototype.Backend.models.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community,Long> {

//    List<Community> findAll();
}
