import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class JourneytotheMoon {

    // Complete the journeyToMoon function below.
    static long journeyToMoon(int n, int[][] astronaut) {

        //make adjacency list
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        for (int[] pair : astronaut) {
            adjacencyList.computeIfAbsent(pair[0], na -> new LinkedList<>()).add(pair[1]);
            adjacencyList.computeIfAbsent(pair[1], na -> new LinkedList<>()).add(pair[0]);
        }

        long base = 0;
        Map<Integer, Integer> groupSizes = new HashMap<>();
        Deque<Integer> searchQueue = new LinkedList<>();
        BitSet visited = new BitSet(n);
        int startingId = visited.nextClearBit(0);
        while (startingId < n) {

            int currentGroupSize = 0;
            searchQueue.addLast(startingId);

            while (!searchQueue.isEmpty()) {

                int aId = searchQueue.removeFirst();
                if (visited.get(aId))
                    continue;
                visited.set(aId);
                currentGroupSize++;
                searchQueue.addAll(adjacencyList.getOrDefault(aId, Collections.emptyList()));
            }

            for (Map.Entry<Integer, Integer> entry : groupSizes.entrySet()) {

                int size = entry.getKey();
                int count = entry.getValue();
                base = base + (size * count * currentGroupSize);
            }

            groupSizes.merge(currentGroupSize, 1, Integer::sum);
            startingId = visited.nextClearBit(0);
        }

        return base;
    }


    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(new FileInputStream("input07.txt"));
        String[] np = scanner.nextLine().split(" ");

        int n = Integer.parseInt(np[0]);

        int p = Integer.parseInt(np[1]);

        int[][] astronaut = new int[p][2];

        for (int i = 0; i < p; i++) {
            String[] astronautRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int astronautItem = Integer.parseInt(astronautRowItems[j]);
                astronaut[i][j] = astronautItem;
            }
        }

        long result = journeyToMoon(n, astronaut);
        System.out.println(result);
        scanner.close();
    }
}
