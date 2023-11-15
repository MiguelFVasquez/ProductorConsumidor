package Punto4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Consumer extends Thread {

    private ArrayList<Character> letrasNoFuncionales;
    private String palabraABuscar;

    private char[] arregloPalabra;

    private ArrayList<Character> buffer;

    public Consumer(String palabraABuscar, ArrayList<Character> buffer) {
        this.palabraABuscar = palabraABuscar;
        this.buffer = buffer;
        letrasNoFuncionales= new ArrayList<>();
    }

    @Override
    public void run() {
        arregloPalabra= new char[ palabraABuscar.toCharArray().length];
        boolean terminado= true;
        char []letrasBuscar= new char[2];
        List<Integer> listaPosiciones;
        //Caracteres que recoge
        char caracter1;
        char caracter2;

        while(terminado) {
                System.out.println("Tamaño actual del buffer (consumidor): " + buffer.size());
            synchronized (buffer) {
                while (buffer.size()<2) {
                    try {
                        buffer.wait();
                        System.out.println("Consumidor en espera");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                caracter1=buffer.remove(0);
                caracter2= buffer.remove(0);
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
                        letrasNoFuncionales.add(caracter1);
                        System.out.println("La letra '" + letraBuscar + "' no se encontró en la palabra.");
                    }
                }
                buffer.notify();
            }
            if(!terminarCiclo(arregloPalabra)){
                terminado= false;
                ProductorA.detenerHilo();
                ProductorB.detenerHilo();
                ProductorC.detenerHilo();
                System.out.println("Arreglo final: "+ Arrays.toString(arregloPalabra));
                System.out.println("Lista de letras no usadas"+ letrasNoFuncionales.toString());

            }
            //buffer.notify();
            try{
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }





    }

    private boolean terminarCiclo(char[] arregloPalabra){
        String palabraActual= "";
        for (char c: arregloPalabra){
                palabraActual+= c;
            }
        if(palabraActual.equals(palabraABuscar)){
            return false;
        }
        return true;

    }

    private synchronized char recogerLetra() throws InterruptedException {
        char caracterBuffer= ' ';
        if (buffer.size()<2){
            wait();
        }else{
            caracterBuffer= buffer.remove(0);
            notify();
        }
        return caracterBuffer;
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


}
