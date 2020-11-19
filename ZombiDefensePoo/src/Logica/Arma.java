package Logica;

public class Arma extends Item{

    public int damage = 12;
    public int rangoAtaque = 4;
    public int ruido = 1;

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
