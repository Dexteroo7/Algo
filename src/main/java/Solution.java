/*
# We have two strings: a normal alphanumeric string and a pattern string. the pattern string can be composed by
# alphanumeric chars plus the char "?" and "*"
#
# We want to check if the first string match the pattern, where the ? means that every char (alphanumeric) is
# permitted in that position, while * allows to
# have a sequence of alphanumeric chars.
#
# Simulate regular expression
#
# ?: any character 1; a=?
# *: Any character 0 or more times; "" == *, "a" ==* "aa" == *  NOT: "ab"== *
#
# as test we want to check that the function returns true to the following calls.
#
# is_matching("abab","abab"): True
# is_matching("abab","a**b"): True
# is_matching("ababab","ab*b"): False
# is_matching("","*"): True
# is_matching("aaaaaab","*?*b"): True
#
def is_matching(s, r):
  pass

*/


/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

class Solution {

    public static boolean isMatch(String input, String pattern) {

        if (input.equals(pattern))
            return true;

        return isMatch(input, pattern, 0, 0, '\0');
    }

    public static boolean isMatch(String input, String pattern, int inputIndex, int patternIndex, char matchedByStar) {

        if (inputIndex == input.length()) {
            //check that remaining pattern is all *
            for (int i = patternIndex; i < pattern.length(); i++) {

                if (pattern.charAt(i) != '*')
                    return false;
            }
            return true;
        }

        if (inputIndex >= input.length() || patternIndex >= pattern.length())
            return false;

        char inputAtIndex = input.charAt(inputIndex);
        char patternAtIndex = pattern.charAt(patternIndex);

        if (inputAtIndex == patternAtIndex) {
            //procced
            return isMatch(input, pattern,inputIndex+1, patternIndex+1, matchedByStar);
        } else {

            if (patternAtIndex == '?') {
                //procced
                return isMatch(input, pattern,inputIndex+1, patternIndex+1, matchedByStar);
            } else if(patternAtIndex == '*') {

                //proceed with single match
                if (matchedByStar != '\0' && matchedByStar != inputAtIndex)
                    return isMatch(input, pattern,inputIndex+1, patternIndex+1, '\0');
                boolean optionA = isMatch(input, pattern,inputIndex+1, patternIndex + 1, inputAtIndex);
                //proceed with multiple matches
                boolean optionB = isMatch(input, pattern,inputIndex+1, patternIndex, inputAtIndex);
                return optionA || optionB;
            } else {
                return false;
            }
        }
    }



    public static void main(String[] args) {

        //aaa, * => (aaa, '') (aa, '') (a, '') ('', '')

        System.out.println(isMatch("abab","abab")); //: True
        System.out.println(isMatch("abab","a**b")); //: True
        System.out.println(isMatch("ababab","ab*b")); //: False
        System.out.println(isMatch("","")); //: True
        System.out.println(isMatch("","*")); //: True
        System.out.println(isMatch("a","")); //: False
        System.out.println(isMatch("a","????")); //: False
        System.out.println(isMatch("a","****")); //: True
        System.out.println(isMatch("aaaaaab","*?*b")); //: True


    }
}
