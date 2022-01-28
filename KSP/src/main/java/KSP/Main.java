
package KSP;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    
    public static int pelatutPelit=0;
    final public static int f=5;
    //f on määrä pelejä, joihin AI keskittyy
    public static ArrayList<Integer> tilasto =new ArrayList<Integer>();
    public static Integer[] pelaajanHistoria=new Integer[f];
    //pelaajaHistoriassa kivi=1, sakset=2 ja paperi=3
    public static Integer[][] aiTaulukko=new Integer[f][3];
    public static Scanner s=new Scanner(System.in);

    public static void main(String[] args) {
        
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
    
    public static void naytaVaihtoehdot() {
        System.out.println("Mitä haluat tehdä?");
        System.out.println("Play = Pelaa peliä, Stats = Näytä voittotilastot, Exit = Poistu pelistä");
    }
    
    public static void pelaajaValitseeAi() {
        System.out.println("Mitä tekoälyä vastaan haluat pelata? (Tällä hetkellä vain yksi, laajennetaan myöhemmin)");
        System.out.println("1. Täysin satunnainen \"tekoäly\", 2. Kolmen naiivn AI:n yhdistelmä");
        String kasky=s.nextLine();
        pelaa(Integer.valueOf(kasky));
    }
    
    public static void pelaa(int ai) {
        //aloitetaan looppi ja jaetaan satunnainen vastaus koneelle
        while (true) {
            if (ai==2) {
                paivitaAi();
            }
            String pelaajaVastaus="";
            String koneVastaus="";
            Random r=new Random();
            /*mikäli ei ole tarpeeksi dataa tai pelataan täysin 
            satunnaisella tekoälyllä, valitaan satunnainen vastaus*/
            if (ai==1 || pelatutPelit<=4) {
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
            }
            //jos ollaan pelattu ainakin 5 peliä, lasketaan paras AI
            else {
                int parasAi=laskeParasAi();
                
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
    
    //ei vielä toteutettu
    public static void paivitaAi() {
        
    }
    
    public static int laskeParasAi() {
        //lasketaan mikä ai voittaisi eniten pelejä viimeisen viiden pelin perusteella
        int suurin=0;
        int parasAi=0;
        int ai1=aiArvo(1);
        if (ai1>suurin) {
            suurin=ai1;
            parasAi=1;
        }
        int ai2=aiArvo(2);
        if (ai2>suurin) {
            suurin=ai2;
            parasAi=2;
        }
        int ai3=aiArvo(3);
        if (ai3>suurin) {
            suurin=ai3;
            parasAi=3;
        }
        return parasAi;
    }
    
    public static int aiArvo(int ai) {
        int arvo=0;
        for (int i=0; i<f; i++) arvo+=aiTaulukko[i][ai-1];
        return arvo;
    }
    
    public static String parasVastaus(int i) {
        String vastaus="";
        switch (i){
            case 1:
                vastaus=ai1Vastaus();
                break;
            case 2:
                vastaus=ai2Vastaus();
                break;
            /*case 3:
                vastaus=ai3Vastaus();
                break;*/
        }
        return vastaus;
    }
    
    /*Ai numero 1 pyrkii aina pelaamaan pelaajan 
    eniten pelaamaa valintaa vastaan */
    public static String ai1Vastaus() {
        int kivi=0;
        int sakset=0;
        int paperi=0;
        for (int i=0; i<pelaajanHistoria.length; i++) {
            int vastaus=pelaajanHistoria[i];
            switch (vastaus) {
                case 1:
                    kivi++;
                    break;
                case 2:
                    paperi++;
                    break;
                case 3:
                    sakset++;
                    break;
            }
        }
        //katsotaan onko yksi kahta isompi
        if (3<=kivi) return "kivi";
        if (3<=sakset) return "sakset";
        if (3<=paperi) return "paperi";
        /*jos mikään ei ole kolmea tai sitä suurempi, tällöin kaksi on 
        yhtä suurta jolloin arvotaa kumpi valitaan*/
        Random r = new Random();
        int kolikonHeitto=r.nextInt(1);
        if (kivi==sakset) {
            if (kolikonHeitto==0) return "kivi";
            return "sakset";
        }
        if (kivi==paperi) {
            if (kolikonHeitto==0) return "kivi";
            return "paperi"; 
        }
        if (kolikonHeitto==0) return "sakset";
        return "paperi";
    }
    
    /*Ai numero 2 pyrkii aina pelaamaan pelaajan 
    vähiten pelaamaa valintaa vastaan */ 
    public static String ai2Vastaus() {
        int kivi=0;
        int sakset=0;
        int paperi=0;
        for (int i=0; i<pelaajanHistoria.length; i++) {
            int vastaus=pelaajanHistoria[i];
            switch (vastaus) {
                case 1:
                    kivi++;
                    break;
                case 2:
                    paperi++;
                    break;
                case 3:
                    sakset++;
                    break;
            }
        }
        Random r=new Random();
        int kolikonHeitto=r.nextInt(1);
        if (kivi<=sakset&&kivi<=paperi) {
            if (kivi==sakset) {
                if (kolikonHeitto==0) return "kivi";
                return "sakset";
            } else if(kivi==paperi) {
                if (kolikonHeitto==0) return "kivi";
                return "paperi";
            } else {
                return "kivi";
            }
        } else if(sakset<=paperi) {
            if(sakset==paperi) {
                if (kolikonHeitto==0) return "sakset";
                return "paperi";
            } else {
                return "sakset";
            }
        } else return "paperi";
    }
    
    //Ai3 ei vielä toteutettu
    public static String ai3Vastaus() {
        return "";
    }
}
