/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author RUMEYSA
 */
package g151210051; 

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URISyntaxException;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.Rule;


public class Mikrodalga {
    private FIS fis;
    private double urun_sicakligi;
    private double ortam_nemi;
    
    public Mikrodalga(double u, double o) throws URISyntaxException{
        this.urun_sicakligi = u;
        this.ortam_nemi = o;
        File dosya = new File(getClass().getResource("Mikrodalga.fcl").toURI());
        fis = FIS.load(dosya.getPath());
        fis.setVariable("urun_sicakligi", urun_sicakligi);
        fis.setVariable("ortam_nemi", ortam_nemi);
        fis.evaluate();
    }
//     public Mikrodalga() throws URISyntaxException{
//        File dosya = new File(getClass().getResource("Mikrodalga.fcl").toURI());
//        fis = FIS.load(dosya.getPath());
//    }
    public FIS getModel(){
        return fis;
    }
    
    @Override
    public String toString(){
        String cikti = urun_sicakligi+"     "+ortam_nemi+"      "+ fis.getVariable("calisma_sicakligi").getValue();
        
        try{
        File dosya=new File("dosya.txt");
        FileWriter yazici=new FileWriter(dosya,true);
        BufferedWriter yaz=new BufferedWriter(yazici);
        yaz.write(cikti);
        yaz.newLine();
        yaz.close();
        }
        catch(Exception hata){
            hata.printStackTrace();
        }
            
        return cikti;
    }
}
    
