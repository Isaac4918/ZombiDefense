package Interfaz;

import javax.swing.*;
import java.awt.*;

//ventana puede que tenga metodos que GestionUI le da para actualizar el Juego
public class Ventana extends JPanel {
    Graphics g1;
    private int clickX;
    private int clickY;
    Proyectiles proyectil=new Proyectiles(0,0,0,0);


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

        g.drawImage(img_fondo.getImage(),0,0,null);

        //g.drawImage(imgzomb1.getImage(),115,98,null);
        if ((((clickX-65)/56))==1){
            proyectil=new Proyectiles(2,0,0,12);
            Runnable proceso=proyectil;
            new Thread(proceso).start();
            clickX=-1;

        }
        g.drawImage(bala.getImage(),proyectil.xi,proyectil.yi,null);
        //System.out.println("equis :"+proyectil.xi);


        repaint();



    }
    public void disparar(Graphics g){
        proyectil=new Proyectiles(2,0,0,6);
        proyectil.run();

        clickX=-1;
        super.paint(g);




    }










}
