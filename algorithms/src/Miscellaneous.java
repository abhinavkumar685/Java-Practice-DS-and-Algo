import java.util.*;

public class Miscellaneous {
    public static void convertToWave(int[] arr, int n){
        // arr[] = {1,2,3,4,5}
        // Output: 2 1 4 3 5
        int i=0;
        int j = 1;
        while(j<n){
            int swap = arr[i];
            arr[i] = arr[j];
            arr[j] = swap;
            i +=2;
            j +=2;
        }
    }

    public static void segregate0and1(int[] arr, int n) {
        // Quick Sort Partition Algorithm
        // https://www.geeksforgeeks.org/java-program-to-segregate-0s-on-left-side-1s-on-right-side-of-the-array/
        int left = 0;
        for (int i = 0; i < n; ++i) {
            if (arr[i] == 0) {
                int temp = arr[left];
                arr[left] = arr[i];
                arr[i] = temp;
                ++left;
            }
        }
    }




    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};



    }
}