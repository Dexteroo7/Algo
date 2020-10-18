import java.util.TreeMap;

class HitCounter {

    TreeMap<Integer, Integer> counter = new TreeMap<>();
    /**
     * Initialize your data structure here.
     */
    public HitCounter() {

    }

    /**
     * Record a hit.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        counter.merge(timestamp, 1, Integer::sum);
        counter.headMap(timestamp-300, true).keySet().clear();
    }

    /**
     * Return the number of hits in the past 5 minutes.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {

        counter.headMap(timestamp-300, true).keySet().clear();
        return counter.values().stream().mapToInt(x->x).sum();
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
