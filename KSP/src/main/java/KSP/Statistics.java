
package KSP;

import static KSP.Game.satunnainenVastaus;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author matti
 */
public class Statistics {
    
    //aiVastaukset pitävät eri tekoälyjen vastauksia
    final private String ai1Vastaukset[][];
    final private String ai2Vastaukset[][];
    final private String ai3Vastaukset[][];
    //pelaajaVastaukset pitää muistissa pelaajan aikaisempia vastauksia
    final private String pelaajaVastaukset[];
    //tulokset pitää arvoja tekoälyjen tuloksista
    final private int aiTulokset[];
    //tilastossa 0 on tasapeli, 1 pelaajan voitto ja 2 koneen voitto

    /**
     *
     */
    public static ArrayList<Integer> tulokset =new ArrayList<Integer>();
    
    /**
     *
     * @param focus
     */
    public Statistics(int focus) {
        ai1Vastaukset=new String[2][5];
        ai2Vastaukset=new String[2][5];
        ai3Vastaukset=new String[2][5];
        pelaajaVastaukset=new String[focus];
        aiTulokset=new int[6];
        
        for (int i=0; i<focus; i++) {
            pelaajaVastaukset[i]="";
        }
    }
    
    
    void setAiVastaukset (int ai, int v, int indeksi, String s) {
        v--;
        if (ai==1) ai1Vastaukset[v][indeksi]=s;
        if (ai==2) ai2Vastaukset[v][indeksi]=s;
        if (ai==3) ai3Vastaukset[v][indeksi]=s;
    }
    
    String[][] getAiVastaukset(int ai) {
        if (ai==1) return ai1Vastaukset;
        if (ai==2) return ai2Vastaukset;
        if (ai==3) return ai3Vastaukset;
        return null;
    }
    
    void setPelaajaVastaukset (int i, String s) {
        pelaajaVastaukset[i]=s;
    }
    
    String[] getPelaajaVastaukset() {
        return pelaajaVastaukset;
    }
    
    void setAiTulokset (int i, int x) {
        aiTulokset[i]=x;
    }
    
    int[] getAiTulokset() {
        return  aiTulokset;
    }
    
    void addTulokset(int x) {
        tulokset.add(x);
    }
    
    
    ArrayList getTulokset () {
        return tulokset;
    }
    
    void test(int kerrat, int samaVastaus) {
        //metodi keskiarvoisten voittoprosenttien ja aikojen laskemiseen
        long testiAikaAlku=System.nanoTime();
        AI tekoaly =new AI();
        Random r=new Random();
        tulokset=new ArrayList<>();
        String pelaajaVastaus="";
        for (int i=0; i<kerrat; i++) {
            System.out.println("peli nro "+i);
            int parasAi=0;
            String koneVastaus="";
            int indeksi=i%5;
            
            //annetaan pelaajalle vastaus
            if (i==0) { //jos ensimm�inen kierros, arvotaan satunnainen
                pelaajaVastaus=satunnainenVastaus("");
            } else { //muulloin arvotaan vaihdetaanko vastaus
                int vaihto=r.nextInt(100);
                if (vaihto>samaVastaus) pelaajaVastaus=satunnainenVastaus(pelaajaVastaus);
            }
            
            tekoaly.syotaVastaus(indeksi, pelaajaVastaus);
            tekoaly.paivitaAi(indeksi, true);
            
            //vaihdetaan teko�ly jos on pelattu viisi peli�
            if (i!=0 && i%5==0) {
                parasAi=tekoaly.laskeParasAi();
                System.out.println("Paras ai on "+parasAi);
            }
            //annetaan teko�lylle vastaus
            if (parasAi!=0) {
                koneVastaus=tekoaly.haeVastaus(tekoaly.getAiTila(parasAi));
            } else { 
                koneVastaus=satunnainenVastaus("");
            }
            
            boolean pelaajaVoitti=false;
            boolean koneVoitti=false;
            //katsotaan pelin tulos
            if (pelaajaVastaus.equals(koneVastaus)) { //peli on tasapeli
                System.out.println("Kummatkin valitsivat "+pelaajaVastaus);
            } else { //peli ei ole tasapeli
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
            }
            
            //tilastoidaan vastaus, tilastossa pelaajan voitto on 0, koneen voitto 1 ja tasapeli 2
            if (pelaajaVoitti) {
                tulokset.add(0);
            }
            if (koneVoitti) {
                tulokset.add(1);
            }
            if (!pelaajaVoitti && !koneVoitti) {
                tulokset.add(2);
            }
            System.out.println("tilaston koko"+tulokset.size());
            System.out.println("kone vastasi "+koneVastaus+", pelaaja vastasi "+pelaajaVastaus);
            
        }
        
        tulostaTilastot();
        long testiAikaLoppu=System.nanoTime();
        System.out.println("Testissä kului yhteensä "+(testiAikaLoppu-testiAikaAlku)+" nanosekuntia");
    }
    
    void tulostaTilastot() {
        if (tulokset.isEmpty()) {
            System.out.println("Et ole pelannut yhtään peliä!");
        } //tarkistetaan onko pelejä pelattu
        else {
            float maara=tulokset.size();
            float pelaajaVoitot=0;
            float koneVoitot=0;
            float tasaPelit=0;
            float voittoMaara=0;
            
            for (int i=0; i<maara; i++) { //lasketaan pelien tulokset
                int tulos=tulokset.get(i);
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
            
            System.out.println("Pelejä on pelattu "+tulokset.size()+" kerta(a)");
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
