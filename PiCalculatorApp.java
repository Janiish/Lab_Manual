abstract class BasePiCalculator {
    private double piValue;
    protected int iterations;

    public BasePiCalculator(int iterations) {
        this.iterations = iterations;
        this.piValue = 0.0;
    }

    protected abstract double calculate();

    protected void setPiValue(double piValue) {
        this.piValue = piValue;
    }

    public void compute() {
        double result = calculate();
        setPiValue(result);
    }

    public double getPiValue() {
        return piValue;
    }
}

class NilakanthaPiCalculator extends BasePiCalculator {
    public NilakanthaPiCalculator(int iterations) {
        super(iterations);
    }

    @Override
    protected double calculate() {
        double pi = 3.0;
        double sign = 1.0;

        for (int i = 1; i <= iterations; i++) {
            double n = i * 2.0;
            pi += sign * (4.0 / (n * (n + 1.0) * (n + 2.0)));
            sign = -sign;
        }

        return pi;
    }
}

public class PiCalculatorApp {
    public static void main(String[] args) {
        BasePiCalculator piCalc = new NilakanthaPiCalculator(1000);

        piCalc.compute();

        System.out.printf("Computed Pi Value : %.15f%n", piCalc.getPiValue());
        System.out.printf("Java Math.PI      : %.15f%n", Math.PI);
        System.out.printf("Difference        : %.15f%n", Math.abs(Math.PI - piCalc.getPiValue()));
    }
}
