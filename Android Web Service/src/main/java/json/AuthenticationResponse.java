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
public class AuthenticationResponse {
    private boolean isAuthenticated;
    private String errors;
    
    
    public AuthenticationResponse() {
    }

    public AuthenticationResponse(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }
   
    public AuthenticationResponse(boolean isAuthenticated, String errors) {
        this.isAuthenticated = isAuthenticated;
        this.errors = errors;
    }

    public boolean isIsAuthenticated() {
        return isAuthenticated;
    }

    public String getErrors() {
        return errors;
    }

    public void setIsAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}
