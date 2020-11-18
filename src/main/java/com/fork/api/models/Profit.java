package com.fork.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"user"})
public class Profit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    private BigDecimal money;
    private Date date;

    public Profit(){}
    public Profit(double money, User user) {
        this.user = user;
        this.money = BigDecimal.valueOf(money);
        this.date = new Date((new java.util.Date()).getTime());
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public BigDecimal getMoney() { return money; }
    public void setMoney(BigDecimal money) { this.money = money; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
