package CollatzConjecture;

import java.util.Scanner;

/**
 * Short app to work out Collatz Conjecture - Start with a number n > 1. Find the number of steps it takes to reach one
 * using the following process: If n is even, divide it by 2. If n is odd, multiply it by 3 and add 1.
 */
public class CollatzConjecture {



    public static void main(String[] args) {

        CollatzConjecture collatzConjecture = new CollatzConjecture();
        int userNumber;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter an integer > 1.");

        do {
            while (!scanner.hasNextInt()){
                scanner.next();
            }
            userNumber = scanner.nextInt();
        } while (userNumber < 2);
        scanner.close();

        System.out.println("Number of steps required to reach 1 from " + userNumber + " using the Collatz conjecture:");
        System.out.println(collatzConjecture.doCollatzConjecture(userNumber));

    }

    public int doCollatzConjecture(int currentNumber){
        int count = 0;
        while (currentNumber != 1){
            if ((currentNumber % 2) == 0){
                currentNumber = currentNumber / 2;
            } else {
                currentNumber = (currentNumber * 3) + 1;
            }
            count++;
        }
        return count;
    }
}
