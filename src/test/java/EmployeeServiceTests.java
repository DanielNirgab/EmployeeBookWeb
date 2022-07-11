
import com.emp.employeebookweb.exception.EmplFullNameIsIncorrect;
import com.emp.employeebookweb.exception.EmployeeAlreadyAddedException;
import com.emp.employeebookweb.exception.EmployeeNotFoundException;
import com.emp.employeebookweb.exception.EmployeeStorageIsFullException;
import com.emp.employeebookweb.model.Employee;
import com.emp.employeebookweb.service.EmployeeServiceImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTests {

    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @ParameterizedTest
    @MethodSource("objectsForAdd")
    public void addTest(String firstName, String secondName, int department, double salary) {
        Employee expected = new Employee(firstName, secondName, department, salary);
        assertEquals(expected, employeeService.addEmployee(firstName, secondName, department, salary));
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.addEmployee(firstName, secondName,
                department, salary));
    }
    @ParameterizedTest
    @MethodSource("incorrectObjectsForAdd")
    public void addIncorrectTest(String firstName, String secondName, int department, double salary) {
        assertThrows(EmplFullNameIsIncorrect.class, () -> employeeService.addEmployee(firstName, secondName,
                department, salary));
    }
    @ParameterizedTest
    @MethodSource("objectsForAdd")
    public void addTestSizeOfStorage(String firstName, String secondName, int department, double salary) {
        List<Employee> employeeList = generateEmployees(10);
        employeeList.forEach(employee -> assertEquals(employee, employeeService.addEmployee(employee.getFirstName(),
                employee.getSecondName(), employee.getDepartment(), employee.getSalary())));

        assertThrows(EmployeeStorageIsFullException.class, () -> employeeService.addEmployee(firstName, secondName, department, salary));
    }

    private List<Employee> generateEmployees(int size) {
        return Stream.iterate(1, i -> i + 1)
                .limit(size)
                .map(i -> new Employee(("Name" + (char)((int)'a' + i)), ("Secondname" + (char)((int)'a'+i)), i, 1000 + i))
                .collect(Collectors.toList());
    }

    @ParameterizedTest
    @MethodSource("objectsForAdd")
    public void removeTest(String firstName, String secondName, int department, double salary) {
        Employee expected = new Employee(firstName, secondName, department, salary);
        assertEquals(expected, employeeService.addEmployee(firstName, secondName, department, salary));
        assertEquals(expected, employeeService.removeEmployee(firstName, secondName, department, salary));
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.removeEmployee(firstName, secondName, department, salary));
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.removeEmployee(firstName, secondName, department, salary));
        assertEquals(new ArrayList<>(), employeeService.getEmployees());

    }
    @ParameterizedTest
    @MethodSource("objectsForAdd")
    public void findTest(String firstName, String secondName, int department, double salary) {
        Employee expected = new Employee(firstName, secondName, department, salary);
        assertEquals(expected, employeeService.addEmployee(firstName, secondName, department, salary));
        assertEquals(expected, employeeService.findEmployee(firstName, secondName, department, salary));
        assertEquals(expected, employeeService.removeEmployee(firstName, secondName, department, salary));
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee(firstName, secondName, department, salary));
    }

    public static Stream<Arguments> objectsForAdd() {
        return Stream.of(
                Arguments.of("Ivan", "Seledkin", 1, 2500),
                Arguments.of("Fedot", "Petrov", 1, 1500),
                Arguments.of("Petr", "Akula", 2, 1700),
                Arguments.of("Enot", "Barsuk", 2, 1300)
                );
    }

    public static Stream<Arguments> incorrectObjectsForAdd() {
        return Stream.of(
                Arguments.of("I$van", "Seledkin", 1, 2500),
                Arguments.of("Fed123ot", "Petrov", 1, 1500),
                Arguments.of("Pet_=r", "Akula", 2, 1700),
                Arguments.of("Enot@", "Barsuk", 2, 1300)
                );
    }

}
