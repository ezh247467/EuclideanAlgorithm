import java.util.*;
public class EuclideanAlgorithm_Practice {
    public static int gcdWithRecursion(int int1, int int2) {
        if (int2 == 0) return Math.abs(int1);
        else {
            int remainder = int1 % int2;
            System.out.println(int1 + " = " + int2 + "(" + (int1 / int2) + 
            ") + " + remainder);
            return gcdWithRecursion(int2, remainder);
        }
    }
    public static int gcdWithWhileLoop(int int1, int int2) {
        int remainder = int1 % int2;
        while (remainder > 0) {
            System.out.println(int1 + " = " + int2 + "(" + (int1 / int2) + 
            ") + " + remainder);
            int1 = int2;
            int2 = remainder;
            remainder = int1 % int2;
        }
        System.out.println(int1 + " = " + int2 + "(" + (int1 / int2) + ") + "
        + remainder);
        return int2;
    }
    public static void linearCombination(int int1, int int2) {
        int tempInt = int1;
        int remainder = int1 % int2;
        ArrayList<Integer[]> pastGCD = new ArrayList<>();
        while (remainder > 0) {
            int quotient = int1 / int2;
            pastGCD.add(new Integer[] {int1, int2, quotient});
            int1 = int2;
            int2 = remainder;
            remainder = int1 % int2;
        }
        if (pastGCD.size() == 0) {
            System.out.println(int2 + " = " + int1 + "(0) + " + int2 + "(1)");
            System.out.println("For the linear combination of " + int1 + "x + " 
            + int2 + "y, we have x = 0 and y = 1");
        }
        else {
            int iter = pastGCD.size() - 1;
            int leftNum = pastGCD.get(iter)[0];
            int rightNum = pastGCD.get(iter)[1];
            int leftMultiplier = 1;
            int rightMultiplier = pastGCD.get(iter)[2] * -1;
            int tracker = 0;
            while (iter > -1) {
                System.out.println(int2 + " = " + leftNum + "(" + 
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
                System.out.println(int2 + " = " + leftNum + "(" + 
                leftMultiplier + ") + " + rightNum + "(" + 
                rightMultiplier + ")");
            }
            System.out.println("For the linear combination of " + leftNum +
            "x + " + rightNum + "y, we have x = " + leftMultiplier + " and y = "
            + rightMultiplier);
        }
    }
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("First number for gcd:");
        int num1 = scnr.nextInt();
        System.out.println("Second number for gcd:");
        int num2 = scnr.nextInt();
        System.out.println("\nFirst using Recursion to find GCD...");
        System.out.println("The gcd of " + num1 + " and " + num2
        + " is " + gcdWithRecursion(num1, num2));
        System.out.println("\nNow using a While Loop to find GCD...");
        System.out.println("The gcd of " + num1 + " and " + num2
        + " is " + gcdWithWhileLoop(num1, num2));
        System.out.println("\nNow finding Linear Combination...");
        linearCombination(num1, num2);
    }
}