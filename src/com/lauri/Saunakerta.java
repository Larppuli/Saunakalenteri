package com.lauri;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Saunakerta implements Serializable {
    static String tiedosto = "data.bin";
    private String sauna;
    private String kuukausi;
    private String paiva;
    private String vuosi;

    public Saunakerta(String sauna, String paiva, String kuukausi,String vuosi) {
        this.sauna = sauna;
        this.kuukausi = kuukausi;
        this.paiva = paiva;
        this.vuosi = vuosi;
    }

    public String getSauna() {
        return sauna;
    }

    public String getKuukausi() {
        return kuukausi;
    }

    public String getPaiva() {
        return paiva;
    }

    public String getVuosi() { return vuosi; }


    /**
     * Metodi lukee data.bin tiedostossa olevan listan ja lisää siihen halutun saunomiskerran (tapahtuma)
     */
    public static void kirjoitaTiedostoon(Saunakerta tapahtuma) {
        ObjectOutputStream oos;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tiedosto));
            ArrayList<Saunakerta> lista = (ArrayList<Saunakerta>) ois.readObject();
            lista.add(tapahtuma);
            oos = new ObjectOutputStream(new FileOutputStream(tiedosto));
            oos.writeObject(lista);
            oos.close();
            System.out.println("\nSaunakerta lisätty\n");
            TimeUnit.SECONDS.sleep(1);
        }
        catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodi etsii data.bin tiedostossa olevasta listasta Saunakerta-oliot, joiden sauna-attribuutti
     * on parametrin sauna mukainen ja tulostaa olioiden päivä,- kuukausi- ja vuosi-attribuutit. Myös osumien määrä tulostetaan.
     */
    public static void etsiSaunallaTiedostosta(String sauna) throws IOException, ClassNotFoundException {
        int i = 0;
        System.out.println("\n");
        ArrayList<Saunakerta> lista = avaaLista();
        for (Saunakerta kerta : lista) {
            if (kerta.getSauna().equals(sauna)) {
                System.out.println(kerta.getPaiva() + ", " + kerta.getKuukausi() + ", " + kerta.getVuosi());
                i += 1;
            }
        }
        if (i != 0) {
            System.out.println("\nSaunomiskertoja kyseisessä saunassa: " + i + "\n");
        }
        else {
            System.out.println("\nEi saunomiskertoja kyseisessä saunassa\n");
        }
    }


    /**
     * Metodilla voi tyhjentää koko tiedoston ja alustaa sen tyhjällä Saunakerta-oliolistalla
     */
    public static void alustaTiedosto() {
        ObjectOutputStream oos;
        {
            try {
                ArrayList<Saunakerta> lista = new ArrayList<>();
                oos = new ObjectOutputStream(new FileOutputStream(tiedosto));
                oos.writeObject(lista);
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodi tarkistaa onko parametri oikea kuukausi
     */
    public static Boolean kelpoKuukausi(String kuukausi) {
        List<String> kuukaudet = Arrays.asList("tammikuu", "helmikuu", "maaliskuu", "huhtikuu", "toukokuu", "kesäkuu", "heinäkuu", "elokuu", "syyskuu", "lokakuu", "marraskuu", "joulukuu");
        return kuukaudet.contains(kuukausi);
    }

    /**
     * Metodi tarkistaa onko parametri oikea viikonpäivä
     */
    public static Boolean kelpoPaiva(String paiva) {
        List<String> paivat = Arrays.asList("maanantai", "tiistai", "keskiviikko", "torstai", "perjantai", "lauantai", "sunnuntai");
        return paivat.contains(paiva);
    }

    /**
     * Metodi palauttaa data.bin tiedostossa sijaitsevan listan
     */
    public static ArrayList<Saunakerta> avaaLista() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tiedosto));
        ArrayList<Saunakerta> lista = (ArrayList<Saunakerta>) ois.readObject();
        ois.close();
        return lista;
    }

    /**
     * Metodi tulostaa kaikkien niiden Saunakerta-olioiden kaikki attribuutit, joiden päivä,- kuukausi- tai vuosi-attribuutti täsmää parametrin aika kanssa
     */
    public static void etsiAjallaTiedostosta(String aika) throws IOException, ClassNotFoundException {
        int i = 0;
        //Tarkastetaan onko parametrina saatu aika viikonpäivä
        if (kelpoPaiva(aika)) {
            System.out.println("\n");
            for (Saunakerta kerta : avaaLista()) {
                if (kerta.getPaiva().equals(aika)) {
                    System.out.println(kerta.getPaiva() + ", " + kerta.getKuukausi() + ", "  + kerta.getVuosi() + ", " + kerta.getSauna());
                    i += 1;
                }
            }
            if (i != 0) {
                System.out.println("\nSaunomiskertoja kyseisenä päivänä: " + i +"\n");
            }
            else {
                System.out.println("\nEi saunomiskertoja kyseisenä päivänä\n");
            }
        }
        //Tarkastetaan onko parametrina saatu aika kuukausi
        else if (kelpoKuukausi(aika)) {
            System.out.println("\n");
            for (Saunakerta kerta : avaaLista()) {
                if (kerta.getKuukausi().equals(aika)) {
                    System.out.println(kerta.getPaiva() + ", " + kerta.getKuukausi() + ", "  + kerta.getVuosi() + ", " + kerta.getSauna());
                    i += 1;
                }
            }
            if (i != 0) {
                System.out.println("\nSaunomiskertoja kyseisenä kuukautena: " + i +"\n");
            }
            else {
                System.out.println("\nEi saunomiskertoja kyseisenä kuukautena\n");
            }
        }
        //Tarkastetaan onko parametrina saatu aika vuosi
        else if (kelpoVuosi(aika)) {
            System.out.println("\n");
            for (Saunakerta kerta : avaaLista()) {
                if (kerta.getVuosi().equals(aika)) {
                    System.out.println(kerta.getPaiva() + ", " + kerta.getKuukausi() + ", "  + kerta.getVuosi() + ", " + kerta.getSauna());
                    i += 1;
                }
            }
            if (i != 0) {
                System.out.println("\nSaunomiskertoja kyseisenä vuonna: " + i + "\n");
                if (aika.equals("2021")) {
                    System.out.println("Kyseisen vuoden saunomiskertoja puuttuu");
                }
            }
            else {
                System.out.println("\nEi saunomiskertoja kyseisenä vuonna\n");
            }
        }
    }

    /**
     * Tarkastetaan onko parametrina saatu merkkijono vuosi, joka ei ole tulevaisuudessa
     */
    public static Boolean kelpoVuosi(String vuosi) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        try {
            int testi = Integer.parseInt(vuosi);
            if (testi<=year) {
                return true;
            }
            else {
                System.out.println("\nValittu vuosi on tulevaisuudessa\n");
                return false;
            }
        }
        catch (Exception e) {
            System.out.println("\nEpävalidi syöte\n");
            return false;
        }
    }
}
