package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeDAOJdbcTest {
    @Test
    public void testGetAll() {
        IEmployeeDAO employeeDAO = new EmployeeDAOJdbc();
        List<Employee> employeeList = employeeDAO.getAll();

        System.out.println(employeeList);
    }

    @Test
    void add() {
        IEmployeeDAO employeeDAO = new EmployeeDAOJdbc();
        Employee employee = new Employee(20, "Nguyen Thanh Nhan", 103.);

        assertTrue(employeeDAO.add(employee));

        //System.out.println(employeeDAO.getAll());
    }
    @Test
    void addAll(){
    List<Employee> employeeList = new ArrayList<>();
    employeeList.add(new Employee(30,"Le Viet Ha",105.));
    employeeList.add(new Employee(40,"Nguyen Cong Tuan Anh",106.));
    employeeList.addAll(employeeList);
    assertEquals(4 ,employeeList.size());
    }
}