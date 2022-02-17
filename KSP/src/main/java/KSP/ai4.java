
package KSP;

public class ai4 {
    /*Ai numero 4 tulee pelaamaan vastauksen, 
    jolla häviäisi pelaajan viime vastaukselle*/
    
    public ai4() {
    }
    
    static String vastaus(String[] pelaajanHistoria, int indeksi) {
        String viimeVastaus=pelaajanHistoria[indeksi];
        switch (viimeVastaus) {
            case "kivi":
                return "sakset";
            case "sakset":
                return "paperi";
            case "paperi":
                return "kivi";
        }
        return "";
    }
}
