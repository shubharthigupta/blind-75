package ProblemsAndSolutions.Array.Medium.SearchInRotatedSortedArray;

public class SearchInRotatedSortedArraySolution {

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;

        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

        }
        return -1;
    }

    // Test Cases

    public static void main(String[] args) {
        // 1. Target in right rotated half
        runTest(new int[]{4,5,6,7,0,1,2}, 0, 4, "Test 1 - Found in rotated half");

        // 2. Target in left sorted half
        runTest(new int[]{4,5,6,7,0,1,2}, 5, 1, "Test 2 - Found in left half");

        // 3. Target not found
        runTest(new int[]{4,5,6,7,0,1,2}, 3, -1, "Test 3 - Not found");

        // 4. Array not rotated
        runTest(new int[]{1,2,3,4,5,6,7}, 4, 3, "Test 4 - Sorted, not rotated");

        // 5. Single-element array, target found
        runTest(new int[]{1}, 1, 0, "Test 5 - One element, found");

        // 6. Single-element array, target not found
        runTest(new int[]{1}, 0, -1, "Test 6 - One element, not found");

        // 7. Two elements, target is second
        runTest(new int[]{3, 1}, 1, 1, "Test 7 - Two elements, found");

        // 8. Target is at pivot point
        runTest(new int[]{6,7,1,2,3,4,5}, 1, 2, "Test 8 - Found at pivot");

        // 9. Large input rotated
        int[] large = new int[10000];
        for (int i = 0; i < 10000; i++) {
            large[i] = (i + 5000) % 10000; // rotated at index 5000
        }
        runTest(large, 42, (42 + 5000) % 10000, "Test 9 - Large rotated array");

        // 10. Found at end
        runTest(new int[]{5,6,7,8,9,1,2,3,4}, 4, 8, "Test 10 - Found at end");

        System.out.println("\n🧪 Testing completed.");
    }

    public static void runTest(int[] input, int target, int expected, String testName) {
        int result = search(input, target);
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

    A rotated sorted array is a type of sorted array which has been rotated n times from left-to-right.
    For example, consider a sorted array : {0,1,2,3,4,5,6}
    Now let's say we rotate it 1 time, it will become : {6,0,1,2,3,4,5}
    If we rotate it again, i.e. for 2 times, it will become : {5,6,0,1,2,3,4}
    Rotating it for number of times equal to its length (in this case, 7) will give us where we started : {0,1,2,3,4,5,6}

    Now, in order to find a given number in an array, doing a binary search gives us a time complexity of O(log(n)).

    Here, we are given a target and we need to search for it in the given rotated array using binary search.

    Line 6-8 :
    Initially, we set our left index indicator to the left most index of the array which is 0 and the right to the right most index which is the (length of the array - 1).
    We set the mid index as 0 as well.

    Line 10-11 :
    We start a while loop untill the left index is greater than the right one.
    We set the mid index of the array.
    Generally, the formula for that is (left+right)/2.
    However, this could lead to overflow error. Suppose we have right as Integer.MAX_VALUE.
    Then having (left+right) would give an overflow value and the computation will yield wrong result.
    So, we modify this formula as (left + (right-left)/2).
    Mathematically, it means the same thing. However, it prevents any overflow error in Java programming.

    Line 12-13 :
    If the element at the mid index is equal to the given target, we have found our number and we return it.

    Line 14 :
    Apart from the element at mid index being the target, we also have two other possibilities.
    - The element at mid index is greater than the one at the left index. This should mean that the subarray from left to mid index is sorted.
    - Otherwise, the subarray from mid to right index should be sorted.
    We enter the first condition.

    Line 15-19 :
    If the subarray from left to mid index is sorted, we check if the given target falls within this range.
    If yes, we will consider only this subarray now for our search further and we set the right index as (mid index - 1).
    If not, we will consider the other subarray and for that we set the left index as (mid index + 1).

    Line 20-26 :
    Going back to the previous conditions mentioned in the description of Line 14, if the element at the mid index is less than the one at the left index,
    the subarray from the mid index to the right is sorted.
    Now we check if the target falls within this range or not and do the same as we did above.

    Line 29 :
    After getting out of the loop, if no element is found, we return -1.
   */
