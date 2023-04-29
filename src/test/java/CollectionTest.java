import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectionTest {

    private Map<String, Double> employeeSalaries = new HashMap<>();

    @BeforeEach
    public void init() {
        employeeSalaries.put("Alice", 50000.0);
        employeeSalaries.put("Bob", 60000.0);
        employeeSalaries.put("Charlie", 70000.0);
        employeeSalaries.put("David", 80000.0);
    }

    @Test
        //Use the computeIfAbsent() method to compute the salary of an employee who is not in the map.
    void add_salary_to_not_present_employe() {
        employeeSalaries.computeIfAbsent("Emily", k -> 90000.0);
        System.out.println("Emily's salary: " + employeeSalaries.get("Emily"));
    }

    @Test
        //Use the computeIfPresent() method to give a 10% raise to all employees who are already in the map
    void raise_10_pc_salary_to_present_employe() {
        List<Double> salaryRaised = employeeSalaries.values().stream().map(salary -> salary *= 1.1).toList();
        employeeSalaries.entrySet().stream().peek(entry -> System.out.println(entry.getKey() + "'s salary: " + entry.getValue()));
    }
}
