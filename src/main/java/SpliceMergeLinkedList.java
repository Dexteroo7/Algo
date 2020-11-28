class SpliceMergeLinkedList {


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
        int prevMax = Integer.MIN_VALUE;
        for (int left = 0; left <= maxPoint; left++) {

            if (heights[left] < prevMax)
                waterSaved += (prevMax - heights[left]);
            else {
                prevMax = heights[left];
            }
        }

        //from right to max-point
        prevMax = Integer.MIN_VALUE;
        for (int right = heights.length - 1; right >= maxPoint; right--) {

            if (heights[right] < prevMax)
                waterSaved += (prevMax - heights[right]);
            else {
                prevMax = heights[right];
            }
        }

        return waterSaved;
    }
}
