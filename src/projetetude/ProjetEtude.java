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
    
    
    public static LineColumn[] getCouple(double dataNumber,double lineNumber,double columnNumber){
        LineColumn[] result=new LineColumn[100];
        int index=0;
        
        for(int line=1;line<=lineNumber;line++){
            for(int column=1;column<=columnNumber;column++){
                if(column*line==dataNumber){
                    result[index]=new LineColumn(line, column);
                    index ++;
                }
            }
        }
        return result;
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
                       handledSugment[i]=sugmentIndex;
                       lineOrColumn="line";
                       System.out.println(counter+"mm");
                   }
                   if(sugmentDataNumber[counter]%columnsNumber==0 &&(!handled(handledSugment, i,counter))){
                       B=true;
                       sugmentIndex=counter;
                       handledSugment[counter]=sugmentIndex;
                       lineOrColumn="column";
                       System.out.println(counter+"mm");

                       
                   }
                   counter++;
               }
               if(B){
                   if(lineOrColumn.equals("line")){
                        double ColumnStart=startRemainingColumns;
                        double columnNumber=sugmentDataNumber[sugmentIndex]/linesNumber;
                       
                       double lineStart=startRemainingLines;
                       double lineNumber=linesNumber;
                       results[i]=new sugmentArea(sugmentIndex+1,sugmentDataNumber[sugmentIndex],lineStart, lineNumber,ColumnStart ,columnNumber );                      
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
                   boolean B2=false;
                   int counter2=0;
                   while (counter2<4 && !B2){
                       if(!handled(handledSugment, i, counter2)){
                           LineColumn LC1[]=getCouple(sugmentDataNumber[counter2], linesNumber, columnsNumber);
                           int counter3=0;
                           while(counter3<4 && !B2){
                               if(!handled(handledSugment, i, counter3) && counter3 != counter2){
                                 LineColumn LC2[]=getCouple(sugmentDataNumber[counter3], linesNumber, columnsNumber);
                                 int c=0;
                                 while(c<LC1.length && !B2){
                                     if(LC1[c]!=null){
                                         int c1=0;
                                         while(c1<LC2.length && !B2){
                                             if(LC2[c1]!=null){
                                                 if(LC1[c].line ==LC2[c1].line){
                                                     if((LC1[c].column + LC2[c1].column)!=columnsNumber){
                                                           double ColumnStart=startRemainingColumns;
                                                           
                                                           double lineStart=startRemainingLines;
                                                           results[i]=new sugmentArea(counter2,sugmentDataNumber[counter2],lineStart, LC1[c].line,ColumnStart ,LC1[c].column );                      
                                                           results[i]=new sugmentArea(counter3,sugmentDataNumber[counter3],lineStart, LC2[c1].line,ColumnStart+LC1[c].column ,LC2[c1].column );                      
                                                           startRemainingLines+=LC1[c].line;
                                                           B2=true;  
                                                           i++;
                                                     } 
                                                 }
                                                 else{
                                                     if(LC1[c].column ==LC2[c1].column){
                                                     if((LC1[c].line + LC2[c1].line)!=linesNumber){
                                                           double ColumnStart=startRemainingColumns;
                                                           
                                                           double lineStart=startRemainingLines;
                                                           results[i]=new sugmentArea(counter2,sugmentDataNumber[counter2],lineStart, LC1[c].line,ColumnStart ,LC1[c].column );                      
                                                           results[i]=new sugmentArea(counter3,sugmentDataNumber[counter3],lineStart, LC2[c1].line+LC1[c].line,ColumnStart+LC1[c].column ,LC2[c1].column );                      
                                                           startRemainingColumns+=LC1[c].column;
                                                           B2=true;  
                                                           i++;
                                                     } 
                                                 }
                                                     
                                                     
                                                 }
                                             }
                                             c1++;
                                         }
                                     }
                                     c++;
                                 }

                               }
                           }
                       }
                       counter2++;
                   }
                   
               }
           }
 
         return results;
    
       }  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                
        
        double[] percentages=new double[4];
        
            percentages[0]=80;
            percentages[1]= 4;
            percentages[2]=16;
            percentages[3]=0;
        
        
        
        sugmentArea[] res=function(25, percentages);
        
        for(int i=0;i<res.length;i++){
            res[i].displayInformation();
            
        }
        
        
        
        JFrame frame = new JFrame("Draw Squares");
        draw panel = new draw(25, res);
        frame.add(panel);
        frame.setSize(25 + 100, 25 + 30); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
       

        
       
            
    }
    
}
