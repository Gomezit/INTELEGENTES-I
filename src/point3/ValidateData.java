/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package point3;

import java.io.Serializable;

/**
 *
 * @author andres
 */
public class ValidateData implements Serializable {

    private String password;
    private String user;

    public ValidateData() {
    }

    public ValidateData(String password, String user) {
        this.password = password;
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
