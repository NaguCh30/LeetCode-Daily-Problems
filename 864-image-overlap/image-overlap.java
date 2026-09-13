class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int[][] count = new int[2 * n - 1][2 * n - 1];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    for (int x = 0; x < n; x++) {
                        for (int y = 0; y < n; y++) {
                            if (img2[x][y] == 1) {
                                int row = i - x + n - 1;
                                int col = j - y + n - 1;

                                count[row][col]++;
                                answer = Math.max(answer, count[row][col]);
                            }
                        }
                    }
                }
            }
        }

        return answer;
    }
}