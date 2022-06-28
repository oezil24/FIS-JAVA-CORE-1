package fis.com.test_final.repository;

import fis.com.test_final.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
}
