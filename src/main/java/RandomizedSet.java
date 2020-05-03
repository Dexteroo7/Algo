import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class RandomizedSet {

    final Map<Integer, Integer> indexMap = new HashMap<>();
    final List items = new ArrayList(1);
    int size = 0;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {


    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {

        Integer index = indexMap.get(val);
        if (index != null)
            return false;
        //set it at the last position
        if (size == 0 || items.isEmpty()) {
            items.clear();
            items.add(val);
            indexMap.put(val, 0);
        } else if (items.size() == size) {
            items.add(val);
            indexMap.put(val, size);
        } else {
            items.set(size, val);
            indexMap.put(val, size);
        }
        size++;
//        System.out.println(items);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {

        Integer index = indexMap.get(val);
        if (index == null)
            return false;
        Object lastItem = items.get(size - 1);
        items.set(index, lastItem);
        indexMap.put((Integer) lastItem, index);
        size--;
        indexMap.remove(val);
//        System.out.println(items);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        int index = ThreadLocalRandom.current().nextInt(0, size);
        return (int) items.get(index);
    }

//    public static void main(String[] args) {
//
//        RandomizedSet randomizedSet = new RandomizedSet();
//        System.out.println(randomizedSet.insert(0));
//        System.out.println(randomizedSet.insert(1));
//        System.out.println(randomizedSet.remove(0));
//        System.out.println(randomizedSet.insert(2));
//        System.out.println(randomizedSet.remove(1));
//        System.out.println(randomizedSet.getRandom());
////        randomizedSet.insert(1);
////        randomizedSet.remove(2);
////        randomizedSet.insert(2);
////        randomizedSet.getRandom();
////        randomizedSet.remove(1);
////        randomizedSet.insert(2);
////        randomizedSet.getRandom();
////
////        System.out.println(randomizedSet.remove(0));
////        System.out.println(randomizedSet.remove(0));
////        System.out.println(randomizedSet.insert(0));
////        System.out.println(randomizedSet.getRandom());
////        System.out.println(randomizedSet.remove(0));
////        System.out.println(randomizedSet.insert(0));
//    }
}
