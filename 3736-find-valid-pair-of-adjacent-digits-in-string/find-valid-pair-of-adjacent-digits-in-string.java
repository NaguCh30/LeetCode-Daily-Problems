class Solution {
    public String findValidPair(String s) {
        
        int[] arr = new int[10];

        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - '0']++;
        }

        int l = 0;

        for (int i = 1; i < s.length(); i++) {
            int num1 = s.charAt(i - 1) - '0';
            int num2 = s.charAt(i) - '0';

            if (num1 != num2 && arr[num1] == num1 && arr[num2] == num2) {
                return s.substring(i - 1, i + 1);
            }
        }

        return "";
    }
}