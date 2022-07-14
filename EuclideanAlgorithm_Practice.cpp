#include <iostream>
#include <vector>
#include <chrono>
#include <thread>
#include <string>
using namespace std;

void typeOut(string text) {
    for (int character = 0; character < text.length(); character++) {
        this_thread::sleep_for(chrono::milliseconds(50));
        cout << text[character] << flush;
    }
}

void typeOutLn(string text) {
    typeOut(text);
    cout << endl;
}

int gcd(int int1, int int2) {
    if (int2 == 0) return abs(int1);
    else {
        int remainder = int1 % int2;
        return gcd(int2, remainder);
    }
}

int gcdWithRecursion(int int1, int int2) {
    if (int2 == 0) return abs(int1);
    else {
        int remainder = int1 % int2;
        typeOutLn(to_string(int1) + " = " + to_string(int2) + "(" + to_string(
        (int1 / int2)) + ") + " + to_string(remainder));
        return gcdWithRecursion(int2, remainder);
    }
}

int gcdWithWhileLoop(int int1, int int2) {
    int remainder = int1 % int2;
    while (remainder > 0) {
        typeOutLn(to_string(int1) + " = " + to_string(int2) + "(" + to_string(
        (int1 / int2)) + ") + " + to_string(remainder));
        int1 = int2;
        int2 = remainder;
        remainder = int1 % int2;
    }
    typeOutLn(to_string(int1) + " = " + to_string(int2) + "(" + to_string(
    (int1 / int2)) + ") + " + to_string(remainder));
    return int2;
}

void linearCombination(int int1, int int2) {
    int tempInt = int1, remainder = int1 % int2;
    vector<vector<int> > pastGCD;
    while (remainder > 0) {
        int arr[3] = {int1, int2, int1 / int2};
        vector<int> data(begin(arr), end(arr));
        pastGCD.push_back(data);
        int1 = int2;
        int2 = remainder;
        remainder = int1 % int2;
    }
    if (pastGCD.size() == 0) {
        typeOutLn(to_string(int2) + " = " + to_string(int1) + "(0) + " + 
        to_string(int2) + "(1)");
        typeOutLn("For the linear combination of " + to_string(int1) + "x + " 
        + to_string(int2) + "y, we have x = 0 and y = 1");
    }
    else {
        int iter = pastGCD.size() - 1, tracker = 0;
        int leftNum = pastGCD[iter][0], rightNum = pastGCD[iter][1];
        int leftMultiplier = 1, rightMultiplier = pastGCD[iter][2] * -1;
        while (iter > -1) {
            typeOutLn(to_string(int2) + " = " + to_string(leftNum) + "(" + 
            to_string(leftMultiplier) + ") + " + to_string(rightNum) + "(" + 
            to_string(rightMultiplier) + ")");
            iter--;
            if (iter < 0) break;
            if (tracker % 2 == 0) {
                leftMultiplier = (rightMultiplier * pastGCD[iter][2] - 
                leftMultiplier) * -1;
                rightNum = pastGCD[iter][0];
            }
            else {
                rightMultiplier = (leftMultiplier * pastGCD[iter][2] - 
                rightMultiplier) * -1;
                leftNum = pastGCD[iter][0];
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
            typeOutLn(to_string(int2) + " = " + to_string(leftNum) + "(" + 
            to_string(leftMultiplier) + ") + " + to_string(rightNum) + "(" + 
            to_string(rightMultiplier) + ")");
        }
        typeOutLn("For the linear combination of " + to_string(leftNum) +
        "x + " + to_string(rightNum) + "y, we have x = " + to_string
        (leftMultiplier) + " and y = " + to_string(rightMultiplier));
    }
}

int lcm(int int1, int int2) {
    return int1 * int2 / gcd(int1, int2);
}

int main() {
    // Taking user input
    int num1, num2;
    typeOut("First number for gcd: ");
    cin >> num1;
    typeOut("Second number for gcd: ");
    cin >> num2;

    // Using the recursive function to find GCD
    typeOutLn("\nFirst using Recursion to find GCD...");
    typeOutLn("The gcd of " + to_string(num1) + " and " + to_string(num2) 
    + " is " + to_string(gcdWithRecursion(num1, num2)));

    // Using a while loop to find GCD
    typeOutLn("\nNow using a While Loop to find GCD...");
    typeOutLn("The gcd of " + to_string(num1) + " and " + to_string(num2) 
    + " is " + to_string(gcdWithWhileLoop(num1, num2)));

    // Finding Linear Combination requires while loop version.
    typeOutLn("\nNow finding Linear Combination...");
    linearCombination(num1, num2);

    // Finding the LCM
    typeOutLn("\nNow finding the LCM...");
    typeOutLn("The LCM of " + to_string(num1) + " and " + to_string(num2) 
    + " is " + to_string(lcm(num1, num2)) + ".");
    return 0;
}