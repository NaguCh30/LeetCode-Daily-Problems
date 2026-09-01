class Solution {
    public String reversePrefix(String word, char ch) {
        int idx = word.indexOf(ch);

        if (idx == -1) {
            return word;
        }

        String firstHalf = word.substring(0, idx + 1);
        String secondHalf = word.substring(idx + 1, word.length());
        StringBuilder sb = new StringBuilder(firstHalf);
        sb.reverse().append(secondHalf);

        return sb.toString();
    }
}