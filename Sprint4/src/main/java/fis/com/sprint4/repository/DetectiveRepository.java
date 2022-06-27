package fis.com.sprint4.repository;

import fis.com.sprint4.entity.Detective;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetectiveRepository extends JpaRepository<Detective, Long> {
    Detective findDetectiveByPersonUsername(String username);
}
