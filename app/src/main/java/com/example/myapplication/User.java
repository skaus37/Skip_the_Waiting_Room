package com.example.myapplication;

public class User {
    private String _firstName;
    private String _lastName;
    private String _accountType;
    private String _email;

    public User(){

    }
    public User(String firstName, String lastName, String accountType, String email){
        _firstName = firstName;
        _lastName = lastName;
        _accountType = accountType;
        _email = email;
    }

    public String getFirstName(){
        return _firstName;
    }
    public void setFirstName (String newFirstName){
        _firstName = newFirstName;
    }

    public String getLastName(){
        return _lastName;
    }

    public void setLastName (String newLastName){
        _lastName = newLastName;
    }
    public String getAccountType(){
        return _accountType;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String newEmail){
        _email = newEmail;
    }


}
