package com.fork.api;

public class Config {

    //Bot default settings
    public static final int BALANCE_PERCENT = 5,
    FORKS_LIVE_TIME_MAX = 10, FORKS_LIVE_TIME_MIN = 3,
    FORK_PROFIT_PERCENT_MIN = 0, FORK_PROFIT_PERCENT_MAX = 100,
    FORK_DONE_TRY_COOLDOWN = 120, FORK_CANCEL_TRY_COOLDOWN = 0,
    FORK_SECOND_BET_TIMEOUT = 120,
    FORK_NOT_CLOSED_COOLDOWN = 30,
    TRY_TIME_MAX = 10;

    //Single BK account default settings
    public static final int ROUNDING = 10,
    BET_SUM_MIN = 0, BET_SUM_MAX = 5000,
    CF_MIN = 0, CF_MAX = 20,
    CF_LIVE_TIME = 20;
}
