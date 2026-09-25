class Solution {

    int index = 0;

    public List<String> braceExpansionII(String expression) {

        Set<String> result = parseExpression(expression);

        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);

        return answer;
    }

    // expression = term (',' term)*
    private Set<String> parseExpression(String s) {

        Set<String> result = parseTerm(s);

        while (index < s.length() && s.charAt(index) == ',') {

            index++; // skip ','

            Set<String> next = parseTerm(s);

            result.addAll(next);
        }

        return result;
    }

    // term = factor factor*
    private Set<String> parseTerm(String s) {

        Set<String> result = new HashSet<>();

        // Important:
        // We need the identity element for concatenation.
        // "" + word = word
        result.add("");

        while (index < s.length()
                && s.charAt(index) != '}'
                && s.charAt(index) != ',') {

            Set<String> factor = parseFactor(s);

            result = concatenate(result, factor);
        }

        return result;
    }

    // factor = letter | '{' expression '}'
    private Set<String> parseFactor(String s) {

        Set<String> result = new HashSet<>();

        char ch = s.charAt(index);

        if (ch == '{') {

            index++; // skip '{'

            result = parseExpression(s);

            index++; // skip '}'

        } else {

            result.add(String.valueOf(ch));

            index++;
        }

        return result;
    }

    // Cartesian product + string concatenation
    private Set<String> concatenate(Set<String> a, Set<String> b) {

        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}