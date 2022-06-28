package fis.com.test_final.repository;

import fis.com.test_final.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByMobile(String mobile);
}
