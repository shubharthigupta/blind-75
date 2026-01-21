# Blind 75 - Problems and Solutions

## Array2 Problems

### 1. Two Sum (E)
**Problem**: Find indices of two numbers that add up to the target.
- Use a HashMap to store complement values while iterating.
- Return indices when match found.
- **Time**: O(n), **Space**: O(n)

### 2. Best Time to Buy and Sell Stock (E)
**Problem**: Max profit from buying and selling once.
- Track minimum price and max profit while traversing.
- **Time**: O(n), **Space**: O(1)

### 3. Contains Duplicate (E)
**Problem**: Check if any value appears at least twice.
- Use a HashSet to track seen elements.
- **Time**: O(n), **Space**: O(n)

### 4. Product of Array2 Except Self (M)
**Problem**: Return array where each element is the product of all other elements.
- Use prefix and postfix product arrays.
- Avoid division.
- **Time**: O(n), **Space**: O(1) (excluding output array)

### 5. Maximum Subarray (M)
**Problem**: Find the contiguous subarray with the largest sum.
- Use Kadane’s algorithm: track current sum and max sum.
- **Time**: O(n), **Space**: O(1)

### 6. Maximum Product Subarray (M)
**Problem**: Find the subarray with the maximum product.
- Track max and min product at each step (due to negatives).
- **Time**: O(n), **Space**: O(1)

### 7. Find Minimum in Rotated Sorted Array2 (M)
**Problem**: Find the minimum in a rotated sorted array without duplicates.
- Use binary search.
- **Time**: O(log n), **Space**: O(1)

### 8. Search in Rotated Sorted Array2 (M)
**Problem**: Search for a target in a rotated sorted array.
- Use modified binary search accounting for rotation.
- **Time**: O(log n), **Space**: O(1)

### 9. 3Sum (M)
**Problem**: Find all unique triplets that sum to zero.
- Sort array, then use two-pointer technique.
- Skip duplicates.
- **Time**: O(n^2), **Space**: O(1)

### 10. Container With Most Water (M)
**Problem**: Find max water container using array of heights.
- Use two-pointer approach from both ends.
- **Time**: O(n), **Space**: O(1)

## Binary Problems

### 11. Sum of Two Integers
**Problem**: Calculate the sum of two integers without using `+` or `-` operators.
- Use bitwise XOR for addition without carry: `a ^ b`
- Use bitwise AND and shift for carry: `(a & b) << 1`
- Repeat until carry is 0.
- **Time**: O(1), **Space**: O(1)

### 12. Number of 1 Bits
**Problem**: Count the number of `1`s in the binary representation of a number.
- Use bit manipulation: `n & (n - 1)` removes the lowest `1` bit.
- Loop until `n == 0`, incrementing a counter.
- **Time**: O(1) for 32-bit integer, **Space**: O(1)

### 13. Counting Bits
**Problem**: For every number from 0 to n, return the number of `1`s in their binary representation.
- Use dynamic programming: `bits[i] = bits[i >> 1] + (i & 1)`
- **Time**: O(n), **Space**: O(n)

### 14. Missing Number
**Problem**: Find the missing number in a sequence from `0` to `n`.
- Use XOR: result = `0 ^ 1 ^ 2 ^ ... ^ n ^ nums[0] ^ nums[1] ^ ... ^ nums[n-1]`
- **Time**: O(n), **Space**: O(1)

### 15. Reverse Bits
**Problem**: Reverse the bits of a 32-bit unsigned integer.
- Iterate through 32 bits, shifting and OR-ing into result.
- **Time**: O(1), **Space**: O(1)

## Dynamic Programming

### 16. Climbing Stairs
**Problem**: Number of ways to climb to the top with 1 or 2 steps.
- Use bottom-up DP: `dp[i] = dp[i - 1] + dp[i - 2]`
- Can optimize to two variables only.
- **Time**: O(n), **Space**: O(1)

### 17. Coin Change
**Problem**: Minimum number of coins needed to make up a given amount.
- Use a DP array `dp[i] = min(dp[i], dp[i - coin] + 1)` for all coins.
- Initialize `dp[0] = 0`, rest as `amount + 1`.
- **Time**: O(amount * coins), **Space**: O(amount)

### 18. Longest Increasing Subsequence
**Problem**: Find the length of the longest increasing subsequence.
- Use a DP array and binary search (patience sorting).
- `dp[i]` stores the smallest tail of all increasing subsequences of length `i+1`.
- **Time**: O(n log n), **Space**: O(n)

### 19. Longest Common Subsequence
**Problem**: Find the length of LCS of two strings.
- Use 2D DP table: `dp[i][j]` = LCS of first `i` chars of text1 and first `j` chars of text2.
- If `text1[i-1] == text2[j-1]` → `dp[i][j] = dp[i-1][j-1] + 1`, else max of top/left.
- **Time**: O(m * n), **Space**: O(m * n)

### 20. Word Break
**Problem**: Determine if a string can be segmented into space-separated dictionary words.
- Use DP: `dp[i] = true` if `s[0..i]` can be formed using dictionary.
- Check all substrings and validate with HashSet.
- **Time**: O(n^2), **Space**: O(n)