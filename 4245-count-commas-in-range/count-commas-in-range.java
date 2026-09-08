class Solution {
    public int countCommas(int n) {
        int ans = 0;
        int start = 1000;

        while (start <= n) {
            ans += n - start + 1;

            start *= 1000;
        }

        return ans;
    }
}