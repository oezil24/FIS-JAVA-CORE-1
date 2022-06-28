package fis.com.test_final.controller;

import fis.com.test_final.dto.CreateCustomerDTO;
import fis.com.test_final.dto.CustomerDTO;
import fis.com.test_final.model.Customer;
import fis.com.test_final.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@Slf4j
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public Page<CustomerDTO> findAll(@PathVariable(name = "pageNumber") Integer pageNumber
            , @PathVariable(name = "pageSize") Integer pageSize) {
        log.info("Response All Customer. PageNumber: {}, PageSize: {}", pageNumber, pageSize);
        return customerService.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable(name = "customerId") Long customerId) {
        Customer customer = customerService.findById(customerId);
        CustomerDTO customerDTO = CustomerDTO.Mapper.mapFromCustomerEntity(customer);
        log.info("Customer find by id: {}", customerDTO);
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @PostMapping("/{pageNumber}/{pageSize}")
    public Page<CustomerDTO> create(@RequestBody CreateCustomerDTO createCustomerDTO,
                                    @PathVariable(name = "pageNumber") Integer pageNumber
            , @PathVariable(name = "pageSize") Integer pageSize) {
        Page<CustomerDTO> customerDTOPage = customerService.create(createCustomerDTO, PageRequest.of(pageNumber, pageSize));
        return customerDTOPage;
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> update(@PathVariable("customerId") Long customerId,
                                              @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = customerService.update(customerId, customerDTO);
        log.info("Updated customer: {}", updatedCustomer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> delete(@PathVariable("customerId") Long customerId) {
        customerService.delete(customerId);
        return new ResponseEntity<>(String.format("Customer with id %s deleted successfully!", customerId), HttpStatus.OK);
    }
}
