package RoughWork;

import java.util.*;

public class Practice {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> intMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (intMap.containsKey(target - nums[i])) {
                return new int[]{intMap.get(target - nums[i]), i};
            }
            intMap.put(nums[i], i);
        }

        return new int[]{};
    }

    public static int bestTimeToBuyAndSellStocks(int[] prices) {
        int minPrice = 0;
        int maxProfit = 0;
        for (int price : prices) {
            minPrice = Math.min(price, minPrice);
            maxProfit = Math.max((price - minPrice), maxProfit);
        }
        return maxProfit;
    }

    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> intSet = new HashSet<>();
        for (int num : nums) {
            if (intSet.contains(num)) return true;
            intSet.add(num);
        }
        return false;
    }

    public static int longestSubstringWithoutRepeatingCharacters(String s) {
        int left = 0, maxLength = 0;
        Set<Character> charSet = new HashSet<>();
        for (int right = 0; right < s.length(); right++) {
            while (charSet.contains(s.charAt(right))) {
                charSet.remove(s.charAt(left));
                left++;
            }
            charSet.add(s.charAt(right));
            maxLength = Math.max(maxLength, charSet.size());
        }
        return maxLength;
    }

    public static int maximumSubarray(int[] nums) {
        int currSum = nums[0], maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currSum = Math.max(currSum + nums[i], nums[i]);
            maxSum = Math.max(currSum, maxSum);
        }
        return maxSum;
    }

    public static int maximumProductSubarray(int[] nums) {
        //2,3,-2,4
        int currMax = nums[0], currMin = nums[0], tempMax, allMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                currMax = 1;
                currMin = 1;
                allMax = Math.max(allMax, 0);
            }
            tempMax = Math.max(nums[i], Math.max((currMax * nums[i]), (currMin * nums[i])));
            currMin = Math.min(nums[i], Math.min((currMax * nums[i]), (currMin * nums[i])));
            currMax = tempMax;
            allMax = Math.max(currMax, allMax);
        }
        return allMax;
    }

    public static int[] productOfArrayExceptSelf(int[] nums) {
        int[] productPrefix = new int[nums.length];
        productPrefix[0] = 1;
        int productSuffix = 1;
        for (int i = 1; i < nums.length; i++) {
            productPrefix[i] = productPrefix[i - 1] * nums[i - 1];
        }
        for (int j = nums.length - 1; j >= 0; j--) {
            productPrefix[j] = productPrefix[j] * productSuffix;
            productSuffix = productSuffix * nums[j];
        }
        return productPrefix;
    }

    public static int findMinimumInRotatedSortedArray(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.min(nums[0], nums[1]);

        int left = 0, right = nums.length - 1, mid = 0;
        if (nums[0] <= nums[nums.length - 1]) {
            return nums[0];
        }
        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid - 1] > nums[mid]) return nums[mid];
            if (nums[mid + 1] < nums[mid]) return nums[mid + 1];
            if (nums[left] > nums[mid]) right = mid - 1;
            if (nums[right] < nums[mid]) left = mid + 1;
        }
        return nums[mid];
    }

    public static int searchInRotatedSortedArray(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        if (nums.length == 1 && target == nums[0]) return nums[0];
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] >= nums[left]) {
                if (target < nums[mid] && target >= nums[left]) {
                    right = mid - 1;
                } else left = mid + 1;
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else right = mid - 1;
            }
        }
        return -1;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        //[-1, 0, 1, 2, -1, -4]
        //[-4, -1, -1, 0, 1, 2]
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int left, right;
        for (int i = 0; i <= nums.length - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            left = i + 1;
            right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while ((left != nums.length - 1) && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while ((right > 0) && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                }
                if (sum < 0) left++;
                if (sum > 0) right--;
            }

        }
        return result;
    }

    public static int containerWithMostWater(int[] nums) {
        //[1,8,6,2,5,4,8,3,7]
        int maxArea = 0, left = 0, right = nums.length - 1;
        while (left < right) {
            maxArea = Math.max(maxArea, (Math.min(nums[left], nums[right]) * (right - left)));
            if (nums[left] < nums[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    public static int sumOfTwoIntegers(int a, int b){
        int carry;
        while (b != 0){
            carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }

    public static int numberOf1Bits(int n){
        int count=0;
        while(n != 0){
            n = n & (n-1);
            count++;
        }
        return count;
    }

    public static int[] countingBits(int n){
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }

    public static int missingNumber(int[] nums){
        int result = 0;
        for(int i=1; i<=nums.length; i++){
            result ^= i ^ nums[i-1];
        }
        return result;
    }

    public static int reverseBits(int n) {
        int reverse = 0;
        for (int i=1;i<=32;i++) {
            reverse <<= 1;
            reverse += (1 & n);
            n >>= 1;
        }
        return reverse;
    }

    public static int climbingStairs(int n){
        if(n==0 || n==1) return 1;
        return climbingStairs(n-1) + climbingStairs(n-2);
    }


}
