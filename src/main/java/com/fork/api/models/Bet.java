package com.fork.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties({ "bk_account" })
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="bk_account_id", nullable=false)
    private BkAccount bk_account;

    private String match_title, team, market;
    private double coefficient, bet_sum;
    private Date bet_date;
    private boolean isSuccess;

    public Bet() {}
    public Bet(BkAccount bkAccount, String match, String team, String market, double coefficient, double sum, Date date, boolean isSuccess) {
        this.bk_account = bkAccount;
        this.match_title = match;
        this.team = team;
        this.market = market;
        this.coefficient = coefficient;
        this.bet_sum = sum;
        this.bet_date = date;
        this.isSuccess = isSuccess;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public BkAccount getBkAccount() { return bk_account; }
    public void setBkAccount(BkAccount bkAccount) { this.bk_account = bkAccount; }

    public String getMatch() { return match_title; }
    public void setMatch(String match) { this.match_title = match; }

    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }

    public String getMarket() { return market; }
    public void setMarket(String market) { this.market = market; }

    public double getCoefficient() { return coefficient; }
    public void setCoefficient(double coefficient) { this.coefficient = coefficient; }

    public double getSum() { return bet_sum; }
    public void setSum(double sum) { this.bet_sum = sum; }

    public Date getDate() { return bet_date; }
    public void setDate(Date date) { this.bet_date = date; }

    public boolean isSuccess() { return isSuccess; }
    public void setSuccess(boolean success) { isSuccess = success; }
}
