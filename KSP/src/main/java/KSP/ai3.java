
package KSP;

public class ai3 {
    /*Ai numero 3 tulee pelaamaan vastauksen, 
    jolla voittaisi pelaajan viime vastauksen*/
    
    public ai3() {
    }
    
    static String vastaus(String[] pelaajanHistoria, int indeksi) {
        String viimeVastaus=pelaajanHistoria[indeksi];
        switch (viimeVastaus) {
            case "kivi":
                return "paperi";
            case "sakset":
                return "kivi";
            case "paperi":
                return "sakset";
        }
        return "";
    }
}
