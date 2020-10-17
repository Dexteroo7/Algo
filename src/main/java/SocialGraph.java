import java.util.*;

public class SocialGraph {

    private static final class SocialMember implements Comparable<SocialMember> {

        final int memberIndex, groupSize;

        private SocialMember(int memberIndex, int groupSize) {
            this.memberIndex = memberIndex;
            this.groupSize = groupSize;
        }

        @Override
        public int compareTo(SocialMember o) {
            return Integer.compare(this.memberIndex, o.memberIndex);
        }
    }

    /*
     * Complete the 'socialGraphs' function below.
     *
     * The function accepts INTEGER_ARRAY counts as parameter.
     */

    public static void socialGraphs(List<Integer> counts) {

        Map<Integer, PriorityQueue<SocialMember>> memberMap = new HashMap<>();
        for (int memberIndex = 0; memberIndex < counts.size(); memberIndex++) {

            int groupSize = counts.get(memberIndex);
            SocialMember member = new SocialMember(memberIndex, groupSize);
            memberMap.computeIfAbsent(groupSize, PriorityQueue::new).add(member);
        }

        Set<Integer> processedIds = new HashSet<>();
        for (int currentIndex = 0; currentIndex < counts.size(); currentIndex++) {
            if (processedIds.contains(currentIndex))
                continue;
            int groupSize = counts.get(currentIndex);
            PriorityQueue<SocialMember> socialMembers = memberMap.get(groupSize);
            String[] toPrint = new String[groupSize];
            for (int i = 0; i < groupSize; i++) {
                int memberIndex = socialMembers.poll().memberIndex;
                toPrint[i] = memberIndex + "";
                processedIds.add(memberIndex);
            }
            System.out.println(String.join(" ", toPrint));
        }
    }

    public static void main(String[] args) {

        socialGraphs(Arrays.asList(3, 3, 3, 1, 1, 1, 3, 3, 3));
        System.out.println();

//        socialGraphs(Arrays.asList(1));
//        System.out.println();
//
//        socialGraphs(Arrays.asList(1, 1));
//        System.out.println();
//
//        socialGraphs(Arrays.asList(1, 1, 1));
//        System.out.println();
    }
}
