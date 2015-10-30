/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
 
@Controller
public class UserController {
     
   @RequestMapping(value="/authenticateUserService", method = RequestMethod.POST, produces="application/json")
    public @ResponseBody AuthenticationResponse authenticateUser(@RequestBody LoginInput loginInput){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String errors = null;
        String query = "select userPassword from user where username = ?";
        
        /*if(loginInput.getUsername().equals("") || loginInput.getPassword().equals("")){
            errors.add("Please fill in all fields.");
        }*/
        
       try {
            //Set up connection with database
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement(query);
            
            //set username
            stmt.setString(1, loginInput.getUsername());
                        
            //Execute sql satatement to obtain account from database
            rs = stmt.executeQuery();

            while (rs != null && rs.next()) {
                String password = rs.getString(1);
                if (password.equals(loginInput.getPassword())){
                    return new AuthenticationResponse(true);     
                }else {
                    errors = "Login failed. Please try again.";
                }
                       
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
       
       return new AuthenticationResponse(false, errors);
    }  
    
   @RequestMapping(value="/getUsersService", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody ArrayList<User> getUsers(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<User> userList = new ArrayList();
        
        try {
            //Set up connection with database
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            
            //Execute sql satatement to obtain account from database
            rs = stmt.executeQuery("select * from user;");

            while (rs != null && rs.next()) {
                
                String username = rs.getString(1);
                String password = rs.getString(2);
                String emailAddress = rs.getString(3);
                String fullname = rs.getString(4);
                String contactNo = rs.getString(5);
                String nricType = rs.getString(6);
                String nric = rs.getString(7);
                String dob = rs.getString(8);
                String gender = rs.getString(9);
                String blkStreetUnit = rs.getString(10);
                String postalCode = rs.getString(11);
                
                userList.add(new User(username, password, emailAddress, fullname, contactNo, nricType, nric, dob, gender, blkStreetUnit, postalCode)); 
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        
        return userList;
    }
    
    @RequestMapping(value="/createUserService", method = RequestMethod.POST, produces="application/json")
    public @ResponseBody AuthenticationResponse createUser(@RequestBody User user){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "INSERT INTO user (username, userPassword, emailAddress, fullName, contactNo, nricType, nric, dob, gender, blkStreetUnit, postalCode) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println("user" + query);
        try {
            //Set up connection with database
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmailAddress());
            stmt.setString(4, user.getFullname());
            stmt.setString(5, user.getContactNo());
            stmt.setString(6, user.getNricType());
            stmt.setString(7, user.getNric());
            stmt.setString(8, user.getDob());
            stmt.setString(9, user.getGender());
            stmt.setString(10, user.getBlkStreetUnit());
            stmt.setString(11, user.getPostalCode());
            stmt.executeUpdate();
            return new AuthenticationResponse(true);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }      
        
        return new AuthenticationResponse(false, "User creation failed.");
    }   
 
   
     
}
