class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int low = 0;
        int high = m;

        while (low <= high) {

            int cutA = (low + high) / 2;

            int cutB = (m + n + 1) / 2 - cutA;

            int Aleft = (cutA == 0) ? Integer.MIN_VALUE : nums1[cutA - 1];
            int Aright = (cutA == m) ? Integer.MAX_VALUE : nums1[cutA];

            int Bleft = (cutB == 0) ? Integer.MIN_VALUE : nums2[cutB - 1];
            int Bright = (cutB == n) ? Integer.MAX_VALUE : nums2[cutB];

            if (Aleft <= Bright && Bleft <= Aright) {

                if ((m + n) % 2 == 0) {
                    int leftMax = Math.max(Aleft, Bleft);
                    int rightMin = Math.min(Aright, Bright);

                    return (leftMax + rightMin) / 2.0;
                }

                return Math.max(Aleft, Bleft);
            }

            else if (Aleft > Bright) {
                high = cutA - 1;
            }

            else {
                low = cutA + 1;
            }
        }

        return 0.0;
    }
}