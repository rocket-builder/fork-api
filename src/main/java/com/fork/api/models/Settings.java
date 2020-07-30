package com.fork.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


@Entity
@JsonIgnoreProperties({ "user" })
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(mappedBy = "settings")
    private User user;

    private int balance_percent;

    public Settings() {}
    public Settings(User user, int balance_percent) {
        this.user = user;
        this.balance_percent = balance_percent;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public int getBalance_percent() { return balance_percent; }
    public void setBalance_percent(int balance_percent) { this.balance_percent = balance_percent; }
}
