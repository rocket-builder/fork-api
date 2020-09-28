package com.fork.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fork.api.Config;
import com.fork.api.Security;
import com.fork.api.enums.Role;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Entity
@JsonIgnoreProperties({ "forks" })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "settings_id", referencedColumnName = "id")
    private Settings settings;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private Set<BkAccount> bk_accounts;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private Set<Fork> forks;

    private String login, password, token;
    private Date signup_date, subscribe_end_date;
    private Role role;
    private boolean is_banned;

    public User() {}
    public User(String login, String password, Date subscribe_end_date) {
        this.login = login;
        this.password = password;
        this.subscribe_end_date = subscribe_end_date;
        this.signup_date = new Date((new java.util.Date()).getTime());
        this.role = Role.USER;
        this.token = Security.MD5(login + password);
        this.is_banned = false;

        this.settings = new Settings(this);
    }

    public User(String login, String password, Date subscribe_end_date, Role role) {
        this.login = login;
        this.password = password;
        this.subscribe_end_date = subscribe_end_date;
        this.signup_date = new Date((new java.util.Date()).getTime());
        this.role = role;
        this.token = Security.MD5(login + password);
        this.is_banned = false;

        this.settings = new Settings(this);
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Settings getSettings() { return settings; }
    public void setSettings(Settings settings) { this.settings = settings; }

    public String getSignup_date() { return new SimpleDateFormat("yyyy-MM-dd").format(signup_date); }
    public void setSignup_date(Date signup_date) { this.signup_date = signup_date; }

    public String getSubscribe_end_date() { return new SimpleDateFormat("yyyy-MM-dd").format(subscribe_end_date); }
    public void setSubscribe_end_date(Date subscribe_end_date) { this.subscribe_end_date = subscribe_end_date; }

    public Set<BkAccount> getBk_accounts() { return bk_accounts; }
    public void setBk_accounts(Set<BkAccount> bk_accounts) { this.bk_accounts = bk_accounts; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public boolean getIs_banned() { return is_banned; }
    public void setIs_banned(boolean is_banned) { this.is_banned = is_banned; }

    public Set<Fork> getForks() { return forks; }
    public void setForks(Set<Fork> forks) { this.forks = forks; }
}
