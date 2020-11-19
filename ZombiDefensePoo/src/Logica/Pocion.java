package Logica;

public class Pocion extends Item {

    int vida = 20;

    public Pocion(String tipo, int vida) {
        super(tipo);
        this.vida = vida;
    }
}
