import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        List<Integer> courseOrder = new ArrayList<>(numCourses);

        int[] inDegree = new int[numCourses];
        Map<Integer, List<Integer>> prerequisiteGraph = new HashMap<>(numCourses);
        for (int[] prerequisite : prerequisites) {
            prerequisiteGraph.computeIfAbsent(prerequisite[1], na -> new ArrayList<>()).add(prerequisite[0]);
            inDegree[prerequisite[0]]++;
        }

        Queue<Integer> khansQueue = new LinkedList<>();
        for (int id = 0; id < inDegree.length; id++) {
            if (inDegree[id] == 0)
                khansQueue.add(id);
        }

        while (!khansQueue.isEmpty()) {

            int courseId = khansQueue.poll();
            courseOrder.add(courseId);

            List<Integer> dependents = prerequisiteGraph.getOrDefault(courseId, Collections.emptyList());
            for (int id : dependents) {
                inDegree[id]--;
                if (inDegree[id] == 0)
                    khansQueue.add(id);
            }
        }

        if (courseOrder.size() < numCourses)
            return new int[0];

        return courseOrder.stream().mapToInt(x -> x).toArray();
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new Solution().findOrder(
                3, new int[][]{
                        new int[]{1, 0},
                        new int[]{1, 2},
                        new int[]{0, 1}
                })));
    }
}
