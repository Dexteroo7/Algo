import java.util.*;

/**
 * Definition for a binary tree node.
 */
public class Codec {

    static char NULL = Character.MAX_VALUE;
    static char LEAF = Character.MAX_VALUE - 1;
    static TreeNode LEAF_SENTINEL = new TreeNode(Character.MAX_VALUE - 1);

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public String serialize(TreeNode root) {
        return serializeUsingLevel(root);
    }

    public TreeNode deserialize(String data) {
        return deserializeUsingLevel(data);
    }

//    public static String serializeUsingPreOrder(TreeNode root) {
//
//        if (root == null) {
//            return "";
//        }
//
//        List<Integer> preOrderWithMarkers = new ArrayList<>();
//        Stack<TreeNode> stack = new Stack<>();
//        stack.push(root);
//        while (!stack.isEmpty()) {
//
//            TreeNode current = stack.pop();
//            if (current == null) {
//                preOrderWithMarkers.add(null);
//                continue;
//            }
//
//            preOrderWithMarkers.add(current.val);
//            stack.push(current.right);
//            stack.push(current.left);
//        }
//
//        char[] toSerialize = new char[preOrderWithMarkers.size()];
//        for (int i = 0; i < preOrderWithMarkers.size(); i++) {
//            toSerialize[i] = mapToChar(preOrderWithMarkers.get(i));
//        }
//
//        return new String(toSerialize);
//    }
//
//    public static TreeNode deserializeUsingPreOrder(String data) {
//
//        if (data == null || data.isEmpty())
//            return null;
//
//        char[] charArray = data.toCharArray();
//        Integer rootValue = mapFromChar(charArray[0]);
//        if (rootValue == null)
//            return null;
//        TreeNode root = new TreeNode(rootValue);
//        preOrderHelper(root, charArray, new int[]{1});
//        return root;
//    }

//    public static void preOrderHelper(TreeNode current, char[] charArray, int[] index) {
//
//        Integer value = mapFromChar(charArray[index[0]++]);
//        if (value != null) {
//            current.left = new TreeNode(value);
//            preOrderHelper(current.left, charArray, index);
//        }
//
//        value = mapFromChar(charArray[index[0]++]);
//        if (value != null) {
//            current.right = new TreeNode(value);
//            preOrderHelper(current.right, charArray, index);
//        }
//    }

    // Encodes a tree to a single string.
    public static String serializeUsingLevel(TreeNode root) {

        if (root == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {

            TreeNode current = queue.poll();
            if (current == LEAF_SENTINEL) {
                builder.append(LEAF);
                continue;
            }
            if (current == null) {
                builder.append(NULL);
                continue;
            }

            builder.append(mapToChar(current.val));
            if (current.left == null && current.right == null) {
                queue.add(LEAF_SENTINEL);
            } else {
                queue.add(current.left);
                queue.add(current.right);
            }
        }

        return builder.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserializeUsingLevel(String data) {
        if (data == null || data.isEmpty())
            return null;

        Integer rootValue = mapFromChar(data.charAt(0));
        if (rootValue == null)
            return null;
        TreeNode root = new TreeNode(rootValue);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int arrayIndex = 1;
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            char value = data.charAt(arrayIndex++);
            if (value == LEAF)
                continue;
            if (value != NULL) {
                current.left = new TreeNode(mapFromChar(value));
                queue.add(current.left);
            }
            value = data.charAt(arrayIndex++);
            if (value != NULL) {
                current.right = new TreeNode(mapFromChar(value));
                queue.add(current.right);
            }
        }
        return root;
    }

    private static char mapToChar(Integer toMap) {

        //value can range from -1000 to +1000
        return toMap == null ? NULL : (char) (toMap + 1000);
    }

    private static Integer mapFromChar(char toMap) {

        if (toMap == NULL)
            return null;
        else
            return toMap - 1000;
    }

    public static void main(String[] args) {

        TreeNode node = new TreeNode(10);
        node.left = new TreeNode(1);
        node.left.right = new TreeNode(2);
        node.left.left = new TreeNode(3);

        String s = serializeUsingLevel(node);
        TreeNode node1 = deserializeUsingLevel(s);
        node1.equals(node);
    }
}