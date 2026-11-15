class Solution {
    public int numOfSubarrays(int[] arr) {

        int MOD = 1_000_000_007;

        long evenCount = 1;
        long oddCount = 0;

        long prefixSum = 0;
        long result = 0;

        for (int num : arr) {

            prefixSum += num;

            if (prefixSum % 2 == 0) {

                result += oddCount;
                evenCount++;

            } else {

                result += evenCount;
                oddCount++;

            }

            result %= MOD;
        }

        return (int) result;
    }
}