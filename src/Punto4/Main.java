package Punto4;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        char[] arregloCaracteres = {
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
        };
        ArrayList<Character> buffer= new ArrayList<>();
        String palabraBuscar= "uniquindio2023";
        ProductorA p1= new ProductorA(arregloCaracteres,buffer);
        ProductorB p2= new ProductorB(arregloCaracteres,buffer);
        ProductorC p3= new ProductorC(arregloCaracteres,buffer);
        Consumer consumer= new Consumer(palabraBuscar,buffer);

        p1.start();
        p2.start();
        p3.start();
        consumer.start();
    }

}
