package Logica;

import java.util.LinkedList;

public class Soldado extends Personaje{

    public Arma arma = new Arma("Arma Larga", 50, 5,  4);
    public boolean ataco = false;
    public boolean movio = false;
    public boolean usoItem = false;
    public boolean escalar = false;

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

    public void usaItem(){
        if(this.items.size() >0){
            Item tmp = (Item) this.items.get(0);
            if(tmp.tipo.equals("Poción")){
                Pocion aux = (Pocion) tmp;
                this.vida += aux.vida;
                this.items.remove(0);
            }
            else if(tmp.tipo.equals("Level")){
                LevelUp aux = (LevelUp) tmp;
                this.nivel += aux.level;
                this.items.remove(0);
            }
            this.usoItem = true;
        }else {
            System.out.println("Sin items");
        }
    }

    public void recogeItem(Item item){
        this.items.add(item);
    }


    public void subirNivel(){
        this.nivel++;
        if(this.tipo == "Tanque"){
            this.vida = (int) (vida*1.20);
            this.arma.damage = (int) (this.arma.damage*1.05);
        }
        if(this.tipo == "Francotirador"){
            this.vida = (int) (vida*1.05);
            this.arma.damage = (int) (this.arma.damage*1.15);
            this.arma.rangoAtaque = (int) (this.arma.rangoAtaque*1.20);
        }
        if(this.tipo == "Explorador"){
            this.vida = (int) (this.vida*1.30);
            this.arma.damage = (int) (this.arma.damage*1.02);
            this.rangoMovimiento = (int) (this.rangoMovimiento*1.20);
            this.escalar = true;
        }
    }

}
