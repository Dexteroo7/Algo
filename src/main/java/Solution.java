class Solution {
    public String reverseOnlyLetters(String s) {

        char[] reversed = new char[s.length()];
        for (int left = 0, right = s.length()-1; left <= right; left++, right--) {

            char fromLeft = s.charAt(left);
            char fromRight = s.charAt(right);
            if (Character.isLetter(fromLeft) && Character.isLetter(fromRight)) {
                reversed[left] = fromRight;
                reversed[right] = fromLeft;
            } else if (!Character.isLetter(fromLeft)) {
                reversed[left] = fromLeft;
                right++;
            }
            else if (!Character.isLetter(fromRight)) {
                reversed[right] = fromRight;
                left--;
            }
        }

        return new String(reversed);
    }
}
