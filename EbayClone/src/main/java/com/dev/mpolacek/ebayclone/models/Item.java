package com.dev.mpolacek.ebayclone.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String photoUrl;
    private int startingPrice;
    private int purchasePrice;
    private String sellerUsername;
    private Boolean deleted;
    private Date modifiedAt;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Bid> bids;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    public Item() {
    }

    public Item(String name, String description, String photoUrl, int startingPrice, int purchasePrice, String sellerUsername, List<Bid> bids, User seller, Boolean deleted, Date modifiedAt) {
        this.name = name;
        this.description = description;
        this.photoUrl = photoUrl;
        this.startingPrice = startingPrice;
        this.purchasePrice = purchasePrice;
        this.sellerUsername = sellerUsername;
        this.bids = bids;
        this.seller = seller;
        this.deleted = deleted;
        this.modifiedAt = modifiedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(int startingPrice) {
        this.startingPrice = startingPrice;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
