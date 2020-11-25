package com.fork.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fork.api.Config;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@Entity
@JsonIgnoreProperties({ "user", "id" })
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(mappedBy = "settings")
    private User user;

    private int forks_live_time_max, forks_live_time_min,
                fork_profit_percent_min, fork_profit_percent_max,
                fork_done_try_cooldown, fork_cancel_try_cooldown,
                fork_second_bet_timeout,
                fork_not_closed_cooldown,
                try_time_max,
                rounding,
                bet_sum_min, bet_sum_max;

    @ColumnDefault("0")
    private boolean if_timeout_close_fork;

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
    public Settings(User user) {
        this.user = user;

        this.forks_live_time_max = Config.FORKS_LIVE_TIME_MAX;
        this.forks_live_time_min = Config.FORKS_LIVE_TIME_MIN;
        this.fork_profit_percent_min = Config.FORK_PROFIT_PERCENT_MIN;
        this.fork_profit_percent_max = Config.FORK_PROFIT_PERCENT_MAX;
        this.fork_done_try_cooldown = Config.FORK_DONE_TRY_COOLDOWN;
        this.fork_cancel_try_cooldown = Config.FORK_CANCEL_TRY_COOLDOWN;
        this.fork_second_bet_timeout = Config.FORK_SECOND_BET_TIMEOUT;
        this.fork_not_closed_cooldown = Config.FORK_NOT_CLOSED_COOLDOWN;
        this.try_time_max = Config.TRY_TIME_MAX;
        this.rounding = Config.ROUNDING;
        this.bet_sum_min = Config.BET_SUM_MIN;
        this.bet_sum_max = Config.BET_SUM_MAX;
        this.if_timeout_close_fork = Config.IF_TIMEOUT_CLOSE_FORKS;

        this.games = new HashSet<>();
        games.add(new Game("CSGO"));
        games.add(new Game("DOTA2"));

        this.markets = new HashSet<>();
        markets.add(new Market("MatchWinner"));
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public ArrayList<String> getGames() {
        ArrayList<String> gamesArr = new ArrayList<>();
        games.forEach(game -> {
            gamesArr.add(game.getGame().toString());
        });
        return gamesArr;
    }
    public void setGames(Set<Game> games) { this.games = games;}

    public ArrayList<String> getMarkets() {
        ArrayList<String> marketsArr = new ArrayList<>();
        markets.forEach(market -> {
            marketsArr.add(market.getMarket().toString());
        });
        return marketsArr;
    }
    public void setMarkets(Set<Market> markets) { this.markets = markets; }

    public int getForks_live_time_max() { return forks_live_time_max; }
    public void setForks_live_time_max(int forks_live_time_max) { this.forks_live_time_max = forks_live_time_max; }

    public int getForks_live_time_min() { return forks_live_time_min; }
    public void setForks_live_time_min(int forks_live_time_min) { this.forks_live_time_min = forks_live_time_min; }

    public int getFork_profit_percent_min() { return fork_profit_percent_min; }
    public void setFork_profit_percent_min(int fork_profit_percent_min) { this.fork_profit_percent_min = fork_profit_percent_min; }

    public int getFork_profit_percent_max() { return fork_profit_percent_max; }
    public void setFork_profit_percent_max(int fork_profit_percent_max) { this.fork_profit_percent_max = fork_profit_percent_max; }

    public int getFork_done_try_cooldown() { return fork_done_try_cooldown; }
    public void setFork_done_try_cooldown(int fork_done_try_cooldown) { this.fork_done_try_cooldown = fork_done_try_cooldown; }

    public int getFork_cancel_try_cooldown() { return fork_cancel_try_cooldown; }
    public void setFork_cancel_try_cooldown(int fork_cancel_try_cooldown) { this.fork_cancel_try_cooldown = fork_cancel_try_cooldown; }

    public int getFork_second_bet_timeout() { return fork_second_bet_timeout; }
    public void setFork_second_bet_timeout(int fork_second_bet_timeout) { this.fork_second_bet_timeout = fork_second_bet_timeout; }

    public int getFork_not_closed_cooldown() { return fork_not_closed_cooldown; }
    public void setFork_not_closed_cooldown(int fork_not_closed_cooldown) { this.fork_not_closed_cooldown = fork_not_closed_cooldown; }

    public int getTry_time_max() { return try_time_max; }
    public void setTry_time_max(int try_time_max) { this.try_time_max = try_time_max; }

    public int getRounding() { return rounding; }
    public void setRounding(int rounding) { this.rounding = rounding; }

    public int getBet_sum_min() { return bet_sum_min; }
    public void setBet_sum_min(int bet_sum_min) { this.bet_sum_min = bet_sum_min; }

    public int getBet_sum_max() { return bet_sum_max; }
    public void setBet_sum_max(int bet_sum_max) { this.bet_sum_max = bet_sum_max; }

    public boolean isIf_timeout_close_fork() { return if_timeout_close_fork; }
    public void setIf_timeout_close_fork(boolean if_timeout_close_fork) { this.if_timeout_close_fork = if_timeout_close_fork; }
}
