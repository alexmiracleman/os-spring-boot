package org.alex.service;

import org.alex.entity.Item;

import java.util.List;

public interface ItemService {

    List<Item> findAll();


    List<Item> addItemReturnAll(Item itemToAdd);


    List<Item> deleteItemReturnAll(Item itemToRemove);


    List<Item> updateItemReturnAll(Item itemToUpdate);

    Item findByIdInCart(List cart, int id);

    void deleteAll();

}


