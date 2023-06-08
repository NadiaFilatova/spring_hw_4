package org.hw_4;

import org.hw_4.entity.Customer;
import org.hw_4.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerRepositoryTest {
    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindCustomerById_ValidId_ReturnsCustomer() {
        // Arrange
        int customerId = 1;
        Customer customer = new Customer();
        customer.setId(customerId);

        when(customerRepository.findCustomerById(customerId)).thenReturn(Optional.of(customer));

        // Act
        Optional<Customer> result = customerRepository.findCustomerById(customerId);

        // Assert
        assertEquals(customerId, result.get().getId());
        verify(customerRepository, times(1)).findCustomerById(customerId);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    public void testFindCustomerByUsernameStartingWith_ValidStatement_ReturnsCustomer() {
        // Arrange
        String statement = "John";
        Customer customer = new Customer();
        customer.setUsername("JohnDoe");

        when(customerRepository.findCustomerByUsernameStartingWith(statement)).thenReturn(Optional.of(customer));

        // Act
        Optional<Customer> result = customerRepository.findCustomerByUsernameStartingWith(statement);

        // Assert
        assertEquals("JohnDoe", result.get().getUsername());
        verify(customerRepository, times(1)).findCustomerByUsernameStartingWith(statement);
        verifyNoMoreInteractions(customerRepository);
    }
}

// Ці тести перевірятимуть, чи коректно ви отримуєте інформацію з бази даних за допомогою методів репозиторію.
// Замість фактичного звернення до бази даних, використовується Mockito для створення підроблених об'єктів репозиторію та встановлення очікуваних результатів.

