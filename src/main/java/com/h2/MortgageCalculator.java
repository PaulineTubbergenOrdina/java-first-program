package com.h2;

import java.text.DecimalFormat;
import java.util.Locale;

public class MortgageCalculator {
    private final long loanAmount;
    private final int termInYears;
    private final float annualRate;
    private double monthlyPayment;

    public MortgageCalculator(long loanAmount, int termInYears, float annualRate) {
        this.loanAmount = loanAmount;
        this.termInYears = termInYears;
        this.annualRate = annualRate;
    }

    private int getNumberOfPayments() {
        return this.termInYears * 12;
    }

    private float getMonthlyInterestRate() {
        float interestRate = this.annualRate/100;
        float monthlyInterestRate = interestRate / 12;
        return monthlyInterestRate;
    }

    public void calculateMonthlyPayment() {
        // M = P(r(1+r)^n/(((1+r)^n)-1)
        // M is the calculated monthly mortgage payment
        // P is the principal amount (loanAmount)
        // r is the monthly interest rate
        // n is the total number of payments
        long P = loanAmount;
        float r = getMonthlyInterestRate();
        int n = getNumberOfPayments();
        double M = P * ((r * Math.pow(1 + r, n))/(Math.pow(1 + r, n) - 1));
        this.monthlyPayment = M;
    }

    @Override
    public String toString() {
        // Locale is necessary to make the tests pass
        Locale.setDefault(Locale.ENGLISH);
        DecimalFormat df = new DecimalFormat("####0.00");
        return "monthlyPayment: " + df.format(monthlyPayment);
    }

    public static void main(String[] args) {
        long loanAmount = Long.parseLong(args[0]);
        int termInYears = Integer.parseInt(args[1]);
        float annualRate = Float.parseFloat(args[2]);
        MortgageCalculator calculator = new MortgageCalculator(loanAmount, termInYears, annualRate);
        calculator.calculateMonthlyPayment();
        System.out.println(calculator.toString());
    }
}
