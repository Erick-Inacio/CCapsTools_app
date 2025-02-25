package com.ccapstools_app.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.ccapstools_app.utils.enums.SocialMediaEnum;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "speakers")
public class Speaker implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company")
    private String company;

    @Column(name = "position")
    private String position;

    @Column(name = "bio")
    private String bio;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ElementCollection
    @CollectionTable(name = "speaker_social_media", joinColumns = @JoinColumn(name = "speaker_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "platform")
    @Column(name = "url")
    private Map<SocialMediaEnum, String> socialMedia = new HashMap<>();

    public Speaker() {
    }

    public Map<SocialMediaEnum, String> getSocialMedia() {
        return socialMedia;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSocialMedia(Map<SocialMediaEnum, String> socialMedia) {
        this.socialMedia = socialMedia;
    }

    public void addSocialMedia(SocialMediaEnum platform, String url) {
        socialMedia.put(platform, url);
    }

    public void removeSocialMedia(SocialMediaEnum platform) {
        socialMedia.remove(platform);
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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Speaker other = (Speaker) obj;
        return Objects.equals(id, other.id) &&
               Objects.equals(company, other.company) &&
               Objects.equals(position, other.position) &&
               Objects.equals(bio, other.bio) &&
               Objects.equals(user, other.user) &&
               Objects.equals(socialMedia, other.socialMedia);
    }

}