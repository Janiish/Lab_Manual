import java.io.*;
import java.util.*;

public class StudentMarksManager {
    private static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Student Marks System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Marks");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    updateMarks(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter Roll Number: ");
        String roll = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Marks: ");
        String marks = scanner.nextLine();

        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            out.println(roll + "," + name + "," + marks);
            System.out.println("Student added successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    private static void viewStudents() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No records found.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.printf("%-10s %-20s %-10s%n", "Roll No", "Name", "Marks");
            System.out.println("---------------------------------------------");
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    System.out.printf("%-10s %-20s %-10s%n", data[0], data[1], data[2]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading data.");
        }
    }

    private static void updateMarks(Scanner scanner) {
        System.out.print("Enter Roll Number to update: ");
        String targetRoll = scanner.nextLine();
        
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No records found.");
            return;
        }

        List<String> records = new ArrayList<>();
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3 && data[0].equals(targetRoll)) {
                    System.out.print("Enter new marks for " + data[1] + ": ");
                    String newMarks = scanner.nextLine();
                    records.add(data[0] + "," + data[1] + "," + newMarks);
                    found = true;
                } else {
                    records.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading data.");
            return;
        }

        if (found) {
            try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME, false))) {
                for (String record : records) {
                    out.println(record);
                }
                System.out.println("Marks updated successfully.");
            } catch (IOException e) {
                System.out.println("Error updating data.");
            }
        } else {
            System.out.println("Roll Number not found.");
        }
    }
}
