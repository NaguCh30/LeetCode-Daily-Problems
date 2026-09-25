class Solution {
    public String longestCommonPrefix(String[] strs) {
        
        Arrays.sort(strs, (a, b) -> Integer.compare(b.length(), a.length()));

        int m = strs.length, n = -1; 
        for (int i = 0; i < m; i++) {
            n = Math.max(n, strs[i].length());
        }

        char mat[][] = new char[m][n];

        for (int i = 0; i < m; i++) {

            String str = strs[i];
            char[] chars = str.toCharArray();

            for (int j = 0; j < chars.length; j++) {

                mat[i][j] = chars[j];
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < strs[0].length(); i++) {
            
            boolean match = true;
            char ch = strs[0].charAt(i);
            for (int j = 0; j < m; j++) {

                if (mat[j][i] != ch) {
                    match = false;
                }
            }

            if (match) {
                sb.append(ch);
            } else {
                break;
            }
        }

        return sb.toString();
    }
}