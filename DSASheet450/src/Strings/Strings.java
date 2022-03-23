package Strings;
import java.util.*;

public class Strings {
    public void reverseStringRecusrsive(char[] s, int left, int right) {
        if(left >= right) return;

        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;

        reverseStringRecusrsive(s, ++left, --right);
    }

    public void reverseString(char[] s) {
        // https://leetcode.com/problems/reverse-string/
        int n = s.length;
        if(n < 2) return;

        int left = 0;
        int right = n-1;
        reverseStringRecusrsive(s, left, right);
    }

    void reverse(char[] ch){
        int start = 0;
        int end = ch.length-1;
        while(start <= end){
            swap(ch, start, end);
            start++;
            end--;
        }
    }
    void swap(char[] arr, int f, int s){
        char temp = arr[f];
        arr[f] = arr[s];
        arr[s] = temp;
    }

    boolean isPalindrome(String S) {
        int n = S.length();
        if(n<2) return true;

        int left = 0;
        int right = n-1;
        while(left < right) {
            if(S.charAt(left) != S.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    void findDuplicates(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            char key = entry.getKey();
            int val = entry.getValue();
            if(val > 1) {
                System.out.println(key);
            }
        }
    }

    public boolean isRotatedString(String s, String goal) {
        // https://www.geeksforgeeks.org/a-program-to-check-if-strings-are-rotations-of-each-other/
        // https://leetcode.com/problems/rotate-string/
        String temp = s + s;
        int n = s.length();
        int left = 0;
        while(left + n <= temp.length()) {
            String sub = temp.substring(left, left+n);
            if(sub.equals(goal)) {
                return true;
            }
            left++;
        }
        return false;
    }

    public boolean validShuffleOfTwoDistinctString(String s1, String s2, String shuffle) {
        // https://www.programiz.com/java-programming/examples/check-valid-shuffle-of-strings
        int l1 = s1.length();
        int l2 = s2.length();
        int shuffleLength = shuffle.length();

        if(shuffleLength != l1 + l2) return false;
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for(int i=0; i<l1; i++) {
            char c = s1.charAt(i);
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }

        for(int i=0; i<l2; i++) {
            char c = s2.charAt(i);
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }

        for(int i=0; i<shuffleLength; i++) {
            char c = shuffle.charAt(i);
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }

        for(Map.Entry<Character, Integer> entry : map1.entrySet()) {
            char key  = entry.getKey();
            int val = entry.getValue();
            if(map2.get(key) != val) {
                return false;
            }
        }
        return true;
    }

    public String countAndSay(int n) {
        // https://leetcode.com/problems/count-and-say/
        if(n==1) {
            return "1";
        }

        String s = countAndSay(n-1);
        int len = s.length();

        StringBuilder sb = new StringBuilder();
        int freq = 1;
        for(int i=0; i<len-1; i++) {
            if(s.charAt(i) == s.charAt(i+1)) {
                freq++;
            }
            else {
                sb.append(freq);
                sb.append(s.charAt(i));
                freq = 1;
            }
        }
        sb.append(freq);
        sb.append(s.charAt(len-1));
        return sb.toString();
    }

    String getLongestPallindrome(String s, int left, int right) {
        while(left>=0 && right <s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left+1, right);
    }

    String longestPallindromeSubstring(String S){
        // https://leetcode.com/problems/longest-palindromic-substring/
        int n = S.length();
        if(n < 2) return S;

        String result = "";
        for(int i=0; i<n; i++) {
            String oddSubstring = getLongestPallindrome(S, i, i);
            String evenSubstring = getLongestPallindrome(S, i, i+1);

            if(oddSubstring.length() > result.length()) {
                result = oddSubstring;
            }
            if(evenSubstring.length() > result.length()) {
                result = evenSubstring;
            }
        }
        return result;
    }

    public int longestCommonSubsequence(String text1, String text2) {
        // https://www.youtube.com/watch?v=NnD96abizww
        int[][] dp = new int[text1.length()+1][text2.length()+1];
        for(int i=1; i<dp.length; i++) {
            for(int j=1; j<dp[0].length; j++) {
                if(text1.charAt(i-1) != text2.charAt(j-1)) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
                else {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    public int LongestRepeatingSubsequence(String str) {
        // https://www.youtube.com/watch?v=UQiZmkVwARg
        int[][] dp = new int[str.length()+1][str.length()+1];
        for(int i=1; i<dp.length; i++) {
            for(int j=1; j<dp[0].length; j++) {
                if(str.charAt(i-1) == str.charAt(j-1) && i != j) {
                    dp[i][j] = dp[i-1][j-1] + 1;

                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    public List<String> findAllSubsequence(List<String> result, String str) {
        // https://www.youtube.com/watch?v=Sa5PmCFF_zI
        // Caller: List<String> result = subsequence(new ArrayList<>(), "abc");
        if(str.length() == 0) {
            List<String> res = new ArrayList<>();
            res.add("");
            return res;
        }

        char ch = str.charAt(0);
        List<String> resp = findAllSubsequence(result, str.substring(1));
        List<String> temp = new ArrayList<>();
        for(String s : resp) {
            temp.add("" + s);
            temp.add(ch + s);
        }
        System.out.println(temp);
        return temp;
    }

    public void powerSet(List<String> result, String str, String current, int index) {
        // https://www.youtube.com/watch?v=mEBEw_xScsE
        // Caller: powerSet(new ArrayList<>(), "abc", "", 0);
        if(index == str.length()) {
            result.add(current);
            return;
        }

        powerSet(result, str, current+str.charAt(index), index+1);
        powerSet(result, str, current, index+1);
    }

    public void stringPermutation(String S, String current, List<String> result) {
        if(S.length() == 0) {
            result.add(current);
            return;
        }

        for(int i=0; i<S.length(); i++) {
            char c = S.charAt(i);
            String leftSubstring = S.substring(0, i);
            String rightSubstring = S.substring(i+1);
            String remainingString = leftSubstring + rightSubstring;
            stringPermutation(remainingString, current + c, result);
        }
    }
    public String swapString(String str, int left, int right) {
        char[] arr = str.toCharArray();
        swap(arr, left, right);
        return new String(arr);
    }
    public List<String> findPermutation(String S) {
        List<String> result = new ArrayList<>();
        if(S.length() == 0) {
            return result;
        }
        stringPermutation(S, "", result);
        return result;
    }

    public void permutationUsingSwapBacktrack(List<String> result, String str, int left, int right) {
        if(left == right) {
            result.add(str);
            return;
        }

        for(int i=left; i<=right; i++) {
            String swapped = swapString(str, left, i);
            permutationUsingSwapBacktrack(result, swapped, left+1, right);
            //Backtrack
//            swapString(str, left, i);
        }
    }

    public void permutationUsingSwaps(String S) {
        // https://www.youtube.com/watch?v=mEBEw_xScsE
        List<String> result = new ArrayList<>();
        if(S.length() == 0) {
            return;
        }
        permutationUsingSwapBacktrack(result, S, 0, S.length()-1);
        System.out.println(result);
    }

    public int maxSubStr(String str, int n) {
        // https://www.geeksforgeeks.org/split-the-binary-string-into-substrings-with-equal-number-of-0s-and-1s/
        int count0 = 0, count1 = 0;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == '0') {
                count0++;
            }
            else {
                count1++;
            }

            if (count0 == count1) {
                cnt++;
            }
        }

        // It is not possible to split the string
        if (count0 != count1) {
            return -1;
        }
        return cnt;
    }

    public int editDistance(String s, String t) {
        // https://leetcode.com/problems/edit-distance/
        // https://www.youtube.com/watch?v=tooMn-xfYCU
        int n1 = s.length();
        int n2 = t.length();

        int[][] dp = new int[n1+1][n2+1];
        for(int i=0; i<dp.length; i++) {
            for(int j=0; j<dp[0].length; j++) {
                if(i==0 && j==0) {
                    dp[i][j] = 0;
                }
                else if(i==0) {
                    dp[i][j] = j;
                }
                else if(j==0) {
                    dp[i][j] = i;
                }
                else {
                    if(s.charAt(i-1) == t.charAt(j-1)) {
                        dp[i][j] = dp[i-1][j-1];
                    }
                    else {
                        int replace = dp[i-1][j-1];
                        int delete = dp[i-1][j];
                        int insert = dp[i][j-1];
                        dp[i][j] = 1 + Math.min(replace, Math.min(delete, insert));
                    }
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void reverse(int[] arr, int start) {
        int end = arr.length-1;
        while(start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }

    static int[] nextPermutation(int N, int[] arr){
        int index = N-2;
        while(index>=0 && arr[index] >= arr[index+1]) {
            index--;
        }

        if(index >= 0) {
            for(int i=N-1; i>index; i--) {
                if(arr[i] > arr[index]) {
                    swap(arr, index, i);
                    break;
                }
            }
        }

        reverse(arr, index+1);
        return arr;
    }

    static boolean isValidParenthesis(String str) {
        // https://leetcode.com/problems/valid-parentheses/
        int n = str.length();
        if(n == 0) return true;

        Stack<Character> stack = new Stack<>();
        for(int i=0; i<n; i++) {
            char c = str.charAt(i);
            if(c=='(' || c=='{' || c=='[') {
                stack.push(c);
            }
            else{
                if(stack.isEmpty()) {
                    return false;
                }
                else {
                    char top = stack.pop();
                    if(c == ')' && top != '(') {
                        return false;
                    }
                    if(c == '}' && top != '{') {
                        return false;
                    }
                    if(c == ']' && top != '[') {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    public static int wordBreak(String A, ArrayList<String> B ) {
        //https://www.youtube.com/watch?v=2NaaM_z_Jig
        int[] dp = new int[A.length()];
        for(int i=0; i<dp.length; i++) {
            for(int j=0; j<=i; j++) {
                String current = A.substring(j, i+1);
                if(B.contains(current)) {
                    if(j == 0) {
                        dp[i] += 1;
                    }
                    else {
                        dp[i] += dp[j-1];
                    }
                }
            }
        }
        return dp[dp.length-1];
    }

    private int prime = 101;
    public int patternSearchUsingRKSAlgorithm(char[] text, char[] pattern) {
        // https://www.youtube.com/watch?v=H4VrKHVG5qI
        // https://github.com/mission-peace/interview/blob/master/src/com/interview/string/RabinKarpSearch.java
        int textLength = text.length;
        int patternLength = pattern.length;

        long patternHash = createHash(pattern, patternLength - 1);
        // First we take a window of pattern length for text
        long textHash = createHash(text, patternLength - 1);

        //total substring to match = n-m+1;
        for(int i=0; i<textLength-patternLength+1; i++) {
            if(patternHash == textHash && checkEqualText(text, i,
                    i+patternLength-1, pattern, 0, patternLength-1)) {
                return i;
            }
            // We have to go untill i value becomes n-m (i>=0 && <= n-m+1-1)
            if(i < textLength-patternLength) {
                textHash = recalculateHash(text, i,
                        i+patternLength, textHash, patternLength);
            }
        }

        return -1;
    }

    private boolean checkEqualText(char[] text, int textStart, int textEnd,
                                   char[] pattern, int patternStart, int patternEnd) {
        while(textStart <= textEnd && patternStart <= patternEnd){
            if(text[textStart] != pattern[patternStart]){
                return false;
            }
            textStart++;
            patternStart++;
        }
        return true;
    }

    private long recalculateHash(char[] text, int oldIndex, int newIndex,
                                 long textHash, int patternLength) {
        long newHash = textHash - text[oldIndex];
        newHash = newHash / prime;
        newHash += text[newIndex] * Math.pow(prime, patternLength-1);
        return newHash;
    }

    private long createHash(char[] str, int end) {
        long hash = 0;
        for(int i=0; i<=end; i++) {
            hash += str[i]* Math.pow(prime, i);
        }
        return hash;
    }

    static String printSequence(String[] arr, String input)
    {
//        String[] arr = {"2","22","222",
//                "3","33","333",
//                "4","44","444",
//                "5","55","555",
//                "6","66","666",
//                "7","77","777","7777",
//                "8","88","888",
//                "9","99","999","9999"
//        };
        StringBuilder output = new StringBuilder();
        int n = input.length();
        for (int i = 0; i < n; i++)
        {
            // Checking for space
            if (input.charAt(i) == ' ')
                output.append("0");

            else
            {
                // Calculating index for each
                // character
                int position = input.charAt(i) - 'A';
                output.append(arr[position]);
            }
        }

        // Output sequence
        return output.toString();
    }

    int countReverseParenthesis(String s) {
        // https://www.youtube.com/watch?v=dTBpH9YT2HQ
        int n = s.length();
        if(n%2 != 0) {
            return -1;
        }

        int open=0, close=0, count=0;
        for(int i=0; i<n; i++) {
            char c = s.charAt(i);
            if(c == '{') {
                open++;
            }
            else {
                if(open == 0) {
                    close++;
                }
                else {
                    open--;
                }
            }
        }
        count = (int)(Math.ceil(open/2.0) + Math.ceil(close/2.0));
        return count;
    }

    long countPallindromicSubsequence(String str) {
        // https://leetcode.com/problems/count-different-palindromic-subsequences/
        // https://www.youtube.com/watch?v=YHSjvswCXC8
        int n = str.length();
        int[][] dp = new int[n][n];
        for(int gap=0; gap<n; gap++) {
            for(int i=0,j=gap; j<n; i++,j++) {
                if(gap == 0) {
                    dp[i][j] = 1;
                }
                else if(gap == 1) {
                    dp[i][j] = str.charAt(i) == str.charAt(j) ?3:2;
                }
                else {
                    if(str.charAt(i) == str.charAt(j)) {
                        dp[i][j] = dp[i][j-1] + dp[i+1][j] + 1;
                    }
                    else {
                        dp[i][j] = dp[i][j-1] + dp[i+1][j] - dp[i+1][j-1];
                    }
                }
            }
        }
        return dp[0][n-1];
    }

    public int countPallindromicSubstrings(String s) {
        // https://leetcode.com/problems/palindromic-substrings/
        // https://www.youtube.com/watch?v=XmSOWnL6T_I
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;
        // Diagonal Traversal
        for(int gap=0; gap<n; gap++) {
            for(int row=0, col=row+gap; col<n; row++, col++) {
                if(gap == 0) {
                    dp[row][col] = true;
                }
                else if(gap == 1) {
                    if(s.charAt(row) == s.charAt(col)) {
                        dp[row][col] = true;
                    }
                }
                else if(s.charAt(row) == s.charAt(col) && dp[row+1][col-1]) {
                    dp[row][col] = true;
                }

                if(dp[row][col]) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int getWordCountDFS(char[][] matrix, String target, int row, int col, int targetIndex) {
        if(row<0 || row>=matrix.length || col<0 || col>=matrix[0].length || matrix[row][col] != target.charAt(targetIndex)) {
            return 0;
        }

        char original = matrix[row][col];
        matrix[row][col] = '0';

        int found = 0;
        // V.V.V Important Condition to remember
        if(targetIndex == target.length()-1) {
            found = 1;
        }
        else {
            found += getWordCountDFS(matrix, target, row+1, col, targetIndex+1);
            found += getWordCountDFS(matrix, target, row-1, col, targetIndex+1);
            found += getWordCountDFS(matrix, target, row, col+1, targetIndex+1);
            found += getWordCountDFS(matrix, target, row, col-1, targetIndex+1);
        }
        matrix[row][col] = original;
        return found;
    }

    public static void wordCount(String[] args){

        char[][] matrix = {{ 'S', 'N', 'B', 'S', 'N' },
                { 'B', 'A', 'K', 'E', 'A' },
                { 'B', 'K', 'B', 'B', 'K' },
                { 'S', 'E', 'B', 'S', 'E' }};
        String word = "SNAKES";
        int count = 0;
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[i].length; j++) {
                if(matrix[i][j] == word.charAt(0)) {
                    count += getWordCountDFS(matrix, word, i, j, 0);
                }
            }
        }
        System.out.println(count);
    }

    public String longestCommonPrefix(String[] strs) {
        StringBuilder result = new StringBuilder();
        String original = strs[0];
        int index = 0;
        while(index < original.length()) {
            boolean matched = true;
            for(int i=1; i<strs.length; i++) {
                String current  = strs[i];
                if(current.length() == 0) {
                    matched = false;
                    break;
                }
                if(index >= current.length()) {
                    matched = false;
                    break;
                }
                if (original.charAt(index) != current.charAt(index)) {
                    matched = false;
                    break;
                }
            }
            if(matched) {
                result.append(original.charAt(index));
                index++;
            }
            else {
                break;
            }
        }
        return result.toString();
    }

    public int convert(char c) {
        switch(c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                throw new RuntimeException("the numeral not supported");
        }
    }

    public int romanToInt(String s) {
        // https://leetcode.com/problems/roman-to-integer/
        // https://www.youtube.com/watch?v=dlATMslQ6Uc
        int lastValue = 0;
        int sum = 0;
        for(int i=s.length()-1; i>=0; i--) {
            int value = convert(s.charAt(i));
            if(value < lastValue) {
                sum -= value;
            }
            else {
                sum += value;
            }
            lastValue = value;
        }
        return sum;
    }

    public int minFlipsToMakeBitsAlternate(String s) {
        // https://www.youtube.com/watch?v=F0E7k6X_kt8
        int count1 = 0; //even-0, odd-1;
        int count2 = 0; // even-1, odd-0;

        for(int i=0; i<s.length(); i++) {
            if(i%2==0 && s.charAt(i) == '1') {
                count1++;
            }
            else if(i%2==1 &&  s.charAt(i) == '0') {
                count1++;
            }
            else if(i%2==0 && s.charAt(i) == '0') {
                count2++;
            }
            else if(i%2==1 &&  s.charAt(i) == '1') {
                count2++;
            }
        }
        return Math.min(count1, count2);
    }

    public static void main(String[] args) {
        Strings obj = new Strings();
        String str1 = "a1b1";
        String str2 = "c2d2";
        String shuffle = "1212dac";
        List<String> result = new ArrayList<>();
        obj.permutationUsingSwaps("abc");
    }
}
