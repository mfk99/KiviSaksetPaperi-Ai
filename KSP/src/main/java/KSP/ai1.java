
package KSP;

import static KSP.Game.satunnainenVastaus;

public class ai1 {
    /*Ai 1 pyrkii aina pelaamaan pelaajan 
    eniten pelaamaa valintaa vastaan */
    
    public ai1() {
    }
    
    static String vastaus(String[] pelaajanHistoria) {
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
        jos on pelattu eniten kiveä, se voitetaan paperilla jne.*/
        if (sakset<kivi && paperi<kivi) {
            return "paperi";
        }
        else if (kivi<sakset && paperi<sakset) {
            return "kivi";
        }
        else if (kivi<paperi && sakset<paperi) {
            return "sakset";
        }
        /*jos mikään ei ole kumpaakin suurempi, tällöin kaksi tai kaikki
        ovat yhtäsuuria, käydään mahdolliset lopputulokset läpi*/
        else {
            /*katsotaa ovatko kaikki yhtäsuuria, 
            jos on arvotaan satunnainen vastaus*/
            if (kivi==sakset&&sakset==paperi) {
                return satunnainenVastaus("");
            }
            /*katsotaan mitkä ovat yhtäsuuria
            jos paperia on pelattu vähiten, 
            tekoäly ei pelaa ainakaan sitä vastaan jne.*/
            else if (kivi==sakset) {
                return satunnainenVastaus("sakset");
            }
            else if (kivi==paperi) {
                return satunnainenVastaus("kivi");
            }
            else {
                return satunnainenVastaus("paperi");
            }
        }
    }
    
}
