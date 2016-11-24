
package toystopinventorymanagementsystem;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.sql.*;


public class ToyStopService implements Serializable{











    ArrayList<Employee> employees = new ArrayList<>();
    ArrayList<Store> stores = new ArrayList<>();

    public void initEmployees(){
        //Create a List of first 200 Employees
        for(int i=0; i<200; i++){
            Employee myEmployee = new Employee();
            myEmployee.setUID(i);
            myEmployee.setRandomName();
            myEmployee.setEmail(myEmployee.getName()+"@mariya.org");

            employees.add(myEmployee);
        }
    }

    public void initStores(){
        //Create a List of Stores in a region
        for(int i=0; i<100; i++){
            Store myStore = new Store();
            myStore.setUID(Util.getSaltNum(-1));
            myStore.addRandomEmployees(employees);
            stores.add(myStore);
            myStore.setAddress(Util.getSaltAlphaNumString());
            myStore.setContactNo("+92"+Util.getSaltNum(9));
            Email storeEmail = new Email();
            storeEmail.setEmailAddress(myStore.getUID()+"@mariya.org");
            myStore.setEmail(storeEmail);

        }

    }

    public void initToys(){
        //Add Toys in random stores
        for(int i=0; i<200000; i++){
            Toy newToy = new Toy();
            newToy.setUID(Util.getSaltNum(-1));
            newToy.setMinAge(Util.getSaltNum(1));
            newToy.setMaxAge(Util.getSaltNum(18));
            newToy.setPrice(Util.getSaltNum(1000));
            newToy.setName(Util.getSaltAlphaString());
            newToy.setAddedOn(LocalDateTime.now());

            Random randStore = new Random();
            int index = randStore.nextInt(stores.size());
            Store selectedStore = (Store)stores.get(index);
            selectedStore.addToy(newToy);

        }
    }


    public int addToys(){
        //Add Toys in random stores

            Toy newToy = new Toy();
            newToy.setUID(Util.getSaltNum(-1));
            newToy.setMinAge(Util.getSaltNum(1));
            newToy.setMaxAge(Util.getSaltNum(18));
            newToy.setPrice(Util.getSaltNum(1000));
            newToy.setName(Util.getSaltAlphaString());
            newToy.setAddedOn(LocalDateTime.now());

            Random randStore = new Random();
            int index = randStore.nextInt(stores.size());
            Store selectedStore = (Store)stores.get(index);
            selectedStore.addToy(newToy);













        return newToy.getUID();


    }



    //Only creates a new employee and returns the UID
    public int addEmployee(){
        Employee myEmployee = new Employee();
        myEmployee.setRandomName();
        myEmployee.setEmail(myEmployee.getName()+"@mariya.org");
        myEmployee.setUID(employees.size()+1);
        employees.add(myEmployee);
        return myEmployee.getUID();
    }

    //Creates a new store
    public int addStore(){
        Store myStore = new Store();
        myStore.setUID(Util.getSaltNum(-1));
        //This will assign any new employees or the ones remaining after previous store insertions.
        myStore.addRandomEmployees(employees);

        String X = myStore.setAddress(Util.getSaltAlphaNumString());


        myStore.setContactNo("+92"+Util.getSaltNum(9));
        String Y =    myStore.setContactNo("+92"+Util.getSaltNum(9));
       // Email storeEmail = new Email();
        //storeEmail.setEmailAddress(myStore.getUID()+"@mariya.org");
        //myStore.setEmail(storeEmail);
        stores.add(myStore);

        int idd = myStore.getUID();


        // JDBC driver name and database URL
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/mariya";

        //  Database credentials
        String USER = "root";
        String PASS = "root";





        Connection conn = null;
        Statement stmt = null;




        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT into store (id, address, contactNumber) values (?,?, ?)";



            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setInt (1,idd);
            preparedStmt.setString (2,X);
            preparedStmt.setString (3,Y);



            preparedStmt.execute();
            conn.close();

            //ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
//            while(rs.next()){
//                //Retrieve by column name
//                int id  = rs.getInt("id");
////                int age = rs.getInt("age");
////                String first = rs.getString("first");
////                String last = rs.getString("last");
//
//                //Display values
//                System.out.print("ID: " + id);
////                System.out.print(", Age: " + age);
////                System.out.print(", First: " + first);
////                System.out.println(", Last: " + last);
//            }
//            //STEP 6: Clean-up environment
            //          rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try






        return myStore.getUID();
    }



}
