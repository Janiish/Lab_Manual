// Step 1: Create a generic class Box<T>
class Box<T> {
    private T item;

    public void set(T item) {
        this.item = item;
    }

    public T get() {
        return item;
    }

    // Displays the runtime type of the stored item
    public void showType() {
        System.out.println("Type of stored item: " + item.getClass().getName());
    }
}

// Step 2: Create a generic class Pair<K, V> for key-value combinations
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void display() {
        System.out.println(key + " = " + value);
    }
}

public class GenericDemo {
    
    // Step 3: Create a generic method findMax() with a bounded type parameter
    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        T max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        
        // Step 4: Create Box objects for Integer and String
        Box<Integer> intBox = new Box<>();
        intBox.set(100);
        System.out.println("Integer Box Value: " + intBox.get());
        intBox.showType();

        Box<String> strBox = new Box<>();
        strBox.set("Hello Generics");
        System.out.println("String Box Value: " + strBox.get());
        strBox.showType();

        // Step 5: Create Pair objects with different type combinations
        System.out.println("\n---- Key-Value Pairs ----");
        Pair<String, Integer> pair1 = new Pair<>("Rahul", 88);
        pair1.display();

        Pair<Integer, String> pair2 = new Pair<>(101, "CSE");
        pair2.display();

        // Step 6: Call findMax() on Integer, String, and Double arrays
        Integer[] intArray = {45, 78, 89, 12};
        String[] strArray = {"Rahul", "Sneha", "Kiran"};
        Double[] doubleArray = {85.5, 75.0, 92.3};

        // Step 7: Display all results
        System.out.println("\nMaximum Number: " + findMax(intArray));
        System.out.println("Maximum (Alphabetical): " + findMax(strArray));
        System.out.println("Maximum Marks: " + findMax(doubleArray));
    }
}
