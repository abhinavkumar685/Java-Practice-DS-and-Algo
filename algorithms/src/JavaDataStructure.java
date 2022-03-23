import java.util.*;

public class JavaDataStructure {
    public static int[][] mergeIntervals(int[][] intervals) {
        // https://leetcode.com/problems/merge-intervals/
        //int[][] intervals = {{1,3}, {2,6}, {8,10}, {15,18}};
        // https://www.youtube.com/watch?v=_FkR5zMwHQ0
        // Best example to use Comparator
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] arr1, int[] arr2) {
                return arr1[0]-arr2[0];
            }
        });

        List<int[]> result = new ArrayList<>();
        for(int[] interval: intervals) {
            if(result.size() == 0) {
                result.add(interval);
            }
            else {
                int[] prevInterval = result.get(result.size()-1);
                if(prevInterval[1] >= interval[0]) {
                    prevInterval[1] = Math.max(prevInterval[1], interval[1]);
                }
                else {
                    result.add(interval);
                }
            }
        }
        // Important conversion example for toArray
        return result.toArray(new int[result.size()][]);
    }

    public static void groupAnagram(String[] arr) {
        //String[] arr = {"act", "god", "cat", "dog", "tac"};
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();

        for(String current: arr) {
            char[] charArray = current.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);

            if(map.containsKey(key)) {
                map.get(key).add(current);
            }
            else {
                List<String> temp = new ArrayList<>();
                temp.add(current);
                map.put(key, temp);
            }
        }
        // for(Map.Entry<String, List<String>> entry: map.entrySet()) {
        //     result.add(entry.getValue());
        // }
        //List<List<String>> result = new ArrayList<>(map.values());
        result.addAll(map.values());

        System.out.println(result);
    }

    String reverseWords(String S)
    {
        if(S.length()== 0) return "";
        String[] splitted = S.split("[.]");

        StringBuilder str = new StringBuilder();
        int n = splitted.length;

        for(int i=n-1; i>=0; i--) {
            str.append(splitted[i]);
            if(i != 0) str.append(".");
        }

        return str.toString();
    }

    static void printPermutation(String str, String stringTillNow) {
        // Initially stringTillNow=""
        if(str.length() == 0) {
            System.out.println(stringTillNow);
            return;
        }

        for(int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            String left = str.substring(0, i);
            String right = str.substring(i+1);
            String leftOver = left + right;
            printPermutation(leftOver, stringTillNow + c);
        }
    }

    static void priorityQueueImplementation() {
        int[] input = {8, 5, 3, 7, 1, 10, 9};
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        for(int x: input) {
            pq1.add(x);
        }
        while(!pq1.isEmpty()) {
            System.out.println(pq1.remove());
        }

        // Using offer and poll
        for(int x: input) {
            pq1.offer(x);
        }
        while(!pq1.isEmpty()) {
            System.out.println(pq1.poll());
        }

        // Changing into reverse
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer i, Integer j) {
                return j-i;
            }
        });
        for(int x: input) {
            pq2.offer(x);
        }
        while(!pq2.isEmpty()) {
            System.out.println(pq2.poll());
        }
    }

    public static String removeChars(String str, String remove ) {
        char[] s = str.toCharArray();
        char[] r = remove.toCharArray();

        boolean[] flags = new boolean[128]; // assumes ASCII
        int src, dst = 0;

        for(src = 0; src < r.length; ++src) {
            System.out.println(r[src]);
            flags[r[src]] = true;
        }

        System.out.println(Arrays.toString(flags));

        for ( src = 0; src < s.length; ++src ) {
            if (!flags[s[src]]) {
                s[dst++] = s[src];
            }
        }
        return new String(s, 0, dst);
    }

    public static void removeChar() {
        String str = "abhinavkumar";
        String remove = "ahkum";
        System.out.println(removeChars(str, remove));
    }

    public static void insertAtBottom(Stack<Integer> stack, int x) {
        if(stack.isEmpty()) {
            stack.push(x);
        }
        else {
            int peek = stack.pop();
            insertAtBottom(stack, x);
            stack.push(peek);
        }
    }

    public static void reverseStack(Stack<Integer> stack) {
        if(stack.isEmpty()) {
            return;
        }
        int top = stack.pop();
        reverseStack(stack);
        insertAtBottom(stack, top);
    }

    public static void insertInSortedStack(Stack<Integer> stack, int x) {
        if(stack.isEmpty() || x > stack.peek()) {
            stack.push(x);
            return;
        }

        int peek = stack.pop();
        insertInSortedStack(stack, x);
        stack.push(peek);
    }

    public static void sortStackDescending(Stack<Integer> stack) {
        // https://www.codingninjas.com/codestudio/problems/sort-a-stack_985275
        if(stack.isEmpty()) {
            return;
        }
        int top = stack.pop();
        sortStackDescending(stack);
        insertInSortedStack(stack,top);
    }

    static int trailingZerosInFactorial(int n) {
        // https://www.geeksforgeeks.org/count-trailing-zeroes-factorial-number/
        // https://www.youtube.com/watch?v=wkvVdggCSeo
        if (n < 0) // Negative Number Edge Case
            return -1;
        // Initialize result
        int count = 0;
        // Keep dividing n by powers
        // of 5 and update count
        for (int i = 5; n / i >= 1; i *= 5)
            count += n / i;

        return count;
    }



    public static int maxGoldHelper(ArrayList<ArrayList<Integer>> grid, int x, int y, int n, int m){
        // Checking if the current cell lies inside the grid grid and whether we have visited this cell or not.
        if (x >= n || x < 0 || y < 0 || y >= m || grid.get(x).get(y) == 0){
            return 0;
        }
        int answer = 0;
        int count = grid.get(x).get(y);
        grid.get(x).set(y, 0);

        // Visiting all the 4 neigbouring cells.
        answer = Math.max(answer, maxGoldHelper(grid, x - 1, y, n, m));
        answer = Math.max(answer, maxGoldHelper(grid, x + 1, y, n , m));
        answer = Math.max(answer, maxGoldHelper(grid, x, y + 1, n , m));
        answer = Math.max(answer, maxGoldHelper(grid, x, y - 1, n , m));

        answer = answer + count;
        grid.get(x).set(y, count);

        return answer;
    }

    public static int maxGold(ArrayList<ArrayList<Integer>> grid, int n, int m) {
        // https://www.codingninjas.com/codestudio/problems/path-with-maximum-gold_1214654
        // Variable to store the maximum amount of gold that we can collect.
        int maxGold = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (grid.get(i).get(j) > 0){
                    maxGold = Math.max(maxGoldHelper(grid, i, j, n, m), maxGold);
                }

            }
        }
        return maxGold;
    }

    public static void getTargetSumSubarrayIndex() {
        // https://www.techiedelight.com/find-subarrays-given-sum-array/
        // https://www.geeksforgeeks.org/find-subarray-with-given-sum/
        int[] arr = {1,2,3,22, 55};
        int target = 55;
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1) ;

        int sum = 0;
        for(int i=0; i<n; i++) {
            sum += arr[i];
            int remainingSum = sum - target;
            if(map.containsKey(remainingSum)) {
                int lastIndex = map.get(remainingSum) + 1;
                System.out.println("left: " + lastIndex + ", right: " + i);
                break;
            }
            map.put(sum, i);
        }
    }


    public static void main(String[] args){
        String[] words = {"act", "god", "cat", "dog", "tac"};
        groupAnagram(words);
    }
}
