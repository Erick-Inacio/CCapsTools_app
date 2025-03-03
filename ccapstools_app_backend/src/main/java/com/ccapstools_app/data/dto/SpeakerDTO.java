package com.ccapstools_app.data.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SpeakerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String company;
    private String position;
    private String bio;
    private UserDTO user;
    private Map<?, String> socialMedia = new HashMap<>();

    public SpeakerDTO() {
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Map<?, String> getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(Map<?, String> socialMedia) {
        if(socialMedia == null || socialMedia.isEmpty()) {
            this.socialMedia = null;
            return;
        }
        convertSocialMediaEnumToString();
    }

    // Métodos

    // Para converter SocialMediaEnum para String antes de enviar para o front
    private void convertSocialMediaEnumToString() {
        if (this.socialMedia != null) {
            Map<String, String> convertedMap = new HashMap<>();
            for (Map.Entry<?, String> entry : this.socialMedia.entrySet()) {
                convertedMap.put(entry.getKey().toString(), entry.getValue()); // Converte qualquer tipo de chave para
                                                                               // String
            }
            this.socialMedia = convertedMap;
        }
    }

    //Serialização
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
        SpeakerDTO other = (SpeakerDTO) obj;
        return Objects.equals(id, other.id) &&
                Objects.equals(company, other.company) &&
                Objects.equals(position, other.position) &&
                Objects.equals(bio, other.bio) &&
                Objects.equals(user, other.user) &&
                Objects.equals(socialMedia, other.socialMedia);
    }

}