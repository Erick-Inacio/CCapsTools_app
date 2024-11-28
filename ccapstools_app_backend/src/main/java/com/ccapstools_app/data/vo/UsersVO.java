package com.ccapstools_app.data.vo;

import java.io.Serializable;
import java.util.Objects;

import com.ccapstools_app.data.enums.UserType;

public class UsersVO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String name;
    private String email;
    private String ra;
    private UserType userType;

    public UsersVO() {
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

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserType getUserType() {
        return userType;
    }   

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (id == null ? 0 : id.hashCode());
        result = 31 * result + (name == null ? 0 : name.hashCode());
        result = 31 * result + (email == null ? 0 : email.hashCode());
        result = 31 * result + (ra == null ? 0 : ra.hashCode());
        result = 31 * result + (userType == null ? 0 : userType.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersVO that = (UsersVO) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(email, that.email) &&
            Objects.equals(ra, that.ra) &&
            Objects.equals(userType, that.userType);
    }
    

}
