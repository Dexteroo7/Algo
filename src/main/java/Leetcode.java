//import java.util.Arrays;
//
//public class Leetcode {
//
////    public static int[] dailyTemperatures(int[] temps) {
////
////        int[] tempMemo = new int[70];
////
////        for (int i = temps.length - 1; i >= 0; i--) {
////            int temp = temps[i] - 30;
////            int nearestWarmest = tempMemo[temp];
////            temps[i] = nearestWarmest == 0 ? 0 : nearestWarmest - i;
////            updateWarmestTemp(tempMemo, i, temp);
////        }
////        return temps;
////    }
////
////    public static void updateWarmestTemp(int[] tempMemo, int index, int temp) {
////
////        for (int i = 0; i < temp; i++) {
////            tempMemo[i] = index;
////        }
////    }
//
//    public static int[] dailyTemperatures(int[] temps) {
//
//        for (int i = temps.length - 1; i >= 0; i--) {
//            int warmerIndex = lookForWarmer(temps, temps, i + 1, temps[i]);
//            if (warmerIndex == 0)
//                temps[i] = 0;
//            else
//                temps[i] = warmerIndex - i;
//        }
//        return warmerDays;
//    }
//
//    //filling from behind
//    public static int lookForWarmer(int[] temps, int[] days, int index, int currentTemp) {
//
//        //no warmer possible
//        if (index >= temps.length || index >= days.length)
//            return 0;
//
//        //this is the index
//        int temp = temps[index];
//        if (temp > currentTemp)
//            return index;
//
//        //no warmer possible
//        if (days[index] == 0)
//            return 0;
//
//        //next possible warmer
//        index += days[index];
//        return lookForWarmer(temps, days, index, currentTemp);
//    }
//
//    public static void main(String[] args) {
//
//        System.out.println(Arrays.toString(dailyTemperatures(new int[]{31, 30, 31, 30, 45})));
//    }
//}
