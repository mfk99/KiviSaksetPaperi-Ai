
package KSP;

import static KSP.Game.satunnainenVastaus;
import java.util.ArrayList;
import java.util.Random;

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
    public static ArrayList<Integer> tulokset =new ArrayList<Integer>();
    
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
        ArrayList<Integer> testiTulokset=new ArrayList<>();
        String pelaajaVastaus="";
        boolean pelaajaVoitti=false;
        boolean koneVoitti=false;
        for (int i=0; i<kerrat; i++) {
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
            
            tekoaly.syotaVastaus(indeksi, pelaajaVastaus);
            
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
                testiTulokset.add(0);
            }
            if (koneVoitti) {
                testiTulokset.add(1);
            }
            if (!pelaajaVoitti && !koneVoitti) {
                testiTulokset.add(2);
            }
            System.out.println("kone vastasi "+koneVastaus+", pelaaja vastasi "+pelaajaVastaus);
        }
        
        ui.tulostaTilastot(testiTulokset);
        long testiAikaLoppu=System.nanoTime();
        System.out.println("Testissä kului yhteensä "+(testiAikaLoppu-testiAikaAlku)+" nanosekuntia");
    }
}
