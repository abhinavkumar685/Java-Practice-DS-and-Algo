import java.util.*;

class BinarySearch {
    int recursiveSearch(int[] arr, int start, int end, int number) {
        if(start <= end) {
            int mid = (start + end)/2;
            if(arr[mid] == number) {
                return mid;
            }
            else if(arr[mid] < number) {
                return recursiveSearch(arr, mid+1, end, number);
            }
            else {
                return recursiveSearch(arr, start, mid-1, number);
            }
        }
        return -1;
    }

    int loopSearch(int[] arr, int number) {
        int start = 0;
        int end = arr.length-1;
        while(start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] == number) {
                return mid;
            } else if (arr[mid] < number) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        // https://leetcode.com/problems/search-a-2d-matrix/
        // https://www.youtube.com/watch?v=ZYpYur0znng
        if(matrix.length == 0) return false;

        int m = matrix.length;
        int n = matrix[0].length;

        int low = 0;
        int high = (m * n) - 1;

        while(low <= high) {
            int mid = low + (high - low)/2;
            if(matrix[mid/n][mid%n] == target) {
                return true;
            }
            else if(matrix[mid/n][mid%n] < target) {
                low = mid + 1;
            }
            else {
                high = mid -1;
            }
        }
        return false;
    }

    public int findRotationCount(int[] nums) {
        // https://www.techiedelight.com/find-number-rotations-circularly-sorted-array/
        // https://www.geeksforgeeks.org/find-rotation-count-rotated-sorted-array/
        int left = 0;
        int right = nums.length - 1;

        // loop till the search space is exhausted
        while (left <= right)
        {
            // if the search space is already sorted, we have
            // found the minimum element (at index `left`)
            if (nums[left] <= nums[right]) {
                return left;
            }

            int mid = (left + right) / 2;

            // find the next and previous element of the `mid` element
            // (in a circular manner)
            int next = (mid + 1) % nums.length;
            int prev = (mid - 1 + nums.length) % nums.length;

            // if the `mid` element is less than both its next and previous
            // neighbor, it is the array's minimum element

            if (nums[mid] <= nums[next] && nums[mid] <= nums[prev]) {
                return mid;
            }

            // if nums[mid…right] is sorted, and `mid` is not the minimum element,
            // then the pivot element cannot be present in nums[mid…right],
            // discard nums[mid…right] and search in the left half

            else if (nums[mid] <= nums[right]) {
                right = mid - 1;
            }

            // if nums[left…mid] is sorted, then the pivot element cannot be present
            // in it; discard nums[left…mid] and search in the right half

            else if (nums[mid] >= nums[left]) {
                left = mid + 1;
            }
        }
        // invalid input
        return -1;
    }
}



public class BinarySearchRun {
    public static void main (String[] args) {
        int[] sorted_array = {1, 2, 5, 10, 15, 20, 25, 30, 40, 50, 51};
        int search_number = 10;
        BinarySearch bs = new BinarySearch();
        int result = bs.recursiveSearch(sorted_array, 0, sorted_array.length-1, search_number);

        if (result == -1) {
            System.out.println("Number " + search_number + "not present in given array");
        }
        else {
            System.out.println("Number " + search_number + " is present at index-> " +
                    result + " of given array");
        }

    }
}
