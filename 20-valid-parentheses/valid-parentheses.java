class Solution {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack();

        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                st.push(ch);
            } else {
                if (st.isEmpty()) return false;

                char p = st.peek();
                if ((ch == ')' && p != '(') ||
                    (ch == '}' && p != '{') ||
                    (ch == ']' && p != '[')) {
                        return false;
                } else {
                    st.pop();
                }
            }
        }

        return st.isEmpty();
    }
}