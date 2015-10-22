/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
 
@Controller
public class AdminController {
     
    @RequestMapping(value="/getAdminListService", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody ArrayList<Admin> getAdmins(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Admin> adminList = new ArrayList();
        
        try {
            //Set up connection with database
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            
            //Execute sql satatement to obtain account from database
            rs = stmt.executeQuery("select * from admin;");

            while (rs != null && rs.next()) {
                String id = rs.getString(1);
                String password = rs.getString(2);
                String email = rs.getString(3);
                String name = rs.getString(4);
                String role = rs.getString(5);

                adminList.add(new Admin(id, password, email, name, role));                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        
        return adminList;
    }
    
     
}
