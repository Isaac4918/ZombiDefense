package Interfaz;

public class Juego extends Thread {
    private int clickX;

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

    private int clickY;


    public Juego() {
        Thread proyectiles;



    }

    @Override
    public void run() {
        super.run();
        //Thread logica=new Thread((Runnable) new GestionIU());
        //logica.run();

        GestionIU gestiu=new GestionIU();



    }


}


