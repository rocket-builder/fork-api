package com.fork.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fork.api.enums.EMarket;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@JsonIgnoreProperties({ "user" })
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(mappedBy = "settings")
    private User user;

    private int balance_percent;
    private int forks_live_time;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Game_Settings",
            joinColumns = { @JoinColumn(name = "game_id") },
            inverseJoinColumns = { @JoinColumn(name = "settings_id") }
    )
    private Set<Game> games;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Market_Settings",
            joinColumns = { @JoinColumn(name = "market_id") },
            inverseJoinColumns = { @JoinColumn(name = "settings_id") }
    )
    private Set<Market> markets;


    public Settings() {}
    public Settings(User user, int balance_percent) {
        this.user = user;
        this.balance_percent = balance_percent;

        this.forks_live_time = 10;

        this.games = new HashSet<>();
        games.add(new Game("СSGO"));
        games.add(new Game("DOTA2"));

        this.markets = new HashSet<>();
        markets.add(new Market("MatchWinner"));
    }
    public Settings(User user, int balance_percent, Set<Game> games, Set<Market> markets, int forks_live_time) {
        this.user = user;
        this.balance_percent = balance_percent;
        this.games = games;
        this.markets = markets;
        this.forks_live_time = forks_live_time;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public int getBalance_percent() { return balance_percent; }
    public void setBalance_percent(int balance_percent) { this.balance_percent = balance_percent; }

    public Set<Game> getGames() { return games; }
    public void setGames(Set<Game> games) { this.games = games;}

    public Set<Market> getMarkets() { return markets; }
    public void setMarkets(Set<Market> markets) { this.markets = markets; }

    public int getForks_live_time() { return forks_live_time; }
    public void setForks_live_time(int forks_live_time) { this.forks_live_time = forks_live_time; }
}
