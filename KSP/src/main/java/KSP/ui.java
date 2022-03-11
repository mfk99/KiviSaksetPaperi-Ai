
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
        //parametri on tekoälyn keskittymispituus
        stats=new Statistics(5);
        while (kaynnissa) {
            String kasky=ui.naytaVaihtoehdot();
                switch (kasky) {
                    case "play":
                        peli.pelaa(admin);
                        break;
                    case "stats":
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
            System.out.println("Mitä haluat tehdä?");
            System.out.println("play = Pelaa peliä");
            System.out.println("stats = Näytä voittotilastot");
            System.out.println("admin = Näytä mitä kone aikoo pelata sinua vastaan");
            System.out.println("test = testaa algoritmien nopeutta ja voittoprosenttia");
            System.out.println("exit = Poistu pelistä");
            syote=s.nextLine();
            if (syote.equalsIgnoreCase("play")) return syote;
            else if (syote.equalsIgnoreCase("stats")) return syote;
            else if (syote.equalsIgnoreCase("admin")) return syote;
            else if (syote.equalsIgnoreCase("test")) return syote;
            else if (syote.equalsIgnoreCase("exit")) return syote;
            else System.out.println("Syötä validi käsky");
            System.out.println("");
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
    
    static void tulostaTilastot(ArrayList <Integer> tilasto) {
        if (tilasto.isEmpty()) {
            System.out.println("Et ole pelannut yhtään peliä!");
        } //tarkistetaan onko pelejä pelattu
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
            
            System.out.println("Pelejä on pelattu "+tilasto.size()+" kerta(a)");
            System.out.println("Pelaaja on voittanut "+pelaajaVoittoProsentti+" prosenttia peleistä");
            System.out.println("Kone on voittanut "+koneVoittoProsentti+" prosenttia peleistä");
            System.out.println("Tasapelejä on ollut "+tasapeliProsentti+" prosenttia peleistä");
            
            if (voittoMaara>0) { //katsotaan onko voittoja ollut, muuten koitetaan jakaa nollalla
                float pelaajaVsKone=pelaajaVoitot/voittoMaara*100;
                float koneVsPelaaja=koneVoitot/voittoMaara*100;
                
                System.out.println("Toisiinsa verrattuna pelaaja on voittanut "+pelaajaVsKone+
                        " ja kone on voittanut "+koneVsPelaaja+" prosenttia peleistä.");
            }
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
