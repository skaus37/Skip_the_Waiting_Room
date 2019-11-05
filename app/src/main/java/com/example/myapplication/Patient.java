package com.example.myapplication;

public class User {

    private String firstName;
    private String lastName;

    private String accountType;

    public User(){

    }

    public User(firstName, lastName, accountType){
        firstName = firstName;
        lastName = lastName;
        accountType = accountType;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getAccountType(){
        return accountType;
    }
}
