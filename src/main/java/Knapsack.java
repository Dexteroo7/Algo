import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

private static final class Key {

	final int remainingCapacity, index;

	private Key(int remainingCapacity, int index) {
		this.remainingCapacity = remainingCapacity;
		this.index = index;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Key))
			return false;
		Key key = (Key) o;
		return remainingCapacity == key.remainingCapacity &&
				index == key.index;
	}

	@Override
	public int hashCode() {

		return Objects.hash(remainingCapacity, index);
	}
}

private static final Map<Key, Integer> MEMO = new HashMap<>();

private static int maxValueInSack(int capacity, int[] value, int[] weight) {

	if (capacity == 0 || value.length == 0 || weight.length == 0)
		return 0;

	return maxValueInSack(capacity, value, weight, 0);
}

private static int maxValueInSack(int remainingCapacity, int[] value, int[] weight, int index) {

	//nothing more to do
	if (remainingCapacity == 0)
		return 0;
	if (index == value.length)
		return 0;

	Key key = new Key(remainingCapacity, index);
	Integer fromMemo = MEMO.get(key);
	if (fromMemo != null)
		return fromMemo;

	int take, notTake;

	//take the item if possible
	if (remainingCapacity >= weight[index])
		take = value[index] + maxValueInSack(remainingCapacity - weight[index], value, weight, index + 1);
	else
		take = 0;
	//do not take the item
	notTake = maxValueInSack(remainingCapacity, value, weight, index + 1);

	//return max value
	int max = Integer.max(take, notTake);
	MEMO.put(key, max);
	return max;
}
