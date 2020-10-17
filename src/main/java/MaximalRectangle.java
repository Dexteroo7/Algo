import java.util.Stack;

class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {

        if (matrix == null || matrix.length == 0)
            return 0;

        int[] heights = new int[matrix[0].length];
        int maxArea = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                if (matrix[i][j] == '0')
                    heights[j] = 0;
                else
                    heights[j] += 1;
            }
            int currentMax = maxAreaHistogram(heights);
            maxArea = Integer.max(maxArea, currentMax);
        }

        return maxArea;
    }

    public int maxAreaHistogram(int[] heights) {

        if (heights.length == 1)
            return heights[0];

        Stack<Integer> stack = new Stack<>();
        int n = heights.length, max = 0, curr;
        for (int i = 0; i <= n; ++i) {

            curr = i == n ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] >= curr) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Integer.max(max, height * width);
            }
            stack.push(i);
        }
        return max;
    }

    public static void main(String[] args) {

//        System.out.println(new Solution().maximalRectangle(new char[][]{
//                new char[]{'1', '0', '1', '0', '0'},
//                new char[]{'1', '0', '1', '1', '1'},
//                new char[]{'1', '1', '1', '1', '1'},
//                new char[]{'1', '0', '0', '1', '0'}}));

        System.out.println(new MaximalRectangle().maxAreaHistogram(
                new int[]{3, 1, 3, 2, 2}));
    }
}
