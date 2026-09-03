class Solution {
    public int[][] merge(int[][] intervals) {
        ArrayList<ArrayList<Integer>> dummy = new ArrayList<>();
        int n = intervals.length;
        int i = 0;

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        while (i < n) {

            int start = intervals[i][0];
            int end = intervals[i][1];

            while (i < n && intervals[i][0] <= end) {
                end = Math.max(end, intervals[i][1]);
                i++;
            }

            ArrayList<Integer> interval = new ArrayList<>();
            interval.add(start);
            interval.add(end);
            dummy.add(interval);
        }

        int size = dummy.size();

        int[][] ans = new int[size][2];

        for (int j = 0; j < size; j++) {
            ArrayList<Integer> interval = dummy.get(j);
            ans[j][0] = interval.get(0);
            ans[j][1] = interval.get(1);
        }

        return ans;
    }
}