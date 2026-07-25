import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

// Step 1: Create an Employee class with id, name, department, and salary.
class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return id + "\t" + name + "\t" + department + "\t" + salary;
    }
}

public class EmployeeAnalytics {
    public static void main(String[] args) {

        // Step 2: Store employee objects in a List.
        List<Employee> employees = Arrays.asList(
            new Employee(101, "Rahul", "CSE", 55000.0),
            new Employee(102, "Sneha", "ECE", 62000.0),
            new Employee(103, "Kiran", "CSE", 48000.0),
            new Employee(104, "Divya", "MECH", 51000.0),
            new Employee(105, "Arjun", "ECE", 70000.0)
        );

        // Display all employees
        System.out.println("---- All Employees ----");
        employees.forEach(e -> System.out.println(e));

        // Step 3: Use filter() and sorted() with lambda expressions to list high-salary employees.
        System.out.println("\n---- Salary Above 50000 (High to Low) ----");
        employees.stream()
                 .filter(e -> e.getSalary() > 50000)
                 .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
                 .forEach(e -> System.out.println(e.getName() + " -> " + e.getSalary()));

        // Step 4: Use map() with a method reference to extract employee names.
        System.out.println("\n---- Employee Names ----");
        List<String> employeeNames = employees.stream()
                                              .map(Employee::getName)
                                              .collect(Collectors.toList());
        System.out.println(employeeNames);

        // Step 5: Use Collectors.groupingBy() to group employees by department.
        System.out.println("\n---- Employees Grouped by Department ----");
        Map<String, List<String>> employeesByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment, 
                        Collectors.mapping(Employee::getName, Collectors.toList())
                ));
        employeesByDept.forEach((dept, names) -> System.out.println(dept + ": " + names));

        // Step 6: Use Collectors.averagingDouble() to find the average salary per department.
        System.out.println("\n---- Average Salary per Department ----");
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment, 
                        Collectors.averagingDouble(Employee::getSalary)
                ));
        avgSalaryByDept.forEach((dept, avgSal) -> 
            System.out.println(dept + ": " + String.format("%.2f", avgSal))
        );

        // Step 7: Use reduce() to compute the total salary.
        double totalSalary = employees.stream()
                                      .map(Employee::getSalary)
                                      .reduce(0.0, Double::sum);
        System.out.println("\nTotal Salary Paid: " + String.format("%.2f", totalSalary));

        // Step 8: Use count(), max(), and Optional to find the CSE count and highest-paid employee.
        long cseCount = employees.stream()
                                 .filter(e -> e.getDepartment().equalsIgnoreCase("CSE"))
                                 .count();
        System.out.println("Number of CSE Employees : " + cseCount);

        Optional<Employee> highestPaid = employees.stream()
                                                  .max(Comparator.comparingDouble(Employee::getSalary));
        
        // Step 9: Display the highest-paid employee result safely from the Optional
        highestPaid.ifPresent(e -> 
            System.out.println("Highest Paid: " + e.getName() + " (" + e.getSalary() + ")")
        );
    }
}
