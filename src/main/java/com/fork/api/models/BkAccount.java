package com.fork.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({ "user", "bets"})
public class BkAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "settings_id", referencedColumnName = "id")
    private BkAccSettings settings;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="bookmaker_id", nullable=false)
    private Bookmaker bookmaker;

    @OneToMany(mappedBy="bk_account", cascade = CascadeType.ALL)
    private Set<Bet> bets;

    @Column(columnDefinition = "boolean default true")
    private boolean isActive;

    private String login, password;
    private double balance;

    public BkAccount() {}
    public BkAccount(User user, Bookmaker bookmaker, String login, String password) {
        this.user = user;
        this.bookmaker = bookmaker;
        this.login = login;
        this.password = password;
        this.balance = 0;
        this.isActive = true;

        this.bets = new HashSet<>();

        this.settings = new BkAccSettings(this);
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Bookmaker getBookmaker() { return bookmaker; }
    public void setBookmaker(Bookmaker bookmaker) { this.bookmaker = bookmaker; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public Set<Bet> getBets() { return bets; }
    public void setBets(Set<Bet> bets) { this.bets = bets; }

    public BkAccSettings getSettings() { return settings; }
    public void setSettings(BkAccSettings settings) { this.settings = settings; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
}
