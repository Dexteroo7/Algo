import java.util.Stack;

public class LargestRectangle {

    private static final class Block {
        final int height;
        int startIndex;

        public Block(int height, int startIndex) {
            this.height = height;
            this.startIndex = startIndex;
        }
    }

    public int largestRectangleArea(int[] heights) {

        Stack<Block> heightsSeen = new Stack<>();
        int maxArea = 0;

        for (int index = 0; index < heights.length; index++) {

            int currentHeight = heights[index];

            //first block
            if (heightsSeen.isEmpty()) {
                heightsSeen.push(new Block(currentHeight, index));
            }
            //same height as previous block
            else if (currentHeight == heightsSeen.peek().height) {
            }
            //greater height than previous block
            else if (currentHeight > heightsSeen.peek().height) {
                heightsSeen.push(new Block(currentHeight, index));
            }
            //less height than previous block
            else {
                //pop
                int startIndex = 0;
                while (!heightsSeen.isEmpty() && heightsSeen.peek().height > currentHeight) {

                    Block currentBlock = heightsSeen.pop();
                    int length = index - currentBlock.startIndex;
                    int area = length * currentBlock.height;
                    maxArea = Integer.max(maxArea, area);
                    startIndex = currentBlock.startIndex;
                }
                if (heightsSeen.isEmpty())
                    heightsSeen.push(new Block(currentHeight, 0));
                else
                    heightsSeen.push(new Block(currentHeight, startIndex));
            }
        }

        while (!heightsSeen.isEmpty()) {
            Block currentBlock = heightsSeen.pop();
            int length = heights.length - currentBlock.startIndex;
            int area = length * currentBlock.height;
            maxArea = Integer.max(maxArea, area);
        }

        return maxArea;
    }

    public static void main(String[] args) {

        System.out.println(new LargestRectangle().largestRectangleArea(new int[]{2, 1, 2}));
        System.out.println(new LargestRectangle().largestRectangleArea(new int[]{3, 2, 1, 0, 1, 2, 3, 4, 4}));
        System.out.println(new LargestRectangle().largestRectangleArea(new int[]{1}));
        System.out.println(new LargestRectangle().largestRectangleArea(new int[]{1, 2}));
        System.out.println(new LargestRectangle().largestRectangleArea(new int[]{1, 2, 4}));
        System.out.println(new LargestRectangle().largestRectangleArea(new int[]{1, 2, 2}));
        System.out.println(new LargestRectangle().largestRectangleArea(new int[]{1, 2, 2, 1, 1, 1, 1, 1}));
        System.out.println(new LargestRectangle().largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }
}
