/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int index = 1;

        ListNode prev = head;
        ListNode curr = head.next;

        int first = -1;
        int previous = -1;

        int minDistance = Integer.MAX_VALUE;

        while (curr != null && curr.next != null) {

            int prevValue = prev.val;
            int currValue = curr.val;
            int nextValue = curr.next.val;

            boolean isCritical =
                    (currValue > prevValue && currValue > nextValue) ||
                    (currValue < prevValue && currValue < nextValue);

            if (isCritical) {

                if (first == -1) {
                    first = index;
                } 
                else {
                    minDistance = Math.min(
                            minDistance,
                            index - previous
                    );
                }

                previous = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        if (first == -1 || previous == first) {
            return new int[]{-1, -1};
        }

        int maxDistance = previous - first;

        return new int[]{minDistance, maxDistance};
    }
}