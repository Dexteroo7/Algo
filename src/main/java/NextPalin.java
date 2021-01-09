import java.util.Arrays;

//https://www.interviewbit.com/problems/next-smallest-palindrome/
class NextPalin {

    public static String solve(String n) {

        char[] output = n.toCharArray();

        int left = 0, right = n.length() - 1;
        int leftLimit = n.length() % 2 == 0 ? n.length() / 2 - 1 : n.length() / 2;
        boolean greater = false;
        for (; left < leftLimit; left++, right--) {
            greater = output[right] < output[left];
            output[right] = output[left];
        }

        if (output[left] > output[right]) {
            output[right] = output[left];
            return new String(output);
        }
        if (output[left] == output[right] && greater) {
            return new String(output);
        }

        output[right] = output[left];
        int carry = 1;
        while (carry == 1) {

            if (left == -1) {
                output = new char[n.length() + 1];
                Arrays.fill(output, '0');
                output[0] = '1';
                output[output.length - 1] = '1';
                return new String(output);
            }

            if (output[left] == '9') {
                output[left] = '0';
                carry = 1;
            } else {
                output[left]++;
                carry = 0;
            }
            output[right] = output[left];
            left--;
            right++;
        }
        return new String(output);
    }

    public static void main(String[] args) {

        //74094882455

        //74094849047

        System.out.println(solve("9"));

        System.out.println(solve("9"));
        System.out.println(solve("78"));
        System.out.println(solve("88245"));
        System.out.println(solve("2168576189279543123341"));
//        System.out.println(solve("2168576189779816758612"));
//        System.out.println(solve("2168576189 33 9816758612"));
    }
}
