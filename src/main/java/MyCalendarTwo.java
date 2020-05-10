import java.util.*;

class MyCalendarTwo {

    final TreeMap<Integer, Integer> schedules = new TreeMap<>();

    public boolean add(int start, int end) {

        int meetings = 0;

        //insert
        schedules.merge(start, 1, Integer::sum);
        schedules.merge(end, -1, Integer::sum);

        //now check if causes issue
        for (Map.Entry<Integer, Integer> entry : schedules.entrySet()) {
            meetings += entry.getValue();
            if (meetings > 2) {
                schedules.merge(start, -1, Integer::sum);
                schedules.merge(end, +1, Integer::sum);
                break;
            }
            if (entry.getKey() > end)
                break;
        }
        return meetings <= 2;
    }

    public MyCalendarTwo() {

    }

    public boolean book(int start, int end) {

        return add(start, end);
    }

    public static void main(String[] args) {
        MyCalendarTwo calendarTwo = new MyCalendarTwo();

        int[] gg = new int[]{33, 44, 85, 95, 20, 37, 91, 100, 89, 100, 77, 87, 80, 95, 42, 61, 40, 50, 85, 99, 74, 91,
                70, 82, 5, 17, 77, 89, 16, 26, 21, 31, 30, 43, 96, 100, 27, 39, 44, 55, 15, 34, 85, 99, 74, 93, 84, 94,
                82, 94, 46, 65, 31, 49, 58, 73, 86, 99, 73, 84, 68, 80, 5, 18, 75, 87, 88, 100, 25, 41, 66, 79, 28, 41,
                60, 70, 62, 73, 16, 33};
        for (int i = 0; i < gg.length; i += 2) {
            System.out.println(calendarTwo.book(gg[i], gg[i + 1]));
        }
    }
}
