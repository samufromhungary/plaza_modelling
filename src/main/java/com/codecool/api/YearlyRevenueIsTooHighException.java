package com.codecool.api;

public class YearlyRevenueIsTooHighException extends Exception {
    public YearlyRevenueIsTooHighException() {
        super("The yearly revenue of this shop is too low for this plaza.");
    }
}
