package graph;

import java.util.*;

class CourseSchedule2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> adjacency = new HashMap<>();
        int[] indegree = new int[numCourses];

        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[1];
            int to = prerequisite[0];
            adjacency.computeIfAbsent(from, na -> new ArrayList<>()).add(to);
            indegree[to]++;
        }

        Deque<Integer> khansQueue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0)
                khansQueue.addLast(i);
        }

        List<Integer> courseOrder = new ArrayList<>();
        while (!khansQueue.isEmpty()) {

            int courseId = khansQueue.pollFirst();
            courseOrder.add(courseId);
            for (int connected : adjacency.getOrDefault(courseId, Collections.emptyList())) {
                indegree[connected]--;
                if (indegree[connected] == 0)
                    khansQueue.addLast(connected);
            }
        }

        if (courseOrder.size() == numCourses)
            return courseOrder.stream().mapToInt(i->i).toArray();
        return new int[0];
    }
}
