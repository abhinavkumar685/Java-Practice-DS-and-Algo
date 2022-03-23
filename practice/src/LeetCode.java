import java.util.*;

public class LeetCode {
    public static String zigZagSolution(String s, int numRows) {
        StringBuilder[] array = new StringBuilder[numRows];
        for(int u=0; u<numRows; u++) {
            array[u] = new StringBuilder();
        }
        char[] myString = s.toCharArray();
        int n = myString.length;
        int index = 0;

        while(index < n) {
            for(int j=0; j<numRows && index<n; j++) {
                array[j].append(myString[index++]);
            }

            for(int k=numRows-2; k>0 && index<n; k--) {
                array[k].append(myString[index++]);
            }
        }

        StringBuilder response = array[0];
        for(int x=1; x<numRows; x++) {
            response.append(array[x].toString());
        }

        return response.toString();

    }

    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";
        if(strs.length == 1) return strs[0];

        StringBuilder result = new StringBuilder();
        String first = strs[0];
        int index=0;
        int n = first.length();
        while(index < n) {
            char original = first.charAt(index);
            boolean charMatched = true;
            for(int i=1; i<strs.length; i++) {
                String currentSting = strs[i];
                int currentLength = currentSting.length();
                if(currentLength == 0) {
                    charMatched = false;
                    break;
                }
                if(currentLength <= index) {
                    charMatched = false;
                    break;
                }
                if (original != currentSting.charAt(index)) {
                    charMatched = false;
                    break;
                }
            }
            if(charMatched) {
                result.append(original);
                index++;
            }
            else {
                break;
            }
        }
        return result.toString();
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        if(nums.length == 0) return new ArrayList<>(res);
        Arrays.sort(nums);
        for(int i=0; i<nums.length-2; i++) {
            int left = i+1;
            int right = nums.length-1;

            while(left < right) {
                int sum = nums[left] + nums[right];
                if(sum == -nums[i]) {
                    res.add(Arrays.asList(nums[i], nums[left++], nums[right--]));
                }
                else if (sum > -nums[i]) {
                    right--;
                }
                else if(sum < -nums[i]) {
                    left++;
                }
            }
        }
        return new ArrayList<>(res);
    }

    public static int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;
        int result = 0;
        Arrays.sort(nums);
        for(int index = 0; index<nums.length-2; index++) {
            int left = index+1;
            int right = nums.length-1;

            while(left < right) {
                int sum = nums[index] + nums[left] + nums[right];
                if(sum == target) return sum;

                if(diff > Math.abs(target - sum)) {
                    result = sum;
                    diff = Math.abs(target - sum);
                }
                if(sum > target) {
                    right --;
                }
                else {
                    left++;
                }
            }
        }
        return result;
    }

    public static void CombinationBackTracking(List<String> result, String digits, int index, StringBuilder currentString,
                                               Map<Integer, String> map) {
        if(index == digits.length()) {
            result.add(currentString.toString());
            return;
        }

        String temp = map.get(digits.charAt(index) - '0');
        for(int i=0; i<temp.length(); i++) {
            currentString.append(temp.charAt(i));
            CombinationBackTracking(result, digits, index+1, currentString, map);
            currentString.deleteCharAt(currentString.length() - 1);
        }

    }
    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if(digits==null || digits.length() == 0) return result;

        Map<Integer, String> map = new HashMap<>();
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

        StringBuilder currentString = new StringBuilder();
        CombinationBackTracking(result, digits, 0, currentString, map);
        return result;
    }

    public static void powerSetBacktrack(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> currentList,
                                         int index, int[] arr) {
        if(index == arr.length) {
            result.add(new ArrayList<>(currentList));
            return;
        }

        currentList.add(arr[index]);
        powerSetBacktrack(result, currentList, index+1, arr);
        currentList.remove(currentList.size()-1);
        powerSetBacktrack(result, currentList, index+1, arr);
    }

    public static void powerSet(int[] arr) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> currentList = new ArrayList<>();
        powerSetBacktrack(result, currentList, 0, arr);
        System.out.println(result);
    }

    public static int trap(int[] height) {
        int arraySize = height.length;
        int maxLeft=0, maxRight=0;
        int left=0, right=arraySize-1;
        int vol = 0;

        while(left < right) {
            if(height[left] < height[right]) {
                if(height[left] > maxLeft) {
                    maxLeft = height[left];
                }
                else {
                    vol += (maxLeft - height[left]);
                }
                left++;
            }
            else {
                if(height[right] > maxRight) {
                    maxRight = height[right];
                }
                else {
                    vol += (maxRight - height[right]);
                }
                right--;
            }
        }
        return vol;
    }


    public static int rob(int[] nums) {
        int length = nums.length;
        if(length == 0) return 0;

        int[] dp = new int[length];

        if(length >= 1){
            dp[0] = nums[0];
        }
        if(length >= 2) {
            dp[1] = Math.max(nums[0], nums[1]);
        }

        for(int i=2; i<length; i++) {
            dp[i] = Math.max(dp[i-1], nums[i] + dp[i-2]);
        }
        return dp[length-1];
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int length = gas.length;
        int result = -1;
        for(int start=0; start<length; start++) {
            int currentStation = start;
            int totalGas = gas[start];
            int count=0;
            while(totalGas >= cost[currentStation]) {
                totalGas -= cost[currentStation];
                currentStation = (currentStation +1) %length;
                totalGas += gas[currentStation];
                if(++count == length) {
                    return start;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] gas = {1,2,3,4,5};
//        int[] cost = {3,4,5,1,2};

        int[] gas = {2,3,4};
        int[] cost = {3,4,3};
        System.out.println(canCompleteCircuit(gas, cost));
    }
}
