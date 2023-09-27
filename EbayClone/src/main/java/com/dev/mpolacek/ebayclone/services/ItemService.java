package com.dev.mpolacek.ebayclone.services;

import com.dev.mpolacek.ebayclone.models.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    void create(Item item);
    void delete(long id);
    List<Item> getAllItems();
    Optional<Item> getItemById(Long id);
    List<Item> getItemsBySellerId(Long id);
}
