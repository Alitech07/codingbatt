package restapi.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restapi.codingbat.entity.Category;
import restapi.codingbat.entity.Problems;

public interface ProblemsRepository extends JpaRepository<Problems,Integer> {
    boolean existsProblemsByBodyAndTitleAndCategoryId(String body, String title, Integer categoryId);
}
