class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> starts = new ArrayList<>();

        int[] needs = new int[26];
        int[] founds = new int[26];
        
        if (p.length() > s.length()) {
            return starts;
        }
        int needLength = p.length();

        for (int i = 0; i < needLength; i++) {
            int nC = (int) p.charAt(i) - 'a';
            int fC = (int) s.charAt(i) - 'a';

            needs[nC]++;
            founds[fC]++;
        }
        if(areSame(needs, founds, p)) {
            starts.add(0);
        }
        for (int i = needLength; i < s.length(); i++) {
            founds[s.charAt(i - needLength) - 'a']--;
            founds[s.charAt(i) - 'a']++;
            if (areSame(needs, founds, p)) {
                starts.add(i - needLength + 1);
            }
        }

        return starts;
        
    }

    private boolean areSame(int[] arr1, int[] arr2, String p) {
        for (int i = 0; i < p.length(); i++) {
            int ind = (int) p.charAt(i) - 'a';
            if (arr1[ind] != arr2[ind]) {
                return false;
            }
        }

        return true;
    }
}