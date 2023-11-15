package Punto3;

public class App {
    public static void main(String[] args) {
        Tuberia tuberia= new Tuberia();
        char[] arregloCaracteres = {
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
        };
        String palabraBuscar= "uniquindio2023";
        P1 p1= new P1(tuberia,arregloCaracteres);
        P2 p2= new P2(tuberia,arregloCaracteres);
        P3 p3= new P3(tuberia,arregloCaracteres);
        Consumidor c= new Consumidor(tuberia,palabraBuscar);

        p1.start();
        p2.start();
        p3.start();
        c.start();

        try{
            p1.join();
            p2.join();
            p3.join();
            c.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Lista de caracteres que no sirven: "+ c.getLetraNoFuncionales());
    }
}
