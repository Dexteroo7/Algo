import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//https://www.codechef.com/submit/ENCD12
class MinSwapsToPalindrome {

    public static final int RIGHT = 0;
    public static final int LEFT = -1;

    public static String minSwaps(String input) {

        char[] chars = input.toCharArray();

        int swaps = 0;

        for (int left = 0, right = input.length() - 1; left <= right; left++, right--) {

            if (chars[left] != chars[right]) {
                //we want to find the min swap in the (left,right) window
                char requiredLeft = chars[right];
                char requiredRight = chars[left];
                for (int leftFinder = left + 1, rightFinder = right - 1; leftFinder < right && rightFinder > left; leftFinder++, rightFinder--) {
                    if (chars[leftFinder] == requiredLeft) {
                        swaps += shift(chars, left, leftFinder, LEFT);
                        break;
                    }
                    if (chars[rightFinder] == requiredRight) {
                        swaps += shift(chars, rightFinder, right, RIGHT);
                        break;
                    }
                }
                if (chars[left] != chars[right])
                    return "Impossible";
            }
        }

        return swaps + "";
    }

    public static int shift(char[] chars, int a, int b, int direction) {

        int swaps = 0;
        if (direction == LEFT)
            while (b > a) {
                char temp = chars[b];
                chars[b] = chars[b - 1];
                chars[b - 1] = temp;
                b--;
                swaps++;
            }
        else
            while (a < b) {
                char temp = chars[a];
                chars[a] = chars[a + 1];
                chars[a + 1] = temp;
                a++;
                swaps++;
            }
        return swaps;
    }

    public static void main(String[] args) {

//        List<String> input = new BufferedReader(new InputStreamReader(System.in)).lines().collect(Collectors.toList());

        List<String> input = Arrays.asList("mamad", "asflkj", "aabb", "aadaa", "abcdbca",
                "0");

        for (int i = 0; i < input.size() - 1; i++) {
            System.out.println(minSwaps(input.get(i)));
        }
    }
}
