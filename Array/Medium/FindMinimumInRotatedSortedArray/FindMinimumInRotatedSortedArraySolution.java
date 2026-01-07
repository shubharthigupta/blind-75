package Array.Medium.FindMinimumInRotatedSortedArray;

public class FindMinimumInRotatedSortedArraySolution {

    public static int findMin(int[] nums) {

        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.min(nums[0], nums[1]);

        int result = nums[0];
        int left = 0;
        int right = nums.length - 1;

        while(left <= right){
            if(nums[left] <= nums[right]){
                result = Math.min(result, nums[left]);
                break;
            }

            int mid = left + ((right - left) / 2);

            if(nums[mid] > nums[mid + 1]) return nums[mid + 1];
            if(nums[mid] < nums[mid - 1]) return nums[mid];

            if(nums[mid] >= nums[left]) left = mid + 1;
            else right = mid - 1;
        }
        return result;
    }

    // Test Cases

    public static void main(String[] args) {
        // 1. Classic rotated array
        runTest(new int[]{3, 4, 5, 1, 2}, 1, "Test 1 - Classic rotation");

        // 2. No rotation (already sorted)
        runTest(new int[]{1, 2, 3, 4, 5}, 1, "Test 2 - No rotation");

        // 3. Minimum at the end
        runTest(new int[]{2, 3, 4, 5, 1}, 1, "Test 3 - Min at the end");

        // 4. Two elements, rotated
        runTest(new int[]{2, 1}, 1, "Test 4 - Two elements rotated");

        // 5. Two elements, not rotated
        runTest(new int[]{1, 2}, 1, "Test 5 - Two elements sorted");

        // 6. One element
        runTest(new int[]{10}, 10, "Test 6 - Single element");

        // 7. Rotated by one (min at index 1)
        runTest(new int[]{5, 1, 2, 3, 4}, 1, "Test 7 - Rotated by one");

        // 8. Large rotation (min in middle)
        runTest(new int[]{6, 7, 8, 9, 1, 2, 3, 4, 5}, 1, "Test 8 - Large rotation");

        // 9. Already sorted, but trick check
        runTest(new int[]{0, 1, 2, 4, 5, 6, 7}, 0, "Test 9 - Sorted ascending");

        // 10. Large input test
        int[] large = new int[10000];
        for (int i = 0; i < 10000; i++) {
            large[i] = i + 1;
        }
        // Rotate at 6000
        int[] rotatedLarge = new int[10000];
        System.arraycopy(large, 6000, rotatedLarge, 0, 4000);
        System.arraycopy(large, 0, rotatedLarge, 4000, 6000);
        runTest(rotatedLarge, 1, "Test 10 - Large rotated array");

        System.out.println("\n🧪 Testing completed.");
    }


    public static void runTest(int[] input, int expected, String testName) {
        int result = findMin(input);
        if (result == expected) {
            System.out.println(testName + ": ✅ PASSED");
        } else {
            System.out.println(testName + ": ❌ FAILED (Expected " + expected + ", but got " + result + ")");
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
    Here, we need to do a modified binary search.
    We keep splitting the array into two parts. We discard the sorted sub-array and take the other non-sorted sub-array for next consideration.

    Line 7-8 :
    In case of an array with a single element, the minimum value will be the element itself.
    In case of a two-element array, we can just return the minimum of these two values.
    Having a separate if statement for these two cases will give us a quicker result with shorter processing time.

    Line 10-12 :
    We set the default value to our result as the first element of the array.
    We also define two integer variables which indicate the left and right index of the concerned sub-array.
    Since initially, the whole array is under consideration, we set the left index as 0 and the right index as the (array length - 1).

    Line 14 :
    We start a loop and it will continue as long as the left index is smaller or equal to the right index.

    Line 15-18 :
    We check if the array is sorted or not.
    If it is, we return the minimum between the previous result value and the element on the left most index of the array.

    Line 20 :
    We set the mid index of the array.
    Generally, the formula for that is (left+right)/2.
    However, this could lead to overflow error. Suppose we have right as Integer.MAX_VALUE.
    Then having (left+right) would give an overflow value and the computation will yield wrong result.
    So, we modify this formula as (left + (right-left)/2).
    Mathematically, it means the same thing. However, it prevents any overflow error in Java programming.

    Line 22-26 :
    After calculating the mid index, we might have the following cases :
    1. We have the pivot on the mid index or the immediate right of the mid index.
        In case the pivot is on the immediate right, we check if nums[mid] > nums[mid+1] returns true, and we set the result as nums[mid+1]
        For example, in case of {...,5,6,0,...}, we find the mid index as the index of 6.
        We check if nums[mid] > nums[mid+1] returns true, which in this case, it does.
        This would obviously mean that the sorted sub-array starts immediately from the right of the mid index
        and thus, value of index [mid+1] will be our result.
        Similarly, in case the pivot is on the mid index itself, we check if nums[mid] < nums[mid-1] returns true, and we set the result as nums[mid]
        For example, in case of {...,6,0,1,...}, we find the mid index as the index of 0.
        We check if nums[mid] < nums[mid-1] returns true, which in this case, it does.
        This would obviously mean that the sorted sub-array starts from the mid index itself
        and thus, value of index [mid] will be our result.
    2. In case the pivot is not immediately near the mid index, we check two case to determine the sorted sub-array on either side of the mid index.
          If the value of nums[mid] is greater than the value of nums[left],
          this should mean that the sub-array starting from left index to mid index is sorted and does not contain the pivot.
          So, we discard this sorted sub-array and consider the sub-array starting from the immediate right of the mid index for the next iteration.
          For that, we set the new left index as (mid + 1) and the right index remains the same.
          Similarly, if the value of nums[mid] is smaller than the value of nums[left],
          this should mean that the sub-array starting from left index to mid index is not sorted and does contain the pivot.
          So, we discard the sorted sub-array on the right
          and consider the sub-array starting from the left most index till the (mid - 1) index for the next iteration.
          For that, we set the new right index as (mid - 1) and the left index remains the same.
     The iteration continues and we finally get the result.

     */


}
