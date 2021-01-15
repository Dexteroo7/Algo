import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

class Traversal {

    static void bfs(int starting, ArrayList<ArrayList<Integer>> list, boolean[] vis, int nov) {

        list.forEach(System.out::println);

        List<Integer> result = bfs(starting, (List<List<Integer>>) (List<? extends List<Integer>>) list, new BitSet(vis.length));
        result.forEach(x -> System.out.print(x + " "));
    }

    static List<Integer> bfs(int starting, List<List<Integer>> list, BitSet visited) {

        List<Integer> bfsResult = new ArrayList<>();
        Deque<Integer> bfsQueue = new LinkedList<>();
        bfsResult.add(starting);
        visited.set(starting);
        bfsQueue.addFirst(starting);

        while (!bfsQueue.isEmpty()) {

            Integer currentId = bfsQueue.removeLast();
            list.get(currentId).stream()
                    .filter(x -> !visited.get(x))
                    .forEach(id -> {
                        bfsResult.add(id);
                        bfsQueue.add(id);
                        visited.set(id);
                    });
        }
        return bfsResult;
    }

    public static void main(String[] args) throws IOException {

        List<String> input = Files.readAllLines(Paths.get("input.txt"), Charset.defaultCharset());
        String[] s = input.get(0).split(" ");
        int nodes = Integer.parseInt(s[0]);
        int edges = Integer.parseInt(s[1]);

        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        int[] edgeRelations = Arrays.stream(input.get(1).split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < edges; i++) {
            int start = edgeRelations[i * 2];
            int end = edgeRelations[(i * 2) + 1];
            adjacencyList.get(start).add(end);
        }

        bfs(0, adjacencyList, new boolean[adjacencyList.size() + 1], -1);
    }
}
