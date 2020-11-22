import java.util.*;

//https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/submissions/
class MinimumRemoveValidParen {
    public String minRemoveToMakeValid(String s) {

        Set<Integer> indexToDelete = new HashSet<>();
        Stack<Integer> openParenIndex = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            if (c == '(') {
                openParenIndex.push(i);
            } else if (c == ')') {

                if (openParenIndex.isEmpty())
                    indexToDelete.add(i);
                else
                    openParenIndex.pop();
            }
        }

        indexToDelete.addAll(openParenIndex);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indexToDelete.contains(i))
                builder.append(s.charAt(i));
        }

        return builder.toString();
    }
}
