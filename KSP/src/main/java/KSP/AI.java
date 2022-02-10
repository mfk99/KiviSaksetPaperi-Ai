
package KSP;

import static KSP.Main.satunnainenVastaus;

public class AI {
    
    //pelaajanHistoria pit‰‰ muistissa pelaajan aikaisempia vastauksia
    private String pelaajanHistoria[];
    //arvot pit‰‰ arvoja teko‰lyjen tuloksista
    private int arvot[][];
    //vastaukset pit‰‰ teko‰lyjen vastauksia
    private String[] vastaukset;
    
    public AI(int x) {
        pelaajanHistoria=new String[5];
        arvot=new int[x][5];
        vastaukset=new String[5];
        for (int i=0; i<5; i++) {
            pelaajanHistoria[i]="";
            vastaukset[i]="";
        }
    }
    
    //p‰ivitet‰‰n teko‰lyjen vastaukset
    void paivitaAi(int i) {
        if (i==0) i=4;
        ai1Vastaus();
        ai2Vastaus();
        ai3Vastaus(i);
        ai4Vastaus(i);
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
    
    /*Ai numero 1 pyrkii aina pelaamaan pelaajan 
    eniten pelaamaa valintaa vastaan */
    void ai1Vastaus() {
        String vastaus="";
        int kivi=0;
        int sakset=0;
        int paperi=0;
        for (int i=0; i<pelaajanHistoria.length; i++) {
            String pelaajaVastaus=pelaajanHistoria[i];
            switch (pelaajaVastaus) {
                case "":
                    break;
                case "kivi":
                    kivi++;
                    break;
                case "paperi":
                    paperi++;
                    break;
                case "sakset":
                    sakset++;
                    break;
            }
        }
        /*katsotaan onko yksi kahta isompi
        jos on pelattu eniten kive‰, se voitetaan paperilla jne.*/
        if (sakset<kivi && paperi<kivi) {
            vastaus="paperi";
        }
        else if (kivi<sakset && paperi<sakset) {
            vastaus="kivi";
        }
        else if (kivi<paperi && sakset<paperi) {
            vastaus="sakset";
        }
        /*jos mik‰‰n ei ole kumpaakin suurempi, t‰llˆin kaksi tai kaikki
        ovat yht‰suuria, k‰yd‰‰n mahdolliset lopputulokset l‰pi*/
        else {
            /*katsotaa ovatko kaikki yht‰suuria, 
            jos on arvotaan satunnainen vastaus*/
            if (kivi==sakset&&sakset==paperi) {
                vastaus=satunnainenVastaus("");
            }
            /*katsotaan mitk‰ ovat yht‰suuria
            jos paperia on pelattu v‰hiten, 
            teko‰ly ei pelaa ainakaan sit‰ vastaan jne.*/
            else if (kivi==sakset) {
                vastaus=satunnainenVastaus("sakset");
            }
            else if (kivi==paperi) {
                vastaus=satunnainenVastaus("kivi");
            }
            else {
                vastaus=satunnainenVastaus("paperi");
            }
        }
        //syˆtet‰‰n vastaus listaan
        vastaukset[0]=vastaus;
    }
    
    /*Ai numero 2 pyrkii aina pelaamaan pelaajan 
    v‰hiten pelaamaa valintaa vastaan */ 
    void ai2Vastaus() {
        String vastaus="";
        int kivi=0;
        int sakset=0;
        int paperi=0;
        for (int i=0; i<pelaajanHistoria.length; i++) {
            String pelaajaVastaus=pelaajanHistoria[i];
            switch (pelaajaVastaus) {
                case "":
                    break;
                case "kivi":
                    kivi++;
                    break;
                case "paperi":
                    paperi++;
                    break;
                case "sakset":
                    sakset++;
                    break;
            }
        }
        //katsotaan onko kiven arvo pienin
        if (kivi<sakset&&kivi<paperi) {
            vastaus="paperi";
        }
        //katsotaan onko sakset pienin
        else if(sakset<paperi&&sakset<kivi) {
            vastaus="kivi";
        }
        //katsotaan onko paperi pienin
        else if(paperi<kivi&&paperi<sakset) {
            vastaus="sakset";
        }
        else {
            //jotkin vastaukset yht‰suuria
            if (kivi==sakset&&sakset==paperi) {
                vastaus=satunnainenVastaus("");
            }
            else if (kivi==sakset) {
                vastaus=satunnainenVastaus("sakset");
            }
            else if (kivi==paperi){
                vastaus=satunnainenVastaus("kivi");
            }
            else {
                vastaus=satunnainenVastaus("paperi");
            }
        }
        vastaukset[1]=vastaus;
    }
    
    //Ai3 ei viel‰ toteutettu
    /*Ai numero 3 tulee pelaamaan vastauksen, 
    jolla voittaisi pelaajan viime vastauksen*/
    void ai3Vastaus(int indeksi) {
        if (indeksi==0) {
            indeksi=pelaajanHistoria.length-1;
        }
        String vastaus="";
        String viimeVastaus=pelaajanHistoria[indeksi];
        switch (viimeVastaus) {
            case "kivi":
                vastaus="paperi";
                break;
            case "sakset":
                vastaus="kivi";
                break;
            case "paperi":
                vastaus="sakset";
                break;
        }
        vastaukset[2]=vastaus;
    }
    
    //Ai4 ei viel‰ toteutettu
    /*Ai numero 4 tulee pelaamaan vastauksen, 
    jolla h‰vi‰isi pelaajan viime vastaukselle*/
    void ai4Vastaus(int indeksi) {
    if (indeksi==0) {
            indeksi=pelaajanHistoria.length+1;
        }
        String vastaus="";
        String viimeVastaus=pelaajanHistoria[indeksi];
        switch (viimeVastaus) {
            case "kivi":
                vastaus="sakset";
                break;
            case "sakset":
                vastaus="paperi";
                break;
            case "paperi":
                vastaus="kivi";
                break;
        }
        vastaukset[3]=vastaus;
    }
}
