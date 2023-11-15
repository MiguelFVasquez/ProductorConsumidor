package Punto2;

import java.util.ArrayList;

public class Productor1 extends Thread {

    private Tuberia tuberia;
    private ArrayList<Character> listaEnviados;
    private ArrayList<Character> letrasEnviadas;
    private String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Productor1(Tuberia tuberia, ArrayList<Character> listaEnviados) {
        this.tuberia = tuberia;
        this.listaEnviados = listaEnviados;
        this.letrasEnviadas= new ArrayList<>();
    }

    @Override
    public void run() {
        char letra1;
        for (int i = 0; i <15 ; i++) {
            letra1= alfabeto.charAt((int) (Math.random() *26));
            agregarCaracter(letra1);
            try {
                //sleep((int) (Math.random() * 100));
                sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Letras enviadas con exito desde productor 1");
        System.out.println("Letras enviadas desde productor1: "+ letrasEnviadas.toString());
    }

    private synchronized void agregarCaracter(char letra1){
        if (!listaEnviados.contains(letra1)){ //Si la lista no tiene la letra que se va a enviar
            listaEnviados.add(letra1);
            tuberia.lanzar(letra1);
            letrasEnviadas.add(letra1);
            System.out.println("Enviando la letra: "+ letra1+ " desde productor1");
        }else {
            System.out.println("No se ha enviado la letra: "+ letra1 +" por estar repetida. Desde productor1");
        }
    }

}
