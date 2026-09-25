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

            int need = q.poll();
            int avail = sandwiches[i];
            if (needs[avail] == 0) {
                q.add(need);
                break;
            }

            if (need == avail) {
                needs[avail]--;
                i++;
            } else if (need != avail) {
                q.add(need);
            }
        }

        return q.size();
    }
}