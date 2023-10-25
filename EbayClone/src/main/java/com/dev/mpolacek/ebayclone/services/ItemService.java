package com.dev.mpolacek.ebayclone.services;

import com.dev.mpolacek.ebayclone.models.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    void create(Item item);
    Item delete(long id);
    List<Item> getAllItems();
    Optional<Item> getItemById(Long id);
    Page<Item> getItemsBySellerId(Long id, Pageable pageable);
    Item updateItem(Item updatedItem);
}
