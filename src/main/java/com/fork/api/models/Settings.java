package com.fork.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
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
    private int forks_live_time_max, forks_live_time_min;

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

        this.forks_live_time_max = 10;
        this.forks_live_time_min = 3;

        this.games = new HashSet<>();
        games.add(new Game("СSGO"));
        games.add(new Game("DOTA2"));

        this.markets = new HashSet<>();
        markets.add(new Market("MatchWinner"));
    }
    public Settings(User user, int balance_percent, Set<Game> games, Set<Market> markets, int forks_live_time_max, int forks_live_time_min) {
        this.user = user;
        this.balance_percent = balance_percent;
        this.games = games;
        this.markets = markets;
        this.forks_live_time_max = forks_live_time_max;
        this.forks_live_time_min = forks_live_time_min;
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

    public int getForks_live_time_max() { return forks_live_time_max; }
    public void setForks_live_time_max(int forks_live_time_max) { this.forks_live_time_max = forks_live_time_max; }

    public int getForks_live_time_min() { return forks_live_time_min; }
    public void setForks_live_time_min(int forks_live_time_min) { this.forks_live_time_min = forks_live_time_min; }
}
