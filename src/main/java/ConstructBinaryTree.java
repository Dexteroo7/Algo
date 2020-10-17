//https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
class ConstructBinaryTree {
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

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        return build(preorder, inorder, new int[]{0}, 0, inorder.length);
    }

    public TreeNode build(int[] preorder, int[] inorder, int[] preIndex, int inSliceStart, int inSliceEnd) {

        if (inSliceStart == inSliceEnd)
            return null;

        TreeNode node = new TreeNode();
        node.val = preorder[preIndex[0]];
        preIndex[0]++;
        int slicePoint = findIn(inorder, node.val, inSliceStart, inSliceEnd);
        node.left = build(preorder, inorder, preIndex, inSliceStart, slicePoint);
        node.right = build(preorder, inorder, preIndex, slicePoint + 1, inSliceEnd);
        return node;
    }

    public int findIn(int[] inorder, int toFind, int inSliceStart, int inSliceEnd) {

        for (int i = inSliceStart; i < inSliceEnd; i++) {
            if (toFind == inorder[i])
                return i;
        }

        throw new IllegalArgumentException();
    }
}
