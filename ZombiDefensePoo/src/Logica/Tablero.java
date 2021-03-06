package Logica;

import java.util.LinkedList;

public class Tablero{
    public Casilla[][] tablero = new Casilla[9][13];
    public int turno = 0;
    public LinkedList soldados = new LinkedList();
    public LinkedList zombies = new LinkedList();
    public LinkedList items = new LinkedList();
    public Utilidades U = new Utilidades();

    public int cantidad = 3;

    public Tablero() throws InterruptedException {
        rellenar();
        generaItem();
        generaObstaculos(4);
        generaSoldados();
        generaZombies(this.cantidad);
        cambiarTurno();
    }

    //=============================================================Manejo del juego==========================================================

    public void cambiarTurno() throws InterruptedException {
        if(turno == 0){
            turno = 1;
            if(this.zombies.size() == 0){
                for(int i = 0; i< this.soldados.size(); i++){
                    Soldado tmp = (Soldado) soldados.get(i);
                    tmp.subirNivel();
                }
            }
        }
        else if(turno == 1){
            for(int i = 0; i< this.soldados.size(); i++){
                Soldado tmp = (Soldado) soldados.get(i);
                tmp.movio = false;
                tmp.ataco = false;
                tmp.usoItem = false;
            }
            turnoZombie();
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 13; j++){
                    this.tablero[i][j].ruido = false;
                }
            }
            turno = 0;
            Thread.sleep(1000);
            cambiarTurno();
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

    public void generaItem(){
        Pocion pocion = new Pocion("Poción", 25);
        LevelUp level = new LevelUp("Level", 1);
        this.items.add(pocion);
        this.items.add(level);
    }

    public void generaObstaculos(int cantidad){
        int cont = 0;
        while (cont < cantidad){
            int x = (int) Math.floor(Math.random()*(9));
            int y = (int) Math.floor(Math.random()*(13));

            int obs = (int) Math.floor(Math.random()*(4));
            this.tablero[x][y].obstaculo = obs;
            cont++;
        }

    }

    //================================================================Soldados================================================================


    public void generaSoldados(){
        int cont = 0;

        while(cont < 3){
            int x = ((cont+1) * 3)-1;
            int y = (int) Math.floor(Math.random()*(12-10+1)+10);

            if(tablero[x][y].personaje == null && tablero[x][y].obstaculo == 0) {

                if(cont == 0){
                    Arma weapon = new Arma("Arma Corta", 60, 1, 1);
                    Soldado tmp = new Soldado(160, x, y, 3, weapon);
                    tmp.tipo = "Tanque";
                    tablero[x][y].personaje = tmp;
                    this.soldados.add(tmp);
                    cont++;

                }else if(cont == 1){
                    Arma weapon = new Arma("Arma Larga", 70, 6, 6);
                    Soldado tmp = new Soldado(75, x, y, 4, weapon);
                    tmp.tipo = "Francotirador";
                    tablero[x][y].personaje = tmp;
                    this.soldados.add(tmp);
                    cont++;

                }else if(cont == 2){
                    Arma weapon = new Arma("Arma media", 35, 4, 4);
                    Soldado tmp = new Soldado(100, x, y, 5, weapon);
                    tmp.tipo = "Explorador";
                    tablero[x][y].personaje = tmp;
                    this.soldados.add(tmp);
                    cont++;
                }
            }
        }
    }

    public void moverSoldado(Soldado actual, int xFinal, int yFinal){

        float distancia = U.distancia(actual.posX, actual.posY, xFinal, yFinal);

        if(actual.rangoMovimiento >= distancia){
            if(tablero[xFinal][yFinal].personaje == null){
                if(tablero[xFinal][yFinal].obstaculo == 0){
                    tablero[xFinal][yFinal].personaje = actual;
                    tablero[actual.posX][actual.posY].personaje = null;
                    actual.posX = xFinal;
                    actual.posY = yFinal;
                    actual.movio = true;
                    if(tablero[xFinal][yFinal].item != null){
                        actual.recogeItem(tablero[xFinal][yFinal].item);
                        tablero[xFinal][yFinal].item = null;
                    }
                }
                if(tablero[xFinal][yFinal].obstaculo != 0){
                    if(actual.escalar){
                        tablero[xFinal][yFinal].personaje = actual;
                        tablero[actual.posX][actual.posY].personaje = null;
                        actual.posX = xFinal;
                        actual.posY = yFinal;
                        actual.movio = true;
                        if(tablero[xFinal][yFinal].item != null){
                            actual.recogeItem(tablero[xFinal][yFinal].item);
                            tablero[xFinal][yFinal].item = null;
                        }
                    }else{
                        System.out.println("Hay un obstáculo");
                    }
                }


            }else {
                System.out.println("Posición ocupada");
            }
        }
        else{
            System.out.println("Fuera del rango de movimiento");
        }



    }

    public void ataqueSolado(Soldado actual, Zombie objetivo){
        float distance = U.distancia(actual.posX, actual.posY, objetivo.posX, objetivo.posY);
        if(actual.arma.rangoAtaque >= distance){
            objetivo.recibirDmg(actual.arma.damage);
            actual.ataco = true;
            if(objetivo.vida <= 0){
                int chanceItem = (int) Math.floor(Math.random()*(101));
                if(chanceItem < 100){
                    objetivo.soltarObjeto(this.items, this.tablero);
                }
                zombies.remove(tablero[objetivo.posX][objetivo.posY].personaje);
                tablero[objetivo.posX][objetivo.posY].personaje = null;


            }
            LinkedList extenderRuido = recorrer(actual.posX, actual.posY, actual.arma.ruido);
            for(int i = 0; i < extenderRuido.size(); i++){
                Casilla tmp = (Casilla) extenderRuido.get(i);
                tmp.ruido = true;
            }

        }

    }


