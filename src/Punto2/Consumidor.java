package Punto2;

import java.util.ArrayList;

public class Consumidor extends Thread {

    private Tuberia tuberia;
    private ArrayList<Character> listaRecibidos;

    public Consumidor(Tuberia tuberia) {
        this.tuberia = tuberia;
        this.listaRecibidos= new ArrayList<>();
    }

    @Override
    public void run() {
        char letra1;
        char letra2;

        for (int i = 0; i < 16; i++) {
            letra1= tuberia.recoger();
            System.out.println("Letra 1 recogida, el caracter es: "+ letra1);
            letra2 = tuberia.recoger();
            System.out.println("Letra 2 recogida, el caracter es: "+letra2);
            listaRecibidos.add(letra1);
            listaRecibidos.add(letra2);
            System.out.println("Lista actual: " + listaRecibidos);
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Lista de letras almacenadas: "+ listaRecibidos.toString());
    }
}
