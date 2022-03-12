
package KSP;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author matti
 */
public class ui {

    /**
     *
     */
    public Game peli;

    /**
     *
     */
    public Statistics stats;
    private boolean admin=false;
    private static Scanner s;
    
    /**
     *
     */
    public ui(){
        peli=new Game();
        s=new Scanner(System.in);
    }
    
    void aloita() {
        boolean kaynnissa=true;
        //parametri on tekoälyn keskittymispituus
        stats=new Statistics(5);
        while (kaynnissa) {
            String kasky=ui.naytaVaihtoehdot();
                switch (kasky) {
                    case "play":
                        peli.pelaa(admin);
                        break;
                    case "stats":
                        stats.tulostaTilastot();
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
            System.out.println("Mitä haluat tehdä?");
            System.out.println("play = Pelaa peliä");
            System.out.println("stats = Näytä voittotilastot");
            System.out.println("admin = Näytä mitä kone aikoo pelata sinua vastaan");
            System.out.println("test = Testaa algoritmien nopeutta ja voittoprosenttia");
            System.out.println("exit = Poistu pelistä");
            syote=s.nextLine();
            if (syote.equalsIgnoreCase("play")) return syote;
            else if (syote.equalsIgnoreCase("stats")) return syote;
            else if (syote.equalsIgnoreCase("admin")) return syote;
            else if (syote.equalsIgnoreCase("test")) return syote;
            else if (syote.equalsIgnoreCase("exit")) return syote;
            else System.out.println("Syötä validi käsky");
            System.out.println("-----");
        }
    }
    
    static String pyydaPelaajaltaVastaus() {
        //pyydetään pelaajalta vastaus
        while (true) {
            System.out.println("Valitse kivi(K) sakset(S) tai paperi(P) (poistu syöttämällä E)");
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
            System.out.println("Syötä validi vaihtoehto");
        }
    }
    
    
    
    int pyydaTestiMaara() {
        System.out.println("Kuinka monta testipeliä pelataan?");
        return s.nextInt();
    }
    
    int pyydaTestiTodNak() {
        System.out.println("Millä todennäköisyydellä valitaan sama vastaus peräkkäin? (%)");
        return s.nextInt();
    }
    
    
    void vaihdaTila() {
        if (admin) {
            System.out.println("Admin on pois päältä");
            admin=false;
        }
        else {
            System.out.println("Admin on päällä");
            admin=true;
        }
        
    }
}
