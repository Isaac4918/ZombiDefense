package Interfaz;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GestionIU extends JFrame implements MouseListener {
    static JFrame frm_ventana;

    Ventana ventana=new Ventana();


    public GestionIU(){

        frm_ventana=new JFrame();
        JPanel panel_juego=new JPanel(null);




        panel_juego.add(ventana);

        ventana.setBounds(0,0,617,700);


        frm_ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frm_ventana.setResizable(false);
        frm_ventana.addMouseListener(this);
        frm_ventana.add(panel_juego);
        frm_ventana.setSize(617,700);
        frm_ventana.setVisible(true);


    }
    @Override
    public void mouseClicked(MouseEvent e) {
        ventana.setClickX(e.getX());
        ventana.setClickY(e.getY());
        //System.out.println("equis:"+ventana.getClickX()+"lle: "+ventana.getClickY());
        System.out.println("cuadricula "+((ventana.getClickX()-65)/56));

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    public static void main(String args[]){
        GestionIU vent=new GestionIU();

    }
}
