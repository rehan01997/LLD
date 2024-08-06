package service;

import dao.TeamDao;
import entity.Team;
import entity.User;

import java.util.List;
import java.util.Optional;

public class TeamService {

    private final TeamDao teamDao;
    private final UserService userService;

    public TeamService(UserService userService) {
        this.teamDao = new TeamDao();
        this.userService = userService;
    }

    public Team getTeam(String teamName) {
        return teamDao.getTeam(teamName);
    }

    public void createTeam(String teamName, List<String> userNamesList) {
        validateUsers(userNamesList);
        Team team = new Team();
        team.setName(teamName);
        for(String userName: userNamesList) {
            User user = userService.getUser(userName);
            user.setTeam(Optional.of(team));
            team.getUsers().add(user);
        }
        teamDao.createTeam(team);
    }

    private void validateUsers(List<String>userNamesList) {
        for(String userName: userNamesList) {
            if(userService.checkIfUserPartOfTeam(userName)) {
                throw new RuntimeException("User already in team, user name: " + userName);
            }
        }
    }
}
