package restapi.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restapi.codingbat.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    boolean existsByName(String name);
}
