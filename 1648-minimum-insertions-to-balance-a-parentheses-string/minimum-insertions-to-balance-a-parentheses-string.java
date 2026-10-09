class Solution {
    public int minInsertions(String s) {
        int opens = 0;
        int additions = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                opens += 2;

                if (opens % 2 != 0) {
                    additions++;
                    opens--;
                }
            } else {
                opens--;

                if (opens < 0) {
                    additions++;
                    opens = 1;
                }
            }
        }

        return opens + additions;
    }
}