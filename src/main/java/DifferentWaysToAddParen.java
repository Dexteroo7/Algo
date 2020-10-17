import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//https://leetcode.com/problems/different-ways-to-add-parentheses/
class DifferentWaysToAddParen {
    public List<Integer> diffWaysToCompute(String input) {

        return ways(input);
    }

    public List<Integer> ways(String input) {

        List<Integer> ways = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) >= '0' && input.charAt(i) <= '9')
                continue;
            List<Integer> leftWays = ways(input.substring(0, i));
            List<Integer> rightWays = ways(input.substring(i + 1));
            for (int leftWay : leftWays) {
                for (int rightWay : rightWays) {
                    ways.add(operate(leftWay, input.charAt(i), rightWay));
                }
            }
        }

        if (ways.isEmpty()) {
            if (input.length() >= 1)
                return Collections.singletonList(Integer.parseInt(input));
        }

        return ways;
    }

    public int operate(int a, char operator, int b) {

        if (operator == '-')
            return a - b;
        if (operator == '+')
            return a + b;
        if (operator == '*')
            return a * b;
        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {

        System.out.println(new DifferentWaysToAddParen().diffWaysToCompute("10+5"));
    }
}
