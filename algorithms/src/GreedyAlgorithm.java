import java.util.*;

class Job {
    int id, profit, deadline;
    Job(int x, int y, int z){
        this.id = x;
        this.deadline = y;
        this.profit = z;
    }

    public String toString() {
        return this.id + " " + this.deadline + " " + this.profit + " ";
    }
}

class Item {
    int value, weight;
    Item(int x, int y){
        this.value = x;
        this.weight = y;
    }
}

public class GreedyAlgorithm {
    public static int activitySelection(int[] start, int[] end, int n) {
        // https://www.youtube.com/watch?v=AsbDqOauGZE
        List<int[]> activity = new ArrayList<>();
        for(int i=0; i<n; i++) {
            int[] a = {start[i], end[i]};
            activity.add(a);
        }

        Collections.sort(activity, (int[] a, int[] b) -> {
            if(a[1] == b[1]) {
                return a[0] - b[0];
            }
            else {
                return a[1] - b[1];
            }
        });

        int count = 1;
        int i = 0;
        int j = 1;

        while(j < n) {
            if(activity.get(i)[1] < activity.get(j)[0]) {
                count++;
                i = j;
                j++;
            }
            else {
                j++;
            }
        }
        return count;
    }

    static int[] JobScheduling(Job[] arr, int n) {
        // https://www.youtube.com/watch?v=bID397v7ja4
        Arrays.sort(arr, (Job j1, Job j2) -> {
            return j2.profit - j1.profit;
        });
        boolean[] visited = new boolean[n];
        int profit = 0;
        int count = 0;
        for(int i=0; i<n; i++) {
            int deadline = arr[i].deadline - 1;
            for(int j=Math.min(n, deadline); j>=0; j--) {
                if(!visited[j]) {
                    visited[j] = true;
                    count++;
                    profit += arr[i].profit;
                    break;
                }
            }
        }

        int[] ans = {count, profit};
        return ans;
    }

    double fractionalKnapsack(int W, Item[] arr, int n) {
        // Your code here
        Arrays.sort(arr, (Item item1, Item item2) -> {
            double r1 = (item1.value * 1.00) / item1.weight;
            double r2 = (item2.value * 1.00) / item2.weight;
            // Descending Order
            if(r1 > r2) {
                return -1;
            }
            else if(r1 < r2) {
                return 1;
            }
            else {
                return 0;
            }
        });

        int cap = W;
        double result = 0.00;

        for(int i=0; i<n; i++) {
            if(cap == 0) {
                break;
            }
            else if(arr[i].weight <= cap) {
                result += (double)arr[i].value;
                cap -= arr[i].weight;
            }
            else {
                result += arr[i].value * ((double)cap / arr[i].weight);
                cap=0;
                break;
            }
        }
        return result;
    }
    static int findPlatform(int[] arr, int[] dep, int n) {
        // https://www.youtube.com/watch?v=eiM8LGi4h_g
        // https://www.youtube.com/watch?v=qHmAwknX6OY (Same for meeting rooms)
        Arrays.sort(arr);
        Arrays.sort(dep);

        int i=0, j=0, count=0;
        while(i < n) {
            if(arr[i] <= dep[j]) {
                count++;
                i++;
            }
            else {
                i++;
                j++;
            }
        }
        return count;
    }

    public int gasStation(int[] gas, int[] cost) {
        // https://leetcode.com/problems/gas-station/
        // https://www.youtube.com/watch?v=xWgbFI_rXJs
        int index = 0;
        int total = 0;
        int tank = 0;

        for(int i=0; i<gas.length; i++) {
            int consume = gas[i] - cost[i];
            tank += consume;
            if(tank < 0) {
                index = i+1;
                tank = 0;
            }
            total += consume;
        }
        return total >= 0 ? index : -1;
    }

    public int candyDistribution(ArrayList<Integer> A) {
        // https://www.youtube.com/watch?v=h6_lIwZYHQw
        // https://www.interviewbit.com/problems/distribute-candy/
        int[] arr_L = new int[A.size()];
        int[] arr_R = new int[A.size()];
        Arrays.fill(arr_L, 1);
        Arrays.fill(arr_R, 1);

        // Moving from left to right
        for(int i=1; i<A.size(); i++) {
            if(A.get(i) > A.get(i-1)) {
                arr_L[i] = arr_L[i-1] + 1;
            }
        }

        // Moving from right to left
        for(int i=A.size()-2; i>=0; i--) {
            if(A.get(i) > A.get(i+1)) {
                arr_R[i] = arr_R[i+1] + 1;
            }
        }

        int sum = 0;
        for(int i=0; i<A.size(); i++) {
            sum += Math.max(arr_L[i], arr_R[i]);
        }
        return sum;
    }

    String stringChooseAndSwap(String A){
        // https://www.youtube.com/watch?v=NhnsINajZRA
        // https://practice.geeksforgeeks.org/problems/choose-and-swap0531/1
        Set<Character> set = new TreeSet<>();
        int n = A.length();
        char[] arr = A.toCharArray();
        for(char c: arr) {
            set.add(c);
        }

        for(int i=0; i<n; i++) {
            set.remove(arr[i]);
            if(set.isEmpty()) {
                break;
            }
            char ch = set.iterator().next();
            if(ch < arr[i]) {
                // swap
                char ch2 = arr[i];
                for(int j=0; j<n; j++) {
                    if(arr[j] == ch) {
                        arr[j] = ch2;
                    }
                    else if(arr[j] == ch2) {
                        arr[j] = ch;
                    }
                }
                break;
            }
        }
        return new String(arr);
    }

}
