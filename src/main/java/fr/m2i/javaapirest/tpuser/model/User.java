/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.javaapirest.tpuser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ben
 */
@XmlRootElement(name = "utilisateurs")
@Entity
@Table( name = "utilisateurs" )
public class User {
    private int id;
    private String lastname;
    private String firstname;
    private String role;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public User() {
    }

    public User(String lastname, String firstname, String role, String email, String password) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.role = role;
        this.email = email;
        this.password = password;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", lastname=" + lastname + ", firstname=" + firstname + ", role=" + role + ", email=" + email + ", password=" + password + '}';
    }
    
    public void copy(User userData) {

        if (userData == null) {
            return;
        }

        if (userData.getLastname() != null) {
            this.lastname = userData.getLastname();
        }

        if (userData.getFirstname() != null) {
            this.firstname = userData.getFirstname();
        }

        if (userData.getEmail() != null) {
            this.email = userData.getEmail();
        }

        if (userData.getPassword() != null) {
            this.password = userData.getPassword();
        }

        if (userData.getRole() != null) {
            this.role = userData.getRole();
        }
    }
}
