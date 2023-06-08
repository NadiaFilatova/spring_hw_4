package org.hw_4.test;

import org.hw_4.entity.Customer;
import org.hw_4.repository.CustomerRepository;
import org.hw_4.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerService(customerRepository);
    }

    @Test
    public void testFindAllCustomers() {
        // Arrange
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        // Act
        List<Customer> result = customerService.findAll();

        // Assert
        assertEquals(2, result.size());
        verify(customerRepository, times(1)).findAll();
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    public void testSaveCustomer() {
        // Arrange
        Customer customer = new Customer();

        when(customerRepository.save(customer)).thenReturn(customer);

        // Act
        customerService.save(customer);

        // Assert
        verify(customerRepository, times(1)).save(customer);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    public void testFindCustomerById() {
        // Arrange
        int customerId = 1;
        Customer customer = new Customer();
        customer.setId(customerId);

        when(customerRepository.findCustomerById(customerId)).thenReturn(Optional.of(customer));

        // Act
        Optional<Customer> result = customerService.findCustomerById(customerId);

        // Assert
        assertEquals(customerId, result.get().getId());
        verify(customerRepository, times(1)).findCustomerById(customerId);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    public void testFindCustomerByUsernameStartingWith() {
        // Arrange
        String username = "user";
        Customer customer = new Customer();
        customer.setUsername(username);

        when(customerRepository.findCustomerByUsernameStartingWith(username)).thenReturn(Optional.of(customer));

        // Act
        Optional<Customer> result = customerService.findCustomerWhereUsernameStartsWith(username);

        // Assert
        assertEquals(username, result.get().getUsername());
        verify(customerRepository, times(1)).findCustomerByUsernameStartingWith(username);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    public void testDeleteCustomersWhoIsNotAdult() {
        // Act
        customerService.deleteCustomersWhoIsNotAdult();

        // Assert
        verify(customerRepository, times(1)).deleteCustomerDueToAge();
        verifyNoMoreInteractions(customerRepository);
    }
}
