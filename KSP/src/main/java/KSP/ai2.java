
package KSP;

import static KSP.Game.satunnainenVastaus;

public class ai2 {
    /*Ai numero 2 pyrkii aina pelaamaan pelaajan 
    vähiten pelaamaa valintaa vastaan */ 
    
    public ai2() {
    }
    
    static String vastaus(int kivi, int sakset, int paperi) {
        
        //katsotaan onko kiven arvo pienin
        if (kivi<sakset&&kivi<paperi) {
            return "paperi";
        }
        //katsotaan onko sakset pienin
        else if(sakset<paperi&&sakset<kivi) {
            return "kivi";
        }
        //katsotaan onko paperi pienin
        else if(paperi<kivi&&paperi<sakset) {
            return "sakset";
        }
        else {
            //jotkin vastaukset yhtäsuuria
            if (kivi==sakset&&sakset==paperi) {
                return satunnainenVastaus("");
            }
            else if (kivi==sakset) {
                return satunnainenVastaus("sakset");
            }
            else if (kivi==paperi){
                return satunnainenVastaus("kivi");
            }
            else {
                return satunnainenVastaus("paperi");
            }
        }
    }
}
