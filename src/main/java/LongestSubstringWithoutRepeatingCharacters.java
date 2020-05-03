import java.util.HashMap;
import java.util.Map;

class LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {

        if (s == null || s.isEmpty())
            return 0;
        if (s.length() == 1)
            return 1;

        int max = 0;
        int[] seen = new int['z' - 'a' + 1];
        for (int left = 0, right = 0; right < s.length(); right++) {

            char c = (char) (s.charAt(right) - 'a');
            if (seen[c] > 0) {
                //move left
                left = Integer.max(left, seen[c]);
            }
            seen[c] = right + 1;
            max = Integer.max(max, right - left + 1);
        }

        return max;
    }

    public static void main(String[] args) {

//        System.out.println(longestPalindrome("a"));
//        System.out.println(longestPalindrome("ab"));
//        System.out.println(longestPalindrome("abba"));
//
//        System.out.println(longestPalindrome("babad"));
//        System.out.println(longestPalindrome("cbbd"));

//        System.out.println(lengthOfLongestSubstring("abccba"));
        System.out.println(lengthOfLongestSubstring("aaa"));
        System.out.println(lengthOfLongestSubstring("aab"));
        System.out.println(lengthOfLongestSubstring("aabb"));
        System.out.println(lengthOfLongestSubstring("aabbc"));
        System.out.println(lengthOfLongestSubstring("aabbcc"));
        System.out.println(lengthOfLongestSubstring("aabbcdc"));
    }
}
