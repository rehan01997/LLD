package service;

import entity.Event;
import entity.Team;
import entity.TimeSlot;
import entity.User;

import java.util.*;
import java.util.stream.Collectors;

public class EventService {

    private final UserService userService;

    private final TeamService teamService;

    Map<String, Event> eventMap = new HashMap<>(); // name, event

    public EventService(UserService userService, TeamService teamService) {
        this.userService = userService;
        this.teamService = teamService;
    }

    public List<Event> getEventByUser(String userName, Date startTime, Date endTime) {
        User user = userService.getUser(userName);
        return user.getEventList()
                .stream()
                .filter(x -> x.getTimeSlot().getStartTime().getTime() >= startTime.getTime() && x.getTimeSlot().getEndTime().getTime() <= endTime.getTime())
                .collect(Collectors.toList());
    }

    public void createEvent(String eventName, List<String> userList, List<String>teamList, int representatives, Date startTime, Date endTime) {
        Set<User> eventUser = new HashSet<>();

        for(String userName: userList) {
            User user = userService.getUser(userName);
            checkIfUserAvailable(user, startTime, endTime);
            eventUser.add(user);
        }

        for(String teamName : teamList) {
            int noOfAvailableMembers = 0;
            List<User>availableTeamUser = new ArrayList<>();
            Team team = teamService.getTeam(teamName);
            for(User user: team.getUsers()) {
                try {
                    checkIfUserAvailable(user, startTime, endTime);
                    availableTeamUser.add(user);
                    noOfAvailableMembers++;
                    if(noOfAvailableMembers == representatives) {
                        break;
                    }
                } catch (RuntimeException e) {
                    System.out.println("Encounter error: " + e.getMessage());
                }
            }

            if(noOfAvailableMembers == representatives) {
                eventUser.addAll(availableTeamUser);
            } else {
                throw new RuntimeException("Number of representatives are not equal to required members");
            }
        }

        Event event = new Event(eventName, new ArrayList<>(eventUser), new TimeSlot(startTime, endTime));
        eventMap.put(eventName, event);
        for(User user: eventUser) {
            userService.addEvent(user, event);
        }

        System.out.println("Event created, event Name:" + eventName);
    }

    private void checkIfUserAvailable(User user, Date startTime, Date endTime) {
        List<Event> eventList = user.getEventList();
        for(Event event: eventList) {
            TimeSlot timeSlot = event.getTimeSlot();
            if(timeSlot.getStartTime().after(startTime) && timeSlot.getStartTime().before(endTime)
               || (timeSlot.getEndTime().after(startTime) && timeSlot.getEndTime().before(endTime))) {
//               || timeSlot.getStartTime().equals(startTime) || timeSlot.getEndTime().equals(endTime)) {
                throw new RuntimeException("User: " + user.getName() + " is not available for the event");
            }
        }

        if(startTime.before(user.getWorkingHours().getStartTime()) || endTime.before(user.getWorkingHours().getStartTime())
           || startTime.after(user.getWorkingHours().getEndTime()) || endTime.after(user.getWorkingHours().getEndTime())) {
            throw new RuntimeException("User: " + user.getName() + " is not available for the event.");
        }
    }
}
