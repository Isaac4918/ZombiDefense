package Logica;

public class Utilidades{

    public Utilidades() {
    }

    public float distancia(int x1, int y1, int x2, int y2){
        float compoX = Math.abs(x1 - x2);
        float compoY = Math.abs(y1- y2);
        float distancia = (float) Math.sqrt(Math.pow(compoX, 2) + Math.pow(compoY,2));
        return distancia;
    }

}
