import java.util.Deque;
import java.util.LinkedList;

class ValidBST {

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

    private static final class Compare {

        TreeNode treeNode;

        public Compare(TreeNode root) {
            this.treeNode = root;
        }

        public Compare(TreeNode root, long lessThan, long greaterThan) {
            this.treeNode = root;
            this.lessThan = lessThan;
            this.greaterThan = greaterThan;
        }

        private long lessThan = Long.MAX_VALUE, greaterThan = Long.MIN_VALUE;

        void lessThan(long value) {
            lessThan = Long.min(lessThan, value);
        }

        void greaterThan(long value) {
            greaterThan = Long.max(greaterThan, value);
        }

        boolean validate() {
            return treeNode.val <= lessThan && treeNode.val >= greaterThan;
        }
    }

    public boolean isValidBST(TreeNode root) {

        if (root == null)
            return true;
        if (root.left == null && root.right == null)
            return true;

        Deque<Compare> bfsQueue = new LinkedList<>();
        bfsQueue.addLast(new Compare(root));

        while (!bfsQueue.isEmpty()) {

            Compare compare = bfsQueue.pollFirst();
            if (!compare.validate())
                return false;
            if (compare.treeNode == null)
                continue;
            TreeNode current = compare.treeNode;
            Compare leftCompare = new Compare(current.left, compare.lessThan, compare.greaterThan);
            Compare rightCompare = new Compare(current.right, compare.lessThan, compare.greaterThan);
            leftCompare.lessThan(current.val);
            rightCompare.greaterThan(current.val);
            bfsQueue.addLast(leftCompare);
            bfsQueue.addLast(rightCompare);
        }

        return true;
    }
}
