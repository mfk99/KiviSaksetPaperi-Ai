
package KSP;

import static KSP.Game.satunnainenVastaus;
import java.util.Random;

/**
 *
 * @author matti
 */
public class AI {
    
    private int tila;
    private int ai1Tila;
    private int ai2Tila;
    private int ai3Tila;
    
    //matriisit pitävät tietoja pelaajan aikaisemmista pelaamiskuvioista
    private int[][]ai3v1Matriisi;
    private int[][]ai3v2Matriisi;
    
    private int pelatutPelit;
    final private Random r;
    final private Statistics stats;
    
    /**
     *
     */
    public AI() {
        r=new Random();
        tila=0;
        ai1Tila=0;
        ai2Tila=0;
        ai3Tila=0;
        ai3v1Matriisi=new int[3][9];
        ai3v2Matriisi=new int[3][27];
        pelatutPelit=0;
        stats=new Statistics(5);
    }
    
    //päivitetään tekoälyjen vastaukset
    void paivitaAi(int indeksi, boolean test) {
        long aiAlku=0;
        if (test) {
            aiAlku=System.nanoTime();
        }
        String[] vastaukset=stats.getPelaajaVastaukset();
        int kivi=0;
        int sakset=0;
        int paperi=0;
        for (int j=0; j<vastaukset.length; j++) {
            String vastaus=vastaukset[j];
            switch (vastaus) {
                case "kivi":
                    kivi++;
                    break;
                case "sakset":
                    sakset++;
                    break;
                default:
                    paperi++;
                    break;
            }
        }
        String pelaajaVastaus=stats.getPelaajaVastaukset()[indeksi];
        if (test) { //jos ollaan testaamassa otetaan aikaa kuinka pitkään algoritmeilla menee
            long ai1Alku=System.nanoTime();
            stats.setAiVastaukset(1,1,indeksi,  ai1v1Vastaus(indeksi));
            stats.setAiVastaukset(1,2,indeksi, ai1v2Vastaus(indeksi));
            long ai2Alku=System.nanoTime();
            stats.setAiVastaukset(2,1,indeksi, ai2v1Vastaus(kivi, sakset, paperi));
            stats.setAiVastaukset(2,2,indeksi, ai2v2Vastaus(kivi, sakset, paperi));
            long ai3Alku=System.nanoTime();
            stats.setAiVastaukset(3,1,indeksi, ai3v1Vastaus(stats.getPelaajaVastaukset(), indeksi));
            stats.setAiVastaukset(3,2,indeksi, ai3v2Vastaus(stats.getPelaajaVastaukset(), indeksi));
            long aiLoppu=System.nanoTime();
            System.out.println("Ai 1 päivityksessä meni "+(ai2Alku-ai1Alku)+" nanosekuntia");
            System.out.println("Ai 2 päivityksessä meni "+(ai3Alku-ai2Alku)+" nanosekuntia");
            System.out.println("Ai 3 päivityksessä meni "+(aiLoppu-ai3Alku)+" nanosekuntia");
            System.out.println("Koko päivityksessä meni "+(aiLoppu-aiAlku)+" nanosekuntia");
            
        } else {
            stats.setAiVastaukset(1,1,indeksi, ai1v1Vastaus(indeksi));
            stats.setAiVastaukset(1,2,indeksi, ai1v2Vastaus(indeksi));
            stats.setAiVastaukset(2,1,indeksi, ai2v1Vastaus(kivi, sakset, paperi));
            stats.setAiVastaukset(2,2,indeksi, ai2v2Vastaus(kivi, sakset, paperi));
            stats.setAiVastaukset(3,1,indeksi, ai3v1Vastaus(stats.getPelaajaVastaukset(), indeksi));
            stats.setAiVastaukset(3,2,indeksi, ai3v2Vastaus(stats.getPelaajaVastaukset(), indeksi));
        }
        pelatutPelit++;
    }
    
    void syotaVastaus(int indeksi, String pelaajaVastaus) {
        stats.setPelaajaVastaukset(indeksi, pelaajaVastaus);
    }
    
    
    
    int laskeParasAi() {
        /* valitaan tekoälyistä se, joka on voittanut eniten pelejä viimeisen
        viiden kierroksen aikana, mikäli on tasapeli arvotaan niiden välillä */
        laskeTulokset();
        paivitaTilat();
        return tila;
    }
    
