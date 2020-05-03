import java.util.BitSet;

//https://www.hackerrank.com/challenges/abbr/problem
class Regex {

    public static boolean isMatch(String toCheck, String pattern) {

        //empty pattern
        if (pattern == null || pattern.isEmpty())
            return toCheck == null || toCheck.isEmpty();

        MyMap myMap = new MyMap(toCheck.length(), pattern.length());
        return isMatch(toCheck, pattern, 0, 0, myMap) == 1;
    }

    private static int isMatch(String toCheck, String pattern, int toCheckPos, int patternPos, MyMap myMap) {

        int fromMemo = myMap.get(toCheckPos, patternPos);
        if (fromMemo != 0)
            return fromMemo;

        //if pattern is exhausted, check if string is fully matched
        if (patternPos >= pattern.length())
            return myMap.set(toCheckPos, patternPos, toCheckPos >= toCheck.length() ? 1 : -1);

        char patternChar = pattern.charAt(patternPos);
        if (patternPos + 1 < pattern.length() && pattern.charAt(patternPos + 1) == '*') {

            if (toCheckPos >= toCheck.length()) {
                //match 0 times
                return myMap.set(toCheckPos, patternPos, isMatch(toCheck, pattern, toCheckPos, patternPos + 2, myMap));
            }
            char charToCheck = toCheck.charAt(toCheckPos);
            if (characterMatch(patternChar, charToCheck)) {
                int dontUsePattern = isMatch(toCheck, pattern, toCheckPos, patternPos + 2, myMap);
                if (dontUsePattern == 1)
                    return myMap.set(toCheckPos, patternPos, 1);
                int usePattern = isMatch(toCheck, pattern, toCheckPos + 1, patternPos, myMap);
                if (usePattern == 1)
                    return myMap.set(toCheckPos, patternPos, 1);
                //if any works
                return myMap.set(toCheckPos, patternPos, -1);
            } else {
                //skip the pattern
                return myMap.set(toCheckPos, patternPos, isMatch(toCheck, pattern, toCheckPos, patternPos + 2, myMap));
            }
        } else {

            //extra char in the pattern
            if (toCheckPos >= toCheck.length())
                return -1;
            char charToCheck = toCheck.charAt(toCheckPos);
            //proceed if matched
            if (characterMatch(patternChar, charToCheck))
                return myMap.set(toCheckPos, patternPos, isMatch(toCheck, pattern, toCheckPos + 1, patternPos + 1, myMap));
            return myMap.set(toCheckPos, patternPos, -1);
        }
    }

    private static final class MyMap {

        final BitSet trueSet;
        final BitSet falseSet;
        final int width;

        private MyMap(int width, int height) {
            this.trueSet = new BitSet((width + 1) * (height + 1));
            this.falseSet = new BitSet((width + 1) * (height + 1));
            this.width = width;
        }

        int set(int a, int b, int value) {
            int index = a + b * width;
            if (value == 1) {
                trueSet.set(index);
            } else {
                falseSet.set(index);
            }
            return value;
        }

        int get(int a, int b) {
            int index = a + b * width;
            if (trueSet.get(index))
                return 1;
            if (falseSet.get(index))
                return -1;
            return 0;
        }
    }

    private static boolean characterMatch(char a, char b) {
        return a == '.' || b == '.' || a == b;
    }

    public static void main(String[] args) {

        System.out.println(isMatch("", ".*"));
        System.out.println(isMatch("", "."));
        System.out.println(isMatch("aa", "a"));
        System.out.println(isMatch("aa", "a*"));
        System.out.println(isMatch("ab", ".*"));
        System.out.println(isMatch("aab", "c*a*b"));
        System.out.println(isMatch("mississippi", "mis*is*p*."));

//        System.out.println(isMatch("ab", "abc"));
//        System.out.println(isMatch("ab", "a*b*abc*"));
//        System.out.println(isMatch("ab", "a*b*abc"));
//        System.out.println(isMatch("ab", "a*b*abc*"));
//        System.out.println(isMatch("abb", "a*b*abc*"));
//        System.out.println(isMatch("abb", "a*b*bc*"));
//        System.out.println(isMatch("abb", "a*bbc*"));
//        System.out.println(isMatch("abb", "abc*"));
//
//        System.out.println(isMatch("abc", "..."));
//        System.out.println(isMatch("abc", ".*.*.*"));
//        System.out.println(isMatch("abc", ".*.*.*.*"));
    }
}
