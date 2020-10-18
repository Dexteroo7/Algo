import java.util.*;

//https://leetcode.com/problems/meeting-rooms-ii/
class MeetingRooms2 {
    public int minMeetingRooms(int[][] intervals) {

        Arrays.sort(intervals, Comparator.<int[]>comparingInt(x -> x[0]).thenComparingInt(x -> x[1]));

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int[] interval : intervals) {
            min = Integer.min(min, interval[0]);
            max = Integer.max(max, interval[1]);
        }

        int[] scheduler = new int[max - min + 1];

        for (int[] interval : intervals) {

            scheduler[interval[0] - min]++;
            scheduler[interval[1] - min]--;
        }

        int roomsRequired = 0;
        int consecutiveMeetings = 0;
        for (int item : scheduler) {
            consecutiveMeetings += item;
            roomsRequired = Integer.max(roomsRequired, consecutiveMeetings);
        }

        return roomsRequired;
    }
}
