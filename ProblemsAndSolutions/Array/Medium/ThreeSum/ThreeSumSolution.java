package ProblemsAndSolutions.Array.Medium.ThreeSum;

import java.util.*;

public class ThreeSumSolution {

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int left;
        int right;
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) return result;
        for (int i = 0; i <= (nums.length - 3); i++) {
            if (i == 0 || (nums[i] != nums[i - 1])) {
                left = i + 1;
                right = (nums.length - 1);
                int target = -nums[i];
                while (left < right) {
                    if (nums[left] + nums[right] == target) {
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        while ((left != nums.length - 1) && nums[left] == nums[left + 1]) left++;
                        while ((right > 0) && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    } else if (nums[left] + nums[right] < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }

    // Test Cases

    public static void main(String[] args) {
        // 1. Basic input
        runTest(new int[]{-1, 0, 1, 2, -1, -4},
                Arrays.asList(Arrays.asList(-1, -1, 2), Arrays.asList(-1, 0, 1)),
                "Test 1 - Basic case");

        // 2. All zeros
        runTest(new int[]{0, 0, 0},
                Arrays.asList(Arrays.asList(0, 0, 0)),
                "Test 2 - All zeros");

        // 3. Not enough elements
        runTest(new int[]{0, 1},
                Collections.emptyList(),
                "Test 3 - Too few elements");

        // 4. No valid triplet
        runTest(new int[]{1, 2, -2, -1},
                Collections.emptyList(),
                "Test 4 - No valid triplet");

        // 5. Duplicates should not appear
        runTest(new int[]{-2, 0, 1, 1, 2},
                Arrays.asList(Arrays.asList(-2, 0, 2), Arrays.asList(-2, 1, 1)),
                "Test 5 - Handle duplicates");

        // 6. Edge negatives and positives
        runTest(new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6},
                Arrays.asList(
                        Arrays.asList(-4, -2, 6),
                        Arrays.asList(-4, 0, 4),
                        Arrays.asList(-4, 1, 3),
                        Arrays.asList(-4, 2, 2),
                        Arrays.asList(-2, -2, 4),
                        Arrays.asList(-2, 0, 2)
                ),
                "Test 6 - Complex combinations");

        // 7. Large input (performance test, no result check)
        int[] large = new int[3000];
        Arrays.fill(large, 0);
        runTest(large,
                Arrays.asList(Arrays.asList(0, 0, 0)),
                "Test 7 - Large zero array");

        System.out.println("\n🧪 Testing completed.");
    }

    public static boolean areTripletListsEqual(List<List<Integer>> a, List<List<Integer>> b) {
        Set<List<Integer>> setA = new HashSet<>();
        for (List<Integer> triplet : a) {
            List<Integer> sorted = new ArrayList<>(triplet);
            Collections.sort(sorted);
            setA.add(sorted);
        }

        Set<List<Integer>> setB = new HashSet<>();
        for (List<Integer> triplet : b) {
            List<Integer> sorted = new ArrayList<>(triplet);
            Collections.sort(sorted);
            setB.add(sorted);
        }

        return setA.equals(setB);
    }

    public static void runTest(int[] input, List<List<Integer>> expected, String testName) {
        List<List<Integer>> result = threeSum(input);
        if (areTripletListsEqual(result, expected)) {
            System.out.println(testName + ": ✅ PASSED");
        } else {
            System.out.println(testName + ": ❌ FAILED");
            System.out.println("  Input:     " + Arrays.toString(input));
            System.out.println("  Expected:  " + expected);
            System.out.println("  Got:       " + result);
        }
    }
}

/*
    Solution Description :
    ----------------------

    The three sum problem can only be solved with a time complexity of O(n^2). This can be treated as an extension of Two Sum.md problem.
    At first, we sort the array using the Arrays.sort method. This will give a time complexity of O(nlog(n)).
    Since now we have all the elements sorted in the array, we know as we go from left to right, the numbers will get bigger.

    Line 10-13 :
    Arrays are sorted and all the required variables are created including the left, right pointers and the result list of the indices triplets.

    Line 14 :
    We do an initial sanity check that the given array has more than three elements at least. If not, we return an empty list as the result.

    Now, the idea is to traverse through each of the element in the array and for each one them, we will have a while loop going on.
    Inside this loop, we set the left indicator and the right indicator in each iteration and check if these three numbers - the concerned element in the array,
    the one on the left pointer and the one on the right, add up to 0 or not.

    Line 15 :
    We start the for loop. Notice that this loop only goes till the third last element of the array. This is because we need to look out for triplets and
    it would not make any sense to look into a subarray where the number of elements is less than 3.

    Line 16 :
    Inside this for loop, we are only going to allow the first element and the element which is not repeated since we do not want duplicate triplets.
    So, if an element is similar to the previous one, we skip it.

    Line 17-19 :
    We set the left pointer on the index just right to the current element of the for loop.
    We set the right pointer on the last index of the array.
    Now, we know that the current element of the for loop, the element on the left pointer and the element on the right pointer should all add up to 0 to form the desired triplet.
    Which should mean, if we add up the element of the left and right pointer, we get the negative of the current element of the for loop.
    Therefore, we set our target as the negative of the current element of the for loop.
    This is what we need to look out for as the sum of the left and right pointer elements.

    Line 20 :
    Now, we start a while loop which goes on as long as the right pointer is greater than the left pointer.

    Line 21-22 :
    We check if the sum of the left pointer element and the right pointer element is equal to the target.
    If it is, we add the triplet of the current element of the for loop, the left pointer element and the right pointer element, to the result list.

    Line 23-26 :
    Now that we have one of our triplets, we discard these left pointer element and the right pointer element and move our pointers to different indices.
    In a while loop, we check if the element in the next index of the left pointer is similar to the current one.
    If it is, we increment our left pointer until it reaches the last index of the similar element.
    We do the same for the right pointer. If the immediate left element of the right pointer element is similar,
    we decrement the right pointer until it reaches the first index of the similar element.
    Now that we have taken care of the similar elements, we move increment the left pointer just once and move it to its immediately right index
    and do the same for the right pointer, decrementing it just once and moving it to its immediately left index.

    Line 27-31 :
    Apart from the case where the left pointer element and the right pointer element add up exactly to the target, we might have two more cases.
    If the sum of left and right is less than the target, it means we need to increase the elements to be summed up.
    This can only be done if we move the left pointer more towards right. So we increment it once.
    In case the sum is greater than the target, we need to decrease the elements to be summed up.
    For that, we move the right pointer more towards left and so we decrement it once.

    Line 35 :
    Finally, we return the result list.


 */