    //lasketaan tekoälyjen vastauksien tulokset
    void laskeTulokset() {
        String[] pelaajaVastaukset=stats.getPelaajaVastaukset();
        for (int ai=0; ai<3; ai++) {
            String[][] AiVastaukset=stats.getAiVastaukset(ai+1);
                for (int versio=0; versio<2; versio++) {
                    int arvo=0;
                    for (int j=0; j<AiVastaukset[0].length; j++) {
                    String aiVastaus=AiVastaukset[versio][j];
                    String pelaajaVastaus=pelaajaVastaukset[j];
                    //jos peli ei ole tasapeli, lasketaan tulos
                    if (!aiVastaus.equals(pelaajaVastaus)) {
                        //peli ei ole tasapeli, katsotaan kumpi voitti
                        switch (pelaajaVastaus) {
                            case "kivi":
                                if (aiVastaus.equals("sakset")) {
                                    System.out.println("Ai"+ai+"v"+versio+" h�visi k�delle "+pelaajaVastaus+" k�dell� "+aiVastaus);
                                    arvo--;
                                } else {
                                    System.out.println("Ai"+ai+"v"+versio+" voitti k�den "+pelaajaVastaus+" k�dell� "+aiVastaus);
                                    arvo++;
                                }   
                                break;
                            case "sakset":
                                if (aiVastaus.equals("paperi")) {
                                    System.out.println("Ai"+ai+"v"+versio+" h�visi k�delle "+pelaajaVastaus+" k�dell� "+aiVastaus);
                                    arvo--;
                                } else {
                                    System.out.println("Ai"+ai+"v"+versio+" voitti k�den "+pelaajaVastaus+" k�dell� "+aiVastaus);
                                    arvo++;
                                }   
                                break;
                            default: //pelaajaVastaus==paperi
                                if (aiVastaus.equals("kivi")) {
                                    System.out.println("Ai"+ai+"v"+versio+" h�visi k�delle "+pelaajaVastaus+" k�dell� "+aiVastaus);
                                    arvo--;
                                } else {
                                    System.out.println("Ai"+ai+"v"+versio+" voitti k�den "+pelaajaVastaus+" k�dell� "+aiVastaus);
                                    arvo++;
                                }   
                                break;
                        }
                    } else {
                        System.out.println("Ai"+ai+"v"+versio+" tuli tasapeli pelaajan "+pelaajaVastaus+" k�dell� "+aiVastaus);
                    }
                }
            stats.setAiTulokset(ai*2+versio, arvo);
            }
        }
    }
    
    //päivitetään tekoälyjen tilat kuvastamaan parhaiten menestyvää versiota
    void paivitaTilat() {
        //päivitetään yksittäisten tekoälyjen tilat
        for (int ai=0; ai<3; ai++) {
            int v1Arvo=stats.getAiTulokset()[ai*2];
            System.out.println("ai"+ai+"v1Arvo="+v1Arvo);
            int v2Arvo=stats.getAiTulokset()[ai*2+1];
            System.out.println("ai"+ai+"v2Arvo="+v2Arvo);
            if (v2Arvo<v1Arvo) {
                setAiTila(ai+1, 1);
            } else if (v1Arvo<v2Arvo) {
                setAiTila(ai+1, 2);
            } else {
                setAiTila(ai+1, r.nextInt(2)+1);
            }
        }
        //päivitetään koko tekoälyn tila
        int [] tulokset=stats.getAiTulokset();
        int ai1Arvo=tulokset[ai1Tila-1];
        int ai2Arvo=tulokset[ai2Tila+1];
        int ai3Arvo=tulokset[ai3Tila+3];
        System.out.println("ai1v"+ai1Tila+" arvo="+ai1Arvo);
        System.out.println("ai2v"+ai2Tila+" arvo="+ai2Arvo);
        System.out.println("ai3v"+ai3Tila+" arvo="+ai3Arvo);
        if (ai2Arvo<ai1Arvo && ai3Arvo<ai1Arvo) {
            tila=1;
        } else if (ai1Arvo<ai2Arvo && ai3Arvo<ai2Arvo) {
            tila=2;
        } else if (ai1Arvo<ai3Arvo && ai1Arvo<ai3Arvo){
            tila=3;
        } else { //suurin arvo on jaettu kahden tai kolmen tekoälyn välillä
            if (ai1Arvo==ai2Arvo && ai2Arvo==ai3Arvo) {
                tila=r.nextInt(3)+1;
            } else if (ai1Arvo==ai2Arvo) {
                tila=r.nextInt(2)+1;
            } else if (ai2Arvo==ai3Arvo) {
                tila=r.nextInt(2)+2;
            } else { //ai1Arvo==ai3Arvo
                tila=r.nextInt(2);
                switch (tila) {
                    case 0:
                        tila=1;
                        break;
                    case 1:
                        tila=3;
                        break;
                }
            }
        }
    }
    
    //ai1 pelaa viime vastauksen mukaan sille häviävän tai voittavan käden
    
