class Solution {
    public int reverseDegree(String s) {
        
        int sum = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            sum += (num(s.charAt(i)) * (i + 1));
        }

        return sum;
    }

    public int num(char ch) {
        return 26 - (ch - 'a');
    }
}