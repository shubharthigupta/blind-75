package ProblemsAndSolutions.Array.Medium.MaximumProductSubarray;

public class MaximumProductSubarraySolution {

    public static int maxProduct(int[] nums) {
        int answer = nums[0];
        int max = 1;
        int min = 1;

        for (int num : nums) {
            if (num == 0) {
                max = 1;
                min = 1;
                answer = Math.max(answer, num);
            } else {
                int tmp = min;
                min = Math.min(num, Math.min((num * min), (num * max)));
                max = Math.max(num, Math.max((num * tmp), (num * max)));
                answer = Math.max(answer, max);
            }
        }
        return answer;
    }

    // Test Cases

    public static void main(String[] args) {
        // 1. Simple case
        runTest(new int[]{2, 3, -2, 4}, 6, "Test 1 - Basic case");

        // 2. Zero in the array
        runTest(new int[]{-2, 0, -1}, 0, "Test 2 - Zero breaks subarray");

        // 3. All positive numbers
        runTest(new int[]{1, 2, 3, 4}, 24, "Test 3 - All positives");

        // 4. All negative numbers
        runTest(new int[]{-2, -3, -4}, 12, "Test 4 - All negatives (even count)");

        // 5. Mixed with large negative product
        runTest(new int[]{-2, -3, 0, -2, -40}, 80, "Test 5 - Mixed negatives and zero");

        // 6. Mixed signs, max at start
        runTest(new int[]{6, -3, -10, 0, 2}, 180, "Test 6 - Max subarray at start");

        // 7. Mixed signs, max at end
        runTest(new int[]{-1, -3, -10, 0, 60}, 60, "Test 7 - Max subarray at end");

        // 8. Single negative
        runTest(new int[]{-2}, -2, "Test 8 - Single negative number");

        // 9. Single positive
        runTest(new int[]{5}, 5, "Test 9 - Single positive number");

        // 10. Two negative values
        runTest(new int[]{-2, -3}, 6, "Test 10 - Two negatives");

        // 11. Negative then positive product
        runTest(new int[]{-1, -2, -9, -6}, 108, "Test 11 - Long negative chain");

        // 12. Large array test
        int[] large = new int[10000];
        for (int i = 0; i < large.length; i++) {
            large[i] = (i == 5000) ? 100 : 1;
        }
        runTest(large, 100, "Test 12 - Large array with one peak");

        System.out.println("\n🧪 Testing completed.");
    }

    public static void runTest(int[] input, int expected, String testName) {
        int result = maxProduct(input);
        if (result == expected) {
            System.out.println(testName + ": ✅ PASSED");
        } else {
            System.out.println(testName + ": ❌ FAILED (Expected " + expected + ", but got " + result + ")");
        }
    }

    /*

    Solution Description :
    ----------------------

    We set the default value of our answer as the first element of the array.
    With each iteration, we keep deciding the maximum and minimum values.

    Initially, we set these values to 1.
    Then from the first digit in the array, we compute the maximum and minimum values among three options :
    1. The number itself.
    2. The product of the number and the current minimum value.
    3. The product of the number and the current maximum value.
    We set the value of our answer to the maximum between the current answer and the maximum value computed at that iteration.

    Why do we need to consider the number at the ith value itself for comparison while calculating the new max and min value ?
    Suppose we have an array [-1, -2 ,-3].
    At the beginning, we have max=1 and min=1.
    At i=0, we have new max=-1, and new min=-1.
    At i=1, we see the new max value is 2 which we get by (nums[i] * old max)
    The minimum here is however -2, which is not (nums[i] * old min). It is rather the value at the ith place, nums[i].
    This is why we need to consider nums[i] with (nums[i] * old max) and (nums[i] * old min) to calculate the new max/min.

    Why do we need to calculate the min value at all if the aim is to find the maximum product ?
    Consider the case explained above.
    At i=2, we see the new min is -6 which is (nums[i] * old max) and the new max is 6, which is (nums[i] * old min).
    This is why we need to calculate the new min value at every ith place because if the next digit is negative, it might give us the new max at (i+1)th place.

    What happens when we see a 0 at any ith place ?
    Consider the array [-1, 2, -3, 0, 5, 2, -1]
    Here, till i=2, we have max=6 and min=-6. Now, if we continue our previous approach, we get the new max=0 and the new min=0.
    This kills the streak. And now, if we move ahead if a max and min set to 0, these two will remain 0 till the end of the loop.
    So, to solve this, in case any element of the given array is 0, we set the max and min to 1 and the answer to the maximum of 0 or the current answer.
    0 is basically a reset to our calculations here.
    Also, 0 cannot be included in the array we consider us the answer. So, 0 in the array is like a partition in the array.
    The answer can either be on the left of the zero or on the right side of it in the array. But it can never include 0.

    Line 6-8 :
    Setting the default value to answer and the min and max variable.

    Line 10 :
    Start the iteration

    Line 11-14 :
    In case of any array element equals to 0, we set the max and min to 1
    and the answer to the maximum of current answer and 0.

    Line 15-19 :
    We set the current min value to a variable tmp.
    We then check the following three numbers :
    1. The number itself.
    2. The product of the number and the current minimum value.
    3. The product of the number and the current maximum value.
    and then set the minimum and maximum value among these three.

     */
}

