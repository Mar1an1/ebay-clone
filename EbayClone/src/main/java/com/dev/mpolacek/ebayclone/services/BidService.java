package com.dev.mpolacek.ebayclone.services;

import com.dev.mpolacek.ebayclone.models.Bid;

import java.util.List;
import java.util.Optional;

public interface BidService {
    void create(Bid bid);
    void delete(Long id);
    List<Bid> getAllBids();
    Optional<Bid> getBidById(Long id);
    List<Bid> getBidsByBidderId(Long id);
}
