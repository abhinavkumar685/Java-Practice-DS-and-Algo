/*
 * https://roytuts.com/hashset-in-java/
 * https://www.baeldung.com/java-objects-hash-vs-objects-hashcode
 * https://www.baeldung.com/java-hashcode
 * https://www.youtube.com/watch?v=ey7FQjfhfJI
 * https://stackoverflow.com/questions/53438370/comparing-objects-in-hashset
 */

import java.util.*;

class StudentClass {
    String name;
    int roll;

    StudentClass(String name, int roll) {
        this.name = name;
        this.roll = roll;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StudentClass other = (StudentClass) obj;
        return Objects.equals(name, other.name);
    }

    public String toString() {
        return "{Name: "+ name + ", Roll: " + roll + "}";
    }
}


public class HashCodeAndEqualsDemo {
    public static List<StudentClass> filter(List<StudentClass> list) {
        Set<StudentClass> set = new HashSet<>();
        for(StudentClass s : list) {
            set.add(s);
        }

        return new ArrayList<StudentClass>(set);
    }

    public static void main(String[] args) {
        // Two students are same if name are equals irrespective of roll no
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
