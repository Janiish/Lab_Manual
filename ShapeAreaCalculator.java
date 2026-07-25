import geometry.Circle;
import geometry.Rectangle;
import geometry.Triangle;

public class ShapeAreaCalculator {
    public static void main(String[] args) {
        System.out.println("Shape Area Calculator"); 
        Circle circle = new Circle(5.0);
        Rectangle rectangle = new Rectangle(4.0, 6.0);
        Triangle triangle = new Triangle(3.0, 8.0);    
        System.out.println("Circle:");
        System.out.println("Radius = " + circle.getRadius());
        System.out.println("Area of Circle = " + circle.calculateArea());      
        System.out.println("Rectangle:");
        System.out.println("Length = " + rectangle.getLength() + ", Width = " + rectangle.getWidth());
        System.out.println("Area of Rectangle = " + rectangle.calculateArea());     
        System.out.println("Triangle:");
        System.out.println("Base = " + triangle.getBase() + ", Height = " + triangle.getHeight());
        System.out.println("Area of Triangle = " + triangle.calculateArea());
    }
}
