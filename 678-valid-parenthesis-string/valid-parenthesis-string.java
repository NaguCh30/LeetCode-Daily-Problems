class Solution {
    public boolean checkValidString(String s) {

        int minOpen = 0;
        int maxOpen = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                minOpen++;
                maxOpen++;
            }
            else if (ch == ')') {
                minOpen--;
                maxOpen--;
            }
            else { // '*'
                minOpen--;  // treat '*' as ')'
                maxOpen++;  // treat '*' as '('
            }

            // Too many closing brackets
            if (maxOpen < 0) {
                return false;
            }

            // We can always choose '*' as empty
            if (minOpen < 0) {
                minOpen = 0;
            }
        }

        return minOpen == 0;
    }
}