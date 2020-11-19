package Logica;

import java.util.LinkedList;

public class Personaje{

    public int vida = 100;
    public int posX = 0;
    public int posY = 0;
    public int nivel = 1;
    public int rangoMovimiento = 3;
    public LinkedList items = new LinkedList();
    public String tipo = "";


    public Personaje() {
    }

    public Personaje(int vida, int posX, int posY, int rangoMovimiento) {
        this.vida = vida;
        this.posX = posX;
        this.posY = posY;
        this.rangoMovimiento = rangoMovimiento;
    }

    public void recibirDmg(int damage){
        this.vida -= damage;
    }

}
