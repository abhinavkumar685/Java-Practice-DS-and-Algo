import java.util.*;

class Student {
    String name;
    int roll;

    Student(String name, int roll) {
        this.name = name;
        this.roll = roll;
    }

    @Override
    public int hashCode() {
        return name.length() + roll;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof StudentClass) {
            StudentClass s = (StudentClass) obj;
            return this.name.equals(s.name);
        }
        return false;
    }

    public String toString() {
        return "{Name: "+ name + ", Roll: " + roll + "}";
    }
}


public class Test {
    public static List<StudentClass> filter(List<StudentClass> list) {
        Set<StudentClass> set = new HashSet<>();
        for(StudentClass s : list) {
            set.add(s);
        }

        return new ArrayList<StudentClass>(set);
    }

    public static void main(String[] args) {
        StudentClass s1 = new StudentClass("abhi", 1);
        StudentClass s2 = new StudentClass("abhi", 2);
        StudentClass s3 = new StudentClass("abhi", 3);
        StudentClass s4 = new StudentClass("abhi1", 4);
        StudentClass s5 = new StudentClass("abhi2", 5);

        List<StudentClass> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);

        System.out.println(filter(list));


    }
}