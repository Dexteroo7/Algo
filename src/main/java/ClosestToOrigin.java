import java.util.Comparator;
import java.util.PriorityQueue;

//https://leetcode.com/problems/k-closest-points-to-origin/
class ClosestToOrigin {


    public int[][] kClosest(int[][] points, int k) {

        PriorityQueue<int[]> closest = new PriorityQueue<>(Comparator.comparingInt(this::cSquare).reversed());
        for (int i = 0; i < points.length; i++) {
            closest.add(points[i]);
            if (closest.size() > k)
                closest.poll();
        }

        int[][] toReturn = new int[k][];
        for (int i = 0; i < k; i++) {
            toReturn[i] = closest.poll();
        }

        return toReturn;
    }

    public int cSquare(int[] point) {

        return (point[0] * point[0]) + (point[1] * point[1]);
    }
}
