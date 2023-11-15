package Punto3;
public class P2 extends Thread{
    private Tuberia tuberia;
    private char [] arregloCaracteres;

    public P2(Tuberia tuberia, char[] arregloCaracteres) {
        this.tuberia = tuberia;
        this.arregloCaracteres = arregloCaracteres;
    }


    @Override
    public void run() {
        char [] consonantes= arregloConsonantes();
        for (char consonante: consonantes){
            tuberia.lanzar(consonante);
            System.out.println("Se ha lanzado la consonante: "+ consonante+" desde productor2");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Funcion para sacar el arreglo de caracteres
     * @return
     */
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
}
