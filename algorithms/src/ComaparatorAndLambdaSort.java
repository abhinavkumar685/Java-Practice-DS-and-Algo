import java.util.*;
/*
    Youtube Link(Detailed Concept): https://www.youtube.com/watch?v=QKnqs7C_pKQ
    Youtube Link(Comparable Interface Concept): https://www.youtube.com/watch?v=DE835GvxZMQ

    1. A class should implement Comparable Interface for sorting
    2. Lambda function either implement Comparator Class directly in Collections.sort() or
    3. Lambda function should directly return the int value for comparison

    Note: Only first argument will be judged based upon the return value by the function.
    For example:

    For Increasing Order:
        a=4, b=5 (Reality is a < b)
        public int compareTo {
            return a-b;
                OR
            if(a < b) return -1;
        }
        We can either write return value as a-b or -1 because both means returning negative value.
        If we return -1(negative value), it means internally it is implied that a<b that's why a-b gave negative value.
        Sort Function Implied that a < b.
        Reality is a < b.
        So sort function will try to push a (which seems to be smaller by sort function) towards left of array.
        ** Here reality and implied value is same**
        In reality also a<b, but smaller value i.e a is moved left, so array will be in increasing order.


        a=5, b=4 (reality a > b)
        public int compareTo {
            return a-b;
                OR
            if(a > b) return 1;
        }
        We can either write return value as a-b or +1 because both means returning positive value.
        If we return +1(positive value), it means internally it is implied that a>b that's why a-b gave positive value.
        Sort Function Implied that a > b.
        Reality is a > b.
        So sort function will try to push a (which seems to be greater by sort function) towards right of array.
        ** Here reality and implied value is same**
        In reality also a>b, but greater value i.e a is moved right, so array will be in increasing order.

    For Decreasing Order:
        a=4, b=5 (Reality is a < b)
        public int compareTo {
            return b-a;
                OR
            if(a < b) return 1;
        }
        We can either write return value as b-a or +1 because both means returning positive value.
        If we return +1(positive value), it means internally it is implied that a>b that's why a-b gave positive value.
        Sort Function Implied that a > b.
        Reality is a < b.
        So sort function will try to push a (which seems to be greater by sort function) towards right of array.
        ** Here reality and implied value is opposite**
        In reality a<b, but smaller value i.e a is moved right, so array will be in decreasing order.


        a=5, b=4 (reality a > b)
        public int compareTo {
            return b-a;
                OR
            if(a > b) return -1;
        }
        We can either write return value as b-a or -1 because both means returning negative value.
        If we return -1(negative value), it means internally it is implied that a<b that's why a-b gave negative value.
        Sort Function Implied that a < b.
        Reality is a > b.
        So sort function will try to push a (which seems to be smaller by sort function) towards left of array.
        ** Here reality and implied value is opposite**
        In reality a>b, but greater value i.e a is moved left, so array will be in decreasing order.
 */

class DVDInfo implements Comparable<DVDInfo> {
    String title;
    String genre;
    String leadActor;

    DVDInfo(String t, String g, String lead) {
        this.title = t;
        this.genre = g;
        this.leadActor = lead;
    }

    public String toString() {
        return "Title: " + title + ", Genre: " + genre + ", Lead Actor: " + leadActor;
    }

    public String getTitle() {
        return title;
    }

    public int compareTo(DVDInfo d) {
        return title.compareTo(d.getTitle());
    }
}

class GenreSort implements Comparator<DVDInfo> {
    /*
    1. Comparator is independent of class and can be used to compare any two class
        since compare method takes two class parameter as input.
    2. When we want to sort using more than one type, then we can use the Comparator instance.
    3. Using comparable, we can use only one type of sort in compareTO method.
     */
    public int compare(DVDInfo d1, DVDInfo d2) {
        return d1.getTitle().compareTo(d2.getTitle());
    }
}

class TestDVD {
    ArrayList<DVDInfo> list = new ArrayList<>();

