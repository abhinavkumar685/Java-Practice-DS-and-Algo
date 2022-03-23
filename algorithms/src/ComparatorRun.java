import java.util.Arrays;
import java.util.Comparator;

class CompareIntArray implements Comparator<int []> {
    public int compare(int[] arr1, int[] arr2) {
        return arr1[0] - arr2[0];
    }
}

public class ComparatorRun {
    public static void main(String[] args) {
        int[][] tuples = {{9,3}, {2,6}, {20,10}, {15,18}};
        Arrays.sort(tuples, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2) {
                return arr1[0] - arr2[0];
            }
        });

        // Using Lambda
        Arrays.sort(tuples, (arr1, arr2) -> arr1[0] - arr2[0]);

        for (int[] tuple : tuples) {
            for (int j = 0; j < tuples[0].length; j++) {
                System.out.print(tuple[j] + " ");
            }
            System.out.println();
        }
    }

}