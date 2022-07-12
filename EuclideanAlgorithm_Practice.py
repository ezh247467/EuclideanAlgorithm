from time import sleep
def typeOut(text):
    for character in text:
        sleep(0.05)
        print(character, end="", flush=True)
def typeOutLn(text):
    typeOut(f"{text}\n")
def gcd(int1, int2):
    if int2 == 0:
        return abs(int1)
    else:
        remainder = int1 % int2
        return gcd(int2, remainder)
def gcdWithRecursion(int1, int2):
    if int2 == 0:
        return abs(int1)
    else:
        remainder = int1 % int2
        typeOutLn(f"{int1} = {int2}({int1 // int2}) + {remainder}")
        return gcdWithRecursion(int2, remainder)
def gcdWithWhileLoop(int1, int2):
    remainder = int1 % int2
    while remainder > 0:
        typeOutLn(f"{int1} = {int2}({int1 // int2}) + {remainder}")
        int1, int2 = int2, remainder
        remainder = int1 % int2
    typeOutLn(f"{int1} = {int2}({int1 // int2}) + {remainder}")
    return int2
def linearCombination(int1, int2):
    tempInt = int1
    remainder = int1 % int2
    pastGCD = []
    while remainder > 0:
        pastGCD.append((int1, int2, int1 // int2))
        int1, int2 = int2, remainder
        remainder = int1 % int2
    if len(pastGCD) == 0:
        typeOutLn(f"{int2} = {int1}(0) + {int2}(1)")
        typeOutLn(f"For the linear combination of {int1}x + {int2}y, we have x = 0 and y = 1")
    else:
        iter = len(pastGCD) - 1
        leftNum = pastGCD[iter][0]
        rightNum = pastGCD[iter][1]
        leftMultiplier = 1
        rightMultiplier = pastGCD[iter][2] * -1
        tracker = 0
        while iter > -1:
            typeOutLn(f"{int2} = {leftNum}({leftMultiplier}) + {rightNum}({rightMultiplier})")
            iter -= 1
            if iter < 0:
                break
            if tracker % 2 == 0:
                leftMultiplier = (rightMultiplier * pastGCD[iter][2] - leftMultiplier) * -1
                rightNum = pastGCD[iter][0]
            else:
                rightMultiplier = (leftMultiplier * pastGCD[iter][2] - rightMultiplier) * -1
                leftNum = pastGCD[iter][0]
            tracker += 1
        if tempInt == rightNum:
            rightNum, leftNum, rightMultiplier, leftMultiplier = leftNum, rightNum,leftMultiplier, rightMultiplier
            typeOutLn(f"{int2} = {leftNum}({leftMultiplier}) + {rightNum}({rightMultiplier})")
        typeOutLn(f"For the linear combination of {leftNum}x + {rightNum}y, we have x = {leftMultiplier} and y = {rightMultiplier}")
def lcm(int1, int2):
    return int1 * int2 // gcd(int1, int2)

typeOut("First number for gcd: ")
num1 = int(input())
typeOut("Second number for gcd: ")
num2 = int(input())
# Using the recursive function to find GCD
typeOutLn("\nFirst using Recursion to find GCD...")
typeOutLn(f"The gcd of {num1} and {num2} is {gcdWithRecursion(num1, num2)}")
# Using a while loop to find GCD
typeOutLn("\nNow using a While Loop to find GCD...")
typeOutLn(f"The gcd of {num1} and {num2} is {gcdWithWhileLoop(num1, num2)}")
# Finding Linear Combination requires while loop version.
typeOutLn("\nNow finding Linear Combination...")
linearCombination(num1, num2)
typeOutLn(f"\nThe LCM of {num1} and {num2} is {lcm(num1, num2)}.")