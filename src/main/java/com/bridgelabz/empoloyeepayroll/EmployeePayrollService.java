package com.bridgelabz.empoloyeepayroll;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * @desc: class for service in employee payroll
 */
public class EmployeePayrollService {
    public enum IOService {CONSOLE_IO, FILE_IO, DB_IO, REST_IO}
    public List<EmployeePayrollData> employeePayrollList;

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
    public void readEmployeePayrollDataFromConsole(Scanner consoleInputReader){
        System.out.print("Enter the Employee id: ");
        int id = consoleInputReader.nextInt();
        System.out.print("Enter the Employee name: ");
        String name = consoleInputReader.next();
        System.out.print("Enter the Employee salary: ");
        Double salary = consoleInputReader.nextDouble();
        employeePayrollList.add(new EmployeePayrollData(id,name,salary));
    }

    /*
     * @desc: function to read data from file
     * @params: Scanner object
     * @return: void
     */
    public void readEmployeePayrollDataFromFile() {
        try {
            Files.lines(Paths.get("data/employee_payroll.txt"))
                    .map(line -> line.trim().split(","))
                    .map(data -> new EmployeePayrollData(Integer.parseInt(data[0].trim()), data[1].trim(), Double.parseDouble(data[2].trim())))
                    .forEach(employeePayrollList::add);
            System.out.println("Reading employee data from file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * @desc: function to write employee payroll data to a file in the "data" folder
     * @params: none
     * @return: void
     */
    public void writeEmployeePayrollDataToFile() {
        String folderPath = "data/";
        String filePath = folderPath + "employee_payroll.txt";

        // Create the "data" folder if it doesn't exist
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("data/employee_payroll.txt", true))) {
            employeePayrollList.forEach(emp -> writer.println(emp.toString()));
            System.out.println("Written objects on the file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * @desc: function to count the number of entries in the file
     * @params: none
     * @return: long
     */
    public long countEntries() {
        long entryCount = 0;
        try {
            List<String> payrollLines = FileUtils.readLines(FileUtils.getFile("data/employee_payroll.txt"), "UTF-8");
            entryCount = payrollLines.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entryCount;
    }

    /*
        @desc: function to read and print data
        @params: none
        @return: none
     */
    public void printData(){
        System.out.println("Printing employee payrolls from file:");
        try {
            List<String> payrollLines = FileUtils.readLines(FileUtils.getFile("data/employee_payroll.txt"), "UTF-8");
            payrollLines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        @desc: function to print data
        @params: none
        @return: none
     */
    public void printEmployeePayroll(){
        System.out.println("Printing employee payrolls from list:");
        employeePayrollList.forEach(emp -> System.out.println(emp.toString()));
    }
}
