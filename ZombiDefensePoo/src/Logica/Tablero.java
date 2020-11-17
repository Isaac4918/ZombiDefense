package Logica;

import java.util.LinkedList;

public class Tablero{
    Casilla[][] tablero = new Casilla[13][9];
    int turno = 0;
    LinkedList soldados = new LinkedList();
    LinkedList zombies = new LinkedList();

    public Tablero() throws InterruptedException {
        rellenar();
        generaSoldados();
        mostrar();

        while(true){
            if(turno%2 == 0){
                generaZombies();
                mostrar();
            }
            
            Thread.sleep(3000);
            turno++;
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

        while(cont < 3){
            int x = (int) Math.floor(Math.random()*(12-10+1)+10);
            int y = (int) Math.floor(Math.random()*(9));

            if(tablero[x][y].personaje == null) {
                tablero[x][y].personaje = new Soldado();
                cont++;
            }
        }
    }

    public void generaZombies(){
        int cont = 0;

        while(cont < 6){
            int x = (int) Math.floor(Math.random()*(3));
            int y = (int) Math.floor(Math.random()*(9));

            if(tablero[x][y].personaje == null) {
                tablero[x][y].personaje = new Zombie();
                cont++;
            }
        }
    }

    public void mostrar(){
        String linea = "";

        boolean Soldado = false;
        boolean Zombie = false;
        System.out.println("=============================");

        for(int i = 0; i < 13; i++){
            for(int j = 0; j < 9; j++){
                try{
                    if(tablero[i][j].personaje.getClass() == Soldado.class){
                        Soldado = true;
                    }

                }catch (NullPointerException e){
                        Soldado = false;
                }

                try{
                    if(tablero[i][j].personaje.getClass() == Zombie.class){
                        Zombie = true;
                    }

                }catch (NullPointerException e){
                    Zombie = false;
                }


                if(tablero[i][j].personaje != null & Soldado){
                    linea += "1  ";
                }

                else if(tablero[i][j].personaje != null & Zombie){
                    linea += "2  ";
                }

                else{
                    linea+= "0  ";
                }
            }
            System.out.println(linea);
            linea = "";
        }
        System.out.println("=============================");
    }


}
