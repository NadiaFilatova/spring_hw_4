package org.hw_4;

import org.hw_4.entity.Item;
import org.hw_4.repository.ItemRepository;
import org.hw_4.services.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ItemServiceTest {
    @Mock
    private ItemRepository itemRepository;
    @InjectMocks
    private ItemService itemService;

    //У першому тесті ми перевіряємо, чи виникає виняток IllegalArgumentException при спробі зберегти недійсний товар.
    @Test
    public void testSave_InvalidItem_ThrowsIllegalArgumentException() {
        // Arrange
        Item item = new Item();
        // Set invalid properties
        item.setPrice((long) -100);

        // Act and Assert
        try {
            itemService.save(item);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Exception is expected, so the test passes
        }
    }


    //метод setUp()- використовується у фреймворку тестування Mockito і виконується перед кожним тестом у класі ItemServiceTest
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        itemService = new ItemService(itemRepository);
    }

    //Цей тест перевіряє роботу методу findAll() класу ItemService.
    @Test
    public void testFindAllItems() {
        // Arrange: створюється список items, що містить два об'єкти типу Item.
        // Ці об'єкти будуть використовуватись для підставлення в результат методу findAll() у itemRepository.
        //when(itemRepository.findAll()).thenReturn(items); встановлює поведінку для itemRepository. Вказується,
        // що коли метод findAll() викликається на itemRepository, то він повинен повернути список items, який був створений раніше.
        List<Item> items = new ArrayList<>();
        items.add(new Item());
        items.add(new Item());
        when(itemRepository.findAll()).thenReturn(items);

        // Act:викликається метод findAll() у об'єкта itemService, і його результат зберігається у змінну result.
        List<Item> result = itemService.findAll();

        // Assert:перевіряється очікуваний результат. Вказується, що розмір списку result повинен бути рівним 2
        // (assertEquals(2, result.size())). Також перевіряється, що метод findAll() був викликаний один раз
        // (verify(itemRepository, times(1)).findAll()).
        //verifyNoMoreInteractions(itemRepository) перевіряє, що після виклику findAll() на itemRepository
        // не відбулось інших взаємодій з цим об'єктом. Це перевіряє,
        // що метод findAll() був єдиним методом, який був викликаний на itemRepository і немає неперевірених додаткових взаємодій.
        assertEquals(2, result.size());
        verify(itemRepository, times(1)).findAll();
        verifyNoMoreInteractions(itemRepository);
    }

    @Test
    public void testSaveItem() {
        // Arrange
        Item item = new Item();

        when(itemRepository.save(item)).thenReturn(item);

        // Act
        itemService.save(item);

        // Assert
        verify(itemRepository, times(1)).save(item);
        verifyNoMoreInteractions(itemRepository);
    }

    @Test
    public void testChangePriceForVacuumCleaner() {
        // Act
        itemService.changePriceForVacuumCleaner();

        // Assert
        verify(itemRepository, times(1)).setPriceForVacuumCleaner();
        verifyNoMoreInteractions(itemRepository);
    }

    // цей тест падає. знайти помилку
    @Test
    public void testChangePriceByItemName() {
        Item item = new Item();
        item.setId(1);
        item.setItemName("Item1");
        item.setPrice(100L);
        when(itemRepository.findById(1)).thenReturn(Optional.of(item));

        Item finalItem = item;
        doAnswer(invocationOnMock -> {
            finalItem.setPrice(200L);
            return null;
        }).when(itemRepository).updatePriceByItemName(200L, "Item1");

        item = itemRepository.findById(1).get();
        assertEquals(200, item.getPrice());

        verify(itemRepository, times(1)).updatePriceByItemName(200L, "Item1");
        verifyNoMoreInteractions(itemRepository);
        // Act
        itemService.changePriceForVacuumCleaner();

        // Assert
        // verify(itemRepository, times(1)).setPriceForVacuumCleaner();
        // verifyNoMoreInteractions(itemRepository);
    }


}

