class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int l = 0, r = 0;
        int n = nums.length;
        int l1 = 0;
        
        while(r < n && l < n) {

            
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            while(map.get(nums[r]) > k) {
                map.put(nums[l], map.get(nums[l]) - 1);
                if (map.get(nums[l]) == 0) {
                    map.remove(nums[l]);
                }
                l++;
            }
            
            int len = r - l + 1;
            l1 = Math.max(l1, len);
            r++;
        }

        return l1;
    }
}