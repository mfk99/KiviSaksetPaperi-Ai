
package KSP;

import java.util.Random;

public class AI {
    
    final private Random r;
    final private Statistics stats;
    
    public AI(int x) {
        r=new Random();
        stats=new Statistics(x);
    }
    
    //p‰ivitet‰‰n teko‰lyjen vastaukset
    void paivitaAi(int i, boolean test) {
        long aiAlku=0;
        if (test) {
            aiAlku=System.nanoTime();
        }
        i--;
        //v‰ltet‰‰n nullPointerException
        if (i==-1) i=4;
        String[] vastaukset=stats.getPelaajaVastaukset();
        int kivi=0;
        int sakset=0;
        int paperi=0;
        for (int j=0; j<vastaukset.length; j++) {
            String vastaus=vastaukset[j];
            if (vastaus.equals("kivi")) kivi++;
            else if (vastaus.equals("sakset")) sakset++;
            else paperi++;
        }
        if (test) {
            long ai1Alku=System.nanoTime();
            stats.setAiVastaukset(0, ai1.vastaus(kivi, sakset, paperi));
            long ai2Alku=System.nanoTime();
            stats.setAiVastaukset(1, ai2.vastaus(kivi, sakset, paperi));
            long ai3Alku=System.nanoTime();
            stats.setAiVastaukset(2, ai3.vastaus(stats.getPelaajaVastaukset(), i));
            long ai4Alku=System.nanoTime();
            stats.setAiVastaukset(3, ai4.vastaus(stats.getPelaajaVastaukset(), i));
            long aiLoppu=System.nanoTime();
            System.out.println("Ai 1 p‰ivityksess‰ meni "+(ai2Alku-ai1Alku)+" nanosekuntia");
            System.out.println("Ai 2 p‰ivityksess‰ meni "+(ai3Alku-ai2Alku)+" nanosekuntia");
            System.out.println("Ai 3 p‰ivityksess‰ meni "+(ai4Alku-ai3Alku)+" nanosekuntia");
            System.out.println("Ai 4 p‰ivityksess‰ meni "+(aiLoppu-ai4Alku)+" nanosekuntia");
            System.out.println("Koko p‰ivityksess‰ meni "+(aiLoppu-aiAlku)+" nanosekuntia");
            
        } else {
            stats.setAiVastaukset(0, ai1.vastaus(kivi, sakset, paperi));
            stats.setAiVastaukset(1, ai2.vastaus(kivi, sakset, paperi));
            stats.setAiVastaukset(2, ai3.vastaus(stats.getPelaajaVastaukset(), i));
            stats.setAiVastaukset(3, ai4.vastaus(stats.getPelaajaVastaukset(), i));
        }
    }
    
    void syotaVastaus(String pelaajaVastaus,int indeksi) {
        stats.setPelaajaVastaukset(indeksi, pelaajaVastaus);
    }
    
    //lasketaan teko‰lyjen vastauksien tulokset
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
    
    int laskeParasAi(int nykyinen, boolean voitto) {
        /*arvotaan teko‰lyist‰ se, joka pelaa tulevalla vuorolla
        jokainen ai saa 5 "lipuketta", jkokaisesta voitosta saa lipukkeen lis‰‰,
        h‰viˆst‰ menett‰‰ yhden ja tasapelist‰ ei ole muutosta */
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
        //jos ai on voittanut viime pelin, rohkaistaan saman ain k‰ytt‰mist‰
        if (voitto) {
            lipukkeet[nykyinen]+=koko;
            
            //siirret‰‰n muiden teko‰lyjen lipukkeiden arvoja
            for (int i=nykyinen; i<4; i++) {
                lipukkeet[i]+=koko;
            }
            koko+=koko;
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
