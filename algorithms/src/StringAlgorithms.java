import java.util.*;

public class StringAlgorithms {
    public static int divisibilityOfStrings(String s, String t) {
        // // https://leetcode.com/discuss/interview-question/427484/mathworks
        int sLength = s.length();
        int tLength = t.length();

        if(tLength > sLength) return -1;
        if(sLength % sLength != 0) return -1;

        StringBuilder sb = new StringBuilder();
        for(int i=0; i*tLength<sLength; i++) {
            sb.append(t);
        }
        if(!sb.toString().equals(s)) {
            return -1;
        }
        for(int i=1; i<=tLength; i++) {
            StringBuilder current = new StringBuilder();
            String sub = t.substring(0, i);
            while(current.length() < tLength) {
                current.append(sub);
            }
            if(current.toString().equals(t)) {
                return i;
            }
        }
        return -1;
    }

    public static String superReducedString(String s) {
        // https://www.hackerrank.com/challenges/reduced-string/problem
        StringBuilder result = new StringBuilder();
        result.append(s);
        for(int i = 1; i < result.length(); i++) {
            if(result.charAt(i) == result.charAt(i-1)) {
                result.delete(i-1, i+1);
                i = 0;
            }
        }
        if(result.length() == 0) {
            return "Empty String";
        }
        return result.toString();
    }

    public static int camelCaseWordCount(String s) {
        // https://www.hackerrank.com/challenges/camelcase/problem
        if(s.length() == 0) return 0;
        int count = 1;
        for(int i=1; i<s.length(); i++) {
            if(Character.isUpperCase(s.charAt(i))) {
                count++;
            }
        }
        return count;
        // return (s.length() - s.replaceAll("[A-Z]", "").length() + 1);
    }

