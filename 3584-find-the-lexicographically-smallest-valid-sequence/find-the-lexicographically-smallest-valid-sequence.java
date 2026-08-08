class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        /*
         * suffix[i] = maximum number of characters from the end
         * of word2 that can be matched exactly in word1[i...].
         */
        int[] suffix = new int[n + 1];

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1];

            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                suffix[i]++;
                j--;
            }
        }

        int[] ans = new int[m];

        int i = 0;
        j = 0;

        // Find the lexicographically smallest prefix.
        while (i < n && j < m) {

            // Exact match: always take it.
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            } 
            // Use this position as our one mismatch.
            else {
                int remaining = m - j - 1;

                if (suffix[i + 1] >= remaining) {
                    ans[j] = i;
                    j++;
                    i++;

                    // Mismatch has been used.
                    break;
                }
            }

            i++;
        }

        // We couldn't even choose enough positions.
        if (j < m && i == n) {
            return new int[0];
        }

        // Mismatch already used, so match the rest exactly.
        while (i < n && j < m) {
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            }
            i++;
        }

        return j == m ? ans : new int[0];
    }
}