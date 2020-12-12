import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

//https://leetcode.com/problems/russian-doll-envelopes/
class RussianDolls {


    public int maxEnvelopes(int[][] envelopes) {

        if (envelopes == null || envelopes.length == 0)
            return 0;

        Arrays.sort(envelopes, (o1, o2) -> {

            int compare = Integer.compare(o1[0], o2[0]);
            if (compare == 0)
                return Integer.compare(o2[1], o1[1]);
            return compare;
        });

        //LCS in heights
        int[] dp = new int[envelopes.length];
        int len = 0;
        for (int i = 0; i < envelopes.length; i++) {
            int height = envelopes[i][1];

            int index = Arrays.binarySearch(dp, 0, len, height);
            index = index < 0 ? -(index + 1) : index;
            dp[index] = height;
            if (index == len)
                len++;
        }

        return len;
    }


    public static void main(String[] args) throws IOException {

        String input = Files.readAllLines(Paths.get("input.txt")).get(0);
        input = input.replaceAll("\\[", "");
        input = input.replaceAll("]", "");
        input = input.replaceAll(" ", "");
        String[] split = input.split(",");
        int[][] testcase = new int[split.length / 2][];
        for (int i = 0; i < split.length; i += 2) {
            testcase[i / 2] = new int[]{Integer.parseInt(split[i]), Integer.parseInt(split[i + 1])};
        }

        System.out.println(new RussianDolls().maxEnvelopes(testcase));
    }
}
