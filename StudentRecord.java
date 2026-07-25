import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Step 1: Create a Student class with Roll Number, Name, and Percentage as data members.
class Student {
    int rollNo;
    String name;
    double percentage;

    // Constructor to initialize data members
    public Student(int rollNo, String name, double percentage) {
        this.rollNo = rollNo;
        this.name = name;
        this.percentage = percentage;
    }

    // Overridden toString() method to display the details in the ArrayList
    @Override
    public String toString() {
        return rollNo + "\t" + name + "\t" + percentage;
    }
}

public class StudentRecord {
    public static void main(String[] args) {
        
        // Step 2: Create an ArrayList to store student objects.
        ArrayList<Student> studentList = new ArrayList<>();
        
        // Step 3: Create a HashMap to store student records using Roll Number as the key.
        HashMap<Integer, Student> studentMap = new HashMap<>();

        // Step 4: Add several student records to both the ArrayList and HashMap.
        Student s1 = new Student(101, "Rahul", 88.5);
        Student s2 = new Student(102, "Sneha", 91.2);
        Student s3 = new Student(103, "Kiran", 84.8);

        // Adding to ArrayList
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);

        // Adding to HashMap
        studentMap.put(s1.rollNo, s1);
        studentMap.put(s2.rollNo, s2);
        studentMap.put(s3.rollNo, s3);

        // Step 5: Display all student records stored in the ArrayList.
        System.out.println("Student Records (ArrayList)");
        System.out.println("Roll\tName\tPercentage");
        for (Student s : studentList) {
            System.out.println(s);
        }

        // Step 6: Search for a student using the Roll Number from the HashMap.
        int searchRoll = 102;
        System.out.println("\nSearching for Roll No " + searchRoll);
        if (studentMap.containsKey(searchRoll)) {
            Student foundStudent = studentMap.get(searchRoll);
            System.out.println("Record Found");
            System.out.println("Roll No   : " + foundStudent.rollNo);
            System.out.println("Name      : " + foundStudent.name);
            System.out.println("Percentage: " + foundStudent.percentage);
        } else {
            System.out.println("Record Not Found");
        }

        // Step 7: Remove a student record from the ArrayList.
        // Removing the first student (Rahul) as requested by the sample output.
        System.out.println("\nAfter Removing First Student");
        studentList.remove(0); 

        // Step 8: Display the updated student records.
        System.out.println("Roll\tName\tPercentage");
        for (Student s : studentList) {
            System.out.println(s);
        }

        // Step 9: Display all entries stored in the HashMap.
        System.out.println("\nStudent Records (HashMap)");
        for (Map.Entry<Integer, Student> entry : studentMap.entrySet()) {
            Student s = entry.getValue();
            System.out.println(entry.getKey() + " -> " + s.name + " (" + s.percentage + "%)");
        }
    }
}
