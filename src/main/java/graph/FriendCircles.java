package graph;

import java.util.*;

class FriendCircles {
    public int findCircleNum(int[][] M) {

        int circles = 0;
        BitSet visited = new BitSet(M.length);

        for (int i = 0; i < M.length; i++) {

            if (visited.get(i))
                continue;

            Queue<Integer> traversalQueue = new LinkedList<>();
            traversalQueue.add(i);
            while (!traversalQueue.isEmpty()) {

                int id = traversalQueue.poll();
                if (visited.get(id))
                    continue;
                visited.set(id);
                for (int j = 0; j < M[id].length; j++) {
                    if (M[id][j] == 1 && !visited.get(j))
                        traversalQueue.add(j);
                }
            }

            circles++;
        }

        return circles;
    }
}
