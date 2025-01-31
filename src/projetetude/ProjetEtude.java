/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetetude;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
/**
 *
 * @author Admin
 */
public class ProjetEtude {
    
    
    public class DrawSquares extends JPanel {

    private int generalSquareSize;
    private double[][] widthHeight;

    public DrawSquares(int generalSquareSize, double[][] widthHeight) {
        this.generalSquareSize = generalSquareSize;
        this.widthHeight = widthHeight;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the general square
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, generalSquareSize, generalSquareSize);

        // Draw the smaller squares based on percentages
        for (double[] wh : widthHeight) {
            double widthPercentage = wh[0];
            double heightPercentage = wh[1];

            int smallSquareWidth = (int) (generalSquareSize * widthPercentage);
            int smallSquareHeight = (int) (generalSquareSize * heightPercentage);

            // Calculate random position within the general square (optional)
            Random rand = new Random();
            int x = rand.nextInt(generalSquareSize - smallSquareWidth);
            int y = rand.nextInt(generalSquareSize - smallSquareHeight);


            Color randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            g.setColor(randomColor);
            g.fillRect(x, y, smallSquareWidth, smallSquareHeight);

            g.setColor(Color.GRAY); // Small square border
            g.drawRect(x, y, smallSquareWidth, smallSquareHeight);
        }
    }
    }

    
    public static boolean handled(int[] indexes, int i,int index ){
        for(int j=0;j<i;j++){
            if(indexes[j]==index){
                       

                return true;
            }
        }
        return false;
        
    }
    
 public static sugmentArea[] function(int imageNumber,double[]percentages){
           double[]sugmentDataNumber=new double[4];
           
           for(int j=0;j<percentages.length;j++){
               double dataNumber=(percentages[j]*imageNumber)/100;
               sugmentDataNumber[j]=dataNumber;
           }
           
           
           double lineColumn=Math.sqrt(imageNumber);
           
           
           double startRemainingLines=0;
           double endRemainingLines=lineColumn-1;
           
           double startRemainingColumns=0;
           double endRemainingColumns=lineColumn-1;
           
           sugmentArea[] results=new sugmentArea[4];
           
           int[] handledSugment=new int[4];
           for (int i=0;i<4;i++){
                double linesNumber=endRemainingLines-startRemainingLines+1;
                double columnsNumber=endRemainingColumns-startRemainingColumns+1;

               boolean B=false;
               int counter=0;
               int sugmentIndex=-1;
               String lineOrColumn="";
               while( counter<4 && !B ){
                
                   if(sugmentDataNumber[counter]%linesNumber==0 &&(!handled(handledSugment, i,counter)) ){
                       B=true;
                       sugmentIndex=counter;
                       handledSugment[counter]=sugmentIndex;
                       lineOrColumn="line";
                       
                   }
                   if(sugmentDataNumber[counter]%columnsNumber==0 &&(!handled(handledSugment, i,counter))){
                       B=true;
                       sugmentIndex=counter;
                       handledSugment[counter]=sugmentIndex;
                       lineOrColumn="column";
                       
                   }
                   counter++;
               }
               if(B){
                   if(lineOrColumn.equals("line")){
                        double ColumnStart=startRemainingColumns;
                        double columnNumber=sugmentDataNumber[sugmentIndex]/linesNumber;
                       
                       double lineStart=startRemainingLines;
                       double lineNumber=linesNumber;
                       results[i]=new sugmentArea(sugmentIndex,sugmentDataNumber[sugmentIndex],lineStart, lineNumber,ColumnStart ,columnNumber );                      
                       startRemainingColumns+=columnNumber;
                       
                   }else{
                       double ColumnStart=startRemainingColumns;
                       double columnNumber=columnsNumber;
                       
                       double lineStart=startRemainingLines;
                       double lineNumber=sugmentDataNumber[sugmentIndex]/columnNumber;
                       results[i]=new sugmentArea(sugmentIndex,sugmentDataNumber[sugmentIndex],lineStart, lineNumber,ColumnStart ,columnNumber );                      
                       startRemainingLines+=lineNumber;
                       
                   }
               }
               else{
                   
               }
           }
 
         return results;
    
       }  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double[] percentages=new double[4];
        
            percentages[0]=40;
            percentages[1]= 20;
            percentages[2]=32;
            percentages[3]=8;
        
        
        
        sugmentArea[] res=function(25, percentages);
        
        for(int j=0 ;j<4;j++){
           res[j].displayInformation();

            
        }
        int generalSquareSize = 400; // Example size
        double[][] widthHeight = {
                {0.2, 0.3}, // 20% width, 30% height
                {0.5, 0.2}, // 50% width, 20% height
                {0.3, 0.6}, // 30% width, 60% height
                {0.1, 0.1}  // 10% width, 10% height
        };

        JFrame frame = new JFrame("Draw Squares");
        //DrawSquares panel = new DrawSquares(generalSquareSize, widthHeight);
        //frame.add(panel);
        frame.setSize(generalSquareSize + 10, generalSquareSize + 30); // Adjust for frame decorations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
       
            
    }
    
}
