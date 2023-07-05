package com.example.helloworld.AllService;

public class AllUser {
    int id;
    String user;
    String pwd;

    @Override
    public String toString() {
        return "AllUser{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public AllUser(String user, String pwd) {
        this.id = id;
        this.user = user;
        this.pwd = pwd;
    }
}
