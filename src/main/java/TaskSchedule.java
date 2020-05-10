import java.util.Arrays;
import java.util.BitSet;

class TaskSchedule {

    public int leastInterval(char[] tasks, int n) {

        int[] counts = new int['Z' - 'A' + 1];
        for (char task : tasks) {
            counts[task - 'A']++;
        }

        Arrays.sort(counts);

        BitSet schedule = new BitSet();
        int maxPos = 0;
        for (int i = counts.length - 1; i >= 0; i--) {
            if (counts[i] == 0)
                break;
            for (int j = 0; j < counts[i]; j++) {

                int toSet = schedule.nextClearBit(1 + (j * (n + 1)));
                maxPos = Integer.max(maxPos, toSet);
                schedule.set(toSet);
            }
        }

        return maxPos;
    }

    public static void main(String[] args) {

        TaskSchedule solution = new TaskSchedule();
        System.out.println(solution.leastInterval(new char[]{'A'}, 2));
        System.out.println(solution.leastInterval(new char[]{'A', 'A'}, 2));
        System.out.println(solution.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B'}, 2));
        System.out.println(solution.leastInterval(new char[]{'A', 'A', 'A', 'C', 'D', 'E', 'F', 'A'}, 2));
        System.out.println(solution.leastInterval(new char[]{'A', 'B', 'C'}, 2));
        System.out.println(solution.leastInterval(new char[]{'A', 'B', 'C', 'C'}, 2));
    }
}
// A A A B B B -> A B .. A B .. A B
