/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package json;

public class Applicant {

    
    int appID;
    int jobIDApplied;
    String username;
    String dateApplied;
    String status;
    Jobs job;
            
    
    public int getAppID() {
        return appID;
    }

    public void setAppID(int appID) {
        this.appID = appID;
    }

    public int getJobIDApplied() {
        return jobIDApplied;
    }

    public void setJobIDApplied(int jobIDApplied) {
        this.jobIDApplied = jobIDApplied;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(String dateApplied) {
        this.dateApplied = dateApplied;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Applicant(int appID, int jobIDApplied, String username, String dateApplied, String status) {
        this.appID = appID;
        this.jobIDApplied = jobIDApplied;
        this.username = username;
        this.dateApplied = dateApplied;
        this.status = status;
    }
    
    public Applicant(int appID, int jobIDApplied, String username, String dateApplied, String status, Jobs job) {
    this.appID = appID;
    this.jobIDApplied = jobIDApplied;
    this.username = username;
    this.dateApplied = dateApplied;
    this.status = status;
    this.job = job;
    }
    
    public Applicant() {
   
    }
   
}
