import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class JumpGame {

    public static boolean canJump(int[] nums) {
        return jump(nums) >= 0;
    }

    public static int jump(int[] nums) {

        if (nums == null || nums.length == 0)
            return -1;
        if (nums.length == 1)
            return 0;

        int currentIndex = 0;
        int jumps = 0;
        while (currentIndex < nums.length) {

            int maxJump = nums[currentIndex];
            //we want to jump an index that gives maximum travel potential
            //we dont want to land on a 0
            //if end is reached we quit
            int maxPotential = -1;
            int nextIndex = -1;
            for (int jump = 1; jump <= maxJump; jump++) {

                int nextPossibleIndex = currentIndex + jump;
                if (nextPossibleIndex >= nums.length - 1) {
                    nextIndex = nums.length;
                    break;
                }
                if (nums[nextPossibleIndex] == 0)
                    continue;
                int potential = (nextPossibleIndex) + nums[nextPossibleIndex];
                if (potential > maxPotential) {
                    maxPotential = potential;
                    nextIndex = nextPossibleIndex;
                }
            }

            //not possible
            if (nextIndex == -1)
                return -1;

            jumps++;
            currentIndex = nextIndex;
        }

        return jumps;
    }

    public static void main(String[] args) throws IOException {

        String[] split = Files.readAllLines(Paths.get("input.txt")).get(0).split(",");
        int[] ints = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();

        System.out.println(jump(new int[]{1, 1}));
        System.out.println(jump(new int[]{2, 1}));
        System.out.println(jump(new int[]{1, 1, 1}));
        System.out.println(jump(new int[]{1, 2, 1}));
        System.out.println(jump(new int[]{2, 2, 1}));
        System.out.println(jump(new int[]{3, 2, 1}));

        System.out.println(jump(new int[]{1, 2, 5, 1, 1, 1, 1, 1}));
        System.out.println(jump(new int[]{1, 2, 5, 1, 1, 1, 1, 1, 1}));
        System.out.println(jump(new int[]{2, 3, 0, 1, 4}));
        System.out.println(jump(ints));

    }
}
