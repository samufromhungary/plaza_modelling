package com.codecool.api;

public class YearlyRevenueIsTooLowException extends Exception {
    public YearlyRevenueIsTooLowException() {
        super("The yearly revenue of this shop is too low for this plaza.");
    }
}
