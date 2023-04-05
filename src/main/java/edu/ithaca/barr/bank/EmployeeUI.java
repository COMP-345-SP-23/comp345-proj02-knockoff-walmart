package edu.ithaca.barr.bank;

public class EmployeeUI extends GroceryStoreUI{

    public EmployeeUI(){
        Employee em = new Employee(1, "employee");
        GroceryStore.getEmployees().add(em);



    }
}
