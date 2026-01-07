package Array.Easy.ContainsDuplicate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicateSolution {

    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> visited = new HashSet<>();
        for (int num : nums) {
            if (visited.contains(num)) return true;
            visited.add(num);
        }
        return false;
    }

    // Test Cases

    public static void main(String[] args) {
        // 1. No duplicates (sorted)
        runTest(new int[]{1, 2, 3, 4, 5}, false, "Test 1 - Unique sorted numbers");

        // 2. No duplicates (unsorted)
        runTest(new int[]{5, 3, 2, 1, 4}, false, "Test 2 - Unique unsorted numbers");

        // 3. Duplicate at start
        runTest(new int[]{2, 2, 3, 4, 5}, true, "Test 3 - Duplicate at beginning");

        // 4. Duplicate at end
        runTest(new int[]{1, 2, 3, 4, 1}, true, "Test 4 - Duplicate at end");

        // 5. Multiple duplicates
        runTest(new int[]{1, 2, 2, 3, 3, 4}, true, "Test 5 - Multiple duplicates");

        // 6. Single element
        runTest(new int[]{10}, false, "Test 6 - Single element");

        // 7. Empty array
        runTest(new int[]{}, false, "Test 7 - Empty array");

        // 8. Large array without duplicates
        int[] largeUnique = new int[10000];
        for (int i = 0; i < 10000; i++) {
            largeUnique[i] = i;
        }
        runTest(largeUnique, false, "Test 8 - Large array no duplicates");

        // 9. Large array with duplicate
        int[] largeWithDup = new int[10000];
        for (int i = 0; i < 9999; i++) {
            largeWithDup[i] = i;
        }
        largeWithDup[9999] = 5000;
        runTest(largeWithDup, true, "Test 9 - Large array with duplicate");

        System.out.println("\n🧪 Testing completed.");
    }

    public static void runTest(int[] input, boolean expected, String testName) {
        boolean result = containsDuplicate(input);
        if (result == expected) {
            System.out.println(testName + ": ✅ PASSED");
        } else {
            System.out.println(testName + ": ❌ FAILED (Expected " + expected + ", but got " + result + ")");
            System.out.println("  Input: " + Arrays.toString(input));
        }
    }

}
