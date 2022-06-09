package com.example.timetablemanager.Databases;

import android.provider.ContactsContract;

public class User {
    int id;
    String name,Username,Email,password;

    public User(int id, String name, String username, String email, String password) {
        this.id = id;
        this.name = name;
        Username = username;
        Email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
