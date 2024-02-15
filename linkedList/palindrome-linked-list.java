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
    // 데크를 이용한 풀이
    public boolean isPalindromeDeque(ListNode head) {
        Deque<Integer> deque = new LinkedList<>();
        // 연결 리스트를 데크에 삽입
        ListNode node = head;
        while (node != null) {
            deque.add(node.val);
            node = node.next;
        }

        // 데크가 모두 비거나(짝수 개일 때) 1개 이하(홀수 개일 때)가 될 때까지 비교
        while (!deque.isEmpty() && deque.size() > 1) {
            // 데크의 양 끝을 추출해 팰린드롬 여부 확인
            if (deque.pollFirst() != deque.pollLast()) {
                return false;
            }
        }
        return true;
    }

    // 러너를 이용한 우아한 풀이
    public boolean isPalindromeRunner(ListNode head) {
        ListNode fast = head, slow = head;
        // 빠른 러너가 끝까지 갈 때까지 느린 러너와 함께 진행
        while (fast != null && fast.next !=null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 홀수 개 일 때 느린 러너가 한 칸 더 앞으로 가도록 처리
        if (fast != null)
            slow = slow.next;

        // 중간에 도달한 느린 러너를 기준으로 하여 역순 연결 리스트를 만든다.
        ListNode rev = null;
        while (slow != null) {
            // 느린 러너로 역순 연결 리스트 rev를 만들어나간다.
            ListNode next = slow.next;
            slow.next = rev;
            rev = slow;
            slow = next;
        }

        // rev 연결 리스트를 끝까지 이동하며 비교
        while (rev != null) {
            // 역순 연결 리스트 rev와 기존 연결 리스트 head를 차례대로 비교
            if (rev.val != head.val)
                return false;
            rev = rev.next;
            head = head.next;
        }
        return true;
    }
}