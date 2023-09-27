package com.dev.mpolacek.ebayclone.repositories;

import com.dev.mpolacek.ebayclone.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsById(Long id);
}
