package com.ccapstools_app.models;

import java.io.Serializable;
import java.util.Objects;

import com.ccapstools_app.utils.enums.Roles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid", unique = true)
    private String uid;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Enumerated(jakarta.persistence.EnumType.STRING)
    @Column(name = "role")
    private Roles role;

    @Column(name = "ra", unique = true, nullable = true)
    private String ra;

    @Column(name = "admin")
    private Boolean admin = false;

    @Column(name = "commission")
    private Boolean commission = false;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getCommission() {
        return commission;
    }

    public void setCommission(Boolean commission) {
        this.commission = commission;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((uid == null) ? 0 : uid.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((ra == null) ? 0 : ra.hashCode());
        result = prime * result + ((admin == null) ? 0 : admin.hashCode());
        result = prime * result + ((commission == null) ? 0 : commission.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(uid, user.uid) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                role == user.role &&
                Objects.equals(ra, user.ra) &&
                Objects.equals(admin, user.admin) &&
                Objects.equals(commission, user.commission);
    }

}
