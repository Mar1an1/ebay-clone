package com.dev.mpolacek.ebayclone.controllers;

import com.dev.mpolacek.ebayclone.models.Item;
import com.dev.mpolacek.ebayclone.services.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> products = itemService.getAllItems();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemDetails(@PathVariable Long id) {
        Optional<Item> product = itemService.getItemById(id);

        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }

        return ResponseEntity.ok(product);
    }

    @PostMapping("/create")
    public ResponseEntity<Item> createItem(@RequestBody Item newItem) {
        itemService.create(newItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(newItem);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item updatedItem) {
        Optional<Item> item = itemService.getItemById(id);

        if (item.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Item updated = itemService.updateItem(updatedItem);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        boolean deleted = itemService.delete(id).getDeleted();

        if (deleted) {
            return ResponseEntity.ok("Product deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }
}
