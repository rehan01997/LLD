package service;

import dao.UserDao;
import entity.Event;
import entity.Team;
import entity.TimeSlot;
import entity.User;

import java.util.Date;
import java.util.Optional;

public class UserService {

    private final UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public void createUser(String name, Date startTime, Date endTime) {
        User user = new User();
        user.setName(name);
        user.setWorkingHours(new TimeSlot(startTime, endTime));
        userDao.createUser(user);
    }

    public User getUser(String name) {
        return userDao.getUser(name);
    }

    public void setTeam(User user, Team team) {
        userDao.setTeam(user, team);
    }

    public Team getTeam(User user, Team team) {
        Optional<Team> userTeam = userDao.getTeam(user.getName());
        return userTeam.orElse(null);
    }
    
    public boolean checkIfUserPartOfTeam(String userName) {
        User user = getUser(userName);
        return user.getTeam().isPresent();
    }

    public void addEvent(User user, Event event) {
        user.getEventList().add(event);
    }
}
