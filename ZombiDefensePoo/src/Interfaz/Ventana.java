package Interfaz;

import Logica.Tablero;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Vector;
import Logica.Tablero;
import Logica.Zombie;

//ventana puede que tenga metodos que GestionUI le da para actualizar el Juego
public class Ventana extends JPanel {
    Graphics g1;
    private int clickX;
    private int clickY;
    public boolean ataque=true;
    public boolean mover=true;
    public boolean item=true;
    Tablero T1 = new Tablero();

    LinkedList <Proyectiles>heroes=new LinkedList();

    public Ventana() throws InterruptedException {
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



        g.drawImage(img_fondo.getImage(),0,0,null);
        g.drawImage(selec.getImage(),Xtt(selec_x),Ytt(selec_y),null);

        g.drawImage(bala.getImage(),proyectil.xi,proyectil.yi,null);






        //g.drawImage(imgzomb1.getImage(),115,98,null);
        //Botones de atacar,moverse y usar item---------
        if (clickX>643){
            if(clickY>242 && clickY<342){
                this.transformar(T1.zombies);
                System.out.println("ataking ratataata");
                clickX=-100;
                clickY=-100;
                ataque=true;
            }
            if(clickY>368 && clickY<468){
                System.out.println("muving");
                clickX=-100;
                clickY=-100;
            }
            if(clickY>493 && clickY<593){
                System.out.println("usando itemquisde");
                clickX=-100;
                clickY=-100;
            }


        }




        //----------------------------------------------


        if (clickX>56 && clickX<574){
            item=false;
            mover=false;
            ataque=false;
            this.mover(0,0);

            /* prueba de seleccion de alguna casilla
            setSelec_x(Xtt(clickX));
            setSelec_y(Ytt(clickY));
            selec_x=(clickX-65)/56;
            selec_y=(clickY-36)/50;
            System.out.println("en la matriz se va a poner en X: "+Xtt(selec_x)+" y: "+Ytt(selec_y));
            clickX=-100;
            clickY=-100;

            */

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
        for(int i=0;i<=heroes.size()-1;i++){
            Proyectiles h= (Proyectiles) heroes.get(i);
            Runnable aux = h;
            new Thread(h).start();
            g.drawImage(zomb1.getImage(),h.xi,h.yi,null);
        }
        g.drawImage(pocion.getImage(),Xtt(0),Ytt(0),null);

        // Gestion de las imagenes de los botones

        if (ataque){
            g.drawImage(batacar.getImage(),635,212,null);
        }else{
            g.drawImage(atacarbn.getImage(),635,212,null);
        }

        if (mover){
            g.drawImage(imover.getImage(),635,337,null);
        }else{
            g.drawImage(moverbn.getImage(),635,337,null);
        }
        if (item){
            g.drawImage(iitem.getImage(),635,462,null);
        }else{
            g.drawImage(itembn.getImage(),635,462,null);
        }


        repaint();
    }
    public void mover(int dx,int dy){
        Proyectiles moviendoc=heroes.get(0);
        System.out.println("xi :"+moviendoc.xi);
        moviendoc=new Proyectiles(ttX(moviendoc.xi),ttY(moviendoc.yi),ttX(moviendoc.xi),ttY(moviendoc.yi)-2);

        Runnable proceso=moviendoc;
        new Thread(proceso).start();
        heroes.set(0,moviendoc);
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

    public void transformar(LinkedList zombies){
        this.heroes = new LinkedList();
        LinkedList aux = new LinkedList();

        for(int a = 0; a < zombies.size(); a++){
            int[] xy = new int[2];
            Zombie tmp = (Zombie) zombies.get(a);
            xy[0] = tmp.posX;
            xy[1] = tmp.posY;
            System.out.println("[" + xy[0] + ", " + xy[1] + "]");
            aux.add(xy);
        }

        T1.turnoZombie();

        for(int i = 0; i < zombies.size(); i++){
            Zombie tmp = (Zombie) zombies.get(i);
            int[] xyInicial = (int[]) aux.get(i);
            System.out.println("============================================");
            System.out.println(xyInicial);
            System.out.println("Largo: " + aux.size());
            System.out.println("[" + xyInicial[0] + ", " + xyInicial[1] + "]");
            Proyectiles heroe1 = new Proyectiles(xyInicial[0],xyInicial[1],tmp.posX,tmp.posY);
            this.heroes.add(heroe1);

        }

        System.out.println("Zombies: " + this.heroes.size());




    }


}
