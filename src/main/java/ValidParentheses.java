import java.util.Stack;

//https://leetcode.com/problems/valid-parentheses/submissions/
class ValidParentheses {

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            char current = s.charAt(i);
            if (current == '(' || current == '{' || current == '[')
                stack.push(current);
            else {

                if (stack.isEmpty())
                    return false;
                char popped = stack.pop();
                if (current == ')' && popped != '(')
                    return false;
                if (current == '}' && popped != '{')
                    return false;
                if (current == ']' && popped != '[')
                    return false;
            }
        }
        return stack.isEmpty();
    }
}
