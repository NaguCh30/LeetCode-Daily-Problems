class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, ArrayList<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] ch = str.toCharArray();
            Arrays.sort(ch);
            String string = new String(ch);
            if (!map.containsKey(string)) {
                map.put(string, new ArrayList<>());
            }

            map.get(string).add(str);
        }

        return new ArrayList<>(map.values());
    }
}