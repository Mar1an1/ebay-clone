package com.dev.mpolacek.ebayclone.services;

import com.dev.mpolacek.ebayclone.exceptions.NotFoundException;
import com.dev.mpolacek.ebayclone.models.Bid;
import com.dev.mpolacek.ebayclone.repositories.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BidServiceImp implements BidService{

    private final BidRepository bidRepository;

    @Autowired
    public BidServiceImp(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    @Override
    public void create(Bid bid) {
        bidRepository.save(bid);
    }

    @Override
    public void delete(Long id) {
        Optional<Bid> bid = bidRepository.findById(id);
        if (bid.isEmpty()) {
            throw new NotFoundException();
        }
        bidRepository.delete(bid.get());
    }

    @Override
    public List<Bid> getAllBids() {
        return bidRepository.findAll();
    }

    @Override
    public Optional<Bid> getBidById(Long id) {
        return bidRepository.findById(id);
    }

    @Override
    public List<Bid> getBidsByBidderId(Long id) {
        return bidRepository.findAll()
                .stream()
                .filter(bid -> Objects.equals(bid.getBidder().getId(), id))
                .collect(Collectors.toList());
    }
}
