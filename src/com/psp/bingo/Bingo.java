package com.psp.bingo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;


public class Bingo implements Runnable{
    private boolean ganador = false;
    private ArrayList<Integer> bolas = new ArrayList<>();
    private CopyOnWriteArrayList<Carton> cartones= new CopyOnWriteArrayList<>();
    public Bingo(){
        super();
    }

    public boolean getGanador(){
        return ganador;
    }

    public ArrayList<Integer> getBolas() {
        return bolas;
    }


    public synchronized ArrayList<Carton> asignar(int numeroCartones){
        ArrayList<Carton> carton = new ArrayList<Carton>();
        int i = 0;
        while(i < numeroCartones){
            carton.add(this.cartones.get(0));
            this.cartones.remove(0);
            i++;
        }
        return carton;
    }
    public boolean comprobarCartones(ArrayList<Carton> cartonesj){
        int contador=0;
        for(Carton carton: cartones){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; i < 9; j++){
                    if(bolas.contains(carton.numeros[i][j])){
                        return false;
                    }else {
                        contador++;
                        System.out.println(contador);
                    }
                    }
                }
            if(contador==15){
                this.ganador=true;
                return true;

            }
        }return false;
    }
    public void rellenarBingo(){
        for (int i = 1; i < 91; i++) {
            this.bolas.add(i);
        }
    }
    public void sacarBola(){
        int indice=(int)(Math.random()*this.bolas.size());
        //System.out.println("tamaño lista: " + this.bolas.size() + "numero generado " + indice);
        int bola=this.bolas.get(indice);
        System.out.println("bola: " + bola);
        bolas.remove(indice);

    }

    public void generarCartones(){
        int contador=0;
        while(contador<50){
            Carton carton= new Carton(generarNumeros());
            //añadir huecos...

            cartones.add(carton);
            contador++;
        }
    }
    public int[][] generarNumeros(){
        int[][] carton = new int[3][9];
        ArrayList<Integer> numerosGenerados = new ArrayList<>();

        int n;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                do {
                    n = aleatorio(10 * j, (10 * j) + 9);
                } while (numerosGenerados.contains(n));
                numerosGenerados.add(n);
            }

        }
        //ordenar numeros en arraylist
        Collections.sort(numerosGenerados);
        int z=0;
        //contruir arraybidimensional
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                carton[j][i] = numerosGenerados.get(z);
                z++;
            }

        }


        // generar 12 n=0 en el carton
        ArrayList<Integer> huecos = new ArrayList<Integer>();
        int hueco;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                do{
                    hueco=aleatorio(0,7);
                }while(huecos.contains(hueco));
                huecos.add(hueco);
            }
            for(Integer borrar :  huecos){
                System.out.println(borrar);
                carton[i][borrar]=0;
            }
            huecos.clear();
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(" ");
            for (int j = 0; j < 8; j++) {
                System.out.print(carton[i][j] + " ");
            }
        }

        return carton;

    }
    public static int aleatorio(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    @Override
    public void run() {

            rellenarBingo();
            generarCartones();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while(!this.ganador){
            sacarBola();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
