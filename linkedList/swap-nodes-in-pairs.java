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
    // 값만 교환
    public ListNode swapPairsChangeVal(ListNode head) {
        // 스왑을 진행할 노드 선언
        ListNode node = head;

        // 현재 노드와 다음 노드가 존재하면 계속 진행
        while (node != null && node.next != null) {
            // 임시 변수를 이용해 값만 교환
            int tmp;
            tmp = node.val;
            node.val = node.next.val;
            node.next.val = tmp;
            // 두 칸 앞으로 이동
            node = node.next.next;
        }
        // 첫 번째 노드를 정답으로 리턴
        return head;
    }
    // 반복 구조로 스왑 -> 책과 다르게 Time Limit Exceeded 결과를 내뱉는다.
    public ListNode swapPairsWhile(ListNode head) {
        // 값을 계산할 임시 노드 선언
        ListNode node = new ListNode(0);
        // 임시 노드를 첫 번째 노드로 선언
        ListNode root = node;
        // 다음 노드는 첫 번째 노드로 지정
        node.next = head;
        // 다음 노드와 다음 다음 노드가 있으면 반복
        while (node.next != null && node.next.next != null) {
            // a는 다음 노드
            ListNode a = node.next;
            // b는 다음 다음 노드
            ListNode b = node.next.next;
            // 위치 변경
            a.next = b.next;
            node.next.next = a;
            // 두 칸 앞으로 이동
            node = node.next.next;
        }
        // 첫 번째 노드는 임시 노드이므로 그다음부턴 결과로 리턴
        return root.next;
    }
    // 재귀 구조로 스왑
    public ListNode swapPairsRecursion(ListNode head) {
        // 현재 노드와 다음 노드가 있으면 반복
        if (head != null && head.next != null) {
            // 다음 노드 선언
            ListNode p = head.next;
            // 다음 다음 노드를 파라미터로 전달하고 스왑된 값을 리턴받음
            head.next = swapPairs(head.next.next);
            // 다음 다음 노드는 현재 노드로 지정
            p.next = head;
            // 다음 노드 리턴
            return p;
        }
        return head;
    }
}