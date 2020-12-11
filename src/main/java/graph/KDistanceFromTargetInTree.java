package graph;

import java.util.*;
import java.util.stream.Collectors;

class KDistanceFromTargetInTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {

        Map<TreeNode, List<TreeNode>> asGraph = new IdentityHashMap<>();

        Queue<TreeNode> traversalQueue = new LinkedList<>();
        traversalQueue.add(root);

        while (!traversalQueue.isEmpty()) {

            TreeNode current = traversalQueue.poll();
            if (current.left != null) {

                asGraph.computeIfAbsent(current, na -> new ArrayList<>()).add(current.left);
                asGraph.computeIfAbsent(current.left, na -> new ArrayList<>()).add(current);
                traversalQueue.add(current.left);
            } if (current.right != null) {

                asGraph.computeIfAbsent(current, na -> new ArrayList<>()).add(current.right);
                asGraph.computeIfAbsent(current.right, na -> new ArrayList<>()).add(current);
                traversalQueue.add(current.right);
            }
        }

        Map<TreeNode, Integer> distanceMap = new IdentityHashMap<>();
        Map<TreeNode, Boolean> visited = new IdentityHashMap<>();

        traversalQueue.add(target);
        distanceMap.put(target, 0);
        while (!traversalQueue.isEmpty()) {

            TreeNode current = traversalQueue.poll();
            if (visited.getOrDefault(current, false))
                continue;
            visited.put(current, true);

            int distance = distanceMap.get(current);
            if (distance > K)
                continue;

            for (TreeNode node : asGraph.getOrDefault(current, Collections.emptyList())) {
                distanceMap.merge(node, distance + 1, Integer::min);
                traversalQueue.add(node);
            }
        }

        return distanceMap.entrySet().stream().filter(x->x.getValue() == K).map(x->x.getKey().val).collect(Collectors.toList());
    }
}
