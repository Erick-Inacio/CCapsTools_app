
package com.ccapstools_app.models.event;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "events")
public class EventModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Data inicial do evento")
    @Column(name = "initial_date_time")
    private LocalDateTime initialDateTime;

    @Schema(description = "Data final do evento")
    @Column(name = "final_date_time")
    private LocalDateTime finalDateTime;

    @Schema(description = "Atividades do evento")
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActivityModel> activities  = new ArrayList<>();

    @Schema(description = "Descrição do evento")
    @Column(name = "description")
    private String description;

    public EventModel() {
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

    public List<ActivityModel> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityModel> activities) {
        this.activities = activities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Métodos
    public void addActivity(ActivityModel activity) {
        activity.setEvent(this);
        this.activities.add(activity);
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EventModel that = (EventModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(initialDateTime, that.initialDateTime) &&
                Objects.equals(finalDateTime, that.finalDateTime) &&
                Objects.equals(activities, that.activities) &&
                Objects.equals(description, that.description);
    }

}
