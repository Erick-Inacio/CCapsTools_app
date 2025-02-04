package com.ccapstools_app.data.vo;

import java.io.Serializable;
import java.util.Objects;

import com.ccapstools_app.utils.enums.Relationship;
import com.ccapstools_app.utils.enums.Role;

public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id = null;
    private String uid;
    private String name;
    private String email;
    private Relationship Relationship;
    private Role role;
    private String ra;

    public UserVO() {

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

    public Relationship getRelationship() {
        return Relationship;
    }

    public void setRelationship(Relationship relationship) {
        Relationship = relationship;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((uid == null) ? 0 : uid.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((Relationship == null) ? 0 : Relationship.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((ra == null) ? 0 : ra.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        UserVO other = (UserVO) obj;
        return Objects.equals(id, other.id) &&
                Objects.equals(uid, other.uid) &&
                Objects.equals(name, other.name) &&
                Objects.equals(email, other.email) &&
                Relationship == other.Relationship &&
                Objects.equals(role, other.role) &&
                Objects.equals(ra, other.ra);
    }

}
