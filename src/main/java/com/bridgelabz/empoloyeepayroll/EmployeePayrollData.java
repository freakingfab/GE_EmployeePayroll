package com.bridgelabz.empoloyeepayroll;

/*
 * @desc: class for data on employee payroll
 */
public class EmployeePayrollData {
    public int id;
    public String name;
    public double salary;

    /*
     * @desc: constructor for class
     * @params: id, name, salary
     * @return: none
     */
    public EmployeePayrollData(Integer id, String name, Double salary){
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    /*
     * @desc: override function for toString function
     * @params: none
     * @return: String
     */
    public String toString(){
        return "id: " + id + ", name: " + name + ", salary: " + salary;
    }

}