    //v1 pelaa käden jolla voitetaan viime vastausta vastaan
    String ai1v1Vastaus(int indeksi) {
        indeksi--;
        if (indeksi<0) indeksi=4;
        String viimeVastaus=stats.getPelaajaVastaukset()[indeksi];
        System.out.println("viimeVastaus="+viimeVastaus);
        if (viimeVastaus.equals("kivi")) return "paperi";
        if (viimeVastaus.equals("sakset")) return "kivi";
        return "paperi";
    }
    
    //v2 pelaa käden jolla hävitään viime vastausta vastaan
    String ai1v2Vastaus(int indeksi) {
        indeksi--;
        if (indeksi<0) indeksi=4;
        String viimeVastaus=stats.getPelaajaVastaukset()[indeksi];
        if (viimeVastaus.equals("kivi")) return "sakset";
        if (viimeVastaus.equals("sakset")) return "paperi";
        return "kivi";
    }
    
    
    /* ai2 pelaa viimeisen viiden vastauksen mukaan eniten 
    tai vähiten pelattua kättä vastaan */
    
    //v1 pelaa eniten pelattua vastaan
    String ai2v1Vastaus(int kivi, int sakset, int paperi) {
        if (sakset<kivi && paperi<kivi) return "paperi";
        if (kivi<sakset && paperi<sakset) return "kivi";
        if (kivi<paperi && sakset<paperi) return "sakset";
        //jos jotkin ovat yhtäsuuria
        if (kivi==sakset && kivi==paperi) return satunnainenVastaus("");
        if (kivi==sakset) return satunnainenVastaus("sakset");
        if (kivi==paperi) return satunnainenVastaus("kivi");
        return satunnainenVastaus("paperi");
    }
    
    //v2 pelaa vähiten pelattua vastaan
    String ai2v2Vastaus(int kivi, int sakset, int paperi) {
        if (kivi<sakset && kivi<paperi) return "kivi";
        if (sakset<kivi && sakset<paperi) return "sakset";
        if (sakset<kivi && paperi<sakset) return "paperi";
        //jos jotkin ovat yhtäsuuria
        if (kivi==sakset && kivi==paperi) return satunnainenVastaus("");
        if (kivi==sakset) return satunnainenVastaus("sakset");
        if (kivi==paperi) return satunnainenVastaus("kivi");
        return satunnainenVastaus("paperi");
    }
    
    /* ai3 etsii pelaajan käyttämiä vastauskuvioita
    ja pelaa niitä vastaan etäisyyksillä 2-3 */
    
    //etäisyys 2
    String ai3v1Vastaus(String[] historia, int indeksi) {
        
        //jos ei ole tarpeeksi dataa, annetaan satunnainen vastaus
        if (pelatutPelit<3) return satunnainenVastaus("");
        String pelaajaVastaus1="";
        String pelaajaVastaus2="";
        String viimeVastaus="";
        //katsotaan kolme viimeisintä vastausta ja lisätään ne matriisiin
        for (int i=0; i<2; i++) {
            indeksi--;
            if (indeksi<0) indeksi=4;
            switch (i) {
                case 0:
                    viimeVastaus=stats.getPelaajaVastaukset()[indeksi];
                    break;
                case 1:
                    pelaajaVastaus2=stats.getPelaajaVastaukset()[indeksi];
                    break;
                case 2:
                    pelaajaVastaus1=stats.getPelaajaVastaukset()[indeksi];
                    break;
            }
        }
        
        //lasketaan minkä indeksin arvoa kuuluu nostaa
        int aikaisemmatArvo=0;
        switch (pelaajaVastaus1) {
            case "kivi":
                //aikaisemmatArvo+=0;
                break;
            case "sakset":
                aikaisemmatArvo+=3;
                break;
            case "paperi":
                aikaisemmatArvo+=6;
                break;
        }
        switch (pelaajaVastaus2) {
            case "kivi":
                //aikaisemmatArvo+=0;
                break;
            case "sakset":
                aikaisemmatArvo+=1;
                break;
            case "paperi":
                aikaisemmatArvo+=2;
                break;
        }
        
        //katsotaan mitä pelattiin viime pelissä
        int arvo=0;
        switch (viimeVastaus) {
            case "kivi":
                break;
            case "sakset":
                arvo=1;
                break;
            case "paperi":
                arvo=2;
                break;
        }
        
        ai3v1Matriisi[arvo][aikaisemmatArvo]++;
        
        /* arvojen päivittämisen jälkeen annetaan todennäköisin vastaus, jos 
        jotkin vastaukset ovat yhtä todennäköisiä arvotaan niiden välillä */
        
        int kiviArvo=ai3v1Matriisi[0][aikaisemmatArvo];
        int saksetArvo=ai3v1Matriisi[1][aikaisemmatArvo];
        int paperiArvo=ai3v1Matriisi[2][aikaisemmatArvo];
        //arvotaan vastaus jos jotkin arvot ovat yhtäsuuria
        if (kiviArvo==saksetArvo && kiviArvo==paperiArvo) return satunnainenVastaus("");
        if (kiviArvo==saksetArvo) return satunnainenVastaus("paperi");
        if (kiviArvo==paperiArvo) return satunnainenVastaus("sakset");
        if (paperiArvo==saksetArvo) return satunnainenVastaus("kivi");
        
        int tulos=r.nextInt(kiviArvo+saksetArvo+paperiArvo);
        if (tulos<kiviArvo) return "kivi";
        else if (tulos<kiviArvo+saksetArvo) return "sakset";
        else return "paperi";
    }
    
