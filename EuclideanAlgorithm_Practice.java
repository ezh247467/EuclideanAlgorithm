import java.util.*;
public class EuclideanAlgorithm_Practice {
    public static void typeOut(String text) {
        for (int i = 0; i < text.length(); i++) {
            try {
                Thread.sleep(50);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.printf("%c", text.charAt(i));
        }
        System.out.println();
    }
    public static int gcdWithRecursion(int int1, int int2) {
        if (int2 == 0) return Math.abs(int1);
        else {
            int remainder = int1 % int2;
            typeOut(int1 + " = " + int2 + "(" + (int1 / int2) + 
            ") + " + remainder);
            return gcdWithRecursion(int2, remainder);
        }
    }
    public static int gcdWithWhileLoop(int int1, int int2) {
        int remainder = int1 % int2;
        while (remainder > 0) {
            typeOut(int1 + " = " + int2 + "(" + (int1 / int2) + 
            ") + " + remainder);
            int1 = int2;
            int2 = remainder;
            remainder = int1 % int2;
        }
        typeOut(int1 + " = " + int2 + "(" + (int1 / int2) + ") + "
        + remainder);
        return int2;
    }
    public static void linearCombination(int int1, int int2) {
        int tempInt = int1, remainder = int1 % int2;
        ArrayList<Integer[]> pastGCD = new ArrayList<>();
        while (remainder > 0) {
            int quotient = int1 / int2;
            pastGCD.add(new Integer[] {int1, int2, quotient});
            int1 = int2;
            int2 = remainder;
            remainder = int1 % int2;
        }
        if (pastGCD.size() == 0) {
            typeOut(int2 + " = " + int1 + "(0) + " + int2 + "(1)");
            typeOut("For the linear combination of " + int1 + "x + " 
            + int2 + "y, we have x = 0 and y = 1");
        }
        else {
            int iter = pastGCD.size() - 1, tracker = 0;
            int leftNum = pastGCD.get(iter)[0], rightNum = pastGCD.get(iter)[1];
            int leftMultiplier = 1, rightMultiplier = pastGCD.get(iter)[2] * -1;
            while (iter > -1) {
                typeOut(int2 + " = " + leftNum + "(" + 
                leftMultiplier + ") + " + rightNum + "(" + 
                rightMultiplier + ")");
                iter--;
                if (iter < 0) break;
                if (tracker % 2 == 0) {
                    leftMultiplier = (rightMultiplier * pastGCD.get(iter)[2] - 
                    leftMultiplier) * -1;
                    rightNum = pastGCD.get(iter)[0];
                }
                else {
                    rightMultiplier = (leftMultiplier * pastGCD.get(iter)[2] - rightMultiplier) * -1;
                    leftNum = pastGCD.get(iter)[0];
                }
                tracker++;
            }
            if (tempInt == rightNum) {
                int tempLeft = leftNum;
                leftNum = rightNum;
                rightNum = tempLeft;
                int tempLeftMultiplier = leftMultiplier;
                leftMultiplier = rightMultiplier;
                rightMultiplier = tempLeftMultiplier;
                typeOut(int2 + " = " + leftNum + "(" + 
                leftMultiplier + ") + " + rightNum + "(" + 
                rightMultiplier + ")");
            }
            typeOut("For the linear combination of " + leftNum +
            "x + " + rightNum + "y, we have x = " + leftMultiplier + " and y = "
            + rightMultiplier);
        }
    }
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        typeOut("First number for gcd:");
        int num1 = scnr.nextInt();
        typeOut("Second number for gcd:");
        int num2 = scnr.nextInt();
        // Using the recursive function to find GCD
        typeOut("\nFirst using Recursion to find GCD...");
        typeOut("The gcd of " + num1 + " and " + num2 + " is " + 
        gcdWithRecursion(num1, num2));
        // Using a while loop to find GCD
        typeOut("\nNow using a While Loop to find GCD...");
        typeOut("The gcd of " + num1 + " and " + num2 + " is " + 
        gcdWithWhileLoop(num1, num2));
        // Turns out you need to use the while loop method to find the linear 
        // combination
        typeOut("\nNow finding Linear Combination...");
        linearCombination(num1, num2);
        scnr.close();
    }
}