package Logica;

public class Arma extends Item{

    int damage = 12;
    int rangoAtaque = 4;
    int ruido = 1;

    public Arma(String tipo) {
        super(tipo);
    }

    public Arma(String tipo, int damage, int rangoAtaque, int ruido) {
        super(tipo);
        this.damage = damage;
        this.rangoAtaque = rangoAtaque;
        this.ruido = ruido;
    }
}
