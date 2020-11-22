import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/reorder-data-in-log-files
class ReorderLogFiles {
    public String[] reorderLogFiles(String[] logs) {

        Map<String, Integer> originalOrder = new HashMap<>();
        for (int i = 0; i < logs.length; i++) {
            originalOrder.put(logs[i], i);
        }

        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String a, String b) {

                boolean isALetter = isLetter(a);
                boolean isBLetter = isLetter(b);
                if (isALetter && isBLetter)
                    return compareLetterLog(a, b);
                if (!isALetter && !isBLetter)
                    return compareDigitLog(a, b, originalOrder);

                if (isALetter && !isBLetter)
                    return -1;

                if (!isALetter && isBLetter)
                    return 1;

                throw new IllegalStateException("bad logs preset");
            }
        };

        Arrays.sort(logs, comparator);
        return logs;
    }

    public boolean isLetter(String log) {

        return Character.isLetter(log.charAt(log.length() - 1));
    }

    public int compareLetterLog(String a, String b) {

        CharSequence ignoringIdentifierA = a.subSequence(a.indexOf(' ') + 1, a.length());
        CharSequence ignoringIdentifierB = b.subSequence(b.indexOf(' ') + 1, b.length());

        int compare = CharSequence.compare(ignoringIdentifierA, ignoringIdentifierB);
        if (compare == 0) {
            //maybe optimise
            return CharSequence.compare(a, b);
        }
        return compare;
    }

    public int compareDigitLog(String a, String b, Map<String, Integer> originalOrder) {

        return Integer.compare(originalOrder.get(a), originalOrder.get(b));
    }
}
