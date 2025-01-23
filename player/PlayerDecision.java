package player;

import java.util.Scanner;

public class PlayerDecision {
    public static int inputWithCheck(int decisionRange) {
        int decisionValue;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your decision.");

        try {
            decisionValue = scanner.nextInt();
            if (decisionValue <= decisionRange){
                return decisionValue;
            }else{
                System.out.println("Please only enter a number within the decision range.");
            }
        } catch (Exception e) {
            System.out.println("Please only enter numbers.");
        }
        return 0;
    }
}
