package TaxCalculator;

/**
 * Constructor for different tax brackets.
 */
public class TaxBracket {
    double maxIncome;
    double taxRate;

    public TaxBracket(double maxIncome, double taxRate) {
        this.maxIncome = maxIncome;
        this.taxRate = taxRate;
    }


}
