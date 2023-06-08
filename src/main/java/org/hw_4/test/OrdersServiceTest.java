package org.hw_4.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.hw_4.entity.Orders;
import org.hw_4.repository.OrdersRepository;
import org.hw_4.services.OrdersService;

@RunWith(MockitoJUnitRunner.class)
public class OrdersServiceTest {

    @Mock
    private OrdersRepository ordersRepository;

    private OrdersService ordersService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ordersService = new OrdersService(ordersRepository);
    }
    //У тесті testFindAllOrders ми налаштовуємо мок-об'єкт таким чином, що виклик ordersRepository.findAll()
    // повертає список з двома замовленнями. Потім ми викликаємо метод findAll на ordersService і перевіряємо,
    // що отриманий результат має розмір 2.
    //Нарешті, ми перевіряємо, що метод ordersRepository.findAll() був викликаний один раз,
    // і що більше взаємодій з ordersRepository не відбулося.
    @Test
    public void testFindAllOrders() {
        // Arrange
        List<Orders> orders = new ArrayList<>();
        orders.add(new Orders());
        orders.add(new Orders());

        when(ordersRepository.findAll()).thenReturn(orders);

        // Act
        List<Orders> result = ordersService.findAll();

        // Assert
        assertEquals(2, result.size());
        verify(ordersRepository, times(1)).findAll();
        verifyNoMoreInteractions(ordersRepository);
    }
    //У методі testSaveOrder ми створюємо нове замовлення Orders, потім викликаємо метод save на ordersService з цим замовленням.
    // Нарешті, ми перевіряємо, що метод ordersRepository.save(order)
    // був викликаний один раз, і що більше взаємодій з ordersRepository не відбулося.
    @Test
    public void testSaveOrder() {
        // Arrange
        Orders order = new Orders();

        // Act
        ordersService.save(order);

        // Assert
        verify(ordersRepository, times(1)).save(order);
        verifyNoMoreInteractions(ordersRepository);
    }

}

