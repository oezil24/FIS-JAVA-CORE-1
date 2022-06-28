package fis.com.test_final.service.impl;

import fis.com.test_final.dto.CreateCustomerDTO;
import fis.com.test_final.dto.CustomerDTO;
import fis.com.test_final.model.Customer;
import fis.com.test_final.repository.CustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class CustomerServiceImplTest {

    private CustomerServiceImpl customerService;

    private CustomerRepo customerRepo;
    private Customer customer;
    private Pageable pageable;

    @BeforeEach
    void init() {
        pageable = mock(Pageable.class);
        customerRepo = mock(CustomerRepo.class);
        customerService = new CustomerServiceImpl(customerRepo);
        customer = Customer.builder()
                .id(1L)
                .name("Nguyen Cat Tuan")
                .address("Ha Tay")
                .mobile("0123456789")
                .build();
    }

    @Test
    void findAll() {
        List<Customer> customerList = new ArrayList<>();
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        Customer customer3 = new Customer();
        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);
        Page<Customer> customers = new PageImpl<>(customerList);

        when(customerRepo.findAll(pageable)).thenReturn(customers);
        log.info("Customer List size: {}", customerList.size());
        Page<CustomerDTO> customerDTOPage = customerService.findAll(pageable);

        assertEquals(customerDTOPage.getTotalElements(), customers.getTotalElements());
        verify(customerRepo, times(1)).findAll(pageable);
    }

    @Test
    void findById() {
        when(customerRepo.findById(1L)).thenReturn(Optional.ofNullable(customer));
        Customer customer1 = customerService.findById(1L);
        log.info("Customer has id = 1L: {}", customer);
        assertThat(customer1).isNotNull();
        assertThat(customer1.getName()).isEqualTo("Nguyen Cat Tuan");
        assertThat(customer1.getAddress()).isEqualTo("Ha Tay");
        assertThat(customer1.getMobile()).isEqualTo("0123456789");
    }

    @Test
    void create() {
        List<Customer> customerList = new ArrayList<>();
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer);
        Page<Customer> customerPage = new PageImpl<>(customerList);
        when(customerRepo.findAll(pageable)).thenReturn(customerPage);
        CreateCustomerDTO createCustomerDTO = CreateCustomerDTO.builder()
                .name("Nguyen Cat Tuan")
                .address("Ha Tay")
                .mobile("0123456789")
                .build();
        Page<CustomerDTO> customerPageCreateAfter = customerService.create(createCustomerDTO, pageable);
        customerPageCreateAfter.forEach(customerInPage -> log.info("Customer in page: {}", customerInPage));
        assertEquals(customerPage.getTotalElements(), customerPageCreateAfter.getTotalElements());
    }

    @Test
    void update_WhenMobileNotExisted() {
        log.info("Update before: {}", customer);
        customer.setMobile("0985850178");
        customer.setAddress("Ha Noi");
        when(customerRepo.save(customer)).thenReturn(customer);

        CustomerDTO customerDTO = CustomerDTO.builder()
                .address("Ha Noi")
                .mobile("0985850178")
                .build();
        when(customerRepo.findByMobile("0985850178")).thenReturn(null);
        when(customerRepo.findById(1L)).thenReturn(Optional.ofNullable(customer));
        CustomerDTO updatedCustomer = customerService.update(1L, customerDTO);
        log.info("Update after: {}", updatedCustomer);
        assertThat(updatedCustomer.getMobile()).isEqualTo("0985850178");
        assertThat(updatedCustomer.getAddress()).isEqualTo("Ha Noi");
    }

    @Test
    void delete() {
        Long id = 1L;
        willDoNothing().given(customerRepo).deleteById(id);
        customerService.delete(id);
        verify(customerRepo, times(1)).deleteById(id);
    }
}