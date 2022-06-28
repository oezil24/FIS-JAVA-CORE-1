package fis.com.test_final.repository;

import fis.com.test_final.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
}
