
package KSP;

import static KSP.Main.pelaajanHistoria;
import static KSP.Main.satunnainenVastaus;

public class AI {
    
    private int arvot[][];
    
    public AI(int x) {
        arvot=new int[x][5];
    }
    
    //ei vielä toteutettu
    public static void paivitaAi() {
        
    }
    
    public static int laskeParasAi() {
        //lasketaan mikä ai voittaisi eniten pelejä viimeisen viiden pelin perusteella
        int suurin=0;
        int parasAi=0;
        for (int i=1; i<=3; i++) {
            
        }
        return parasAi;
    }
    
    public static String parasVastaus(int i) {
        String vastaus="";
        switch (i){
            case 1:
                vastaus=ai1Vastaus();
                break;
            case 2:
                vastaus=ai2Vastaus();
                break;
            /*case 3:
                vastaus=ai3Vastaus();
                break;*/
        }
        return vastaus;
    }
    
    /*Ai numero 1 pyrkii aina pelaamaan pelaajan 
    eniten pelaamaa valintaa vastaan */
    public static String ai1Vastaus() {
        int kivi=0;
        int sakset=0;
        int paperi=0;
        for (int i=0; i<pelaajanHistoria.length; i++) {
            int vastaus=pelaajanHistoria[i];
            switch (vastaus) {
                case 1:
                    kivi++;
                    break;
                case 2:
                    paperi++;
                    break;
                case 3:
                    sakset++;
                    break;
            }
        }
        //katsotaan onko yksi kahta isompi
        if (3<=kivi) return "kivi";
        if (3<=sakset) return "sakset";
        if (3<=paperi) return "paperi";
        /*jos mikään ei ole kolmea tai sitä suurempi, tällöin kaksi on 
        yhtä suurta jolloin arvotaa kumpi valitaan*/
        if (kivi==sakset) {
            return satunnainenVastaus("paperi");
        }
        if (kivi==paperi) {
            return satunnainenVastaus("sakset");
        }
        return satunnainenVastaus("kivi");
    }
    
    /*Ai numero 2 pyrkii aina pelaamaan pelaajan 
    vähiten pelaamaa valintaa vastaan */ 
    public static String ai2Vastaus() {
        int kivi=0;
        int sakset=0;
        int paperi=0;
        for (int i=0; i<pelaajanHistoria.length; i++) {
            int vastaus=pelaajanHistoria[i];
            switch (vastaus) {
                case 1:
                    kivi++;
                    break;
                case 2:
                    paperi++;
                    break;
                case 3:
                    sakset++;
                    break;
            }
        }
        //katsotaan onko kiven arvo pienin
        if (kivi<=sakset&&kivi<=paperi) {
            if (kivi==sakset) {
                return satunnainenVastaus("paperi");
            } else if(kivi==paperi) {
                return satunnainenVastaus("sakset");
            } else {
                return "kivi";
            }
        } 
        //katsotaan onko sakset pienin
        else if(sakset<=paperi) {
            if(sakset==paperi) {
                return satunnainenVastaus("kivi");
            } else {
                return "sakset";
            }
        } 
        //viimeinen mahdollinen pinein vaihtoehto on paperi
        else return "paperi";
    }
    
    //Ai3 ei vielä toteutettu
    /*Ai numero 3 tulee pelaamaan vastauksen, 
    jolla häviäisi pelaajan viime vastaukselle*/
    public static String ai3Vastaus() {
        return "";
    }
    
    //Ai4 ei vielä toteutettu
    /*Ai numero 3 tulee pelaamaan vastauksen, 
    jolla voittaisi pelaajan viime vastauksen*/
    public static String ai4Vastaus() {
        return "";
    }
}
