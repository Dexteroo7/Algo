//import org.checkerframework.checker.units.qual.K;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//class LFUCache {
//
//    private static final class DoubleLinkedList {
//
//        KeyValue left = new KeyValue(-1, -1), right = new KeyValue(-1, -1);
//        {
//            left.next = right;
//            right.prev = left;
//        }
//
//        public KeyValue popLeft() {
//
//            KeyValue toPop = left.next;
//            if (toPop == right)
//                throw new IllegalStateException("Empty list");
//
//            left.next = toPop.next;
//            toPop.next.prev = left;
//
//            return toPop;
//        }
//    }
//
//    private static final class KeyValue {
//
//        final int key;
//
//        KeyValue prev, next;
//
//        int usageCounter = 0;
//        int value;
//
//        public KeyValue(int key, int value) {
//            this.key = key;
//            this.value = value;
//        }
//    }
//
//    final Map<Integer, KeyValue> keyValueMap;
//    final int capacity;
//
//    public LFUCache(int capacity) {
//        this.capacity = capacity;
//        this.keyValueMap = new HashMap<>();
//    }
//
//    public int get(int key) {
//
//        KeyValue keyValue = keyValueMap.get(key);
//        if (keyValue == null)
//            return -1;
//
//        keyValue.usageCounter++;
//
//        return keyValue.value;
//    }
//
//    public void put(int key, int value) {
//
//        KeyValue keyValue = keyValueMap.get(key);
//        if (keyValue != null) {
//            keyValue.value = value;
//            keyValue.unlink();
//            return;
//        }
//
//        keyValue = new KeyValue(key, value);
//
//        doubleLinkedList.add(keyValue);
//        keyValueMap.put(key, keyValue);
//
//        if (keyValueMap.size() > capacity) {
//            KeyValue left = usageList.remove(0);
//            keyValueMap.remove(left.key);
//        }
//    }
//
//    public void markUse(KeyValue keyValue) {
//
//
//    }
//
//    public static void main(String[] args) {
//
//        LRUCache cache = new LRUCache(3 /* capacity */);
//
//        cache.put(1, 1);
//        System.out.println(cache.get(1));
//        cache.put(2, 2);
//        System.out.println(cache.get(1));
//        cache.put(3, 3);
//        System.out.println(cache.get(1));
//        cache.put(4, 4); //pop 2
//        System.out.println(cache.get(1));
//
//        cache.put(5, 5); //pop 3
//        System.out.println(cache.get(1));
//        System.out.println(cache.get(2));
//        System.out.println(cache.get(3));
//        System.out.println(cache.get(4));
//        System.out.println(cache.get(5));
//        System.out.println(cache.get(1));
//    }
//}
