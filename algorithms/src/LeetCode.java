import java.util.*;
import java.util.LinkedList;

public class LeetCode {
    public int containerWithMostWater(int[] height) {
        // https://leetcode.com/problems/container-with-most-water/
        // https://www.youtube.com/watch?v=qUDp8IUbZto
        int maxVolume = 0;
        int left = 0;
        int right = height.length-1;

        while(left < right) {
            int leftHeight = height[left];
            int rightHeight = height[right];

            int minHeight = Math.min(leftHeight, rightHeight);
            int currentVolume = minHeight * (right - left);
            maxVolume = Math.max(maxVolume, currentVolume);
            if(leftHeight < rightHeight) {
                left++;
            }
            else {
                right--;
            }
        }
        return maxVolume;
    }

    public int trappingRainWater(int[] height) {
        // https://leetcode.com/problems/trapping-rain-water/
        // https://www.youtube.com/watch?v=PwEdhxQkFZs
        int leftMax = 0, rightMax = 0;
        int n = height.length;
        int result = 0;

        int left = 0, right = n-1;

        while(left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if(leftMax < rightMax) {
                result += leftMax - height[left];
                left++;
            }
            else {
                result += rightMax - height[right];
                right--;
            }
        }
        return result;
    }

    static int getDistinctCount(int[] arr) {
        //https://leetcode.com/problems/remove-duplicates-from-sorted-array/
        if(arr.length == 1) return 1;

        int i=0, j=1;
        int n = arr.length;

        while(j < n) {
            if(arr[i] != arr[j]) {
                i++;
                arr[i] = arr[j];
            }
            j++;
        }
        return i+1;
    }

    static int removeElement(int[] nums, int val) {
        //https://leetcode.com/problems/remove-element/
        int n = nums.length;
        if(n==0) return 0;

        int left=0;
        while(left<n && nums[left] != val) {
            left++;
        }
        if(left == n) return left;

        int right = left+1;
        while(right < n) {
            if(nums[right] == val) {
                right++;
            }
            else {
                nums[left] = nums[right];
                left++;
                right++;
            }
        }
        return left;
    }

    static int mySqrt(int x) {
        // https://leetcode.com/problems/sqrtx/
        // https://www.youtube.com/watch?v=eC0b6lUj84w
        if(x<2) return x;
        long left = 1;
        long right = x;
        long ans = -1;

        while(left <= right) {
            long mid = left + (right-left)/2;
            long sqr = mid * mid;
            if(sqr == x) {
                ans = mid;
                break;
            }
            if(sqr < x) {
                ans = mid;
                left = mid +1;
            }
            else {
                right = mid-1;
            }
        }
        return (int)ans;
    }

