import java.util.*;

class VerticalOrderTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static final class Point {
        final int column, row, value;

        public Point(int column, int row, int value) {
            this.column = column;
            this.row = row;
            this.value = value;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {

        List<Point> points = new ArrayList<>();
        List<List<Integer>> toReturn = new ArrayList<>();

        fillPoints(root, new int[]{0, 1000}, points);

        points.sort(Comparator.<Point>comparingInt(x -> x.column).thenComparingInt(x -> x.row).thenComparingInt(x -> x.value));
        int column = points.get(0).column;
        int row = points.get(0).row;
        List<Integer> toAdd = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {

            Point point = points.get(i);
            if (point.row != row || point.column != column) {
                temp.sort(Comparator.naturalOrder());
                toAdd.addAll(temp);
                temp = new ArrayList<>();
                row = point.row;
            }
            if (point.column != column) {
                toReturn.add(toAdd);
                toAdd = new ArrayList<>();
                temp = new ArrayList<>();
                column = point.column;
                row = point.row;
            }
            temp.add(point.value);
        }

        temp.sort(Comparator.naturalOrder());
        toAdd.addAll(temp);
        toReturn.add(toAdd);

        return toReturn;
    }

    public void fillPoints(TreeNode current, int[] currentPosition, List<Point> points) {

        if (current == null)
            return;
        points.add(new Point(currentPosition[1], currentPosition[0], current.val));

        //adjust for left
        currentPosition[0]++;
        currentPosition[1]--;
        fillPoints(current.left, currentPosition, points);

        //adjust for right
        currentPosition[1] += 2;
        fillPoints(current.right, currentPosition, points);

        //restore
        currentPosition[0]--;
        currentPosition[1]--;
    }
}
