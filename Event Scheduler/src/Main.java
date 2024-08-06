import entity.Event;
import service.EventService;
import service.TeamService;
import service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static class EventCalender {
        EventService eventService;
        UserService userService;
        TeamService teamService;

        private SimpleDateFormat dateFormat;

        EventCalender() {
            userService = new UserService();
            teamService = new TeamService(userService);
            eventService = new EventService(userService, teamService);
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

        public void createUser(String name, String startTime, String endTime) throws ParseException {
            userService.createUser(name, dateFormat.parse(startTime), dateFormat.parse(endTime));
        }

        public void createTeam(String name, List<String> userList) {
            teamService.createTeam(name, userList);
        }

        public void createEvent(String eventName, List<String>userList, List<String>teamList, int representatives, String startTime, String endTime) throws ParseException {
            eventService.createEvent(eventName, userList, teamList, representatives, dateFormat.parse(startTime), dateFormat.parse(endTime));
        }

        public void getEvent(String userName, String startTime, String endTime) throws ParseException {
            List<Event> eventList = eventService.getEventByUser(userName, dateFormat.parse(startTime), dateFormat.parse(endTime));
            eventList.forEach(x -> System.out.println("Event list for user name: " + userName + " - " + x.getName()));
        }
    }


    public static void main(String[] args) {
        try {
            EventCalender eventCalender = new EventCalender();
            eventCalender.createUser("A", "2024-01-10 10:00:00", "2024-01-10 19:00:00");
            eventCalender.createUser("B", "2024-01-10 09:30:00", "2024-01-10 17:30:00");
            eventCalender.createUser("C", "2024-01-10 11:30:00", "2024-01-10 18:30:00");
            eventCalender.createUser("D", "2024-01-10 10:00:00", "2024-01-10 18:00:00");
            eventCalender.createUser("E", "2024-01-10 11:00:00", "2024-01-10 19:30:00");
            eventCalender.createUser("F", "2024-01-10 11:00:00", "2024-01-10 18:30:00");

            eventCalender.createTeam("T1", new ArrayList<>(Arrays.asList("C","E")));
            eventCalender.createTeam("T2", new ArrayList<>(Arrays.asList("B","D","F")));

            eventCalender.createEvent("Event1", List.of("A"), List.of("T1"), 2, "2024-01-10 14:00:00", "2024-01-10 15:00:00");

            // user C is not available
            //eventCalender.createEvent("Event2", List.of("C"), List.of(), 2, "2024-01-10 14:00:00", "2024-01-10 15:00:00");

            eventCalender.createEvent("Event3", List.of(), List.of("T1","T2"), 2, "2024-01-10 15:00:00", "2024-01-10 16:00:00");
            eventCalender.createEvent("Event4", List.of("A"), List.of("T2"), 1, "2024-01-10 15:00:00", "2024-01-10 16:00:00");

            //user F is not available
            //eventCalender.createEvent("Event5", List.of("A","F"), List.of(), 1, "2024-01-10 10:00:00", "2024-01-10 11:00:00");

            // get event by user
            eventCalender.getEvent("A", "2024-01-10 10:00:00", "2024-01-10 17:00:00");
            eventCalender.getEvent("B", "2024-01-10 10:00:00", "2024-01-10 17:00:00");

        } catch (ParseException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}