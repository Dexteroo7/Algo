class FirstMissingPositive {

    public static int firstMissingPositive(int[] nums) {

        if (nums == null || nums.length == 0)
            return 1;

        for (int i = 1; i <= nums.length; i++) {

            int current = nums[i - 1];
            //ignore out of bounds
            if (current <= 0 || current > nums.length) {
                continue;
            }
            //ignore if already in place
            if (current == i)
                continue;

            int temp = nums[current - 1];
            nums[current - 1] = current;
            nums[i - 1] = temp;
            if (temp != current)
                i--;
        }

        int toReturn = nums.length + 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1)
                return i + 1;
        }

        return toReturn;
    }

    public static void main(String[] args) {

        System.out.println(firstMissingPositive(new int[]{2, 1}));
        System.out.println(firstMissingPositive(new int[]{1, 1}));
        System.out.println(firstMissingPositive(new int[]{10, 2, -1}));
        System.out.println(firstMissingPositive(new int[]{-1}));
        System.out.println(firstMissingPositive(new int[]{0}));
        System.out.println(firstMissingPositive(new int[]{1}));
        System.out.println(firstMissingPositive(new int[]{2}));
        System.out.println(firstMissingPositive(new int[]{-1, -1}));
        System.out.println(firstMissingPositive(new int[]{-1, 0, 1}));
        System.out.println(firstMissingPositive(new int[]{-1, 0, 2}));
        System.out.println(firstMissingPositive(new int[]{1, 2, 0}));
        System.out.println(firstMissingPositive(new int[]{3, 4, -1, 1}));
        System.out.println(firstMissingPositive(new int[]{7, 8, 9, 11, 12}));
    }
}
