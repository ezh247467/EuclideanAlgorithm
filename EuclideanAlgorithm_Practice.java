import java.util.*;
public class EuclideanAlgorithm_Practice {
    public static void typeOut(String text) {
        for (int character = 0; character < text.length(); character++) {
            try {
                Thread.sleep(50);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.printf("%c", text.charAt(character));
        }
    }
    public static void typeOutLn(String text) {
        typeOut(text);
        System.out.println();
    }
    public static int gcd(int int1, int int2) {
        if (int2 == 0) return Math.abs(int1);
        else {
            int remainder = int1 % int2;
            return gcd(int2, remainder);
        }
    }
    public static int gcdWithRecursion(int int1, int int2) {
        if (int2 == 0) return Math.abs(int1);
        else {
            int remainder = int1 % int2;
            typeOutLn(int1 + " = " + int2 + "(" + (int1 / int2) + 
            ") + " + remainder);
            return gcdWithRecursion(int2, remainder);
        }
    }
    public static int gcdWithWhileLoop(int int1, int int2) {
        int remainder = int1 % int2;
        while (remainder > 0) {
            typeOutLn(int1 + " = " + int2 + "(" + (int1 / int2) + 
            ") + " + remainder);
            int1 = int2;
            int2 = remainder;
            remainder = int1 % int2;
        }
        typeOutLn(int1 + " = " + int2 + "(" + (int1 / int2) + ") + "
        + remainder);
        return int2;
    }
    public static void linearCombination(int int1, int int2) {
        int tempInt = int1, remainder = int1 % int2;
        ArrayList<Integer[]> pastGCD = new ArrayList<>();
        while (remainder > 0) {
            pastGCD.add(new Integer[] {int1, int2, int1 / int2});
            int1 = int2;
            int2 = remainder;
            remainder = int1 % int2;
        }
        if (pastGCD.size() == 0) {
            typeOutLn(int2 + " = " + int1 + "(0) + " + int2 + "(1)");
            typeOutLn("For the linear combination of " + int1 + "x + " 
            + int2 + "y, we have x = 0 and y = 1");
        }
        else {
            int iter = pastGCD.size() - 1, tracker = 0;
            int leftNum = pastGCD.get(iter)[0], rightNum = pastGCD.get(iter)[1];
            int leftMultiplier = 1, rightMultiplier = pastGCD.get(iter)[2] * -1;
            while (iter > -1) {
                typeOutLn(int2 + " = " + leftNum + "(" + 
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
                typeOutLn(int2 + " = " + leftNum + "(" + 
                leftMultiplier + ") + " + rightNum + "(" + 
                rightMultiplier + ")");
            }
            typeOutLn("For the linear combination of " + leftNum +
            "x + " + rightNum + "y, we have x = " + leftMultiplier + " and y = "
            + rightMultiplier);
        }
    }
    public static int lcm(int int1, int int2) {
        return int1 * int2 / gcd(int1, int2);
    }
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        typeOut("First number for gcd: ");
        int num1 = scnr.nextInt();
        typeOut("Second number for gcd: ");
        int num2 = scnr.nextInt();
        // Using the recursive function to find GCD
        typeOutLn("\nFirst using Recursion to find GCD...");
        typeOutLn("The gcd of " + num1 + " and " + num2 + " is " + 
        gcdWithRecursion(num1, num2));
        // Using a while loop to find GCD
        typeOutLn("\nNow using a While Loop to find GCD...");
        typeOutLn("The gcd of " + num1 + " and " + num2 + " is " + 
        gcdWithWhileLoop(num1, num2));
        // Finding Linear Combination requires while loop version.
        typeOutLn("\nNow finding Linear Combination...");
        linearCombination(num1, num2);
        typeOutLn("\nThe LCM of " + num1 + " and " + num2 + " is " + lcm(num1, num2) + ".");
        scnr.close();
    }
}