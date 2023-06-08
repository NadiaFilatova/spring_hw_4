package org.hw_4.test;

import org.hw_4.controller.CustomerController;
import org.hw_4.entity.Customer;
import org.hw_4.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class CustomerControllerTest {

    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerController = new CustomerController(customerService);
    }

    @Test
    void getAllCustomers_ReturnsListOfCustomers() {
        // Arrange
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setUsername("John");
        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setUsername("Jane");
        List<Customer> customers = Arrays.asList(customer1, customer2);
        when(customerService.findAll()).thenReturn(customers);

        // Act
        ResponseEntity<List<Customer>> response = customerController.getAllCustomers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customers, response.getBody());
    }

    @Test
    void getCustomerById_ExistingId_ReturnsCustomer() {
        // Arrange
        int customerId = 1;
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setUsername("John");
        when(customerService.findCustomerById(customerId)).thenReturn(Optional.of(customer));

        // Act
        ResponseEntity<Customer> response = customerController.getCustomerById(customerId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    void getCustomerById_NonExistingId_ReturnsNotFound() {
        // Arrange
        int customerId = 1;
        when(customerService.findCustomerById(customerId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Customer> response = customerController.getCustomerById(customerId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertFalse(response.getBody().isPresent());
    }

    @Test
    void createCustomer_ValidCustomer_ReturnsCreated() {
        // Arrange
        Customer customer = new Customer();
        customer.setId(1);
        customer.setUsername("John");
        // Act
        ResponseEntity<Void> response = customerController.createCustomer(customer);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(customerService, times(1)).save(customer);
    }
}
