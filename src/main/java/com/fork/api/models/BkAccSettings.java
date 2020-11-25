package com.fork.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fork.api.Config;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({ "account" })
public class BkAccSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(mappedBy = "settings")
    private BkAccount account;

    private String bkMirror;

    private double cf_min, cf_max;

    @ColumnDefault("10")
    private int cf_live_time;

    public BkAccSettings(){}
    public BkAccSettings(BkAccount account) {
        this.account = account;

        this.bkMirror = "";
        this.cf_min = Config.CF_MIN;
        this.cf_max = Config.CF_MAX;
        this.cf_live_time = Config.CF_LIVE_TIME;
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
}
