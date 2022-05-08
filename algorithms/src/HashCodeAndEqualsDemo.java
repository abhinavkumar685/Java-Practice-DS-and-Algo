/*
 * https://roytuts.com/hashset-in-java/
 * https://www.baeldung.com/java-objects-hash-vs-objects-hashcode
 * https://www.baeldung.com/java-hashcode
 * https://www.youtube.com/watch?v=ey7FQjfhfJI
 * https://stackoverflow.com/questions/53438370/comparing-objects-in-hashset
 */

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
        Student other = (Student) obj;
        return Objects.equals(name, other.name);
    }

    public String toString() {
        return "{Name: "+ name + ", Roll: " + roll + "}";
    }
}


public class HashCodeAndEqualsDemo {
    public static List<Student> filter(List<Student> list) {
        Set<Student> set = new HashSet<>();
        for(Student s : list) {
            set.add(s);
        }

        return new ArrayList<Student>(set);
    }

    public static void main(String[] args) {
        // Two students are same if name are equals irrespective of roll no
        Student s1 = new Student("abhi", 1);
        Student s2 = new Student("abhi", 2);
        Student s3 = new Student("abhi", 3);
        Student s4 = new Student("abhi1", 4);
        Student s5 = new Student("abhi2", 5);

        List<Student> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);

        System.out.println(filter(list));
    }
}
