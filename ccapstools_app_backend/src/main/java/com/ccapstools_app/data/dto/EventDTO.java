package com.ccapstools_app.data.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class EventDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDateTime initialDateTime;
    private LocalDateTime finalDateTime;
    private List<ActivityDTO> activities;
    private String description;

    public EventDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getInitialDateTime() {
        return initialDateTime;
    }

    public void setInitialDateTime(LocalDateTime initialDateTime) {
        this.initialDateTime = initialDateTime;
    }

    public LocalDateTime getFinalDateTime() {
        return finalDateTime;
    }

    public void setFinalDateTime(LocalDateTime finalDateTime) {
        this.finalDateTime = finalDateTime;
    }

    public List<ActivityDTO> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityDTO> activities) {
        this.activities = activities;
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
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((initialDateTime == null) ? 0 : initialDateTime.hashCode());
        result = prime * result + ((finalDateTime == null) ? 0 : finalDateTime.hashCode());
        result = prime * result + ((activities == null) ? 0 : activities.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
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
        EventDTO other = (EventDTO) obj;
        return Objects.equals(id, other.id) &&
               Objects.equals(initialDateTime, other.initialDateTime) &&
               Objects.equals(finalDateTime, other.finalDateTime) &&
               Objects.equals(activities, other.activities) &&
               Objects.equals(description, other.description);
    }

    
}
