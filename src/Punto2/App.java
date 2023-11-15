package Punto2;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        Tuberia tuberia = new Tuberia();
        ArrayList<Character> listaEnviados = new ArrayList<>();
        Productor1 p1 = new Productor1(tuberia, listaEnviados);
        Productor2 p2 = new Productor2(tuberia, listaEnviados);
        Consumidor c = new Consumidor(tuberia);

        p1.start();
        p2.start();
        c.start();
    }



}
