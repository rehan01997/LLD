package entity;

import java.util.List;

public class Event {
    private String name;
    private List<User> participantList;
    private TimeSlot timeSlot;

    public Event(String name, List<User> participantList, TimeSlot timeSlot) {
        this.name = name;
        this.participantList = participantList;
        this.timeSlot = timeSlot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(List<User> participantList) {
        this.participantList = participantList;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }
}
