import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BiConsumer;

public class Job_Sequencing_Problem {

    private static final class Job {
        final int id, deadline, profit;

        private Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }

        @Override
        public String toString() {
            return "Job{" +
                    "id=" + id +
                    ", deadline=" + deadline +
                    ", profit=" + profit +
                    '}';
        }
    }

    static void gg(Map<Integer, PriorityQueue<Integer>> grouped) {

        grouped.forEach(new BiConsumer<Integer, PriorityQueue<Integer>>() {
            @Override
            public void accept(Integer deadline, PriorityQueue<Integer> profits) {
                System.out.println("deadline: " + deadline + ", profits: " + profits);
            }
        });
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get("input.txt"))));

        int testCases = Integer.parseInt(reader.readLine());

        for (int na = 0; na < testCases; na++) {

            int jobCount = Integer.parseInt(reader.readLine());
            String[] split = reader.readLine().split(" ");

            List<Job> jobs = new ArrayList<>();
            BitSet timeline = new BitSet();
            for (int i = 0; i < split.length; i += 3) {
                int id = Integer.parseInt(split[i]);
                int deadline = Integer.parseInt(split[i + 1]);
                int profit = Integer.parseInt(split[i + 2]);
                Job job = new Job(id, deadline, profit);
                jobs.add(job);
            }

            jobs.sort(Comparator.<Job>comparingInt(x -> x.profit).reversed());
//            jobs.forEach(System.out::println);

            List<Job> scheduled = new ArrayList<>();
            for (Job job : jobs) {
                int deadline = job.deadline;
                int possibleSchedule = timeline.previousClearBit(deadline - 1);
                if (possibleSchedule >= 0) {
                    timeline.set(possibleSchedule);
                    scheduled.add(job);
                }
            }
            System.out.println(scheduled.size() + " " + scheduled.stream().mapToInt(x -> x.profit).sum());
//            System.out.println();
//            scheduled.forEach(System.out::println);
//            System.out.println();
//            System.out.println();
        }
    }
}
