package Punto3;

import java.util.ArrayList;

public class P1 extends Thread{

    private Tuberia tuberia;
    private char [] arregloCaracteres;

    public P1(Tuberia tuberia, char[] arregloCaracteres) {
        this.tuberia = tuberia;
        this.arregloCaracteres = arregloCaracteres;
    }

    @Override
    public void run() {
        char [] vocales= arregloVocales();

        for (char vocal: vocales){
            tuberia.lanzar(vocal);
            System.out.println("Se ha lanzado la vocal: "+ vocal+" desde el productor1");
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Funcion para obtener el arreglo de caracteres
     * @return
     */
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


}
