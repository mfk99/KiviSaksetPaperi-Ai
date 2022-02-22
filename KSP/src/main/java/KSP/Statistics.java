
package KSP;

import java.util.ArrayList;

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
}
