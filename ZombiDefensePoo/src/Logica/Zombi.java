package Logica;

import java.util.LinkedList;

public class Zombi extends Personaje{
    int rangoAtaque;
    int rangoEscucha;
    int damage;

    public Zombi(int vida, int velocidad, int armadura, int posX, int posY, int rangoMovimiento, int rangoAtaque, int rangoEscucha, int damage) {
        super(vida, velocidad, armadura, posX, posY, rangoMovimiento);
        this.rangoAtaque = rangoAtaque;
        this.rangoEscucha = rangoEscucha;
        this.damage = damage;
    }

    public void soltarObjeto(){

    }
}
