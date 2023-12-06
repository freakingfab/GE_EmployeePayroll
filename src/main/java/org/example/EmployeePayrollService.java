package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * @desc: class for service in employee payroll
 */
public class EmployeePayrollService {
    public enum IOService {CONSOLE_IO, FILE_IO, DB_IO, REST_IO}
    private List<EmployeePayrollData> employeePayrollList;

    /*
     * @desc: constructor for class
     * @params: none
     * @return: none
     */
    public EmployeePayrollService(){
        this.employeePayrollList = new ArrayList<>();
    }

    /*
     * @desc: parameterised constructor for class
     * @params: List<EmployeePayrollData>
     * @return: none
     */
    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList){
        this.employeePayrollList = employeePayrollList;
    }

    /*
     * @desc: function to read data from cnsole
     * @params: Scanner object
     * @return: void
     */
    public void readEmployeePayrollData(Scanner consoleInputReader){
        System.out.print("Enter the Employee id: ");
        int id = consoleInputReader.nextInt();
        System.out.print("Enter the Employee name: ");
        String name = consoleInputReader.next();
        System.out.print("Enter the Employee salary: ");
        Double salary = consoleInputReader.nextDouble();
        employeePayrollList.add(new EmployeePayrollData(id,name,salary));
    }

    /*
     * @desc: function to display data on console
     * @params: none
     * @return: void
     */
    public void writeEmployeePayrollData(){
        System.out.println("Writing employee data on console");
        employeePayrollList.stream().forEach(emp -> System.out.println(emp.toString()));
    }
}
