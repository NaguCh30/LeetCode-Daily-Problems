class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int gl = g.length, sl = s.length;
        int count = 0;
        int i = 0, j = 0;

        while (i < gl && j < sl) {
            if (s[j] >= g[i]) {
                count++;
                i++;
                j++;
            } else if (s[j] < g[i]) {
                j++;
            }
        }

        return count;
    }
}