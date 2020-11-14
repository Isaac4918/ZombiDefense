package Interfaz;

import javax.swing.*;
import java.awt.*;

//ventana puede que tenga metodos que GestionUI le da para actualizar el juego
public class Ventana extends JPanel {

    private int clickX;
    private int clickY;


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
        ImageIcon imgzomb1=new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/zomb1.gif")).getImage());

        g.drawImage(img_fondo.getImage(),0,0,null);
        g.drawImage(imgzomb1.getImage(),115,98,null);

        repaint();

    }










}
