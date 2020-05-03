//import com.google.common.io.Files;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.charset.Charset;
//import java.util.Arrays;
//import java.util.List;
//import java.util.function.Function;
//import java.util.function.ToIntFunction;
//import java.util.function.ToLongFunction;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//class TODOMedia {
//
//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//
//        if (nums1.length == 0)
//            return findMedian(nums2);
//        if (nums2.length == 0)
//            return findMedian(nums1);
//
//        int totalLength = nums1.length + nums2.length;
//        boolean isActual = totalLength % 2 != 0;
//        int medianPartitionSize = totalLength / 2;
//
//        //assuming median in in nums1
//        int possibleMedianAt = 0;
//        int nextPossibleMedianAt = -1;
//        while (possibleMedianAt != nextPossibleMedianAt) {
//
//            int medianValue = nums1[possibleMedianAt];
//
//            int right = nums1.length - possibleMedianAt - 1;
//            if (right < medianPartitionSize) {
//
//                //we need (medianPartitionSize - right) items from other on the right
//                int requiredFromOther = (medianPartitionSize - right);
//                if (requiredFromOther > nums2.length) {
//                    //move to left
//                }
//                int valueFromOther = nums2[nums2.length - requiredFromOther - 1];
//                if (valueFromOther)
//                    if (valueFromOther)
//            } else {
//
//                //if left > medianPartitionSize, we already screwed
//                //-1 indicates we need to decrease index
//                return -1;
//            }
//        }
//    }
//
//    public double findMedian(int[] array) {
//
//        if (array.length == 0)
//            return 0;
//
//
//    }
//
//    public static void main(String[] args) throws IOException {
//
//        List<String> lines = Files.readLines(new File("shit.txt"), Charset.defaultCharset());
//        double[] blah = lines.stream().filter(x -> x.contains("Task with schema"))
//                .mapToLong(value -> {
//                    String[] split = value.split("(\\D+)");
//                    return Long.parseLong(split[1]);
//                }).mapToDouble(x -> x / 1000000000D).toArray();
//        System.out.println(Arrays.toString(blah));
//    }
//}
