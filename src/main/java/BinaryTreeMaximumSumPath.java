import java.util.stream.IntStream;

class BinaryTreeMaximumSumPath {

    public class TreeNode {
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

    int maxSeen = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {

        maxPathSumRecur(root, root);
        return maxSeen;
    }

    public Integer maxPathSumRecur(TreeNode current, TreeNode root) {

        if (current == null)
            return null;

        Integer fromLeft = maxPathSumRecur(current.left, root);
        Integer fromRight = maxPathSumRecur(current.right, root);

        int max;
        if (fromLeft != null && fromRight != null) {

            //if path has root on this node
            max = IntStream.of(
                    current.val,
                    fromLeft + current.val,
                    fromRight + current.val,
                    fromLeft + fromRight + current.val)
                    .max()
                    .getAsInt();
            maxSeen = Integer.max(maxSeen, max);

            //for returning chose one of the paths
            max = IntStream.of(
                    current.val,
                    fromLeft + current.val,
                    fromRight + current.val)
                    .max()
                    .getAsInt();
        } else if (fromLeft == null && fromRight != null) {
            max = IntStream.of(
                    current.val,
                    fromRight + current.val)
                    .max()
                    .getAsInt();
        } else if (fromLeft != null && fromRight == null) {
            max = IntStream.of(
                    current.val,
                    fromLeft + current.val)
                    .max()
                    .getAsInt();
        } else
            max = current.val;
        maxSeen = Integer.max(maxSeen, max);
        return max;
    }
}
