package Logica;

import java.util.LinkedList;

public class Utilidades{

    public Utilidades() {
    }

    public float distancia(int x1, int y1, int x2, int y2){
        if(x1 == x2 && y1 == y2){
            return 0;
        }else{
            float compoX = Math.abs(x1 - x2);
            float compoY = Math.abs(y1- y2);
            float distancia = (float) Math.sqrt(Math.pow(compoX, 2) + Math.pow(compoY,2));
            return distancia;
        }
    }

    public Soldado masCerca(LinkedList soldados, Personaje personaje){
        int posMenor = 0;
        float distMenor = 100000;

        for(int a = 0; a < soldados.size(); a++){
            Soldado tmp = (Soldado) soldados.get(a);
            float distancia = this.distancia(personaje.posX, personaje.posY, tmp.posX, tmp.posY);
            if(distancia < distMenor){
                distMenor = distancia;
                posMenor = a;
            }
        }

        return (Soldado) soldados.get(posMenor);
    }

}
