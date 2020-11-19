import java.util.Arrays;

class TransformIntoAnotherString {

    public static final char NULL = '@';

    public boolean canConvert(String str1, String str2) {

        if (str1.equals(str2))
            return true;
        if (str1.length() != str2.length())
            return false;

        //simple check
        char[] conversion = new char['z' + 1];
        Arrays.fill(conversion, NULL);
        for (int i = 0; i < str1.length(); i++) {

            char current = str1.charAt(i);
            char required = str2.charAt(i);
            if (current == required) {
                conversion[current] = required;
            } else if (conversion[current] == NULL) {
                conversion[current] = required;
            } else if (conversion[current] != required)
                return false;
        }

        //we can break cycles by a new character
        for (int i = 'a'; i <= 'z'; i++) {
            if (conversion[i] == NULL)
                return true;
        }

        int[] inDegree = new int[conversion.length];
        for (int index = 'a'; index <= 'z'; index++) {
            inDegree[conversion[index]]++;
        }

        //if any indegree > 1 we can convert
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] > 1)
                return true;
        }

        return false;
    }

    public static void main(String[] args) {

        System.out.println(new TransformIntoAnotherString().canConvert("abcdefghijklmnopqrstuvwxyz", "bcdefghijklmnopqrstuvwxyzq"));
        System.out.println(new TransformIntoAnotherString().canConvert("abcdefghijklmnopqrstuvwxyz", "bcaaefghijklmnopqrstuvwxyz"));
        System.out.println(new TransformIntoAnotherString().canConvert("leetcode", "codeleet"));
        System.out.println(new TransformIntoAnotherString().canConvert("aabcc", "ccdee"));
        System.out.println(new TransformIntoAnotherString().canConvert("abcdefghijklmnopqrstuvwxyz", "bcdefghijklmnopqrstuvwxayy"));
    }
}
