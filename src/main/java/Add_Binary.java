public class Add_Binary {
    public String addBinary(String a, String b) {

        StringBuilder output = new StringBuilder();

        int aPointer = a.length() - 1;
        int bPointer = b.length() - 1;
        char carry = '0';

        while (aPointer >= 0 || bPointer >= 0 || carry == '1') {

            char fromA = aPointer >= 0 ? a.charAt(aPointer) : '0';
            char fromB = bPointer >= 0 ? b.charAt(bPointer) : '0';
            char[] result = add(fromA, fromB, carry);

            output.append(result[0]);
            carry = result[1];

            aPointer--;
            bPointer--;
        }
        return output.reverse().toString();
    }

    public char[] add(char a, char b, char c) {

        if (a == '1' && b == '1' && c == '1')
            return new char[]{'1', '1'};
        if (a == '1' && b == '0' && c == '1')
            return new char[]{'0', '1'};
        if (a == '0' && b == '1' && c == '1')
            return new char[]{'0', '1'};
        if (a == '0' && b == '0' && c == '1')
            return new char[]{'1', '0'};
        if (a == '1' && b == '1' && c == '0')
            return new char[]{'0', '1'};
        if (a == '1' && b == '0' && c == '0')
            return new char[]{'1', '0'};
        if (a == '0' && b == '1' && c == '0')
            return new char[]{'1', '0'};
        return new char[]{'0', '0'};
    }
}
