package com.ccapstools_app.data.dto;

import java.util.Objects;

import com.ccapstools_app.utils.enums.Roles;

public class SpeakerDTO extends UserDTO{
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private String socialMedia;
    private Roles role;
    private String company;
    private String position;
    private String description;

    public SpeakerDTO() {
    }

    public static Long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(String socialMedia) {
        this.socialMedia = socialMedia;
    }

    public Roles getRelationship() {
        return role;
    }

    public void setRelationship(Roles role) {
        this.role = role;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((socialMedia == null) ? 0 : socialMedia.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpeakerDTO that = (SpeakerDTO) o;
        return id == that.id &&
                Objects.equals(socialMedia, that.socialMedia) &&
                role == that.role &&
                Objects.equals(company, that.company) &&
                Objects.equals(position, that.position) &&
                Objects.equals(description, that.description);
    }    

}