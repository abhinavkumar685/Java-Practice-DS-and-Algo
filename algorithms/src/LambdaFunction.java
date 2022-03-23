import java.util.*;
import java.util.stream.Stream;

// FunctionalInterface will have only 1 abstract method
@FunctionalInterface
interface Drawable {
    public void draw();
}

interface Drawable2 {
    public void draw(int width);
}

interface Drawable3 {
    public String draw(int width);
}

interface Addition {
    public int add(int a, int b);
}

@FunctionalInterface
interface Sayable {
    String say(String message);
}

class Product {
    int id;
    String name;
    float price;

    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

public class LambdaFunction {
    // https://www.javatpoint.com/java-lambda-expressions

    static void test1() {
        int x = 10;
        int y = 20;
        Drawable d1 = () -> {
            System.out.println(x);
        };
        d1.draw();

        Drawable2 d2 = (int width) -> {
            System.out.println(width);
        };
        d2.draw(x);

        Drawable3 d3 = (int width) -> {
            return "Drawing width: " + width;
        };
        System.out.println(d3.draw(x));

        Addition a1 = (int a, int b) -> {
            return (a + b);
        };
        System.out.println(a1.add(x,y));

        // Without Return
        Addition a2 = (int a, int b) -> (a+b);
        System.out.println(a1.add(x,y));

        Sayable person = (message)-> {
            String str1 = "I would like to say, ";
            String str2 = str1 + message;
            return str2;
        };
        System.out.println(person.say("time is precious."));
    }

    static void loop() {
        List<String> myList =new ArrayList<String>();
        myList.add("ankit");
        myList.add("mayank");
        myList.add("irfan");
        myList.add("jai");
        myList.forEach((n)->System.out.println(n));
    }

    static void threadCreation() {
        Runnable r1=new Runnable(){
            public void run(){
                System.out.println("Thread1 is running...");
            }
        };
        Thread t1=new Thread(r1);
        t1.start();

        //Thread Example with lambda
        Runnable r2=()->{
            System.out.println("Thread2 is running...");
        };
        Thread t2=new Thread(r2);
        t2.start();
    }

    static void lambdaComparator() {
        List<Product> myList = new ArrayList<>();
        myList.add(new Product(1,"HP Laptop",25000f));
        myList.add(new Product(3,"Keyboard",300f));
        myList.add(new Product(2,"Dell Mouse",150f));
        System.out.println("Sorting on the basis of name...");
        Collections.sort(myList, (p1, p2) -> {
            return p1.name.compareTo(p2.name);
        });

        for(Product p:myList){
            System.out.println(p.id+" "+p.name+" "+p.price);
        }

        // Without Lambda
        Collections.sort(myList, new Comparator<Product>() {
            public int compare(Product p1, Product p2) {
                return p1.name.compareTo(p2.name);
            }
        });
        for(Product p:myList){
            System.out.println(p.id+" "+p.name+" "+p.price);
        }

        Collections.sort(myList, (p1, p2) -> {
            return (int)(p1.price - p2.price);
        });

        for(Product p:myList){
            System.out.println(p.id+" "+p.name+" "+p.price);
        }
    }

    static void lambdaFilter() {
        List<Product> list = new ArrayList<>();
        list.add(new Product(1,"Samsung A5",17000f));
        list.add(new Product(3,"Iphone 6S",65000f));
        list.add(new Product(2,"Sony Xperia",25000f));
        list.add(new Product(4,"Nokia Lumia",15000f));
        list.add(new Product(5,"Redmi4 ",26000f));
        list.add(new Product(6,"Lenevo Vibe",19000f));

        // using lambda to filter data
        Stream<Product> filtered_data = list.stream().filter(p -> p.price > 20000);

        // using lambda to iterate through collection
        filtered_data.forEach(
                product -> System.out.println(product.name+": "+product.price)
        );
    }

    static void priorityQueueImplementation() {
        int[] input = {8, 5, 3, 7, 1, 10, 9};
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        for(int x: input) {
            pq1.add(x);
        }
        while(!pq1.isEmpty()) {
            System.out.println(pq1.remove());
        }
        // Using offer and poll
        for(int x: input) {
            pq1.offer(x);
        }
        while(!pq1.isEmpty()) {
            System.out.println(pq1.poll());
        }
        // Changing into reverse
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer i, Integer j) {
                return j-i;
            }
        });
        for(int x: input) {
            pq2.offer(x);
        }
        while(!pq2.isEmpty()) {
            System.out.println(pq2.poll());
        }
    }

    static void priorityQueue() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

    }

    public static void main(String[] args) {
        lambdaComparator();
    }
}
