package com.ccapstools_app.models.event;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.ccapstools_app.models.users.SpeakerModel;
import com.ccapstools_app.utils.enums.ActivityTypeEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "activities")
public class ActivityModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Palestrantes dessa atividade")
    @ManyToMany
    @JoinTable(name = "activity_speakers", joinColumns = @JoinColumn(name = "activity_id"), inverseJoinColumns = @JoinColumn(name = "speaker_id"))
    private List<SpeakerModel> speakers;

    @Schema(description = "Tipo da atividade")
    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type")
    private ActivityTypeEnum activityType;

    @Schema(description = "Nome da atividade")
    @Column(name = "activity_name")
    private String activityName;

    @Schema(description = "Datas da atividade")
    @ElementCollection
    @CollectionTable(name = "activity_dates", joinColumns = @JoinColumn(name = "activity_id"))
    @Column(name = "date")
    private List<LocalDateTime> dates;

    @Schema(description = "Duração da atividade")
    @Column(name = "duration")
    private Integer duration;

    @Schema(description = "Local da atividade")
    @Column(name = "local")
    private String local;

    @Schema(description = "Alvo da atividade")
    @Column(name = "aimed_audience")
    private String aimedAudience;

    @Schema(description = "Pré-requisitos da atividade")
    @Column(name = "prerequisite")
    private String prerequisite;

    @Schema(description = "Hardware e/ou Software necessário para a atividade")
    @Column(name = "hard_software_required")
    private String hardSoftwareRequired;

    @Schema(description = "Descrição da atividade")
    @Column(name = "description")
    private String description;

    @Schema(description = "Status de aprovação da atividade")
    private boolean approved;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private EventModel event;

    public ActivityModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SpeakerModel> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<SpeakerModel> speakers) {
        this.speakers = speakers;
    }

    public ActivityTypeEnum getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityTypeEnum activityType) {
        this.activityType = activityType;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public List<LocalDateTime> getDates() {
        return dates;
    }

    public void setDates(List<LocalDateTime> dates) {
        this.dates = dates;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getAimedAudience() {
        return aimedAudience;
    }

    public void setAimedAudience(String aimedAudience) {
        this.aimedAudience = aimedAudience;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public String getHardSoftwareRequired() {
        return hardSoftwareRequired;
    }

    public void setHardSoftwareRequired(String hardSoftwareRequired) {
        this.hardSoftwareRequired = hardSoftwareRequired;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public EventModel getEvent() {
        return event;
    }

    public void setEvent(EventModel event) {
        this.event = event;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((speakers == null) ? 0 : speakers.hashCode());
        result = prime * result + ((activityType == null) ? 0 : activityType.hashCode());
        result = prime * result + ((activityName == null) ? 0 : activityName.hashCode());
        result = prime * result + ((dates == null) ? 0 : dates.hashCode());
        result = prime * result + ((duration == null) ? 0 : duration.hashCode());
        result = prime * result + ((local == null) ? 0 : local.hashCode());
        result = prime * result + ((aimedAudience == null) ? 0 : aimedAudience.hashCode());
        result = prime * result + ((prerequisite == null) ? 0 : prerequisite.hashCode());
        result = prime * result + ((hardSoftwareRequired == null) ? 0 : hardSoftwareRequired.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + (approved ? 1231 : 1237);
        result = prime * result + ((event == null) ? 0 : event.hashCode());
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
        ActivityModel other = (ActivityModel) obj;
        return Objects.equals(id, other.id) && Objects.equals(speakers, other.speakers)
                && Objects.equals(activityType, other.activityType) && Objects.equals(activityName, other.activityName)
                && Objects.equals(dates, other.dates) && Objects.equals(duration, other.duration)
                && Objects.equals(local, other.local) && Objects.equals(aimedAudience, other.aimedAudience)
                && Objects.equals(prerequisite, other.prerequisite)
                && Objects.equals(hardSoftwareRequired, other.hardSoftwareRequired)
                && Objects.equals(description, other.description) && approved == other.approved
                && Objects.equals(event, other.event);
    }

}