    //================================================================Zombies=================================================================

    public void generaZombies(int cantidad){
        int cont = 0;

        while(cont < cantidad){
            int x = (int) Math.floor(Math.random()*(9));
            int y = 0;

            if(tablero[x][y].personaje == null && tablero[x][y].obstaculo == 0) {

                if(cont == 0 || cont == 3 || cont == 6){

                    Zombie tmp = new Zombie(150, x, y, 2, 1, 3, 5, 60);
                    tmp.tipo = "Tanque";
                    tablero[x][y].personaje = tmp;
                    this.zombies.add(tmp);
                    cont++;

                }else if(cont == 1 || cont == 4 || cont == 7){
                    Zombie tmp = new Zombie(70, x, y, 5, 1, 2, 4, 40);
                    tmp.tipo = "Corredor";
                    tablero[x][y].personaje = tmp;
                    this.zombies.add(tmp);
                    cont++;

                }else if(cont == 2 || cont == 5 || cont == 8){
                    Zombie tmp = new Zombie(100, x, y, 3, 2, 4, 5, 70);
                    tmp.tipo = "Berserker";
                    tablero[x][y].personaje = tmp;
                    this.zombies.add(tmp);
                    cont++;
                }


            }
        }
    }

    public void turnoZombie(){

        //System.out.println("Largo de zombies: " + this.zombies.size());
        int cont = 0;

        while(cont < zombies.size()){
            Zombie tmp = (Zombie) zombies.get(cont);
            //System.out.println("Zombie " + cont + " | posx: " + tmp.posX + " | posy: " + tmp.posY);

            //===============Ataque=========================

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

            //===============Vision=========================

            int[] destino = vision(tmp);

            if(destino != null){
                moverZombie(tmp, destino[0], destino[1]);
                cont++;
                System.out.println("Ví algo");
                System.out.println("Zombie " + cont + " | posx: " + destino[0] + " | posy: " + destino[1]);
            }

            //===============Escucha=========================
            else{

                int[] destino2 = escuchar(tmp);

                if(destino2 != null){
                    moverZombie(tmp, destino2[0], destino2[1]);
                    cont++;
                    System.out.println("Escuché algo");
                    System.out.println(destino2[0] + ", " + destino2[1]);
                }

                //===============Random=========================
                else{

                    moverZombie(tmp, tmp.posX, tmp.posY + 2);
                    cont++;
                    System.out.println("Random");
                    System.out.println(tmp.posX + ", " + tmp.posY);
                }

            }

        }
        this.mostrar();
    }

    public void moverZombie(Personaje zombie, int xf, int yf){

        int xi = zombie.posX;
        int yi = zombie.posY;

        float dist = U.distancia(xi,yi,xf,yf);

        if(yf >= 13){
            this.zombies.remove(zombie);
            tablero[zombie.posX][zombie.posY].personaje = null;
        }

        if(zombie.rangoMovimiento >= dist){
            if(tablero[xf][yf].personaje == null && tablero[xf][yf].obstaculo == 0){
                while ((xi!=xf) || (yi!=yf)){

                    if (xi<xf){
                        xi++;
                    }

                    if (xi>xf){
                        xi--;
                    }

                    if (yi<yf){
                        yi++;
                    }

                    if (yi>yf){
                        yi--;
                    }
                }

                tablero[xi][yi].personaje = zombie;
                tablero[zombie.posX][zombie.posY].personaje = null;
                zombie.posX = xi;
                zombie.posY = yi;
            }
            else {
                moverZombie(zombie, xf, yf-1);
            }
        }
        else{
            if(tablero[xf][yf].personaje == null){
                int cont = 0;
                while (((xi!=xf) || (yi!=yf)) && (cont < zombie.rangoMovimiento)){

                    if (xi<xf){
                        xi++;
                        cont++;
                    }

                    if (xi>xf){
                        xi--;
                        cont++;
                    }

                    if (yi<yf){
                        yi++;
                        cont++;
                    }

                    if (yi>yf){
                        yi--;
                        cont++;
                    }
                }

                tablero[xi][yi].personaje = zombie;
                tablero[zombie.posX][zombie.posY].personaje = null;
                zombie.posX = xi;
                zombie.posY = yi;
            }

            else {
                moverZombie(zombie, xf, yf-1);
            }
        }
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
        LinkedList soldadosVistos = new LinkedList();

        boolean visto = false;
        int[] xy = new int[2];

        for(int i = 0; i < casillas.size(); i++){
            Casilla tmp = (Casilla) casillas.get(i);
            if(tmp.personaje != null){
                try{
                    if(tmp.personaje.getClass() == Soldado.class){
                        soldadosVistos.add(tmp.personaje);
                        visto = true;
                    }
                }catch (NullPointerException e){
                }
            }
        }

        Soldado cercano = new Soldado();

        if(soldadosVistos.size() != 0){
            cercano = U.masCerca(soldadosVistos, zombie);
        }

        if(visto){
            xy[0] = cercano.posX;
            xy[1] = cercano.posY;
        }else{
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
