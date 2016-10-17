package com.example.ngoveni.todo;
/**
 * Created by Mofokeng on 16-Oct-16.
 */
public class User {

    public User(String password, String userName, String fName, String lName) {
        this.password = password;
        this.userName = userName;
        this.fName = fName;
        this.lName = lName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    private String userName;
    private String password;
    private String fName;
    private String lName;
}
