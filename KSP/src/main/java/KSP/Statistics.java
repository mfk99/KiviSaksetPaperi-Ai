
package KSP;

import static KSP.Game.satunnainenVastaus;
import java.util.ArrayList;
import java.util.Random;

public class Statistics {
    
    
    //aiVastaukset pit‰‰ teko‰lyjen vastauksia
    final private String aiVastaukset[];
    //pelaajaVastaukset pit‰‰ muistissa pelaajan aikaisempia vastauksia
    final private String pelaajaVastaukset[];
    //tulokset pit‰‰ arvoja teko‰lyjen tuloksista
    final private int aiTulokset[][];
    //tilastossa 0 on tasapeli, 1 pelaajan voitto ja 2 koneen voitto
    public static ArrayList<Integer> tulokset =new ArrayList<Integer>();
    
    public Statistics(int x) {
        aiVastaukset=new String[5];
        pelaajaVastaukset=new String[5];
        aiTulokset=new int[x][5];
        
        for (int i=0; i<5; i++) {
            pelaajaVastaukset[i]="";
            aiVastaukset[i]="";
        }
    }
    
    
    void setAiVastaukset (int i, String s) {
        aiVastaukset[i]=s;
    }
    
    String[] getAiVastaukset() {
        return aiVastaukset;
    }
    
    void setPelaajaVastaukset (int i, String s) {
        pelaajaVastaukset[i]=s;
    }
    
    String[] getPelaajaVastaukset() {
        return pelaajaVastaukset;
    }
    
    void setAiTulokset (int i, int j, int x) {
        aiTulokset[i][j]=x;
    }
    
    int[][] getAiTulokset() {
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
        AI tekoaly =new AI(4);
        Random r=new Random();
        ArrayList<Integer> testiTulokset=new ArrayList<>();
        String pelaajaVastaus="";
        for (int i=0; i<kerrat; i++) {
            int parasAi=0;
            String koneVastaus;
            int indeksi=i%5;
            if (i==0) {
                koneVastaus=satunnainenVastaus("");
                pelaajaVastaus=satunnainenVastaus("");
            }
            else {
                tekoaly.paivitaAi(indeksi, true);
                parasAi=tekoaly.laskeParasAi();
                koneVastaus=tekoaly.haeVastaus(parasAi);
                
                //katsotaan vaihdetaanko vastaus
                int random=r.nextInt(100);
                if (random>=samaVastaus) { //arvotaan uusi vastaus
                    pelaajaVastaus=Game.satunnainenVastaus(pelaajaVastaus);
                }
            }
            
            tekoaly.syotaVastaus(pelaajaVastaus, indeksi);
            if (i>0) tekoaly.laskeTulokset(pelaajaVastaus, indeksi);
            
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
        System.out.println("Testiss‰ kului yhteens‰ "+(testiAikaLoppu-testiAikaAlku)+" nanosekuntia");
    }
}
