
package KSP;

import java.util.Random;

public class Game {
    
    public AI tekoaly;
    public int pelatutPelit;
    
    public Game() {
        pelatutPelit=0;
        tekoaly=new AI(4);
    }
    
    void pelaa(int ai, boolean admin) {
        //aloitetaan looppi ja jaetaan satunnainen vastaus koneelle
        while (true) {
            int parasAi=0;
            String pelaajaVastaus="";
            String koneVastaus;
            //lasketaan mihin indeksiin valinta sijoitetaan
            int indeksi=pelatutPelit%5;
            /*mikäli ei ole dataa tai pelataan täysin 
            satunnaisella tekoälyllä, valitaan satunnainen vastaus*/
            if (ai==1 || pelatutPelit<1) {
                koneVastaus=satunnainenVastaus("");
            }
            /*jos ollaan pelattu ainakin 1 peli, lasketaan paras AI
            ja asetetaan koneen vastaukseksi sen tekoälyn vastaus */
            else {
                tekoaly.paivitaAi(indeksi, false);
                parasAi=tekoaly.laskeParasAi();
                koneVastaus=tekoaly.haeVastaus(parasAi);
            }
            
            //näytetään tekoäly ja sen vastaus jos admin on päällä
            if (admin) {
                if (ai==1 || pelatutPelit<1) {
                    System.out.println("Satunnainen ai aikoo valita käden "+koneVastaus);
                } else {
                    System.out.println("Ai nro "+parasAi+ " aikoo valita käden "+koneVastaus);
                }
            }
            
            pelaajaVastaus=ui.pyydaPelaajaltaVastaus();
            
            if (pelaajaVastaus.equals("exit")) break;
            
            tekoaly.syotaVastaus(pelaajaVastaus, indeksi);
            if (pelatutPelit>0) tekoaly.laskeTulokset(pelaajaVastaus, indeksi);
            pelatutPelit++;
            
            //katsotaan pelin tulos
            boolean pelaajaVoitti=false;
            boolean koneVoitti=false;
            if (pelaajaVastaus.equals(koneVastaus)) { //peli on tasapeli
                System.out.println("Kummatkin valitsivat "+pelaajaVastaus);
            }
            else { //peli ei ole tasapeli
                switch (pelaajaVastaus) {
                    case "kivi":
                        if (koneVastaus.equals("sakset")) {
                            pelaajaVoitti=true;
                        }
                        else {
                            koneVoitti=true;
                        }
                        break;
                    case "sakset":
                        if (koneVastaus.equals("paperi")) {
                            pelaajaVoitti=true;
                        }
                        else {
                            koneVoitti=true;
                        }
                        break;
                    case "paperi":
                        if (koneVastaus.equals("kivi")) {
                            pelaajaVoitti=true;
                        }
                        else {
                            koneVoitti=true;
                        }
                        break;
                }
                System.out.println("Pelaaja valitsi "+pelaajaVastaus+" ja kone valitsi "+koneVastaus);
            }
            
            //tilastoidaan vastaus ja tulostetaan tulos
            //tilastossa pelaajan voitto on 0, koneen voitto 1 ja tasapeli 2
            if (pelaajaVoitti) {
                Statistics.tulokset.add(0);
                System.out.println("Pelaaja voitti!");
            }
            if (koneVoitti) {
                Statistics.tulokset.add(1);
                System.out.println("Kone voitti!");
            }
            if (!pelaajaVoitti && !koneVoitti) {
                Statistics.tulokset.add(2);
                System.out.println("Tasapeli!");
            }
        }
    }

    //palauttaa satunnaisen vastauksen, vastausta voi rajoittaa kahteen satunnaiseen
    public static String satunnainenVastaus(String rajoitus) {
        Random r=new Random();
        int satunnainen=0;
        if (rajoitus.equals("")) {
            satunnainen=r.nextInt(3);
        } else {
            satunnainen=r.nextInt(2);
        }
        if (satunnainen==0 && !rajoitus.equals("kivi")) {
            return ("kivi");
        } else if(satunnainen==1 && !rajoitus.equals("sakset")) {
            return ("sakset");
        } else {
            return ("paperi");
        }
    }
}
