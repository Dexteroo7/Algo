//https://leetcode.com/problems/knight-dialer/
class KnightDialer {

    private static final int[][] JUMP_MAP = new int[][]{
            new int[]{4, 6},
            new int[]{6, 8},
            new int[]{7, 9},
            new int[]{4, 8},
            new int[]{0, 3, 9},
            new int[]{},
            new int[]{0, 1, 7},
            new int[]{2, 6},
            new int[]{1, 3},
            new int[]{2, 4}
    };

    public int knightDialer(int n) {

        if (n == 0)
            return 0;
        if (n == 1)
            return 10;

        int[] counts = new int[]{2, 2, 2, 2, 3, 0, 3, 2, 2, 2};

        for (int i = 2; i < n; i++) {

            int[] clone = new int[counts.length];
            for (int j = 0; j < clone.length; j++) {
                for (int to : JUMP_MAP[j]) {
                    clone[j] = (clone[j] + counts[to]) % (1000000007);
                }
            }

            counts = clone;
        }

        int sum = 0;
        for (int count : counts) {
            sum = (sum + count) % (1000000007);
        }

        return sum;
    }

    public static void main(String[] args) {

        System.out.println(new KnightDialer().knightDialer(1));
        System.out.println(new KnightDialer().knightDialer(2));
        System.out.println(new KnightDialer().knightDialer(3));
        System.out.println(new KnightDialer().knightDialer(4));
        System.out.println(new KnightDialer().knightDialer(5));
        System.out.println(new KnightDialer().knightDialer(3131));
    }
}
