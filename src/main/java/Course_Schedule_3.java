import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Course_Schedule_3 {

    static class Course {

        final int duration, deadline;

        Course(int duration, int deadline) {
            this.duration = duration;
            this.deadline = deadline;
        }
    }

    public int scheduleCourse(int[][] courses) {

        List<Course> courseList = new ArrayList<>(courses.length);
        for (int[] cours : courses) {
            courseList.add(new Course(cours[0], cours[1]));
        }

        courseList.sort(Comparator.comparingInt(x -> x.deadline));
        return scheduleCourse(courseList, 0, 0);
    }

    public int scheduleCourse(List<Course> courses, int index, int timeLine) {

        if (index >= courses.size())
            return 0;

        Course current = courses.get(index);
        if (timeLine + current.duration > current.deadline)
            return scheduleCourse(courses, index + 1, timeLine);

        //either do this course, or dont do this course
        int ansDo = 1 + scheduleCourse(courses, index + 1, timeLine + current.duration);
        int ansDont = scheduleCourse(courses, index + 1, timeLine);

        return Integer.max(ansDo, ansDont);
    }

    public static void main(String[] args) {

        Course_Schedule_3 solution = new Course_Schedule_3();
        System.out.println(solution.scheduleCourse(new int[][]{new int[]{2, 5},
                new int[]{2, 19},
                new int[]{1, 8},
                new int[]{1, 3}}));
    }
}
