package edu.ithaca.barr.bank;

public class Employee {
    
    private int id;
    private String name;

    public Employee(int idIn, String nameIn){
        id = idIn;
        name = nameIn;
    }


    /*
     * @return employee's id
     */
    public int getId(){
        return id;
    }

    /*
     * @return employee's name
     */
    public String getName(){
        return name;
    }
}
