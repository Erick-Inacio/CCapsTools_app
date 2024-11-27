package com.ccapstools_app.models;

import java.io.Serializable;
import java.util.Objects;

import com.ccapstools_app.data.enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Enumerated(jakarta.persistence.EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    @Column(name = "ra")
    private String ra;

    public Users() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + (id == null ? 0 : id.hashCode());
        result = PRIME * result + (name == null ? 0 : name.hashCode());
        result = PRIME * result + (email == null ? 0 : email.hashCode());
        result = PRIME * result + (userType == null ? 0 : userType.hashCode());
        result = PRIME * result + (ra == null ? 0 : ra.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id) &&
                Objects.equals(name, users.name) &&
                Objects.equals(email, users.email) &&
                userType == users.userType &&
                Objects.equals(ra, users.ra);
    }


    

}
