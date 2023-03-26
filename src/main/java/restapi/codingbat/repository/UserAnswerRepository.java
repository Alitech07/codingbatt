package restapi.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restapi.codingbat.entity.UserAnswer;

public interface UserAnswerRepository extends JpaRepository<UserAnswer,Integer> {
}
