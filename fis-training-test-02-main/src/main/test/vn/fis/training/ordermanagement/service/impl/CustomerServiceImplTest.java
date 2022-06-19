package vn.fis.training.ordermanagement.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {
    @Autowired
    private CustomerServiceImpl customerService = new CustomerServiceImpl();
    @Test
    void createCustomer() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomerById() {
    }

    @Test
    void findAll() {
        assertEquals(2,customerService.findAll());
    }

    @Test
    void findByMobileNumber() {
    }
}