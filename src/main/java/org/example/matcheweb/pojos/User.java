package org.example.matcheweb.pojos;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String role;
    private Date birthdate;
    private String sport;
    private String squadra;
    private int punti;
    private int rank;
    private int numschedine;
    private List<String> premi;

    public User() {
        this.numschedine = 0;
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.username = "";
        this.password = "";
        this.role = "";
        this.birthdate = null;
        this.sport = "";
        this.squadra = "";
        this.punti = 0;
        this.rank = 0;
        this.premi = List.of();
    }

    public User(String firstName, String lastName, String email, String username, String password, String role, Date birthdate, String sport, String squadra) {
        this.numschedine = 0;
        this.id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.birthdate = birthdate;
        this.sport = sport;
        this.squadra = squadra;
        this.punti = 0;
        this.rank = 0;
    }

    public int getId() {return id;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getEmail() {return email;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getRole() {return role;}
    public Date getBirthdate() {return birthdate;}
    public String getsport() {return sport;}
    public String getsquadra() {return squadra;}
    public int getPunti() {return punti;}
    public int getRank() {return rank;}
    public int getNumschedine() {return numschedine;}
    public List<String> getPremi() {return premi;}

    public void setPremi(List<String> premi) {this.premi = premi;}
    public void setId(int id) {this.id = id;}
    public void setNome(String firstName) {this.firstName = firstName;}
    public void setCognome(String lastName) {this.lastName = lastName;}
    public void setEmail(String email) {this.email = email;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public void setRole(String role) {this.role = role;}
    public void setBirthdate(Date birthdate) {this.birthdate = birthdate;}
    public void setSport(String sport) {this.sport = sport;}
    public void setSquadra(String squadra) {this.squadra = squadra;}
    public void setPunti(int points) {this.punti = points;}
    public void setRank(int rank) {this.rank = rank;}
    public void setNumschedine(int numschedine) {this.numschedine = numschedine;}
}
