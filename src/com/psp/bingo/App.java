package com.psp.bingo;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        Bingo bombo = new Bingo();
        bombo.rellenarBingo();
        System.out.println(bombo.getBolas());
        for (int i = 0; i < 50; i++) {
            System.out.println(bombo.sacarBola());
        }
        System.out.println(bombo.getBolas());



    }


}
