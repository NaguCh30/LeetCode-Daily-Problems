class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        
        HashMap<String, String> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < knowledge.size(); i++) {
            List<String> pair = knowledge.get(i);
            map.put(pair.get(0), pair.get(1));
        }  

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                i++;
                StringBuilder key = new StringBuilder();
                while (s.charAt(i) != ')') {
                    key.append(s.charAt(i));
                    i++;
                }

                if (map.containsKey(key.toString())) {
                    sb.append(map.get(key.toString()));
                } else {
                    sb.append("?");
                }
            } else {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString(); 
    }
}