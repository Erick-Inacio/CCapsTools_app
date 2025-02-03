package com.ccapstools_app.data.vo;

import java.util.Objects;

public class SpeakerVO extends UserVO {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String socialMedia;
    private String company;
    private String position;
    private String bio;

    public SpeakerVO() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(String socialMedia) {
        this.socialMedia = socialMedia;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((socialMedia == null) ? 0 : socialMedia.hashCode());
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        result = prime * result + ((bio == null) ? 0 : bio.hashCode());
        return result;
    }

    @Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof SpeakerVO)) return false;
    if (!super.equals(obj)) return false;
    SpeakerVO other = (SpeakerVO) obj;
    return Objects.equals(id, other.id) &&
           Objects.equals(socialMedia, other.socialMedia) &&
           Objects.equals(company, other.company) &&
           Objects.equals(position, other.position) &&
           Objects.equals(bio, other.bio);
}

    
}
