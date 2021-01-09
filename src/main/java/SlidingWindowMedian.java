import java.util.Arrays;

class SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {

        if (k == 1) {
            double[] toReturn = new double[nums.length];
            for (int i = 0; i < nums.length; i++) {
                toReturn[i] = nums[i];
            }
            return toReturn;
        }

        int[] movingWindow = new int[k];
        double[] toReturn = new double[nums.length - k + 1];
        System.arraycopy(nums, 0, movingWindow, 0, k);
        Arrays.sort(movingWindow);
        toReturn[0] = getMedian(movingWindow);

        for (int i = k, left = 0, toReturnIndex = 1; i < nums.length; i++, left++, toReturnIndex++) {

            //remove and shrink array
            int toRemove = nums[left];
            int removeIndex = Arrays.binarySearch(movingWindow, toRemove);
            for (int j = removeIndex + 1; j < movingWindow.length; j++) {
                movingWindow[j - 1] = movingWindow[j];
            }

            //add and expand back
            int toAdd = nums[i];
            int addIndex = Arrays.binarySearch(movingWindow, 0, movingWindow.length - 1, toAdd);
            if (addIndex < 0)
                addIndex = -(addIndex + 1);
            for (int j = movingWindow.length - 1; j > addIndex; j--) {
                movingWindow[j] = movingWindow[j - 1];
            }
            movingWindow[addIndex] = toAdd;
            toReturn[toReturnIndex] = getMedian(movingWindow);
        }

        return toReturn;
    }

    public double getMedian(int[] sorted) {

        if (sorted.length % 2 == 0) {
            double sum = (double) sorted[sorted.length / 2] + (double) sorted[(sorted.length / 2) - 1];
            return sum / 2.0;
        } else
            return sorted[sorted.length / 2];
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new SlidingWindowMedian().medianSlidingWindow(new int[]{2147483647, 2147483647}, 2)));
    }
}
