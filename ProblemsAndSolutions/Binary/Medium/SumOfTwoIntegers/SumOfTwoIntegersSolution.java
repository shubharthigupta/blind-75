package ProblemsAndSolutions.Binary.Medium.SumOfTwoIntegers;

public class SumOfTwoIntegersSolution {

    public static int getSum(int a, int b) {
        while (b != 0) {
            int carry = a & b;
            a ^= b;
            b = carry << 1;
        }
        return a;
    }

    // Test Cases

    public static void main(String[] args) {
        // 1. Positive numbers
        runTest(1, 2, 3, "Test 1 - Positive numbers");

        // 2. One positive, one negative (cancelling out)
        runTest(-1, 1, 0, "Test 2 - Negative and positive cancel");

        // 3. Both negative
        runTest(-2, -3, -5, "Test 3 - Both negative");

        // 4. Large positive numbers
        runTest(1000, 2000, 3000, "Test 4 - Large positives");

        // 5. Large negative numbers
        runTest(-1000, -2000, -3000, "Test 5 - Large negatives");

        // 6. One zero
        runTest(0, 5, 5, "Test 6 - One zero (a=0)");
        runTest(7, 0, 7, "Test 7 - One zero (b=0)");

        // 7. Zero and zero
        runTest(0, 0, 0, "Test 8 - Both zero");

        // 8. Max and min integer values
        runTest(Integer.MAX_VALUE, 0, Integer.MAX_VALUE, "Test 9 - Max int and 0");
        runTest(Integer.MIN_VALUE, 0, Integer.MIN_VALUE, "Test 10 - Min int and 0");

        // 9. Max + Min (should be -1)
        runTest(Integer.MAX_VALUE, Integer.MIN_VALUE, -1, "Test 11 - Max + Min");

        // 10. Cross-boundary sum
        runTest(2147483640, 5, 2147483645, "Test 12 - Near max int");

        System.out.println("\n🧪 Testing completed.");
    }

