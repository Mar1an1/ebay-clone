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
import java.util.Date;
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

    @Override
    public Item updateItem(Item updatedItem) {
        Optional<Item> existingProduct = itemRepository.findById(updatedItem.getId());

        if (existingProduct.isEmpty()) {
            throw new NotFoundException();
        }

        existingProduct.get().setName(updatedItem.getName());
        existingProduct.get().setDescription(updatedItem.getDescription());
        existingProduct.get().setPhotoUrl(updatedItem.getPhotoUrl());
        existingProduct.get().setStartingPrice(updatedItem.getStartingPrice());
        existingProduct.get().setPurchasePrice(updatedItem.getPurchasePrice());
        existingProduct.get().setSellerUsername(updatedItem.getSellerUsername());
        existingProduct.get().setDeleted(updatedItem.getDeleted());
        existingProduct.get().setModifiedAt(updatedItem.getModifiedAt());
        existingProduct.get().setBids(updatedItem.getBids());

        return itemRepository.save(existingProduct.get());
    }

    @Override
    public Item delete(long id) {
        Optional<Item> existingItem = itemRepository.findById(id);

        if (existingItem.isEmpty()) {
            throw new NotFoundException();
        }

        existingItem.get().setDeleted(true);
        existingItem.get().setModifiedAt(new Date());

        return itemRepository.save(existingItem.get());
    }
}