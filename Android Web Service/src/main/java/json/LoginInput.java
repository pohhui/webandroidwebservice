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
public class LoginInput {

    private String username;
    private String password;
    private String emailAddress;
    private String confirmedPassword;

    public LoginInput() {
    }

    public LoginInput(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginInput(String username, String password, String emailAddress) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
