import com.emp.employeebookweb.model.Employee;
import com.emp.employeebookweb.service.DepartmentServiceImpl;
import com.emp.employeebookweb.service.EmployeeService;
import com.emp.employeebookweb.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTests {

    @Mock
    private EmployeeServiceImpl employeeService;
    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    public void beforeEach() {
        List<Employee> employees = List.of(
                new Employee("Ivan", "Seledkin", 1, 2500),
                new Employee("Fedot", "Petrov", 1, 1500),
                new Employee("Petr", "Akula", 2, 1700),
                new Employee("Enot", "Barsuk", 2, 1300)
        );
        when(employeeService.getEmployees()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("employeeListWithMinSalaryParam")
    public void employeeWithMinSalary(int dep, Employee expected) {
        assertEquals(expected, departmentService.getMinSalaryByDepartment(dep));
    }

    @ParameterizedTest
    @MethodSource("employeeListWithMaxSalaryParam")
    public void employeeWithMaxSalary(int dep, Employee expected) {
        assertEquals(expected, departmentService.getMaxSalaryByDepartment(dep));
    }

    public static Stream<Arguments> employeeListWithMaxSalaryParam() {
        return Stream.of(
                Arguments.of(1, new Employee("Ivan", "Seledkin", 1, 2500)),
                Arguments.of(2, new Employee("Petr", "Akula", 2, 1700))
        );
    }
    public static Stream<Arguments> employeeListWithMinSalaryParam() {
        return Stream.of(
                Arguments.of(1, new Employee("Fedot", "Petrov", 1, 1500)),
                Arguments.of(2, new Employee("Enot", "Barsuk", 2, 1300))
        );
    }


}
