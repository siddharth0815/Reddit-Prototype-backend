package Reddit.Prototype.Backend.repository;


import Reddit.Prototype.Backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
   Optional<User> findByUserName(String userName);


}
