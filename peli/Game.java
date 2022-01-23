import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    
    public static ArrayList<Integer> tilasto =new ArrayList<Integer>();
    public static Scanner scanner=new Scanner(System.in);

    public static void main(String[] args) {
        
        while (true) {
            naytaVaihtoehdot();
            String kasky=scanner.nextLine();
            
            if (kasky.equalsIgnoreCase("play")) valitseAi();
            else if (kasky.equalsIgnoreCase("stats")) naytaTilastot();
            else if (kasky.equalsIgnoreCase("exit")) break;
            else System.out.println("Syötä validi käsky");
            System.out.println("");
        }
    }
    
    public static void naytaVaihtoehdot() {
        System.out.println("Mitä haluat tehdä?");
        System.out.println("Play = Pelaa peliä, Stats = Näytä voittotilastot, Exit = Poistu pelistä");
    }
    
    public static void valitseAi() {
        System.out.println("Mitä tekoälyä vastaan haluat pelata? (Tällä hetkellä vain yksi, laajennetaan myöhemmin)");
        System.out.println("1. Täysin satunnainen \"tekoäly\"");
        String kasky=scanner.nextLine();

        switch(kasky) {
            case "1":
                pelaa(1);
                break;
        }
    }
    
    public static void pelaa(int ai) {
        //aloitetaan looppi ja jaetaan satunnainen vastaus koneelle
        while (true) {
            String pelaajaVastaus="";
            String koneVastaus="";
            Random r=new Random();
            int i=r.nextInt(3);
            switch (i) {
                case 0:
                    koneVastaus="kivi";
                    break;
                case 1 :
                    koneVastaus="sakset";
                    break;
                case 2 :
                    koneVastaus="paperi";
                    break;
            }
            //pyydetään pelaajalta vastaus
            while (true) {
                System.out.println("Valitse kivi(K) sakset(S) tai paperi(P)");
                pelaajaVastaus=scanner.nextLine();
                if (pelaajaVastaus.equalsIgnoreCase("K")) {
                    pelaajaVastaus="kivi";
                    break;
                }
                else if (pelaajaVastaus.equalsIgnoreCase("S")) {
                    pelaajaVastaus="sakset";
                    break;
                }
                else if (pelaajaVastaus.equalsIgnoreCase("P")) {
                    pelaajaVastaus="paperi";
                    break;
                }
                System.out.println("Syötä validi vaihtoehto");
            }
            
            //katsotaan pelin tulos
            boolean pelaajaVoitti=false;
            boolean koneVoitti=false;
            if (pelaajaVastaus.equals(koneVastaus)) { //peli on tasapeli
                System.out.println("Kummatkin valitsivat "+pelaajaVastaus);
            }
            else { //peli ei ole tasapeli
                switch (pelaajaVastaus) {
                    case "kivi":
                        if (koneVastaus.equals("sakset")) {
                            pelaajaVoitti=true;
                        }
                        else {
                            koneVoitti=true;
                        }
                        break;
                    case "sakset":
                        if (koneVastaus.equals("paperi")) {
                            pelaajaVoitti=true;
                        }
                        else {
                            koneVoitti=true;
                        }
                        break;
                    case "paperi":
                        if (koneVastaus.equals("kivi")) {
                            pelaajaVoitti=true;
                        }
                        else {
                            koneVoitti=true;
                        }
                        break;
                }
                System.out.println("Pelaaja valitsi "+pelaajaVastaus+" ja kone valitsi "+koneVastaus);
            }
            
            //tilastoidaan vastaus ja tulostetaan tulos
            //tilastossa pelaajan voitto on 0, koneen voitto 1 ja tasapeli 2
            if (pelaajaVoitti) {
                tilasto.add(0);
                System.out.println("Pelaaja voitti!");
            }
            if (koneVoitti) {
                tilasto.add(1);
                System.out.println("Kone voitti!");
            }
            if (!pelaajaVoitti && !koneVoitti) {
                tilasto.add(2);
                System.out.println("Tasapeli!");
            }
            
            while (true) {
                System.out.println("Haluatko pelata uudelleen? (Y/N)");
                String vastaus=scanner.nextLine();
                if (vastaus.equalsIgnoreCase("N")) {
                    break;
                }
                if (vastaus.equalsIgnoreCase("Y")) {
                    pelaa(ai);
                    break;
                }
            }
            break;
        }
    }


    public static void naytaTilastot() {
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
}

