/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcSingleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author manish
 */
public class JdbcSingleton {

    private static JdbcSingleton jdbc;

    private JdbcSingleton() {
    }

    public static JdbcSingleton getInstance() {
        if (jdbc == null) {
            jdbc = new JdbcSingleton();
        }
        return jdbc;
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection con = null;
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "");
        return con;
    }

    //to insert the record in database
    public int insert(int eid, String name, int age) throws ClassNotFoundException, SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        int recordCounter = 0;
        try {
            c = this.getConnection();
            ps = c.prepareStatement("insert into emp(eid,name,age)values(?,?,?)");
            ps.setInt(1, eid);
            ps.setString(2, name);
            ps.setInt(3, age);
            recordCounter = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return recordCounter;
    }

    //to view the data from database
    public void view() throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            con = this.getConnection();
            ps = con.prepareStatement("select * from emp");
//            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Eid= " + rs.getInt(1) + "\t" + "Name= " + rs.getString(2)+"\t" + "age= " + rs.getInt(3));

            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    //update age
    public int update(int eid,String name, int age) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;

        int recordCounter = 0;
        try {
            c = this.getConnection();
            ps = c.prepareStatement("update emp set name=?,age=? where eid="+ eid + " ");
            ps.setString(1, name);
            ps.setInt(2, age);
            recordCounter = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return recordCounter;
    }

// to delete the data from the database   
    public int delete(int userid) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        int recordCounter = 0;
        try {
            c = this.getConnection();
            ps = c.prepareStatement(" delete from emp where eid='" + userid + "' ");
            recordCounter = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return recordCounter;
    }

}
