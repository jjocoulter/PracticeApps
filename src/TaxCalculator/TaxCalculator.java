package TaxCalculator;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

import static jdk.nashorn.internal.objects.NativeMath.round;

/**
 * a program for calculating tax on a given income.
 * income cap      marginal tax rate
 * ¤10,000           0.00 (0%)
 * ¤30,000           0.10 (10%)
 * ¤100,000           0.25 (25%)
 * --              0.40 (40%)
 */
public class TaxCalculator {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<TaxBracket> taxBrackets = new ArrayList<>();

        //Set tax brackets

        taxBrackets.add(new TaxBracket(10000, 0));
        taxBrackets.add(new TaxBracket(30000, 0.1));
        taxBrackets.add(new TaxBracket(100000, 0.25));
        taxBrackets.add(new TaxBracket(0, 0.4));

        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        double income;
        double taxPaid = 0;

        System.out.println("Please enter your yearly income to calculate tax:");
        while (true) {
            if (scanner.hasNextDouble()) {
                income = scanner.nextDouble();
                break;
            } else {
                System.out.println("Invalid input. Please enter yearly income as an integer.");
                scanner.next();
            }
        }

        if (income <= taxBrackets.get(0).maxIncome) {
            System.out.println("You are under the tax threshold. No tax to pay.");
        } else {
            for (int i = 1; i < taxBrackets.size(); i++) {
                //Check income is greater than tax bracket (and previous tax bracket for final tax bracket)
                if (income >= taxBrackets.get(i).maxIncome && income >= taxBrackets.get(i-1).maxIncome) {
                    //Only tax income from within the tax bracket range.
                    if (((taxBrackets.get(i).maxIncome - taxBrackets.get(i - 1).maxIncome) > 0)) {
                        double currentTaxableIncome = taxBrackets.get(i).maxIncome - taxBrackets.get(i - 1).maxIncome;
                        taxPaid += currentTaxableIncome * taxBrackets.get(i).taxRate;
                        //For final tax bracket, max income is set at 0 so needs to be calculated based on income instead of
                        //previous tax bracket max amount.
                    } else if ((taxBrackets.get(i).maxIncome - taxBrackets.get(i - 1).maxIncome) < 0) {
                        double currentTaxableIncome = income - taxBrackets.get(i - 1).maxIncome;
                        taxPaid += currentTaxableIncome * taxBrackets.get(i).taxRate;
                    }
                }
            }
            System.out.println("On a yearly income of: " + currencyInstance.format(income));
            System.out.println("Tax Paid is: " + currencyInstance.format(taxPaid));
            System.out.println();
            System.out.println("Take home income is: " + currencyInstance.format(income - taxPaid));
        }


    }
}
