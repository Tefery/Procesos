package Abismo;


import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Elena
 */
public class DiviUniversal {

    public static void main(String args[]) {
        Scanner pedir = new Scanner(System.in);

        System.out.println("\n\tInserte el numero: ");
        String numString = pedir.nextLine();
        while (numString != null) {
            try {
                int num = Integer.parseInt(numString);

                num = Math.abs(num);

                System.out.println("\n\tSUS DIVISORES NEGATIVOS SON: ");
                System.out.println("\t\t" + num * (-1));
                for (int posDiv = num / 2; posDiv >= 1; posDiv--) {
                    if (num % posDiv == 0) {
                        System.out.println("\t\t" + posDiv * (-1));
                    }
                }
                System.out.println("\n\n\tLOS DIVISORES POSITIVOS SON: ");
                for (int posDiv = 1; posDiv <= num / 2; posDiv++) {
                    if (num % posDiv == 0) {
                        System.out.println("\t\t" + posDiv);
                    }
                }
                System.out.println("\t\t" + num);
            } catch (Exception e) {
                System.out.println("\n\tLo siento, ha insertado letras.");
                System.out.println("\n\tNo puedo visualizarte los divisores.");
            }
            System.out.println("\n\tInserte el siguiente numero: ");
            numString = pedir.nextLine();
        }
        pedir.close();
    }
}
