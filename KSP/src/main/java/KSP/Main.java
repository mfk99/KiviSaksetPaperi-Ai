
package KSP;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static AI tekoaly;
    public static int pelatutPelit=0;
    private static boolean admin=false;
    //tilastossa 0 on tasapeli, 1 pelaajan voitto ja 2 koneen voitto
    public static ArrayList<Integer> tilasto =new ArrayList<Integer>();
    public static Scanner s=new Scanner(System.in);

    public static void main(String[] args) {
        tekoaly=new AI(4);
        while (true) {
            naytaVaihtoehdot();
            String kasky=s.nextLine();
            
            if (kasky.equalsIgnoreCase("play")) pelaajaValitseeAi();
            else if (kasky.equalsIgnoreCase("stats")) naytaTilastot();
            else if (kasky.equalsIgnoreCase("admin")) vaihdaTila();
            else if (kasky.equalsIgnoreCase("exit")) break;
            else System.out.println("Sy�t� validi k�sky");
            System.out.println("");
        }
    }
    
    static void vaihdaTila() {
        if (admin) {
            System.out.println("Admin on pois p��lt�");
            admin=false;
        }
        else {
            System.out.println("Admin on p��ll�");
            admin=true;
        }
        
    }
    
    static void naytaVaihtoehdot() {
        System.out.println("Mit� haluat tehd�?");
        System.out.println("Play = Pelaa peli�, Stats = N�yt� voittotilastot, Admin = N�yt� mit� kone aikoo pelata sinua vastaan Exit = Poistu pelist�");
    }
    
    static void pelaajaValitseeAi() {
        System.out.println("Mit� teko�ly� vastaan haluat pelata? (T�ll� hetkell� vain yksi, laajennetaan my�hemmin)");
        System.out.println("1. T�ysin satunnainen \"teko�ly\", 2. Nelj�n naiivn AI:n yhdistelm�");
        String kasky=s.nextLine();
        pelaa(Integer.valueOf(kasky));
    }
    
    static void pelaa(int ai) {
        //aloitetaan looppi ja jaetaan satunnainen vastaus koneelle
        while (true) {
            int parasAi=0;
            String pelaajaVastaus="";
            String koneVastaus;
            //lasketaan mihin indeksiin valinta sijoitetaan
            int indeksi=pelatutPelit%5;
            /*mik�li ei ole dataa tai pelataan t�ysin 
            satunnaisella teko�lyll�, valitaan satunnainen vastaus*/
            if (ai==1 || pelatutPelit<1) {
                koneVastaus=satunnainenVastaus("");
            }
            /*jos ollaan pelattu ainakin 1 peli, lasketaan paras AI
            ja asetetaan koneen vastaukseksi sen teko�lyn vastaus */
            else {
                tekoaly.paivitaAi(indeksi);
                parasAi=tekoaly.laskeParasAi();
                koneVastaus=tekoaly.haeVastaus(parasAi);
            }
            
            //n�ytet��n teko�ly ja sen vastaus jos admin on p��ll�
            if (admin) {
                if (ai==1 || pelatutPelit<1) {
                    System.out.println("Satunnainen ai aikoo valita k�den "+koneVastaus);
                }
                System.out.println("Ai nro "+parasAi+ " aikoo valita k�den "+koneVastaus);
            }
            
            //pyydet��n pelaajalta vastaus
            while (true) {
                System.out.println("Valitse kivi(K) sakset(S) tai paperi(P)");
                pelaajaVastaus=s.nextLine();
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
                System.out.println("Sy�t� validi vaihtoehto");
            }
            tekoaly.syotaVastaus(pelaajaVastaus, indeksi);
            if (pelatutPelit>0) tekoaly.laskeTulokset(pelaajaVastaus, indeksi);
            pelatutPelit++;
            
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
}