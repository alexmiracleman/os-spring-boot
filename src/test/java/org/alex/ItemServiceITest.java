package org.alex;

import org.alex.entity.Item;
import org.alex.entity.ItemDepartmentType;
import org.alex.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@DisplayName("ItemService integration test")
public class ItemServiceITest {

    @Autowired
    private ItemService itemService;

    private Item item1 = Item.builder()
            .name("Sofa")
            .price(1)
            .itemDepartmentType(ItemDepartmentType.FURNITURE)
            .creationModificationDate(LocalDateTime.now())
            .build();

    private Item item2 = Item.builder()
            .name("TV")
            .price(2)
            .itemDepartmentType(ItemDepartmentType.ELECTRONICS)
            .creationModificationDate(LocalDateTime.now())
            .build();

    private Item item3 = Item.builder()
            .name("Milk")
            .price(3)
            .itemDepartmentType(ItemDepartmentType.GROCERIES)
            .creationModificationDate(LocalDateTime.now())
            .build();

    @BeforeEach
    void setUp() {
        itemService.deleteAll();
        itemService.addItemReturnAll(item1);
        itemService.addItemReturnAll(item2);
        itemService.addItemReturnAll(item3);
    }

    @Test
    @DisplayName("Get all items from DB test")
    public void findAllItemsTest() {
        List<Item> items = itemService.findAll();
        int expectedSize = 3;
        int actualSize = items.size();

        Item expectedFirstItem = item1;
        Item expectedSecondItem = item2;
        Item expectedThirdItem = item3;

        assertFalse(items.isEmpty());
        assertEquals(expectedSize, actualSize);

        assertEquals(expectedFirstItem.getName(), items.get(0).getName());
        assertEquals(expectedSecondItem.getName(), items.get(1).getName());
        assertEquals(expectedThirdItem.getName(), items.get(2).getName());

        assertEquals(expectedFirstItem.getPrice(), items.get(0).getPrice());
        assertEquals(expectedSecondItem.getPrice(), items.get(1).getPrice());
        assertEquals(expectedThirdItem.getPrice(), items.get(2).getPrice());

        assertEquals(expectedFirstItem.getItemDepartmentType(), items.get(0).getItemDepartmentType());
        assertEquals(expectedSecondItem.getItemDepartmentType(), items.get(1).getItemDepartmentType());
        assertEquals(expectedThirdItem.getItemDepartmentType(), items.get(2).getItemDepartmentType());
    }

    @Test
    @DisplayName("Add new item to DB test")
    void addUserWithTheDuplicateEmail() {

        Item item = Item.builder()
                .name("Bed")
                .price(5)
                .itemDepartmentType(ItemDepartmentType.FURNITURE)
                .creationModificationDate(LocalDateTime.now())
                .build();

        List<Item> items = itemService.addItemReturnAll(item);


        int expectedSize = 4;
        int actualSize = items.size();
        assertEquals(expectedSize, actualSize);

    }
    @Test
    @DisplayName("Update item price")
    void updateItemPrice() {

        Item item = Item.builder()
                .name("Sofa")
                .price(5)
                .build();


        List<Item> items = itemService.updateItemReturnAll(item);

        int expectedSize = 3;
        int actualSize = items.size();
        assertEquals(expectedSize, actualSize);

        int expectedPrice = 5;
        int actualPrice = items.get(0).getPrice();
        assertEquals(expectedPrice, actualPrice);

    }
    @Test
    @DisplayName("Delete item by name")
    void deleteItemByName() {

        Item item = Item.builder()
                .name("TV")
                .build();


        List<Item> items = itemService.deleteItemReturnAll(item);

        int expectedSize = 2;
        int actualSize = items.size();
        assertEquals(expectedSize, actualSize);

    }

}