    public static void runTest(int a, int b, int expected, String testName) {
        int result = getSum(a, b);
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

    This problem instructs to not use any arithmetic operations to solve.
    In such case, we need to use binary operations to find the sum of the given integers.
    In order to do that we need to understand binary representation of the numbers and binary operators.
    Refer to Notes below to get a brief understanding.

    Let's understand the code with an example.
    Take a = 23 and b = 56.
    In binary forms, a = 0010111 and b = 0111000.
    In simple arithmetic addition, it adds up to 79 which is 1001111.
          0010111
        + 0111000
        ---------
          1001111
    However, the question prohibits using arithmetic operation.

    Now what exactly happens when you try to add up binary digits ?
    0 + 0 = 0
    1 + 0 = 1
    0 + 1 = 1
    1 + 1 = 0 with a carry of 1.
    This is almost similar to what happens when you use the XOR (^) operator, except XOR does not take care of the carry over.
    So, applying the XOR operator on a and b would give us the similar results as a + b but for only those places where there are no carry overs.
    	0010111
      ^	0111000
    	-------
    	0101111
    Notice that by doing this, we got 0101111 which is 47 in decimal representation.
    The carry over only happens in case of a 1 + 1. In any other case, there is no carry over.
    0 + 0 = No carry over
    1 + 0 = No carry over
    0 + 1 = No carry over
    1 + 1 = Carry over
    This pattern is similar when we use the AND (&) operator. Only 1 & 1 gives 1. Every other combination gives 0.
        0010111
      & 0111000
        -------
        0010000
    This gives us 0010000 which is 32.
    Now, this shows us where the carry over should happen. However, 1 & 1 gives 1, but 1 + 1 gives 10.
    So, we need to shift this carry 1 place to the left.
    This can be done by the left shift operator (<<).
    0010000 << 1 = 0100000

    Next, let's check what we get by adding this left shifted carry with the result of the XOR operation.
    => 0101111 + 0100000 = 1001111
    => 47 + 32 = 79
    We see that this sum gives us the same result as (23 + 56) which is 79.

    However, as restricted by the question we still cannot do a direct arithmetic addition.
    And, we see that we still have a case of carry over at 6th position from right in case of 0101111 + 0100000.
    This means we need to perform this action once again by replacing a with 47 and b with 32.

    This goes on until we get two numbers whose arithmetic addition is similar to XOR operation between them.
    That can only happen when there is no case of carry over.
    So essentially, when we keep on looping, we are shifting the carry overs to the left.

    Here's how whole iteration would look like :
    |-------|-----------------------|-----------------------|-------------------------------|
    |  i    |	carry = a & b		|		a = a ^ b		|		b = carry << 1			|
    |-------|-----------------------|-----------------------|-------------------------------|
    |	    |						|						|								|
    |	    |	23		0010111     |	23		0010111     |	16 << 1		0010000 << 1	|
    |  1    |	56	&	0111000 &	|	56	^	0111000	^   |	-------		------------    |
    |	    |	--		-------		|	--		-------     |	32			0100000         |
    |	    |	16		0010000		|	47		0101111	    |                               |
    |	    |						|                       |                               |
    |-------|-----------------------|-----------------------|-------------------------------|
    |	    |						|						|								|
    |	    |	47		0101111     |	47		0101111     |	32 << 1		0100000 << 1	|
    |	    |	32	&	0100000 &	|	32	^	0100000	^   |	-------		------------    |
    |  2    |	--		-------		|	--		-------     |	64			1000000         |
    |	    |	32		0100000		|	15		0001111	    |                               |
    |	    |						|                       |                               |
    |-------|-----------------------|-----------------------|-------------------------------|
    |	    |						|						|								|
    |	    |	15		0001111     |	15		0001111     |	00 << 1		0000000 << 1	|
    |	    |	64	&	1000000 &	|	64	^	1000000	^   |	-------		------------    |
    |  3    |	--		-------		|	--		-------     |	00			0000000         |
    |	    |	00		0000000		|	79		1001111	    |                               |
    |	    |						|                       |                               |
    |-------|-----------------------|-----------------------|-------------------------------|

    If you look close enough, you will understand that what we are trying to do is convert the summation into two such numbers
    where both the numbers get added without any carry over.
    Or in other words, when XOR operator gives the same result as arithmetic addition between the two numbers.

    Notes :
    -------

    Before understanding binary, let's understand the concept of base.
    Base is a form of representing numbers. A base 10 representation of numbers is called decimal representation which is the numbers as we know them.
    Base 2 is called Binary. And similarly, base 8 is called Octal, base 16 (representing 11 to 15 as letters from A to F) is called Hexadecimal.
    The Base is the number whose powered forms can sum up to represent a decimal number. For example,
    Octal form (Base 8) of 153 would be represented as 231. To understand better, let's write 153 in the following form :
    => 128 + 24 + 1
    => ((8^2) x 2) + ((8^1) x 3) + ((8^0) x 1)
    So, we take the multipliers of these powered 8s and that gives us the octal form of 153 which is 231.

    Binary representation : Any decimal number can be represented in binary form using 1s and 0s.
    Each nth place from the left (also called a bit) in a binary form, represents the (n-1) power of 2.
    To represent a number in a binary form, we sum up the value of the powered 2s in those places.
    If a place is considered in the summation, we put 1 there. If not, 0.

    For example, 13 can be represented as 1101.
    To understand this better, visualise 13 as :
    => 8 + 4 + 0 + 1
    => ((2^3) x 1) + ((2^2) x 1) + ((2^1) x 0) + ((2^0) x 1)
    So, 13 can be represented as 1101 in binary form.

    Now that we know how to represent a base 10 number in a binary form, let us understand how to represent a negative number in binary form as well.
    There are no + or - signs in binary representation of numbers.
    This can be done in 3 ways - Sign Magnitude Form, 1's compliment and 2's compliment.

    Sign Magnitude Form - In this form of binary representation, we reserve the right most bit to represent the sign of the number.
    For +, we put 0 and for -, we put 1.
    So, in a 8 bit storage, we represent the number 13 as 00001101.
    Similarly, -13 can be represented as 10001101.
    The right most bit is called the MSB or the Most Significant Bit and the rest are called Magnitude.
    Remember, in a 8 bit Sign Magnitude Form, 10001101 does not mean 141. The MSB is just for the sign and should not be considered in Magnitude calculation.
    So, 10001101 is equal to -13.
    An 8 bit SMF binary representation can only represent from -127 to +127.
    So, an n-bit SMF can represent a range of numbers from -(2^(n-1) - 1) to +(2^(n-1) - 1).
    There are two representations of the number 0 in SMF : 00000000 or 10000000.

    1's Compliment - In this form, we still have the concept of MSB. However, in addition to that, we convert all the 0s to 1s and vice-versa
    to represent the negative of that number.
    For example, +13 is represented as 00001101 in binary form.
    To represent -13 in 1's compliment form, we convert all the 0s to 1s and 1s to 0s. So we get, 11110010.
    11110010 should not be considered as 242 since the MSB is just to represent sign and not calculated with Magnitude.
    Now generally, 11111111 is 256 in normal binary representation. However, 256 is out of bounds in 1's compliment form of 8 bit binary representation.
    So in 1's compliment form 11111111 is similar to 00000000 and is considered to be equal to 0.
    To get -13 in decimal form, we subtract 13 from 0. Similarly, if you subtract, 00001101 from 11111111, you get 11110010.
    This is the 1's compliment representation of -13.
    An 8 bit 1's Compliment binary representation can only represent from -127 to +127.
    So, an n-bit 1's Compliment can represent a range of numbers from -(2^(n-1) - 1) to +(2^(n-1) - 1).
    There are two representations of the number 0 in SMF : 00000000 or 10000000.

    2's Compliment - Here, the positive number representation is the same.
    However, the negative representation of a umber is done by finding its 1s compliment and then adding 1 to it.
    For example, +13 is represented as 00001101 in binary form.
    To represent -13 in 2's compliment form, we convert it to 1's Compliment, which is 11110010. Then, we add 1 to it which gives 11110011.
    Another simple way to find 2s compliment of a binary number is to keep the digits same from left till you find a 1 and then convert the 0s to 1s and vice-versa
    For example, for 00101100, which is 44 in decimal form,  we can find the 2s compliment by starting to look for 1 from left.
    We see that we find 0 in the first and second place and then we find 1 at the third place. So, we keep these three digits the same and convert the remaining.
    This, would give us 11010100. In normal binary representation, that's 212. However, in 2's compliment representation, that's -13.
    2's compliment removes the drawback of dual representation of 0 which we saw in 1's compliment form.
    This gives us an extra space to store 1 more negative number in this representation.
    An 8 bit 2's Compliment binary representation can represent from -128 to +127.
    So, an n-bit 2's Compliment can represent a range of numbers from -(2^(n-1)) to +(2^(n-1) - 1).
    Here, 0 can only be represented as 00000000. 11111111 is considered as -1.

    Binary Operators :
    There are several operators which can be used to perform certain calculations on the binary numbers. Some of them are :
    -   AND (&) : This can be visualised as an operator which will return true in case both of the elements are true.
        In case of a false element, false is returned.
        1 is considered to be true in binary and 0 as false. So, the AND operator works the following way
        1 & 1 = 1
        1 & 0 = 0
        0 & 1 = 0
        0 & 0 = 0
        For example, 23 (010111) & 56 (111000) will give 16 (010000).
    -   OR (|) : This returns true in case any one of the elements is true. It works in the following way
        1 & 1 = 1
        1 & 0 = 1
        0 & 1 = 1
        0 & 0 = 0
        For example, 23 (010111) & 56 (111000) will give 63 (111111).
    -   XOR (^) : This returns true in case the elements are different. It works in the following way
        1 & 1 = 0
        1 & 0 = 1
        0 & 1 = 1
        0 & 0 = 0
        For example, 23 (010111) & 56 (111000) will give 47 (101111).
    -   NOT (!) : This returns true in case of False and vice-versa. It works in the following way
        !1 = 0
        !0 = 1
        For example, !23 (!(010111)) will give 40 (101000).
    -   Left Shift (<<) : This is used to shift the binary digits up to n places to the left.
        Last n digits are discarded and the first n places are filled with 0s.
        For example, 23 << 2 (010111) will give us 28 (011100). Here, we are shifting each digit in the binary form of 23 to 2 places towards left.
    -   Right Shift (>>) : This is used to shift the binary digits up to n places to the right.
        First n digits are discarded and the last n places are filled with 0s.
        For example, 23 >> 2 (010111) will give us 5 (000101). Here, we are shifting each digit in the binary form of 23 to 2 places towards right.

 */
