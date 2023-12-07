package com.bridgelabz.empoloyeepayroll;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Employee Payroll System");
        Scanner consoleInputReader = new Scanner(System.in);
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayrollDataFromFile();
        employeePayrollService.printEmployeePayroll();
        employeePayrollService.countEntries();
    }
}