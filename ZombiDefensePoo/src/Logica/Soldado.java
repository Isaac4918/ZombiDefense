package Logica;

import java.util.LinkedList;

public class Soldado extends Personaje{

    //private Arma arma;


    public Soldado(int vida, int velocidad, int armadura, int posX, int posY, int rangoMovimiento) {
        super(vida, velocidad, armadura, posX, posY, rangoMovimiento);
    }

    /*public void setArma(Arma arma) {
        this.arma = arma;
    }*/

    public void usarHabilidad(int index){

    }

    public void usaItem(int index){

    }

    /*public void atacar(Personaje objetivo){
        objetivo.recibirDmg(this.arma.damage);
    }*/
}
