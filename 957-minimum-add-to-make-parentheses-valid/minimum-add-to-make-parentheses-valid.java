class Solution {
    public int minAddToMakeValid(String s) {
        int opens = 0;
        int additions = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                opens++;
            } else {
                if (opens > 0) {
                    opens--;
                } else {
                    additions++;
                }
            }
        }

        return opens + additions;
    }
}