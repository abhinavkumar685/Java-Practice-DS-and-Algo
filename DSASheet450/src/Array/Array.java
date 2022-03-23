package Array;

import java.util.*;

public class Array {
    static void reverseArray(int[] arr, int start, int end)
    {
        int temp;
        while (start < end)
        {
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static int kthSmallest(int[] arr, int l, int r, int k) {
         /*
            Arrays.sort(arr,l,r+1);
            return arr[l+k-1];
        */
        Queue<Integer> pq = new PriorityQueue<>();
        for(int a : arr) {
            pq.add(a);
        }
        for(int i=1; i<k; i++) {
            pq.remove();
        }
        return pq.remove();
    }

    public static void sort012(int[] a, int n) {
        int count0 = 0, count1=0, count2=0;
        for(int i : a) {
            if(i == 0) {
                count0++;
            }
            else if(i ==1) {
                count1++;
            }
            else if(i ==2) {
                count2++;
            }
        }
        int index = 0;
        while(count0>0) {
            a[index++] = 0;
            count0--;
        }
        while(count1>0) {
            a[index++] = 1;
            count1--;
        }
        while(count2>0) {
            a[index++] = 2;
            count2--;
        }
    }

    public void dutchFlagProblem(int[] arr) {
        // Sort 0s, 1s and 2s
        // https://www.youtube.com/watch?v=MbV26HWqxrs
        int j = 0;      // 0 to j-1 --> 0s
        int i = 0;      // j to i-1 --> 1s
        int k = arr.length - 1;     // k+1 to end --> 2s
        // i to k --> unknowns
        while(i <= k) {
            if(arr[i] == 0) {
                swap(arr, i, j);
                i++;
                j++;
            }
            else if(arr[i] == 1) {
                i++;
            }
            else {
                // arr[i] == 2
                swap(arr, i, k);
                k--;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void moveNegativeToStart(int[] arr) {
        // Logic of partition of Quick Sort using pivot as 0
        int i=0, j=0, pivot = 0;
        while(i < arr.length) {
            if(arr[i] > pivot) {
                i++;
            }
            else {
                swap(arr, i, j);
                i++;
                j++;
            }
        }
    }

    public void rotateClockWiseBy1(int[] arr, int n) {
        if(n == 1) return;

        int lastElement = arr[n - 1];
        for(int i=n-1; i>=1; i--) {
            arr[i] = arr[i-1];

        }
        arr[0] = lastElement;
    }

    public static long maxSubarraySum(int[] arr, int n){
        // Kadane's Algorithm
        // https://www.youtube.com/watch?v=VMtyGnNcdPw
        int max = arr[0];
        int current = arr[0];
        for(int i=1; i<n; i++) {
            if(current >= 0 ) {
                current += arr[i];
            }
            else {
                current = arr[i];
            }
            max = Math.max(current, max);
        }
        return max;
    }

    int minimizeHeightI(int[] arr, int n, int k) {
        // https://www.youtube.com/watch?v=tSLiZAQG9NM
        Arrays.sort(arr);
        int max = arr[n-1];
        int min = arr[0];
        int diff = max - min;

        for(int i=1; i<n; i++) {
            max = Math.max(arr[i-1]+k, arr[n-1]-k);
            min = Math.min(arr[i]-k, arr[0]+k);
            diff = Math.min(diff, max-min);
        }
        return diff;
    }

    int getMinDiff(int[] arr, int n, int k) {
        // https://www.youtube.com/watch?v=o9WG7t6EKZo
        Arrays.sort(arr);
        int min = arr[0];
        int max = arr[n-1];
        int diff = max - min;
        for(int i=1; i<n; i++) {
            if(arr[i] >= k) {
                max = Math.max(arr[i-1]+k, arr[n-1]-k);
                min = Math.min(arr[i]-k, arr[0]+k);
                diff = Math.min(diff, max-min);
            }
        }
        return diff;
    }

    static int minJumps(int[] arr){
        // https://www.youtube.com/watch?v=phgjL7SbsWs
        int n = arr.length;
        if(n == 1) return 0;

        Integer[] dp = new Integer[n];
        dp[n-1] = 0;
        dp[0] = -1;
        for(int i=n-2; i>=0; i--) {
            int steps = arr[i];
            int min = Integer.MAX_VALUE;
            for(int j=1; j<=steps && i+j < n; j++) {
                if(dp[i+j] != null && dp[i+j] < min) {
                    min = dp[i+j];
                }
            }
            if(min != Integer.MAX_VALUE) {
                dp[i] = 1 + min;
            }
        }
        return dp[0];
    }

    public int findDuplicate(int[] nums) {
        // https://leetcode.com/problems/find-the-duplicate-number
        // https://www.youtube.com/watch?v=wjYnzkAhcNk
        int slow = 0;
        int fast = 0;
        while(true) {
            slow = nums[slow];
            // Advancing two times
            fast = nums[nums[fast]];
            if(slow == fast) {
                break;
            }
        }

        int slow2 = 0;
        while(true) {
            slow = nums[slow];
            slow2 = nums[slow2];
            if(slow == slow2) {
                break;
            }
        }
        return slow;
    }

    public void mergeTwoSortedArraysWithoutExtraSpace(int[] arr1, int[] arr2, int n, int m) {
        // https://www.youtube.com/watch?v=hVl2b3bLzBw
        // Use Gap Algorithm to fully optimize in NlogN (see video above)
        for(int index=0; index<n; index++) {
            boolean swap = false;
            if(arr1[index] > arr2[0]) {
                // swap elements between two arrays
                swap = true;
                int temp = arr1[index];
                arr1[index] = arr2[0];
                arr2[0] = temp;
            }
            if(swap) {
                // This flag is to optimize. Sort only when there is value swap.
                // Do insertion sort in the second array towards right side.
                for(int i=1; i<arr2.length; i++) {
                    if(arr2[i] < arr2[i-1]) {
                        // swap element
                        int temp = arr2[i];
                        arr2[i] = arr2[i-1];
                        arr2[i-1] = temp;
                    }
                }
            }
        }
    }

    public int[][] mergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, (int[] arr1, int[] arr2) -> {
            return arr1[0] == arr2[0] ? arr1[1] - arr2[1] : arr1[0] - arr2[0];
        });

        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);
        for(int[] current : intervals) {
            int[] previous = result.get(result.size()-1);
            if(previous[1] >= current[0]) {
                previous[1] = Math.max(previous[1], current[1]);
            }
            else {
                result.add(current);
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    public void nextPermutation(int[] nums) {
        // https://leetcode.com/problems/next-permutation
        // https://www.youtube.com/watch?v=LuLCLgMElus
        int n = nums.length;
        if(n < 2) return;

        int index = n-2;
        while(index >= 0 && nums[index] >= nums[index+1]) {
            index--;
        }

        if(index >= 0) {
            int j = n-1;
            while(j > index && nums[j] <= nums[index]) {
                j--;
            }

            // Swap between i & j
            int temp = nums[index];
            nums[index] = nums[j];
            nums[j] = temp;
        }

        // reversing array from i+1 to last index
        int left = index+1;
        int right = n-1;
        while(left < right) {
            int temp1 = nums[left];
            nums[left] = nums[right];
            nums[right] = temp1;
            left++;
            right--;
        }
    }

    static long inversionCount = 0;
    static long[] mergeTwoSortedArray(long[] arr1, long[] arr2) {
        int i=0, j=0, k=0;
        long[] merged = new long[arr1.length + arr2.length];

        while(i<arr1.length && j<arr2.length) {
            if(arr1[i] <= arr2[j]) {
                merged[k] = arr1[i];
                i++;
                k++;
            }
            else {
                // if arr1 element is greater than all element in right of that element is also greater
                inversionCount += arr1.length - i;
                merged[k] = arr2[j];
                j++;
                k++;
            }
        }

        while(i < arr1.length) {
            merged[k] = arr1[i];
            i++;
            k++;
        }

        while(j < arr2.length) {
            merged[k] = arr2[j];
            j++;
            k++;
        }

        return merged;
    }

    static long[] mergeSort(long[] arr, int left, int right) {
        if(left == right) {
            long[] res = new long[1];
            res[0] = arr[left];
            return res;
        }

        int mid = (left + right) / 2;
        long[] leftArray = mergeSort(arr, left, mid);
        long[] rightArray = mergeSort(arr, mid+1, right);
        return mergeTwoSortedArray(leftArray, rightArray);
    }

    static long inversionCount(long[] arr, long N) {
        // https://www.youtube.com/watch?v=uojx--MK_n0
        int left = 0;
        int right = arr.length - 1;
        long[] sorted = mergeSort(arr, left, right);
        return inversionCount;

    }

    static int maxProfitWithSingleTransaction(int[] prices) {
        // https://leetcode.com/problems/best-time-to-buy-and-sell-stock
        // https://www.youtube.com/watch?v=4YjEHmw1MX0
        int n = prices.length;
        int leastPrice = Integer.MAX_VALUE; // least price so far
        int maxProfit = 0;
        int currentProfit = 0;
        for (int price : prices) {
            if (price < leastPrice) {
                leastPrice = price;
            }
            currentProfit = price - leastPrice;
            if (currentProfit > maxProfit) {
                maxProfit = currentProfit;
            }
        }
        return maxProfit;
    }

    static int getPairsCount(int[] arr, int n, int k) {
        // https://www.youtube.com/watch?v=5L9Jrehvoew
        // Should be done in O(n) hence not sorting
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i : arr) {
            int complement = k - i;
            if(map.containsKey(complement)) {
                int frequency = map.get(complement);
                count += frequency;
            }

            map.putIfAbsent(i, 0);
            map.put(i, map.get(i) + 1);
        }
        return count;
    }

    static ArrayList<Integer> commonElements(int[] A, int[] B, int[] C, int n1, int n2, int n3) {
        // https://www.youtube.com/watch?v=ajWCEu1razQ
        ArrayList<Integer> result = new ArrayList<>();
        int i=0, j=0, k=0;
        while(i<n1 && j<n2 && k<n3) {
            if(A[i] == B[j] && B[j] == C[k]) {
                result.add(A[i]);
                i++;
                j++;
                k++;
            }
            else if(A[i] < B[j]) {
                i++;
            }
            else if(B[j] < C[k]) {
                j++;
            }
            else {
                k++;
            }

            // Taking care of duplicates
            while(i>0  && i<n1 && A[i] == A[i-1]) {
                i++;
            }
            while(j>0 && j<n2 && B[j] == B[j-1]) {
                j++;
            }
            while(k>0 && k<n3 && C[k] == C[k-1]) {
                k++;
            }
        }
        return result;
    }

    static void alternatePositiveAndNegativeValue(int[] arr){
        // https://www.youtube.com/watch?v=-z9pFUaUsGQ
        int low = 0;
        int n = arr.length;
        int high = arr.length - 1;
        while(low < high) {
            while(low <n &&  arr[low] >= 0) low++;
            while(high>=0 && arr[high] < 0) high--;
            if(low < high) {
                swap(arr, low, high);
            }
        }
        // low pointer will be on first negative number
        if(low==0 || low==n) {
            return;
        }
        int k=0;
        while(k<n && low<n) {
            swap(arr, k, low);
            k+=2;
            low++;
        }
    }

    static boolean findSubarrayWithSumZero(int[] arr,int n) {
        // https://www.youtube.com/watch?v=QtbFXZrEZYc
        // O(n)
        int prefixSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<n; i++) {
            prefixSum += arr[i];
            if(prefixSum==0 || arr[i]==0 || map.containsKey(prefixSum)) {
                return true;
            }
            else {
                map.putIfAbsent(prefixSum, 0);
                map.put(prefixSum, map.get(prefixSum) + 1);
            }
        }
        return false;
    }

    static ArrayList<Integer> factorial(int N){
        // https://www.youtube.com/watch?v=vxPBrr5x2jk
        // If ArrayList is not used then use Linked List and add carry at its head.
        ArrayList<Integer> result = new ArrayList<>();
        int carry = 0, size = 0;
        result.add(0, 1);
        size = 1;
        int val = 2;
        while(val <= N) {
            for(int index=size-1; index>=0; index--) {
                int temp = result.get(index) * val + carry;
                result.set(index, temp%10);
                carry = temp/10;
            }
            while(carry != 0) {
                result.add(0, carry%10);
                carry = carry/10;
                size++;
            }
            val++;
        }
        return result;
    }

    long maxProduct(int[] arr, int n) {
        // https://www.youtube.com/watch?v=cNLHESv_XPc
        long max = 1;
        long min = 1;
        long result = Integer.MIN_VALUE;;

        for(int val : arr) {
            if(val == 0) {
                max = 1;
                min = 1;
            }
            else {
                long temp1 = max * val;
                long temp2 = min * val;
                max = Math.max(temp1, temp2);
                max = Math.max(max, val);
                min = Math.min(temp1, temp2);
                min = Math.min(min, val);
                result = Math.max(result, max);
            }
        }
        return result;
    }

    static int findLongestConseqSubseq(int[] arr, int N) {
        // https://www.youtube.com/watch?v=YWXbu5uyGXs
        Map<Integer, Boolean> map = new HashMap<>();
        for(int val : arr) {
            map.put(val, true);
        }

        for(int val: arr) {
            if(map.containsKey(val - 1)) {
                map.put(val, false);
            }
        }

        int maxLength = 0;
        int maxStartPoint = 0;
        for(int val : arr) {
            int start = val;
            int length = 1;
            while(map.containsKey(start + length)) {
                length++;
            }

            if(length > maxLength) {
                maxLength = length;
                maxStartPoint = start;
            }
        }
        return maxLength;
    }

    static void moreThanNdK(int[] arr, int n, int k) {
        // https://www.youtube.com/watch?v=dTUXcdF1T70
        int x = n / k;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(arr[i], map.getOrDefault(arr[i],0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer temp = (Integer)entry.getValue();
            if (temp > x)
                System.out.println(entry.getKey());
        }
    }

    static int maxProfitAtMostTwoTransactions(int[] prices, int n) {
        // https://www.youtube.com/watch?v=B-biMG6xnb8
        // Detailed Analysis: https://www.youtube.com/watch?v=wuzTpONbd-0
        int[] profit = new int[n];

        // Going from right to left.
        // This is to check if I buy in current day, what can be max profit if I will sell it in one of right subarray.
        int maxSellingPrice = prices[n-1];
        for(int i=n-2; i>=0; i--) {
            if(prices[i] > maxSellingPrice) {
                maxSellingPrice = prices[i];
            }
            profit[i] = Math.max(profit[i+1], maxSellingPrice-prices[i]);
        }

        // Going from left to right.
        // This is to check if I sell in current day, what can be max profit if I earlier bought it in one of left subarray.
        int minBuyPrice = prices[0];
        for(int i=1; i<n; i++) {
            if(prices[i] < minBuyPrice) {
                minBuyPrice = prices[i];
            }
            profit[i] = Math.max(profit[i-1], profit[i] + (prices[i] - minBuyPrice));
        }
        return profit[n-1];
    }

     static boolean tripletSum(int[] A, int n, int X) {
        Arrays.sort(A);
        for(int i=0; i<=n-3; i++) {
            int val = A[i];
            int target = X - val;

            int left = i+1;
            int right = n-1;
            while(left < right) {
                int sum = A[left] + A[right];
                if(sum == target) {
                    return true;
                }
                else if(sum < target) {
                    left++;
                }
                else {
                    right--;
                }
            }
        }
        return false;
    }

    static int trappingWater(int[] height) {
        // https://www.youtube.com/watch?v=_qeh8VQCm0A
        int arraySize = height.length;
        int maxLeft=0, maxRight=0;
        int left=0, right=arraySize-1;
        int vol = 0;

        while(left < right) {
            if(height[left] < height[right]) {
                if(height[left] > maxLeft) {
                    maxLeft = height[left];
                }

                vol += (maxLeft - height[left]);
                left++;
            }
            else {
                if(height[right] > maxRight) {
                    maxRight = height[right];
                }
                vol += (maxRight - height[right]);
                right--;
            }
        }
        return vol;
    }

    static int findMinDiff (ArrayList<Integer> a, int n, int m) {
        // https://www.youtube.com/watch?v=T_2CATt_XyQ
        // Sliding Window problem
        Collections.sort(a);
        int minDiff = Integer.MAX_VALUE;

        for(int i=0; i+m-1<n; i++) {
            int diff = a.get(i+m-1) - a.get(i);
            if(diff < minDiff) {
                minDiff = diff;
            }
        }
        return minDiff;
    }

    static int minimumSizeSubArraySum(int target, int[] nums) {
        // https://leetcode.com/problems/minimum-size-subarray-sum/
        // https://www.youtube.com/watch?v=4691X1MAmIU
        int n = nums.length;
        int left = 0, right = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        while(right < n) {
            while(sum<target && right<n) {
                sum += nums[right];
                right++;
            }

            while(sum>=target && left<n) {
                int len = right - left;
                minLength = Math.min(minLength, len);
                sum -= nums[left];
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    static void threeWayPartition(int[] array, int a, int b) {
        // https://www.youtube.com/watch?v=QtpTDMBJqH0
        int left=0, i=0, right=array.length-1;
        for(i=0; i<=right; i++) {
            if(array[i] < a) {
                swap(array, left, i);
                left++;
            }
            else if(array[i] > b) {
                swap(array, right, i);
                right--;
                //Element which is swapped from right is still unknown.
                // Hence we need to maintain i pointer at same point to analyze this again
                i--;
            }
        }
    }

     static int minSwapKTogether(int[] arr, int n, int k) {
        // https://www.youtube.com/watch?v=6_-zOrJlTDo
        // Sliding window technique to make favourable elements together
        int favCount=0, nonFavCount=0;
        for(int i : arr) {
            if(i <= k) {
                favCount++;
            }
        }

        for(int i=0; i<favCount; i++) {
            if(arr[i] > k) {
                nonFavCount++;
            }
        }

        int left = 0;
        int right = favCount-1;
        int result = Integer.MAX_VALUE;

        while(right < n) {
            result = Math.min(result, nonFavCount);
            right++;
            if(right<n && arr[right]>k) {
                nonFavCount++;
            }
            if(left<n && arr[left]>k) {
                nonFavCount--;
            }
            left++;
        }
        return result==Integer.MAX_VALUE ? 0 : result;
    }

    static boolean isPalindrome(int n) {
        String num = String.valueOf(n);
        int p1 = 0, p2 = num.length() - 1;
        while(p1 < p2)
            if(num.charAt(p1++) != num.charAt(p2--)) {
                return false;
            }
        return true;
    }

     static int palindromeArray(int[] a, int n) {
        for(int i = 0; i < n; i++)
            if(!isPalindrome(a[i]))
                return 0;
        return 1;

    }

    static int findMedian(int[] v) {
        Arrays.sort(v);
        int n = v.length;
        int left = 0;
        int right = n-1;
        int mid = (left + right)/2;
        if(n%2 == 1) {
            return v[mid];
        }
        return (v[mid] + v[mid+1])/2;
    }

    public double findMedianSortedArrays(int[] a, int[] b) {
        // https://leetcode.com/problems/median-of-two-sorted-arrays/
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
            // totalLength+1 for accommodating odd elements count
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

    public static void main(String[] args) {
        int[] arr = {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8};
        alternatePositiveAndNegativeValue(arr);
        System.out.println(Arrays.toString(arr));
    }
}
