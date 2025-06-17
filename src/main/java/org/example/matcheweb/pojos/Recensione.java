package org.example.matcheweb.pojos;

public class Recensione {
    private int ID;
    private int USER_ID;
    private int VOTO;
    private String COMMENTO;

public Recensione (){
    this.ID=0;
    this.USER_ID=0;
    this.VOTO=0;
    this.COMMENTO="";

}
public Recensione (int ID, int USER_ID, int VOTO, String COMMENTO){
    this.ID=ID;
    this.USER_ID=USER_ID;
    this.VOTO=VOTO;
    this.COMMENTO=COMMENTO;
}
    public int getID() {return ID;}
    public int getUSER_ID() {return USER_ID;}
    public int getVOTO() {return VOTO;}
    public String getCOMMENTO() {return COMMENTO;}
    public void setID(int ID) {this.ID = ID;}
    public void setUSER_ID(int USER_ID){this.USER_ID= USER_ID;}
    public void setVOTO(int VOTO){this.VOTO= this.VOTO;}
    public void setCOMMENTO(String COMMENTO){this.COMMENTO= this.COMMENTO;}


}
