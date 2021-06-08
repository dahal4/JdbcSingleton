/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcSingleton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author manish
 */
public class JdbcSingletonDemo {

    static int count = 1;
    static int choice;

    public static void main(String[] args) throws IOException {
        JdbcSingleton jdbc = JdbcSingleton.getInstance();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("DATABASE OPERATIONS");
            System.out.println(" --------------------- ");
            System.out.println(" 1. Insertion ");
            System.out.println(" 2. View      ");
            System.out.println(" 3. Delete    ");
            System.out.println(" 4. Update    ");
            System.out.println(" 5. Exit      ");

            System.out.print("\n");
            System.out.print("Please enter your choice: ");

            choice = Integer.parseInt(br.readLine());
            switch (choice) {

                case 1: {
                    System.out.println("Enter the employee id:");
                    int eid = Integer.parseInt(br.readLine());
                    System.out.print("Enter the employee name : ");
                    String username = br.readLine();
                    System.out.print("Enter employee age: ");
                    int age = Integer.parseInt(br.readLine());

                    try {
                        int i = jdbc.insert(eid, username, age);
                        if (i > 0) {
                            System.out.println((count++) + " Data has been inserted successfully");
                        } else {
                            System.out.println("Data has not been inserted ");
                        }

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    System.out.println("Press Enter key to continue...");
                    System.in.read();

                }//End of case 1  
                break;
                //view
                case 2: {

                    try {
                        jdbc.view();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    System.out.println("Press Enter key to continue...");
                    System.in.read();

                }//End of case 2  
                break;
                //delete
                case 3: {
                    System.out.print("Enter the employee id,  you want to delete: ");
                    int userid = Integer.parseInt(br.readLine());

                    try {
                        int i = jdbc.delete(userid);
                        if (i > 0) {
                            System.out.println((count++) + " Data has been deleted successfully");
                        } else {
                            System.out.println("Data has not been deleted");
                        }

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    System.out.println("Press Enter key to continue...");
                    System.in.read();

                }//End of case 3  
                break;
                case 4: {
                    System.out.print("Enter the employee id,  you want to update: ");
                    int eid = Integer.parseInt(br.readLine());
                    System.out.print("Enter the new name: ");
                    String name = br.readLine();
                    System.out.print("Enter the new age ");
                    int age = Integer.parseInt(br.readLine());

                    try {
                        int i = jdbc.update(eid, name, age);
                        if (i > 0) {
                            System.out.println((count++) + " Data has been updated successfully");
                        }

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    System.out.println("Press Enter key to continue...");
                    System.in.read();

                }// end of case 4  
                break;

                default:
                    return;
            }
        } while (choice != 4);

    }

}
