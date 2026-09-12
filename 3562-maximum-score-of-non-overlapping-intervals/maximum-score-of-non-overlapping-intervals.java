class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0])
                return Integer.compare(a[0], b[0]);

            return Integer.compare(a[1], b[1]);
        });

        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int low = i + 1;
            int high = n;

            while (low < high) {
                int mid = low + (high - low) / 2;

                if (arr[mid][0] > arr[i][1])
                    high = mid;
                else
                    low = mid + 1;
            }

            next[i] = low;
        }

        List<Integer>[][] dp = new ArrayList[n + 1][5];

        for (int i = 0; i <= n; i++)
            dp[i][0] = new ArrayList<>();

        for (int k = 0; k <= 4; k++)
            dp[n][k] = new ArrayList<>();

        for (int i = n - 1; i >= 0; i--) {

            for (int k = 1; k <= 4; k++) {

                List<Integer> skip = dp[i + 1][k];

                List<Integer> take = new ArrayList<>();
                take.add(arr[i][3]);
                take.addAll(dp[next[i]][k - 1]);

                long takeScore = score(take, intervals);
                long skipScore = score(skip, intervals);

                if (takeScore > skipScore) {
                    dp[i][k] = take;
                } else if (takeScore < skipScore) {
                    dp[i][k] = skip;
                } else {
                    dp[i][k] = lexicographicallySmaller(take, skip);
                }
            }
        }

        List<Integer> answer = dp[0][4];

        Collections.sort(answer);

        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private long score(List<Integer> indices, List<List<Integer>> intervals) {
        long sum = 0;

        for (int index : indices)
            sum += intervals.get(index).get(2);

        return sum;
    }

    private List<Integer> lexicographicallySmaller(
            List<Integer> a,
            List<Integer> b) {

        List<Integer> x = new ArrayList<>(a);
        List<Integer> y = new ArrayList<>(b);

        Collections.sort(x);
        Collections.sort(y);

        int len = Math.min(x.size(), y.size());

        for (int i = 0; i < len; i++) {
            if (!x.get(i).equals(y.get(i)))
                return x.get(i) < y.get(i) ? a : b;
        }

        return x.size() <= y.size() ? a : b;
    }
}