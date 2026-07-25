import java.util.Scanner;

public class PiCalculator {
    
    // Step 2: Declare private variables (accessible only within this class)
    private int terms;
    private double computedPi;
    
    // Step 3: Declare a public final variable (constant)
    public final double PI = Math.PI;

    // Step 6: Constructor to initialize the private variables
    public PiCalculator(int terms) {
        this.terms = terms;
        System.out.println("Calculating Pi using Leibniz Series...");
        this.computedPi = calculateLeibniz(terms);
    }

    // Private method to compute Pi using the Leibniz Series
    private double calculateLeibniz(int n) {
        double pi = 0.0;
        for (int i = 0; i < n; i++) {
            double term = 4.0 / (2 * i + 1);
            if (i % 2 != 0) {
                term = -term; // Alternate subtraction and addition
            }
            pi += term;
        }
        return pi;
    }

    // Step 5: Public method accessible from anywhere
    public void getPiValue() {
        System.out.println("Public Method - Displaying Result:");
        System.out.println("Approximated value of Pi: " + computedPi);
    }

    // Step 4: Protected method accessible within the same package and subclasses
    protected void displayPrecisionInfo() {
        System.out.println("Protected Method - Displaying Precision Info:");
        System.out.println("Precision used: " + terms + " terms");
        System.out.println("Series used: Leibniz Series (4/1-4/3+4/5-4/7+4/9...)");
    }

    // Protected method to calculate area
    protected double calculateArea(double radius) {
        return computedPi * radius * radius;
    }

    // Method to demonstrate accessing private data from inside the class
    public void displayPrivateData() {
        System.out.println("Private Data - Accessed only within class:");
        System.out.println("Raw computed value (private): " + computedPi);
    }

    public static void main(String[] args) {
        System.out.println("Pi Calculator using Access Specifiers");
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of terms for Pi approximation: ");
        int terms = scanner.nextInt();

        // Step 7a: Create an object of PiCalculator
        PiCalculator calc = new PiCalculator(terms);

        // Step 7b: Access the public method to display the value of Pi
        calc.getPiValue();

        // Step 7d: Call the protected method to display precision info
        calc.displayPrecisionInfo();

        // Calling method to show private data
        calc.displayPrivateData();

        scanner.close();
    }
}
