package Logica;

import java.util.LinkedList;

public class Zombie extends Personaje{
    public int rangoAtaque = 1;
    public int rangoEscucha = 4;
    public int rangoVision = 3;
    public int damage = 34;

    public Zombie() {
    }

    public Zombie(int vida, int posX, int posY, int rangoMovimiento, int rangoAtaque, int rangoEscucha, int rangoVision, int damage) {
        super(vida, posX, posY, rangoMovimiento);
        this.rangoAtaque = rangoAtaque;
        this.rangoEscucha = rangoEscucha;
        this.rangoVision = rangoVision;
        this.damage = damage;
    }

    public void soltarObjeto(LinkedList items, Casilla[][] tablero){
        int indice = (int) Math.floor(Math.random()*(items.size()));

        Item tmp = (Item) items.get(indice);
        Casilla casillaActual = tablero[this.posX][this.posY];
        casillaActual.item = tmp;

    }

    public void atacar(Soldado objetivo){
        objetivo.recibirDmg(this.damage);
    }



}
