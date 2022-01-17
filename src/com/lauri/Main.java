package com.lauri;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        while (true) {
            Scanner scanner1 = new Scanner(System.in);
            System.out.print("Syötä 1, jos haluat lisätä saunomiskerran\nSyötä 2, jos haluat etsiä saunomiskertoja\nValintasi: ");
            String valinta1 = scanner1.nextLine();

            //Määritellään toiminta, kun käyttäjä haluaa lisätä uuden saunomiskerran
            if (valinta1.equals("1")) {
                String vuosi;
                String kuukausi;
                String paiva;
                Scanner scanner2 = new Scanner(System.in);
                System.out.print("Syötä sauna: ");
                String sauna = scanner2.nextLine();
                while (true) {
                    Scanner scanner3 = new Scanner(System.in);
                    System.out.print("Syötä viikonpäivä: ");
                    paiva = scanner3.nextLine().toLowerCase();
                    if (Saunakerta.kelpoPaiva(paiva)) {
                        break;
                    } else {
                        System.out.println("\nEpävalidi syöte\n");
                    }
                }
                while (true) {
                    Scanner scanner4 = new Scanner(System.in);
                    System.out.print("Syötä kuukausi: ");
                    kuukausi = scanner4.nextLine().toLowerCase();
                    if (Saunakerta.kelpoKuukausi(kuukausi)) {
                        break;
                    } else {
                        System.out.println("\nEpävalidi syöte\n");
                    }
                }
                while (true) {
                    Scanner scanner5 = new Scanner(System.in);
                    System.out.print("Syötä vuosi: ");
                    vuosi = scanner5.nextLine();
                    if (Saunakerta.kelpoVuosi(vuosi)) {
                        break;
                    }
                }
                Saunakerta.kirjoitaTiedostoon(new Saunakerta(sauna, paiva, kuukausi, vuosi));
            }
            //Määritellään toiminta, kun käyttäjä haluaa etsiä saunomiskertoja
            else if (valinta1.equals("2")) {
                while (true) {
                    Scanner scanner6 = new Scanner(System.in);
                    System.out.print("Syötä 1, jos haluat etsiä saunomiskertoja saunan perusteella\nSyötä 2, jos haluat etsiä saunomiskertoja päivän, kuukauden tai vuoden perusteella\nValintasi: ");
                    String valinta2 = scanner6.nextLine();
                    //Määritellään toiminta, kun käyttäjä haluaa etsiä saunomiskertoja saunan perusteella
                    if (valinta2.equals("1")) {
                        Scanner scanner7 = new Scanner(System.in);
                        System.out.print("Syötä sauna: ");
                        String sauna = scanner7.nextLine();
                        Saunakerta.etsiSaunallaTiedostosta(sauna);
                        break;
                    }
                    //Määritellään toiminta, kun käyttäjä haluaa etsiä saunomiskertoja päivän, kuukauden tai vuoden perusteella
                    if (valinta2.equals("2")) {
                        Scanner scanner8 = new Scanner(System.in);
                        System.out.print("Syötä päivä, kuukausi tai vuosi: ");
                        String aika = scanner8.nextLine();
                        Saunakerta.etsiAjallaTiedostosta(aika);
                        break;
                    }
                    else {
                        System.out.println("\nEpäkelpo syöte\n");
                    }
                }
            }
            else {
                System.out.println("\nEpävalidi syöte\n");
            }
        }
    }
}
