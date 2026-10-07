class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        

        PriorityQueue<String> pq = new PriorityQueue<>(
            (a, b) -> {
                int freq = Integer.compare(map.get(b), map.get(a));

                if (freq != 0) {
                    return freq;
                }

                return a.compareTo(b);
            }
        );

        pq.addAll(map.keySet());

        List<String> ans = new ArrayList<>();
        while (k-- > 0) {
            ans.add(pq.poll());
        }

        return ans;
    }
}