    public void doComparison() {
        populateList();
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);

        GenreSort gs = new GenreSort();
        Collections.sort(list, gs);
        System.out.println(list);
    }

    private void populateList() {
        list.add(new DVDInfo("T1", "G1", "LA1"));
        list.add(new DVDInfo("T2", "G2", "LA2"));
        list.add(new DVDInfo("T3", "G3", "LA3"));
    }
}

class ComparePair implements Comparable<ComparePair>{
    int val1;
    int val2;

    ComparePair(int v1, int v2) {
        val1 = v1;
        val2 = v2;
    }

    public int compareTo(ComparePair p) {
        if(this.val1 < p.val1) {
            return -1;
        }
        else if(this.val1 == p.val1) {
            return this.val2 - p.val2;
        }
        else {
            return 1;
        }
    }

    public String toString() {
        return val1 + " " + val2;
    }
}

public class ComaparatorAndLambdaSort {
    public static void lambdaWithComparator(List<ComparePair> list) {
        // https://www.youtube.com/watch?v=QKnqs7C_pKQ
        /*
        Below is example of Anonymous Inner class using Comparator
         */
        Collections.sort(list, new Comparator<ComparePair>() {
            @Override
            // Increasing Order
            public int compare(ComparePair p1, ComparePair p2) {
                if(p1.val1 < p2.val1) {
                    return -1;
                }
                else if(p1.val1 == p2.val1) {
                    return p1.val2 - p2.val2;
                }
                else {
                    return 1;
                }
            }
        });
        System.out.println(list);
    }

    public static void lambdaWithoutComparator(List<ComparePair> list) {
        /*
        Here we are using Comparator as functional interface by passing lambda expression
         */
        Collections.sort(list, (ComparePair p1, ComparePair p2) -> {
            // return p1.val1 == p2.val1 ? p1.val2 - p2.val2 : p1.val1 - p2.val1;
            if(p1.val1 == p2.val1) {
                return p1.val2 - p2.val2;
            }
            else {
                return p1.val1 - p2.val1;
            }
        });

        // If single statement in Lambda then no return and no curly Braces
        Collections.sort(list, (ComparePair p1, ComparePair p2) ->
                p1.val1 == p2.val1 ? p1.val2 - p2.val2 : p1.val1 - p2.val1);
        System.out.println(list);
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (int[] arr1, int[] arr2) -> {
            return arr1[0] == arr2[0] ? arr1[1] - arr2[1] : arr1[0] - arr2[0];
        });

        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);
        for(int[] current : intervals) {
            int[] previous = result.get(result.size()-1);
            if(previous[1] >= current[0]) {
                previous[1] = Math.max(previous[1], current[1]);
            }
            else {
                result.add(current);
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        List<ComparePair> list = new ArrayList<>();
        ComparePair p1 = new ComparePair(0,2);
        ComparePair p2 = new ComparePair(0,1);
        ComparePair p3 = new ComparePair(1,3);
        ComparePair p4 = new ComparePair(1,2);
        ComparePair p5 = new ComparePair(4,5);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);

//        Collections.sort(list);
//        System.out.println(list);

//        lambdaWithComparator(list);

        lambdaWithoutComparator(list);
    }
}

interface Foo {
    // Functional Interface as only one abstract method
    void foof();
}

class Bar {
    void doStuff(Foo f){
        f.foof();
    }
}


class MyClass{
    void go1() {
        // Using Anonymous Inner Class
        Bar b = new Bar();
        b.doStuff(new Foo() {
            public void foof() {
                System.out.println("Printing foof, implemented using Anonymous class");
            }
        });
    }

    void go2() {
        // Using Lambda
        Bar b = new Bar();
        b.doStuff(() -> System.out.println("Printing foof, implemented using Functional Interface"));
    }

    public static void main(String[] args) {
        MyClass m = new MyClass();
        m.go1();
        m.go2();
    }
}