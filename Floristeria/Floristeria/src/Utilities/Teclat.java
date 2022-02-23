package Utilities;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase para gestionar l'entrada de dades de teclat
 */
public class Teclat {

    private static final Scanner lectura = new Scanner(System.in);

    /** 
     * Aquest mètode llegeix un String per teclat
     * @param missatge
     * @return String
     */
    public static String leerString(String missatge) {
        String cadena = "";
        boolean correcte = false;

        do {
            System.out.print(missatge);
            try {
                cadena = lectura.nextLine();
                correcte = true;
            } catch (Exception ex) {
                System.out.println("Error en la introducció de la cadena.");
            }
        } while (!correcte);
        return cadena;
    }

     /** 
      * Aquest mètode llegeix un float per teclat
      * @param missatge
      * @return float
      */
    public static float leerFloat(String missatge) {
        float numero = 0.0f;
        boolean correcte = false;

        do {
            System.out.print(missatge);
            try {
                numero = lectura.nextFloat();
                correcte = true;
            } catch (InputMismatchException ex) {
                System.out.println("Error de format.");
            }
            lectura.nextLine();
        } while (!correcte);
        return numero;
    }

     /** 
      * Aqueset mètode llegeix un double per teclat
      * @param missatge
      * @return double
      */
    public static double leerDouble(String missatge) {
        double numero = 0.0;
        boolean correcte = false;

        do {
            System.out.print(missatge);
            try {
                numero = lectura.nextDouble();
                correcte = true;
            } catch (InputMismatchException ex) {
                System.out.println("Error de format.");
            }
            lectura.nextLine();
        } while (!correcte);
        return numero;
    }

     /** 
      * Aquest mètode llegeix un enter per teclat
      * @param missatge
      * @return int
      */
    public static int leerInt(String missatge) {
        int numero = 0;
        boolean correcte = false;

        do {
            System.out.print(missatge);
            try {
                numero = lectura.nextInt();
                correcte = true;
            } catch (InputMismatchException ex) {
                System.out.println("Error de format.");
            }
            lectura.nextLine();
        } while (!correcte);
        return numero;
    }
    
    /** 
     * Aquest mètode llegeix un byte per teclat
     * @param missatge
     * @return byte
     */
    public static byte leerByte(String missatge) {
        byte numero = 0;
        boolean correcte = false;

        do {
            System.out.print(missatge);
            try {
                numero = lectura.nextByte();
                correcte = true;
            } catch (InputMismatchException ex) {
                System.out.println("Error de format.");
            }
            lectura.nextLine();
        } while (!correcte);
        return numero;
    }

     /** 
      * Aquest mètode llegeix un char per teclat
      * @param missatge 
      * @return char
      */
    public static char leerChar(String missatge) {
        char caracter = 0;
        boolean correcte = false;

        do {
            System.out.print(missatge);
            try {
                caracter = lectura.next().charAt(0);// Prenem el primer caracter en una lectura de cadena
                correcte = true;
            } catch (InputMismatchException ex) {
                System.out.println("Error de format.");
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
            lectura.nextLine();
        } while (!correcte);
        return caracter;
    }
    
    /** 
     * Aquest mètode llegeix un booleà per teclat
     * @param missatge
     * @return boolean
     */
    public static boolean leerSiNo(String missatge) {
        boolean retorn = false;
        boolean correcte = false;
        String cadena = "";

        do {
            System.out.print(missatge);
            try {
                cadena = lectura.nextLine().toUpperCase();
                correcte = true;
                if (cadena.charAt(0) == 'S') {
                    retorn = true;
                } else if (cadena.charAt(0) == 'N') {
                    retorn = false;
                } else {
                    System.out.println("Introducció no vàlida.");
                    correcte = false;
                }
            } catch (Exception ex) {
                System.out.println("Error en la introducció de la cadena.");
            }
        } while (!correcte);
        return retorn;
    }
}