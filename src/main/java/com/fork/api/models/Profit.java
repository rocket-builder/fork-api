package com.fork.api.models;

import com.fork.api.repos.ForkRepos;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Calendar;

public class Profit {

    @Autowired
    ForkRepos forkRepos;

    private float day = 0;
    private float week = 0;
    private float month = 0;

    public Profit(){
        this.day = 0;
        this.week = 0;
        this.month = 0;
    }
    public Profit(User user){

        Date today = new Date();

        List<Fork> forks = forkRepos.findAllByBkAccount_User(user);
        forks.forEach(fork -> {

            if(fork.getFork_date().equals(today)) this.day += fork.getProfit();
            if(isDateInCurrentWeek(fork.getFork_date())) this.week += fork.getProfit();
            if(fork.getFork_date().getMonth() == today.getMonth()) this.month += fork.getProfit();
        });
    }

    private boolean isDateInCurrentWeek(Date date) {
        Calendar currentCalendar = Calendar.getInstance();
        int week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
        int year = currentCalendar.get(Calendar.YEAR);
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(date);
        int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
        int targetYear = targetCalendar.get(Calendar.YEAR);
        return week == targetWeek && year == targetYear;
    }

    public float getDay() { return day; }
    public void setDay(float day) { this.day = day; }

    public float getWeek() { return week; }
    public void setWeek(float week) { this.week = week; }

    public float getMonth() { return month; }
    public void setMonth(float month) { this.month = month; }
}
