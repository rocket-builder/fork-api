package com.fork.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIgnoreProperties({ "user", "bets", "forks" })
public class BkAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="bookmaker_id", nullable=false)
    private Bookmaker bookmaker;

    @OneToMany(mappedBy="bk_account")
    private Set<Bet> bets;

    @OneToMany(mappedBy="bkAccount")
    private Set<Fork> forks;

    private String login, password;
    private float balance;

    public BkAccount() {}
    public BkAccount(User user, Bookmaker bookmaker, String login, String password) {
        this.user = user;
        this.bookmaker = bookmaker;
        this.login = login;
        this.password = password;
        this.balance = 0;

        this.bets = null;
        this.forks = null;
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

    public float getBalance() { return balance; }
    public void setBalance(float balance) { this.balance = balance; }

    public Set<Bet> getBets() { return bets; }
    public void setBets(Set<Bet> bets) { this.bets = bets; }

    public Set<Fork> getForks() { return forks; }
    public void setForks(Set<Fork> forks) { this.forks = forks; }
}
