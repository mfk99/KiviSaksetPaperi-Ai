
package KSP;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static AI tekoaly;
    public static int pelatutPelit=0;
    final public static int f=5;
    //f on määrä pelejä, joihin AI keskittyy
    public static ArrayList<Integer> tilasto =new ArrayList<Integer>();
    public static Integer[] pelaajanHistoria=new Integer[f];
    //pelaajaHistoriassa kivi=1, sakset=2 ja paperi=3
    public static Integer[][] aiTaulukko=new Integer[f][3];
    public static Scanner s=new Scanner(System.in);

    public static void main(String[] args) {
        AI ai=new AI(3);
        while (true) {
            naytaVaihtoehdot();
            String kasky=s.nextLine();
            
            if (kasky.equalsIgnoreCase("play")) pelaajaValitseeAi();
            else if (kasky.equalsIgnoreCase("stats")) naytaTilastot();
            else if (kasky.equalsIgnoreCase("exit")) break;
            else System.out.println("Syötä validi käsky");
            System.out.println("");
        }
    }
    
    static void naytaVaihtoehdot() {
        System.out.println("Mitä haluat tehdä?");
        System.out.println("Play = Pelaa peliä, Stats = Näytä voittotilastot, Exit = Poistu pelistä");
    }
    
    static void pelaajaValitseeAi() {
        System.out.println("Mitä tekoälyä vastaan haluat pelata? (Tällä hetkellä vain yksi, laajennetaan myöhemmin)");
        System.out.println("1. Täysin satunnainen \"tekoäly\", 2. Kolmen naiivn AI:n yhdistelmä");
        String kasky=s.nextLine();
        pelaa(Integer.valueOf(kasky));
    }
    
    static void pelaa(int ai) {
        //aloitetaan looppi ja jaetaan satunnainen vastaus koneelle
        while (true) {
            if (ai==2) {
                tekoaly.paivitaAi();
            }
            String pelaajaVastaus="";
            String koneVastaus="";
            /*mikäli ei ole tarpeeksi dataa tai pelataan täysin 
            satunnaisella tekoälyllä, valitaan satunnainen vastaus*/
            if (ai==1 || pelatutPelit<=4) {
                koneVastaus=satunnainenVastaus("");
            }
            //jos ollaan pelattu ainakin 5 peliä, lasketaan paras AI
            else {
                int parasAi=tekoaly.laskeParasAi();
                
            }
            //lasketaan mihin indeksiin valinta sijoitetaan
            int indeksi=pelatutPelit%f;
            //pyydetään pelaajalta vastaus
            
            while (true) {
                System.out.println("Valitse kivi(K) sakset(S) tai paperi(P)");
                pelaajaVastaus=s.nextLine();
                if (pelaajaVastaus.equalsIgnoreCase("K")) {
                    pelaajaVastaus="kivi";
                    pelaajanHistoria[indeksi]=1;
                    break;
                }
                else if (pelaajaVastaus.equalsIgnoreCase("S")) {
                    pelaajaVastaus="sakset";
                    pelaajanHistoria[indeksi]=2;
                    break;
                }
                else if (pelaajaVastaus.equalsIgnoreCase("P")) {
                    pelaajaVastaus="paperi";
                    pelaajanHistoria[indeksi]=3;
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

                
            System.out.println("Haluatko pelata uudelleen? (Y/N)");
            boolean loppu=false;
            while(true){
                String vastaus=s.nextLine();
                if (vastaus.equalsIgnoreCase("Y")) {
                    break;
                }
                if (vastaus.equalsIgnoreCase("N")) {
                    loppu=true;
                    break;
                }
            }
            if (loppu) break;
        }
    }

    static void naytaTilastot() {
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
    
    //palauttaa satunnaisen vastauksen, vastausta voi rajoittaa kahteen satunnaiseen
    public static String satunnainenVastaus(String rajoitus) {
        Random r=new Random();
        int satunnainen=0;
        if (rajoitus.equals("")) {
            satunnainen=r.nextInt(3);
        } else {
            satunnainen=r.nextInt(2);
        }
        if (satunnainen==0 && !rajoitus.equals("kivi")) {
            return ("kivi");
        } else if(satunnainen==1 && !rajoitus.equals("sakset")) {
            return ("sakset");
        } else {
            return ("paperi");
        }
    }
    
    public static int aiArvo(int ai) {
        int arvo=0;
        for (int i=0; i<f; i++) arvo+=aiTaulukko[i][ai-1];
        return arvo;
    }
    
}