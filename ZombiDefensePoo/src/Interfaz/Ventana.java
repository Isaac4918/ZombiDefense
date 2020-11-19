package Interfaz;

import Logica.*;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

//ventana puede que tenga metodos que GestionUI le da para actualizar el Juego
public class Ventana extends JPanel {
    Graphics g1;
    private int clickX;
    private int clickY;
    public boolean ataque=false;
    public boolean mover=false;
    public boolean item=false;
    public String label_vida="";
    public String label_ataque="";
    public String label_rango="";
    public String label_nivel="";


    public int[] moving = {-10000, -10000};
    public int[] attacking = {-10000, -10000};


    Tablero T1 = new Tablero();

    LinkedList <Proyectiles> Heroes =new LinkedList();
    LinkedList <Proyectiles> Zombies =new LinkedList();
    LinkedList <Proyectiles> Balas =new LinkedList();



    public Ventana() throws InterruptedException {
        this.obtenerPersonajes();
        //label_vida.setFont(new Font("Franklin Gothic Heavy", Font.PLAIN, 14));

    }

    public int getSelec_x() {
        return selec_x;
    }

    public void setSelec_x(int selec_x) {
        this.selec_x = selec_x;
    }

    public int getSelec_y() {
        return selec_y;
    }

    public void setSelec_y(int selec_y) {
        this.selec_y = selec_y;
    }

    public int selec_x=0;
    public int selec_y=0;

    Proyectiles proyectil=new Proyectiles(2,0,0,4);


    public int getClickX() {
        return clickX;
    }

    public void setClickX(int clickX) {
        this.clickX = clickX;
    }

    public int getClickY() {
        return clickY;
    }

    public void setClickY(int clickY) {
        this.clickY = clickY;
    }



