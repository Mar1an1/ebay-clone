package com.dev.mpolacek.ebayclone.services;

import com.dev.mpolacek.ebayclone.exceptions.NotFoundException;
import com.dev.mpolacek.ebayclone.models.Item;
import com.dev.mpolacek.ebayclone.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImp implements ItemService{

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImp(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void create(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void delete(long id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isEmpty()) {
            throw new NotFoundException();
        }
        itemRepository.delete(item.get());
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Page<Item> getItemsBySellerId(Long id, Pageable pageable) {
        Page<Item> items = itemRepository.findBySellerUsernameAndDeletedIsFalse(id, pageable);
        List<Item> itemList = new ArrayList<>();
        items.forEach(itemList::add);

        return new PageImpl<Item>(itemList, pageable, items.getTotalElements());
    }
}