package com.ccapstools_app.data.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.ccapstools_app.utils.enums.ActivityTypeEnum;

public class ActivityVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String activityName;
    private List<Long> speakers;
    private ActivityTypeEnum activityType;
    private List<LocalDateTime> dates;
    private Integer duration;
    private String local;
    private String aimedAudience;
    private String prerequisite;
    private String hardSoftwareRequired;
    private String description;
    private boolean approved;
    private EventVO event;

    public ActivityVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Long> speakers) {
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

    public EventVO getEvent() {
        return event;
    }

    public void setEvent(EventVO event) {
        this.event = event;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((activityName == null) ? 0 : activityName.hashCode());
        result = prime * result + ((speakers == null) ? 0 : speakers.hashCode());
        result = prime * result + ((activityType == null) ? 0 : activityType.hashCode());
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
    ActivityVO other = (ActivityVO) obj;
    return Objects.equals(id, other.id) &&
           Objects.equals(activityName, other.activityName) &&
           Objects.equals(speakers, other.speakers) &&
           activityType == other.activityType &&
           Objects.equals(dates, other.dates) &&
           Objects.equals(duration, other.duration) &&
           Objects.equals(local, other.local) &&
           Objects.equals(aimedAudience, other.aimedAudience) &&
           Objects.equals(prerequisite, other.prerequisite) &&
           Objects.equals(hardSoftwareRequired, other.hardSoftwareRequired) &&
           Objects.equals(description, other.description) &&
           approved == other.approved &&
           Objects.equals(event, other.event);
}

    
}