    public void paint(Graphics g){
        super.paint(g);
        ImageIcon img_fondo=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/fondo.png")).getImage());
        ImageIcon bala=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/bala.png")).getImage());
        ImageIcon tanque=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/tanque.gif")).getImage());
        ImageIcon sniper=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/sniper.gif")).getImage());
        ImageIcon explorer=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/explorer.gif")).getImage());
        ImageIcon zomb1=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/zomb1.gif")).getImage());
        ImageIcon zomb2=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/zomb2.gif")).getImage());
        ImageIcon zomb3=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/zomb3.gif")).getImage());
        ImageIcon carro=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/carro.png")).getImage());
        ImageIcon carro2=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/carro2.png")).getImage());
        ImageIcon barrera=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/barrera.png")).getImage());
        ImageIcon pocion=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/pocion.png")).getImage());
        ImageIcon ak=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/ak.png")).getImage());
        ImageIcon levelup=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/levelup.png")).getImage());
        ImageIcon selec=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/select.png")).getImage());
        ImageIcon batacar=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/batacar.png")).getImage());
        ImageIcon iitem=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/item.png")).getImage());
        ImageIcon imover=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/bmover.png")).getImage());
        ImageIcon atacarbn=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/atacarbn.png")).getImage());
        ImageIcon itembn=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/itembn.png")).getImage());
        ImageIcon moverbn=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/moverbn.png")).getImage());
        ImageIcon skipTurno=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/bterminar.png")).getImage());


        g.drawImage(img_fondo.getImage(),0,0,null);
        g.drawImage(selec.getImage(),Xtt(selec_x),Ytt(selec_y),null);
        g.drawString(label_vida,635,20);
        g.drawString(label_ataque,635,50);
        g.drawString(label_rango,635,80);
        g.drawString(label_nivel,635,110);
        //g.drawImage(bala.getImage(),proyectil.xi,proyectil.yi,null);






        //g.drawImage(imgzomb1.getImage(),115,98,null);
        //Botones de atacar,moverse y usar item---------
        if (clickX>643){
            if(clickY>162 && clickY<262 && ataque){
                System.out.println("ataking ratataata");
                this.Balas = new LinkedList();
                this.attacking[0] = selec_x;
                this.attacking[1] = selec_y;
                clickX=-100;
                clickY=-100;
                ataque=true;
            }
            if(clickY>287 && clickY<387 && mover){
                System.out.println("muving");
                this.moving[0] = selec_x;
                this.moving[1] = selec_y;
                System.out.println("Soldado " + " | posx: " + this.moving[0] + " | posy: " + this.moving[1]);
                clickX=-100;
                clickY=-100;
            }
            if(clickY>412 && clickY<512 && item){
                System.out.println("usando itemquisde");
                clickX=-100;
                clickY=-100;
            }
            if(clickY>537 && clickY<637){
                this.transformarZombies(T1.zombies);
                transformarHeroes(T1.soldados);
                System.out.println("pasado de verga");
                clickX=-100;
                clickY=-100;
            }


        }




        //----------------------------------------------


        if (clickX>56 && clickX<574){

            //prueba de seleccion de alguna casilla
            selec_x=ttX(clickX);
            selec_y=ttY(clickY);

            if(this.moving[0] > 0 && this.moving[1] > 0){
                Soldado tmp = (Soldado) T1.tablero[this.moving[0]][this.moving[1]].personaje;
                T1.moverSoldado(tmp, selec_x, selec_y);
                this.transformarHeroes(T1.soldados);
                this.moving[0] = -10000;
                this.moving[1] = -10000;
            }

            if(this.attacking[0] > 0 && this.attacking[1] > 0){
                Soldado tmp = (Soldado) T1.tablero[this.attacking[0]][this.attacking[1]].personaje;
                Zombie target = (Zombie) T1.tablero[selec_x][selec_y].personaje;

                Proyectiles bullet = new Proyectiles(this.attacking[0],this.attacking[1],target.posX,target.posY);
                this.Balas.add(bullet);

                T1.ataqueSolado(tmp,target);
                this.updateZombies(T1.zombies);
                this.attacking[0] = -10000;
                this.attacking[1] = -10000;


            }


            boolean soldado = false;

            Personaje tmp = T1.tablero[selec_x][selec_y].personaje;

            try{
                if(tmp.getClass() == Soldado.class){
                    this.mover = true;
                    this.ataque = true;
                    this.item = true;
                    label_vida="Vida: "+tmp.vida;
                    label_ataque="Ataque: "+((Soldado) tmp).arma.damage;
                    label_nivel="Nivel: "+tmp.nivel;
                    label_rango="Rango de movimiento : "+tmp.rangoMovimiento;
                    soldado = true;
                }
            }catch (NullPointerException e){
            }

            try{
                if(tmp.getClass() == Zombie.class){
                    label_vida="Vida: "+tmp.vida;
                    label_ataque="Ataque: "+ ((Zombie) tmp).damage;
                    label_nivel="Nivel: "+tmp.nivel;
                    label_rango="Rango de movimiento : "+tmp.rangoMovimiento;
                }
            }catch (NullPointerException e){
            }


            if(!soldado){
                this.mover = false;
                this.ataque = false;
                this.item = false;
            }

            System.out.println("en la matriz se va a poner en X: "+Xtt(selec_x)+" y: "+Ytt(selec_y));
            clickX=-100;
            clickY=-100;



/*
            // Se prueba el proyectil con coordenadas de inicio y final
            proyectil=new Proyectiles(2,0,0,12);
            Runnable proceso=proyectil;
            new Thread(proceso).start();
            clickX=-1;
*/




            //Se prueba el movimiento de los personajes




        }


        //g.drawImage(bala.getImage(),proyectil.xi,proyectil.yi,null);
        //System.out.println("equis :"+proyectil.xi);
        for(int i = 0; i<= Zombies.size()-1; i++){
            Proyectiles h= (Proyectiles) Zombies.get(i);
            Runnable aux = h;
            new Thread(h).start();

            if(h.tipo == "Tanque"){
                g.drawImage(zomb1.getImage(),h.xi,h.yi,null);
            }
            if(h.tipo == "Corredor"){
                g.drawImage(zomb2.getImage(),h.xi,h.yi,null);
            }
            if(h.tipo == "Berserker"){
                g.drawImage(zomb3.getImage(),h.xi,h.yi,null);
            }

        }

        for(int i = 0; i<= Balas.size()-1; i++){
            Proyectiles h= (Proyectiles) Balas.get(i);
            Runnable aux = h;
            if (h.xi==h.xf && h.yi==h.yf){
                h.xi=-1000;
                h.xf=-1000;
            }
            new Thread(h).start();
            g.drawImage(bala.getImage(),h.xi,h.yi,null);
        }

        for(int i = 0; i<= Heroes.size()-1; i++){
            Proyectiles h= (Proyectiles) Heroes.get(i);
            Runnable aux = h;
            new Thread(h).start();

            if(h.tipo == "Tanque"){
                g.drawImage(tanque.getImage(),h.xi,h.yi,null);
            }
            if(h.tipo == "Francotirador"){
                g.drawImage(sniper.getImage(),h.xi,h.yi,null);
            }
            if(h.tipo == "Explorador"){
                g.drawImage(explorer.getImage(),h.xi,h.yi,null);
            }
        }

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 13; j++){
                Casilla tmp = T1.tablero[i][j];
                int obs=tmp.obstaculo;

                if (obs==1){
                    g.drawImage(carro.getImage(), Xtt(tmp.posX),Ytt(tmp.posY),null);
                }
                if (obs==2){
                    g.drawImage(carro2.getImage(), Xtt(tmp.posX),Ytt(tmp.posY),null);
                }
                if (obs==3){
                    g.drawImage(barrera.getImage(), Xtt(tmp.posX),Ytt(tmp.posY),null);
                }
            }
        }

        //g.drawImage(pocion.getImage(),Xtt(0),Ytt(0),null);

        // Gestion de las imagenes de los botones

        if (ataque){
            g.drawImage(batacar.getImage(),635,162,null);
        }else{
            g.drawImage(atacarbn.getImage(),635,162,null);
        }

        if (mover){
            g.drawImage(imover.getImage(),635,287,null);
        }else{
            g.drawImage(moverbn.getImage(),635,287,null);
        }
        if (item){
            g.drawImage(iitem.getImage(),635,412,null);
        }else{
            g.drawImage(itembn.getImage(),635,412,null);
        }
        g.drawImage(skipTurno.getImage(),635,537,null);

        repaint();
    }
    public void mover(int dx,int dy){
        Proyectiles moviendoc= Zombies.get(0);
        System.out.println("xi :"+moviendoc.xi);
        moviendoc=new Proyectiles(ttX(moviendoc.xi),ttY(moviendoc.yi),ttX(moviendoc.xi),ttY(moviendoc.yi)-2);

        Runnable proceso=moviendoc;
        new Thread(proceso).start();
        Zombies.set(0,moviendoc);
        clickX=-100;
    }

    public int Xtt(int x){
        return ((x*57)+50);
    }
    public int Ytt(int y){
        return ((y*50)+1);
    }
    public int ttX(int x){
        return (x-65)/56;
    }
    public int ttY(int y){
        return (y-36)/50;
    }


    public void obtenerPersonajes(){
        LinkedList Soldados = this.T1.soldados;
        LinkedList Zombies = this.T1.zombies;

        for(int i = 0; i < Soldados.size(); i++){
            Soldado tmp = (Soldado) Soldados.get(i);
            Proyectiles heroe1 = new Proyectiles(tmp.posX,tmp.posY,tmp.posX,tmp.posY);
            heroe1.tipo = tmp.tipo;
            this.Heroes.add(heroe1);
        }
        for(int i = 0; i < Zombies.size(); i++){
            Zombie tmp = (Zombie) Zombies.get(i);
            Proyectiles zombie = new Proyectiles(tmp.posX,tmp.posY,tmp.posX,tmp.posY);
            zombie.tipo = tmp.tipo;
            this.Zombies.add(zombie);
        }
    }

    public void transformarHeroes(LinkedList Soldados){
        this.Heroes = new LinkedList();
        for(int i = 0; i < Soldados.size(); i++){
            Soldado tmp = (Soldado) Soldados.get(i);
            Proyectiles heroe1 = new Proyectiles(tmp.posX,tmp.posY,tmp.posX,tmp.posY);
            heroe1.tipo = tmp.tipo;
            this.Heroes.add(heroe1);
        }
    }

    public void updateZombies(LinkedList listaZombies){
        this.Zombies = new LinkedList();
        for(int i = 0; i < listaZombies.size(); i++){
            Zombie tmp = (Zombie) listaZombies.get(i);
            Proyectiles zombie = new Proyectiles(tmp.posX,tmp.posY,tmp.posX,tmp.posY);
            zombie.tipo = tmp.tipo;
            this.Zombies.add(zombie);
        }
    }

    public void transformarZombies(LinkedList zombies){
        this.Zombies = new LinkedList();
        LinkedList aux = new LinkedList();

        for(int a = 0; a < zombies.size(); a++){
            int[] xy = new int[2];
            Zombie tmp = (Zombie) zombies.get(a);
            xy[0] = tmp.posX;
            xy[1] = tmp.posY;
            //System.out.println("[" + xy[0] + ", " + xy[1] + "]");
            aux.add(xy);
        }

        try {
            T1.cambiarTurno();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < zombies.size(); i++){
            Zombie tmp = (Zombie) zombies.get(i);
            int[] xyInicial = (int[]) aux.get(i);
            /*System.out.println("============================================");
            System.out.println(xyInicial);
            System.out.println("Largo: " + aux.size());
            System.out.println("[" + xyInicial[0] + ", " + xyInicial[1] + "]");*/
            Proyectiles heroe1 = new Proyectiles(xyInicial[0],xyInicial[1],tmp.posX,tmp.posY);
            heroe1.tipo = tmp.tipo;
            this.Zombies.add(heroe1);

        }

        //System.out.println("Zombies: " + this.heroes.size());




    }


}
