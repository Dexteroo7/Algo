import java.util.HashMap;
import java.util.Map;

class LRUCache {

    private static final class DoubleLinkedList {

        //sentinel values
        KeyValue left = new KeyValue(-1, -1), right = new KeyValue(-1, -1);

        {
            left.next = right;
            right.prev = left;
        }

        public void add(KeyValue keyValue) {

            //add just behind right
            keyValue.prev = right.prev;
            keyValue.next = right;
            right.prev.next = keyValue;
            right.prev = keyValue;
        }

        public KeyValue popLeft() {

            KeyValue toPop = left.next;
            toPop.unlink();
            return toPop;
        }
    }

    private static final class KeyValue {

        final int key;
        int value;

        KeyValue next, prev;

        public KeyValue(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public void unlink() {

            //nothing to do
            if (next == null && prev == null)
                return;

            if (prev == null)
                next.prev = null;
            else if (next == null)
                prev.next = null;
            else {
                prev.next = next;
                next.prev = prev;
            }
            next = prev = null;
        }
    }


    final DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
    final Map<Integer, KeyValue> keyValueMap;
    final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.keyValueMap = new HashMap<>();
    }

    public int get(int key) {

        KeyValue keyValue = keyValueMap.get(key);
        if (keyValue == null)
            return -1;

        keyValue.unlink();
        doubleLinkedList.add(keyValue);

        return keyValue.value;
    }

    public void put(int key, int value) {

        KeyValue keyValue = keyValueMap.get(key);
        if (keyValue != null) {
            keyValue.value = value;
            keyValue.unlink();
        } else
            keyValue = new KeyValue(key, value);

        doubleLinkedList.add(keyValue);
        keyValueMap.put(key, keyValue);

        if (keyValueMap.size() > capacity) {
            KeyValue left = doubleLinkedList.popLeft();
            keyValueMap.remove(left.key);
        }
    }

    public static void main(String[] args) {

        LRUCache cache = new LRUCache(3 /* capacity */);

        cache.put(1, 1);
        System.out.println(cache.get(1));
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(1));
        cache.put(4, 4); //pop 2
        System.out.println(cache.get(1));

        cache.put(5, 5); //pop 3
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
        System.out.println(cache.get(5));
        System.out.println(cache.get(1));
    }
}
