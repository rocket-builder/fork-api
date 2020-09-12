package com.fork.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fork.api.enums.EGame;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({ "settings", "title" })
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany(mappedBy = "games")
    private Set<Settings> settings = new HashSet<>();

    private EGame game;
    private String title;

    public Game(){}
    public Game(String title) {
        this.game = EGame.valueOf(title);
        this.title = title;
    }

    public EGame getGame() { return game; }
    public void setGame(EGame game) { this.game = game; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Set<Settings> getSettings() { return settings; }
    public void setSettings(Set<Settings> settings) { this.settings = settings; }
}
