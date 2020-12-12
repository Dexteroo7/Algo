//https://leetcode.com/problems/swap-for-longest-repeated-character-substring/
class UglyQuestion {
    public int maxRepOpt1(String text) {

        int max = 0;
        for (char from = 'a'; from <= 'z'; from++)
            max = Integer.max(max, maxRepOptForChar(text, from));
        return max;
    }

    public int maxRepOptForChar(String text, char toCheck) {

        int left = 0;
        int divide = -1;

        int max = 0;

        int bestLeft = 0, bestRight = 0, bestDivide = -1;

        for (int right = 0; right < text.length(); right++) {

            char current = text.charAt(right);
            if (current != toCheck) {

                if (right + 1 < text.length() && right - 1 >= 0 && text.charAt(right + 1) == toCheck && text.charAt(right - 1) == toCheck) {
                    if (divide != -1)
                        left = divide + 1;
                    divide = right;
                } else {

                    left = divide > -1 ? divide + 1 : right + 1;
                    divide = -1;
                }
            }

            int count = divide > 0 ? right - left : right - left + 1;
            if (count > max) {
                max = count;
                bestLeft = left;
                bestRight = right;
                bestDivide = divide;
            }
        }

        //see if we can swap with external
        for (int i = 0; i < bestLeft; i++) {
            if (text.charAt(i) == toCheck) {
                return bestDivide > 0 ? bestRight - bestLeft + 1 : bestRight - bestLeft + 2;
            }
        }
        for (int i = bestRight + 1; i < text.length(); i++) {
            if (text.charAt(i) == toCheck)
                return bestDivide > 0 ? bestRight - bestLeft + 1 : bestRight - bestLeft + 2;
        }

        return bestDivide > 0 ? bestRight - bestLeft : bestRight - bestLeft + 1;
    }
}
