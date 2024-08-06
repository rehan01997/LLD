package dao;

import entity.Team;

import java.util.HashMap;
import java.util.Map;

public class TeamDao {

    Map<String, Team> teamMap = new HashMap<>(); // name, team

    public Team getTeam(String teamName) {
        if(!teamMap.containsKey(teamName)) {
            throw new RuntimeException("Team does not exist for team name " + teamName);
        }
        return teamMap.get(teamName);
    }

    public void createTeam(Team team) {
        if(teamMap.containsKey(team.getName())) {
            throw new RuntimeException("Team already exist for team name: " + team.getName());
        }
        teamMap.put(team.getName(), team);
    }
}
