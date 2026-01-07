package Binary.Easy.MissingNumber;

import java.util.Arrays;

public class MissingNumberSolution {

    public static int missingNumber(int[] nums) {
        int result = 0;
        for(int i = 1; i <= nums.length; i++){
            result ^= i ^ nums[i-1];
        }
        return result;
    }

    // Test Cases

    public static void main(String[] args) {
        // 1. Basic test
        runTest(new int[]{3, 0, 1}, 2, "Test 1 - Basic example");

        // 2. Missing last number
        runTest(new int[]{0, 1}, 2, "Test 2 - Missing last (n)");

        // 3. Missing first number
        runTest(new int[]{1, 2, 3}, 0, "Test 3 - Missing first (0)");

        // 4. Unordered input
        runTest(new int[]{4, 2, 1, 0}, 3, "Test 4 - Missing middle (unordered)");

        // 5. Single element, missing 0
        runTest(new int[]{1}, 0, "Test 5 - Single element, missing 0");

        // 6. Single element, missing 1
        runTest(new int[]{0}, 1, "Test 6 - Single element, missing 1");

        // 7. Larger array
        runTest(new int[]{9,6,4,2,3,5,7,0,1}, 8, "Test 7 - Missing 8 from 0–9");

        // 8. Edge case with max n
        int n = 10000;
        int[] large = new int[n];
        for (int i = 0, j = 0; i <= n; i++) {
            if (i != 5678) {
                large[j++] = i;
            }
        }
        runTest(large, 5678, "Test 8 - Large input, missing 5678");

        System.out.println("\n🧪 Testing completed.");
    }

    public static void runTest(int[] input, int expected, String testName) {
        int result = missingNumber(input);
        if (result == expected) {
            System.out.println(testName + ": ✅ PASSED");
        } else {
            System.out.println(testName + ": ❌ FAILED (Expected " + expected + ", but got " + result + ")");
            System.out.println("  Input: " + Arrays.toString(input));
        }
    }

}

/*

    Solution Description :
    ----------------------

    This problem can be solved in multiple ways with a time complexity of O(n).
    However, we need to keep in mind that the space complexity needs to be O(1).

    Let's resolve this using binary operations.
    Before discussing the solutions, few things we need to know about the XOR operation :
    For any number n, m and k,
    - Number XORed with itself, returns 0 =>    n ^ n = 0
    - Number XORed with 0, returns itself =>    n ^ 0 = n
    - Order of XORing does not matter. =>       n ^ m ^ k = n ^ k ^ m = m ^ n ^ k

    Using this we can think of a solution.
    For a given array arr[n] having n digits between 0 and n,
    if we put all the digits and the indices through XOR operation, then the output will give us the missing number.

    For example, arr = {0,1,5,4,2}
    Now, the length of the array is 5 and it contains numbers between 0 to 5.
    If we XOR all the digits in the array along with the indices, all the common elements will cancel out to give 0
    and 0 XORed with the missing element, will give out the missing element.
    => (0 ^ 1 ^ 5 ^ 4 ^ 2) ^ (0 ^ 1 ^ 2 ^ 3 ^ 4 ^ 5)
    => (0 ^ 0) ^ (1 ^ 1) ^ (2 ^ 2) ^ (3) ^ (4 ^ 4) ^ (5 ^ 5)
    => 0 ^ 0 ^ 0 ^ 3 ^ 0 ^ 0
    => 3
    So, we see, we got the missing element.

    Line 6 :
    We set the result as 0. In case of an empty array, we will return 0.

    Line 7 :
    Since we have already set the initial value of result as 0, we will start the loop from 1
    and will go till the length of the array as we need to consider it in our calculation.
    Here, think of i as the total number of elements in the array considered in that iteration instead of the array index.
    For example, for 2nd index of the array, we are considering 3 elements in total. So, index is 2 but i will be 3 here.

    Line 8 :
    We use the short hand operator to set the new result as previous result ^ i ^ nums[i-1]

    Line 10 :
    Finally we return result which holds the missing number.

*/
