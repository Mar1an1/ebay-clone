package com.dev.mpolacek.ebayclone.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private int greenBayDollars;
    @OneToMany(mappedBy = "bidder")
    private List<Bid> bids;
    @OneToMany(mappedBy = "seller")
    private List<Item> items;

    public User(String username, String password, int greenBayDollars) {
        this.username = username;
        this.password = password;
        this.greenBayDollars = greenBayDollars;
    }

    public User() {
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
}
