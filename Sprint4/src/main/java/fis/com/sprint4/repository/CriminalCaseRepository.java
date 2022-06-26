package fis.com.sprint4.repository;


import fis.com.sprint4.entity.CriminalCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriminalCaseRepository extends JpaRepository<CriminalCase, Long> {
}
