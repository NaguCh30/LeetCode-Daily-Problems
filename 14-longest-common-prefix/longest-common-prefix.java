class Solution {
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        int n = strs.length;
        int fl = strs[0].length(), ll = strs[n - 1].length();

        int i = 0;
        while (i < fl && i < ll && strs[0].charAt(i) == strs[n - 1].charAt(i)) {
            i++;
        }

        return strs[0].substring(0, i);
    }
}