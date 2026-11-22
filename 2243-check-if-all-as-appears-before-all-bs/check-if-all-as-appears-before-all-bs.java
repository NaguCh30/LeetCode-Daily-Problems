class Solution {
    public boolean checkString(String s) {
        boolean bAppeared = false;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'a' && bAppeared) {
                return false;
            }
            if (ch == 'b' && !bAppeared) {
                bAppeared = true;
            }
        }
        return true;
    }
}