    public boolean isPerfectSquare(int num) {
        //https://leetcode.com/problems/valid-perfect-square/
        long left = 1, right = num;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (mid * mid == num) return true;
            if (mid * mid < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    static boolean judgeSquareSum(int c) {
        // https://leetcode.com/problems/sum-of-square-numbers/
        if(c < 0) return false;
        int mid = (int)Math.sqrt(c);
        long left = 0;
        long right = mid;
        while(left <= right) {
            long result = (left * left) + (right * right);
            if(result == c) {
                return true;
            }
            if(result > c) {
                right--;
            }
            else {
                left++;
            }
        }
        return false;
    }

    public int longestValidParentheses(String s) {
        // https://www.youtube.com/watch?v=qC5DGX0CPFA
        // https://leetcode.com/problems/longest-valid-parentheses/
        int n = s.length();
        if(n == 0) return 0;

        Stack<Character> charStack = new Stack<>();
        Stack<Integer> indexStack = new Stack<>();
        indexStack.push(-1);
        int result = 0;

        for(int i=0; i<n; i++) {
            char c= s.charAt(i);
            if(c == '(') {
                charStack.push(c);
                indexStack.push(i);
            }
            else {
                if(!charStack.isEmpty() && charStack.peek() == '(') {
                    charStack.pop();
                    indexStack.pop();
                    result = Math.max(result, i-indexStack.peek());
                }
                else{
                    indexStack.push(i);
                }
            }
        }
        return result;
    }

    public int searchRotatedArray(int[] nums, int target) {
        // https://leetcode.com/problems/search-in-rotated-sorted-array/
        // https://www.youtube.com/watch?v=1uu3g_uu8O0
        int n = nums.length;
        int left = 0, right = n-1;
        while(left <= right) {
            int mid = left + (right - left)/2;
            if(target == nums[mid]) {
                return mid;
            }
            else if(nums[left] <= nums[mid]){
                if(target >= nums[left] && target < nums[mid]) {
                    right = mid-1;
                }
                else{
                    left = mid+1;
                }
            }
            else {
                if(target > nums[mid] && target <= nums[right]) {
                    left = mid+1;
                }
                else{
                    right = mid-1;
                }
            }
        }
        return -1;
    }

    static int[] firstAndLastIndexSortedArray(int[] nums, int target) {
        //https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
        //https://www.youtube.com/watch?v=Y7LiLNdayF0
        int[] result = {-1, -1};
        int left = 0;
        int right = nums.length-1;
        //Finding the leftmost index
        while(left <= right) {
            int mid = (left + right)/2;
            if(nums[mid] == target) {
                result[0] = mid;
                right = mid-1;
            }
            else if(target > nums[mid]) {
                left = mid+1;
            }
            else {
                right = mid-1;
            }

        }
        left = 0;
        right = nums.length-1;
        //Finding the rightmost index
        while(left <= right) {
            int mid = (left + right)/2;
            if(nums[mid] == target) {
                result[1] = mid;
                left = mid+1;
            }
            else if(target > nums[mid]) {
                left = mid+1;
            }
            else {
                right = mid-1;
            }

        }
        return result;
    }

    static boolean checkIfValidSudokuEntry(char[][] board, int row, int column, char c) {
        for(int i=0; i<board[0].length; i++) {
            if(i!=column && board[row][i] == c) {
                return false;
            }
        }
        for(int i=0; i<board.length; i++) {
            if(i!=row && board[i][column] == c) {
                return false;
            }
        }
        int subMatrixRow = row/3 * 3;
        int subMatrixColumn = column/3 * 3;

        for(int i=subMatrixRow; i<subMatrixRow+3; i++) {
            for(int j=subMatrixColumn; j<subMatrixColumn+3; j++) {
                if(i != row && j != column && board[i][j] == c) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isValidSudoku(char[][] board) {
        // https://leetcode.com/problems/valid-sudoku/
        // https://github.com/naresh1406/youtube/blob/master/src/main/cp/leetcode/problems/_0036_Valid_Sudoku.java
        int rowLength = board.length;
        int colLength = board[0].length;

        for(int row=0; row<rowLength; row++) {
            for(int col=0; col<colLength; col++) {
                if(board[row][col] != '.') {
                    char c = board[row][col];
                    if(!checkIfValidSudokuEntry(board, row, col, c)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static boolean isValidSudokuMethod2(char[][] board) {
        //https://leetcode.com/problems/valid-sudoku/
        Set<String> set = new HashSet<>();
        for(int row=0; row<board.length; row++) {
            for(int col=0; col<board[row].length; col++) {
                char current = board[row][col];
                if(current != '.') {
                    if(!set.add(current + " found in row " + row) ||
                            !set.add(current + " found in col " + col) ||
                            !set.add(current + " found in block " + row/3 + " - " + col/3)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static int maxSubArray(int[] nums) {
        // https://leetcode.com/problems/maximum-subarray/
        // Kadane's Algorithm
        // https://www.youtube.com/watch?v=VMtyGnNcdPw
        int maxSum = nums[0];
        int currentSum = nums[0];
        for(int i=1; i<nums.length; i++) {
            if(currentSum >= 0) {
                currentSum += nums[i];
            }
            else {
                currentSum = nums[i];
            }

            if(currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        return maxSum;
    }

    public int maxProduct(int[] nums) {
        // https://leetcode.com/problems/maximum-product-subarray
        // https://www.youtube.com/watch?v=tHNsZHXnYd4
        /* As we know that on multiplying with negative number max will become min and min will become max,
            so why not as soon as we encounter negative element, we swap the max and min already. */
        int max = nums[0], min = nums[0], ans = nums[0];
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            // Swapping min and max
            if (nums[i] < 0){
                int temp = max;
                max = min;
                min = temp;
            }
            max = Math.max(nums[i], max * nums[i]);
            min = Math.min(nums[i], min * nums[i]);
            ans = Math.max(ans, max);
        }
        return ans;
    }

    public int firstMissingPositive(int[] nums) {
        // https://leetcode.com/problems/first-missing-positive
        // https://www.youtube.com/watch?v=QeBvfH1dpOU
        boolean one = false;
        int n = nums.length;
        for(int i=0; i<nums.length; i++){
            if(nums[i] == 1) {
                one = true;
            }
            else {
                if(nums[i] < 1 || nums[i] > n) {
                    nums[i] = 1;
                }
            }
        }

        if(!one) return 1;
        for(int i=0; i<n; i++) {
            int index = Math.abs(nums[i]);
            nums[index-1] = - Math.abs(nums[index-1]);
        }

        for(int i=0; i<n; i++) {
            if(nums[i] > 0) {
                return i+1;
            }
        }
        return n+1;
    }

    static ArrayList<Integer> spiralMatrixTraverse(int[][] arr, int r, int c)
    {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int top=0, down=arr.length-1, left=0, right=arr[0].length-1;
        int direction = 0; //0, 1, 2, 3

        while(top <= down && left <= right) {
            if(direction == 0) {
                // Move from left to right
                for(int i=left; i<=right; i++) {
                    result.add(arr[top][i]);
                }
                top++;
            }
            else if(direction == 1) {
                //Move from top to down
                for(int i=top; i<=down; i++) {
                    result.add(arr[i][right]);
                }
                right--;
            }
            else if(direction == 2) {
                //Move from right to left
                for(int i=right; i>=left; i--) {
                    result.add(arr[down][i]);
                }
                down--;
            }
            else if(direction == 3) {
                //Move form down to top
                for(int i=down; i>=top; i--) {
                    result.add(arr[i][left]);
                }
                left++;
            }
            direction = (direction + 1) % 4;
        }
        return result;
    }

    static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int top = 0, down = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        int direction = 0;
        int val = 1;

        while (top <= down && left <= right) {
            if (direction == 0) {
                // Move from left to right
                for (int i = left; i <= right; i++) {
                    matrix[top][i] = val++;
                }
                top++;
            } else if (direction == 1) {
                //Move from top to down
                for (int i = top; i <= down; i++) {
                    matrix[i][right] = val++;
                }
                right--;
            } else if (direction == 2) {
                //Move from right to left
                for (int i = right; i >= left; i--) {
                    matrix[down][i] = val++;
                }
                down--;
            } else if (direction == 3) {
                //Move form down to top
                for (int i = down; i >= top; i--) {
                    matrix[i][left] = val++;
                }
                left++;
            }
            direction = (direction + 1) % 4;
        }
        return matrix;
    }

    public void floodFillRecursive(int[][] image, int row, int col, int currentColour, int newColor) {
        if(row<0 || row>=image.length || col<0 || col>=image[0].length ||
                image[row][col] != currentColour || image[row][col] == newColor) {
            return;
        }
        image[row][col] = newColor;
        floodFillRecursive(image, row-1, col, currentColour, newColor);
        floodFillRecursive(image, row, col+1, currentColour, newColor);
        floodFillRecursive(image, row+1, col, currentColour, newColor);
        floodFillRecursive(image, row, col-1, currentColour, newColor);
    }

    public void floodFill(int[][] image, int sr, int sc, int newColor) {
        // https://leetcode.com/problems/flood-fill/
        floodFillRecursive(image, sr, sc, image[sr][sc], newColor);
        System.out.println(Arrays.deepToString(image));
    }

    public int basicCalculator(String s) {
        // https://www.youtube.com/watch?v=HUfUzA9Ekgo
        // https://leetcode.com/problems/basic-calculator
        int n = s.length();
        if(n == 0) return 0;

        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        int sign = +1;

        for(int i=0; i<n; i++) {
            char ch = s.charAt(i);
            if(Character.isDigit(ch)) {
                int val = 0;
                while(i < n && Character.isDigit(s.charAt(i))) {
                    val = val * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--;
                val = val * sign;
                sum += val;
                sign = +1;
            }
            else if(ch == '(') {
                stack.push(sum);
                stack.push(sign);
                sum = 0;
                sign = +1;
            }
            else if(ch == ')') {
                sum *= stack.pop();
                sum += stack.pop();
            }
            else if(ch == '-') {
                sign = sign * -1;
            }
        }
        return sum;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // https://leetcode.com/problems/next-greater-element-i/
        // https://www.youtube.com/watch?v=rSf9vPtKcmI
        // https://www.youtube.com/watch?v=XP8iEi9Aa8c
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        stack.push(nums2[nums2.length-1]);
        map.put(nums2[nums2.length-1], -1);

        for(int i=nums2.length-2; i>=0; i--) {
            while(!stack.isEmpty() && nums2[i] >= stack.peek()) {
                stack.pop();
            }

            if(stack.isEmpty()) {
                map.put(nums2[i], -1);
            }
            else {
                map.put(nums2[i], stack.peek());
            }
            stack.push(nums2[i]);
        }

        for(int k=0; k<nums1.length; k++) {
            nums1[k] = map.get(nums1[k]);
        }
        return nums1;
    }

    public int[] nextGreaterElementsII(int[] nums) {
        // https://www.youtube.com/watch?v=RkG94TvnUFs
        // https://leetcode.com/problems/next-greater-element-ii
        int[] result = new int[nums.length];
        Stack<Integer> stack = new Stack<>();

        for(int i=nums.length-2; i>=0; i--) {
            if(stack.size()>0 && nums[i] > stack.peek()) {
                stack.pop();
            }
            stack.push(nums[i]);
        }

        for(int i=nums.length-1; i>=0; i--) {
            while(!stack.isEmpty() && nums[i] >= stack.peek()) {
                stack.pop();
            }

            if(stack.isEmpty()) {
                result[i] = -1;
            }
            else {
                result[i] = stack.peek();
            }
            stack.push(nums[i]);
        }
        return result;
    }

    public int[] maxSlidingWindowOptimizedUsingDeque(int[] nums, int k) {
        // https://www.geeksforgeeks.org/sliding-window-maximum-maximum-of-all-subarrays-of-size-k/
        // https://www.youtube.com/watch?v=CZQGRp93K4k
        // https://leetcode.com/problems/sliding-window-maximum
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>();
        int i, index=0;

        for(i=0; i<k; i++) {
            while(!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
                dq.removeLast();
            }
            dq.addLast(i);
        }

        for(; i<n; i++) {
            result[index++] = nums[dq.peekFirst()];

            while(!dq.isEmpty() && dq.peekFirst() <= i-k) {
                dq.removeFirst();
            }

            while(!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
                dq.removeLast();
            }

            dq.addLast(i);
        }
        // Printing for Last Window
        result[index++] = nums[dq.peekFirst()];
        return result;
    }

    public static void slidingWindowMaximum(int[] arr, int k) {
        // https://www.geeksforgeeks.org/sliding-window-maximum-maximum-of-all-subarrays-of-size-k/
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        List<Integer> result = new ArrayList<>();
        int i=0;
        for(; i<k; i++) {
            queue.add(arr[i]);
        }
        result.add(queue.peek());
        queue.remove(arr[0]);

        for(; i<arr.length; i++) {
            queue.add(arr[i]);
            result.add(queue.peek());
            queue.remove(arr[i-k+1]);
        }
        System.out.println(result);
    }

    public static int divisibilityOfStrings(String s, String t) {
        // https://leetcode.com/discuss/interview-question/427484/mathworks
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

    public static ArrayList<Integer> minSubarrayBruteForce(ArrayList<Integer> arr, int n, int x)  {
        // https://www.codingninjas.com/codestudio/problems/minimum-subarray-with-required-sum_696460
        ArrayList<Integer> result = new ArrayList<>();
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        int minIndex = Integer.MAX_VALUE;
        int minLength = Integer.MAX_VALUE;
        for(int i=0; i<n; i++) {
            int sum = 0;
            ArrayList<Integer> current = new ArrayList<>();
            for(int j=i; j<n; j++) {
                int val = arr.get(j);
                sum += val;
                current.add(val);
                if(sum > x) {
                    int length = j-i+1;
                    if(length < minLength) {
                        minLength = length;
                        minIndex = i;
                        map.put(i, current);
                    }
                    break;
                }
            }
        }
        if(minIndex == Integer.MAX_VALUE){
            return result;
        }
        return map.get(minIndex);
    }

    public static ArrayList<Integer> minSubarraySlidingWindow(ArrayList<Integer> arr, int n, int x)  {
        // https://www.codingninjas.com/codestudio/problems/minimum-subarray-with-required-sum_696460
        // https://www.youtube.com/watch?v=NKoHjWl2m8Y
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        int minIndex = Integer.MAX_VALUE;
        int minLength = Integer.MAX_VALUE;
        int start = 0;
        int sum = 0;
        ArrayList<Integer> current = new ArrayList<>();
        for(int end=0; end<n; end++) {
            int val = arr.get(end);
            sum += val;
            current.add(val);
            while(sum > x && start <= end) {
                ArrayList<Integer> newCurrent = new ArrayList<>(current);
                map.put(start, newCurrent);
                int length = end - start + 1;
                if(length < minLength) {
                    minLength = length;
                    minIndex = start;
                }
                int startVal = arr.get(start);
                sum -= startVal;
                current.remove(Integer.valueOf(startVal));
                ++start;
            }
        }

        if(minIndex == Integer.MAX_VALUE){
            return new ArrayList<>();
        }

        return map.get(minIndex);
    }

    public int minDistance(String word1, String word2) {
        // https://leetcode.com/problems/edit-distance/
        // https://www.youtube.com/watch?v=tooMn-xfYCU
        if(word1.equals(word2)) return 0;
        if(word1.equals("")) return word2.length();
        if(word2.equals("")) return word1.length();

        if(word1.substring(0,1).equals(word2.substring(0,1))) {
            return minDistance(word1.substring(1), word2.substring(1));
        }
        int insert = minDistance(word1, word2.substring(1));
        int delete = minDistance(word1.substring(1), word2);
        int replace = minDistance(word1.substring(1), word2.substring(1));
        return 1 + Math.min(insert, Math.min(delete, replace));
    }

    public void swap(int[] nums, int i, int j) {
        int k = nums[i];
        nums[i] = nums[j];
        nums[j] = k;
    }

    public void reverseArrayFromIndex(int[] nums, int index) {
        int i = index;
        int j = nums.length -1;
        while(i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public void nextPermutation(int[] nums) {
        // https://www.youtube.com/watch?v=IhsUbEMfIbY
        // https://leetcode.com/problems/next-permutation
        int n = nums.length;
        if(n<2) return;

        int i = n-2;
        // Get the swap position
        while(i>=0 && nums[i] >= nums[i+1]) {
            i--;
        }
        if(i >= 0) {
            // Find the next greater number of ith position and swap
            int j = n-1;
            while(j>=0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        // reverse the number after the ith position till last of array
        reverseArrayFromIndex(nums, i+1);
    }

    public int findMaxConsecutiveOnesWithOneSwap(int[] nums) {
        // https://www.youtube.com/watch?v=ix_TazzqHn0
        // https://www.lintcode.com/problem/883/
        int ans = 0;
        int count = 0;
        int j = -1;

        for(int i=0; i<nums.length; i++) {
            if(nums[i] == 0) {
                count++;
            }

            while(count > 1) {
                j++;
                if(nums[j] == 0) {
                    count--;
                }
            }

            int len = i - j;
            ans = Math.max(ans, len);
        }
        return ans;
    }

    public int longestOnes(int[] arr, int k) {
        // https://leetcode.com/problems/max-consecutive-ones-iii
        // https://www.youtube.com/watch?v=QPfalDbqa4A
        int j=-1, ans=0, count=0;
        for(int i=0; i<arr.length; i++) {
            if(arr[i] == 0) {
                count++;
            }

            while(count > k) {
                j++;

                if(arr[j] == 0) {
                    count--;
                }
            }
            int len = i-j;
            ans = Math.max(ans, len);
        }
        return ans;
    }

    public static int connectRopes(int[] lengths, int n) {
        // https://www.codingninjas.com/codestudio/problems/connect-ropes_975278
        // https://www.youtube.com/watch?v=Eb1A6nm_Nic
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i : lengths) {
            pq.add(i);
        }
        int ans = 0;
        while(pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();
            int sum = first + second;
            ans += sum;
            pq.add(sum);
        }
        return ans;
    }

    public int maximumProduct(int[] nums) {
        // https://leetcode.com/problems/maximum-product-of-three-numbers
        // https://www.youtube.com/watch?v=fQF7tAmMKHE
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for(int i=0; i<nums.length; i++) {
            if(nums[i] > max1) {
                max3 = max2;
                max2 = max1;
                max1 = nums[i];
            }
            else if(nums[i] > max2) {
                max3 = max2;
                max2 = nums[i];
            }
            else if(nums[i] > max3){
                max3 = nums[i];
            }

            if(nums[i] < min1) {
                min2 = min1;
                min1 = nums[i];
            }
            else if(nums[i] < min2){
                min2 = nums[i];
            }
        }

        int product1 = max1 * max2 * max3;
        int product2 = min1 * min2 * max1;

        return Math.max(product1, product2);
    }

    public int[] productExceptSelf(int[] nums) {
        // https://leetcode.com/problems/product-of-array-except-self
        // https://www.youtube.com/watch?v=UBkpyXgx0g0
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        int[] result = new int[nums.length];

        int prod = 1;
        // Filling left subarray product
        for(int i=0; i<nums.length; i++) {
            prod = prod * nums[i];
            left[i] = prod;
        }

        // Filling right subarray product
        prod = 1;
        for(int i=nums.length-1; i>=0; i--) {
            prod = prod * nums[i];
            right[i] = prod;
        }

        prod = 1;
        result[0] = right[1];
        result[nums.length-1] = left[nums.length-2];
        for(int i=1; i<nums.length-1; i++) {
            result[i] = left[i-1] * right[i+1];
        }
        return result;
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // https://leetcode.com/problems/subarray-product-less-than-k/
        // https://www.youtube.com/watch?v=bZQI58quN6E
        if(k<=1) return 0;
        int left=0, prod=1, count = 0;

        for(int i=0; i<nums.length; i++) {
            prod = prod * nums[i];
            while(prod >= k) {
                prod = prod / nums[left];
                left++;
            }
            count += (i - left + 1);
        }
        return count;
    }

    public int consecutiveNumbersSum(int n) {
        // https://leetcode.com/problems/consecutive-numbers-sum
        // https://www.youtube.com/watch?v=EiC2eIlYu_w
        int count = 0;
        for(int k = 1; 2*n > k*(k-1); k++){
            int numerator = n - (k*(k-1)/2);
            if(numerator % k == 0) {
                count++;
            }
        }
        return count;
    }

    public void nextMatch(Queue<String> queue, String current, Map<String, Boolean> visited) {
        for(int i=0; i<current.length(); i++) {
            char[] arr = current.toCharArray();
            for(int j=0; j<26; j++) {
                char c = (char)('a' + j);
                arr[i] = c;
                String nextWord = String.valueOf(arr);
                if(visited.containsKey(nextWord) && !visited.get(nextWord)) {
                    queue.add(nextWord);
                    visited.put(nextWord, true);
                }
            }
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // https://leetcode.com/problems/word-ladder/
        // https://www.youtube.com/watch?v=nVH5hyVSXa8
        if(!wordList.contains(endWord)) return 0;

        Map<String, Boolean> visited = new HashMap<>();
        for(String word : wordList) {
            visited.put(word, false);
        }

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        visited.put(beginWord, true);
        int length = 1;

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i<size; i++) {
                String current = queue.remove();
                if(current.equals(endWord)) {
                    return length;
                }
                nextMatch(queue, current, visited);
            }
            length++;
        }
        return 0;
    }

    public double findMedianSortedArrays(int[] a, int[] b) {
        // https://leetcode.com/problems/median-of-two-sorted-arrays
        // https://www.youtube.com/watch?v=jDJuW7tSxio
        if(a.length > b.length) {
            int[] temp = a;
            a = b;
            b = temp;
        }

        int low = 0;
        int high = a.length;
        int totalLength = a.length + b.length;

        while(low <= high) {
            // here we will assume elements before mid index
            int a_mid_index = (low + high)/2;
            int b_mid_index = (totalLength+1)/2 - a_mid_index;

            int a_left = (a_mid_index == 0) ? Integer.MIN_VALUE : a[a_mid_index - 1];
            int a_mid = (a_mid_index == a.length) ? Integer.MAX_VALUE : a[a_mid_index];
            int b_left = (b_mid_index == 0) ? Integer.MIN_VALUE : b[b_mid_index - 1];
            int b_mid = (b_mid_index == b.length) ? Integer.MAX_VALUE : b[b_mid_index];

            if(a_left <= b_mid && b_left <= a_mid) {
                double median = 0.0;
                if(totalLength % 2 == 0) {
                    int lmax = Math.max(a_left, b_left);
                    int rmin = Math.min(a_mid, b_mid);
                    median = (lmax + rmin) / 2.0;
                }
                else {
                    median = Math.max(a_left, b_left);
                }
                return median;
            }
            else {
                if(a_left > b_mid) {
                    high = a_mid_index - 1;
                }
                else if(b_left > a_mid) {
                    low = a_mid_index + 1;
                }
            }
        }
        return 0.0;
    }

    public String expandPallindrome(String s, int left, int right) {
        while(left>=0 && right<s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left+1, right);
    }

    public String longestPalindrome(String s) {
        // https://leetcode.com/problems/longest-palindromic-substring
        // https://www.youtube.com/watch?v=8npXbrIH56A
        String lps = "";
        for(int i=0; i<s.length(); i++) {
            String odd = expandPallindrome(s, i, i);
            String even = expandPallindrome(s, i, i+1);
            if(odd.length() > lps.length()) {
                lps = odd;
            }
            if(even.length() > lps.length()) {
                lps = even;
            }
        }
        return lps;
    }

    public List<List<Integer>> twoSum(int[] nums, int start, int end, int target) {
        // https://www.youtube.com/watch?v=l5Ruk_Ub8B4

        // Arrays.sort(nums);
        int left = start;
        int right = end;
        List<List<Integer>> result = new ArrayList<>();
        while(left < right) {
            if(left != start && nums[left] == nums[left-1]) {
                left++;
                continue;
            }
            int sum  = nums[left] + nums[right];
            if(sum == target) {
                List<Integer> sub = new ArrayList<>();
                sub.add(nums[left]);
                sub.add(nums[right]);
                result.add(new ArrayList<Integer>(sub));
                left++;
                right--;
            }
            else if(sum > target) {
                right--;
            }
            else {
                left++;
            }
        }
        return result;
    }

    public List<List<Integer>> threeSum(int[] nums, int target) {
        // https://leetcode.com/problems/3sum/
        // https://www.youtube.com/watch?v=1IBgYGJqKP8
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if(n < 3) return result;

        Arrays.sort(nums);
        for(int i=0; i<=n-3; i++) {
            if(i != 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int val1 = nums[i];
            int rem = target - val1;
            List<List<Integer>> subSum = twoSum(nums, i+1, n-1, rem);
            for(List<Integer> sub : subSum) {
                sub.add(val1);
                result.add(sub);
            }
        }
        return result;
    }

    public String largestNumber(int[] nums) {
        // https://leetcode.com/problems/largest-number
        // https://www.youtube.com/watch?v=VV_KPrG_PzE
        int n = nums.length;
        String[] arr = new String[n];

        int countZero = 0;
        for(int i=0; i<n; i++) {
            if(nums[i] == 0) {
                countZero++;
            }
            arr[i] = String.valueOf(nums[i]);
            // arr[i] = nums[i] + "";
        }

        if(countZero == n) {
            return "0";
        }

        Arrays.sort(arr, (a, b) -> {
            long n1 = Long.parseLong(a + b);
            long n2 = Long.parseLong(b + a);

            if(n1 > n2) {
                return 1;
            }
            else if(n1 < n2) {
                return -1;
            }
            else {
                return 0;
            }
        });

        StringBuilder sb = new StringBuilder();
        for(int i=n-1; i>=0; i--) {
            sb.append(arr[i]);
        }

        return sb.toString();
    }

    String printLargest(String[] arr) {
        // https://www.geeksforgeeks.org/given-an-array-of-numbers-arrange-the-numbers-to-form-the-biggest-number/
        Arrays.sort(arr, new Comparator<String>() {
            public int compare(String s1, String s2) {
                String XY = s1 + s2;
                String YX = s2 + s1;

                return YX.compareTo(XY);
            }
        });

        StringBuilder sb = new StringBuilder();
        for(String s : arr) {
            sb.append(s);
        }

        return sb.toString();
    }

    public int findUnsortedSubarray(int[] nums) {
        // https://leetcode.com/problems/shortest-unsorted-continuous-subarray
        // https://www.youtube.com/watch?v=Z4DJwKEFkNI
        int n = nums.length;
        int start = 0, end = -1;
        int max = nums[0];
        int min = nums[n-1];

        // Traversing from left to right to get end index
        for(int i=1; i<n; i++) {
            max = Math.max(max, nums[i]);
            if(nums[i] < max) {
                end = i;
            }
        }

        // Traversing from right to left to get start index
        for(int i=n-2; i>=0; i--) {
            min = Math.min(min, nums[i]);
            if(nums[i] > min) {
                start = i;
            }
        }

        return end - start + 1;
    }

    public String multiplyString(String num1, String num2) {
        // https://leetcode.com/problems/multiply-strings
        // https://www.youtube.com/watch?v=5NdhK3tZViQ
        if(num1.equals("0") || num2.equals("0")) return "0";

        int l1 = num1.length();
        int l2 = num2.length();
        int[] result = new int[l1 + l2];
        int i = l2-1;
        int endIndex = 0;
        while(i >= 0) {
            int i_val = num2.charAt(i) -'0';
            i--;
            int carry = 0;
            int k = result.length - 1 - endIndex;
            int j = l1-1;
            while(j >= 0 || carry != 0) {
                int j_val = j>=0 ? num1.charAt(j)-'0' : 0;
                j--;
                int prod = i_val * j_val  + carry + result[k];
                result[k] = prod % 10;
                carry = prod / 10;
                k--;
            }
            endIndex++;
        }
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for(int val : result) {
            if(val == 0 && flag == false) {
                continue;
            }
            else {
                sb.append(val);
                flag = true;
            }
        }
        return sb.toString();
    }

    public boolean increasingTriplet(int[] nums) {
        // https://leetcode.com/problems/increasing-triplet-subsequence
        // https://www.youtube.com/watch?v=xZ5FBqk-cFw
        int n = nums.length;
        if(n < 3) return false;

        int[] leftMinArray = new int[n];
        leftMinArray[0] = nums[0];
        for(int i=1; i<n; i++) {
            leftMinArray[i] = Math.min(nums[i], leftMinArray[i-1]);
        }

        int[] rightMaxArray = new int[n];
        rightMaxArray[n-1] = nums[n-1];
        for(int i=n-2; i>=0; i--) {
            rightMaxArray[i] = Math.max(nums[i], rightMaxArray[i+1]);
        }

        for(int i=0; i<n; i++) {
            int left = leftMinArray[i];
            int val = nums[i];
            int right = rightMaxArray[i];

            if(val>left && right>val) {
                return true;
            }
        }
        return false;
    }

    public int subarraySumEqualsK(int[] nums, int k) {
        // https://leetcode.com/problems/subarray-sum-equals-k
        // https://www.youtube.com/watch?v=20v8zSo2v18
        int count = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(sum, 1);

        for(int i=0; i<nums.length; i++) {
            sum += nums[i];
            if(map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum ,0) + 1);
        }
        return count;
    }

    public int pivotIndex(int[] nums) {
        // https://leetcode.com/problems/find-pivot-index/
        int n = nums.length;
        int[] leftSum = new int[n];
        int[] rightSum = new int[n];

        leftSum[0] = nums[0];
        for(int i=1; i<n; i++) {
            leftSum[i] = leftSum[i-1] + nums[i];
        }

        rightSum[n-1] = nums[n-1];
        for(int i=n-2; i>=0; i--) {
            rightSum[i] = rightSum[i+1] + nums[i];
        }

        int index = -1;

        for(int i=0; i<n; i++) {
            if(i == 0) {
                int left = 0;
                int right = rightSum[i+1];
                if(left == right) {
                    index = i;
                    break;
                }
            }

            else if(i == n-1) {
                int left = leftSum[n-2];
                int right = 0;
                if(left == right) {
                    index = i;
                    break;
                }
            }
            else {
                int left = leftSum[i-1];
                int right = rightSum[i+1];
                if(left == right) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    public int findKDiffPairs(int[] nums, int k) {
        // https://leetcode.com/problems/k-diff-pairs-in-an-array/
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;

        for(int x : nums) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        int count = 0;

        if(k == 0) {
            for(int key : map.keySet()) {
                if(map.get(key) > 1) {
                    count++;
                }
            }
        }
        else {
            for(int key : map.keySet()) {
                if(map.containsKey(key + k)) {
                    count++;
                }

            }
        }
        return count;
    }

    public void combinationSumBacktrack(List<List<Integer>> result, List<Integer> current, int currentSum, int index, int[] candidates, int target) {
        if(currentSum == target) {
            result.add(new ArrayList<Integer>(current));
            return;
        }

        if(currentSum > target) {
            return;
        }

        for(int i=index; i<candidates.length; i++) {
            if(i > index && candidates[i] == candidates[i-1]) {
                continue;
            }

            current.add(candidates[i]);
            combinationSumBacktrack(result, current, currentSum + candidates[i], i+1, candidates, target);
            current.remove(current.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // https://www.youtube.com/watch?v=dvZdWR0sHMk
        Arrays.sort(candidates);

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        int index = 0;
        int currentSum = 0;

        combinationSumBacktrack(result, current, currentSum, index, candidates, target);
        return result;

    }

    public void backtrackSubset(List<List<Integer>> result,
                                List<Integer> current, int index, int[] nums) {

        result.add(new ArrayList<Integer>(current));
        for(int i=index; i<nums.length; i++) {
            current.add(nums[i]);
            backtrackSubset(result, current, i+1, nums);
            current.remove(current.size() - 1);
        }
    }

    public List<List<Integer>> subsetsOrPowersetMethod2(int[] nums) {
        // One good method helpful in other problems
        // https://leetcode.com/problems/subsets/
        //https://www.youtube.com/watch?v=kYY9DotIKlo
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        int index = 0;
        backtrackSubset(result, current, index, nums);
        return result;
    }

    public String addStrings(String num1, String num2) {
        // https://leetcode.com/problems/add-strings/

        if(num1 == null || num1.length() == 0) return num2;
        if(num2 == null || num2.length() == 0) return num1;

        int sum = 0;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        int index1 = num1.length() - 1;
        int index2 = num2.length() - 1;

        while(index1 >= 0 || index2 >= 0) {
            int val1 = index1>=0 ? num1.charAt(index1)-'0' : 0;
            int val2 = index2>=0 ? num2.charAt(index2)-'0' : 0;
            sum = val1 + val2 + carry;
            sb.append(String.valueOf(sum%10));
            carry = sum / 10;
            index1--;
            index2--;
        }

        if(carry != 0) {
            sb.append(String.valueOf(carry));
        }

        return sb.reverse().toString();
    }

    public int minimumDeleteSum(String s1, String s2) {
        // https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
        // Concept: https://www.youtube.com/watch?v=GPePWKCEy24
        // Better Code: https://www.youtube.com/watch?v=rRPbJL8wsMI
        int n1 = s1.length();
        int n2 = s2.length();

        if(n1 == 0 && n2 == 0) return 0;

        int[][] dp = new int[n1+1][n2+1];

        for(int i=0; i<dp.length; i++) {
            for(int j=0; j<dp[0].length; j++) {
                if(i==0 && j==0) {
                    dp[i][j] = 0;
                }
                else if(i==0) {
                    dp[i][j] = (int)s2.charAt(j-1) + dp[i][j-1];
                }
                else if(j==0) {
                    dp[i][j] = (int)s1.charAt(i-1) + dp[i-1][j];
                }
                else {
                    if(s1.charAt(i-1) == s2.charAt(j-1)) {
                        dp[i][j] = dp[i-1][j-1];
                    }
                    else {
                        dp[i][j] = Math.min(dp[i-1][j] + s1.charAt(i-1),
                                dp[i][j-1] + s2.charAt(j-1));
                    }
                }
            }
        }

        return dp[dp.length-1][dp[0].length-1];
    }

    public int minDistanceAscii(String s1, String s2) {
        // Only small diff from below problem
        // https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
        int n1 = s1.length();
        int n2 = s2.length();

        if(n1 == 0 && n2 == 0) return 0;

        int[][] dp = new int[n1+1][n2+1];

        for(int i=0; i<dp.length; i++) {
            for(int j=0; j<dp[0].length; j++) {
                if(i==0 && j==0) {
                    dp[i][j] = 0;
                }
                else if(i==0) {
                    dp[i][j] = 1 + dp[i][j-1];
                }
                else if(j==0) {
                    dp[i][j] = 1 + dp[i-1][j];
                }
                else {
                    if(s1.charAt(i-1) == s2.charAt(j-1)) {
                        dp[i][j] = dp[i-1][j-1];
                    }
                    else {
                        dp[i][j] = Math.min(dp[i-1][j] + 1, dp[i][j-1] + 1);
                    }
                }
            }
        }

        return dp[dp.length-1][dp[0].length-1];
    }

    public int findPairs(int[] nums, int k) {
        // https://leetcode.com/problems/k-diff-pairs-in-an-array/
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;

        for(int x : nums) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        int count = 0;

        if(k == 0) {
            for(int key : map.keySet()) {
                if(map.get(key) > 1) {
                    count++;
                }
            }
        }
        else {
            for(int key : map.keySet()) {
                if(map.containsKey(key + k)) {
                    count++;
                }

            }
        }
        return count;
    }

    public boolean regularExpressionMatch(String s, String p) {
        // https://leetcode.com/problems/regular-expression-matching/
        // https://www.youtube.com/watch?v=DJvw8jCmxUU
        int sLength = s.length();
        int pLength = p.length();

        boolean[][] dp = new boolean[pLength+1][sLength+1];
        for(int i=0; i<dp.length; i++) {
            for(int j=0; j<dp[i].length; j++) {
                if(i==0 && j==0) {
                    dp[i][j] = true;
                }
                else if(i==0) {
                    dp[i][j] = false;
                }
                else if(j==0) {
                    if(p.charAt(i-1) == '*') {
                        dp[i][j] = (i-2 >= 0) ? dp[i-2][j] : true;
                    }
                    else {
                        dp[i][j] = false;
                    }
                }
                else {
                    char pChar = p.charAt(i-1);
                    char sChar = s.charAt(j-1);

                    if(pChar == '*') {
                        dp[i][j] = dp[i-2][j];

                        char pSecondLastChar = p.charAt(i-2);
                        if(pSecondLastChar == '.' || pSecondLastChar == sChar) {
                            dp[i][j] = dp[i][j] || dp[i][j-1];
                        }

                    }
                    else if(pChar == '.') {
                        dp[i][j] = dp[i-1][j-1];
                    }
                    else if(pChar == sChar) {
                        dp[i][j] = dp[i-1][j-1];
                    }
                    else {
                        dp[i][j] = false;
                    }
                }
            }
        }

        return dp[pLength][sLength];
    }

    public boolean wildcardMatch(String s, String p) {
        // https://leetcode.com/problems/wildcard-matching/
        // https://www.youtube.com/watch?v=NbgUZAoIz3g
        int sLength = s.length();
        int pLength = p.length();

        boolean[][] dp = new boolean[pLength+1][sLength+1];
        for(int i=0; i<dp.length; i++) {
            for(int j=0; j<dp[i].length; j++) {
                if(i==0 && j==0) {
                    dp[i][j] = true;
                }
                else if(i==0) {
                    dp[i][j] = false;
                }
                else if(j==0) {
                    if(p.charAt(i-1) == '*') {
                        dp[i][j] = dp[i-1][j];
                    }
                    else {
                        dp[i][j] = false;
                    }
                }
                else {
                    char pChar = p.charAt(i-1);
                    char sChar = s.charAt(j-1);

                    if(pChar == '*') {
                        for(int k=j; k>=0; k--) {
                            if(dp[i - 1][k]) {
                                dp[i][j] = true;
                                break;
                            }
                        }
                    }
                    else if(pChar == '?') {
                        dp[i][j] = dp[i-1][j-1];
                    }
                    else if(pChar == sChar) {
                        dp[i][j] = dp[i-1][j-1];
                    }
                    else {
                        dp[i][j] = false;
                    }
                }
            }
        }

        return dp[pLength][sLength];
    }

    public int maximumGap(final List<Integer> A) {
        // max distance
        // https://www.geeksforgeeks.org/given-an-array-arr-find-the-maximum-j-i-such-that-arrj-arri/
        // https://www.youtube.com/watch?v=Zyhxzg0WLWA
        int n = A.size();
        if(n < 2) {
            return 0;
        }

        int[] left = new int[n];
        left[0] = A.get(0);
        for(int i=1; i<n; i++) {
            left[i] = Math.min(left[i-1], A.get(i));
        }

        int[] right = new int[n];
        right[n-1] = A.get(n-1);
        for(int i=n-2; i>=0; i--) {
            right[i] = Math.max(right[i+1], A.get(i));
        }

        int ans = Integer.MIN_VALUE;
        int x=0, y=0;

        while(x<n && y<n) {
            if(left[x] <= right[y]) {
                ans = Math.max(ans, y-x);
                y++;
            }
            else {
                x++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6};
        System.out.println(getDistinctCount(arr));
    }
}
