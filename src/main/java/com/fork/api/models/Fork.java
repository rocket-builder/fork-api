package com.fork.api.models;

import javax.persistence.*;

@Entity
public class Fork {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bet_id", referencedColumnName = "id")
    private Bet betLeft;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bet_id", referencedColumnName = "id")
    private Bet betRight;

    public Fork(){}
    public Fork(Bet betLeft, Bet betRight) {
        this.betLeft = betLeft;
        this.betRight = betRight;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Bet getBetLeft() { return betLeft; }
    public void setBetLeft(Bet betLeft) { this.betLeft = betLeft; }

    public Bet getBetRight() { return betRight; }
    public void setBetRight(Bet betRight) { this.betRight = betRight; }
}
