import java.util.Comparator;
import java.util.PriorityQueue;

//https://leetcode.com/problems/sort-characters-by-frequency/
class FrequencySort {

    public String frequencySort(String s) {

        if (s == null || s.isEmpty())
            return s;

        int[] counter = new int['z' + 1];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i)] += 1;
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.<Integer>comparingInt(x -> counter[x]).reversed());
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] > 0)
                priorityQueue.add(i);
        }

        StringBuilder builder = new StringBuilder();
        while (!priorityQueue.isEmpty()) {
            int aChar = priorityQueue.poll();
            for (int i = 0; i < counter[aChar]; i++) {
                builder.append((char)aChar);
            }
        }

        return builder.toString();
    }
}
