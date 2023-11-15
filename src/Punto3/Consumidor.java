package Punto3;

import java.util.*;

public class Consumidor extends Thread{
    private Tuberia tuberia;
    private ArrayList<Character> letraNoFuncionales;
    private String palabraABuscar;

    public Consumidor(Tuberia tuberia, String palabraABuscar) {
        this.tuberia = tuberia;
        this.letraNoFuncionales = new ArrayList<>();
        this.palabraABuscar = palabraABuscar;
    }

    @Override
    public void run() {
        char [] arregloPalabra= new char[ palabraABuscar.toCharArray().length];
        char []letrasBuscar= new char[2];
        List <Integer> listaPosiciones;

        char caracter1;
        char caracter2;

        for (int i=0; i<36; i++){//36 es el total de caracters que se mandan
            caracter1= tuberia.recoger();
            caracter2= tuberia.recoger();
            letrasBuscar[0]= caracter1;
            letrasBuscar[1]= caracter2;
            //Muestro las letras que se han recogido
            System.out.println("Se han recogido las letras: " + caracter1 +" y "+ caracter2);
            //Obtiene las posiciones de la primera letra
            for (char letraBuscar : letrasBuscar) {
                List<Integer> posiciones = buscarPosiciones(palabraABuscar, letraBuscar);

                // Verificar si se encontraron posiciones
                if (!posiciones.isEmpty()) {
                    // Asignar la letra en las posiciones encontradas en el arreglo
                    for (int posicion : posiciones) {
                        arregloPalabra[posicion] = letraBuscar;
                    }
                    System.out.println("Estado actual del arreglo: " + Arrays.toString(arregloPalabra));
                } else {
                    System.out.println("La letra '" + letraBuscar + "' no se encontró en la palabra.");
                    letraNoFuncionales.add(letraBuscar);
                }
            }

            try{
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Palabra formada: "+ Arrays.toString(arregloPalabra));
    }

    private static List<Integer> buscarPosiciones(String palabra, char letraBuscar) {
        List<Integer> posiciones = new ArrayList<>();
        // Buscar posiciones de la letra en la palabra
        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.charAt(i) == letraBuscar) {
                posiciones.add(i);
            }
        }
        return posiciones;
    }

    public ArrayList<Character> getLetraNoFuncionales() {
        return letraNoFuncionales;
    }
}
