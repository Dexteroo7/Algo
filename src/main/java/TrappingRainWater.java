//https://leetcode.com/problems/trapping-rain-water/
class TrappingRainWater {

    public int trap(int[] heights) {

        if (heights == null || heights.length < 3)
            return 0;

        int maxPoint = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] > heights[maxPoint])
                maxPoint = i;
        }

        int waterSaved = 0;

        //from left to max-point
        int currentSaved = 0;
        int prevMax = Integer.MIN_VALUE;
        for (int left = 0; left <= maxPoint; left++) {

            if (heights[left] < prevMax)
                currentSaved += (prevMax - heights[left]);
            else {
                waterSaved += currentSaved;
                prevMax = heights[left];
                currentSaved = 0;
            }
        }

        //from right to max-point
        currentSaved = 0;
        prevMax = Integer.MIN_VALUE;
        for (int right = heights.length - 1; right >= maxPoint; right--) {

            if (heights[right] < prevMax)
                currentSaved += (prevMax - heights[right]);
            else {
                waterSaved += currentSaved;
                prevMax = heights[right];
                currentSaved = 0;
            }
        }

        return waterSaved;
    }

    public static void main(String[] args) {

        System.out.println(new TrappingRainWater().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}
