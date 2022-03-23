package SearchingAndSorting;

import java.util.*;

class SwapPair {
    int val;
    int index;

    SwapPair(int v, int idx) {
        this.val = v;
        this.index = idx;
    }

    public String toString() {
        return "[" + this.val + "," + this.index + "]";
    }
}

public class SearchingAndSorting {
    static int[] firstAndLastIndex(int[] nums, int target) {
        // https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array
        // https://www.youtube.com/watch?v=9NXZccIWNqU
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
        //Finding the leftmost index
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

    static ArrayList<Integer> valueEqualToIndex(int[] arr, int n) {
        // code here
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i = 0 ;i< n;i++){
            if(arr[i]==i+1) {
                result.add(arr[i]);
            }
        }
        return result;
    }

    static int searchRotatedArray(int[] arr, int target) {
        // https://leetcode.com/problems/search-in-rotated-sorted-arra
        // https://www.youtube.com/watch?v=1uu3g_uu8O0
        int low = 0;
        int high = arr.length-1;
        while(low <= high) {
            int mid = (low + high) / 2;
            if(target == arr[mid]) {
                return mid;
            }
            else if(arr[low] <= arr[mid]) {
                // Left half is sorted
                if(target >= arr[low] & target < arr[mid]) {
                    high = mid-1;
                }
                else {
                    low = mid + 1;
                }
            }
            else if(arr[mid] <= arr[high]) {
                // Right half is sorted
                if(target > arr[mid] && target <= arr[high]) {
                    low = mid + 1;
                }
                else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    static int countSquares(int N) {
        // https://practice.geeksforgeeks.org/problems/count-squares3649
        int count = 0;
        for(int i=1;i*i<N;i++) {
            count++;
        }
        return count;
    }

    static int mySqrt(int x) {
        // https://leetcode.com/problems/sqrtx
        // Basically a Binary Search Algorithm
        if(x < 2) return x;

        long low = 1;
        long high = x;
        long ans = -1;

        while(low <= high) {
            long mid = (low + high) / 2;
            long sqr = mid * mid;
            if(sqr == x) {
                ans = mid;
                break;
            }
            else if(sqr < x) {
                ans = mid;
                low = mid + 1;

            }
            else {
                high = mid - 1;
            }
        }
        return (int)ans;
    }

    static int middleOfThreeNumbers(int A, int B, int C){
        if(A>B && A<C || A<B && A>C) {
            return A;
        }
        else if(B>A && B<C || B<A && B>C) {
            return B;
        }
        else  {
            return C;
        }
    }

    static int[] findMissingAndRepeatingElement(int[] arr, int n) {
        // https://practice.geeksforgeeks.org/problems/find-missing-and-repeating2512
        // https://www.youtube.com/watch?v=qfbBRtbhQ04

        // Awesome question to use O(1) space to mark it visited in place
        int[] result = new int[2];
        for(int i=0; i<n; i++) {
            int val = Math.abs(arr[i]);
            int myElement = arr[val - 1];
            if(myElement < 0) {
                result[0] = val;
            }
            else {
                arr[val-1] = -myElement;
            }
        }

        for(int i=0; i<n; i++) {
            if(arr[i] > 0) {
                result[1] = i+1;
                break;
            }
        }
        return result;
    }

    static int majorityElement(int[] a, int size) {
        // https://www.youtube.com/watch?v=YXywKwT9EKA
        // Awesome Concept for counter
        int majorElement = a[0];
        int count = 1;
        for(int i=1; i<size; i++) {
            if(a[i] == majorElement) {
                count++;
            }
            else {
                count--;
            }
            if(count == 0) {
                majorElement = a[i];
                count = 1;
            }
        }
        int c = 0;
        for(int i=0; i<size; i++) {
            if(a[i] == majorElement) {
                c++;
            }
        }

        return c > size/2 ? majorElement : -1;
    }

    static int search(int[] arr, int n, int x) {
        // https://www.geeksforgeeks.org/search-an-element-in-an-array-where-difference-between-adjacent-elements-is-1/
        // Important Concept
        int i = 0;
        while (i < n)
        {
            // If x is found at index i
            if (arr[i] == x)
                return i;

            // Jump the difference between current
            // array element and x
            i = i + Math.abs(arr[i]-x);
        }

        System.out.println ("number is not present!");
        return -1;
    }

    static int searchElementWithadjacentDistanceK(int[] arr, int n, int x, int k) {
        // https://www.geeksforgeeks.org/searching-array-adjacent-differ-k/
        int i = 0;
        while (i < n) {
            // If x is found at index i
            if (arr[i] == x)
                return i;

            // Jump the difference between
            // current array element and x
            // divided by k We use max here
            // to make sure that i moves
            // at-least one step ahead.
            i = i + Math.max(1, Math.abs(arr[i]
                    - x) / k);
        }

        System.out.println("number is not present!");
        return -1;
    }

    public boolean findDiffPair(int[] arr, int size, int n) {
        // https://www.youtube.com/watch?v=XGrXiVi7Ces
        Arrays.sort(arr);
        int i=0, j=1;
        while(i<size && j<size) {
            int diff = arr[j] - arr[i];
            if(diff == n) {
                return true;
            }
            else if(diff < n) {
                j++;
            }
            else {
                i++;
            }
        }
        return false;
    }

    public ArrayList<ArrayList<Integer>> twoSum(int[] arr, int start, int target) {
        int n = arr.length;
        int left = start;
        int right = n-1;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        while(left < right) {
            if(left != start && arr[left] == arr[left-1]) {
                left++;
                continue;
            }
            int sum  = arr[left] + arr[right];
            if(sum == target) {
                List<Integer> sub = new ArrayList<>();
                sub.add(arr[left]);
                sub.add(arr[right]);
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

    public ArrayList<ArrayList<Integer>> threeSum(int[] arr, int start, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int n = arr.length;
        if(n - start< 3) {
            return result;
        }

        for(int i=start; i<=n-3; i++) {
            if(i!=start && arr[i] == arr[i-1]) {
                continue;
            }
            int twoSumTarget = target - arr[i];
            ArrayList<ArrayList<Integer>> twoSumResult = twoSum(arr, i+1, twoSumTarget);
            for(ArrayList<Integer> list : twoSumResult) {
                list.add(0, arr[i]);
                result.add(new ArrayList<Integer>(list));
            }
        }
        return result;
    }

    public ArrayList<ArrayList<Integer>> fourSum(int[] arr, int k) {
        // https://www.youtube.com/watch?v=hGz0b8L7plI
        int n = arr.length;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(n < 4) {
            return result;
        }
        Arrays.sort(arr);
        for(int i=0; i<=n-4; i++) {
            if(i!=0 && arr[i] == arr[i-1]) {
                continue;
            }
            int threeSumTarget = k - arr[i];
            ArrayList<ArrayList<Integer>> threeSumResult = threeSum(arr, i+1, threeSumTarget);
            for(ArrayList<Integer> list : threeSumResult) {
                list.add(0, arr[i]);
                result.add(new ArrayList<Integer>(list));
            }
        }
        return result;
    }

    public int robHouse(int[] arr, int n) {
        // https://practice.geeksforgeeks.org/problems/stickler-theif-1587115621
        int[] dp = new int[n+1];
        dp[1] = arr[0];
        for(int i=2; i<dp.length; i++) {
            dp[i] = Math.max(dp[i-1], arr[i-1] + dp[i-2]);
        }
        return dp[dp.length-1];
    }

    long countTripletsWithSumSmallerThanK(long[] arr, int n,int sum) {
        // https://practice.geeksforgeeks.org/problems/count-triplets-with-sum-smaller-than-x
        // https://www.youtube.com/watch?v=9455buJlb_k
        Arrays.sort(arr);
        long count = 0;
        for(int i=0; i<=n-3; i++) {
            if(i!=0 && arr[i] == arr[i-1]) {
                continue;
            }
            long target = sum - arr[i];

            // Target Sum for two elements
            int start = i+1;
            int left = start;
            int right = n-1;
            while(left<right) {
                if(left != start && arr[left] == arr[left-1]) {
                    continue;
                }
                long s = arr[left] + arr[right];
                if(s < target) {
                    count += right-left;
                    left++;
                }
                else {
                    right--;
                }
            }
        }
        return count;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void mergeSortedArrayWithoutExtraSpace(int[] arr1, int[] arr2, int n, int m) {
        // Optimize it using Gap Algorithm
        // https://www.youtube.com/watch?v=hVl2b3bLzBw&t=155s
        for(int i=0; i<n; i++) {
            boolean swap = false;

            if(arr1[i] > arr2[0]) {
                int temp = arr1[i];
                arr1[i] = arr2[0];
                arr2[0] = temp;
                swap = true;
            }

            if(swap) {
                for(int j=1; j<m; j++) {
                    if(arr2[j-1] > arr2[j]) {
                        swap(arr2, j-1, j);
                    }
                }
            }

        }
    }

    public static long findSubarray(long[] arr ,int n)  {
        // https://www.youtube.com/watch?v=C9-n_H7dsvU&t=351s
        int count = 0;
        int sum = 0;
        int index = -1;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(sum, 1);
        while(index < n-1) {
            index++;
            sum += arr[index];
            if(map.containsKey(sum)) {
                count += map.get(sum);
                map.put(sum, map.get(sum)+1);
            }
            else {
                map.put(sum, 1);
            }
        }
        return count;
    }

    public int[] productExceptSelf(int[] nums) {
        // https://leetcode.com/problems/product-of-array-except-self/
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

    static void sortBySetBitCount(Integer[] arr, int n) {
        // https://www.youtube.com/watch?v=RdQiA97A7XQ
        Arrays.sort(arr,(a,b)-> {
            return Integer.compare(Integer.bitCount(b),Integer.bitCount(a));
        });
    }

    public int minSwapsToSortArray(int[] arr) {
        // https://www.youtube.com/watch?v=m-8_yQao-lI
        int n = arr.length;
        SwapPair[] pArray = new SwapPair[n];
        for(int i=0; i<n; i++) {
            SwapPair pair = new SwapPair(arr[i], i);
            pArray[i] = pair;
        }

        Arrays.sort(pArray, new Comparator<SwapPair>() {
            public int compare(SwapPair p1, SwapPair p2) {
                return p1.val - p2.val;
            }
        });

        boolean[] visited = new boolean[n];
        int swap = 0;

        for(int i=0; i<n; i++) {
            if(visited[i] || pArray[i].index == i) {
                continue;
            }
            int cycleLength = 0;
            int j = i;
            while(!visited[j]) {
                visited[j] = true;
                cycleLength++;
                j = pArray[j].index;
            }
            swap += (cycleLength - 1);

        }
        return swap;
    }

    public static void main(String[] args) {
        int[] arr = {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8};
        System.out.println(Arrays.toString(arr));
    }
}
