package com.ccapstools_app.data.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.ccapstools_app.utils.enums.SocialMediaEnum;

public class SpeakerVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String company;
    private String position;
    private String bio;
    private Long user;
    private Map<SocialMediaEnum, String> socialMedia = new HashMap<>();

    public SpeakerVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Map<SocialMediaEnum, String> getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(Map<SocialMediaEnum, String> socialMedia) {
        this.socialMedia = socialMedia;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        result = prime * result + ((bio == null) ? 0 : bio.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((socialMedia == null) ? 0 : socialMedia.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SpeakerVO other = (SpeakerVO) o;
        return Objects.equals(id, other.id) &&
                Objects.equals(company, other.company) &&
                Objects.equals(position, other.position) &&
                Objects.equals(bio, other.bio) &&
                Objects.equals(user, other.user) &&
                Objects.equals(socialMedia, other.socialMedia);
    }

}
