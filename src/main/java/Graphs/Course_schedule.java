package Graphs;

import java.util.*;

class Course_schedule {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {

        BitSet visited = new BitSet(numCourses);

        Map<Integer, List<Integer>> adjacency = new HashMap<>();
        int[] inDegree = new int[numCourses];

        for (int[] prerequisite : prerequisites) {
            inDegree[prerequisite[0]]++;
            adjacency.computeIfAbsent(prerequisite[1], na -> new ArrayList<>()).add(prerequisite[0]);
        }

        Stack<Integer> khansQueue = new Stack<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0)
                khansQueue.push(i);
        }

        while (!khansQueue.isEmpty()) {

            int current = khansQueue.pop();
            //course completed
            if (visited.get(current))
                continue;
            visited.set(current);
            for (int id : adjacency.getOrDefault(current, Collections.emptyList())) {
                inDegree[id]--;
                if (inDegree[id] == 0)
                    khansQueue.push(id);
            }
        }

        return visited.nextClearBit(0) >= numCourses;
    }

    public static void main(String[] args) {

        canFinish(2, new int[][]{{1, 0}});
    }
}
