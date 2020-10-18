class LongestValidParentheses {
    public int longestValidParentheses(String s) {

        if (s == null || s.length() < 2)
            return 0;

        int counter = 0;
        int length = 0;
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {

            length++;
            char c = s.charAt(i);
            if (c == '(')
                counter++;
            else if (c == ')')
                counter--;
            else
                throw new IllegalArgumentException();
            if (counter == 0)
                maxLength = Integer.max(maxLength, length);
            else if (counter < 0) {
                length = 0;
                counter = 0;
            }
        }

        counter = 0;
        length = 0;

        for (int i = s.length() - 1; i >= 0; i--) {

            length++;
            char c = s.charAt(i);
            if (c == ')')
                counter++;
            else if (c == '(')
                counter--;
            else
                throw new IllegalArgumentException();
            if (counter == 0)
                maxLength = Integer.max(maxLength, length);
            else if (counter < 0) {
                length = 0;
                counter = 0;
            }
        }

        return maxLength;
    }
}
