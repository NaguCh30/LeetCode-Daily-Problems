class Solution {
    public boolean checkDivisibility(int n) {
        int[] sp = sumANDpro(n);
        int s = sp[0];
        int p = sp[1];

        return n % (s + p) == 0;
    }

    private int[] sumANDpro(int n) {
        int sum = 0;
        int pro = 1;

        while (n != 0) {
            int digit = n % 10;
            n /= 10;

            pro *= digit;
            sum += digit;
        }

        return new int[]{sum, pro};
    }
}