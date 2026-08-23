class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int mid = n / 2;

        int diff = 0;
        int leftQ = 0;
        int rightQ = 0;

        for (int i = 0; i < n; i++) {
            char ch = num.charAt(i);

            if (ch == '?') {
                if (i < mid) {
                    leftQ++;
                } else {
                    rightQ++;
                }
            } else {
                int digit = ch - '0';

                if (i < mid) {
                    diff += digit;
                } else {
                    diff -= digit;
                }
            }
        }

        int qDiff = leftQ - rightQ;

        return diff * 2 != -9 * qDiff;
    }
}