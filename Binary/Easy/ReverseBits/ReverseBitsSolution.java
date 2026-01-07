package Binary.Easy.ReverseBits;

public class ReverseBitsSolution {

    // you need treat n as an unsigned value
    public static int reverseBits(int n) {
        int reverse = 0;
        for (int i=1;i<=32;i++) {
            reverse <<= 1;
            reverse += (1 & n);
            n >>= 1;
        }
        return reverse;
    }

    // Test Cases

    public static void main(String[] args) {
        // 1. LeetCode example
        runTest(0b00000010100101000001111010011100, 0b00111001011110000010100101000000, "Test 1 - LeetCode input 1");

        // 2. LeetCode example with trailing 0
        runTest(0b11111111111111111111111111111101, 0b10111111111111111111111111111111, "Test 2 - LeetCode input 2");

        // 3. All zeros
        runTest(0b00000000000000000000000000000000, 0b00000000000000000000000000000000, "Test 3 - All zeros");

        // 4. All ones
        runTest(0b11111111111111111111111111111111, 0b11111111111111111111111111111111, "Test 4 - All ones");

        // 5. Only MSB set
        runTest(0b10000000000000000000000000000000, 0b00000000000000000000000000000001, "Test 5 - Only MSB set");

        // 6. Only LSB set
        runTest(0b00000000000000000000000000000001, 0b10000000000000000000000000000000, "Test 6 - Only LSB set");

        // 7. Palindromic bits (same after reverse)
        runTest(0b10000000000000000000000000000001, 0b10000000000000000000000000000001, "Test 7 - Palindromic bits");

        // 8. Alternating bits
        runTest(0b10101010101010101010101010101010, 0b01010101010101010101010101010101, "Test 8 - Alternating bits");

        // 9. Half bits set
        runTest(0b00000000000000001111111111111111, 0b11111111111111110000000000000000, "Test 9 - Half-set low bits");

        // 10. Random pattern
        runTest(0xF0F0F0F0, 0x0F0F0F0F, "Test 10 - Random alternating nibbles");

        System.out.println("\n🧪 Testing completed.");
    }

    public static void runTest(int input, int expected, String testName) {
        int result = reverseBits(input);
        if (result == expected) {
            System.out.println(testName + ": ✅ PASSED");
        } else {
            System.out.printf("%s: ❌ FAILED (Expected: %s, Got: %s)\n", testName, Integer.toUnsignedString(expected), Integer.toUnsignedString(result));
        }
    }

}

/*

    Solution Description :
    ----------------------

    This problem requires the use of Binary operators for solution.
    The concept is to reverse the bits of a number and we will get our answer.
    However, we need to understand that the number of total bits stored for an integer value is 32.
    So, reversing a number would mean suffixing all the zeroes to our reversed number which were prefixed in the input number.
    For example, let's take the number 53. The binary representation of 53 would be 110101.
    It looks like reversing this number would give us 101011 which is 43.
    This is not the case.
    We need to look 53 as 00000000000000000000000000110101. Since, an integer value is stored in a 32-bit.
    So, when reversed, it would give us 10101100000000000000000000000000, which is 2885681152.

    In Java, we have a 2's compliment signed convention but we can ignore that while working on the solution.

    Line 7 :
    We define our return variable as reverse and set it to 0.

    Line 8 :
    We start the loop with i set to 1 till 32, to cover all the bit spaces.

    Line 9 :
    We apply left shift on the number, moving each digit one place to the left.
    For all the zeroes until we find a 1, it would have no effect.
    So, starting the reverse value with 0 and then shifting it to the left in the beginning of the first iteration will still keep the value as 0.
    This would start making a difference when we get the first 1 of our given number from the left.

    Line 10 :
    The AND (&) operator when applied with 1 gives us the same digit.
    => 1 & 1 = 1
    => 1 & 0 = 0
    When applied with 0, it returns 0 no matter what the digit is.
    => 1 & 0 = 0
    => 0 & 0 = 0
    This can be used to get the left most digit of the given binary number.
    For example, 110101 & 1 would give us 1.
    110100 & 1 would give 0.
    See that we get the same as the digit on the left most place of the number.
    In our code, apply AND (&) on the number with 1. This will give us the digit (1 or 0) at the 1st place from the left of the given number.
    Now, this number needs to be added with the reverse which we have got.
    If the reverse is 0, we do not need to worry.
    If the reverse was 1, it will get left shifted at line 9 and become 10.
    Now, whatever we get from (1 & n), we need to add it to the reverse.
    If (1 & n) gives 0, we get the (10 + 0 =) 10 as the new reverse. Else, we get (10 + 1 =) 11.
    However, addition will be an arithmetic operation. This can be done using the OR (|) binary operator.
    => 1 | 1 = 1
    => 1 | 0 = 1
    => 0 | 1 = 1
    => 0 | 0 = 0
    So, (10 | 0) will give 10 and (10 | 1) will give us 11.

    Line 11 :
    Finally we right shift our given number by 1.
    This will move the second digit from the left of our given number to the first place from the left
    and thus, this will be considered in the next iteration.

    So, to look at it, we are reducing the given number from the left by 1 digit in each rotation
    and increasing the new number by prefixing this removed digit on the left of the new one.

    For example, let's consider a binary number with 4 places for the ease of understanding.
    We have a given number, 1101 (which is 13) and we have to return the reverse of that, 1011 (which is 11).
    Here's how the iteration will go (given we consider 4 places, instead of 32) :

    Iteration       n           reverse     reverse <<= 1      (1 & n)     reverse += (1 & n)     n >>= 1
    ---------       -----       -------     -------------      -------     -------------------    -------
    i = 1           1101        0           0                  1           1                      110
    i = 2            110        1           10                 0           10                     11
    i = 3             11        10          100                1           101                    1
    i = 4              1        101         1010               1           1011                   0

    Final result (reverse) = 1011.

*/
