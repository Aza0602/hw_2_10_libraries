import com.github.aza0602.hw_2_10_libraries.exception.EmployeeAlreadyAddedException;
import com.github.aza0602.hw_2_10_libraries.exception.EmployeeNotFoundException;
import com.github.aza0602.hw_2_10_libraries.exception.EmployeeStoragesFullException;
import com.github.aza0602.hw_2_10_libraries.model.Employee;
import com.github.aza0602.hw_2_10_libraries.service.ValidatorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private static final int LIMIT = 10;

    private final List<Employee> employees = new ArrayList<>();

    private final ValidatorService validatorService;

    public EmployeeService(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    public Employee add(String name,
                        String surname,
                        int department,
                        int salary) {
        Employee employee = new Employee(
                validatorService.validateName(name),
                validatorService.validateSurname(surname),
                department,
                salary
        );
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStoragesFullException();
    }

    public Employee remove(String name,
                           String surname) {
        Employee employee = employees.stream()
                .filter(emp -> emp.getName().equals(name) && emp.getSurname.equals(surname))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
        employees.remove(employee);
        return employee;
    }

    public Employee find(String name,
                         String surname) {
        return employees.stream()
                .filter(emp -> emp.getName().equals(name) && emp.getSurname.equals(surname))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }
}
