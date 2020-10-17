import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

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

        //sort by deadline
        courseList.sort(Comparator.<Course>comparingInt(x -> x.deadline)
                .thenComparingInt(x -> x.duration));

        int timeLine = 0;
        PriorityQueue<Course> taken = new PriorityQueue<>(Comparator.<Course>comparingInt(x -> x.duration).reversed());
        for (Course course : courseList) {

            timeLine += course.duration;
            taken.add(course);
            if (timeLine > course.deadline)
                timeLine -= taken.poll().duration;
        }

        return taken.size();
    }
}
