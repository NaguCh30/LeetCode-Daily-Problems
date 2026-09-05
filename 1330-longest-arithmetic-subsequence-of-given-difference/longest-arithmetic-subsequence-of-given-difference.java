class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        HashMap<Integer, Integer> dp = new HashMap<>();

        int ans = 1;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int x = arr[i];

            int prev = dp.getOrDefault(x - difference, 0);

            int curr = prev + 1;

            dp.put(x, Math.max(dp.getOrDefault(x, 0), curr));
            ans = Math.max(ans, dp.get(x));
        }

        return ans;
    }
}