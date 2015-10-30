/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

/**
 *
 * @author ng_po_000
 */
public class Admin {
    private String adminID;
    private String adminPassword;
    private String adminEmail;
    private String adminName;
    private String adminRole;
    

    public Admin(String adminID, String adminPassword, String adminEmail, String adminName, String adminRole) {
        this.adminID = adminID;
        this.adminPassword = adminPassword;
        this.adminEmail = adminEmail;
        this.adminName = adminName;
        this.adminRole = adminRole;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String id) {
        this.adminID = adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }
    
    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole (String adminRole) {
        this.adminRole = adminRole;
    }
}
