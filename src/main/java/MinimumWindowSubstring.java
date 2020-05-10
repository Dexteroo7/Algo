import java.util.*;

class MinimumWindowSubstring {

    public String minWindow(String string, String toFind) {

        if (toFind.length() == 1)
            return string.contains(toFind) ? toFind : "";

        int min = Integer.MAX_VALUE;
        int leftMin = -1, rightMin = -1;

        Map<Character, Integer> needToLookFor = new HashMap<>();
        for (int i = 0; i < toFind.length(); i++) {
            needToLookFor.merge(toFind.charAt(i), 1, Integer::sum);
        }

        Map<Character, Integer> runningCount = new HashMap<>();
        int formed = 0;
        for (int left = 0, right = 0; right < string.length(); right++) {

            char charAt = string.charAt(right);
            int requiredCount = needToLookFor.getOrDefault(charAt, 0);
            if (requiredCount > 0) {
                int found = runningCount.merge(charAt, 1, Integer::sum);
                if (found == requiredCount)
                    formed++;
            }

            while (formed == needToLookFor.keySet().size()) {

                int size = right - left + 1;
                if (size < min) {
                    min = size;
                    leftMin = left;
                    rightMin = right;
                }
                char charAtLeft = string.charAt(left);
                requiredCount = needToLookFor.getOrDefault(charAtLeft, 0);
                if (requiredCount > 0) {
                    int found = runningCount.merge(charAtLeft, -1, Integer::sum);
                    if (found < requiredCount) {
                        formed--;
                    }
                }
                left++;
            }
        }

        if (leftMin == -1 || rightMin == -1)
            return "";

        return string.substring(leftMin, rightMin + 1);
    }

    public static void main(String[] args) {

        MinimumWindowSubstring solution = new MinimumWindowSubstring();
        solution.minWindow("ADOBECODEBANC", "ABC");
    }
}
