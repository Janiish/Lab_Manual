// Step 2 (Procedure): Import the required package java.io.*
import java.io.*;
import java.util.Scanner;

public class StudentFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Step 1: Create a text file named students.txt
        String fileName = "students.txt";

        // --- WRITING TO FILE ---
        // Step 3 (Procedure): Create a FileWriter wrapped in a BufferedWriter
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            System.out.println("Enter Details of 3 Students");
            
            // Step 2 & 4: Accept details and write into the file
            for (int i = 1; i <= 3; i++) {
                System.out.println("Student " + i);
                System.out.print("Roll No: ");
                String rollNo = scanner.nextLine();
                System.out.print("Name: ");
                String name = scanner.nextLine();
                System.out.print("Marks: ");
                String marks = scanner.nextLine();

                // Writing to the file in a comma-separated format
                writer.write(rollNo + ", " + name + ", " + marks);
                writer.newLine(); // Move to the next line
            }
            // Step 5 (Procedure): Flush and close the writer (handled automatically by try-with-resources)
            System.out.println("Student records saved successfully.\n");
            
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

        // --- READING FROM FILE ---
        // Step 8 & 9 (Procedure): Open the same file using FileReader wrapped in BufferedReader
        System.out.println("Student Records");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Read records line by line using readLine()
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        // --- SEARCHING IN FILE ---
        // Step 10 (Procedure): Search for a particular student by entering a Roll Number
        System.out.print("\nEnter Roll Number to Search: ");
        String searchRollNo = scanner.nextLine();
        boolean isFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] studentData = line.split(", ");
                
                // Compare entered roll number with the records
                if (studentData.length == 3 && studentData[0].equals(searchRollNo)) {
                    // Step 11 (Procedure): Display the matching student's details
                    System.out.println("Student Found");
                    System.out.println("Roll No: " + studentData[0]);
                    System.out.println("Name: " + studentData[1]);
                    System.out.println("Marks: " + studentData[2]);
                    isFound = true;
                    break;
                }
            }
            
            if (!isFound) {
                System.out.println("Student not found.");
            }
            // Step 12 (Procedure): Close the BufferedReader (handled by try-with-resources)
            
        } catch (IOException e) {
            System.out.println("An error occurred while searching the file.");
            e.printStackTrace();
        }

        scanner.close();
    }
}
