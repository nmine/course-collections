import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectionMapTest {

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
        employeeSalaries.forEach((key, value)
                -> employeeSalaries.computeIfPresent(key, (n, salary) -> salary * 1.1));
        employeeSalaries.forEach((key, value)
                -> System.out.println(key + "'s salary: " + value));

        //with list (only values)
        //List<Double> salaryRaised = employeeSalaries.values().stream().map(salary -> salary *= 1.1).toList();
        //employeeSalaries.entrySet().stream().peek(entry -> System.out.println(entry.getKey() + "'s salary: " + entry.getValue()));
    }

    @Test
        //Use the computeIfPresent() method to give a 20% raise to employees whose names start with the letter "B".
    void raise_20_pc_salary_to_employe_with_name_starting_with_B() {
        //List<String> result = employeeSalaries.keySet().stream().filter(name -> name.startsWith("B")).toList();
        //result.forEach(n -> employeeSalaries.computeIfPresent(n, (name, salary) -> salary * 1.1));

        employeeSalaries.keySet().stream().forEach(s -> {
            if(s.startsWith("B"))
                employeeSalaries.computeIfPresent(s, (s1, aDouble) -> aDouble*1.1);
        });
        employeeSalaries.forEach((key, value)
                -> System.out.println(key + "'s salary: " + value));

        //with list (only values)
        //List<Double> salaryRaised = employeeSalaries.values().stream().map(salary -> salary *= 1.1).toList();
        //employeeSalaries.entrySet().stream().peek(entry -> System.out.println(entry.getKey() + "'s salary: " + entry.getValue()));
    }

    //Add an employee with a salary of 0 if they are not in the map.
    @Test
    void Add_an_employee_with_a_salary_of_0_if_not_in_the_map() {
        employeeSalaries.computeIfAbsent("John", s -> (double) 0);
        employeeSalaries.forEach((key, value)
                -> System.out.println(key + "'s salary: " + value));
    }

    // Group employees based on their salary ranges
    @Test
    void grouping_by() {
        Map<String, List<Map.Entry<String, Double>>> collect = employeeSalaries.entrySet().stream()
                .collect(Collectors.groupingBy(entry -> {
                    double salary = entry.getValue();
                    if (salary < 60000.0) {
                        return "Low";
                    } else if (salary < 90000.0) {
                        return "Medium";
                    } else {
                        return "High";
                    }
                }));
        employeeSalaries.forEach((key, value)
                -> System.out.println(key + "'s salary: " + value));
    }

    // Group employees based on their salary ranges
    @Test
    void mappin() {
        employeeSalaries.entrySet().stream().collect(Collectors.mapping(Map.Entry::getKey, Collectors.toList()));
        employeeSalaries.forEach((key, value)
                -> System.out.println(key + "'s salary: " + value));
    }

    @Test
    void partition_by() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Map<Boolean, List<Integer>> evenOddMap = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println(evenOddMap);
    }

    @Test
    void group_by_list() {
        List<String> words = Arrays.asList("hello", "world", "java", "programming", "language");

        Map<Integer, List<String>> lengthMap = words.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println(lengthMap);
    }
    @Test
    void mapping() {
        List<String> words = Arrays.asList("hello", "world", "java", "programming", "language");

        Map<Integer, List<Character>> characterMap = words.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.mapping(s -> s.charAt(0), Collectors.toList())));

        System.out.println(characterMap);
    }

}
