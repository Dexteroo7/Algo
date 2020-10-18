import java.util.Comparator;
import java.util.PriorityQueue;

//https://leetcode.com/problems/find-median-from-data-stream/submissions/
class MedianFinder {

    /**
     * initialize your data structure here.
     */

    PriorityQueue<Integer> leftHalf = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> rightHalf = new PriorityQueue<>(Comparator.naturalOrder());

    public MedianFinder() {
    }

    public void addNum(int num) {

        if (leftHalf.isEmpty())
            leftHalf.add(num);
        else if (leftHalf.peek() >= num)
            leftHalf.add(num);
        else
            rightHalf.add(num);

        if (leftHalf.size() > rightHalf.size() + 1)
            rightHalf.add(leftHalf.poll());
        if (rightHalf.size() > leftHalf.size() + 1)
            leftHalf.add(rightHalf.poll());
    }

    public double findMedian() {

        if (leftHalf.isEmpty() && rightHalf.isEmpty())
            throw new IllegalArgumentException();
        if (leftHalf.isEmpty())
            return rightHalf.peek();
        if (rightHalf.isEmpty())
            return leftHalf.peek();

        int fromLeft = leftHalf.peek();
        int fromRight = rightHalf.peek();
        if ((leftHalf.size() + rightHalf.size()) % 2 == 0)
            return (fromLeft + fromRight) / 2.0;
        else
            return leftHalf.size() > rightHalf.size() ? fromLeft : fromRight;
    }

    public static void main(String[] args) {

        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(4);
        System.out.println(medianFinder.findMedian());
    }
}
