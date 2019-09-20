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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TransferFunctionType;

public class YSA {
    private static final File egitimDosya= new File(YSA.class.getResource("Egitim.txt").getPath());
    private static final File testDosya= new File(YSA.class.getResource("Test.txt").getPath());
    
    private double[] maksimumlar;
    private double[] minumumlar;
    private DataSet egitimVeriSeti;
    private DataSet testVeriSeti;
    private int araKatmanNoronSayisi;
    
    MomentumBackpropagation bp;
    
    public YSA(int araKatmanNoronSayisi,double momentum, double ogrenmeKatsayisi,double error,int epoch) throws FileNotFoundException
    {
        maksimumlar = new double[3];
        minumumlar = new double[3];
        
        for(int i=0;i<2;i++)
        {
            maksimumlar[i] = Double.MIN_VALUE;
            minumumlar[i] = Double.MAX_VALUE;
        }
        VeriSetiMaks(egitimDosya);
        VeriSetiMaks(testDosya);
        
        egitimVeriSeti = veriSeti(egitimDosya);
        testVeriSeti = veriSeti(testDosya);
        this.araKatmanNoronSayisi = araKatmanNoronSayisi;
        bp = new MomentumBackpropagation();
        bp.setMomentum(momentum);
        bp.setMaxError(error);
        bp.setMaxIterations(epoch);
        
    }
    private void VeriSetiMaks(File veriSeti) throws FileNotFoundException
    {
        Scanner oku = new Scanner(veriSeti);
        while(oku.hasNextDouble())
        {
            for(int i=0;i<2;i++)
            {
                double d= oku.nextDouble();
                if(d > maksimumlar[i]) maksimumlar[i] = d;
                if(d < minumumlar[i]) minumumlar[i] = d;
            }
            oku.nextDouble();
        }
        oku.close();
    }
    private double minmax(double x,double xmax,double xmin)
    {
        return(x-xmin)/(xmax-xmin);
    }
    private DataSet veriSeti(File dosya) throws FileNotFoundException
    {
        Scanner oku = new Scanner(dosya);
        DataSet veriseti = new DataSet(2,1);
        while(oku.hasNextDouble())
        {
            double []inputs = new double[2];
            for(int i=0;i<2;i++)
            {
                
                inputs[i]=minmax(oku.nextDouble(),maksimumlar[i],minumumlar[i]);
            }
            DataSetRow row = new DataSetRow(inputs,new double[]{minmax(oku.nextDouble(),maksimumlar[2],minumumlar[2])});
                    veriseti.addRow(row);
        }
        oku.close();
        return veriseti;
    }
    public void egit()
    {
        MultiLayerPerceptron sinirselAg = new  MultiLayerPerceptron(TransferFunctionType.SIGMOID,2,araKatmanNoronSayisi,1);
        sinirselAg.setLearningRule(bp);
        sinirselAg.learn(egitimVeriSeti);
        sinirselAg.save("ogrenenAg.nnet");
        System.out.println("Egitim Tamamlandı");
        
    }
    public double EgitimHata()
    {
        return bp.getTotalNetworkError();
    }
    private double mse(double[] beklenen,double[] cikti)
    {
        double toplamHata = 0;
        for (int i=0;i<3;i++)
        {
            toplamHata += Math.pow(beklenen[i]-cikti[i],2);
        }
        return toplamHata/3;
    }
    public double test()
    {
        NeuralNetwork sinirselAg = NeuralNetwork.createFromFile("ogrenenAg.nnet");
        double toplamHata = 0;
        for(DataSetRow row : testVeriSeti)
        {
            sinirselAg.setInput(row.getInput());
            sinirselAg.calculate();
            toplamHata += mse(row.getDesiredOutput(),sinirselAg.getOutput());
            
        }
        return toplamHata/testVeriSeti.size();
    }
    
    public String tekTest(double[] inputs)
    {
        for(int i=0;i<2;i++)
        {
            inputs[i] = minmax(inputs[i] , maksimumlar[i],minumumlar[i]);
        }
        NeuralNetwork sinirselAg = NeuralNetwork.createFromFile("ogrenenAg.nnet");
        sinirselAg.setInput(inputs);
        sinirselAg.calculate();
        return sinirselAg.getOutput()[0]+"";
    }
    
}
