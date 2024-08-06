package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class User {
    private String name;
    private TimeSlot workingHours;
    private Optional<Team> team = Optional.empty();
    private List<Event> eventList = new ArrayList<>(); // day, blocked time


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimeSlot getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(TimeSlot workingHours) {
        this.workingHours = workingHours;
    }

    public Optional<Team> getTeam() {
        return team;
    }

    public void setTeam(Optional<Team> team) {
        this.team = team;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }
}
