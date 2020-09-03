package com.fork.api.models;

import com.fork.api.repos.ForkRepos;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Calendar;

public class Profit {

    private String userLogin;
    private float day;
    private float week;
    private float month;

    public Profit(){
        this.userLogin = "";
        this.day = 0;
        this.week = 0;
        this.month = 0;
    }
    public Profit(List<Fork> forks, User user){
        this.userLogin = user.getLogin();
        this.day = 0;
        this.week = 0;
        this.month = 0;

        Date today = new Date((new java.util.Date()).getTime());

        forks.forEach(fork -> {

            if(
                    fork.getFork_date().getDay() == today.getDay() &&
                    fork.getFork_date().getMonth() == today.getMonth() &&
                    fork.getFork_date().getYear() == today.getYear()
            ) this.day += fork.getProfit();
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

    public String getUserLogin() { return userLogin; }
    public void setUserLogin(String userLogin) { this.userLogin = userLogin; }
}