    //etäisyys 3
    String ai3v2Vastaus(String[] historia, int indeksi) {
        
        /*algoritmin runko on samanlainen kuin 3v1, 
        paitsi lasketaan yksi aikaisempi vastaus */
        if (pelatutPelit<4) return satunnainenVastaus("");
        String pelaajaVastaus1="";
        String pelaajaVastaus2="";
        String pelaajaVastaus3="";
        String viimeVastaus="";
        //katsotaan neljä viimeisintä vastausta ja lisätään ne matriisiin
        for (int i=0; i<3; i++) {
            indeksi--;
            if (indeksi<0) indeksi=4;
            switch (i) {
                case 0:
                    viimeVastaus=stats.getPelaajaVastaukset()[indeksi];
                    break;
                case 1:
                    pelaajaVastaus3=stats.getPelaajaVastaukset()[indeksi];
                    break;
                case 2:
                    pelaajaVastaus2=stats.getPelaajaVastaukset()[indeksi];
                    break;
                case 3:
                    pelaajaVastaus1=stats.getPelaajaVastaukset()[indeksi];
                    break;
            }
        }
        
        //lasketaan minkä indeksin arvoa kuuluu nostaa
        int aikaisemmatArvo=0;
        switch (pelaajaVastaus1) {
            case "kivi":
                //aikaisemmatArvo+=0;
                break;
            case "sakset":
                aikaisemmatArvo+=9;
                break;
            case "paperi":
                aikaisemmatArvo+=18;
                break;
        }
        switch (pelaajaVastaus2) {
            case "kivi":
                //aikaisemmatArvo+=0;
                break;
            case "sakset":
                aikaisemmatArvo+=3;
                break;
            case "paperi":
                aikaisemmatArvo+=6;
                break;
        }
        switch (pelaajaVastaus3) {
            case "kivi":
                //aikaisemmatArvo+=0;
                break;
            case "sakset":
                aikaisemmatArvo+=1;
                break;
            case "paperi":
                aikaisemmatArvo+=2;
                break;
        }
        
        //katsotaan mitä pelattiin viime pelissä
        int arvo=0;
        switch (viimeVastaus) {
            case "kivi":
                break;
            case "sakset":
                arvo=1;
                break;
            case "paperi":
                arvo=2;
                break;
        }
        
        ai3v2Matriisi[arvo][aikaisemmatArvo]++;
        
        /* arvojen päivittämisen jälkeen annetaan todennäköisin vastaus, jos 
        jotkin vastaukset ovat yhtä todennäköisiä arvotaan niiden välillä */
        
        int kiviArvo=ai3v2Matriisi[0][aikaisemmatArvo];
        int saksetArvo=ai3v2Matriisi[1][aikaisemmatArvo];
        int paperiArvo=ai3v2Matriisi[2][aikaisemmatArvo];
        //arvotaan vastaus jos jotkin arvot ovat yhtäsuuria
        if (kiviArvo==saksetArvo && kiviArvo==paperiArvo) return satunnainenVastaus("");
        if (kiviArvo==saksetArvo) return satunnainenVastaus("paperi");
        if (kiviArvo==paperiArvo) return satunnainenVastaus("sakset");
        if (paperiArvo==saksetArvo) return satunnainenVastaus("kivi");
        
        int tulos=r.nextInt(kiviArvo+saksetArvo+paperiArvo);
        if (tulos<kiviArvo) return "kivi";
        else if (tulos<kiviArvo+saksetArvo) return "sakset";
        else return "paperi";
    }
    
    //palautetaan koneen laskema vastaus
    String haeVastaus(int ai) {
        int versio=getAiTila(ai)-1;
        return stats.getAiVastaukset(ai) [versio][pelatutPelit%5];
    }
    
    void setAiTila (int ai, int x) {
        switch (ai) {
            case 0:
                tila=x;
                break;
            case 1:
                ai1Tila=x;
                break;
            case 2:
                ai2Tila=x;
                break;
            case 3:
                ai3Tila=x;
                break;
        }
    }
    
    int getAiTila(int ai){
        if (ai==0) return tila;
        if (ai==1) return ai1Tila;
        if (ai==2) return ai2Tila;
        if (ai==3) return ai3Tila;
        else return 0;
    }
}