import java.util.Scanner;

public class CountCharacters {
    public static void main(String[] args) {
        
        // Step 2: Read a string input from the user using Scanner
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        
        // Step 3: Initialize four counters
        int vowels = 0;
        int consonants = 0;
        int digits = 0;
        int specialChars = 0;
        
        // Step 4: Convert the string to lowercase for easier checking
        String lowerCaseInput = input.toLowerCase();
        
        // Step 5: Traverse the string character by character
        for (int i = 0; i < lowerCaseInput.length(); i++) {
            char ch = lowerCaseInput.charAt(i);
            
            // a. If the character is a letter (a-z)
            if (ch >= 'a' && ch <= 'z') {
                // a. If it is one of a, e, i, o, u, increment vowels
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowels++;
                } else {
                    // b. Else, increment consonants
                    consonants++;
                }
            } 
            // b. Else if the character is a digit (0-9), increment digits
            else if (ch >= '0' && ch <= '9') {
                digits++;
            } 
            // c. Else if the character is not a whitespace, increment specialChars
            else if (ch != ' ' && ch != '\t' && ch != '\n') {
                specialChars++;
            }
        }
        
        // Step 6: Display the counts
        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
        System.out.println("Digits: " + digits);
        System.out.println("Special Characters: " + specialChars);
        
        scanner.close();
    }
}
