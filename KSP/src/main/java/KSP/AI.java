
package KSP;

import static KSP.Game.satunnainenVastaus;


public class AI {
    
    //vastaukset pit‰‰ teko‰lyjen vastauksia
    private String vastaukset[];
    //pelaajanHistoria pit‰‰ muistissa pelaajan aikaisempia vastauksia
    private String pelaajanHistoria[];
    //arvot pit‰‰ arvoja teko‰lyjen tuloksista
    private int arvot[][];
    
    
    public AI(int x) {
        ai1 ai1=new ai1();
        ai2 ai2=new ai2();
        ai3 ai3=new ai3();
        ai4 ai4=new ai4();
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
        //lasketaan mik‰ ai voittaisi eniten pelej‰ viimeisen viiden pelin perusteella
        int suurin=-5;
        int parasAi=0;
        for (int i=0; i<arvot.length; i++) {
            int nyky=0;
            for (int j=0; j<5; j++) {
                nyky+=arvot[i][j];
            }
            if (suurin<nyky) {
                suurin=nyky;
                parasAi=i+1;
            }
        }
        return parasAi;
    }
    
    //palautetaan koneen laskema vastaus
    String haeVastaus(int i) {
        return vastaukset[i-1];
    }
}
