import java.util.Arrays;

class Sorting {
    int[] selectionSort(int[] arr) {
        // https://www.youtube.com/watch?v=EU9FIt1t-Is
        if(arr.length < 2) {
            return arr;
        }
        for(int i=0; i<arr.length-1; i++) {
            int minIndex = i;
            for(int j=i+1; j<arr.length; j++) {
                if(arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // swap
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    void insertionSort(int[] arr) {
        // https://www.youtube.com/watch?v=MMt2x6an_i8
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            for(int j=i-1; j>=0; j--) {
                if(arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                }
                else {
                    break;
                }
            }
        }
    }

    int[] bubbleSort(int[] arr) {
        // https://www.youtube.com/watch?v=Jv-eGC2xmtU
        //we are taking i < n-1 because arr[j+1] index should be accommodated at a[j];
        if(arr.length < 2) {
            return arr;
        }
        for(int iter=1; iter<=arr.length-1; iter++) {
            boolean swap = false;
            for(int j=0; j<arr.length-iter; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                    swap = true;
                }
            }
            if(!swap) {
                System.out.println("No need of swap now at i: " + iter);
                break;
            }
        }
        return arr;
    }

    void _mergeSortMergeMethod2(int[] array, int start, int mid, int end) {
        int n1 = mid - start + 1;
        int n2 = end - mid;

        int[] L = new int[n1];
        int[] M = new int[n2];

        // fill the left and right array
        for (int i = 0; i < n1; i++)
            L[i] = array[start + i];
        for (int j = 0; j < n2; j++)
            M[j] = array[mid + 1 + j];

        // Maintain current index of sub-arrays and main array
        int i=0, j=0, k=start;

        // Until we reach either end of either L or M, pick larger among
        // elements L and M and place them in the correct position at A[start..end]
        // for sorting in descending
        // use if(L[i] >= <[j])
        while (i < n1 && j < n2) {
            if (L[i] <= M[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = M[j];
                j++;
            }
            k++;
        }

        // When we run out of elements in either L or M,
        // pick up the remaining elements and put in A[p..r]
        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = M[j];
            j++;
            k++;
        }
    }

    void _mergeSortMerge(int[] arr, int start, int mid, int end) {
        int i=start;
        int j=mid+1;
        int k=0;
        int[] temp_array = new int[end - start +1];

        while(i <= mid && j <= end) {
            if(arr[i] <= arr[j]) {
                temp_array[k] = arr[i];
                i++;
                k++;
            }
            else {
                temp_array[k] = arr[j];
                j++;
                k++;
            }
        }
        if(i > mid) {
            while(j <= end) {
                temp_array[k] = arr[j];
                j++;
                k++;
            }
        }
        else {
            while(i <= mid) {
                temp_array[k] = arr[i];
                k++;
                i++;
            }
        }
        // now put temp_array element back in original array
//		for(int p=start; p<=end; p++) {
//			arr[p] = temp_array[p - start];
//		}

        for(i=0; i<temp_array.length; i++) {
            arr[start+i] = temp_array[i];
        }

    }

    void mergeSort(int[] arr, int start, int end) {
        // At least two elements in array
        if(start < end) {
            int mid = (start + end)/2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            _mergeSortMerge(arr, start, mid, end);
        }
    }

    void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    int getPivotIndexAfterArrayPartition(int[] arr, int pivot, int low, int high) {
        // https://www.youtube.com/watch?v=kdO5Q0nmPjU
        int i=low, j=low;
        while(i <= high) {
            if(arr[i] <= pivot) {
                swap(arr, i, j);
                i++;
                j++;
            }
            else {
                i++;
            }
        }
        return j-1;
    }

    public void quickSort(int[] arr, int low, int high) {
        // https://www.youtube.com/watch?v=kdO5Q0nmPjU
        if(low >= high) {
            return;
        }
        int pivot = arr[high]; // Always
        int pivotIndex = getPivotIndexAfterArrayPartition(arr, pivot, low, high);
        quickSort(arr, low, pivotIndex-1);
        quickSort(arr, pivotIndex+1, high);
    }

    public int[] mergeTwoSortedArray(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] temp = new int[n+m];

        int index1 = 0, index2 = 0, k = 0;

        while(index1 < m && index2 < n) {
            if(nums1[index1] <= nums2[index2]) {
                temp[k] = nums1[index1];
                index1++;
            }
            else {
                temp[k] = nums2[index2];
                index2++;
            }
            k++;
        }
        while(index1 < m) {
            temp[k] = nums1[index1];
            index1++;
            k++;
        }
        while(index2 < n) {
            temp[k] = nums2[index2];
            index2++;
            k++;
        }
        return temp;
    }

    public int[] mergeSortBestMethod(int[] arr, int low, int high) {
        // https://www.youtube.com/watch?v=aiUHB-3EOg8
        if(low == high) {
            int[] temp = new int[1];
            temp[0] = arr[low]; // temp[0] = arr[high] will also work
            return temp;
            // return new int[]{arr[low]};
        }

        int mid = (high + low)/2;
        int[] firstSortedHalf = mergeSortBestMethod(arr, low, mid);
        int[] secondSortedHalf = mergeSortBestMethod(arr, mid+1, high);
        int[] fullSortedArray = mergeTwoSortedArray(firstSortedHalf, secondSortedHalf);
        return fullSortedArray;
    }

    int[] arrayPartitionUsingPivot(int[] arr, int pivot) {
        // https://www.youtube.com/watch?v=if40LxQ8_Xo
        // Useful in even-odd,
        // 0s and 1s segregation
        // zeroes and non-zeroes partition

        // 0 to j-1 --> <= pivot
        // j to i-1 --> >pivot
        // i to n-1  --> unknown
        int i=0, j=0;
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
        return arr;
    }
    public int[] sortedSquares(int[] nums) {
        // https://leetcode.com/problems/squares-of-a-sorted-array
        int start = 0;
        int end = nums.length - 1;
        int [] output = new int[nums.length];
        for(int i = nums.length - 1; i >= 0; i--)
        {
            if(Math.abs(nums[end]) < Math.abs(nums[start]))
            {
                output[i] = nums[start]*nums[start];
                start++;
            }
            else
            {
                output[i] = nums[end]*nums[end];
                end--;
            }
        }
        return output;
    }

    public void sort0sAnd1s(int[] arr) {
        // https://www.youtube.com/watch?v=jFrUwjx4eoA
        int i = 0;
        int j = 0;
        while(i < arr.length) {
            if(arr[i] == 1) {
                i++;
            }
            else {
                swap(arr, i, j);
                i++;
                j++;
            }
        }
        System.out.println(Arrays.toString(arr));
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
}


public class    Algorithms {
    public static void main(String[] args) {
        int[] array = {1, 5, 2, 8, 76, 93, 100};
        Sorting sort = new Sorting();
//        int[] result = sort.mergeSortBestMethod(array, 0, 6);
//
//        for (int i : result) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
        sort.sort0sAnd1s(new int[]{1, 0, 0, 1, 1, 1, 0, 0, 0 ,1, 1, 1, 0});
    }
}
