package Binary.Easy.CountingBits;

import java.util.Arrays;

public class CountingBitsSolution {

    public static int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }

    public static int[] countBits2(int n) {
        int[] countArray = new int[n + 1];
        countArray[0] = 0;
        int offset = 1;
        for (int i = 1; i <= n; i++) {
            if (i == offset * 2) {
                offset = i;
            }
            countArray[i] = 1 + countArray[i - offset];
        }
        return countArray;
    }

    public static int[] countBitsRecursive(int n) {
        int[] arr= new int[n+1];
        helper(arr, 1, 1);
        return arr;
    }


    // Test Cases

    public static void main(String[] args) {
        // 1. Smallest input
        runTest(0, new int[]{0}, "Test 1 - n = 0");

        // 2. Small input
        runTest(2, new int[]{0, 1, 1}, "Test 2 - n = 2");

        // 3. Power of two
        runTest(4, new int[]{0, 1, 1, 2, 1}, "Test 3 - n = 4");

        // 4. Non-power of two
        runTest(5, new int[]{0, 1, 1, 2, 1, 2}, "Test 4 - n = 5");

        // 5. Binary pattern switch
        runTest(7, new int[]{0, 1, 1, 2, 1, 2, 2, 3}, "Test 5 - n = 7");

        // 6. Edge of common test range
        runTest(15, new int[]{
                0, 1, 1, 2, 1, 2, 2, 3,
                1, 2, 2, 3, 2, 3, 3, 4
        }, "Test 6 - n = 15");

        // 7. Larger input, sanity check on length
        int[] res = countBits(100000);
        boolean correctLength = res.length == 100001;
        System.out.println("Test 7 - n = 100000 (length check): " + (correctLength ? "✅ PASSED" : "❌ FAILED"));

        System.out.println("\n🧪 Testing completed.");
    }

    private static void helper(int[] arr, int index, int num){
        if(index >= arr.length){
            return;
        }
        arr[index] = num;
        helper(arr, index*2, num);
        helper(arr, index*2+1, num+1);
    }

    public static void runTest(int n, int[] expected, String testName) {
        int[] result = countBits(n);
        if (Arrays.equals(result, expected)) {
            System.out.println(testName + ": ✅ PASSED");
        } else {
            System.out.println(testName + ": ❌ FAILED");
            System.out.println("  Input:    n = " + n);
            System.out.println("  Expected: " + Arrays.toString(expected));
            System.out.println("  Got:      " + Arrays.toString(result));
        }
    }

}

