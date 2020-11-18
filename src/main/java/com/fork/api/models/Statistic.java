package com.fork.api.models;

import com.fork.api.repos.ForkRepos;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

public class Statistic {

    private String userLogin;
    private BigDecimal day;
    private BigDecimal week;
    private BigDecimal month;

    public Statistic(){
        this.userLogin = "";
        this.day = BigDecimal.ZERO;
        this.week = BigDecimal.ZERO;
        this.month = BigDecimal.ZERO;
    }
    public Statistic(List<Profit> profits, User user){
        this.userLogin = user.getLogin();
        this.day = BigDecimal.ZERO;
        this.week = BigDecimal.ZERO;
        this.month = BigDecimal.ZERO;

        Date today = new Date((new java.util.Date()).getTime());

        profits.forEach(profit -> {
            Date profitDate = profit.getDate();
            if(
                    profitDate.getDay() == today.getDay() &&
                    profitDate.getMonth() == today.getMonth() &&
                    profitDate.getYear() == today.getYear()
            ) {
                this.day = day.add(profit.getMoney());
            }
            if(isDateInCurrentWeek(profitDate)) {
                this.week = week.add(profit.getMoney());
            }
            if(profitDate.getMonth() == today.getMonth()) {
                this.month = month.add(profit.getMoney());
            }
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

    public BigDecimal getDay() { return day; }
    public void setDay(BigDecimal day) { this.day = day; }

    public BigDecimal getWeek() { return week; }
    public void setWeek(BigDecimal week) { this.week = week; }

    public BigDecimal getMonth() { return month; }
    public void setMonth(BigDecimal month) { this.month = month; }

    public String getUserLogin() { return userLogin; }
    public void setUserLogin(String userLogin) { this.userLogin = userLogin; }
}
