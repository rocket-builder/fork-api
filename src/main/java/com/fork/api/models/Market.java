package com.fork.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fork.api.enums.EMarket;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({ "settings", "title" })
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany(mappedBy = "markets")
    private Set<Settings> settings = new HashSet<>();

    private EMarket market;
    private String title;

    public Market(){}
    public Market(String title) {
        this.market = EMarket.valueOf(title);
        this.title = title;
    }

    public EMarket getMarket() { return market; }
    public void setMarket(EMarket market) { this.market = market; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Set<Settings> getSettings() { return settings; }
    public void setSettings(Set<Settings> settings) { this.settings = settings; }
}