import java.util.*;

class PartitionLabels {
    public List<Integer> partitionLabels(String s) {

        if (s == null || s.isEmpty())
            return Collections.emptyList();

        if (s.length() == 1)
            return Collections.singletonList(1);

        List<Integer> toReturn = new ArrayList<>();

        int[] lastOccurrence = new int['z' + 1];
        for (int i = 0; i < s.length(); i++) {
            lastOccurrence[s.charAt(i)] = Integer.max(lastOccurrence[s.charAt(i)], i);
        }

        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {

            char current = s.charAt(i);
            right = Integer.max(right, lastOccurrence[current]);
            if (i == right) {
                toReturn.add(right - left + 1);
                left = i + 1;
                right = left;
            }
        }

        if (right > left)
            toReturn.add(right - left + 1);

        return toReturn;
    }
}
