class Solution {
    public int maxFrequencyElements(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);

        int max = -1;
        for (int value : map.values()) if (value > max) max = value;

        int sum = 0;

        for (int value : map.values()) if (value == max) sum += value;

        return sum;
    }
}