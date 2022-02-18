
package KSP;

import java.util.Random;

public class AI {
    
    //vastaukset pit‰‰ teko‰lyjen vastauksia
    final private String vastaukset[];
    //pelaajanHistoria pit‰‰ muistissa pelaajan aikaisempia vastauksia
    final private String pelaajanHistoria[];
    //arvot pit‰‰ arvoja teko‰lyjen tuloksista
    final private int arvot[][];
    final private Random r;
    
    public AI(int x) {
        r=new Random();
        vastaukset=new String[5];
        pelaajanHistoria=new String[5];
        arvot=new int[x][5];
        for (int i=0; i<5; i++) {
            pelaajanHistoria[i]="";
            vastaukset[i]="";
        }
    }
    
    //p‰ivitet‰‰n teko‰lyjen vastaukset
    void paivitaAi(int i) {
        i--;
        //v‰ltet‰‰n nullPointerException
        if (i==-1) i=4;
        vastaukset[0]=ai1.vastaus(pelaajanHistoria);
        vastaukset[1]=ai2.vastaus(pelaajanHistoria);
        vastaukset[2]=ai3.vastaus(pelaajanHistoria, i);
        vastaukset[3]=ai4.vastaus(pelaajanHistoria, i);
    }
    
    /* syˆtt‰‰ vastauksen historia-taulukkoon, 
    hyv‰ pit‰‰ erill‰‰n laskeTulokset-metodista */
    void syotaVastaus(String pelaajaVastaus,int indeksi) {
        pelaajanHistoria[indeksi]=pelaajaVastaus;
    }
    
    //lasketaan teko‰lyjen vastauksien tulokset
    void laskeTulokset(String pelaajaVastaus, int indeksi) {
        for (int i=0; i<4; i++) {
            String aiVastaus=vastaukset[i];
            //katsotaan onko peli tasapeli
            if (aiVastaus.equals(pelaajaVastaus)) {
                arvot[i][indeksi]=0;
            }
            //peli ei ole tasapeli, katsotaan kumpi voitti
            else if (pelaajaVastaus.equals("kivi")) {
                if (aiVastaus.equals("sakset")) {
                    arvot[i][indeksi]=-1;
                } else {
                    arvot[i][indeksi]=1;
                }
            } else if (pelaajaVastaus.equals("sakset")) {
                if (aiVastaus.equals("paperi")) {
                    arvot[i][indeksi]=-1;
                } else {
                    arvot[i][indeksi]=1;
                }
            } else {
                if (aiVastaus.equals("kivi")) {
                    arvot[i][indeksi]=-1;
                } else {
                    arvot[i][indeksi]=1;
                }
            }
        }
    }
    
    int laskeParasAi() {
        /*arvotaan teko‰lyist‰ se, joka pelaa tulevalla vuorolla
        jokainen ai saa 5 "lipuketta", jkokaisesta voitosta saa lipukkeen lis‰‰,
        h‰viˆst‰ menett‰‰ yhden ja tasapelist‰ ei ole muutosta */
        int[] lipukkeet=new int[4];
        int koko=0;
        for (int i=0; i<arvot.length; i++) {
            lipukkeet[i]=5;
            koko+=5;
            for (int j=0; j<arvot[0].length; j++) {
                int arvo=arvot[i][j];
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
        return vastaukset[i-1];
    }
}
