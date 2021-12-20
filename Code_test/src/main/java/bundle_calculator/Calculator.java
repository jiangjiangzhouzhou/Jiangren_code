package bundle_calculator;

import java.util.List;
import java.util.ArrayList;
import lombok.Getter;
import lombok.extern.java.Log;

@Log
public class Calculator {
    @Getter private static Integer[] imgBundle = {5,10};
    private static double[]  imgPrice = {450, 800};

    @Getter private static Integer[] audioBundle = {3,6,9};
    private static double[]  audioPrice = {427.50, 810, 1147.50};

    @Getter private static Integer[] videoBundle = {3,5,9};
    private static double[]  videoPrice = {570, 900, 1530};


    public static void main(String[] args) {
        int imgNumber = 10;
        int audioNumber = 15;
        int videoNumber = 13;
        printInformation(imgPrice, getImgBundle(imgNumber, imgBundle), imgNumber, "IMG", imgBundle);
        printInformation(audioPrice, getBundle(audioNumber,audioBundle), audioNumber, "FLAC", audioBundle);
        printInformation(videoPrice, getBundle(videoNumber,videoBundle), videoNumber, "VID", videoBundle);
    }

    public static List<Integer> getImgBundle(int number, Integer[] numberofBundle) {
        ArrayList<Integer> bundles = new ArrayList<Integer>();
        if (number<= 0 ){
            throw new IllegalArgumentException("The number of items must be bigger than 0");
        }
        int small = 0;
        int big = 0;
        int previousNumber = 0;
        int currentNumber;
        int bigBundle = number/numberofBundle[0]+1;
        int smallBundle = number/numberofBundle[numberofBundle.length-1] + 1;
        if (number%numberofBundle[numberofBundle.length-1] == 0){
            big = number/numberofBundle[numberofBundle.length-1];
        }
        else{
            for(int i = smallBundle; i <= bigBundle; i++){
                for(int a = 0; a<= i ; a++){
                    currentNumber = a*numberofBundle[0] + (i-a)*numberofBundle[1];
                    if (previousNumber == 0 || (currentNumber>= number && currentNumber<previousNumber)){
                        previousNumber = currentNumber;
                        small = a;
                        big = i-a;
                    }
                }
            }
        }
        bundles.add(small);
        bundles.add(big);
        return bundles;
    }
    public static List<Integer> getBundle(int number, Integer[] numberofBundle) {
        ArrayList<Integer> bundles = new ArrayList<Integer>();
        if (number<= 0 ){
            throw new IllegalArgumentException("The number of items must be bigger than 0");
        }
        int small = 0;
        int middle = 0;
        int big = 0;
        int previousNumber = 0;
        int currentNumber;
        int bigBundle = number/numberofBundle[0]+1;
        int smallBundle = number/numberofBundle[numberofBundle.length-1] + 1;
        if (number%numberofBundle[numberofBundle.length-1] == 0){
            big = number/numberofBundle[numberofBundle.length-1];
        }
        else{
            for(int i = smallBundle; i <= bigBundle; i++){
                for(int a = 0; a<= i ; a++){
                    for (int b = 0; b<= i-a; b++){
                        currentNumber = a*numberofBundle[0] + b*numberofBundle[1] + (i-a-b)*numberofBundle[2];
                        if (previousNumber == 0 || (currentNumber>= number && currentNumber<previousNumber)){
                            previousNumber = currentNumber;
                            small = a;
                            middle = b;
                            big = i-a-b;
                        }
                    }
                }
            }
        }
        bundles.add(small);
        bundles.add(middle);
        bundles.add(big);
        return bundles;
    }

    public static void printInformation(double[] prices, List<Integer> bundles, int number, String label, Integer[] format){
        double cost = 0;
        for (int i=0; i < prices.length; i++){
            cost = prices[i]* bundles.get(i) + cost;
        }
        log.info( number + " " + label + " $" + cost);
        for (int f=0; f < format.length; f++){
            if (bundles.get(f)!=0){
                log.info(bundles.get(f) + " * " + format[f] + " $"+prices[f]* bundles.get(f));
            }
        }
    }
}
