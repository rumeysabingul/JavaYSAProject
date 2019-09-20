/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bulanikodev;

/**
 *
 * @author RUMEYSA
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class BulanikOdev {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        //ilk 14 test
        //sonrakiler Egitim
        //outputu hocanın anlattığı gibi ayırıyoruz.
        Scanner in = new Scanner(System.in);
        int araKatmanNoronSayisi;
        double momentum,ogrenmeKatsayisi,error;
        int epoch,sec=0;
        YSA ysa=null;
        do{
            System.out.println("1. Egitim ve Test Yap");
            System.out.println("2. Tekli Test Yap");
            System.out.println("3. Cikis");
            System.out.print("?=>");
            sec = in.nextInt();
            switch(sec)
            {
                case 1:
                    System.out.print("Ara Katman Noron Sayisi: ");
                    araKatmanNoronSayisi = in.nextInt();
                    System.out.print("Momentum: ");
                    momentum = in.nextDouble();
                    System.out.print("Ogrenme Kaysayisi: ");
                    ogrenmeKatsayisi = in.nextDouble();
                    System.out.print("Min Hata: ");
                    error = in.nextDouble();
                    System.out.print("Epoch Sayısı: ");
                    epoch = in.nextInt();
                    ysa = new YSA(araKatmanNoronSayisi, momentum , ogrenmeKatsayisi , error, epoch);
                    ysa.egit();
                    System.out.println("Egitim Sonunda Elde Edilen Hata : "+ysa.EgitimHata());
                    System.out.println("Test  Sonunda Elde Edilen Hata : "+ysa.test());
                    System.in.read();
                    break;
                case 2:
                    if(ysa ==null)
                    {
                        System.out.println("Once Egitim");
                        System.in.read();
                        break;
                    }
                    double []inputs = new double[8];
                    System.out.print("Urun Sicakligi: ");
                    inputs[0]=in.nextDouble();
                    System.out.print(": ");
                    inputs[1]=in.nextDouble();
                    System.out.print("Ortam Nemi: ");
                    inputs[2]=in.nextDouble();
                    System.out.print("Calisma Sicakli: "+ysa.tekTest(inputs));
                    System.in.read();
                    
                    break;
            }
        }while(sec != 3);
    }
    
            
}
