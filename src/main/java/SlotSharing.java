import java.util.ArrayList;
import java.util.List;

public class SlotSharing {

    private static final class Topic {
        String name;
        int weight;
    }

    private static final class Group {
        List<Topic> topic;
        int parallelism;

        //effective weight is sum of individual weights divided by parallelism
        public int effectiveWeight() {
            return topic.stream().mapToInt(x -> x.weight).sum() / parallelism;
        }
    }

    private static final class Plan {
        List<Group> groups;

        public int requiredSlots() {
            return groups.stream().mapToInt(x -> x.parallelism).sum();
        }
    }

    public static void main(String[] args) {

        //given list of topics
        List<Topic> topics = new ArrayList<>();

        //divide into groups, such that
        //Group.effectiveWeight of each group is as close to each other as possible
        //Plan.requiredSlots of overall plan = specified scale factor

        //lets say we want to run on 100 slots
        int slots = 100;

        //one way to achieve this is to have a single group with parallelism of 100
        //this is wasteful as some topics have a very low weight, and a parallelism of 100 will overall not ensure best resource utilization
        //or 100 groups with parallelism of 1
        //this is wasteful, as a this is equivalent to slot per topic, which does not scale for high weight topics


        //lets look at a sample situation
        /**
         * topic | weight
         * topic1 10
         * topic2 5
         * topic3 2
         * topic4 1
         * topic5 1
         * topic6 1
         *
         * lets say we want to divide this up into 20 slots
         * group | parallelism
         * [topic1] 10
         * [topic2] 5
         * [topic3] 2
         * [topic4, topic5, topic6] 3
         *
         * each group has effective weight of 1
         */
    }
}
