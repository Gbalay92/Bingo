package com.psp.bingo;

import java.util.ArrayList;

public class Jugador implements Runnable {
    private boolean ganador = false;
    private ArrayList<Carton> cartones;
    private String nombre;
    private int creditos;
    private Bingo bingo;

    public Jugador(String nombre, int creditos, Bingo bingo) {
        this.nombre = nombre;
        this.creditos = creditos;
        this.bingo = bingo;
    }

    public void gastar(int numeroCartones){
        this.creditos-=(numeroCartones*2);
    }

    @Override
    public synchronized void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        gastar(5);
        cartones=bingo.asignar(5);

       System.out.println(" al jugador " + this.nombre + " le quedan " + this.creditos + " y juega con " + cartones.size() +" cartones");
        while (!this.bingo.getGanador()) {
           this.ganador= bingo.comprobarCartones(cartones);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (this.ganador){
            System.out.println(this.nombre + " HA CANTADO BINGO");
        }
    }
}
