import java.util.*;
import java.util.LinkedList;

class Cell {
    int row, col, dist;
    Cell(int x, int y, int dis) {
        this.row = x;
        this.col = y;
        this.dist = dis;
    }
}

public class BackTracking {
    static boolean checkIfValid(char[][] board, int row, int column, char c) {
        for(int i=0; i<board[0].length; i++) {
            if(board[row][i] == c) {
                return false;
            }
        }

        for(int i=0; i<board.length; i++) {
            if(board[i][column] == c) {
                return false;
            }
        }

        int subMatrixRow = row/3 * 3;
        int subMatrixColumn = column/3 * 3;

        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(board[subMatrixRow + i][subMatrixColumn + j] == c) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean solve(char[][] board, int row, int col) {
        if (col == board[0].length) {
            col = 0;
            row += 1;
        }
        if (row == board.length) {
            return true;
        }
        if (board[row][col] != '.') {
            return solve(board, row, col+1);
        }
        for (char num = '1'; num <= '9'; num++) {
            if (checkIfValid(board, row, col, num)) {
                board[row][col] = num;
                if (solve(board, row, col+1)) {
                    return true;
                }
                else {
                    board[row][col] = '.';
                }
            }
        }
        return false;
    }


    static void solveSudoku(char[][] board) {
        // https://leetcode.com/problems/sudoku-solver/
        solve(board, 0, 0);
    }

    static void letterCombinationsDFS(String digits, int index, List<String> result, StringBuilder currentString,
                          HashMap<Integer, String> map) {
        if(index == digits.length()) {
            result.add(currentString.toString());
            return;
        }
        String tmp = map.get(digits.charAt(index) - '0');
        for(int i=0; i<tmp.length(); i++) {
            currentString.append(tmp.charAt(i));
            letterCombinationsDFS(digits, index+1, result, currentString, map);
            currentString.deleteCharAt(currentString.length()-1);
        }
    }

    static List<String> letterCombinations(String digits) {
        // https://leetcode.com/problems/letter-combinations-of-a-phone-number/
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0)
            return result;

        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "");
        map.put(1, "");
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        letterCombinationsDFS(digits, 0, result, new StringBuilder(), map);
        return result;
    }

    static void getCombinationRecursive(int[] arr, int index, String str, List<String> result) {
        String map = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if(index >= arr.length) {
            result.add(str);
            return;
        }
        int sum = 0;
        for(int j=index; j<=Math.min(index+1, arr.length-1); j++){
            sum = sum*10 + arr[j];
            if(sum>0 && sum<=26) {
                getCombinationRecursive(arr, j+1, str+map.charAt(sum-1), result);
            }
        }
    }

    static void getCombinations(int[] arr) {
        // https://www.techiedelight.com/combinations-of-words-formed-replacing-given-numbers-corresponding-english-alphabet/
        // int[] arr = {1, 2, 2, 1};
        // result = [ABBA, ABU, AVA, LBA, LU]
        List<String> result = new ArrayList<>();
        String str = "";

        int n = arr.length;
        if(n == 0) return;
        getCombinationRecursive(arr, 0, str, result);
        System.out.println(result);
    }

    static void getCombinationsFromNumbersBacktrack(List<String> result, int[] arr, int index, String current) {
        // Best Solution
        // https://www.techiedelight.com/combinations-of-words-formed-replacing-given-numbers-corresponding-english-alphabet/
        // combinationsFromNumbers(result, arr, 0 , "");
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        if(index >= arr.length) {
            result.add(current);
            return;
        }
        getCombinationsFromNumbersBacktrack(result, arr, index+1, current+str.charAt(arr[index]-1));
        if(index+1 < arr.length) {
            int sum = arr[index] * 10 +  arr[index+1];
            if(sum > 0 && sum <= 27) {
                getCombinationsFromNumbersBacktrack(result, arr, index+2, current+str.charAt(sum-1));
            }
        }
    }

    static void letterCombineDFS(int n, int k, int index, List<Integer> current, List<List<Integer>> result) {
        if(current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        for(int i=index; i<=n; i++) {
            current.add(i);
            letterCombineDFS(n, k, i+1, current, result);
            current.remove(current.size()-1);
        }
    }

    static void letterCombine(int n, int k) {
        //https://leetcode.com/problems/combinations/
        List<List<Integer>> result = new ArrayList<>();
        if(k == 0) {
            result.add(new ArrayList<>());
            return;
        }
        int index=1;

        List<Integer> current = new ArrayList<>();
        letterCombineDFS(n, k, index, current, result);
        System.out.println(result);
    }

    static void generateParenthesisBacktrack(List<String> result, StringBuilder current,
                                             int n, int openCount, int closeCount) {
        if(current.length() == 2*n) {
            result.add(current.toString());
        }

        if(openCount < n) {
            current.append("(");
            generateParenthesisBacktrack(result, current, n, openCount+1, closeCount);
            current.deleteCharAt(current.length()-1);
        }

        if(closeCount < openCount) {
            current.append(")");
            generateParenthesisBacktrack(result, current, n, openCount, closeCount+1);
            current.deleteCharAt(current.length()-1);
        }
    }

    static List<String> generateParenthesis(int n) {
        if(n == 0) {
            return new ArrayList<String>();
        }
        List<String> result = new ArrayList<String>();
        StringBuilder current = new StringBuilder();
        generateParenthesisBacktrack(result, current, n, 0, 0);
        return result;
    }

    static void swapArray(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    static void permutationRecursive(List<List<Integer>> result, int[] nums, int currentIndex) {
        // https://leetcode.com/problems/permutations/submissions/
        if(currentIndex == nums.length) {
            List<Integer> temp = new ArrayList<>();
            for(int u: nums) {
                temp.add(u);
            }
            result.add(temp);
            return;
        }
        for(int i=currentIndex; i<nums.length; i++) {
            swapArray(nums, currentIndex, i);
            permutationRecursive(result, nums, currentIndex+1);
            swapArray(nums, currentIndex, i);
        }
    }

    static void arrayPermutationBacktrack(List<List<Integer>> result, List<Integer> current,
                                          int[] arr, boolean[] visited) {
        if(current.size() == arr.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        for(int i=0; i<arr.length; i++) {
            if(visited[i]) {
                continue;
            }
            current.add(arr[i]);
            visited[i] = true;
            arrayPermutationBacktrack(result, current, arr, visited);
            current.remove(current.size()-1);
            visited[i] = false;
        }
    }

    static void arrayPermutation(int[] arr) {
        // https://www.youtube.com/watch?v=R3Sm9V2OSCo
        List<List<Integer>> result = new ArrayList<>();
        int n = arr.length;
        if(n == 0) {
            result.add(new ArrayList<>());
            System.out.println(result);
            return;
        }

        List<Integer> current = new ArrayList<>();
        //By default all the values are false in array
        boolean[] visited = new boolean[n];
        arrayPermutationBacktrack(result, new ArrayList<Integer>(), arr, visited);
        System.out.println(result);
    }

    public boolean isValidEntry(char[][] board, int row, int col, char c) {
        // Check in row
        for(int i=0; i<board[0].length; i++) {
            if(board[row][i] == c) {
                return false;
            }
        }
        // Check in column
        for(int i=0; i<board.length; i++) {
            if(board[i][col] == c) {
                return false;
            }
        }
        // Check in 3x3 grid
        int subRow = (row/3) * 3;
        int subCol = (col/3) * 3;
        for(int i=subRow; i<subRow+3; i++) {
            for(int j=subCol; j<subCol+3; j++){
                if(board[i][j] == c) {
                    return false;
                }
            }
        }
        return true;
    }

    public void showSudoku(char[][] board) {
        for (char[] i : board) {
            for (char j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public void sudokuSolveMethod2(char[][] board, int row, int col) {
        if(row == board.length) {
            showSudoku(board);
            return;
        }

        int nextRow = 0;
        int nextCol = 0;

        if(col == board[0].length-1) {
            nextRow = row + 1;
            nextCol = 0;
        }
        else {
            nextRow = row;
            nextCol = col+1;
        }

        if(board[row][col] != '.') {
            sudokuSolveMethod2(board, nextRow, nextCol);
        }
        else {
            for(char c='1'; c<='9'; c++) {
                if(isValidEntry(board, row, col, c)) {
                    board[row][col] = c;
                    sudokuSolveMethod2(board, nextRow, nextCol);
                    board[row][col] = '.';
                }
            }
        }
    }

    static boolean isQueenSafe(char[][] chess, int row, int col) {
        for(int i=row-1,j=col; i>=0; i--) {
            if(chess[i][j] == 'Q') {
                return false;
            }
        }
        for(int i=row-1,j=col-1; i>=0. && j>=0; i--,j--) {
            if(chess[i][j] == 'Q') {
                return false;
            }
        }
        for(int i=row-1,j=col+1; i>=0. && j<chess[0].length; i--,j++) {
            if(chess[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    static void solveNQueensBacktrack(List<List<String>> result, char[][] chess, int row) {
        if(row == chess.length) {
            List<String> ans = new ArrayList<>();
            for(int i=0; i<chess.length; i++) {
                ans.add(new String(chess[i]));
            }
            result.add(new ArrayList<String>(ans));
            return;
        }

        for(int col=0; col<chess.length; col++) {
            if(isQueenSafe(chess, row, col)) {
                chess[row][col] = 'Q';
                solveNQueensBacktrack(result, chess, row+1);
                chess[row][col] = '.';
            }
        }
    }

    static void solveNQueens(int n) {
        // https://www.youtube.com/watch?v=05y82cP3bJo
        // https://leetcode.com/problems/n-queens
        List<List<String>> result = new ArrayList<>();
        char[][] chess = new char[n][n];

        for (char[] c : chess) {
            Arrays.fill(c, '.');
        }
        solveNQueensBacktrack(result, chess, 0);
        System.out.println(result);
    }

    static void combinationSumBacktrack(List<List<Integer>> result, List<Integer> current,
                                        int currentSum, int[] candidates, int index, int target) {
        if(index == candidates.length) {
            if(currentSum == target) {
                result.add(new ArrayList<Integer>(current));
            }
            return;
        }
        current.add(candidates[index]);
        combinationSumBacktrack(result, current, currentSum + candidates[index],
                candidates, index+1, target);
        current.remove(current.size()-1);
        combinationSumBacktrack(result, current, currentSum, candidates, index+1, target);
    }

    static void combinationSum(int[] candidates, int target) {
        // https://www.youtube.com/watch?v=HGDmj5NrrjM
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        int currentSum = 0;
        int startIndex = 0;
        combinationSumBacktrack(result, current, currentSum, candidates, startIndex, target);
        System.out.println(result);
    }

    static void combinationSumWithDuplicatesBacktrack(List<List<Integer>> result, List<Integer> current,
                                                      int currentSum, int[] candidates, int index, int target) {
        if(index == candidates.length) {
            if(currentSum == target) {
                result.add(new ArrayList<Integer>(current));
            }
            return;
        }
        if(currentSum > target) {
            return;
        }

        current.add(candidates[index]);
        combinationSumWithDuplicatesBacktrack(result, current, currentSum + candidates[index],
                candidates, index, target);
        current.remove(current.size()-1);
        combinationSumWithDuplicatesBacktrack(result, current, currentSum, candidates, index+1, target);
    }

    static void combinationSumWithDuplicates(int[] candidates, int target) {
        // https://leetcode.com/problems/combination-sum/
        // https://www.youtube.com/watch?v=OyZFFqQtu98
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        int currentSum = 0;
        int startIndex = 0;
        combinationSumWithDuplicatesBacktrack(result, current, currentSum, candidates, startIndex, target);
        System.out.println(result);
    }

    static void powersetORSubsequenceBacktrack(List<List<Integer>> result,  List<Integer> current, int[] arr, int index) {
        if(index == arr.length) {
            result.add(new ArrayList<Integer>(current));
            return;
        }
        current.add(arr[index]);
        powersetORSubsequenceBacktrack(result, current, arr, index+1);
        current.remove(current.size()-1);
        powersetORSubsequenceBacktrack(result, current, arr, index+1);
    }

    static void powersetORSubsequence(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        int index = 0;
        powersetORSubsequenceBacktrack(result, current, nums, index);
        System.out.println(result);
    }

    static void letterCasePermutationBacktrack(List<String> result, String current, String input, int index) {
        // https://leetcode.com/problems/letter-case-permutation/
        if(index == input.length()){
            result.add(current);
            return;
        }
        char c = input.charAt(index);
        if(Character.isDigit(c)) {
            letterCasePermutationBacktrack(result, current + c, input, index+1);
        }
        else {
            letterCasePermutationBacktrack(result, current + Character.toLowerCase(c), input, index+1);
            letterCasePermutationBacktrack(result, current + Character.toUpperCase(c), input, index+1);
        }
    }

    static List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<String>();
        String current = "";
        letterCasePermutationBacktrack(result, current, s, 0);
        return result;
    }

    static void showBoard(int[][] board) {
        for (int[] ints : board) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void knightTourDFS(int[][] chess, int row, int col, int moveNumber) {
        // https://www.youtube.com/watch?v=SP880DBRJ_8
        // Input: knightTourDFS(chess, 0, 0, 1);
        if(row<0 || row>=chess.length || col<0 || col>=chess[0].length || chess[row][col]>0) {
            return;
        }
        if(moveNumber == chess.length * chess[0].length) {
            chess[row][col] = moveNumber;
            System.out.println(Arrays.deepToString(chess));
            chess[row][col] = 0;
            return;
        }
        chess[row][col] = moveNumber;
        knightTourDFS(chess, row-2, col+1, moveNumber+1);
        knightTourDFS(chess, row-1, col+2, moveNumber+1);
        knightTourDFS(chess, row+1, col+2, moveNumber+1);
        knightTourDFS(chess, row+2, col+1, moveNumber+1);

        knightTourDFS(chess, row+2, col-1, moveNumber+1);
        knightTourDFS(chess, row+1, col-2, moveNumber+1);
        knightTourDFS(chess, row-1, col-2, moveNumber+1);
        knightTourDFS(chess, row-2, col-1, moveNumber+1);
        chess[row][col] = 0;
    }

    public static boolean isInsideBoard(int Rows, int Cols, int x, int y) {
        return x >= 0 && x < Rows && y >= 0 && y < Cols;
    }

    public static int knightWalkBFS(int Rows, int Cols, int srcRow, int srcCol, int destRow, int destCol) {
        // https://www.interviewbit.com/problems/knight-on-chess-board/
        // knightWalk(8, 8, 1, 1, 8, 8) (indexing from 1)

        // x and y direction, where a knight can move
        int[] dx = { -2, -1, 1, 2, -2, -1, 1, 2 };
        int[] dy = { -1, -2, -2, -1, 1, 2, 2, 1 };

        Queue<Cell> queue = new LinkedList<>();
        queue.add(new Cell(srcRow-1, srcCol-1, 0));
        boolean[][] visited = new boolean[Rows][Cols];
        visited[srcRow-1][srcCol-1] = true;

        while(!queue.isEmpty()) {
            Cell current = queue.remove();
            if(current.row == destRow-1 && current.col == destCol-1) {
                return current.dist;
            }
            for(int i=0; i<8; i++) {
                for(int j=0; j<8; j++) {
                    int x = current.row + dx[i];
                    int y = current.col + dy[i];
                    if(isInsideBoard(Rows, Cols, x, y) && !visited[x][y]) {
                        visited[x][y] = true;
                        queue.add(new Cell(x, y, current.dist + 1));
                    }
                }
            }
        }
        return -1;
    }

    public static void stringPermutationRecursive(String str, StringBuilder current,
                                                  List<String> result, boolean[] used) {
        if(current.length() == str.length()) {
            result.add(current.toString());
            return;
        }
        for(int i=0; i<str.length(); i++) {
            if(used[i]) {
                continue;
            }
            current.append(str.charAt(i));
            used[i] = true;
            stringPermutationRecursive(str, current, result, used);
            current.deleteCharAt(current.length()-1);
            used[i] = false;
        }
    }

    public static boolean partitionKEqualSubsetBacktrack(int[] arr, List<List<Integer>>result,
                                                         int index, int[] eachSubsetSum,
                                                         int nonEmptySetCount, int k, int n) {
        if(index == arr.length) {
            if(nonEmptySetCount == k) {
                boolean flag = true;
                for(int i=0; i<eachSubsetSum.length-1; i++) {
                    if(eachSubsetSum[i] != eachSubsetSum[i+1]){
                        flag = false;
                        break;
                    }
                }
                return flag;
            }
            return false;
        }

        for(int i=0; i<result.size(); i++) {
            if(result.get(i).size() > 0) {
                result.get(i).add(arr[index]);
                eachSubsetSum[i] += arr[index];
                if(partitionKEqualSubsetBacktrack(arr, result, index+1, eachSubsetSum,
                        nonEmptySetCount, k, n)) return true;
                result.get(i).remove(result.get(i).size()-1);
                eachSubsetSum[i] -= arr[index];
            }
            else {
                result.get(i).add(arr[index]);
                eachSubsetSum[i] += arr[index];
                if(partitionKEqualSubsetBacktrack(arr, result, index+1, eachSubsetSum,
                        nonEmptySetCount+1, k, n)) return true;
                result.get(i).remove(result.get(i).size()-1);
                eachSubsetSum[i] -= arr[index];
                break;
            }
        }
        return false;
    }

    public static boolean partitionKEqualSubset(int[] arr, int n, int k) {
        // https://www.youtube.com/watch?v=TvvGj1FtHIk
        // https://leetcode.com/problems/partition-to-k-equal-sum-subsets
        if(k == 1) return true;
        int sum = 0;
        for(int i : arr) {
            sum += i;
        }

        if(k > n || sum % k != 0) {
            return false;
        }

        int[] eachSubsetSum = new int[k];
        List<List<Integer>> result = new ArrayList<>();
        for(int i=0; i<k; i++) {
            result.add(new ArrayList<>());
        }
        int index = 0;
        int nonEmptySetCount = 0;
        return partitionKEqualSubsetBacktrack(arr, result, index, eachSubsetSum, nonEmptySetCount, k, n);
    }

    public static void stringPermutation() {
        String str = "abcd";
        StringBuilder current = new StringBuilder();
        boolean[] used = new boolean[str.length()];
        List<String> result = new ArrayList<>();
        stringPermutationRecursive(str, current, result, used);
        System.out.println(result);
    }


    public static void main(String[] args) {
        //letterCombine(3, 2);
    }
}
