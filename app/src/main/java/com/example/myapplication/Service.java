package com.example.myapplication;

public class Service {

    private String id;
    private String serviceName;
    private String role;

    public Service(){

    }

    public Service(String _id, String _serviceName, String _role){
        id = _id;
        serviceName = _serviceName;
        role = _role;
    }

    public Service(String _serviceName, String _role){
        serviceName = _serviceName;
        role = _role;
    }

    public String getID(){
        return id;
    }

    public String getServiceName(){
        return serviceName;
    }

    public String getRole(){
        return role;
    }
}
