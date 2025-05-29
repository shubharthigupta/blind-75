package ProblemsAndSolutions.Array.Easy.TwoSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumSolution {

    public static int[] twoSum(int[] nums, int target){

        Map<Integer, Integer> intMap = new HashMap<>();

        for(int i=0; i<nums.length; i++){
            if(intMap.containsKey(target - nums[i])){
                return new int[]{intMap.get(target - nums[i]),i};
            }
            intMap.put(nums[i], i);
        }
        return new int[]{};
    }


    // Test Cases

    public static void main(String[] args) {
        // 1. Basic example
        runTest(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}, "Test 1 - Basic example");

        // 2. Includes negative number
        runTest(new int[]{3, 2, 4}, 6, new int[]{1, 2}, "Test 2 - With negative/positive mix");

        // 3. Multiple pairs possible (ensure only one valid returned)
        runTest(new int[]{3, 3}, 6, new int[]{0, 1}, "Test 3 - Duplicates, same value");

        // 4. Zero and negatives
        runTest(new int[]{0, 4, 3, 0}, 0, new int[]{0, 3}, "Test 4 - Two zeros");

        // 5. Large input size (edge boundary test)
        int[] large = new int[10000];
        for (int i = 0; i < 9999; i++) large[i] = i;
        large[9999] = 100000;
        runTest(large, 100001, new int[]{9998, 9999}, "Test 5 - Large input");

        // 6. Negative target
        runTest(new int[]{-3, 4, 3, 90}, 0, new int[]{0, 2}, "Test 6 - Target is zero");

        // 7. Target is sum of first and last
        runTest(new int[]{1, 2, 3, 4, 5}, 6, new int[]{1, 3}, "Test 7 - Mid-pair sum");

        // 8. No solution (should return empty array)
        runTest(new int[]{1, 2, 3}, 10, new int[]{}, "Test 8 - No solution");

        System.out.println("\n🧪 Testing completed.");
    }


    public static boolean isValidPair(int[] result, int[] expected, int[] input, int target) {
        if (expected.length == 0) {
            return result.length == 0; // both expected and result are empty
        }
        if (result.length != 2) return false;
        int a = input[result[0]];
        int b = input[result[1]];
        return a + b == target && result[0] != result[1];
    }


    public static void runTest(int[] input, int target, int[] expected, String testName) {
        int[] result = twoSum(input, target);
        if (isValidPair(result, expected, input, target)) {
            System.out.println(testName + ": ✅ PASSED");
        } else {
            System.out.println(testName + ": ❌ FAILED (Expected indices for sum " + target + ")");
            System.out.println("  Input: " + Arrays.toString(input));
            System.out.println("  Target: " + target);
            System.out.println("  Output indices: " + Arrays.toString(result));
        }
    }



    /*

    Solution Description :
    ----------------------

    Line 11 :
    Initializing a HashMap which will contain the elements of the input integer array 'nums' as Key
    and the index of the element as Value.

    Line 13:
    Iterating over nums.

    Line 14-16:
    If the difference between the input integer 'target' and the current element is equal to a key which is already present in intMap,
    we return the result as an integer array of the value for the corresponding key from the map (which is the index of the element key)
    and the index of the current element.

    Line 17:
    Otherwise, add the current element as the key with its index as the value in the intMap.

    Line 19:
    If the loop ends without finding any exact solution from nums we return an empty integer array as default result.

     */

}
