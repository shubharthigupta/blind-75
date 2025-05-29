package ProblemsAndSolutions.Array.Medium.MaximumSubarray;

public class MaximumSubarraySolution {

    public static int maxSubArray(int[] nums) {
        int currMax = nums[0];
        int overallMax = nums[0];
        for(int i = 1; i < nums.length; i++){
            currMax = Math.max((currMax + nums[i]), nums[i]);
            overallMax = Math.max(currMax, overallMax);
        }
        return overallMax;
    }

    // Test Cases

    public static void main(String[] args) {
        // 1. LeetCode basic example
        runTest(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}, 6, "Test 1 - LeetCode example");

        // 2. All positive numbers
        runTest(new int[]{1, 2, 3, 4, 5}, 15, "Test 2 - All positive");

        // 3. All negative numbers
        runTest(new int[]{-1, -2, -3, -4}, -1, "Test 3 - All negative");

        // 4. Mix with large positive at end
        runTest(new int[]{-2, -1, 2, 3, 4, -5}, 9, "Test 4 - Positive tail");

        // 5. Single negative element
        runTest(new int[]{-5}, -5, "Test 5 - One negative");

        // 6. Single positive element
        runTest(new int[]{7}, 7, "Test 6 - One positive");

        // 7. Max subarray at start
        runTest(new int[]{5, 4, -1, -2, -3}, 9, "Test 7 - Max at start");

        // 8. Max subarray in middle
        runTest(new int[]{-2, -3, 4, -1, -2, 1, 5, -3}, 7, "Test 8 - Max in middle");

        // 9. Max subarray at end
        runTest(new int[]{-2, -3, -4, 2, 4, 6}, 12, "Test 9 - Max at end");

        // 10. Large input
        int[] large = new int[100000];
        for (int i = 0; i < 100000; i++) {
            large[i] = (i % 2 == 0) ? 1 : -1;
        }
        runTest(large, 1, "Test 10 - Large alternating 1s and -1s");

        System.out.println("\n🧪 Testing completed.");
    }

    public static void runTest(int[] input, int expected, String testName) {
        int result = maxSubArray(input);
        if (result == expected) {
            System.out.println(testName + ": ✅ PASSED");
        } else {
            System.out.println(testName + ": ❌ FAILED (Expected " + expected + ", but got " + result + ")");
        }
    }

    /*

    Solution Description :
    ----------------------

    We will be using Kadane's algorithm in solving this problem.
    Kadane's algorithm is a dynamic programming approach used to efficiently find the maximum sum of a contiguous subarray within a given array.
    It solves the maximum subarray sum problem in linear time, making it a valuable tool for various programming and optimization challenges.

    In this problem, we do not necessarily need to find the exact sub array.
    The solution only demands the maximum sum that can be obtained from a sub array of the given array and that is what we will return.
    At first, we start from the left end of the array which is the 0th element.
    As we proceed to the right, including the nth element each time, we calculate two values.
    The first one is the Current Maximum value which is the maximum between the sum of all the elements till the nth position and the nth element.
    The other is the Overall Maximum which is the maximum between the Current Maximum and the existing Overall Maximum.

    The idea behind this is to calculate the maximums as we traverse through the array.
    If the nth element is greater than the sum of all the elements till the nth position, it becomes the Current Maximum.
    This essentially means that all the previous elements can be discarded and the sub array now to be considered, starts from this element.
    The current maximum will keep on increasing and decreasing as we move along the array.
    However, to capture the peak value of this Current Maximum, we have the Overall Maximum, which is the final answer.

    Line 6-7 :
    We initialize both Current and Overall Maximum with the value of the 0th element of the given array since in the beginning this is the only subset.

    Line 8-11 :
    We start with the loop.
    We get the sum of the existing current maximum value and the nth element value and compare it with the nth element value.
    Whichever is higher, we update the Current Maximum value with that.
    We then update the Overall Maximum value with the maximum between the Current Maximum and the existing Overall Maximum value.

     */

}
