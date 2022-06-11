import java.util.*;

public class practiceTest {
    public static void getMAxProductSet(int[] arr) {
        int length = arr.length;
        HashSet<Integer> result = new HashSet<>();
        if(length == 0) {
            System.out.println(result);
            return;
        }

        int currentProduct = 1;
        int minElement = 1;
        for (int i : arr) {
            if (i == 0 || i == 1) {
                continue;
            }

            result.add(i);
            currentProduct *= i;

            if (i < 0) {
                minElement = -1 * Math.min(Math.abs(minElement), Math.abs(i));
                System.out.println(minElement);
            } else {
                minElement = Math.min(minElement, i);
            }
        }

        if(currentProduct < 0) {
            currentProduct = currentProduct / minElement;
            result.remove(minElement);
        }
        System.out.println(currentProduct);
        System.out.print(result);
    }

    static ArrayList<Integer> spiralOrder(int[][] arr) {
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

    public static void powerSetRecursive(ArrayList<ArrayList<Integer>> result,
                                         ArrayList<Integer> currentList, int[] arr, int index) {
        if(index == arr.length) {
            result.add(new ArrayList<>(currentList));
            return;
        }
        currentList.add(arr[index]);
        powerSetRecursive(result, currentList, arr, index+1);
        currentList.remove(currentList.size() - 1);
        powerSetRecursive(result, currentList, arr, index+1);

    }

    public static void powerSet(int[] arr) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> currentList = new ArrayList<Integer>();
        powerSetRecursive(result, currentList, arr, 0);
        System.out.println(result);
    }

    public static void subsequenceRecursive(ArrayList<String> result,
                                            StringBuilder currentList, String str, int index) {
        if(index == str.length()) {
            result.add(currentList.toString());
            return;
        }

        currentList.append(str.charAt(index));
        subsequenceRecursive(result, currentList, str, index+1);
        currentList.deleteCharAt(currentList.length() - 1);
        subsequenceRecursive(result, currentList, str, index+1);

    }

    public static void subsequence(String str) {
        ArrayList<String> result = new ArrayList<String>();
        StringBuilder currentList = new StringBuilder();
        subsequenceRecursive(result, currentList, str, 0);
        System.out.println(result);
    }

    public static void swap(StringBuilder str, int index1, int index2) {
        char charIndex1 = str.charAt(index1);
        char charIndex2 = str.charAt(index2);
        str.setCharAt(index1, charIndex2);
        str.setCharAt(index2, charIndex1);
    }

    public static void permutationRecursive(ArrayList<String> result,
                                             StringBuilder currentString, int index) {
        if(index == currentString.length()-1) {
            result.add(currentString.toString());
            return;
        }

        for(int j=index; j<currentString.length(); j++) {
            swap(currentString, index, j);
            permutationRecursive(result, currentString, index+1);
            // Reverse Swap
            swap(currentString, j, index);
        }
    }

    public static void permutation(String str) {
        ArrayList<String> result = new ArrayList<>();
        StringBuilder myString = new StringBuilder(str);
        permutationRecursive(result, myString, 0);
        System.out.println(result);
    }

    public static int findPathCountRecursive(int[][] arr, int row, int col, int destRow, int destCol) {
        if(row<0 || row>arr.length-1 || col<0 || col>arr[0].length-1) {
            return 0;
        }

        if(row == destRow && col == destCol) {
            return 1;
        }

        return findPathCountRecursive(arr, row-1, col, destRow, destCol) +
                findPathCountRecursive(arr, row, col+1, destRow, destCol);
    }

    public static void rotateArrayLeft(int[] arr, int k) {
        int n = arr.length;
        k = k % n;
        int[] temp = new int[k];
        // Put first k elements in temp array
        for(int i=0; i<k; i++) {
            temp[i] = arr[i];
        }
        for(int j=0; j<n-k; j++) {
            arr[j] = arr[j+k];
        }
        for(int i=0; i<k; i++) {
            arr[n-k+i] = temp[i];
        }
    }

    public static void rotateArrayRight(int[] arr, int k) {
        // https://www.geeksforgeeks.org/rotate-matrix-right-k-times/
        int n = arr.length;
        k = k % n;
        int[] temp = new int[n-k];
        //Copy first n-k elements to temp array
        for(int i=0; i<n-k; i++) {
            temp[i] = arr[i];
        }
        //Shifting last k elements to the start of array
        for(int j=n-k; j<n; j++) {
            arr[j-n+k] = arr[j];
        }
        //Copying the temp elements after that
        for(int i=k; i<n; i++) {
            arr[i] = temp[i-k];
        }
    }

    static void rotateMatrixClockwise(int[][] arr) {

        int top=0, down = arr.length-1;
        int left=0, right = arr[0].length-1;
        int current = 0;

        while(top < down && left < right) {
            int previous = arr[top+1][left];
            //Moving left to Right
            for(int i=left; i<=right; i++) {
                current = arr[top][i];
                arr[top][i] = previous;
                previous = current;
            }
            top++;


            //Moving from top to down
            for(int i=top; i<=down; i++) {
                current = arr[i][right];
                arr[i][right] = previous;
                previous = current;
            }
            right--;


            //Move from right to left
            for (int i=right; i>=left; i--) {
                current = arr[down][i];
                arr[down][i] = previous;
                previous = current;
            }
            down--;


            for(int i=down; i>=top; i--) {
                current = arr[i][left];
                arr[i][left] = previous;
                previous = current;
            }
            left++;

        }
    }

    static void printDiagonalSpiral(int[][] arr) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(arr.length == 0 || arr[0].length == 0) {
            return;
        }

        int rowMax = arr.length - 1;
        int colMax = arr[0].length - 1;
        boolean up = true;
        int row = 0;
        int col = 0;

        while(row<=rowMax && col<=colMax) {
            //Going up
            if(up) {
                while(row>0 && col<colMax) {
                    result.add(arr[row][col]);
                    row--;
                    col++;
                }
                result.add(arr[row][col]);
                if(col == colMax) {
                    row++;
                }
                else {
                    col++;
                }
            }
            //Going down
            else {
                while(row<rowMax && col > 0) {
                    result.add(arr[row][col]);
                    row++;
                    col--;
                }
                result.add(arr[row][col]);
                if(row == rowMax) {
                    col++;
                }
                else {
                    row++;
                }
            }
            up=!up;
        }
        System.out.println(result);
    }

    static void printAllDiagonal(int[][] arr) {
        ArrayList<Integer> result = new ArrayList<>();
        if(arr.length == 0 || arr[0].length == 0) {
            return;
        }

        int rowMax = arr.length - 1;
        int colMax = arr[0].length - 1;

        for(int row=0; row<=rowMax; row++) {
            int currentRow = row;
            int currentCol = 0;
            while(currentRow >=0) {
                result.add(arr[currentRow][currentCol]);
                currentRow--;
                currentCol++;
            }
        }

        for(int col=1; col<=colMax; col++) {
            int currentRow = rowMax;
            int currentCol = col;
            while(currentCol <=colMax) {
                result.add(arr[currentRow][currentCol]);
                currentRow--;
                currentCol++;
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, -5, -2, -3, -1, 10, 10, 10};
        int[] nums1 = {0, 1, 1, 1, -1, 10, 10, 10};
//        getMAxProductSet(nums);

        int[][] a = { { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };

        int[][] a1 = { { 1, 2, 3},
                { 4, 5, 6},
                { 7, 8, 9}};

        printAllDiagonal(a1);
    }
}
