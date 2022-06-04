package vn.fis.training.service;

import vn.fis.training.domain.Customer;
import vn.fis.training.exception.CustomerNotFoundException;
import vn.fis.training.exception.DuplicateCustomerException;
import vn.fis.training.exception.InvalidCustomerException;
import vn.fis.training.store.InMemoryCustomerStore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleCustomerService implements CustomerService{

    private InMemoryCustomerStore customerStore;

    @Override
    public Customer findById(String id) throws IllegalAccessException {
        if (isNullOrEmpty(id)) {
            throw new IllegalAccessException("khong tim thay");
        }
        List<Customer> customerList = customerStore.findAll();
        for (int idx = 0; idx < customerList.size(); idx++) {
            if (customerList.get(idx).getId().equals(id)) {
                return customerList.get(idx);
            }
        }
        throw new CustomerNotFoundException(String.format("Khong tim thay"));
    }
    private boolean isNullOrEmpty(String id){
        if (id == null) return false;
        if (id.trim().length()==0) return false;
        return true;
    }
    @Override
    public Customer createCustomer(Customer customer) {
        validate(customer);
        checkDuplicate(customer);
        return customerStore.insertOrUpdate(customer);
    }

    private void checkDuplicate(Customer customer) {
        if (customerStore.findAll().stream().filter(customer1 -> {
            return customer1.getMobile().equals(customer.getMobile());
        }).findFirst().isPresent()){
            throw new DuplicateCustomerException(customer,String.format("format"));
        }
    }

    private void validate(Customer customer) {
        if (isNullOrEmpty(customer.getName())){
            throw new InvalidCustomerException(customer,"Ten trong");
        }
        if (isNullOrEmpty(customer.getMobile())){
            throw new InvalidCustomerException(customer,"dien thoai trong");
        }
    }

    @Override
    public Customer updateCustomer(Customer customer) throws IllegalAccessException {
        validate(customer);
        findById(customer.getId());
        return customerStore.insertOrUpdate(customer);
    }

    @Override
    public void deleteCustomerById(String id) throws IllegalAccessException {
        if (isNullOrEmpty(id)){
            throw new IllegalAccessException("khong tim thay id");
        }
        findById(id);
        customerStore.deleteById(id);
    }

    @Override
    public List<Customer> findAllOrderByNameAsc() {
        return customerStore.findAll().stream().sorted((Comparator.comparing(Customer::getName))).collect(Collectors.toList());
    }

    @Override
    public List<Customer> findAllOrderByNameLimit(int limit) {
        return customerStore.findAll().stream().sorted((Comparator.comparing(Customer::getName))).limit(limit).collect(Collectors.toList());
    }

    @Override
    public List<Customer> findAllCustomerByNameLikeOrderByNameAsc(String custName, String limit) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        return null;
    }

    @Override
    public List<SummaryCustomerByAgeDTO> summaryCustomerByAgeOrderByAgeDesc() {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        return null;
    }

}
