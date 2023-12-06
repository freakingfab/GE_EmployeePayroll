package com.bridgelabz.empoloyeepayroll;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
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

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            employeePayrollList.forEach(emp -> {
                String employeeData = emp.toString() + "\n";
                try {
                    fileWriter.write(employeeData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("Employee payroll data written to file successfully.");
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
        long entries = 0;
        String filePath = "data/employee_payroll.txt";

        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                entries++;
                fileScanner.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entries;
    }

    /*
        @desc: function to read and print data
        @params: none
        @return: none
     */
    public void printData(){
        try {
            Files.lines(new File("data/employee_payroll.txt").toPath())
                    .forEach(System.out::println);
        }
        catch(IOException e){
            // handle exception
        }
    }
}
