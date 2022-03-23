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
