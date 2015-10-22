/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import java.sql.Connection;
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

/**
 *
 * @author Evangeline
 */
@Controller
public class ApplicantController {

    @RequestMapping(value = "/getAppliedJobByUser", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody ArrayList<Applicant> getAppliedJobByUser(@RequestBody Applicant applicant) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
       
        ArrayList<Applicant> appliedJobList = new ArrayList<Applicant>();
        String query = "SELECT a.username, j.postingTitle, j.businessUnit, j.location, j.createdBy, j.createdOn, j.statusCode, j.employmentType, j.shift, j.description, j.requirement, j.validity, a.jobIDApplied, appID, a.dateApplied, status FROM application a INNER JOIN job j on j.jobID = a.jobIDApplied where a.username = \"" + applicant.getUsername() + "\"";
        try {
            //Set up connection with database
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement(query);
            //stmt.setString(1, username);
                        
            //Execute sql satatement to obtain account from database
            rs = stmt.executeQuery();
            //Execute sql satatement to obtain jobs from database
            // rs = stmt.executeQuery("SELECT a.username, j.postingTitle, j.businessUnit, j.location, j.createdBy, j.CreatedOn, J.statusCode, j.employmentType, j.shift, j.description, j.requirement, j.validity,a.jobIDApplied, appID, a.dateApplied, status FROM application a INNER JOIN job j on j.jobID = a.jobIDApplied where a.username = + '" + username  + "' ORDER BY appID desc ");
            System.out.println("here " + applicant.getUsername() + "end");
        
            while (rs != null && rs.next()) {
                System.out.println("loop");
                String name = rs.getString(1);
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
                int jobID = rs.getInt(13);
                int appID = rs.getInt(14);
                String dateApplied = rs.getString(15);
                String status = rs.getString(16);

                Jobs job = new Jobs(jobID, businessUnit, postingTitle, createdBy, createdOn, location, employmentType, shift, description, requirement, validity);
                Applicant a = new Applicant(appID, jobID, name, dateApplied, status, job);
                appliedJobList.add(a);
                System.out.println(requirement);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        System.out.println(appliedJobList.size());
        return appliedJobList;

    }

}
