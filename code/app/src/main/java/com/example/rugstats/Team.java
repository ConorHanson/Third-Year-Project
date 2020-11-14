package com.example.rugstats;

public class Team {

    private String Name;
    private String Coach;
    private String Age;
    private Integer Turnover;
    private String Match;


    public Team() {

    }
//Get the Match string
    public String getMatch() {
        return Match;
    }
//Set match to the string
    public void setMatch(String match) {
        Match = match;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getTo() {
        return Turnover;
    }

    public void setTo(Integer turnover_won) {
        Turnover = turnover_won;
    }

    public String getCoach() {
        return Coach;
    }

    public void setCoach(String coach) {
        Coach = coach;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }
}
