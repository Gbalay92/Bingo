package com.psp.bingo;

import java.util.ArrayList;

public class Jugador implements Runnable {
    private ArrayList<Carton> cartones;
    private String nombre;
    private int creditos;
    private Bingo bingo;

    public Jugador(ArrayList<Carton> cartones, String nombre, int creditos, Bingo bingo) {
        this.cartones = cartones;
        this.nombre = nombre;
        this.creditos = creditos;
        this.bingo = bingo;
    }

    @Override
    public void run() {
        synchronized(new Object()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        while (!this.bingo.getGanador()) {

        }
    }
}
