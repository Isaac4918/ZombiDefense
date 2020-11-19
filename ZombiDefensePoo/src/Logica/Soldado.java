package Logica;

import java.util.LinkedList;

public class Soldado extends Personaje{

    public Arma arma = new Arma("Arma Larga", 50, 5,  4);

    public Soldado() {
    }

    public Soldado(int vida, int posX, int posY, int rangoMovimiento, Arma arma) {
        super(vida, posX, posY, rangoMovimiento);
        this.arma = arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public void usarHabilidad(int index){

    }

    public void usaItem(int index){

    }

}
