package Binary.Easy.NumberOf1Bits;

public class NumberOf1BitsSolution {

    public static int hammingWeight(int n) {
        int count = 0;
        for(int i =0; i < 32; i++){
            int mask = 1 << i;
            if ((mask & n) != 0) count++;
        }
        return count;
    }

    public static int brianKernighan(int n){
        int count=0;
        while(n != 0){
            n = n & (n-1);
            count++;
        }
        return count;
    }

    // Test Cases

    public static void main(String[] args) {
        // 1. Example from LeetCode
        runTest(0b00000000000000000000000000001011, 3, "Test 1 - 3 ones");

        // 2. Single bit set
        runTest(0b00000000000000000000000010000000, 1, "Test 2 - 1 one");

        // 3. All bits set except one
        runTest(0b11111111111111111111111111111101, 31, "Test 3 - 31 ones");

        // 4. Zero input
        runTest(0b00000000000000000000000000000000, 0, "Test 4 - All zeros");

        // 5. All bits set
        runTest(0xFFFFFFFF, 32, "Test 5 - All 32 ones");

        // 6. Alternating 1s and 0s: 0101...
        runTest(0x55555555, 16, "Test 6 - 16 alternating ones (0101...)");

        // 7. Alternating 1s and 0s: 1010...
        runTest(0xAAAAAAAA, 16, "Test 7 - 16 alternating ones (1010...)");

        // 8. Single most significant bit
        runTest(0x80000000, 1, "Test 8 - MSB only");

        // 9. Single least significant bit
        runTest(0x00000001, 1, "Test 9 - LSB only");

        // 10. Random pattern
        runTest(0xF0F0F0F0, 16, "Test 10 - Random alternating nibbles");

        System.out.println("\n🧪 Testing completed.");
    }

    public static void runTest(int n, int expected, String testName) {
        int result = brianKernighan(n);
        int result2 = hammingWeight(n);
        if (result == expected && result2 == expected) {
            System.out.println(testName + ": ✅ PASSED");
        } else {
            System.out.println(testName + ": ❌ FAILED (Expected " + expected + ", but got " + result + ")");
        }
    }

}

/*

    Solution Description :
    ----------------------

    // Hamming Weight

    The problem statement mentions that we need to find the number of 1s in the binary representation of the given number.
    In order to solve this problem, we need to think which binary operator can be useful for us.
    Let's look at the AND (&) operator and how it behaves :
    0 & 0 = 0
    1 & 0 = 0
    0 & 1 = 0
    1 & 1 = 1
    We see that in case of 1 & 1, we get a 1 as the result.
    This logic can be leveraged in order to solve this problem.

    For example, let's tak a number, say 53.
    An int value has a size of 32 bit, so the binary representation would be 00000000000000000000000000110101.
    Looking from the left, we need to traverse to the last place on the right and count each 1 we find.
    Let's start with the last most place. If we take number 1, which is 00000000000000000000000000110101, and we use the AND (&) operator :
    => 53 & 1
    => 00000000000000000000000000110101 & 00000000000000000000000000000001
    => 1
    The left most place of both the numbers has one, and so we get a 1.
    If the answer had been a 0, we would have understood that the number holds a 0 in its left most place.
    Now, let's talk about the second most left place of the number. We can repeat the same process to check whether there is any 1 at that place.
    But for this, we need to use 10 instead of 1. We can do that by left shifting our previous number, 1 << 1 = 10.

    The pattern should be visible by now. We keep on left shifting our number, operate it with & on the given number and count if the value comes 1.
    We return the final count as our answer.

    Line 6 :
    Initialize the count with 0.

    Line 7 :
    Start the for loop. The iteration must be less than 32, since that is the bit size of any integer.

    Line 8-9 :
    We define our number and initialize with 1 left shifted by the number of the iteration.
    So, for first iteration, it will be 1 << 0 = 1.
    Second, 1 << 1 = 10. Third, 1 << 2 = 100. And so on.
    Next, we operate the given number with this number using & and if the answer is a non-zero (which is 1), we increment our count.

    Line 11 :
    We return our count as the answer.


    // Brian Kernighan

    This method is better optimized than the one above since the number of iteration is not 32 always. The iteration only occurs k number of times,
    where k is the number of set bits.
    The idea behind this algorithm is when you subtract 1 from a given number, the first 1 from the right most end in the binary representation along
    with all the zeroes to its right will flip and the remaining bits on the left remains the same. Both the number
    and the number after subtracting 1 from it, when put under & operator, cancel out the 1 from the right most end of the number,
    and this becomes our new number for the next iteration.
    111 - 001 = 110 -> The first occurrence of 1 from right gets flipped to 0. So, 1 becomes 0.
    110 - 001 = 101 -> The first occurrence of 1 from right along with the following 0, gets flipped and 10 becomes 01.
    100 - 001 = 011 -> Similarly, 100 becomes 011 here.

    To understand the algorithm further, let's take an example of a number 52.
    In binary form, 52 is represented as 110100.
    Let's look at the process of each iteration of this algorithm
    and count how many iterations does it take until the number is converted to 0 through the repeated steps.

    i=1 : ((110100) & (110100 - 1)) = (110100 & 110011) = 110000
    i=2 : ((110000) & (110000 - 1)) = (110000 & 100001) = 100000
    i=3 : ((100000) & (100000 - 1)) = (100000 & 011111) = 000000

    We see that in each iteration, we are essentially removing a single 1 from the original number.
    Hence, the number of iterations is equal to the number of 1 bits in the binary representation of the number.

    Line 15 :
    We define and initialize the count with 0.

    Line 16 :
    We start the while loop and make it run until the given number becomes 0.

    Line 17 :
    We set the new number for the next iteration as the result of & operator between the number and the number subtracted by 1.

    Line 18 :
    We increment the count in each iteration.

    Line 20 :
    Finally, we return the number of counts as the answer.

 */
