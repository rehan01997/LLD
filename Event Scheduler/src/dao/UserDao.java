package dao;

import entity.Team;
import entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserDao {

    private final Map<String, User> userMap = new HashMap<>(); // name, user object

    public User getUser(String name) {
        if(!userMap.containsKey(name)) {
            throw new RuntimeException("User not found for user name " + name);
        }
        return userMap.get(name);
    }

    public void createUser(User user) {
        if(userMap.containsKey(user.getName())) {
            throw new RuntimeException("User already exist for user name " + user.getName());
        }
        userMap.put(user.getName(), user);
    }

    public Optional<Team> getTeam(String userName) {
        User user = getUser(userName);
        return user.getTeam();
    }

    public void setTeam(User user, Team team) {
        user.setTeam(Optional.ofNullable(team));
    }
}
