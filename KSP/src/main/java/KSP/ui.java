
package KSP;
import java.util.Scanner;
import java.util.ArrayList;

public class ui {
    public Game peli;
    
    private boolean admin=false;
    //tilastossa 0 on tasapeli, 1 pelaajan voitto ja 2 koneen voitto
    public static ArrayList<Integer> tilasto =new ArrayList<Integer>();
    
    //k�ytt�liittym�
    private static Scanner s;
    public ui(){
        peli=new Game();
        s=new Scanner(System.in);
    }
    
    void aloita() {
        boolean kaynnissa=true;
        while (kaynnissa) {
            String kasky=ui.naytaVaihtoehdot();
                switch (kasky) {
                    case "play":
                        String ai=valitseAi();
                        peli.pelaa(Integer.valueOf(ai), admin);
                        break;
                    case "stats":
                        tulostaTilastot(tilasto);
                        break;
                    case "admin":
                        vaihdaTila();
                        break;
                    case "exit":
                        kaynnissa=false;
                        break;
                }
            System.out.println("");
        }
    }
    
    static String naytaVaihtoehdot() {
        String syote="";
        while (true) {
            System.out.println("Mit� haluat tehd�?");
            System.out.println("Play = Pelaa peli�");
            System.out.println("Stats = N�yt� voittotilastot");
            System.out.println("Admin = N�yt� mit� kone aikoo pelata sinua vastaan");
            System.out.println("Exit = Poistu pelist�");
            syote=s.nextLine();
            if (syote.equalsIgnoreCase("play")) return syote;
            else if (syote.equalsIgnoreCase("stats")) return syote;
            else if (syote.equalsIgnoreCase("admin")) return syote;
            else if (syote.equalsIgnoreCase("exit")) return syote;
            else System.out.println("Sy�t� validi k�sky");
            System.out.println("");
        }
    }
    
    String valitseAi() {
        while (true) {
            System.out.println("Mit� teko�ly� vastaan haluat pelata?");
            System.out.println("1. T�ysin satunnainen \"teko�ly\", "
                    + "2. Nelj�n naiivn AI:n yhdistelm�");
            String kasky=s.nextLine();
            if (kasky.equals("1")||kasky.equals("2")) {
                return kasky;
            }
            System.out.println("Sy�t� validi k�sky");
        }
    }
    
    static String pyydaPelaajaltaVastaus() {
        //pyydet��n pelaajalta vastaus
        while (true) {
            System.out.println("Valitse kivi(K) sakset(S) tai paperi(P)");
            String vastaus=s.nextLine();
            if (vastaus.equalsIgnoreCase("K")) {
                return "kivi";
            }
            else if (vastaus.equalsIgnoreCase("S")) {
                return "sakset";
            }
            else if (vastaus.equalsIgnoreCase("P")) {
                return "paperi";
            }
            System.out.println("Sy�t� validi vaihtoehto");
        }
    }
    
    static boolean kysyUusiPeli() {
        while (true) {
            System.out.println("Haluatko pelata uudelleen? (Y/N)");
            String vastaus=s.nextLine();
            if (vastaus.equalsIgnoreCase("Y")) return true;
            else if (vastaus.equalsIgnoreCase("N")) return false;
            else System.out.println("Sy�t� validi k�sky");
        }
        
        
    }
    
    void tulostaTilastot(ArrayList <Integer> tilasto) {
        if (tilasto.isEmpty()) {
            System.out.println("Et ole pelannut yht��n peli�!");
        } //tarkistetaan onko pelej� pelattu
        else {
            float maara=tilasto.size();
            float pelaajaVoitot=0;
            float koneVoitot=0;
            float tasaPelit=0;
            float voittoMaara=0;
            
            for (int i=0; i<maara; i++) { //lasketaan pelien tulokset
                int tulos=tilasto.get(i);
                switch (tulos){
                    case 0:
                        pelaajaVoitot++;
                        voittoMaara++;
                        break;
                    case 1:
                        koneVoitot++;
                        voittoMaara++;
                        break;
                    case 2:
                        tasaPelit++;
                        break;
                }
            }
            //lasketaan prosentit
            float pelaajaVoittoProsentti= pelaajaVoitot/maara*100;
            float koneVoittoProsentti= koneVoitot/maara*100;
            float tasapeliProsentti=tasaPelit/maara*100;
            
            System.out.println("Pelej� on pelattu "+tilasto.size()+" kerta(a)");
            System.out.println("Pelaaja on voittanut "+pelaajaVoittoProsentti+" prosenttia peleist�");
            System.out.println("Kone on voittanut "+koneVoittoProsentti+" prosenttia peleist�");
            System.out.println("Tasapelej� on ollut "+tasapeliProsentti+" prosenttia peleist�");
            
            if (voittoMaara>0) { //katsotaan onko voittoja ollut, muuten koitetaan jakaa nollalla
                float pelaajaVsKone=pelaajaVoitot/voittoMaara*100;
                float koneVsPelaaja=koneVoitot/voittoMaara*100;
                
                System.out.println("Toisiinsa verrattuna pelaaja on voittanut "+pelaajaVsKone+
                        " ja kone on voittanut "+koneVsPelaaja+" prosenttia peleist�.");
            }
        }
    }
    
    void vaihdaTila() {
        if (admin) {
            System.out.println("Admin on pois p��lt�");
            admin=false;
        }
        else {
            System.out.println("Admin on p��ll�");
            admin=true;
        }
        
    }
    
    
}
