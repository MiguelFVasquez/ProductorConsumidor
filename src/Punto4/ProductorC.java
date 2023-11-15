package Punto4;

import java.util.ArrayList;

public class ProductorC extends Thread {

    private static boolean produciendo = true;
    private char [] arregloCaracteres;
    private ArrayList<Character> buffer;


    public ProductorC(char[] arregloCaracteres, ArrayList<Character> buffer) {
        this.arregloCaracteres = arregloCaracteres;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        char[] numeros= arregloNumeros();
        int i=0;
        while (produciendo) {

            lanzarLetra(numeros[i]);
            //System.out.println("Tamaño actual del buffer (PC): " + buffer.size());
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (i < numeros.length-1) {
                i++;
            } else {
                i = 0;
            }
        }
    }
    private void lanzarLetra(char letra1) {
        synchronized (buffer) {
            if (buffer.size() < 12) {
                buffer.add(letra1);
                System.out.println("Número: " + letra1 + " lanzado desde el productorC");
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
    private char[] arregloNumeros(){
        char[] numeros= new char[10];
        int indiceArreglo=0;
        for (char caracter: arregloCaracteres){
            if (esNumero(caracter)){
                numeros[indiceArreglo]= caracter;
                indiceArreglo++;
            }
        }
        return numeros;
    }

    private boolean esNumero(char c){
        return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9';
    }
    public static void detenerHilo(){
        produciendo = false;

    }

}


