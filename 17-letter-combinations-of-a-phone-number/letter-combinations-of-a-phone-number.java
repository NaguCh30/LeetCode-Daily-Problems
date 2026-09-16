class Solution {

    String map[] = {
                    "", "", "abc",  "def", 
                    "ghi", "jkl", "mno", 
                    "pqrs", "tuv", "wxyz"
                };
    
    List<String> result = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        
        if (digits.length() == 0) {
            return result;
        }

        backtrack(digits, 0, new StringBuilder());

        return result;
    }

    public void backtrack(String digits, int index, StringBuilder current) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        String letters = map[digits.charAt(index) - '0'];

        for (char letter : letters.toCharArray()) {
            current.append(letter);
            backtrack(digits, index + 1, current);
            current.deleteCharAt(current.length() - 1);
        }
    }
}