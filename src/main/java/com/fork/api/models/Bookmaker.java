package com.fork.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIgnoreProperties({ "accounts" })
public class Bookmaker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy="bookmaker", cascade = CascadeType.ALL)
    private Set<BkAccount> accounts;

    private String title, link;

    public Bookmaker() {}
    public Bookmaker(String title, String link) {
        this.title = title;
        this.link = link;

    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Set<BkAccount> getAccounts() { return accounts; }
    public void setAccounts(Set<BkAccount> accounts) { this.accounts = accounts; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
}
