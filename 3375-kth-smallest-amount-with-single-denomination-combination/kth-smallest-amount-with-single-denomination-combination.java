class Solution {

    public long findKthSmallest(int[] coins, int k) {
        long low = 1;
        long high = 1_000_000_000_000L; // 1e12

        while (low < high) {
            long mid = low + (high - low) / 2;

            if (count(mid, coins) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long count(long x, int[] coins) {
        int n = coins.length;
        long total = 0;

        // Inclusion-exclusion over all subsets
        for (int mask = 1; mask < (1 << n); mask++) {

            long lcm = 1;
            int bits = 0;
            boolean overflow = false;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bits++;

                    long g = gcd(lcm, coins[i]);

                    // Prevent overflow and values larger than x
                    lcm = lcm / g;

                    if (lcm > x / coins[i]) {
                        overflow = true;
                        break;
                    }

                    lcm *= coins[i];
                }
            }

            if (overflow || lcm > x) {
                continue;
            }

            long multiples = x / lcm;

            if ((bits & 1) == 1) {
                total += multiples;
            } else {
                total -= multiples;
            }
        }

        return total;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}