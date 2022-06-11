import java.util.*;


public class Practice {
    public static String gcdOfStrings(String str1, String str2) {
        int n1 = str1.length();
        int n2 = str2.length();

        if(n1==0 || n2==0) return "";

        if(n2 > n1) {
            return gcdOfStrings(str2, str1);
        }


        //str1 is having str2
        int index = 0;
        while(index <= n1-n2) {
            int left = index;
            int right = index + n2;
            if(!str2.equals(str1.substring(left, right))) {
                return "";
            }

            index += n2;
        }

        String current = "";
        for(int i=0; i<n2; i++) {
            current = str2.substring(0, i+1);
            int size = current.length();

            int left = 0;
            int right = left + size;

            boolean flag = true;
            while(left <= n2-size) {
                if(!current.equals(str2.substring(left, right))) {
                    flag = false;
                    break;
                }
                left += size;
            }

            if(flag) {
                return current;
            }
        }
        return "";

    }
    public static void main(String[] args) {
        String string1 = "ABCABC";
        String string2 = "ABC";
        System.out.println(gcdOfStrings(string1, string2));


    }
}