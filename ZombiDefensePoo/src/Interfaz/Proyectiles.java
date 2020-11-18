package Interfaz;


import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Proyectiles  extends JPanel implements Runnable {
    int xi;
    int yi;

    public int getXi() {
        return xi;
    }

    public void setXi(int xi) {
        this.xi = xi;
    }

    public int getYi() {
        return yi;
    }

    public void setYi(int yi) {
        this.yi = yi;
    }

    public int getXf() {
        return xf;
    }

    public void setXf(int xf) {
        this.xf = xf;
    }

    public int getYf() {
        return yf;
    }

    public void setYf(int yf) {
        this.yf = yf;
    }

    int xf;
    int yf;

    public Proyectiles(int xi, int yi, int xf, int yf) {
        this.xi=(xi*56)+65;
        this.yi=(yi*50)+36;
        this.xf=(xf*56)+65;
        this.yf=(yf*50)+36;

    }

    @Override
    public void run() {

        //System.out.println("yi: "+yi+ "    yf: "+yf);

        try {
            while ((xi!=xf) || (yi!=yf)){

                if (xi<xf){
                    setXi(xi+1);
                    //System.out.println("Equis de bala"+xi);
                }if (xi>xf){
                    setXi(xi-1);
                }
                if (yi<yf){
                    setYi(yi+1);
                    //System.out.println("Y actual: "+yi);
                }if (yi>yf){
                    setYi(yi-1);
                }
                Thread.sleep(1500);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}