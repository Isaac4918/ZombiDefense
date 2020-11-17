package Logica;

import java.util.LinkedList;

public class Tablero{
    Casilla[][] tablero = new Casilla[9][13];
    int turno = 0;
    LinkedList soldados = new LinkedList();
    LinkedList zombies = new LinkedList();

    public Tablero() throws InterruptedException {
        rellenar();
        generaSoldados();
        generaZombies();
        mostrar();

        /*while(true){
            Thread.sleep(3000);
            if(turno%2 == 0){
                turnoZombie();
                mostrar();
            }
            turno++;
        }*/
    }

    public void rellenar(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 13; j++){
                Casilla tmp = new Casilla();
                tmp.posX = i;
                tmp.posY = j;
                this.tablero[i][j] = tmp;
            }
        }
    }

    public void moverse(Personaje objetivo, int x, int y){
        tablero[x][y].personaje = tablero[objetivo.posX][objetivo.posY].personaje;
        tablero[objetivo.posX][objetivo.posY].personaje = null;
        objetivo.posX = x;
        objetivo.posY = y;
    }

    public void generaSoldados(){
        int cont = 0;

        while(cont < 3){
            int x = (int) Math.floor(Math.random()*(9));
            int y = (int) Math.floor(Math.random()*(12-10+1)+10);

            if(tablero[x][y].personaje == null) {
                Soldado tmp = new Soldado();
                tablero[x][y].personaje = tmp;
                tmp.posX = x;
                tmp.posY = y;
                this.soldados.add(tmp);
                cont++;
            }
        }
    }

    public void generaZombies(){
        int cont = 0;

        while(cont < 6){
            int x = (int) Math.floor(Math.random()*(9));
            int y = (int) Math.floor(Math.random()*(3));

            if(tablero[x][y].personaje == null) {
                Zombie tmp = new Zombie();
                tablero[x][y].personaje = tmp;
                tmp.posX = x;
                tmp.posY = y;
                this.zombies.add(tmp);
                cont++;
            }
        }
    }

    public void mostrar(){
        String linea = "";

        boolean Soldado = false;
        boolean Zombie = false;
        System.out.println("=============================");

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 13; j++){
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

    public void turnoSoldado(){

    }

    public void turnoZombie(){
        int cont = 0;

        while(cont < zombies.size()){
            Zombie tmp = (Zombie) zombies.get(cont);

            int[] destino = escuchar(tmp);

            if(destino != null){
                moverse(tmp, destino[0], destino[1]);
                cont++;
            }
            else{
                int[] destino2 = vision(tmp);

                if(destino2 != null){
                    moverse(tmp, destino2[0], destino2[1]);
                    cont++;
                }

                else {
                    int x = (int) Math.floor(Math.random()*(3-(-3)+1)+(-3));
                    int y = (int) Math.floor(Math.random()*(3)+1);

                    int xFinal = tmp.posX + x;
                    int yFinal = tmp.posY + y;

                    if(0<=xFinal & xFinal<9 & yFinal<13){
                        if(tablero[xFinal][yFinal].personaje == null){
                            this.moverse(tmp, xFinal, yFinal);
                            cont++;
                        }
                    }
                }

            }
        }
    }

    public LinkedList recorrer(int x,int y,int rango){
        LinkedList casillas=new LinkedList();
        for (int i=x-rango;i<=x+rango;i++){
            if (i>=9 || i<=-1){
                continue;
            }
            for (int j=y-rango;j<=y+rango;j++){
                if (j>=13 || j<=-1){
                    continue;
                }
                Casilla tmp = tablero[i][j];
                if (i==x && i==j){
                    continue;
                }
                casillas.add(tmp);
            }
        }
        /*for(int i = 0; i < casillas.size(); i++){
            System.out.println("=====");
            Casilla tmp = (Casilla) casillas.get(i);
            System.out.println(tmp.ruido);
        }*/
        return casillas;
    }

    public int[] escuchar(Zombie zombie){
        LinkedList casillas = this.recorrer(zombie.posX, zombie.posY, zombie.rangoEscucha);

        int[] xy = new int[2];
        boolean ruido = false;

        for(int i = 0; i< casillas.size(); i++){
            Casilla tmp = (Casilla) casillas.get(i);
            if(tmp.ruido){
                xy[0] = tmp.posX;
                xy[1] = tmp.posY;
                ruido = true;
                break;
            }
        }

        if(!ruido){
            xy = null;
        }

        return xy;
    }

    public int[] vision(Zombie zombie){
        LinkedList casillas = this.recorrer(zombie.posX, zombie.posY, zombie.rangoVision);

        int[] xy = new int[2];
        boolean visto = false;

        for(int i = 0; i< casillas.size(); i++){
            Casilla tmp = (Casilla) casillas.get(i);
            if(tmp.personaje != null){
                xy[0] = tmp.posX;
                xy[1] = tmp.posY;
                visto = true;
                break;
            }
        }

        if(!visto){
            xy = null;
        }

        return xy;
    }

}
