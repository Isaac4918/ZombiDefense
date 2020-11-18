package Logica;

import java.util.LinkedList;

public class Tablero{
    public Casilla[][] tablero = new Casilla[9][13];
    public int turno = 0;
    public LinkedList soldados = new LinkedList();
    public LinkedList zombies = new LinkedList();
    public Utilidades U = new Utilidades();

    public Tablero() throws InterruptedException {
        rellenar();
        generaSoldados();
        generaZombies(3);
        //mostrar();

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

    public void moverZombie(Personaje zombie, int x, int y){
        if(tablero[x][y].personaje == null){
            tablero[x][y].personaje = zombie;
            tablero[zombie.posX][zombie.posY].personaje = null;
            zombie.posX = x;
            zombie.posY = y;
        }else {
            moverZombie(zombie, x, y-1);
            /*tablero[x][y-1].personaje = zombie;
            tablero[zombie.posX][zombie.posY].personaje = null;
            zombie.posX = x;
            zombie.posY = y-1;*/
        }
    }

    public void generaSoldados(){
        int cont = 0;

        while(cont < 3){
            int x = ((cont+1) * 3)-1;
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

    public void generaZombies(int cantidad){
        int cont = 0;

        while(cont < cantidad){
            int x = (int) Math.floor(Math.random()*(9));
            int y = 0;

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

    public void turnoZombie(){

        //System.out.println("Largo de zombies: " + this.zombies.size());
        int cont = 0;

        while(cont < zombies.size()){
            Zombie tmp = (Zombie) zombies.get(cont);
            //System.out.println("Zombie " + cont + " | posx: " + tmp.posX + " | posy: " + tmp.posY);

            int[] target = objetivoAtaque(tmp);

            if(target != null){
                Soldado objetivo = (Soldado) tablero[target[0]][target[1]].personaje;
                tmp.atacar(objetivo);
                if(objetivo.vida <= 0){
                    soldados.remove(tablero[target[0]][target[1]].personaje);
                    tablero[target[0]][target[1]].personaje = null;
                }
                cont++;
                continue;
            }

            int[] destino = vision(tmp);

            if(destino != null){
                moverZombie(tmp, destino[0], destino[1]);
                cont++;
                /*System.out.println("Ví algo");
                System.out.println(destino[0] + ", " + destino[1]);*/
            }
            else{

                int[] destino2 = escuchar(tmp);

                if(destino2 != null){
                    moverZombie(tmp, destino2[0], destino2[1]);
                    cont++;
                    /*System.out.println("Escuché algo");
                    System.out.println(destino2[0] + ", " + destino2[1]);*/
                }

                else{

                    moverZombie(tmp, tmp.posX, tmp.posY + 3);
                    cont++;
                    /*System.out.println("Random");
                    System.out.println(tmp.posX + ", " + tmp.posY);*/
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

        LinkedList distancias = new LinkedList();
        float[] disXY = new float[3];

        int[] xy = new int[2];
        boolean visto = false;
        boolean Soldado = false;

        for(int i = 0; i< casillas.size(); i++){
            Casilla tmp = (Casilla) casillas.get(i);
            if(tmp.personaje != null){

                try{
                    if(tmp.personaje.getClass() == Soldado.class){
                        Soldado = true;
                    }

                }catch (NullPointerException e){
                }

                if(Soldado){
                    float dista = U.distancia(zombie.posX, zombie.posY, tmp.posX, tmp.posY);

                    disXY[0] = dista;
                    disXY[1] = tmp.posX;
                    disXY[2] = tmp.posY;

                    distancias.add(disXY);

                    visto = true;
                }
            }
        }

        int posMenor = 0;
        float disMenor = 10000;

        for(int a = 0; a < distancias.size(); a++){
            float[] tmp = (float[]) distancias.get(a);
            if(tmp[0] < disMenor){
                posMenor = a;
                disMenor = tmp[0];
            }
        }


        if(visto){
            float[] tmp = (float[]) distancias.get(posMenor);
            xy[0] = (int) tmp[1];
            xy[1] = (int) tmp[2];
        }
        else {
            xy = null;
        }

        return xy;
    }

    public int[] objetivoAtaque(Zombie zombie){
        LinkedList casillas = this.recorrer(zombie.posX, zombie.posY, zombie.rangoAtaque);

        int[] xy = new int[2];
        boolean atacable = false;
        boolean Soldado = false;

        for(int i = 0; i< casillas.size(); i++){
            Casilla tmp = (Casilla) casillas.get(i);
            if(tmp.personaje != null){

                try{
                    if(tmp.personaje.getClass() == Soldado.class){
                        Soldado = true;
                    }

                }catch (NullPointerException e){
                }

                if(Soldado){
                    xy[0] = tmp.posX;
                    xy[1] = tmp.posY;
                    atacable = true;
                    break;
                }
            }
        }

        if(!atacable){
            xy = null;
        }

        return xy;
    }

}
