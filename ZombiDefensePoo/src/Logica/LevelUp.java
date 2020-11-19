package Logica;

public class LevelUp extends Item{
    int level = 1;

    public LevelUp(String tipo, int level) {
        super(tipo);
        this.level = level;
    }
}
