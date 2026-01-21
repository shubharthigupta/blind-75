package Array.Other.LongestSubstringWithoutRepeatingCharacters;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharactersSolution {

    public static int lengthOfLongestSubstring(String s) {
        Set<Character> seen = new HashSet<>();
        int left = 0, maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            while (seen.contains(s.charAt(right))) {
                seen.remove(s.charAt(left));
                left++;
            }
            seen.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    // Test Cases

    public static void main(String[] args) {
        // 1. LeetCode example
        runTest("abcabcbb", 3, "Test 1 - LeetCode case 1");

        // 2. Repeating characters
        runTest("bbbbb", 1, "Test 2 - All same characters");

        // 3. Alternating repeat
        runTest("pwwkew", 3, "Test 3 - Mixed repeats");

        // 4. Empty string
        runTest("", 0, "Test 4 - Empty input");

        // 5. All unique characters
        runTest("abcdef", 6, "Test 5 - All unique characters");

        // 6. Single character
        runTest("a", 1, "Test 6 - Single character");

        // 7. Substring with reset needed
        runTest("abba", 2, "Test 7 - Overlap reset");

        // 8. Long unique run in the middle
        runTest("abcdeafghij", 10, "Test 8 - Unique run with one repeat");

        // 9. Unicode characters (emojis)
        runTest("🙂🙃🙂🙃😎", 3, "Test 9 - Unicode characters");

        // 10. Large input with no repeats
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 128; i++) {
            sb.append((char) i);
        }
        runTest(sb.toString(), 128, "Test 10 - Long unique ASCII characters");

        System.out.println("\n🧪 Testing completed.");
    }


    public static void runTest(String input, int expected, String testName) {
        int result = lengthOfLongestSubstring(input);
        if (result == expected) {
            System.out.println(testName + ": ✅ PASSED");
        } else {
            System.out.println(testName + ": ❌ FAILED (Expected " + expected + ", but got " + result + ")");
            System.out.println("  Input: \"" + input + "\"");
        }
    }

}
