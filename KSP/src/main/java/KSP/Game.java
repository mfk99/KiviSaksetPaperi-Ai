
package KSP;

import java.util.Random;

public class Game {
    
    private AI tekoaly;
    private int pelatutPelit;
    
    public Game() {
        tekoaly=new AI();
        pelatutPelit=0;
    }
    
    void pelaa(boolean admin) {
        //aloitetaan looppi ja jaetaan satunnainen vastaus koneelle
        String koneVastaus;
        int parasAi=tekoaly.getAiTila(0);
        while (true) {
            String pelaajaVastaus="";
            boolean pelaajaVoitti=false;
            boolean koneVoitti=false;
            //lasketaan mihin indeksiin valinta sijoitetaan
            int indeksi=pelatutPelit%5;
            //haetaan koneelle vastaus
            if (parasAi!=0) { 
                //jos ai on valittu haetaan siltä vastaus
                koneVastaus=tekoaly.haeVastaus(tekoaly.getAiTila(parasAi));
            } else { 
                //muulloin valitaan satunnainen vastaus
                koneVastaus=satunnainenVastaus("");
            }
            
            //näytetään tekoäly ja sen vastaus jos admin on päällä
            if (admin) {
                if (tekoaly.getAiTila(0)==0) {
                    System.out.println("Satunnainen ai aikoo valita käden "+koneVastaus);
                } else {
                    System.out.println("Tekoälyn "+parasAi+ " versio "
                            +tekoaly.getAiTila(parasAi)+" aikoo valita käden "+koneVastaus);
                }
            }
            
            pelaajaVastaus=ui.pyydaPelaajaltaVastaus();
            if (pelaajaVastaus.equals("exit")) break;
            /*jos validi vastaus on syätetty, lisätään se historiaan ja 
            päivitetään tekoälyjen vastaukset */
            tekoaly.syotaVastaus(indeksi, pelaajaVastaus);
            tekoaly.paivitaAi(indeksi, false);
            
            pelatutPelit++;
            
            //katsotaan pelin tulos
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
            //valitaan uusi tekoäly jos on pelattu viisi peliä vaihtamatta
            if (pelatutPelit%5==0) {
                parasAi=tekoaly.laskeParasAi();
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
