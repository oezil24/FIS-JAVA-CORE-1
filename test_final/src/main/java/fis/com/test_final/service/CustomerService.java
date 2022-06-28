package fis.com.test_final.service;

import fis.com.test_final.dto.CreateCustomerDTO;
import fis.com.test_final.dto.CustomerDTO;
import fis.com.test_final.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CustomerService {
    Page<CustomerDTO> findAll(Pageable pageable);
    Customer findById(Long customerId);

    Page<CustomerDTO> create(CreateCustomerDTO customerDTO, Pageable pageable);

    CustomerDTO update(Long customerId, CustomerDTO customerDTO);

    void delete(Long customerId);
}
