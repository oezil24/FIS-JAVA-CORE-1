package fis.com.test_final.repository;

import fis.com.test_final.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
@SpringBootTest
@Slf4j
class CustomerRepoTest {
    @Autowired
    private CustomerRepo customerRepo;
    @Test
    void testCreateReadUpdateDelete() {
        Customer customer = Customer.builder()
                .name("Le Viet Ha")
                .mobile("0968556308")
                .address("Ha Noi")
                .build();
        customerRepo.save(customer);
        log.info("Saved Customer: {}", customer);

        List<Customer> customerList = customerRepo.findAll();
        customerList.forEach(customerInList -> log.info("Customer List: {}", customerInList));
        assertThat(customerList).extracting(Customer::getName).contains("Le Viet Ha");
        assertThat(customerList).extracting(Customer::getMobile).contains("0968556308");
        assertThat(customerList).extracting(Customer::getAddress).contains("Ha Noi");

        log.info("Update before: {}", customer);
        customer.setName("Nguyen Cat Tuan");
        Customer updatedCustomer = customerRepo.save(customer);
        log.info("Update after: {}", updatedCustomer);
        assertThat("Nguyen Cat Tuan".equals(customerRepo.findById(1L).orElseThrow().getName()));

        customerRepo.deleteAll();
        assertThat(customerRepo.findAll().isEmpty());
    }
}