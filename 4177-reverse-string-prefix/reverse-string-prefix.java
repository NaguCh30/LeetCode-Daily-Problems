class Solution {
    public String reversePrefix(String s, int k) {
        int len = s.length();
        if (k > len) return s;

        String firstHalf = s.substring(0, k);
        String secondHalf = s.substring(k, len);
        StringBuilder sb = new StringBuilder();
        
        return sb.append(firstHalf).reverse().append(secondHalf).toString();
    }
}