/*

    Solution Description :
    ----------------------

    There are multiple solutions to this problem which gives us O(n) time complexity.
    Let's look at the first two which are examples of Dynamic Programming.

    Solution 1 :

    This approach is the simplest one. In order to understand this, we need to know few basic things about binary operators.
        1. n >> 1 : This essentially means dividing the number n by 2.
                    When the bits are shifted right once, the new number is the half of the previous one.
                    In case of even numbers, it is just the half and in case of odd numbers, it is the nearest lower integer.
                    Ex.,(6 >> 1) = ((110) >> 1) = (011) = 3
                        (7 >> 1) = ((111) >> 1) = (011) = 3
        2. n << 1 : Similar to the above, this essentially means multiplying the number n by 2.
                    When the bits are shifted once to the left, the new number is the double of the previous one.
                    Ex.,(6 << 1) = ((110) << 1) = (1100) = 12
                        (7 << 1) = ((111) << 1) = (1110) = 14
        3. n & 1 :  This is essentially a method to check if the number is odd or even.
                    When a number is put to an & operator with 1, it gives 1 in case of an odd number, and 0 in case of an even number.
                    For an odd number we know, that the right most bit will be 1. So, 1 & 1 will give 1.
                    For an even number, the right most bit is 0. So, 0 & 1 gives us 0.
                    Ex.,(6 & 1) = ((110) & 001) = (000) = 0
                        (7 & 1) = ((111) & 001) = (001) = 1
    Now that we know these basic interpretations of bitwise operators >>, << and &, let's understand our problem.
    An even number n will always have equal number of 1s in its binary representation as the half of n.
    This is in the binary representation of an even number, we will always have 0 at the right most place.
    So even if the number is left shifted (>>) once, we do not lose any 1s and the number of 1s remain the same.
    For an odd number, the number of 1s in its binary representation is just one more than the number of 1s in the binary representation of (n-1).
    An odd number will always have 1 in its right most place, which is lost when we left shift once.
    So, the number of 1s in an odd number is just 1 more than the number of 1s in its previous even number.

    Putting this theory together, we can calculate the number of 1s of any given number using the already derived data of the half of that number.
    So, to get the number of 1s in a number n, we first need to find the number of 1s in (n/2), which we get by (n >> 1 )
    Then, if n is even or odd, we add 0 or 1 to it respectively which can be done by adding just (n & 1). It will add 0 or 1 based on if n is even or odd.
    This is the step which we need to do for any number i between 0 and n.
    countOf1(i) = countOf1(i >> 1) + (i & 1)

    Line 8 :
    We define our ans array which will have the answer. This will have a size 1 more than the given number n, since we are also storing the number of 1s in 0.

    Line 9 :
    We start the iteration from 1 to n.
    We do not need to do the iteration for i=0, since the int array has a default value of 0 set in each place
    and we know the number of 1s in the number 0 is 0.

    Line 10 :
    We compute the number of 1s for any number 1 using the formula discussed above and store it in ans[i].

    Line 11 :
    We return our result array ans.


    Solution 2:

    We know that every number which can be represented as a power of 2, has a single 1 in its binary representation.
    For example,
    1 = 2^0 = 1
    2 = 2^1 = 10
    4 = 2^2 = 100
    8 = 2^3 = 1000
    and so on.
    Suppose while iterating, we reach a number n which is similar to the numbers above, i.e, n can be represented as 2^k where k is any positive integer.
    Now we know n is going to have a single 1 in its binary representation and this 1 is going to be at the right most place.
    If we look closely to the pattern, n+m (where m is any integer < n) will have 1 in the similar place as n,
    and along with that, for the remaining places, it will have the same number of 1s as m.

    Let's take a look at an example where the given number is 9. So, we need to find the number of 1s in the binary representations of numbers from 0 to 9.
    0 = 0       => 0
    1 = 1       => 1
    2 = 10      => 1
    3 = 11      => 2 ------------> m
    4 = 100     => 1 ------> n     |
    5 = 101     => 2               |----------> (n + m) - n
    6 = 110     => 2               |
    7 = 111     => 3 ------------> (n + m)
    8 = 1000    => 1
    9 = 1001    => 2
    In this example, if we take n as 4 (since 4 = 2^2), we see 7 is (4 + 3).
    So, in case of 7, m is 3.
    Look at the binary representation of 3. It is 11. So, number of 1s in the binary representation of 3 is 2.
    Now, in case of 7, if look at the places except the right most one, it is similar to what we have in 3.

    This pattern is similar to any number you take.
    Suppose you take 53.
    Binary representation of 53 is 110101.
    Now, 53 can be represented as (32 + 21). Since, 32 can be represented as 2^5, we know it will have a single 1 in its binary representation.
    Binary representation of 21 is 10101. This is similar to the remaining places of the binary form of 53 except the right most one.
    => 32 + 21  100000 + 10101      No. of 1s => (1 + 3)
    => 53       110101              No. of 1s => (4)

    This whole thing means if we have already calculated the number of 1s of the previous numbers, we can use that to calculate the same for the next numbers.
    This is dynamic programming.

    In this problem, whenever we reach a number which can be represented as a power of 2, we store it as offset.
    For the remaining numbers till we reach our next offset, we will just get the number of ones we had for (current number - offset) and add 1 to that.

    Line 16 :
    We define our countArray which will store the number of 1s for all the numbers from 0 to n
    So, it will have a size of (n + 1).

    Line 17 :
    Since we know that for 0, there are no 1s in the binary representation.
    So, we set the number of 1s as 0 for 0.

    Line 18 :
    We set our offset as 1 since this is the smallest number which can be represented as a power of 2 => 2^0.

    Line 19 :
    We start the loop from 1 to n.

    Line 20-22 :
    We set a check to verify if the new number qualifies to be the new offset.
    Offsets are always going to be a power of 2.
    Such as 1,2,4,8,16,32 and so on. This means, any offset is just the double of the previous one.
    So, if the new number is the double of the previous one, it qualifies to be the new offset.

    Line 23 :
    We set the number of 1s for the current number to 1 more than the number of 1s as the (current number - offset).

 */
