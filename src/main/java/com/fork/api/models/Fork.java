package com.fork.api.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Fork {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private float profit;
    private Date fork_date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "betLeft_id", referencedColumnName = "id")
    private Bet betLeft;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "betRight_id", referencedColumnName = "id")
    private Bet betRight;

    @ManyToOne
    @JoinColumn(name="bkAccount_id", nullable=false)
    private BkAccount bkAccount;

    public Fork(){}
    public Fork(Bet betLeft, Bet betRight) {
        this.betLeft = betLeft;
        this.betRight = betRight;
        this.profit =
                Math.abs(
                (betLeft.getSum() * betLeft.getCoefficient()) -
                        (betRight.getSum() * betRight.getCoefficient())
                );
        this.fork_date = betLeft.getDate();
        this.bkAccount = betLeft.getBkAccount();
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Bet getBetLeft() { return betLeft; }
    public void setBetLeft(Bet betLeft) { this.betLeft = betLeft; }

    public Bet getBetRight() { return betRight; }
    public void setBetRight(Bet betRight) { this.betRight = betRight; }

    public float getProfit() { return profit; }
    public void setProfit(float profit) { this.profit = profit; }

    public Date getFork_date() { return fork_date; }
    public void setFork_date(Date fork_date) { this.fork_date = fork_date; }

    public BkAccount getBkAccount() { return bkAccount; }
    public void setBkAccount(BkAccount bkAccount) { this.bkAccount = bkAccount; }
}
