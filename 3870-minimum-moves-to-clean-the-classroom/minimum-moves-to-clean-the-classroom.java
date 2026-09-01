import java.util.*;

class Solution {

    static class State {
        int r;
        int c;
        int mask;
        int energy;

        State(int r, int c, int mask, int energy) {
            this.r = r;
            this.c = c;
            this.mask = mask;
            this.energy = energy;
        }
    }

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0;
        int sc = 0;

        int litterCount = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                char ch = classroom[r].charAt(c);

                if (ch == 'S') {
                    sr = r;
                    sc = c;
                }

                if (ch == 'L') {
                    litterCount++;
                }
            }
        }

        int allCollected = (1 << litterCount) - 1;

        boolean[][][][] visited =
            new boolean[m][n][1 << litterCount][energy + 1];

        Queue<State> queue = new LinkedList<>();

        queue.offer(new State(sr, sc, 0, energy));
        visited[sr][sc][0][energy] = true;

        int moves = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int size = queue.size();

            while (size-- > 0) {

                State cur = queue.poll();

                if (cur.mask == allCollected) {
                    return moves;
                }

                for (int d = 0; d < 4; d++) {

                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    if (cur.energy == 0) {
                        continue;
                    }

                    int newEnergy = cur.energy - 1;
                    int newMask = cur.mask;

                    char cell = classroom[nr].charAt(nc);

                    if (cell == 'L') {

                        int litterIndex = getLitterIndex(
                            classroom, nr, nc
                        );

                        newMask |= (1 << litterIndex);
                    }

                    if (cell == 'R') {
                        newEnergy = energy;
                    }

                    if (newEnergy == 0 && cell != 'R') {
                    }

                    if (!visited[nr][nc][newMask][newEnergy]) {

                        visited[nr][nc][newMask][newEnergy] = true;

                        queue.offer(
                            new State(nr, nc, newMask, newEnergy)
                        );
                    }
                }
            }

            moves++;
        }

        return -1;
    }

    private int getLitterIndex(String[] classroom, int r, int c) {

        int index = 0;

        for (int i = 0; i < classroom.length; i++) {
            for (int j = 0; j < classroom[0].length(); j++) {

                if (classroom[i].charAt(j) == 'L') {

                    if (i == r && j == c) {
                        return index;
                    }

                    index++;
                }
            }
        }

        return -1;
    }
}