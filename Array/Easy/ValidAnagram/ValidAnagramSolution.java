package Array.Easy.ValidAnagram;

import java.util.HashMap;
import java.util.HashSet;

public class ValidAnagramSolution {

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Integer> charMap = new HashMap<>();
        for(int i=0; i<s.length(); i++){
            char currentChar = s.charAt(i);
            charMap.put(currentChar, charMap.getOrDefault(currentChar, 0) + 1);
        }
        for(int i=0; i<t.length(); i++){
            char currentChar = t.charAt(i);
            if(!charMap.containsKey(currentChar) || charMap.get(currentChar) == 0){
                return false;
            }
            charMap.put(currentChar, charMap.get(currentChar) - 1);
        }
        return true;
    }

}
