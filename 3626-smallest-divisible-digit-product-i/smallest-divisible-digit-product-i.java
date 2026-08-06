class Solution {
    public int smallestNumber(int n, int t) {
        
        while(true) {
            int pro = product(n);
            if (pro % t == 0) return n;
            n++;
        }
    }

    private int product(int n) {
        int pro = 1;

        while (n != 0) {
            int digit = n % 10;
            n /= 10;
            pro = pro * digit;
        }

        return pro;
    }
}