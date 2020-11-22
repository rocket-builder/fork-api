package com.fork.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.math3.util.Precision;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

//@JsonIgnoreProperties({ "user" })
@Entity
public class Fork {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    private double leftProfit;
    private double rightProfit;

    private Date fork_date;
    private String leftMarket, rightMarket;
    private String leftBkTitle, rightBkTitle;
    private String leftTeamTitle, rightTeamTitle;
    private String leftBkAccLogin, rightBkAccLogin;
    private String matchTitle;
    private long leftBkAccId, rightBkAccId;

    public Fork(){}
    public Fork(Bet betLeft, Bet betRight) {
        if(!betLeft.isSuccess() && !betRight.isSuccess()){
            this.leftProfit = 0;
            this.rightProfit = 0;
        } else {
            if(betLeft.isSuccess() && !betRight.isSuccess()){
                this.rightProfit = (betLeft.getSum() * betLeft.getCoefficient()) - betLeft.getSum();
                this.leftProfit = -betLeft.getSum();
            } else if(betRight.isSuccess() && !betLeft.isSuccess()){
                this.leftProfit = (betRight.getSum() * betRight.getCoefficient()) - betRight.getSum();
                this.rightProfit = -betRight.getSum();
            } else {
                double bank = betLeft.getSum() + betRight.getSum();
                this.leftProfit = Math.floor((betLeft.getSum() * betLeft.getCoefficient()) - bank);
                this.rightProfit = Math.floor((betRight.getSum() * betRight.getCoefficient()) - bank);
            }
        }

        this.fork_date = betLeft.getDate();

        this.leftMarket = betLeft.getMarket();
        this.rightMarket = betRight.getMarket();

        this.leftBkTitle = betLeft.getBkAccount().getBookmaker().getTitle();
        this.rightBkTitle = betRight.getBkAccount().getBookmaker().getTitle();

        this.leftBkAccId = betLeft.getBkAccount().getId();
        this.rightBkAccId = betRight.getBkAccount().getId();

        this.leftTeamTitle = betLeft.getTeam();
        this.rightTeamTitle = betRight.getTeam();

        this.leftBkAccLogin = betLeft.getBkAccount().getLogin();
        this.rightBkAccLogin = betRight.getBkAccount().getLogin();

        this.matchTitle = betLeft.getMatch();
        this.user = betLeft.getBkAccount().getUser();
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public double getLeftProfit() { return leftProfit; }
    public void setLeftProfit(double leftProfit) { this.leftProfit = leftProfit; }

    public double getRightProfit() { return rightProfit; }
    public void setRightProfit(double rightProfit) { this.rightProfit = rightProfit; }

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

    public String getLeftTeamTitle() { return leftTeamTitle; }
    public void setLeftTeamTitle(String leftTeamTitle) { this.leftTeamTitle = leftTeamTitle; }

    public String getRightTeamTitle() { return rightTeamTitle; }
    public void setRightTeamTitle(String rightTeamTitle) { this.rightTeamTitle = rightTeamTitle; }

    public String getMatchTitle() { return matchTitle; }
    public void setMatchTitle(String matchTitle) { this.matchTitle = matchTitle; }

    public String getLeftMarket() { return leftMarket; }
    public void setLeftMarket(String leftMarket) { this.leftMarket = leftMarket; }

    public String getRightMarket() { return rightMarket; }
    public void setRightMarket(String rightMarket) { this.rightMarket = rightMarket; }

    public String getLeftBkAccLogin() { return leftBkAccLogin; }
    public void setLeftBkAccLogin(String leftBkAccLogin) { this.leftBkAccLogin = leftBkAccLogin; }

    public String getRightBkAccLogin() { return rightBkAccLogin; }
    public void setRightBkAccLogin(String rightBkAccLogin) { this.rightBkAccLogin = rightBkAccLogin; }
}
