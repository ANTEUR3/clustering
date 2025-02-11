/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sugmentgeneration;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class SugmentGeneration {
    
    public static int[] getCouples(int pixels, int linesNumber){
        int[]couples=new int[100];
        int indice=0;
        
        for(int j=0;j<100;j++){
            couples[j]=0;
        }
        
        for(int i=1;i<=linesNumber;i++){
            for(int j=1;j<=linesNumber;j++){
                if(i*j ==pixels){
                    couples[indice]=i;
                    couples[indice+1]=j;
                    indice+=2;
                }
            }
        }
        
        return couples;
    }
    
    //--------------------------------------------------------------------------
     public static int[] sugmentHandling(int[] sugmentState, int sugmentId){
         sugmentState[sugmentId]=1;
         
         return sugmentState;
              
     }
    
    //--------------------------------------------------------------------------
    
    public static int[] checkingPossibility(int[] couples, lineState[] linesState){
        
        int []result=new int[5];
        
        int cpl=0;
        boolean BCouples=false;
        while(couples[cpl]!=0 && !BCouples){
            int line=couples[cpl];
            int column=couples[cpl+1];
            int i=0;
            boolean B=false;
            while(i<((linesState.length-line)+1)&&!B){
                int counter=0;
                boolean B2=true;
                while(counter<line && B2){
                    if(linesState[i+counter].columnsNumber!=column){
                        B2=false;
                    }
                    counter++;
                }
                if(B2){
                     B=true; 
                     result[0]=i;
                     result[1]=line;
                     result[3]=linesState[i].columnStart;
                     result[4]=linesState[i].columnEnd;
                     return result;
                }
                i++;
            } 
            if(B){
                BCouples=true;
                 
                
            }
            cpl+=2;
        }
        result [0]=-1;
        result [1]=0;
        result [2]=0;
        result[3]=0;
        result[4]=0;
        return result;
    }
    // check possibility on columns 
    public static int[] checkingPossibility2(int[] couples, columnState[] columnsStates){
            
        
        int []result=new int[5];
        
        int cpl=0;
        boolean BCouples=false;
        while(couples[cpl]!=0 && !BCouples){
            
            int line=couples[cpl];
            int column=couples[cpl+1];
            
            int i=0;
            boolean B=false;
            while(i<=((columnsStates.length-column))&&!B){
                
                int counter=0;
                boolean B2=true;
                while(counter<column && B2){
                   
                    if(columnsStates[i+counter].lineNumber!=line){
                        B2=false;
                    }
                    counter++;
                }
                if(B2){
                     B=true; 
                     result[0]=columnsStates[i].lineStart;
                     result[1]=i;
                     result[2]=column;
                     System.out.println(result[0]+" "+result[1]+" "+result[2]+" ");
                     return result;
                }
                i++;
            } 
            if(B){
                BCouples=true;   
            }
            cpl+=2;
        }
        result [0]=-1;
        result [1]=0;
        result [2]=0;
        result[3]=0;
        result[4]=0;
        return result;
    }
    //-------------------------------------------------
        public static int[] checkingPossibility3(int[] couples, lineState[] linesState){
        
        int []result=new int[5];
        
        int cpl=0;
        boolean BCouples=false;
        while(couples[cpl]!=0 && !BCouples){
            int line=couples[cpl];
            int column=couples[cpl+1];
            int i=0;
            boolean B=false;
            while(i<((linesState.length-line)+1)&&!B){
                int counter=0;
                boolean B2=true;
                while(counter<line && B2){
                    if(linesState[i+counter].columnsNumber<column){
                        B2=false;
                    }
                    counter++;
                }
                if(B2){
                     B=true; 
                     result[0]=i;
                     result[1]=line;
                     result[2]=linesState[i].columnStart;
                     result[3]=column;
                     return result;
                }
                i++;
            } 
            if(B){
                BCouples=true;
                 
                
            }
            cpl+=2;
        }
        result [0]=-1;
        result [1]=0;
        result [2]=0;
        result[3]=0;
        result[4]=0;
        return result;
    }
        
            public static lineColumns[] checkingPossibility4(int pixelsNumber, lineState[] linesState){
            lineColumns[] linesColumns=new lineColumns[1000];
            int indice=0;
            
            int tmp=pixelsNumber;
            int counter=0;
            
            while(counter<linesState.length && tmp>0){
                if(linesState[counter].columnsNumber>0){
                    if(linesState[counter].columnsNumber<=tmp){
                    linesColumns[indice]=new lineColumns(counter, linesState[counter].columnStart, linesState[counter].columnEnd);
                    tmp-=linesState[counter].columnsNumber;
                    indice++;
                }
                else{
                        linesColumns[indice]=new lineColumns(counter, linesState[counter].columnStart, linesState[counter].columnStart+tmp-1);
                        tmp=0;
                        indice++;                  
                }
                }
                counter++;
            }

        return linesColumns;
                    
    }
    

    public static sugmentPosition[] getSugmentsPositions (int sugmentNumber , int linesNumber , int[] sugmentsPercentages){
        sugmentPosition[] result =new sugmentPosition[sugmentNumber];
        // lines states initialisation
        lineState[] linesStates=new lineState[linesNumber];
        for(int l=0;l<linesNumber;l++){
            linesStates[l]=new lineState(0,linesNumber-1, linesNumber);
        }
        
        // columns states initialisation
        columnState[] columnsStates=new columnState[linesNumber];
        for(int l=0;l<linesNumber;l++){
            columnsStates[l]=new columnState(0,linesNumber-1, linesNumber);
        }
        // sugments pixels number
        int[] sugmentsPixelsNumbers=new int[sugmentNumber];
        
        for(int j=0;j<sugmentNumber;j++){
            int pixels= sugmentsPercentages[j]*(linesNumber*linesNumber)/100;
            sugmentsPixelsNumbers[j]=pixels;  
        }
        //sugments states if sugment is handled or no
        int[] sugmentsStates=new int[sugmentNumber];
        for(int u=0;u<sugmentNumber;u++){
            sugmentsStates[u]=0;
        }
        for (int i=0;i<sugmentNumber;i++){
            int counter1=0;
            boolean B1=false;
            while(counter1<sugmentNumber && !B1){
                    if(sugmentsStates[counter1]==0){
                    int[] couples=getCouples(sugmentsPixelsNumbers[counter1], linesNumber);
                    int[] check =checkingPossibility(couples, linesStates);
                    if(check[0]!=-1){
                    B1=true;
                    lineColumns[] sugmentPos=new lineColumns[check[1]];
                    for(int pos=0;pos<check[1];pos++){
                        sugmentPos[pos]=new lineColumns(pos+check[0], check[3], check[4]); 
                        linesStates[pos+check[0]].columnsNumber=0;
                        //
                        for(int h=0;h<columnsStates.length;h++){
                            columnsStates[h].lineNumber-=1;
                            columnsStates[h].lineStart+=1;  
                        }
                    }
                     result[i]=new sugmentPosition(counter1+1, sugmentPos);
                     
                        sugmentsStates=sugmentHandling(sugmentsStates, counter1);
                 } 
                    }
                counter1++;
            }
            if(!B1){
                boolean B2=false;
                int counter2=0;
                while(counter2<sugmentNumber && !B2){
                    if(sugmentsStates[counter2]==0){
                    int[] couples=getCouples(sugmentsPixelsNumbers[counter2], linesNumber);
                    int[] check =checkingPossibility2(couples, columnsStates);
                    if(check[0]!=-1){
                    B2=true;
                    lineColumns[] sugmentPos=new lineColumns[linesNumber-check[0]];
                    int cc=0;
                    for(int pos=check[0];pos<linesNumber;pos++){
                        sugmentPos[cc]=new lineColumns(pos, check[1], check[1]+check[2]-1); 
                        linesStates[pos].columnsNumber-=check[2];
                        linesStates[pos].columnStart+=check[2];
                        cc++;
                    }
                    for(int p=0;p<check[2];p++){
                        columnsStates[check[1]+p].lineNumber-=(linesNumber-check[0]);
                    }
                     result[i]=new sugmentPosition(counter2+1, sugmentPos);
                     sugmentsStates=sugmentHandling(sugmentsStates, counter2);
                 } 
                    }
                counter2++;
            }  
                if(!B2){
                    boolean B3=false;
                    int counter3=0;
                    while(counter3<sugmentNumber && !B3){
                    if(sugmentsStates[counter3]==0){
                    int[] couples=getCouples(sugmentsPixelsNumbers[counter3], linesNumber);
                    int[] check =checkingPossibility3(couples, linesStates);
                    if(check[0]!=-1){
                    B3=true;
                    lineColumns[] sugmentPos=new lineColumns[check[1]];
                    int cc=0;
                    for(int pos=0;pos<check[1];pos++){
                        sugmentPos[cc]=new lineColumns(pos+check[0], check[2], check[2]+check[3]-1); 
                        linesStates[pos].columnsNumber-=check[3];
                        linesStates[pos].columnStart+=check[3];

                        
                        cc++;
                    }
                    
                    for(int z=0;z<check[3];z++){
                        columnsStates[check[2]+z].lineNumber-=check[1];
                        columnsStates[check[2]+z].lineStart+=check[1];
                    }
                    
                     result[i]=new sugmentPosition(counter3+1, sugmentPos);
                     sugmentsStates=sugmentHandling(sugmentsStates, counter3);
                 } 
                    }
                counter3++;
            } 
                    if(!B3){
                         int counter4=0;
                         boolean B4=false;
                         while(counter4<sugmentNumber && !B4){
                             
                         if(sugmentsStates[counter4]==0){
                             B4=true;
                    
                        lineColumns[] lc =checkingPossibility4(sugmentsPixelsNumbers[counter4], linesStates);
                        int lN=0;
                        while(lc[lN]!=null){
                            lN++; 
                        }
                        System.out.println("France   "+lN);
                        lineColumns[] LC=new lineColumns[lN];
                        for(int g=0;g<lN;g++){
                            System.out.println("******  "+lc[g].columnStart+"  "+lc[g].columnEnd);
                            LC[g]=new lineColumns(lc[g].line, lc[g].columnStart, lc[g].columnEnd);
                            linesStates[lc[g].line].columnsNumber-=lc[g].columnEnd-lc[g].columnStart+1;
                            linesStates[lc[g].line].columnStart+=lc[g].columnEnd-lc[g].columnStart+1;
                            int cn=lc[g].columnEnd-lc[g].columnStart+1;
                            
                            for(int b=0;b<cn;b++){
                                columnsStates[b+lc[g].columnStart].lineNumber-=1;
                                columnsStates[b+lc[g].columnStart].lineStart+=1;
                                
                            }
                        }
                         
                         result[i]=new sugmentPosition(counter4+1, LC);
                    }
                        counter4++;
                    }     
                    }
              }
            }     
            
        }
        
        for(int h=0;h<linesStates.length;h++){
            System.out.println(" "+linesStates[h].columnsNumber);
            System.out.println(linesStates[h].columnStart+"/"+linesStates[h].columnEnd);

            
        }
        return result;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt user
        System.out.print("please enter the number of sugment : ");
        // Read sugments number
        int sugmentsNumber = scanner.nextInt();
        
        int[] sugmentsPercentages=new int[10];
        // read sugment percentages 
        for(int i=0;i<sugmentsNumber;i++){
             System.out.print("please enter the percentage of sugment  "+(i+1)+": ");
        // Read sugment percentage
              sugmentsPercentages[i]=scanner.nextInt();
        }
         System.out.print("please enter the pixels number  ");
        // Read sugment percentage
         int pixelsNumber=scanner.nextInt();
         // define the number of columns and lines 
         double linesColumnsNumberD=Math.sqrt(pixelsNumber);
         int linesColumnsNumber=(int)linesColumnsNumberD;
         
         
         
         sugmentPosition[] S= getSugmentsPositions(sugmentsNumber,linesColumnsNumber,sugmentsPercentages);
         
         for(int ln=0;ln<sugmentsNumber;ln++){
             System.out.println(S[ln].id);
             for(int i=0;i<S[ln].linesColumns.length;i++){
                 System.out.println(S[ln].linesColumns[i].line);
                 System.out.println(S[ln].linesColumns[i].columnStart);
                 System.out.println(S[ln].linesColumns[i].columnEnd);
                 System.out.println("***");
                
             }
              System.out.println("---------------------------------");
         }
         
         
         
         
         
    }
    
}
