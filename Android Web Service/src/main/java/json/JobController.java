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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
 
@Controller
public class JobController {
    
   @RequestMapping(value="/getAllJobs", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody ArrayList<Jobs> getAllJobs(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Jobs> jobList = new ArrayList();
        
        try {
            //Set up connection with database
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            
            //Execute sql satatement to obtain jobs from database
            rs = stmt.executeQuery("select * from job ORDER BY jobID desc;");

            while (rs != null && rs.next()) {
                int jobID = rs.getInt(1);
                String postingTitle = rs.getString(2);
                String businessUnit = rs.getString(3);
                String location = rs.getString(4);
                String createdBy = rs.getString(5);
                String createdOn = rs.getString(6);
                String employmentType = rs.getString(8);
                String shift = rs.getString(9);
                String description = rs.getString(10);
                String requirement = rs.getString(11);
                String validity = rs.getString(12);

                Jobs jobPost = new Jobs(jobID, businessUnit, postingTitle, createdBy, createdOn, location, employmentType, shift, description, requirement, validity);
                jobList.add(jobPost);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        
        return jobList;
    }
    
    @RequestMapping(value="/getSingleJob", method = RequestMethod.POST, produces="application/json")
    public @ResponseBody Jobs getSingleJob(@RequestBody Jobs job){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Jobs j = null;
        String query = "select * from job where jobID = ?";
        
        
       try {
            //Set up connection with database
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement(query);
            
            //set username
            //stmt.setString(1, Integer.parseInt(job.getJobId()));
            stmt.setInt(1,job.getJobId());
                        
            //Execute sql satatement to obtain account from database
            rs = stmt.executeQuery();
       //     System.out.println("jobid" + query)
            while (rs != null && rs.next()) {
                int jobID = rs.getInt(1);
                String postingTitle = rs.getString(2);
                String businessUnit = rs.getString(3);
                String location = rs.getString(4);
                String createdBy = rs.getString(5);
                String createdOn = rs.getString(6);
                String employmentType = rs.getString(8);
                String shift = rs.getString(9);
                String description = rs.getString(10);
                String requirement = rs.getString(11);
                String validity = rs.getString(12);

                j = new Jobs(jobID, businessUnit, postingTitle, createdBy, createdOn, location, employmentType, shift, description, requirement, validity);
                       
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
       
       return j;
    }  
    
}
