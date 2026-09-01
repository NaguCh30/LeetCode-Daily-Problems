class Solution {
    public String reversePrefix(String word, char ch) {
        char[] chars = word.toCharArray();

        int ind = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ch) {
                ind = i;
                break;
            }
        }

        if (ind == -1) {
            return word;
        }

        int i = 0;
        while (i <= ind) {
            char temp = chars[i];
            chars[i] = chars[ind];
            chars[ind] = temp;
            i++;
            ind--;
        }

        return new String(chars);
    }
}