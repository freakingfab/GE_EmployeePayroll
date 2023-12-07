package com.bridgelabz.empoloyeepayroll;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/*
    @desc: class for testing service for employee payroll
 */
public class EmployeePayrollServiceTest {
    /*
        @desc: testing writing object in file
     */
    @Test
    public void given3EmployeesWhenWrittenToFileShouldMatchEmployeeEntries(){
        EmployeePayrollData[] arrayOfEmps = {
                new EmployeePayrollData(1, "Jeff", 85.23),
                new EmployeePayrollData(2, "Mark", 56.23),
                new EmployeePayrollData(3, "Musk", 96.58)
        };
        EmployeePayrollService employeePayrollService = new EmployeePayrollService(Arrays.asList(arrayOfEmps));
        employeePayrollService.writeEmployeePayrollDataToFile();
        employeePayrollService.printData();
        long entries = employeePayrollService.countEntries();
        Assert.assertEquals(3,entries);
    }

    /*
        @desc: testing read from file
        @params: none
        @return: none
     */
    @Test
    public void givenFileOnReadingFromFileShouldMatchEmployeeCount(){
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayrollDataFromFile();
        long entries = employeePayrollService.employeePayrollList.size();
        Assert.assertEquals(3,entries);
    }
}
