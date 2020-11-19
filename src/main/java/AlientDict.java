class AlientDict {
    public boolean isAlienSorted(String[] words, String order) {

        int[] index = new int['z' + 1];
        for (int i = 0; i < order.length(); i++) {
            index[order.charAt(i)] = i;
        }

        for (int i = 1; i < words.length; i++) {

            String a = words[i-1];
            String b = words[i];
            if (!compare(a, b, index))
                return false;
        }

        return true;
    }

    public boolean compare(String a, String b, int[] index) {

        //return true if a <= b
        for (int i = 0, j = 0; i < a.length() && j < b.length(); i++, j++) {

            char fromA = a.charAt(i);
            char fromB = b.charAt(j);
            if (index[fromA] == index[fromB])
                continue;
            return index[fromA] < index[fromB];
        }

        return a.length() <= b.length();
    }
}
