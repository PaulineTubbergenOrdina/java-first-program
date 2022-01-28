package com.h2;

import java.time.LocalDate;
import java.time.YearMonth;

public class SavingsCalculator {
    private static float[] credits;
    private static float[] debits;

    public SavingsCalculator(float[] credits, float[] debits) {
        this.credits = credits;
        this.debits = debits;
    }

    private float sumOfCredits() {
        float sum = 0.0f;
        for (float credit : this.credits) {
            sum = sum + credit;
        }
        return sum;
    }

    private float sumOfDebits() {
        float sum = 0.0f;
        for (float debit : this.debits) {
            sum = sum + debit;
        }
        return sum;
    }

    private static int remainingDaysInMonth(LocalDate date) {
        YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonth());
        int totalDaysInMonth = yearMonth.lengthOfMonth();
        int remainingDays = totalDaysInMonth - date.getDayOfMonth();
        return remainingDays;
    }

    public float calculate() {
        return sumOfCredits() - sumOfDebits();
    }

    public static void main(String[] args) {
        String[] creditsAsAString = args[0].split(",");
        String[] debitsAsAString = args[1].split(",");
        float[] credits = new float[creditsAsAString.length];
        float[] debits = new float[debitsAsAString.length];

        for(int i = 0; i < creditsAsAString.length; i++) {
            credits[i] = Float.parseFloat(creditsAsAString[i]);
        }

        for(int i = 0; i < debitsAsAString.length; i++) {
            debits[i] = Float.parseFloat(debitsAsAString[i]);
        }

        SavingsCalculator calculator = new SavingsCalculator(credits, debits);
        float netSavings = calculator.calculate();
        System.out.println("Net Savings = " + netSavings + ", remaining days in month = " + remainingDaysInMonth(LocalDate.now()));
    }
}
