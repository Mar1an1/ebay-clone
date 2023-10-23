package com.dev.mpolacek.ebayclone.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String password;
    private int greenBayDollars;
    @OneToMany(mappedBy = "bidder")
    private List<Bid> bids;
    @OneToMany(mappedBy = "seller")
    private List<Item> items;

    public User(String username, String password, int greenBayDollars, String email) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.greenBayDollars = greenBayDollars;
    }

    public User() {
    }

    public User(String email, String username, String encodedPassword) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGreenBayDollars() {
        return greenBayDollars;
    }

    public void setGreenBayDollars(int greenBayDollars) {
        this.greenBayDollars = greenBayDollars;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
