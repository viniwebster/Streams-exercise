import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        List<Employee> list = new ArrayList<>();
        System.out.print("Enter salary: ");
        double n = sc.nextDouble();

        String pathFile = "src/in.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                String name = fields[0];
                String email = fields[1];
                double salary = Double.parseDouble(fields[2]);
                list.add(new Employee(name, email, salary));
                line = br.readLine();
            }
            List<String> emails = list.stream().filter(e -> e.getSalary() > n)
                    .map(Employee::getEmail)
                        .sorted(Comparator.comparing(String::toUpperCase)).toList();

            System.out.println("Email of people whose salary is more than " + n);
            for (String e : emails) {
                System.out.println(e);
            }

            double sum = list.stream().filter(e -> e.getName().charAt(0) == 'M').map(Employee::getSalary).reduce(0.0, Double::sum);
            System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}