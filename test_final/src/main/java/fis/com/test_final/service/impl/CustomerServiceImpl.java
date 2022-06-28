package fis.com.test_final.service.impl;


import fis.com.test_final.dto.CreateCustomerDTO;
import fis.com.test_final.dto.CustomerDTO;
import fis.com.test_final.exception.CustomerNotFoundException;
import fis.com.test_final.exception.MobileExistedException;
import fis.com.test_final.model.Customer;
import fis.com.test_final.repository.CustomerRepo;
import fis.com.test_final.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepo;

    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public Page<CustomerDTO> findAll(Pageable pageable) {
        log.info("Query all customer: PageNumber: {}, PageSize: {}", pageable.getPageNumber(), pageable.getPageSize());
        return customerRepo.findAll(pageable).map(CustomerDTO.Mapper::mapFromCustomerEntity);
    }

    @Override
    public Customer findById(Long customerId) {
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> {
            try {
                throw new CustomerNotFoundException(String.format("Not found customer with id %s", customerId));
            } catch (CustomerNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        log.info("Service: Customer: {}", customer);
        return customer;
    }

    @Override
    public Page<CustomerDTO> create(CreateCustomerDTO createCustomerDTO, Pageable pageable) {
        Customer customer = Customer.builder()
                .name(createCustomerDTO.getName())
                .mobile(createCustomerDTO.getMobile())
                .address(createCustomerDTO.getAddress())
                .build();
        customerRepo.save(customer);
        log.info("Saved Customer: {}", customer);
        return findAll(pageable);
    }

    @Override
    public CustomerDTO update(Long customerId, CustomerDTO customerDTO) {
        Customer customer = customerRepo.findByMobile(customerDTO.getMobile());
        if(null != customer) {
            try {
                throw new MobileExistedException(String.format("Mobile %s is existed!!!", customerDTO.getMobile()));
            } catch (MobileExistedException e) {
                throw new RuntimeException(e);
            }

        }
        Customer savedCustomer = customerRepo.findById(customerId).get();
        savedCustomer.setAddress(customerDTO.getAddress());
        savedCustomer.setMobile(customerDTO.getMobile());
        Customer updatedCustomer = customerRepo.save(savedCustomer);
        log.info("Customer update after: {}", updatedCustomer);
        return CustomerDTO.Mapper.mapFromCustomerEntity(updatedCustomer);
    }

    @Override
    public void delete(Long customerId) {
            customerRepo.deleteById(customerId);
    }
}
