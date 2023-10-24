package org.alex.repository;


import org.alex.entity.Item;

import java.util.List;


public interface ItemRepository {

    List<Item> findAll();

    void add(Item item);

    void delete(Item item);

    void update(Item item);

    void deleteAll();
}
