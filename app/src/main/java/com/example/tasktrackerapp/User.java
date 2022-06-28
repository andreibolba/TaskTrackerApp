package com.example.tasktrackerapp;

public class User {
    private String fName;
    private String lName;
    private String username;
    private String email;
    private String pass;
    private String phone;
    private Boolean logged;

    public User(){
        this.fName=null;
        this.lName=null;
        this.username=null;
        this.email=null;
        this.pass=null;
        this.phone=null;
        this.logged=false;
    }

    public User(String fName,String lName,String username,String email,String pass,String phone){
        this.fName=fName;
        this.lName=lName;
        this.username=username;
        this.email=email;
        this.pass=pass;
        this.phone=phone;
        this.logged=true;
    }

    public String getfName() { return fName; }
    public String getlName() { return lName; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPass() { return pass; }
    public String getPhone() { return phone; }
    public Boolean getLogged() {return logged;}

    public void setfName(String fName) { this.fName = fName; }
    public void setlName(String lName) { this.lName = lName; }
    public void setEmail(String email) { this.email = email; }
    public void setPass(String pass) { this.pass = pass; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setUsername(String username) { this.username = username; }

    @Override
    public String toString() {
        return "User{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", phone='" + phone + '\'' +
                ", logged=" + logged +
                '}';
    }
}
