class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();

        for (String word : strs) {
            char[] WA = word.toCharArray();
            Arrays.sort(WA);
            String key = new String(WA);

            if (map.containsKey(key)) {
                map.get(key).add(word);
            } else {
                map.put(key, new ArrayList<>());
                map.get(key).add(word);
            }
        }

        List<List<String>> list = new ArrayList<>();

        for (ArrayList<String> value : map.values()) {
            list.add(value);
        }

        return list;
    }
}