package com.fork.api.models;

import com.fork.api.Config;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "settings_id", referencedColumnName = "id")
    private Settings settings;

    @OneToMany(mappedBy="user")
    private Set<BkAccount> bk_accounts;

    private String login, password;
    private Date signup_date, subscribe_end_date;

    public User() {}
    public User(String login, String password, Date subscribe_end_date) {
        this.login = login;
        this.password = password;
        this.subscribe_end_date = subscribe_end_date;
        this.signup_date = new Date((new java.util.Date()).getTime());

        this.settings = new Settings(
                this,
                Config.BALANCE_PERCENT
        );
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Settings getSettings() { return settings; }
    public void setSettings(Settings settings) { this.settings = settings; }

    public Date getSignup_date() { return signup_date; }
    public void setSignup_date(Date signup_date) { this.signup_date = signup_date; }

    public Date getSubscribe_end_date() { return subscribe_end_date; }
    public void setSubscribe_end_date(Date subscribe_end_date) { this.subscribe_end_date = subscribe_end_date; }
}
