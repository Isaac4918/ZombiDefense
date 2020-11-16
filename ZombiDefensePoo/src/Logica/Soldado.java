package Logica;

import java.util.LinkedList;

public class Soldado extends Personaje{

    private Arma arma;
    Utilidades U = new Utilidades();


    public Soldado(int vida, int velocidad, int armadura, int posX, int posY, int rangoMovimiento) {
        super(vida, velocidad, armadura, posX, posY, rangoMovimiento);
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public void usarHabilidad(int index){

    }

    public void usaItem(int index){

    }

    public void atacar(Personaje objetivo){
        float distance = U.distancia(this.posX, this.posY, objetivo.posX, objetivo.posY);
        if(arma.rangoAtaque >= distance){
            objetivo.recibirDmg(this.arma.damage);
        }
    }
}
