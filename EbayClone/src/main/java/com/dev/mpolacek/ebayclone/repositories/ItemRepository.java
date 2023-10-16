package com.dev.mpolacek.ebayclone.repositories;

import com.dev.mpolacek.ebayclone.models.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsById(Long id);

    Page<Item> findBySellerUsernameAndDeletedIsFalse(Long userId, Pageable pageable);

    Optional<Item> findByIdAndDeletedIsFalse(Long itemId);
}
