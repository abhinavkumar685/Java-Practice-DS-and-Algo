import java.util.*;

public class DynamicProgramming {
    public static int[][] mergeIntervals(int[][] intervals) {
        // https://leetcode.com/problems/merge-intervals/
        //int[][] intervals = {{1,3}, {2,6}, {8,10}, {15,18}};
        // Best example to use Comparator
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] arr1, int[] arr2) {
                return arr1[0]-arr2[0];
            }
        });

        List<int[]> result = new ArrayList<>();
        int[] currentInterval = intervals[0];
        result.add(currentInterval);

        for (int[] thisInterval : intervals) {
            if (thisInterval[0] <= currentInterval[1]) {
                //Dynamically changing content of list with given object reference after adding already
                currentInterval[1] = Math.max(thisInterval[1], currentInterval[1]);
            } else {
                currentInterval = thisInterval;
                result.add(currentInterval);
            }
        }
        // Important conversion example for toArray
        // While converting list to array, array size should be given.
        // In case of 2D array, only 1st size can be given
        return result.toArray(new int[result.size()][]);
    }

    public static int minPathSumMethod2(int[][] arr) {
        int rowSize = arr.length;
        int colSize = arr[0].length;

        int[][] dp = new int[rowSize][colSize];
        dp[0][0] = arr[0][0];

        // Fill all the left most row prior
        for( int row=1; row < rowSize; row++) {
            dp[row][0] = arr[row][0] + dp[row-1][0];
        }

        // Fill all the top most column prior
        for( int col=1; col < colSize; col++) {
            dp[0][col] = arr[0][col] + dp[0][col-1];
        }

        for(int row=1; row<rowSize; row++) {
            for(int col=1; col<colSize; col++) {
                dp[row][col] = arr[row][col] + Math.min(dp[row-1][col], dp[row][col-1]);
            }
        }
        return dp[rowSize-1][colSize-1];
    }

    public int bestTimeToBuyStock2(int[] prices) {
        int n = prices.length;
        if(n == 0) return 0;

        int diff = 0;
        for(int i=1; i<n; i++) {
            if(prices[i]>prices[i-1]){
                diff += (prices[i]-prices[i-1]);
            }
        }
        return diff;
    }

    static void fibonacci1(int n){
        if(n == 0 || n==1) {
            System.out.println(n);
            return;
        }
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        System.out.println(dp[n]);
    }

    static int fibonacciMemoised(int n, int[] dp){
        if(n ==0 || n==1) {
            return n;
        }
        if(dp[n] != 0) {
            return dp[n];
        }
        int fb1 = fibonacciMemoised(n-1, dp);
        int fb2 = fibonacciMemoised(n-2, dp);
        dp[n] = fb1 + fb2;
        return dp[n];
    }

    static void fibonacci2(int n) {
        int[] dp = new int[n+1];
        System.out.println(fibonacciMemoised(n, dp));
    }

    static List<String> printStairsPath(int n) {
        // https://www.youtube.com/watch?v=hMJAlbJIS7E
        if(n == 0) {
            List<String> temp = new ArrayList<>();
            temp.add("");
            return temp;
        }
        else if(n < 0) {
            return new ArrayList<String>();
        }
        List<String> path1 = printStairsPath(n-1);
        List<String> path2 = printStairsPath(n-2);
        List<String> path3 = printStairsPath(n-3);
        List<String> totalPath = new ArrayList<>();

        for(String str: path1){
            totalPath.add(1 + str);
        }
        for(String str: path2){
            totalPath.add(2 + str);
        }
        for(String str: path3){
            totalPath.add(3 + str);
        }
        return totalPath;
    }

    static void climbStairsWithJumps(int n){
        // https://www.youtube.com/watch?v=uNqoQ0sNZCM
        int[] dp = new int[n+1];
        int[] input = {3, 3, 0, 2, 2, 3};

        dp[n] = 1;
        for(int i=n-1; i>=0; i--) {
            for(int j=1; j<=input[i] && i+j<=n; j++) {
                dp[i] += dp[i+j];
            }
        }
        System.out.println(dp[0]);
    }

    static void climbStairsWithMinimumSteps(int n) {
        // https://www.youtube.com/watch?v=Zobz9BXpwYE
        int[] input = {3, 2, 4, 2, 0, 2, 3, 1, 2, 2};
        Integer[] dp = new Integer[n+1];
        dp[n] = 0;
        for(int i=n-1; i>=0; i--) {
            if(input[i] > 0) {
                int min = Integer.MAX_VALUE;
                for(int j=1; j<=input[i] && i+j<dp.length; j++) {
                    if(dp[i+j] != null) {
                        min = Math.min(min, dp[i + j]);
                    }
                }
                if(min != Integer.MAX_VALUE) {
                    dp[i] = min + 1;
                }
                else {
                    dp[i] = null;
                }
            }
        }
        System.out.println(dp[0]);
    }

    static int minPathSumRecursive(int[][] arr, int row, int col, int destRow, int destCol) {

        // int[][] arr = {{1,3,1},{1,5,1},{4,2,1}};
        // System.out.println(minPathSumRecursive(arr, 0, 0, 2, 2));

        if(row<0 || row>=arr.length || col<0 || col>=arr[0].length) {
            return 100000;
        }
        else if(row == destRow && col==destCol) {
            return arr[row][col];
        }

        int val = arr[row][col];
        int val1 = val + minPathSumRecursive(arr, row, col+1, destRow, destCol);
        int val2 = val + minPathSumRecursive(arr, row+1, col, destRow, destCol);

        return Math.min(val1, val2);
    }

    static void minPathSum(int[][] arr) {
        // https://leetcode.com/problems/minimum-path-sum/
        // https://www.youtube.com/watch?v=BzTIOsC0xWM
        int rowLength = arr.length, colLength = arr[0].length;
        int[][] dp = new int[rowLength][colLength];

        for(int i=rowLength-1; i>=0; i--) {
            for(int j=colLength-1; j>=0; j--) {
                if(i == rowLength-1 && j == colLength-1) {
                    dp[i][j] = arr[i][j];
                }
                else if(i == rowLength-1) {
                    dp[i][j] = dp[i][j+1] + arr[i][j];
                }
                else if(j == colLength-1) {
                    dp[i][j] = dp[i+1][j] + arr[i][j];
                }
                else {
                    dp[i][j] = Math.min(dp[i+1][j], dp[i][j+1]) + arr[i][j];
                }
            }
        }
        System.out.println(dp[0][0]);
    }

    public void uniquePaths(int m, int n) {
        // https://leetcode.com/problems/unique-paths/
        // https://leetcode.com/problems/unique-paths/discuss/182143/Recursive-memoization-and-dynamic-programming-solutions
        int[][] dp = new int[m][n];
        for(int row=0; row<m; row++) {
            for(int col=0; col<n; col++) {
                if(row == 0) {
                    dp[row][col] = 1;
                }
                else if(col == 0) {
                    dp[row][col] = 1;
                }
                else {
                    dp[row][col] = dp[row-1][col] + dp[row][col-1];
                }
            }
        }
        System.out.println(dp[m-1][n-1]);
    }

    static int goldMineRecursive(int[][] arr, int row, int col) {
        if(row<0 || row>=arr.length || col<0 || col>= arr[row].length) {
            return 0;
        }

        int val  = arr[row][col];
        int val1 = val + goldMineRecursive(arr, row-1, col+1);
        int val2 = val + goldMineRecursive(arr, row, col+1);
        int val3 = val + goldMineRecursive(arr, row+1, col+1);

        return Math.max(Math.max(val1, val2), val3);
    }

    static void goldMineMethod2(int[][] arr) {
        // https://www.youtube.com/watch?v=hs0lilfJ7C0
        int maxSum = 0;
        for(int i=0; i<arr.length; i++) {
            int currentSum = goldMineRecursive(arr, i, 0);
            maxSum = Math.max(currentSum, maxSum);
        }
        System.out.println(maxSum);
    }

    static void goldMine(int[][] arr) {
        // https://www.youtube.com/watch?v=hs0lilfJ7C0
        int m = arr.length;
        int n = arr[0].length;
        int[][] dp = new int[m][n];

        for(int col=n-1; col>=0; col--){
            for(int row=m-1; row>=0; row--) {
                if(col == n-1) {
                    dp[row][col] = arr[row][col];
                }
                else if(row == 0) {
                    dp[row][col] = Math.max(dp[row][col+1], dp[row+1][col+1]) + arr[row][col];
                }
                else if(row == m-1) {
                    dp[row][col] = Math.max(dp[row][col+1], dp[row-1][col+1]) + arr[row][col];
                }
                else {
                    dp[row][col] = Math.max(Math.max(dp[row][col+1], dp[row-1][col+1]), dp[row+1][col+1]) +
                            arr[row][col];
                }
            }
        }
        int max = dp[0][0];
        for (int[] row : dp) {
            if (row[0] > max) {
                max = row[0];
            }
        }
        System.out.println(max);
    }

    static void targetSumSubset(int[] arr, int target) {
        // https://www.youtube.com/watch?v=tRpkluGqINc
        boolean[][] dp = new boolean[arr.length+1][target+1];
        for(int row=0; row<dp.length; row++) {
            for(int col=0; col<dp[0].length; col++) {
                if(row == 0 && col == 0) {
                    dp[row][col] = true;
                }
                else if(row == 0) {
                    dp[row][col] = false;
                }
                else if(col == 0) {
                    dp[row][col] = true;
                }
                else {
                    if(dp[row-1][col] == true) {
                        dp[row][col] = true;
                    }
                    else {
                        int val = arr[row-1];
                        if(col >= val) {
                            if(dp[row-1][col-val] == true) {
                                dp[row][col] = true;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(dp[arr.length][target]);
    }

    public boolean canEqualPartition(int[] arr) {
        int sum = 0;
        for(int i: arr) {
            sum += i;
        }

        if(sum%2 != 0) {
            return false;
        }

        int target = sum/2;

        boolean[][] dp = new boolean[arr.length+1][target+1];
        for(int row=0; row<dp.length; row++) {
            for(int col=0; col<dp[0].length; col++) {
                if(row == 0 && col == 0) {
                    dp[row][col] = true;
                }
                else if(row == 0) {
                    dp[row][col] = false;
                }
                else if(col == 0) {
                    dp[row][col] = true;
                }
                else {
                    if(dp[row - 1][col]) {
                        dp[row][col] = true;
                    }
                    else {
                        int val = arr[row-1];
                        if(col >= val) {
                            if(dp[row - 1][col - val]) {
                                dp[row][col] = true;
                            }
                        }
                    }
                }
            }
        }
        return dp[arr.length][target];
    }

    static void coinChangeCombination(int[] coins, int target) {
        // https://www.youtube.com/watch?v=l_nR5X9VmaI
        int[] dp = new int[target+1];
        dp[0] = 1;
        for(int i=0; i<coins.length; i++) {
            for(int j=coins[i]; j<dp.length; j++) {
                dp[j] = dp[j] + dp[j-coins[i]];
            }
        }
        System.out.println(dp[target]);
    }

    static void coinChangePermutations(int[] coins, int target) {
        // https://www.youtube.com/watch?v=yc0LunmJA1A
        int[] dp = new int[target+1];
        dp[0] = 1;

        for(int i=1; i<=target; i++) {
            for(int j=0; j<coins.length; j++) {
                if(i >= coins[j]) {
                    dp[i] += dp[i-coins[j]];
                }
            }
        }
        System.out.println(dp[target]);
    }

    static void knapsack01(int[] weight, int[] value, int targetWeight) {
        int[][] dp = new int[weight.length + 1][targetWeight + 1];
        // Starting with 1 index coz by default 0 index is filled with zeroes of array
        for(int i=1; i<dp.length; i++) {
            for(int j=1; j<dp[0].length; j++) {
                if(j >= weight[i-1]) {
                    // dp[i][j] = Math.max(dp[i - 1][j - weight[i - 1]] + value[i - 1], dp[i - 1][j]);
                    if(dp[i-1][j-weight[i-1]] + value[i-1] > dp[i-1][j]) {
                        dp[i][j] = dp[i-1][j-weight[i-1]] + value[i-1];
                    }
                    else {
                        dp[i][j] = dp[i-1][j];
                    }
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        System.out.println(dp[weight.length][targetWeight]);
    }

    static void knapsack01Unbounded(int[] weight, int[] value, int targetWeight) {
        // https://www.youtube.com/watch?v=jgps7MXtKRQ
        int[] dp = new int[targetWeight + 1];
        dp[0] = 0;
        for(int target=1; target<=targetWeight; target++) {
            int max = 0;
            for(int i=0; i<weight.length; i++) {
                if(weight[i] <= target) {
                    int remainingCapacity = target - weight[i];
                    int total = dp[remainingCapacity] + value[i];
                    max = Math.max(max, total);
                }
            }
            dp[target] = max;
        }
        System.out.println(dp[targetWeight]);
    }

    static void countBinaryStrings(int n) {
        // https://www.youtube.com/watch?v=nqrXHJWMeBc
        int[] dp0 = new int[n + 1];
        int[] dp1 = new int[n + 1];
        dp0[1] = 1;
        dp1[1] = 1;
        for(int i=2; i<n+1 ; i++) {
            dp0[i] =  dp1[i-1];
            dp1[i] =  dp0[i-1] + dp1[i-1];
        }
        System.out.println(dp0[n] + dp1[n]);
    }

    static void arrangeBuildings(int n) {
        // https://www.youtube.com/watch?v=0nF-BMYy7tc
        int oldCountWithSpaceAtLast = 1;
        int oldCountWithBuildingAtLast = 1;
        for(int i=2; i<n; i++) {
            int newCountWithBuildingAtLast = oldCountWithSpaceAtLast;
            int newCountWithSpaceAtLast = oldCountWithSpaceAtLast + oldCountWithBuildingAtLast;

            oldCountWithSpaceAtLast = newCountWithSpaceAtLast;
            oldCountWithBuildingAtLast = newCountWithBuildingAtLast;
        }
        int total = oldCountWithSpaceAtLast + oldCountWithBuildingAtLast;
        total = total * total;
        System.out.println(total);
    }

    static void countEncodings(String str) {
        // https://www.youtube.com/watch?v=jFZmBQ569So
        int n = str.length();
        int[] dp = new int[10];
        dp[0] = 1;

        for(int i=1; i<n; i++) {
            if(str.charAt(i-1) == '0' && str.charAt(i) == '0') {
                dp[i] = 0;
            }
            else if(str.charAt(i-1) == '0' && str.charAt(i) != '0') {
                dp[i] = dp[i-1];
            }
            else if(str.charAt(i-1) != '0' && str.charAt(i) == '0') {
                if(str.charAt(i-1) == '1' || str.charAt(i-1) == '2') {
                    dp[i] = dp[i-1] + (i>=2 ? dp[i-2] : 1);
                }
                else {
                    dp[i] = 0;
                }
            }
            else {
                if(Integer.parseInt(str.substring(i-1, i+1)) <= 26) {
                    dp[i] = dp[i-1] + (i>=2 ? dp[i-2] : 1);
                }
                else {
                    dp[i] = dp[i-1];
                }
            }
        }
        System.out.println(dp[n-1]);
    }

    static void maxSumNonAdjacent(int[] arr) {
        // https://www.youtube.com/watch?v=VT4bZV24QNo
        int include = arr[0];
        int exclude = 0;

        for(int i=1; i<arr.length; i++) {
            int newInclude = exclude + arr[i];
            int newExclude = Math.max(include, exclude);

            include = newInclude;
            exclude = newExclude;
        }
        System.out.println(Math.max(include, exclude));
    }

    public void paintHouse1(int[][] costs) {
        // https://www.youtube.com/watch?v=kh48JLieeW8
        // https://www.lintcode.com/problem/515/
        if(costs.length == 0) return;
        int oldRed = costs[0][0];
        int oldBlue = costs[0][1];
        int oldGreen = costs[0][2];

        for(int i=1; i<costs.length; i++) {
            int newRed = Math.min(oldBlue, oldGreen) + costs[i][0];
            int newBlue = Math.min(oldRed, oldGreen) + costs[i][1];
            int newGreen = Math.min(oldRed, oldBlue) + costs[i][2];

            oldRed = newRed;
            oldBlue = newBlue;
            oldGreen = newGreen;
        }
        System.out.println(Math.min(Math.min(oldRed, oldBlue), oldGreen));
    }

    public void paintHouse2(int[][] costs) {
        // https://www.youtube.com/watch?v=jGywRalvoRw
        // https://www.lintcode.com/problem/516/
        if(costs.length == 0) return;
        int[][] dp = new int[costs.length][costs[0].length];
        for(int i=0; i<costs[0].length; i++) {
            dp[0][i] = costs[0][i];
        }
        for(int row=1; row<costs.length; row++) {
            for(int col=0; col<costs[0].length; col++) {
                int min = Integer.MAX_VALUE;
                for(int k=0; k<costs[0].length; k++) {
                    if(k != col) {
                        if(dp[row-1][k] < min) {
                            min = dp[row-1][k];
                        }
                    }
                }
                dp[row][col] = min + costs[row][col];
            }
        }
        int result = Integer.MAX_VALUE;
        for(int i=0; i<dp[0].length; i++) {
            result = Math.min(result, dp[dp.length-1][i]);
        }
        System.out.println(result);
    }

    public void paintFence(int n, int k) {
        // https://www.youtube.com/watch?v=ju8vrEAsa3Q
        int same = k * 1;
        int diff = k * (k-1);
        int total = same + diff;
        for(int i=3; i<=n; i++) {
            same = diff * 1;
            diff = total * (k-1);
            total = same + diff;
        }
        System.out.println(total);
    }

    public static void tiling(int n) {
        // https://www.youtube.com/watch?v=dVT9JeUMMDE
        if(n==0) return;
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        System.out.println(dp[n]);
    }

    public static void tilingMxN(int m, int n) {
        // https://www.youtube.com/watch?v=_c_R-uIi-zU
        int[] dp = new int[n+1];
        for(int i=1; i<=n; i++) {
            if(i < m) {
                dp[i] = 1;
            }
            else if(i == m) {
                dp[i] = 2;
            }
            else {
                dp[i] = dp[i-1] + dp[i-m];
            }
        }
        System.out.println(dp[n]);
    }

    public static void friendPairing(int n) {
        // https://www.youtube.com/watch?v=SHDu0Ufjyk8
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3; i<=n; i++) {
            dp[i] = dp[i-1] + (i-1) * dp[i-2];
        }
        System.out.println(dp[n]);
    }

    static void partitionIntoSubsets(int n, int k) {
        // https://www.youtube.com/watch?v=IiAsqfjhZnY
        if(n==0 || k==0 || n<k) {
            return;
        }
        int[][] dp = new int[k+1][n+1];
        for(int t=1; t<=k; t++) {
            for(int p=1; p<=n; p++) {
                if(p<t) {
                    dp[t][p] = 0;
                }
                else if(p == t) {
                    dp[t][p] = 1;
                }
                else {
                    dp[t][p] = dp[t-1][p-1] + dp[t][p-1] * t;
                }
            }
        }
        System.out.println(dp[k][n]);
    }

    static void maxProfit1(int[] prices) {
        // https://www.youtube.com/watch?v=4YjEHmw1MX0
        // https://leetcode.com/problems/best-time-to-buy-and-sell-stock
        int least = Integer.MAX_VALUE;
        int profit = 0;
        for (int price : prices) {
            least = Math.min(least, price);
            profit = Math.max(profit, price - least);
        }
        System.out.println(profit);
    }

    static void maxProfit2(int[] prices) {
        // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii
        // https://www.youtube.com/watch?v=HWJ9kIPpzXs
        int buy = prices[0];
        int sell = prices[0];
        int profit = 0;
        for(int i=1; i<prices.length; i++) {
            if(prices[i] < prices[i-1]) {
                profit += (sell-buy);
                buy = prices[i];
                sell = prices[i];
            }
            else {
                sell = prices[i];
            }
        }
        profit += (sell-buy);
        System.out.println(profit);
    }

    static List<Integer> hackerCards(List<Integer> collection, int d) {
        List<Integer> result = new ArrayList<>();
        int total = 0;
        for(int i=1; i<=d; i++) {
            if(!collection.contains(i)) {
                if(total + i <= d) {
                    result.add(i);
                }
                total += i;
            }
        }
        return result;
    }

    public int maxProfitWithTransactionFee(int[] prices, int fee) {
        // https://www.youtube.com/watch?v=pTQB9wbIpfU
        // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
        int n = prices.length;
        int oldBoughtProfit = -prices[0];
        int oldSoldProfit = 0;
        for(int i=1; i<n; i++) {
            int newBoughtProfit = 0;
            int newSoldProfit = 0;
            // Profit till now after buying at current price
            if(oldSoldProfit - prices[i] > oldBoughtProfit) {
                newBoughtProfit = oldSoldProfit - prices[i];
            }
            else {
                newBoughtProfit = oldBoughtProfit;
            }
            // Profit till now after selling at current price
            if(oldBoughtProfit + prices[i] - fee > oldSoldProfit) {
                newSoldProfit = oldBoughtProfit + prices[i] - fee;
            }
            else {
                newSoldProfit = oldSoldProfit;
            }

            oldBoughtProfit = newBoughtProfit;
            oldSoldProfit = newSoldProfit;
        }
        return oldSoldProfit;
    }

    public int maxProfitWithCooldown(int[] prices) {
        // https://www.youtube.com/watch?v=GY0O57llkKQ
        // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
        int n = prices.length;
        int oldBoughtProfit = -prices[0];
        int oldSoldProfit = 0;
        int oldCooldownProfit = 0;
        for(int i=1; i<n; i++) {
            int newBoughtProfit = 0;
            int newSoldProfit = 0;
            int newCooldownProfit = 0;
            // Profit till now after buying at current price
            if(oldCooldownProfit - prices[i] > oldBoughtProfit) {
                newBoughtProfit = oldCooldownProfit - prices[i];
            }
            else {
                newBoughtProfit = oldBoughtProfit;
            }
            // Profit till now after selling at current price
            if(oldBoughtProfit + prices[i] > oldSoldProfit) {
                newSoldProfit = oldBoughtProfit + prices[i];
            }
            else {
                newSoldProfit = oldSoldProfit;
            }
            if(oldSoldProfit > oldCooldownProfit) {
                newCooldownProfit = oldSoldProfit;
            }
            else {
                newCooldownProfit = oldCooldownProfit;
            }

            oldBoughtProfit = newBoughtProfit;
            oldSoldProfit = newSoldProfit;
            oldCooldownProfit = newCooldownProfit;
        }
        return oldSoldProfit;
    }

    public int maxProfit3(int[] prices) {
        int firstSell = 0;
        int secondSell = 0;
        int firstBuy = Integer.MAX_VALUE;
        int secondBuy = Integer.MAX_VALUE;
        for(int i = 0; i < prices.length; i++) {
            int p = prices[i];
            firstBuy = Math.min(firstBuy, p);
            firstSell = Math.max(firstSell, p - firstBuy);
            secondBuy = Math.min(secondBuy, p - firstSell);
            secondSell = Math.max(secondSell, p - secondBuy);
        }
        return secondSell;
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        // https://www.youtube.com/watch?v=dxbRB6gWCqg
        // https://leetcode.com/problems/insert-interval/
        Arrays.sort(intervals, (int[] arr1, int[] arr2) -> {
            return arr1[0]-arr2[0];
        });
        int n = intervals.length;
        List<int[]> result = new ArrayList<>();
        // Get the Insert point of newInterval
        int idx = 0;
        while(idx < n) {
            if(intervals[idx][0] < newInterval[0]) {
                result.add(intervals[idx]);
                idx++;
            }
            else {
                break;
            }
        }
        // At insert point either add or merge with existing interval
        if(result.size() == 0 || (result.get(result.size()-1)[1] < newInterval[0])) {
            result.add(newInterval);
        }
        else {
            int[] previous = result.get(result.size()-1);
            previous[1] = Math.max(previous[1], newInterval[1]);
        }
        // Now go till end to merge the existing intervals
        while(idx < n) {
            int[] previous = result.get(result.size()-1);
            if(previous[1] >= intervals[idx][0]) {
                previous[1] = Math.max(previous[1], intervals[idx][1]);
            }
            else {
                result.add(intervals[idx]);
            }
            idx++;
        }
        return result.toArray(new int[result.size()][]);
    }

    static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        // https://leetcode.com/problems/interval-list-intersections
        // https://www.youtube.com/watch?v=fmdNUOQnhrU
        List<int[]> result = new ArrayList<>();
        if(firstList.length == 0 || secondList.length == 0) {
            return result.toArray(new int[0][]);
        }
        int index1 = 0, index2 = 0;
        while(index1 < firstList.length && index2 < secondList.length) {
            int currentStart = Math.max(firstList[index1][0], secondList[index2][0]);
            int currentEnd = Math.min(firstList[index1][1], secondList[index2][1]);

            if(currentStart <= currentEnd) {
                int[] current = {currentStart, currentEnd};
                result.add(current);
            }
            if(firstList[index1][1] < secondList[index2][1]) {
                index1++;
            }
            else {
                index2++;
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    public class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public int minMeetingRooms(List<Interval> intervals) {
        // https://www.youtube.com/watch?v=qHmAwknX6OY
        // https://www.lintcode.com/problem/919/
        // Using sort https://www.youtube.com/watch?v=NKf1OJhEZj0
        if(intervals.size() == 0) return 0;
        Collections.sort(intervals, (Interval i1, Interval i2) -> {
            return i1.start - i2.start;
        });
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(Interval interval : intervals) {
            if(queue.size() == 0) {
                queue.offer(interval.end);
            }
            else {
                if(queue.peek() > interval.start) {
                    queue.offer(interval.end);
                }
                else {
                    queue.poll();
                    queue.offer(interval.end);
                }
            }
        }
        return queue.size();
    }

    public static int LongestCommonSubsequence(String s1, String s2) {
        // https://www.youtube.com/watch?v=0Ql40Llp09E
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

    public int coinChangeMinAmount(int[] coins, int amount) {
        // https://leetcode.com/problems/coin-change/
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for(int amt=1; amt<=amount; amt++) {
            dp[amt] = Integer.MAX_VALUE;
            for(int c=coins.length-1; c>=0; c--) {
                int coin = coins[c];
                if(amt >= coin) {
                    int remain = amt - coin;
                    if(dp[remain] < Integer.MAX_VALUE) {
                        dp[amt]  =  Math.min(dp[amt], 1 + dp[remain]);
                    }
                }
            }
        }
        return dp[amount] < Integer.MAX_VALUE ? dp[amount] : -1;
    }

    public static int editDistance(String str1, String str2) {
        // https://leetcode.com/problems/edit-distance
        // https://www.youtube.com/watch?v=tooMn-xfYCU
        int[][] dp = new int[str1.length()+1][str2.length()+1];
        for(int i=0; i<dp.length; i++) {
            for(int j=0; j<dp[0].length; j++) {
                if(i==0 && j==0) {
                    dp[i][j] = 0;
                }
                else if(i == 0) {
                    dp[i][j] = j;
                }
                else if(j == 0) {
                    dp[i][j] = i;
                }
                else {
                    if(str1.charAt(i-1) == str2.charAt(j-1)) {
                        dp[i][j] = dp[i-1][j-1];
                    }
                    else {
                        int insert = dp[i][j-1];
                        int delete = dp[i-1][j];
                        int replace = dp[i-1][j-1];
                        dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
                    }
                }
            }
        }
        return dp[dp.length - 1 ][dp[0].length - 1];
    }

    public static int minSubsetSumDifference(int[] arr, int n) {
        // https://www.techiedelight.com/minimum-sum-partition-problem/
        // https://www.youtube.com/watch?v=FB0KUhsxXGY
        int sum = 0;
        for(int x : arr){
            sum += x;
        }

        boolean[][] dp = new boolean[arr.length + 1][sum + 1];
        for(int i=0; i<dp.length; i++) {
            for(int j=0; j<dp[0].length; j++) {
                if(i==0 && j==0) {
                    dp[i][j] = true;
                }
                else if(i==0) {
                    dp[i][j] = false;
                }
                else if(j==0) {
                    dp[i][j] = true;
                }
                else {
                    if(dp[i-1][j]) {
                        dp[i][j] = true;
                    }
                    else {
                        int val = arr[i-1];
                        if(j >= val){
                            if(dp[i-1][j-val]) {
                                dp[i][j] = true;
                            }
                        }
                    }
                }
            }
        }
        int j = sum / 2;
        while(j >= 0 && !dp[dp.length-1][j]) {
            j--;
        }
        return sum - 2 * j;
    }

    public boolean isValidMove(int n, int ni, int nj) {
        return ni >= 0 && nj >= 0 && ni < n && nj < n;
    }

    public double knightProbability(int n, int k, int row, int column) {
        // https://leetcode.com/problems/knight-probability-in-chessboard
        // https://www.youtube.com/watch?v=54nJhM2AZv4
        double[][] current = new double[n][n];
        double[][] next = new double[n][n];

        current[row][column] = 1;

        for(int m=0; m<k; m++) {
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    if(current[i][j] != 0) {
                        int ni = 0;
                        int nj = 0;

                        ni = i-2;
                        nj = j+1;
                        if(isValidMove(n, ni, nj)) {
                            next[ni][nj] += current[i][j] / 8.0;
                        }

                        ni = i-1;
                        nj = j+2;
                        if(isValidMove(n, ni, nj)) {
                            next[ni][nj] += current[i][j] / 8.0;
                        }

                        ni = i+1;
                        nj = j+2;
                        if(isValidMove(n, ni, nj)) {
                            next[ni][nj] += current[i][j] / 8.0;
                        }

                        ni = i+2;
                        nj = j+1;
                        if(isValidMove(n, ni, nj)) {
                            next[ni][nj] += current[i][j] / 8.0;
                        }

                        ni = i+2;
                        nj = j-1;
                        if(isValidMove(n, ni, nj)) {
                            next[ni][nj] += current[i][j] / 8.0;
                        }

                        ni = i+1;
                        nj = j-2;
                        if(isValidMove(n, ni, nj)) {
                            next[ni][nj] += current[i][j] / 8.0;
                        }

                        ni = i-1;
                        nj = j-2;
                        if(isValidMove(n, ni, nj)) {
                            next[ni][nj] += current[i][j] / 8.0;
                        }

                        ni = i-2;
                        nj = j-1;
                        if(isValidMove(n, ni, nj)) {
                            next[ni][nj] += current[i][j] / 8.0;
                        }
                    }
                }
            }
            current = next;
            next = new double[n][n];
        }
        double sum = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                sum += current[i][j];
            }
        }
        return sum;
    }

    public static int countSubsequences(int[] a, int n, int p) {
        // https://www.codingninjas.com/codestudio/problems/count-the-number-of-subsequences-having-product-not-more-than-p_1214647
        // https://www.youtube.com/watch?v=iOjV84903WA
        int mod = 1000000007;
        // 2D array to store result.
        int[][] dp = new int[p + 1][n + 1];
        for (int i = 1; i <= p; i++) {
            for (int j = 1; j <= n; j++) {
                // Store result of 'j - 1' elements.
                dp[i][j] = dp[i][j - 1] % mod;
                // Check whether to include 'j'th element or not.
                if (a[j - 1] <= i && a[j - 1] > 0) {
                    //Count of subsequences by taking 'j'th element.
                    dp[i][j] = (dp[i][j] + dp[i / a[j - 1]][j - 1] + 1) % mod;
                }
            }
        }
        // Result is stored at 'DP[P][N]'.
        return dp[p][n];
    }

    public static int subsequenceSumEquaksK(int[] a, int n, int k) {
        // https://geeksforgeeks.org/find-all-subsequences-with-sum-equals-to-k/
        // https://www.techiedelight.com/subset-sum-problem/
        int mod = 1000000007;
        // 2D array to store result.
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                // Store result of 'j - 1' elements.
                dp[i][j] = dp[i][j - 1] % mod;
                // Check whether to include 'j'th element or not.
                if (a[j - 1] <= i && a[j - 1] > 0) {
                    //Count of subsequences by taking 'j'th element.
                    dp[i][j] = (dp[i][j] + dp[i - a[j - 1]][j - 1] + 1) % mod;
                }
            }
        }
        // Result is stored at 'DP[P][N]'.
        return dp[k][n];
    }

    public int maximumSumSquareSubMatrix(ArrayList<ArrayList<Integer>> A, int B) {
        // https://www.interviewbit.com/problems/maximum-sum-square-submatrix/
        // https://www.youtube.com/watch?v=WxjYE4_agbo
        int row = A.size();
        int col = A.get(0).size();
        int maxSum = Integer.MIN_VALUE;
        int[][] dp = new int[row+1][col+1];

        for(int i=1; i<dp.length; i++) {
            for(int j=1; j<dp.length; j++) {
                dp[i][j] = A.get(i-1).get(j-1) + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1];
            }
        }

        for(int i=1; i<dp.length; i++) {
            for(int j=1; j<dp.length; j++) {
                int sum = Integer.MIN_VALUE;
                if(i-B>=0 && j-B>=0) {
                    sum = dp[i][j] - dp[i-B][j] - dp[i][j-B] + dp[i-B][j-B];
                    maxSum = Math.max(sum, maxSum);
                }
            }
        }
        return maxSum;
    }

    public boolean wordBreakMemoized(String s, List<String> wordDict, Map<String, Boolean> map) {
        if(wordDict.contains(s)) {
            return true;
        }
        if(map.containsKey(s)) {
            return map.get(s);
        }
        for(int i=1; i<=s.length(); i++) {
            String left = s.substring(0, i);
            if(wordDict.contains(left) && wordBreakMemoized(s.substring(i), wordDict, map)) {
                map.put(s, true);
                return true;
            }
        }
        map.put(s, false);
        return false;
    }

    public boolean wordBreakI(String s, List<String> wordDict) {
        // https://leetcode.com/problems/word-break
        // https://www.youtube.com/watch?v=LPs6Qo5qlJM
        Map<String, Boolean> map = new HashMap<>();
        return wordBreakMemoized(s, wordDict, map);
    }

    public boolean wordBreakDP(String s, List<String> wordDict) {
        // https://leetcode.com/problems/word-break
        // https://www.youtube.com/watch?v=iWenZCZEBIA
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for(int i=0; i<=n; i++) {
            for(int j=0; j<i; j++) {
                if(dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    public boolean wordBreakDPOptimized(String s, List<String> wordDict) {
        // https://leetcode.com/problems/word-break
        // https://www.youtube.com/watch?v=iWenZCZEBIA
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        int maxLen = 0;
        for(String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }
        dp[0] = true;
        for(int i=0; i<=n; i++) {
            for(int j=i-1; j>=0; j--) {
                if(i - j > maxLen) {
                    continue;
                }
                if(dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    public int countSubstringsUsingDiagonalTraversal(String s) {
        // https://leetcode.com/problems/palindromic-substrings
        // https://www.youtube.com/watch?v=XmSOWnL6T_I
        int n = s.length();
        if(s.length() < 2) return n;
        int count = 0;
        boolean[][] dp = new boolean[n][n];

        // Diagonal Traversal
        // gap = It is the diff between two index of string from start to end
        // gap = 0 means 1 char will be taken

        for(int gap = 0; gap < n; gap++) {
            // i = diagonal start row which is always from  row 0 of table
            // j = diagonal column tracker which starts from gap and goes till table end

            for(int i=0, j=gap; j<dp.length; i++, j++) {
                if(gap == 0) {
                    dp[i][j] = true;
                }
                else if(gap == 1) {
                    if(s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = true;
                    }
                    else {
                        dp[i][j] = false;
                    }
                }
                else {
                    if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1] == true) {
                        dp[i][j] = true;
                    }
                    else {
                        dp[i][j] = false;
                    }
                }

                if(dp[i][j] == true) {
                    count++;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        arr.add(4);
        arr.add(6);
        arr.add(12);
        arr.add(8);
        System.out.println(hackerCards(arr, 14));
    }
}
