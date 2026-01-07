package Array.Medium.ProductOfArrayExceptSelf;

import java.util.Arrays;

public class ProductOfArrayExceptSelfSolution {

    public static int[] productExceptSelf(int[] nums) {
        int[] prefixProduct = new int[nums.length];
        prefixProduct[0] = 1;
        for(int i = 1; i < nums.length; i++){
            prefixProduct[i] = prefixProduct[i-1]*nums[i-1];
        }
        int suffixProduct = 1;
        for(int j = nums.length-1; j >= 0; j--){
            prefixProduct[j] = prefixProduct[j]*suffixProduct;
            suffixProduct = suffixProduct*nums[j];
        }
        return prefixProduct;
    }

    // Test Cases

    public static void main(String[] args) {
        // 1. Basic case
        runTest(new int[]{1, 2, 3, 4}, new int[]{24, 12, 8, 6}, "Test 1 - Basic");

        // 2. With zero included
        runTest(new int[]{-1, 1, 0, -3, 3}, new int[]{0, 0, 9, 0, 0}, "Test 2 - Includes zero");

        // 3. Two zeros (output should be all zeros)
        runTest(new int[]{0, 4, 0}, new int[]{0, 0, 0}, "Test 3 - Two zeros");

        // 4. One zero (non-zero results except at zero position)
        runTest(new int[]{1, 2, 0, 4}, new int[]{0, 0, 8, 0}, "Test 4 - One zero");

        // 5. All ones
        runTest(new int[]{1, 1, 1, 1}, new int[]{1, 1, 1, 1}, "Test 5 - All ones");

        // 6. All negative numbers
        runTest(new int[]{-1, -2, -3, -4}, new int[]{-24, -12, -8, -6}, "Test 6 - All negatives");

        // 7. Single element (invalid based on constraints, but good for robustness)
        runTest(new int[]{5}, new int[]{1}, "Test 7 - Single element");

        // 8. Large input
        int[] largeInput = new int[100000];
        Arrays.fill(largeInput, 2);
        int[] largeExpected = new int[100000];
        Arrays.fill(largeExpected, (int)Math.pow(2, 99999)); // Note: too large for int, will overflow in real
        // Skipping actual execution of large case to avoid runtime issues

        System.out.println("\n🧪 Testing completed.");
    }

    public static boolean arraysEqual(int[] a, int[] b) {
        return Arrays.equals(a, b);
    }

    public static void runTest(int[] input, int[] expected, String testName) {
        int[] result = productExceptSelf(input);
        if (arraysEqual(result, expected)) {
            System.out.println(testName + ": ✅ PASSED");
        } else {
            System.out.println(testName + ": ❌ FAILED");
            System.out.println("  Input:     " + Arrays.toString(input));
            System.out.println("  Expected:  " + Arrays.toString(expected));
            System.out.println("  Got:       " + Arrays.toString(result));
        }
    }



    /*

    Solution Description :
    ----------------------

    The solution is based on the concepts of Prefix Product and Suffix Product.
    For example, given an array {1,2,3,4}
    The prefix product array would consist of the products of all the elements before the current element.
    So, prefix product = {1,1,2,6}.
    For 0th element, we have no elements before it, so the product would be 1.
    For 1st element, we have '1' in the given array, so the product would be 1.
    For the 2nd element, we have two elements before that (0th and 1st) which are '1' and '2', the product of which would be 2. And so on.
    Similarly, the suffix product would consist of the products of all the elements after the current element.
    For the given array, suffix product = {24,12,4,1}
    Combining these two arrays and multiplying the nth element of both, we get our answer = {24,12,8,6}

    Now, resolving this using both prefix and suffix product array would require a time complexity of O(3n).
    We can further improve this by not using an array for the suffix product. Instead, we can use it as an integer variable.
    In the second loop, we can just keep on changing the suffix product. This would give us a time complexity of O(2n).

    Further, instead of having a separate array for the answer, we can modify and reuse the prefix product array.
    This would again reduce the space complexity.


    Line 8 :
    We initialize the prefix product array with the same length as the given num array.

    Line 9:
    Since the prefix product of the 0th element of the num array is always going to be 1, we set the 0th element of prefix product array as 1.

    Line 10-12:
    We create the prefix product array. The nth element of this array is just going to be the product of the previous element and the nth element of the num array.

    Line 13:
    We initialize the suffix product variable with 1 (since the suffix product of the last element of num array is always going to be 1)

    Line 14-18:
    We start a reverse loop. We modify the nth element of the prefix product array with the product of the current nth element of the prefix product array
    and the current suffix product value.
    Then, for the next iteration, we update the suffix product value with the product of the current suffix product value and the nth element of the num array.
    Finally, we return the prefix product array as the answer after completing the loop.

     */

}
