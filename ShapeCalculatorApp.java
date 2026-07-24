import java.util.ArrayList;
import java.util.List;

interface Shape {
    double calculateArea();
}

abstract class AbstractShape implements Shape {
    private final String name;
    protected String shapeCategory;
    String packageNote;

    AbstractShape(String name, String shapeCategory) {
        this.name = name;
        this.shapeCategory = shapeCategory;
        this.packageNote = "Shared package visibility";
    }

    public String getName() {
        return name;
    }

    public void printInfo() {
        System.out.printf("Shape: %-10s | Category: %-12s | Access Note: %s%n", 
                          name, shapeCategory, packageNote);
    }
}

class Circle extends AbstractShape {
    private final double radius;

    public Circle(double radius) {
        super("Circle", "2D Curved");
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    public double getRadius() {
        return radius;
    }
}

class Rectangle extends AbstractShape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        super("Rectangle", "2D Polygon");
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }
}

public class ShapeCalculatorApp {
    public static void main(String[] args) {
        System.out.println("=== 1. DEMONSTRATING INTERFACES & POLYMORPHISM ===");
        
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(5.0));
        shapes.add(new Rectangle(4.0, 6.0));
        shapes.add(new Circle(2.5));

        for (Shape shape : shapes) {
            System.out.printf("Calculated Area: %-8.2f%n", shape.calculateArea());
        }

        System.out.println("\n=== 2. DEMONSTRATING ACCESS SPECIFIERS ===");
        
        Circle testCircle = new Circle(3.0);

        System.out.println("Public getter access: " + testCircle.getName());
        System.out.println("Protected field access: " + testCircle.shapeCategory);
        System.out.println("Package-private field access: " + testCircle.packageNote);

        testCircle.printInfo();
    }
}

```
