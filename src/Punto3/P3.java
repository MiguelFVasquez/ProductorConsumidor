package Punto3;



public class P3 extends Thread{
    private Tuberia tuberia;
    private char [] arregloCaracteres;

    public P3(Tuberia tuberia, char[] arregloCaracteres) {
        this.tuberia = tuberia;
        this.arregloCaracteres = arregloCaracteres;
    }

    @Override
    public void run() {
        char[] numeros= arregloNumeros();

        for (char c: numeros){
            tuberia.lanzar(c);
            System.out.println("Se ha lanzado el numero: "+ c + " desde productor3");
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Funcion para scar el arreglo de los numeros
     * @return
     */
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

}
