package com.ccapstools_app.data.dto;

import java.io.Serializable;
import java.util.Objects;

public class UsersDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;


    private Long id;
    private String name;
    private String email;
    private String ra;
    private String userType;
    
    public UsersDTO() {
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

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + (id == null ? 0 : id.hashCode());
        result = PRIME * result + (name == null ? 0 : name.hashCode());
        result = PRIME * result + (email == null ? 0 : email.hashCode());
        result = PRIME * result + (ra == null ? 0 : ra.hashCode());
        result = PRIME * result + (userType == null ? 0 : userType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UsersDTO other = (UsersDTO) obj;
        return Objects.equals(id, other.id) &&
               Objects.equals(name, other.name) &&
               Objects.equals(email, other.email) &&
               Objects.equals(ra, other.ra) &&
               Objects.equals(userType, other.userType);
    }

    

}
