package Logica;

import java.util.LinkedList;

public class Personaje {

    public int vida = 100;
    public int velocidad = 10;
    public int armadura = 10;
    public int posX = 0;
    public int posY = 0;
    int nivel = 1;
    public int rangoMovimiento = 3;
    LinkedList habilidades = new LinkedList();
    LinkedList items = new LinkedList();


    public Personaje() {
    }

    public Personaje(int vida, int velocidad, int armadura, int posX, int posY, int rangoMovimiento) {
        this.vida = vida;
        this.velocidad = velocidad;
        this.armadura = armadura;
        this.posX = posX;
        this.posY = posY;
        this.rangoMovimiento = rangoMovimiento;
    }

    public void moverse(int x, int y){
        this.posX = x;
        this.posY = y;
    }

    public void recibirDmg(int damage){
        this.vida -= damage;
    }

}
