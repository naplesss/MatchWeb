package org.example.matcheweb.pojos;

public class Recensione {
    private int id;
    private int userId;
    private int voto;
    private String commento;

public Recensione (){
    this.id=0;
    this.userId=0;
    this.voto=0;
    this.commento="";

}
public Recensione (int id, int userId, int voto, String commento){
    this.id=id;
    this.userId=userId;
    this.voto=voto;
    this.commento=commento;
}
    public int getId() {return id;}
    public int getUserId() {return userId;}
    public int getVoto() {return voto;}
    public String getCommento() {return commento;}
    public void setId(int id) {this.id = id;}
    public void setUserId(int userId){this.userId=userId;}
    public void setVoto(int voto){this.voto=voto;}
    public void setCommento(String commento){this.commento=commento;}


}
