package com.dev.mpolacek.ebayclone.repositories;

import com.dev.mpolacek.ebayclone.models.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
    boolean existsById(Long id);
}