    public static String caesarCipher(String s, int k) {
        // https://www.hackerrank.com/challenges/caesar-cipher-1/problem
        StringBuilder res = new StringBuilder();
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            int num = 0;
            k = k % 26;
            if(c>=97 && c<= 122) {
                num = (int)c + k;
                if(num > 122) {
                    num = num + 96 - 122;
                }
                res.append((char)num);
            }
            else if(c>=65 && c<= 90) {
                num = (int)c + k;
                if(num > 90) {
                    num = num + 64 - 90;
                }
                res.append((char)num);
            }
            else {
                res.append(c);
            }
        }
        return res.toString();
    }

    public static void separateNumbers(String s) {
        // https://www.hackerrank.com/challenges/separate-the-numbers/problem
        int n = s.length();
        if(n < 2) {
            System.out.println("NO");
            return;
        }

        for(int i=1; i<=(n/2); i++) {
            String sub = s.substring(0, i);
            Long num = Long.parseLong(sub);
            String current = sub;
            while(current.length() < n) {
                current += Long.toString(++num);
            }
            if(s.equals(current)) {
                System.out.println("YES " + sub);
                return;
            }
        }
        System.out.println("NO");
    }

    public static int buildAnagramByModifyingString(String s) {
        // https://www.hackerrank.com/challenges/anagram/problem
        int count  = 0;
        int n = s.length();
        if(n % 2 != 0) {
            return -1;
        }
        String s1 = s.substring(0,n/2);
        String s2 = s.substring(n/2,n);
        char[] s1Chars = s1.toCharArray();
        for (char c : s1Chars){
            int index = s2.indexOf(c);
            if (index == -1){
                count++;
            } else {
                s2 = s2.substring(0,index)+s2.substring(index+1);
            }
        }
        return count;
    }

    public static int makingAnagramsByDeleting(String s1, String s2) {
        // https://www.hackerrank.com/challenges/making-anagrams/problem
        int[] cArr =new int[26];
        int[] cArr1 =new int[26];

        for(int i=0;i<s1.length();i++) {
            cArr[s1.charAt(i)-97]++;
        }
        for(int i=0;i<s2.length();i++) {
            cArr1[s2.charAt(i)-97]++;
        }
        int count=0;

        for(int i=0;i<26;i++) {
            count += Math.abs(cArr[i]-cArr1[i]);
        }
        return count;
    }

    public static int commonChild(String s1, String s2) {
        // https://www.youtube.com/watch?v=0Ql40Llp09E
        // https://www.hackerrank.com/challenges/common-child/problem
        int n1 = s1.length();
        int n2 = s2.length();

        int[][] dp = new int[n1+1][n2+1];
        for(int i=dp.length-2; i>=0; i--) {
            for(int j=dp[0].length-2; j>=0; j--) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(j);
                if(c1 == c2) {
                    dp[i][j] = 1 + dp[i+1][j+1];
                }
                else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }
        return dp[0][0];
    }

    public static boolean balanced(int N, Map<Character, Integer> map) {
        if (map.get('A') <= N && map.get('C') <= N && map.get('G') <= N
                && map.get('T') <= N)
            return true;
        return false;
    }

    public static int steadyGene(String s) {
        // https://www.hackerrank.com/challenges/bear-and-steady-gene/problem
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('A', 0);
        map.put('C', 0);
        map.put('G', 0);
        map.put('T', 0);
        int N = s.length()/4;
        for (int i = 0; i < s.length(); ++i) {
            map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
        }

        int min = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;

        while (i < s.length() && j < s.length()) {
            if (!balanced(N, map)) {
                map.put(s.charAt(j), map.get(s.charAt(j)) - 1);
                j++;
            } else {
                min = Math.min(min, j - i);
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
                i++;
            }
        }
        return min;
    }

    public static int sherlockAndAnagrams(String s) {
        // https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem
        // https://www.youtube.com/watch?v=7z_v54Zk3Bk
        int n = s.length();
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<n; i++) {
            for(int j=i; j<n; j++) {
                char[] c = s.substring(i, j+1).toCharArray();
                Arrays.sort(c);
                String ns = new String(c);

                if(map.containsKey(ns)) {
                    map.put(ns, map.get(ns) + 1);
                }
                else {
                    map.put(ns, 1);
                }
            }
        }
        int pair = 0;
        for(String current : map.keySet()) {
            int v = map.get(current);
            pair += (v * (v-1))/2;
        }
        return pair;
    }

    public static String highestValuePalindrome(String s, int n, int k) {
        // https://www.hackerrank.com/challenges/richie-rich/problem
        // https://www.youtube.com/watch?v=SlfyStPbW3E
        if(n ==0 || k==0) {
            return s;
        }

        int[] visited = new int[n];
        char[] arr = s.toCharArray();
        int l = 0, r = arr.length - 1;

        // Step-1: Making String Pallindrome
        while(l <= r) {
            char left = arr[l];
            char right = arr[r];
            if(left != right) {
                if(k <= 0) {
                    return "-1";
                }
                if(left > right) {
                    arr[r] = left;
                    visited[r] = 1;
                    k--;
                }
                else {
                    arr[l] = right;
                    visited[l] = 1;
                    k--;
                }
            }
            l++;
            r--;
        }
        if(k <= 0) {
            return new String(arr);
        }

        // Step2: Making pallindrome Max
        l = 0;
        r = arr.length-1;
        while(l<=r && k>0) {
            char left = arr[l];
            char right = arr[r];
            if(left != '9') {
                if(visited[l] == 1 || visited[r] == 1) {
                    arr[l] = '9';
                    arr[r] = '9';
                    k--;
                }
                else {
                    if(k>1) {
                        arr[l] = '9';
                        arr[r] = '9';
                        k -= 2;
                    }
                }
            }
            l++;
            r--;
            if(l == r && k>=1) {
                arr[l] = '9';
                break;
            }
        }
        return new String(arr);
    }

    public List<String> removeComments(String[] source) {
        // https://leetcode.com/problems/remove-comments/
        List<String> result = new ArrayList<>();
        boolean blockComment = false;
        StringBuilder sb = new StringBuilder();

        for(String s: source) {
            char[] ch = s.toCharArray();

            for(int i=0; i<ch.length; i++) {
                char c = ch[i];
                if(!blockComment) {
                    if(c != '/') {
                        sb.append(c);
                    }
                    else {
                        if(i < ch.length -1) {
                            if(ch[i+1] == '/') {
                                break;
                            }
                            else if(ch[i+1] == '*') {
                                blockComment = true;
                                i++;
                            }
                            else {
                                sb.append(c);
                            }
                        }
                        else {
                            sb.append(c);
                        }
                    }
                }
                else {
                    if(c == '*') {
                        if(i < ch.length-1) {
                            if(ch[i+1] == '/') {
                                blockComment = false;
                                i++;
                            }
                        }
                    }
                }
            }
            if(!blockComment) {
                if(sb.length() > 0) {
                    result.add(sb.toString());
                    sb = new StringBuilder();
                }
            }
        }
        return result;
    }

    public boolean isConsecutive(String str) {
        // https://www.geeksforgeeks.org/consecutive-sequenced-numbers-in-a-string/
        for(int i=1; i<=str.length()/2; i++) {
            String current = str.substring(0, i);
            StringBuilder sb = new StringBuilder();
            int start = Integer.parseInt(current);
            while(sb.length() < str.length()) {
                sb.append(start);
                start++;
            }

            if(sb.toString().equals(str)) {
                return true;
            }
        }

        return false;
    }

    public String rle(String str) {
        // https://www.geeksforgeeks.org/run-length-encoding/
        int n = str.length();
        if(n == 0) return "";

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<n; i++) {
            int count = 1;
            while(i+1 < n && str.charAt(i) == str.charAt(i+1)) {
                count++;
                i++;
            }
            sb.append(str.charAt(i));
            sb.append(count);
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        String s1 = "bcdbcdbcdbcd", t1 = "bcdbcd";
        String s2 = "bcdbcdbcd", t2 = "bcdbcd";
        String s3 = "lrbb", t3 = "lrbb";
        System.out.println(divisibilityOfStrings(s1, t1));
        System.out.println(divisibilityOfStrings(s2, t2));
        System.out.println(divisibilityOfStrings(s3, t3));
    }
}
