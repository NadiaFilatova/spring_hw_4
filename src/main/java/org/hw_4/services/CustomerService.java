package org.hw_4.services;

import org.hw_4.entity.Customer;
import org.hw_4.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Transactional
    public void save(Customer customer) {
        customerRepository.save(customer);
    }
    //task3
    public Optional<Customer> findCustomerById(int id) {
        return customerRepository.findCustomerById(id);
    }

    public Optional<Customer> findCustomerWhereUsernameStartsWith(String statement) {
        return customerRepository.findCustomerByUsernameStartingWith(statement);
    }
    @Transactional
    public void deleteCustomersWhoIsNotAdult(){
        customerRepository.deleteCustomerDueToAge();
    }
}
