package Punto4;

import java.util.ArrayList;

public class ProductorA extends Thread{

    private char [] arregloCaracteres;
    private ArrayList<Character> buffer;

    private static boolean produciendo = true;


    public ProductorA(char[] arregloCaracteres, ArrayList<Character>  buffer) {
        this.arregloCaracteres = arregloCaracteres;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        char[] vocales = arregloVocales();
        int i = 0;
        while(produciendo) {

            lanzarLetra(vocales[i]);//System.out.println("Tamaño actual del buffer(PA): " + buffer.size());
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i<vocales.length-1){
                i++;
            }else{
                i=0;
            }
        }
    }

    private void lanzarLetra(char letra1) {
        synchronized (buffer) {
            if (buffer.size() < 12) {
                buffer.add(letra1);
                System.out.println("Vocal: " + letra1 + " lanzada desde el productorA");
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
    private char [] arregloVocales(){
        char [] vocales= new char[5];
        int indicesVocal=0;
        for (char caracter : arregloCaracteres) {
            if (esVocal(caracter)) {
                vocales[indicesVocal] = caracter;
                indicesVocal++;
            }
        }
        return vocales;
    }
    private boolean esVocal(char c) {
        char vocal = Character.toLowerCase(c);
        return vocal == 'a' || vocal == 'e' || vocal == 'i' || vocal == 'o' || vocal == 'u';
    }
    public static void detenerHilo(){
        produciendo = false;

    }
}
