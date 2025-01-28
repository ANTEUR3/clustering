/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetetude;

/**
 *
 * @author Admin
 */
public class ProjetEtude {
    
    public static boolean handled(int[] indexes, int i,int index ){
        for(int j=0;j<i;j++){
            if(indexes[j]==index){
                       

                return true;
            }
        }
        return false;
        
    }
    
 public static sugmentArea[] function(int imageNumber,int[]percentages){
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
                       results[i]=new sugmentArea(sugmentIndex,lineStart, lineNumber,ColumnStart ,columnNumber );                      
                       startRemainingColumns+=columnNumber;
                       
                   }else{
                       double ColumnStart=startRemainingColumns;
                       double columnNumber=columnsNumber;
                       
                       double lineStart=startRemainingLines;
                       double lineNumber=sugmentDataNumber[sugmentIndex]/columnNumber;
                       results[i]=new sugmentArea(sugmentIndex,lineStart, lineNumber,ColumnStart ,columnNumber );                      
                       startRemainingLines+=lineNumber;
                       
                   }
               }
           }
 
         return results;
    
       }  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] percentages=new int[4];
        
            percentages[0]=20;
            percentages[1]=48;
            percentages[2]=16;
            percentages[3]=16;
        
        
        
        sugmentArea[] res=function(25, percentages);
        
        for(int j=0 ;j<4;j++){
           res[j].displayInformation();

            
        }
       
            
    }
    
}
