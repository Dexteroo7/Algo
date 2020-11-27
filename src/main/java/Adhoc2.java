import java.util.*;

class Adhoc2 {

    Map<Integer, Integer> TIME_IDS = new HashMap<>();
    public int numPairsDivisibleBy60(int[] times) {

        int counter = 0;
        TIME_IDS.clear();
        for (int i = 0; i < times.length; i++) {

            int time = times[i];
            for (int j = 60 * (time / 60); j <= 1000; j += 60) {
                counter += TIME_IDS.getOrDefault(j - time, 0);
            }
            TIME_IDS.merge(time, 1, Integer::sum);
        }

        return counter;
    }

    public static void main(String[] args) {

        System.out.println(new Adhoc2().numPairsDivisibleBy60(new int[]{18, 18, 71, 471, 121, 362, 467, 107, 138, 254}));
    }
}
