class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;

        // Reverse the array
        for (int i = 0; i < n / 2; i++) {
            int temp = nums[i];
            nums[i] = nums[n - 1 - i];
            nums[n - 1 - i] = temp;
        }

        long res = 0;
        long budget = k;

        Deque<Integer> q = new ArrayDeque<>();

        for (int j = 0, i = 0; j < n; j++) {

            while (!q.isEmpty() && nums[q.peekLast()] < nums[j]) {
                int r = q.pollLast();
                int l = q.isEmpty() ? i - 1 : q.peekLast();

                budget -= 1L * (r - l) * (nums[j] - nums[r]);
            }

            q.addLast(j);

            while (budget < 0) {
                budget += nums[q.peekFirst()] - nums[i];

                if (q.peekFirst() == i) {
                    q.pollFirst();
                }

                i++;
            }

            res += j - i + 1;
        }

        return res;
    }
}
