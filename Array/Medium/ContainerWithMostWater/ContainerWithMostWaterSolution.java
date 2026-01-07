package Array.Medium.ContainerWithMostWater;

public class ContainerWithMostWaterSolution {

    public static int maxArea(int[] height) {
        int result = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            result = Math.max((right - left) * Math.min(height[left], height[right]), result);
            if(height[left] < height[right]){
                left++;
            } else {
                right--;
            }
        }
        return result;
    }

    // Test Cases

    public static void main(String[] args) {
        // 1. Basic example (LeetCode classic)
        runTest(new int[]{1,8,6,2,5,4,8,3,7}, 49, "Test 1 - Classic case");

        // 2. Minimal array size
        runTest(new int[]{1,1}, 1, "Test 2 - Two bars only");

        // 3. Increasing height
        runTest(new int[]{1,2,3,4,5}, 6, "Test 3 - Increasing height");

        // 4. Decreasing height
        runTest(new int[]{5,4,3,2,1}, 6, "Test 4 - Decreasing height");

        // 5. All heights equal
        runTest(new int[]{3,3,3,3,3}, 12, "Test 5 - All equal heights");

        // 6. Height with 0s
        runTest(new int[]{0,0,0,0,10,0,0}, 0, "Test 6 - Mostly zeros");

        // 7. Very large numbers
        runTest(new int[]{10000,10000}, 10000, "Test 7 - Max height values");

        // 8. Max width, small height
        runTest(new int[]{1,0,0,0,0,0,0,0,0,1}, 9, "Test 8 - Max width, min height");

        // 9. Complex pattern
        runTest(new int[]{2,3,10,5,7,8,9}, 36, "Test 9 - Complex pattern");

        // 10. Large input array
        int[] large = new int[100000];
        for (int i = 0; i < large.length; i++) {
            large[i] = 10000;
        }
        runTest(large, (large.length - 1) * 10000, "Test 10 - Large uniform array");

        System.out.println("\n🧪 Testing completed.");
    }

    public static void runTest(int[] input, int expected, String testName) {
        int result = maxArea(input);
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

    Consider this as a graph with indices on the x-axis and the array element values on the y-  .
    We need to find the maximum possible area on this graph which will essentially represent the maximum volume of water that can be contained.

           ^
       10 _|
       9  _|
       8  _|        8                   8
       7  _|        |___________________|_______7
       6  _|        |   6               |       |
       5  _|        |   |       5       |       |
       4  _|        |   |       |   4   |       |
       3  _|        |   |       |   |   |   3   |
       2  _|        |   |   2   |   |   |   |   |
       1  _|    1   |   |   |   |   |   |   |   |
           |____|___|___|___|___|___|___|___|___|___________ >
                0   1   2   3   4   5   6   7   8   9   10
                    L                           R

    Line 6-8 :
    This is a two-pointer problem. We set the result as 0.
    We set the left pointer at index 0 and the right one at the last index.

    Line 9 :
    We start the loop and it goes till the left pointer is smaller than the right one.

    Line 10 :
    We check the length of the initial area first. This can be calculated by subtracting the left pointer from the right one.
    We got the length, now let's find the height.
    This is equal to the minimum of the values at these left and right pointer.
    For example, in the above graph, if the left pointer is at index 1 and the right pointer is at index 8, then
    length = 8 - 1 = 7
    height = minimum of (array[1], array[8]) = 7
    Area = 7 * 7 = 49
    We store the maximum result between the area obtained in the current iteration and the previous ones.

    Line 11-15 :
    If the value of the element at the left pointer is smaller than the right one, we increment the left pointer by 1, i.e., move the left pointer to the immediate right index.
    Else, we decrement the right pointer, i.e., move it to the immediate left index.

    Line 17 :
    We return the result which holds the maximum area obtained from all the iterations.

 */
