import java.util.ArrayList;
import java.util.BitSet;
import java.util.stream.IntStream;

class DetectCycleDirected {
    static boolean isCyclic(ArrayList<ArrayList<Integer>> adjacency, int numberOfVertices) {

        BitSet visited = new BitSet(numberOfVertices + 1);
        BitSet reuseSeen = new BitSet(numberOfVertices + 1);

        int start = visited.nextClearBit(0);
        while (start < numberOfVertices) {

            reuseSeen.clear();
            BitSet returned = dfs(adjacency, start, reuseSeen, visited);
            if (returned == null)
                return true;

            start = visited.nextClearBit(0);
        }

        return false;
    }

    static BitSet dfs(ArrayList<ArrayList<Integer>> adjacency, int current, BitSet seen, BitSet visited) {

        if (!visited.get(current)) {

            visited.set(current);
            ArrayList<Integer> connections = adjacency.get(current);
            for (int connection : connections) {

                BitSet returned = dfs(adjacency, connection, seen, visited);
                //hack to halt when cycle is detected
                if (returned == null)
                    return null;
                if (returned.get(current))
                    return null;
            }
        }
        seen.set(current);
        return seen;
    }

    public static void main(String[] args) {

//        int nodes = 61;
//        String edges = "45 16 54 29 12 41 36 13 9 31 49 52 46 53 22 4 8 11 35 19 11 54 22 47 30 37 42 53 44 47 54 28 4 47 59 19 29 35 32 39 5 23 32 51 17 55 57 25 7 31 46 18 26 8 6 57 45 50 51 30 37 47 60 43 35 59 1 4";
        int nodes = 3;
        String edges = "0 1";

        ArrayList<ArrayList<Integer>> adjacency = new ArrayList<>(nodes);
        IntStream.range(0, nodes).forEach(na -> adjacency.add(new ArrayList<>()));
        String[] split = edges.trim().split(" ");
        for (int i = 0; i < split.length; i += 2) {

            int from = Integer.parseInt(split[i]);
            int to = Integer.parseInt(split[i + 1]);
            adjacency.get(from).add(to);
        }

        boolean cyclic = isCyclic(adjacency, nodes);
        System.out.println(cyclic);
    }
}
