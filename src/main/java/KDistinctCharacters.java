import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
class KDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {

        if (k == 0)
            return 0;
        if (s.length() == 1)
            return 1;

        int[] characterCounter = new int[256];
        int size = 0;

        int left = 0;
        int maxLength = 0;
        for (int right = 0; right < s.length(); right++) {

            int current = s.charAt(right);
            characterCounter[current] += 1;
            if (characterCounter[current] == 1)
                size++;
            if (size > k) {

                maxLength = Integer.max(maxLength, right - left);
                int fromLeft = s.charAt(left);
                if (characterCounter[fromLeft] == 1) {
                    characterCounter[fromLeft] = 0;
                    size--;
                } else
                    characterCounter[fromLeft]--;
                left++;
            }
        }

        maxLength = Integer.max(maxLength, s.length() - left);
        return maxLength;
    }
}
