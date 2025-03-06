package com.ccapstools_app.data.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class EventVO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDateTime initialDateTime;
    private LocalDateTime finalDateTime;
    private String description;

    public EventVO() {}

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
        // result = prime * result + ((activities == null) ? 0 : activities.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
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
        EventVO that = (EventVO) o;
        return Objects.equals(this.id, that.id) &&
               Objects.equals(this.initialDateTime, that.initialDateTime) &&
               Objects.equals(this.finalDateTime, that.finalDateTime) &&
            //    Objects.equals(this.activities, that.activities) &&
               Objects.equals(this.description, that.description);
    }

    
}
