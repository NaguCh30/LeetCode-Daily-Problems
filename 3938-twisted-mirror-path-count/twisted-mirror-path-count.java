class Solution {

    static final int[][][] mat = {
        {
            {1, 1},
            {1, 1}
        },
        {
            {0, 1},
            {1, 0}
        }
    };

    static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] A) {

        int n = A[0].length;
        long left = 1;

        for (int i = 0; i < A.length; i++) {

            for (int j = 0; j < n; j++) {

                int c = A[i][j];
                int top = i > 0 ? A[i - 1][j] : 0;

                A[i][j] = (int) (
                    (left * mat[c][1][0] +
                     top * mat[c][1][1]) % MOD
                );

                left = (
                    left * mat[c][0][0] +
                    top * mat[c][0][1]
                ) % MOD;
            }

            left = 0;
        }

        return A[A.length - 1][n - 1];
    }
}