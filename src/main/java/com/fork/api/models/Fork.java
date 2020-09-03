package com.fork.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@JsonIgnoreProperties({ "user" })
public class Fork {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    private float profit;
    private Date fork_date;
    private String leftBkTitle, rightBkTitle;
    private long leftBkAccId, rightBkAccId;

    public Fork(){}
    public Fork(Bet betLeft, Bet betRight) {
        this.profit =
                Math.abs(
                (betLeft.getSum() * betLeft.getCoefficient()) -
                        (betRight.getSum() * betRight.getCoefficient())
                );
        this.fork_date = betLeft.getDate();
        this.leftBkTitle = betLeft.getBkAccount().getBookmaker().getTitle();
        this.rightBkTitle = betRight.getBkAccount().getBookmaker().getTitle();
        this.leftBkAccId = betLeft.getBkAccount().getId();
        this.rightBkAccId = betRight.getBkAccount().getId();
        this.user = betLeft.getBkAccount().getUser();
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public float getProfit() { return profit; }
    public void setProfit(float profit) { this.profit = profit; }

    public Date getFork_date() { return fork_date; }
    public void setFork_date(Date fork_date) { this.fork_date = fork_date; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getLeftBkTitle() { return leftBkTitle; }
    public void setLeftBkTitle(String leftBkTitle) { this.leftBkTitle = leftBkTitle; }

    public String getRightBkTitle() { return rightBkTitle; }
    public void setRightBkTitle(String rightBkTitle) { this.rightBkTitle = rightBkTitle; }

    public long getLeftBkAccId() { return leftBkAccId; }
    public void setLeftBkAccId(long leftBkAccId) { this.leftBkAccId = leftBkAccId; }

    public long getRightBkAccId() { return rightBkAccId; }
    public void setRightBkAccId(long rightBkAccId) { this.rightBkAccId = rightBkAccId; }
}
