// 재귀를 이용한 분리
class Solution1 {
    public Set<Character> toSortedSet(String s) {
        // 문자열을 문자 단위 집합으로 저장할 변수 선언
        Set<Character> set = new TreeSet<>(new Comparator<Character>() {
            // 비교 메소드 재정의
            @Override
            public int compare(Character o1, Character o2) {
                // 동일한 문자이면 무시
                if (o1 == o2)
                    return 0 ;
                // 새로운 문자(o1)가 기본 문자(o2)보다 크면 뒤에 위치
                else if (o1 > o2)
                    return 1;
                // 작으면 앞에 위치
                else
                    return -1;
            }
        });
        // 문자열을 문자 단위로 집합에 저장, 정렬된 상태로 저장된다.
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }
        return set;
    }

    public String removeDuplicateLetters(String s) {
        // 정렬된 문자열 집합 순회
        for (char c : toSortedSet(s)) {
            // 해당 문자가 포함된 위치부터 접미사로 지정
            String suffix = s.substring(s.indexOf(c));
            // 전체 집합과 접미사 집합이 일치하면 해당 문자 정답에 추가하고 재귀 호출 진행
            if (toSortedSet(s).equals(toSortedSet(suffix))) {
                return c + removeDuplicateLetters(suffix.replace(String.valueOf(c), ""));
            }
        }
        return "";
    }
}

// 스택을 이용한 문자 제거
class Solution2 {
    public String removeDuplicateLetters(String s) {
        // 문자 개수를 계산해서 담아둘 변수 선언
        Map<Character, Integer> counter = new HashMap<>();
        // 이미 처리한 문자 여부를 확인하기 위한 변수 선언
        Map<Character, Boolean> seen = new HashMap<>();
        // 문제 풀이에 사용할 스택 서언
        Deque<Character> stack = new ArrayDeque<>();
        // 문자별 개수 계산
        for (char c : s.toCharArray())
            counter.put(c, counter.get(c) == null ? 1 : counter.get(c) + 1);

        for (char c : s.toCharArray()) {
            // 현재 처리하는 문자는 카운터에서 -1 처리
            counter.put(c, counter.get(c) - 1);
            // 이미 처리한 문자는 건너뛴다.
            if (seen.get(c) != null && seen.get(c) == true)
                continue;
            // 스택에 있는 문자가 현재 문자보다 더 뒤에 붙여야 할 문자라면 스택에서 제거하고
            // 처리하지 않은 걸로 표시
            while (!stack.isEmpty() && stack.peek() > c && counter.get(stack.peek()) > 0)
                seen.put(stack.pop(), false);
            // 현재 문자를 스택에 삽입
            stack.push(c);
            // 현재 문자를 처리한 걸로 선언
            seen.put(c, true);
        }

        // 스택에 있는 문자를 큐 순서대로 추루하여 리턴
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pollLast());
        return sb.toString();
    }
}