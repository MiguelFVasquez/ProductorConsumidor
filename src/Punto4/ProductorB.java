package Punto4;

import java.util.ArrayList;

public class ProductorB extends Thread {

    private static boolean produciendo = true;
    private char [] arregloCaracteres;
    private ArrayList<Character> buffer;


    public ProductorB(char[] arregloCaracteres, ArrayList<Character> buffer) {
        this.arregloCaracteres = arregloCaracteres;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        char[] consonantes = arregloConsonantes();
        int i = 0;
        while (produciendo) {
            lanzarLetra(consonantes[i]);
            //System.out.println("Tamaño actual del buffer (PB): " + buffer.size());
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i < consonantes.length-1) {
                i++;
            } else i = 0;
        }

    }
    private void lanzarLetra(char letra1) {
        synchronized (buffer) {
            if (buffer.size() < 12) {
                buffer.add(letra1);
                System.out.println("Consonante: " + letra1 + " lanzada desde el productorB");
                buffer.notify(); // Cambiado para notificar al consumidor
            } else {
                try {
                    buffer.wait();
                    //System.out.println("Hilo 1 en espera");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private char [] arregloConsonantes(){
        char [] consonantes= new char[21];
        int indiceCaracter=0;
        for (char caracter: arregloCaracteres){
            if (esConsonante(caracter)){
                consonantes[indiceCaracter]= caracter;
                indiceCaracter++;
            }
        }
        return consonantes;
    }

    private static boolean esConsonante(char c) {
        c = Character.toLowerCase(c);

        if ((c >= 'a' && c <= 'z') && !esVocal(c)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean esVocal(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
    public static void detenerHilo(){
        produciendo = false;

    }
}
