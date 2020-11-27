package com.fork.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fork.api.Config;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.text.ParseException;

@Entity
@JsonIgnoreProperties({ "account" })
public class BkAccSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(mappedBy = "settings")
    private BkAccount account;

    private String bkMirror = "";

    private double cf_min, cf_max;

    private int cf_live_time = 0;
    private int place_bet_rule = 0;

    public BkAccSettings(){}
    public BkAccSettings(BkAccount account) {
        this.account = account;
        this.bkMirror = "";
        this.cf_min = Config.CF_MIN;
        this.cf_max = Config.CF_MAX;
        this.cf_live_time = Config.CF_LIVE_TIME;
        this.place_bet_rule = Config.PLACE_BET_RULE;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public BkAccount getAccount() { return account; }
    public void setAccount(BkAccount account) { this.account = account; }

    public String getBkMirror() { return bkMirror; }
    public void setBkMirror(String bkMirror) { this.bkMirror = bkMirror; }

    public double getCf_min() { return cf_min; }
    public void setCf_min(double cf_min) { this.cf_min = cf_min; }

    public double getCf_max() { return cf_max; }
    public void setCf_max(double cf_max) { this.cf_max = cf_max; }

    public int getCf_live_time() { return cf_live_time; }
    public void setCf_live_time(int cf_live_time) { this.cf_live_time = cf_live_time; }

    public int getPlace_bet_rule() { return place_bet_rule; }
    public void setPlace_bet_rule(int place_bet_rule) throws ParseException {
        if(place_bet_rule < 0 || place_bet_rule > 2){
            throw new ParseException("place bet rule cannot be a ".concat(String.valueOf(place_bet_rule)), 0);
        } else {
            this.place_bet_rule = place_bet_rule;
        }
    }
}
