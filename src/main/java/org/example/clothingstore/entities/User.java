package org.example.clothingstore.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements Serializable {

    private String username;

    private String password;

    private String email;

    private String fullName;

    private int age;

    private List<Role> roles;

    public User() {
        this.roles = new ArrayList<>();
    }

    public User(String username, String password, String email, String fullName, int age) {
        this();

        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.age = age;
    }

    @Column(nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @ManyToMany(fetch = FetchType.EAGER)
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

//@Entity
//@Table(name = "users")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//public class User extends BaseEntity implements Serializable {
//
//    private String username;
//    private String login;
//    private String password;
//    private String email;
//    private String phone;
//    private List<Role> roles;
//
//    public User(String username, String login, String password, String email, String phone) {
//        this();
//        setUsername(username);
//        setLogin(login);
//        setPassword(password);
//        setEmail(email);
//        setPhone(phone);
//    }
//
//    public User() {
//        this.roles = new ArrayList<>();
//    }
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    public List<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<Role> roles) {
//        this.roles = roles;
//    }
//
//    @Column(name = "username", nullable = false)
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    @Column(name = "login", nullable = false)
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//
//    @Column(name = "password", nullable = false)
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    @Column(name = "email")
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    @Column(name = "phone")
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//}
