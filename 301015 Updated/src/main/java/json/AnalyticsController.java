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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AnalyticsController {

    @RequestMapping(value = "/getStatusBreakdownByBusinessUnit/{businessUnit}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    StatusBreakdownResponse getStatusBreakdownByBusinessUnit(@PathVariable("businessUnit") String businessUnit) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Jobs> jobList = new ArrayList();

        int pending = 0;
        int reviewed = 0;

        try {
            //Set up connection with database
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();

            //Execute sql satatement to obtain jobs from database
            rs = stmt.executeQuery("select a.status from job j inner join application a on j.jobID = a.jobIDApplied and j.businessUnit = '" + businessUnit + "';");

            while (rs != null && rs.next()) {
                String status = rs.getString(1);

                switch (status) {
                    case "Pending":
                        pending++;
                        break;
                    case "Reviewed":
                        reviewed++;
                        break;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }

        return new StatusBreakdownResponse(pending, reviewed);
    }
}
