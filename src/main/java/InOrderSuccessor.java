class InOrderSuccessor {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Keeper {

        final int lookingFor;
        TreeNode last;

        public Keeper(int lookingFor) {
            this.lookingFor = lookingFor;
        }

        public TreeNode add(TreeNode node) {

            if (last == null) {
                last = node;
                return null;
            }
            if (last.val == lookingFor)
                return node;
            last = node;
            return null;
        }
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {

        Keeper keeper = new Keeper(p.val);
        return inOrder(root, keeper);
    }

    public TreeNode inOrder(TreeNode node, Keeper keeper) {

        if (node == null)
            return null;

        TreeNode fromLeft = inOrder(node.left, keeper);
        if (fromLeft != null)
            return fromLeft;
        TreeNode add = keeper.add(node);
        if (add != null)
            return add;
        return inOrder(node.right, keeper);
    }
}
