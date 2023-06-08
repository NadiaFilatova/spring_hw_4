package org.hw_4.test;


import org.hw_4.entity.Item;
import org.hw_4.repository.ItemRepository;
import org.hw_4.services.ItemService;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

//У цьому прикладі ми використовуємо аннотації @Mock і @InjectMocks для створення підроблених об'єктів та внедрення їх в тести.
// В методі setUp() ми викликаємо MockitoAnnotations.openMocks(this) для ініціалізації моків.
// Тест testFindAllItems() перевіряє роботу методу findAll() в ItemRepository.
@RunWith(MockitoJUnitRunner.class)
public class ItemRepositoryTest {

    @Mock
    private ItemRepository itemRepository;

    private ItemService itemService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        itemService = new ItemService(itemRepository);
    }

    @Test
    public void testFindAllItems() {
        // Arrange
        List<Item> items = new ArrayList<>();
        items.add(new Item());
        items.add(new Item());

        when(itemRepository.findAll()).thenReturn(items);

        // Act
        List<Item> result = itemService.findAll();

        // Assert
        assertEquals(2, result.size());
        verify(itemRepository, times(1)).findAll();
        verifyNoMoreInteractions(itemRepository);
    }

}
