/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g151210051;

/**
 *
 * @author RUMEYSA
 */

import java.net.URISyntaxException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

public class G151210051 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    double u=0,o=0;
        for(int i=0;i<3000;i++){
            Random r=new Random();
            Random r1=new Random();
           u = 18 + (103 - 18) * r.nextDouble();
           o = 91 * r.nextDouble();
            try {
            Mikrodalga m = new Mikrodalga(u,o);
//            JFuzzyChart.get().chart(m.getModel());
            System.out.println(m);
            } 
            catch (URISyntaxException ex) {
                
            System.out.print("HATA");
            }
        }
    }   
}
