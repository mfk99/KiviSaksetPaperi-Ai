
package KSP;

import java.util.Random;

public class AI {
    
    final private Random r;
    final private Statistics stats;
    
    public AI(int x) {
        r=new Random();
        stats=new Statistics(x);
    }
    
    //päivitetään tekoälyjen vastaukset
    void paivitaAi(int i) {
        i--;
        //vältetään nullPointerException
        if (i==-1) i=4;
        stats.setAiVastaukset(0, ai1.vastaus(stats.getPelaajaVastaukset()));
        stats.setAiVastaukset(1, ai1.vastaus(stats.getPelaajaVastaukset()));
        stats.setAiVastaukset(2, ai3.vastaus(stats.getPelaajaVastaukset(), i));
        stats.setAiVastaukset(3, ai4.vastaus(stats.getPelaajaVastaukset(), i));
    }
    
    void syotaVastaus(String pelaajaVastaus,int indeksi) {
        stats.setPelaajaVastaukset(indeksi, pelaajaVastaus);
    }
    
    //lasketaan tekoälyjen vastauksien tulokset
    void laskeTulokset(String pelaajaVastaus, int indeksi) {
        for (int i=0; i<4; i++) {
            String aiVastaus=stats.getAiVastaukset()[i];
            //katsotaan onko peli tasapeli
            if (aiVastaus.equals(pelaajaVastaus)) {
                stats.setAiTulokset(i, indeksi, 0);
            }
            //peli ei ole tasapeli, katsotaan kumpi voitti
            else if (pelaajaVastaus.equals("kivi")) {
                if (aiVastaus.equals("sakset")) {
                    stats.setAiTulokset(i, indeksi, -1);
                } else {
                    stats.setAiTulokset(i, indeksi, 1);
                }
            } else if (pelaajaVastaus.equals("sakset")) {
                if (aiVastaus.equals("paperi")) {
                    stats.setAiTulokset(i, indeksi, -1);
                } else {
                    stats.setAiTulokset(i, indeksi, 1);
                }
            } else {
                if (aiVastaus.equals("kivi")) {
                    stats.setAiTulokset(i, indeksi, -1);
                } else {
                    stats.setAiTulokset(i, indeksi, 1);
                }
            }
        }
    }
    
    int laskeParasAi() {
        /*arvotaan tekoälyistä se, joka pelaa tulevalla vuorolla
        jokainen ai saa 5 "lipuketta", jkokaisesta voitosta saa lipukkeen lisää,
        häviöstä menettää yhden ja tasapelistä ei ole muutosta */
        int[] lipukkeet=new int[4];
        int koko=0;
        int[][] tulokset=stats.getAiTulokset();
        for (int i=0; i<tulokset.length; i++) {
            lipukkeet[i]=5;
            koko+=5;
            for (int j=0; j<tulokset[0].length; j++) {
                int arvo=stats.getAiTulokset()[i][j];
                lipukkeet[i]+=arvo;
                koko+=arvo;
            }
        }
        int voittaja=r.nextInt(koko);
        if (voittaja>=lipukkeet[0]) return 1;
        if (voittaja>=lipukkeet[1]) return 2;
        if (voittaja>=lipukkeet[2]) return 3;
        else return 4;
    }
    
    //palautetaan koneen laskema vastaus
    String haeVastaus(int i) {
        return stats.getAiVastaukset()[i-1];
    }
}
