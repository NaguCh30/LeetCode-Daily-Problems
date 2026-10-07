class Solution {
    public int addDigits(int num) {
        if (num <= 9) return num;

        int sum = 0;
        while (num > 9) {
            sum = 0;
            while (num > 0) {
                int r = num % 10;
                sum += r;
                num /= 10;
            } 
            num = sum;
        } 

        return num;  
    }
}