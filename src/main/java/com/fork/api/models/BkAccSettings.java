package com.fork.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fork.api.Config;

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

    private int cf_min, cf_max;

    public BkAccSettings(){}
    public BkAccSettings(BkAccount account) {
        this.account = account;

        this.bkMirror = "";
        this.cf_min = Config.CF_MIN;
        this.cf_max = Config.CF_MAX;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public BkAccount getAccount() { return account; }
    public void setAccount(BkAccount account) { this.account = account; }

    public String getBkMirror() { return bkMirror; }
    public void setBkMirror(String bkMirror) { this.bkMirror = bkMirror; }

    public int getCf_min() { return cf_min; }
    public void setCf_min(int cf_min) { this.cf_min = cf_min; }

    public int getCf_max() { return cf_max; }
    public void setCf_max(int cf_max) { this.cf_max = cf_max; }
}
