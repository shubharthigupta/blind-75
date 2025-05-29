package ProblemsAndSolutions.DynamicProgramming.Easy.ClimbingStairs;

public class ClimbingStairsSolution {

    public static int climbingStairs(int n){
        if(n==0 || n==1) return 1;
        return climbingStairs(n-1) + climbingStairs(n-2);
    }

    // Test Cases

    public static void main(String[] args) {
        // 1. Minimum edge case
        runTest(1, 1, "Test 1 - n = 1 (only 1 way)");

        // 2. Base case
        runTest(2, 2, "Test 2 - n = 2 (1+1 or 2)");

        // 3. Small input
        runTest(3, 3, "Test 3 - n = 3");

        // 4. Small input
        runTest(4, 5, "Test 4 - n = 4");

        // 5. Medium input
        runTest(5, 8, "Test 5 - n = 5");

        // 6. Check known Fibonacci value
        runTest(10, 89, "Test 6 - n = 10");

        // 7. Upper constraint
        runTest(45, 1836311903, "Test 7 - n = 45 (upper bound)");

        System.out.println("\n🧪 Testing completed.");
    }

    public static void runTest(int input, int expected, String testName) {
        int result = climbingStairs(input);
        if (result == expected) {
            System.out.println(testName + ": ✅ PASSED");
        } else {
            System.out.println(testName + ": ❌ FAILED (Expected " + expected + ", but got " + result + ")");
        }
    }

}

/*

    Solution Description :
    ----------------------

    Before solving this question, let us understand the question first and simplify it for us.
    If a person has n steps to climb, we need to find the number of distinct ways it can climb to the top with 1 or 2 steps at a time.
    Suppose, the total number of steps are 3.
    Total ways to climb : (1 step + 1 step + 1 step) or (2 steps + 1 step) or (1 step + 2 steps). So, 3 ways in total.

    Now, let us visualise this graphically.
    If the total number of steps given is 5 and considering the person is at 0th step.


           (0)______________________________________+2______________________________________(2)_________+2__________(4)___+2___(6)-Overstep
            |                                                                                |                       |
            +1                                                                               +1                      +1
            |                                                                                |                       |
           (1)______________________+2__________________________(3)___+2___(5)-End          (3)___+2___(5)-End      (5)-End
            |                                                    |                           |
            +1                                                   +1                          +1
            |                                                    |                           |
           (2)_________+2__________(4)___+2___(6)-Overstep      (4)___+2___(6)-Overstep     (4)___+2___(6)-Overstep
            |                       |                            |                           |
            +1                      +1                           +1                          +1
            |                       |                            |                           |
           (3)___+2___(5)-End      (5)-End                      (5)-End                     (5)-End
            |
            +1
            |
           (4)___+2___(6)-Overstep
            |
            +1
            |
           (5)-End

    At first, the person can take either 1 step, which will land him/her on 1st step, or he/she can take 2 steps to reach the 2nd step.
    On 1st step, there are again two options - +1 step to reach 2nd step or +2 steps to reach 3rd step.
    Similarly, from 2nd step, two options to move forward - +1 step to reach 4th step or +2 steps to reach 5th step.

    One way to solve this problem is the Depth First Search, where we use recursion and it will give us a time complexity of O(2^n)
    and space complexity of O(n). This is not efficient, so let's analyse it further.

    If you look at the tree above, you will see that on each step,
    the path options to reach the final step 5 from there is same, no matter how you reach that step.

    For example, there are 3 ways to reach 3rd Step - (1+1+1) or (2+1) 0r (1+2).
    However, no matter you reach there, the ways to reach 5th step is constant, i.e. 2 ways - (1+1) or (2).
    So, this part is repetitive in the tree above:

    (3)___+2___(5)-End
     |
     +1
     |
    (4)___+2___(6)-Overstep
     |
     +1
     |
    (5)-End

    There are 3 places where you will see this, since there are 3 ways to reach the 3rd step.
    This is similar to any of the steps.
    The sub-tree from 1st step occurs only once in the whole big tree.
    The sub-tree from 2nd step repeats twice in the whole big tree.
    The sub-tree from 3rd step repeats thrice in the whole big tree.
    The sub-tree from 4th step repeats 5 times in the whole big tree.
    And we see, there are 8 different ways to reach 5th and final step.

    This will simplify our analysis to solve this problem since we can compute it once and re-use it.
    So, we can implement the dynamic programming approach where we cache the already computed result so that it can be reused further without any redundant work.
    This is also called Memoization. It would take a time complexity of O(n).
    However, this would take a space complexity of O(n). Let's see if we can reduce it as well.

    To look further in the pattern, let's calculate the steps to reach the final step from each step:
    0th Step    1st Step    2nd Step    3rd Step    4th Step    5th Step
    --------    --------    --------    --------    --------    --------
    8           5           3           2           1           1
    Here we see the pattern similar to the fibonacci series.
    So, the answer would just be fibonacci of 5 (total number of steps) which would give us 8 in this case.
    We do not need to store the value of all the previous numbers in this series.
    We can get the answer if we just know what comes in the previous two places of that number and then just add it up.

 */
