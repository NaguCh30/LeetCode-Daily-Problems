class Solution {
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Character> pq = new PriorityQueue<>(
            (a, b) -> {
                return Integer.compare(map.get(b), map.get(a));
            }
        );

        pq.addAll(map.keySet());

        StringBuilder sb = new StringBuilder();
        
        while (!pq.isEmpty()) {
            char val = pq.poll();
            int num = map.get(val);

            for (int i = 0; i < num; i++) {
                sb.append(val);
            }
        }

        return sb.toString();
    }
}