import java.util.*;

class My_Calendar_1 {

    static class Range {
        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        final int start, end;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Range range = (Range) o;
            return start == range.start &&
                    end == range.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }

        public boolean intersects(Range other) {
            return this.start <= other.start & this.end > other.start ||
                    this.start >= other.start && this.start < other.end;
        }
    }

    TreeSet<Range> schedule = new TreeSet<>(Comparator.<Range>comparingInt(x -> x.start)
            .thenComparingInt(x -> x.end));

    public My_Calendar_1() {

    }

    public boolean book(int start, int end) {

        Range toInsert = new Range(start, end);
        Range upper = schedule.ceiling(toInsert);
        if (upper != null && toInsert.intersects(upper))
            return false;
        Range lower = schedule.floor(toInsert);
        if (lower != null && toInsert.intersects(lower))
            return false;
        schedule.add(toInsert);
        return true;
    }
}
