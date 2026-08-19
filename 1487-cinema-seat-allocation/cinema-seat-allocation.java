class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            if (col >= 2 && col <= 9) {
                int mask = map.getOrDefault(row, 0);

                mask |= 1 << (col - 2);

                map.put(row, mask);
            }
        }

        int answer = (n - map.size()) * 2;

        int leftBlock  = 0b1111;     
        int middleBlock = 0b00111100;
        int rightBlock = 0b11110000;

        for (int mask : map.values()) {

            boolean left = (mask & leftBlock) == 0;
            boolean middle = (mask & middleBlock) == 0;
            boolean right = (mask & rightBlock) == 0;

            if (left && right) {
                answer += 2;
            } else if (left || middle || right) {
                answer += 1;
            }
        }

        return answer;
    }
}