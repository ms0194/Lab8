package toystopinventorymanagementsystem;


import java.sql.*;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.*;
import java.util.Scanner;


public class ToyStopInventoryManagementSystem implements Serializable {


    ToyStopService tsService = new ToyStopService();

    public void init() {

        tsService.initEmployees();
        tsService.initStores();
        tsService.initToys();
        System.out.println("Init complete");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        ToyStopInventoryManagementSystem objj = new ToyStopInventoryManagementSystem();


        objj.init();
        ToyStopService object = new ToyStopService();

        //Des();


        objj.showMenu(objj);


    }


    private void loadData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }




    private void showMenu(ToyStopInventoryManagementSystem obj) {

        while (true) {
            System.out.println("Welcome to Toy Stop Inventory Management System");
            System.out.println("Enter 1 to show all data");
            System.out.println("Enter 2 to add a new Store");
            System.out.println("Enter 3 to add a new Employee");
            System.out.println("Enter 4 to add a new Toy");


            Scanner input = new Scanner(System.in);

            int in = input.nextInt();


            if (in == 1) {

                obj.printAll();
            } else if (in == 2) {

                obj.addStore();
                System.out.println("A new store has been added");


            } else if (in == 3) {


                obj.addEmployee();
                System.out.println("A new Employee has been added");


            } else if (in == 4) {


                int id = obj.addToy();


                System.out.println("A new toy has been added");

            }


        }

    }

    private void printAll() {
        System.out.println(this.tsService.stores);
    }

    private void addStore() {

        System.out.println(this.tsService.addStore());
    }

    private void addEmployee() {

        System.out.println(this.tsService.addEmployee());
    }

    private int addToy() {

        System.out.println(this.tsService.addToys());
        return 0;
    }

    private void SaveState(ToyStopInventoryManagementSystem tsims) {


    }



    public static void Serialize(ToyStopInventoryManagementSystem objj) {


        try {

            FileOutputStream fout = new FileOutputStream("/Users/Mariya/file.ser");
            ObjectOutputStream out = new ObjectOutputStream(fout);

            out.writeObject(objj);

            out.flush();



        } catch (IOException i) {
            i.printStackTrace();
        }

    }


    public static ToyStopInventoryManagementSystem Deserial() {

        ToyStopInventoryManagementSystem obj1 = new ToyStopInventoryManagementSystem();
        try {
            FileInputStream fileIn = new FileInputStream("/Users/Mariya/file.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            obj1 = (ToyStopInventoryManagementSystem) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();

        } catch (ClassNotFoundException c) {
            System.out.println(" not found");
            c.printStackTrace();

        }
        return obj1;


    }






}


