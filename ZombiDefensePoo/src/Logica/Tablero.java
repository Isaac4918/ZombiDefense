package Logica;

import java.util.LinkedList;

public class Tablero{
    Casilla[][] tablero = new Casilla[13][9];
    int turno = 0;
    LinkedList soldados = new LinkedList();
    LinkedList zombies = new LinkedList();

    public Tablero() {
        rellenar();

        while(true){

        }
    }

    public void rellenar(){
        for(int i = 0; i < 13; i++){
            for(int j = 0; j < 9; j++){
                Casilla tmp = new Casilla();
                this.tablero[i][j] = tmp;
            }
        }
    }

    public void moverse(Personaje objetivo, int x, int y){
        tablero[x][y].personaje = tablero[objetivo.posX][objetivo.posY].personaje;
        tablero[objetivo.posX][objetivo.posY].personaje = null;
    }

    public void generaSoldados(){
        int cont = 0;

        while(cont < 4){
            cont++;
        }
    }


}
