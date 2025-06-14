package org.example.matcheweb.pojos;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String role;
    private String birthdate;
    private String sport;
    private String squadra;
    private int points;
    private int rank;
    public User() {
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.username = "";
        this.password = "";
        this.role = "";
        this.birthdate = "";
        this.sport = "";
        this.squadra = "";
        this.points = 0;
        this.rank = 0;
    }

    public User(String firstName, String lastName, String email, String username, String password, String role, String birthdate, String sport, String squadra) {
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
        this.points = 0;
        this.rank = 0;
    }

    public int getId() {return id;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getEmail() {return email;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getRole() {return role;}
    public String getBirthdate() {return birthdate;}
    public String getsport() {return sport;}
    public String getsquadra() {return squadra;}
    public int getPunti() {return points;}
    public int getClassifica() {return rank;}

    public void setId(int id) {this.id = id;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setEmail(String email) {this.email = email;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public void setRole(String role) {this.role = role;}
    public void setBirthdate(String birthdate) {this.birthdate = birthdate;}
    public void setSport(String sport) {this.sport = sport;}
    public void setSquadra(String squadra) {this.squadra = squadra;}
    public void setPunti(int points) {this.points = points;}
    public void setClassifica(int rank) {this.rank = rank;}
}
