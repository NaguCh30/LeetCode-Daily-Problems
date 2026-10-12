class Solution {
    public int[] replaceElements(int[] arr) {
        int n = arr.length;
        int curr = arr[n - 1];

        int ans[] = new int[n];
        ans[n - 1] = -1;

        for (int i = n - 2; i >= 0; i--) {
            int elem = arr[i];
            ans[i] = curr;
            if (elem > curr) {
                curr = elem;
            }
        }

        return ans;
    }
}