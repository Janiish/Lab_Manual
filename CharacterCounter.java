import java.util.Scanner;

public class CharacterCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        int vowels = 0;
        int consonants = 0;
        int digits = 0;
        int specialChars = 0;
        int spaces = 0;

        for (char ch : input.toCharArray()) {
            if (Character.isLetter(ch)) {
                char lower = Character.toLowerCase(ch);
                if (lower == 'a' || lower == 'e' || lower == 'i' || lower == 'o' || lower == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            } else if (Character.isDigit(ch)) {
                digits++;
            } else if (Character.isWhitespace(ch)) {
                spaces++;
            } else {
                specialChars++;
            }
        }

        System.out.println("\n--- Character Count Summary ---");
        System.out.println("Vowels             : " + vowels);
        System.out.println("Consonants         : " + consonants);
        System.out.println("Digits             : " + digits);
        System.out.println("Special Characters : " + specialChars);
        System.out.println("Spaces             : " + spaces);

        scanner.close();
    }
}
