class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;

        int oddCount = 0, oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 != 0) { oddCount++; oddChar = i; }
        }

        boolean odd = (n % 2 == 1);
        if (odd && oddCount != 1) return "";
        if (!odd && oddCount != 0) return "";

        int half = n / 2;
        int[] halfCnt = new int[26];
        for (int i = 0; i < 26; i++) {
            int c = cnt[i];
            if (odd && i == oddChar) c -= 1;
            halfCnt[i] = c / 2;
        }

        char middleChar = odd ? (char) ('a' + oddChar) : '\0';
        String targetHalf = target.substring(0, half);

        // Case B: first half exactly equals targetHalf
        int[] thCnt = new int[26];
        for (char c : targetHalf.toCharArray()) thCnt[c - 'a']++;
        boolean matches = true;
        for (int i = 0; i < 26; i++) {
            if (thCnt[i] != halfCnt[i]) { matches = false; break; }
        }

        if (matches) {
            StringBuilder sb = new StringBuilder();
            sb.append(targetHalf);
            if (odd) sb.append(middleChar);
            sb.append(new StringBuilder(targetHalf).reverse());
            String F = sb.toString();
            if (F.compareTo(target) > 0) return F;
        }

        // Case A: find the rightmost breakpoint with a strictly greater char
        int[] remaining = halfCnt.clone();
        int bestI = -1, bestChar = -1;

        for (int i = 0; i < half; i++) {
            char tc = targetHalf.charAt(i);
            for (int c = tc - 'a' + 1; c < 26; c++) {
                if (remaining[c] > 0) { bestI = i; bestChar = c; break; }
            }
            int tcIdx = tc - 'a';
            if (remaining[tcIdx] > 0) remaining[tcIdx]--;
            else break;
        }

        if (bestI == -1) return "";

        int[] remaining2 = halfCnt.clone();
        for (int i = 0; i < bestI; i++) remaining2[targetHalf.charAt(i) - 'a']--;
        remaining2[bestChar]--;

        StringBuilder pBuilder = new StringBuilder();
        pBuilder.append(targetHalf, 0, bestI);
        pBuilder.append((char) ('a' + bestChar));
        for (int c = 0; c < 26; c++) {
            for (int k = 0; k < remaining2[c]; k++) pBuilder.append((char) ('a' + c));
        }

        String P = pBuilder.toString();
        StringBuilder result = new StringBuilder();
        result.append(P);
        if (odd) result.append(middleChar);
        result.append(new StringBuilder(P).reverse());

        return result.toString();
    }
}