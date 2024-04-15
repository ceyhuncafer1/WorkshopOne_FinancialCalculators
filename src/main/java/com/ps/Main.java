package com.ps;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Which calculator would you like to choose?\n1. Mortgage Calculator\n2. Future Value Calculator\n3. Present Value Calculator");

        byte userAnswer = scanner.nextByte();

        if(userAnswer == 1){

            System.out.println("Enter your principal amount:");
            double principal = scanner.nextDouble();

            System.out.println("Enter your annual interest rate:");
            double interestRateOne = scanner.nextDouble();

            System.out.println("Enter your loan length in years");
            double loanLength = scanner.nextDouble();

            double[] mortgageCalcAnswers = mortgageCalculator(principal, interestRateOne, loanLength);

            double monthlyPayment = mortgageCalcAnswers[0];
            double totalInterestPaid = mortgageCalcAnswers[1];

            System.out.printf("Monthly payment: $%.2f%n", monthlyPayment);
            System.out.printf("Total interest paid: $%.2f%n", totalInterestPaid);

        } else if(userAnswer == 2){

            System.out.println("Enter your deposit amount:");
            double deposit = scanner.nextDouble();

            System.out.println("Enter your annual interest rate:");
            double interestRateTwo = scanner.nextDouble();

            System.out.println("Enter your length of maturity in years:");
            double maturityLength = scanner.nextDouble();

            double futureValueAnswer = futureValueCalculator(deposit, interestRateTwo, maturityLength);

            System.out.printf("Future Value: $%.2f%n", futureValueAnswer);
            System.out.printf("Total interest earned: %.2f%n", (futureValueAnswer - deposit));

        } else{

            System.out.print("Enter the monthly payout: ");
            double monthlyPayout = scanner.nextDouble();

            System.out.print("Enter the expected interest rate (as a percentage): ");
            double interestRate = scanner.nextDouble() / 100;

            System.out.print("Enter the number of years: ");
            int years = scanner.nextInt();

            double presentValue = calculatePresentValue(monthlyPayout, interestRate, years);
            System.out.printf("You must invest $%.2f%n", presentValue);

        }



    }

    public static double[] mortgageCalculator(double principal, double annualInterestRate, double loanLengthYears){

        double monthlyInterestRate = annualInterestRate / 12 / 100;

        double numberOfMonthlyPayments = (int) (loanLengthYears * 12);

        double interestCalculation = Math.pow(1 + monthlyInterestRate, numberOfMonthlyPayments);

        double monthlyPayment = (principal * monthlyInterestRate * interestCalculation) / (interestCalculation - 1);

        double totalPayment = monthlyPayment * numberOfMonthlyPayments;

        double totalInterestPaid = totalPayment - principal;

        return new double[]{monthlyPayment, totalInterestPaid};
    }

    public static double futureValueCalculator(double deposit, double interestRateTwo, double maturityLength){
        double dailyInterestRate = interestRateTwo / 100 / 365;
        return deposit * Math.pow(1 + dailyInterestRate, maturityLength * 365);

    }

    public static double calculatePresentValue(double monthlyPayout, double expectedInterestRate, double presentValueYears){
        double monthlyInterestRate = expectedInterestRate / 12;

        int months = (int) presentValueYears * 12;

        double presentValue = monthlyPayout * (1 - Math.pow(1 + monthlyInterestRate, -months)) / monthlyInterestRate;

        return presentValue;
    }
}