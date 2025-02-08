package com.ccapstools_app.data.dto;

import java.io.Serializable;
import java.util.Objects;

import com.ccapstools_app.utils.enums.Relationship;

public class SpeakerDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private Relationship Relationship;
    private String company;
    private String position;
    private String description;
    private UserDTO user;
    private String socialMedia;

    public SpeakerDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Relationship getRelationship() {
        return Relationship;
    }

    public void setRelationship(Relationship relationship) {
        Relationship = relationship;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(String socialMedia) {
        this.socialMedia = socialMedia;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((Relationship == null) ? 0 : Relationship.hashCode());
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((socialMedia == null) ? 0 : socialMedia.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SpeakerDTO other = (SpeakerDTO) obj;
        return Objects.equals(id, other.id) &&
                Relationship == other.Relationship &&
                Objects.equals(company, other.company) &&
                Objects.equals(position, other.position) &&
                Objects.equals(description, other.description) &&
                Objects.equals(user, other.user) &&
                Objects.equals(socialMedia, other.socialMedia);
    }

    

}