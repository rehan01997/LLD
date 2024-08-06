package entity;

import java.sql.Time;
import java.util.Comparator;
import java.util.Date;

public class TimeSlot implements Comparable<TimeSlot> {

    public Date startTime;

    public Date endTime;

    public TimeSlot(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public int compareTo(TimeSlot o) {
        return this.startTime.compareTo(o.getStartTime());
    }
}
