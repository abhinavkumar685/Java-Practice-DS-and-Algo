import java.util.*;
import java.util.LinkedList;

public class ListFlattener {
    private static List<Object> lst(Object... objs) {
        return Arrays.asList(objs);
    }

    public static List<Integer> flatten(List<?> list) {
        List<Integer> ret = new ArrayList<>();
        flattenHelper(list, ret);
        return ret;
    }

    public static void flattenHelper(List<?> nestedList, List<Integer> flatList) {
        for (Object item : nestedList) {
            if (item instanceof List<?>) {
                flattenHelper((List<?>) item, flatList);
            } else {
                flatList.add((Integer) item);
            }
        }
    }

    public static List<Integer> flattenUsingQueue(List<?> nestedList) {
        List<Integer> flatList = new ArrayList<>();
        Queue<Object> queue = new LinkedList<>(nestedList);
        while(!queue.isEmpty()) {
            Object item = queue.remove();
            if(item instanceof List<?>) {
                // Using addAll to add all elements of a list by extracting it.
                queue.addAll((List<?>)item);
            }
            else {
                flatList.add((Integer)item);
            }
        }
        return flatList;
    }

    public static void main(String[] args) {
        // https://gist.github.com/seanzhu/11167427
        List<Object> nestedList = lst(1, lst(2, lst(3, 4)),
                lst(5, 6, 7), 8, lst(lst(9, 10)));
        List<Integer> flatList = flatten(nestedList);
        System.out.println(nestedList);
        System.out.println(flatList);
    }
}
