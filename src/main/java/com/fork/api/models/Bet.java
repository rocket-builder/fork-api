package com.fork.api.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="bk_account_id", nullable=false)
    private BkAccount bk_account;

    private String match_title, team;
    private float coefficient, bet_sum;
    private Date bet_date;

    public Bet() {}
    public Bet(BkAccount bkAccount, String match, String team, float coefficient, float sum, Date date) {
        this.bk_account = bkAccount;
        this.match_title = match;
        this.team = team;
        this.coefficient = coefficient;
        this.bet_sum = sum;
        this.bet_date = date;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public BkAccount getBkAccount() { return bk_account; }
    public void setBkAccount(BkAccount bkAccount) { this.bk_account = bkAccount; }

    public String getMatch() { return match_title; }
    public void setMatch(String match) { this.match_title = match; }

    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }

    public float getCoefficient() { return coefficient; }
    public void setCoefficient(float coefficient) { this.coefficient = coefficient; }

    public float getSum() { return bet_sum; }
    public void setSum(float sum) { this.bet_sum = sum; }

    public Date getDate() { return bet_date; }
    public void setDate(Date date) { this.bet_date = date; }
}
