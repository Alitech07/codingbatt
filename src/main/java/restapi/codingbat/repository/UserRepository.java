package restapi.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restapi.codingbat.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByEmail(String email);
}
