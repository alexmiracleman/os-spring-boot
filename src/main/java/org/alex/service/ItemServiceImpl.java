package org.alex.service;

import lombok.RequiredArgsConstructor;
import org.alex.repository.ItemRepository;
import org.alex.entity.Item;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> addItemReturnAll(Item itemToAdd) {
        LocalDateTime now = LocalDateTime.now();
        itemToAdd.setCreationModificationDate(now);

        itemRepository.add(itemToAdd);
        return itemRepository.findAll();
    }

    @Override
    public List<Item> deleteItemReturnAll(Item itemToRemove) {
        itemRepository.delete(itemToRemove);
        return itemRepository.findAll();
    }

    @Override
    public List<Item> updateItemReturnAll(Item itemToUpdate) {
        LocalDateTime now = LocalDateTime.now();
        itemToUpdate.setCreationModificationDate(now);

        itemRepository.update(itemToUpdate);
        return itemRepository.findAll();
    }

    @Override
    public Item findByIdInCart(List cart, int id) {
        List<Item> items = cart;
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    @Override
    public void deleteAll() {
        itemRepository.deleteAll();
    }
}
