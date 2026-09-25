class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        
        Queue<Integer> q = new LinkedList<>();

        int needs[] = new int[2];

        for (int student : students) {
            q.add(student);
            needs[student]++;
        }

        int i = 0;
        while(!q.isEmpty()) {

            int need = q.peek();
            int avail = sandwiches[i];

            if (needs[avail] == 0) {
                break;
            }

            if (need == avail) {
                q.poll();
                needs[avail]--;
                i++;
            } else {
                q.poll();
                q.add(need);
            }
        }

        return q.size();
    }
}