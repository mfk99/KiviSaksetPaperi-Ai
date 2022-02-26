
package KSP;
import java.util.ArrayList;
import java.util.Scanner;

public class ui {
    public Game peli;
    public Statistics stats;
    private boolean admin=false;
    private static Scanner s;
    
    public ui(){
        peli=new Game();
        s=new Scanner(System.in);
    }
    
    void aloita() {
        boolean kaynnissa=true;
        stats=new Statistics(4);
        while (kaynnissa) {
            String kasky=ui.naytaVaihtoehdot();
                switch (kasky) {
                    case "play":
                        String ai=valitseAi();
                        peli.pelaa(Integer.valueOf(ai), admin);
                        break;
                    case "stats":
                        if (stats.getTulokset()==null) System.out.println("�,ao");
                        tulostaTilastot(stats.getTulokset());
                        break;
                    case "admin":
                        vaihdaTila();
                        break;
                    case "test":
                        int i=pyydaTestiMaara();
                        int j=pyydaTestiTodNak();
                        stats.test(i, j);
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
            System.out.println("play = Pelaa peli�");
            System.out.println("stats = N�yt� voittotilastot");
            System.out.println("admin = N�yt� mit� kone aikoo pelata sinua vastaan");
            System.out.println("test = testaa algoritmien nopeutta ja voittoprosenttia");
            System.out.println("exit = Poistu pelist�");
            syote=s.nextLine();
            if (syote.equalsIgnoreCase("play")) return syote;
            else if (syote.equalsIgnoreCase("stats")) return syote;
            else if (syote.equalsIgnoreCase("admin")) return syote;
            else if (syote.equalsIgnoreCase("test")) return syote;
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
            System.out.println("Valitse kivi(K) sakset(S) tai paperi(P) (poistu sy�tt�m�ll� e)");
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
            else if (vastaus.equalsIgnoreCase("e")) {
                return "exit";
            }
            System.out.println("Sy�t� validi vaihtoehto");
        }
    }
    
    static void tulostaTilastot(ArrayList <Integer> tilasto) {
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
    
    int pyydaTestiMaara() {
        System.out.println("Kuinka monta testipeli� pelataan?");
        return s.nextInt();
    }
    
    int pyydaTestiTodNak() {
        System.out.println("Mill� todenn�k�isyydell� valitaan sama vastaus per�kk�in? (%)");
        return s.nextInt();
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
