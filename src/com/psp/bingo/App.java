package com.psp.bingo;


public class App {
    public static void main(String[] args) throws InterruptedException {
        Bingo bombo = new Bingo();
        Jugador jugador1 = new Jugador("Gonzalo", 50, bombo);
        Jugador jugador2 = new Jugador("Ramon", 50,bombo);
        Jugador jugador3 = new Jugador("Alberto", 50, bombo);
        Jugador jugador4 = new Jugador("Pablo", 50, bombo);


        new Thread(bombo).start();
        new Thread(jugador1).start();
        new Thread(jugador2).start();
        new Thread(jugador3).start();
        new Thread(jugador4).start();

    }


}
