class Solution {
    public boolean canAliceWin(int n) {
        if (n < 10) {
            return false;
        }
        int curr = 10; 
        boolean turn = true;
        while (n != 0) {
            n -= curr;
            curr--;

            if (curr == 0 || curr > n) {
                return turn;
            }

            turn = !turn;
        }
        return turn;
    }
}