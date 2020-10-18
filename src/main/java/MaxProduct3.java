import java.util.Comparator;
import java.util.PriorityQueue;

//https://leetcode.com/problems/maximum-product-of-three-numbers/
class MaxProduct3 {
    public int maximumProduct(int[] nums) {

        PriorityQueue<Integer> highest3 = new PriorityQueue<>();
        PriorityQueue<Integer> lowest2 = new PriorityQueue<>(Comparator.reverseOrder());

        for (int num : nums) {
            if (highest3.size() < 3)
                highest3.add(num);
            else if (highest3.peek() < num) {
                highest3.poll();
                highest3.add(num);
            }
            if (lowest2.size() < 2)
                lowest2.add(num);
            else if (lowest2.peek() > num) {
                lowest2.poll();
                lowest2.add(num);
            }
        }

        int highest;
        int a = highest3.poll() * highest3.poll() * (highest = highest3.poll());
        int b = lowest2.poll() * lowest2.poll() * highest;

        return Integer.max(a, b);
    }

    public static void main(String[] args) {

        new MaxProduct3().maximumProduct(new int[]{0, 1, -1, 3});
    }
}
