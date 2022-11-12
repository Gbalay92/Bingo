package com.psp.bingo;

import java.util.ArrayList;

public class Bingo implements Runnable{
    private boolean ganador = false;
    private ArrayList<Integer> bolas = new ArrayList<>();
    private ArrayList<Carton> cartones= new ArrayList<>();
    public Bingo(){
        super();
    }

    public boolean getGanador(){
        return ganador;
    }

    public ArrayList<Integer> getBolas() {
        return bolas;
    }

    public void rellenarBingo(){
        for (int i = 1; i < 91; i++) {
            this.bolas.add(i);
        }
    }
    public int sacarBola(){
        int indice=(int)(Math.random()*this.bolas.size());
        //System.out.println("tamaño lista: " + this.bolas.size() + "numero generado " + indice);
        int bola=this.bolas.get(indice);
        bolas.remove(indice);
        return bola;
    }

    public void generarCartones(){
        int contador=0;
        while(contador<50){
            Carton carton= new Carton(generarNumeros());
            cartones.add(carton);
        }
    }
    public int[][] generarNumeros(){
        int[][] carton = new int[3][9];
        ArrayList<Integer> numerosGenerados = new ArrayList<>();

        int n;
        //generar numeros
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                do {
                    n = aleatorio(10 * j, (10 * j) + 9);
                } while (numerosGenerados.contains(n));

                numerosGenerados.add(n);
                carton[i][j] = n;
            }
        }/*
        for (int i = 0; i < 3; i++) {
            System.out.println(" ");
            for (int j = 0; j < 9; j++) {
                System.out.print(carton[i][j] + " ");
            }
        }*/

        // generar 12 n=0 en el carton
        for (int i = 0; i < 12; i++) {

        }

        return carton;

    }
    public static int aleatorio(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    @Override
    public void run() {
        synchronized(new Object()){
            rellenarBingo();
            generarCartones();
            notifyAll();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
