package org.example;

import com.mysql.cj.protocol.a.LocalDateTimeValueEncoder;
import lombok.SneakyThrows;
import org.example.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOJdbc implements IEmployeeDAO {
    public final static String URL = "jdbc:mysql://localhost:3306/jdbc_demo";
    public final static String USER_NAME = "root";
    public final static String PASSWORD = "123456";

    private final static Logger logger = LoggerFactory.getLogger(EmployeeDAOJdbc.class);

    @Override
    public List<Employee> getAll() {
        String query = "SELECT * FROM Employee";

        List<Employee> employees = new ArrayList<>();
        try (Connection con =
                     DriverManager.getConnection(URL, USER_NAME, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                long empID = rs.getLong("ID");
                String name = rs.getString("NAME");
                double salary = rs.getDouble("SALARY");
                Employee employee = new Employee(empID, name, salary);
                //employee.setCreateDate(DateUtil.convertToLocalDateTime(date));
                employee.setCreateDate(DateUtil.getLocalDateTime(rs.getString("CREATED_DATE")));
                employees.add(employee);

            } // end of while
        } catch (SQLException e) {
            // Log error
            // System.out.println("SQL Exception: " + e);
            logger.error(e.toString());
        } // end of try-with-resources
        return employees;
    }

    @Override
    public boolean add(Employee employee) {

        try (Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {


            PreparedStatement stmt = con.prepareStatement("INSERT INTO Employee VALUES (?, ?, ?, ?)");
            stmt.setLong(1, employee.getId());
            stmt.setString(2, employee.getName());
            stmt.setDouble(3, employee.getSalary());
            stmt.setString(4, DateUtil.getString(employee.getCreateDate()));
            if (stmt.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            //Log error
            logger.error(e.toString());
        }
        // end of try-with-resources
        return false;
    }

    @Override
    public boolean addAll(List<Employee> employeeList) {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Employee VALUES (?, ?, ?, ?)");
            con.setAutoCommit(false);
            for (Employee e : employeeList) {
                stmt.setLong(1, e.getId());
                stmt.setString(2, e.getName());
                stmt.setDouble(3, e.getSalary());
                stmt.setString(4, DateUtil.getString(e.getCreateDate()));
                stmt.executeUpdate();
            }
            con.commit();
            } catch(SQLException e){
                //Log error
                logger.error(e.toString());
                try {
                    con.rollback();
                    return false;
                } catch (SQLException ex){
                    logger.error((ex.toString()));
                }
            }
        finally {
            try {
                    con.close();
            } catch (SQLException ex){
                logger.error(ex.toString());
            }
        }
            // end of try-with-resources
            return true;

        }

    }

