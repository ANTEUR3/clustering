/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject1;
import org.apache.commons.math3.distribution.MultivariateNormalDistribution;
import org.apache.commons.math3.ml.clustering.DoublePoint;

import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;
/**
 *
 * @author Admin
 */


public class Mavenproject1 {
    
    public static int[] getCouples(int pixelsNumber ,int maxWidth,int maxHeight){
        int[] result=new int[200];
        int resultIndex=0;
        int center=(int)maxHeight/2;
        for(int i=0;i<center;i++)
        {
           if(pixelsNumber%(center-i)==0 && pixelsNumber/(center-i)<=maxWidth){
               result[resultIndex]=center-i;
               result[resultIndex+1]=pixelsNumber/(center-i);
               
               resultIndex+=2;
           }
            if(pixelsNumber%(center+i+1)==0 && pixelsNumber/(center+i+1)<=maxWidth){
               result[resultIndex]=center+i+1;
               result[resultIndex+1]=pixelsNumber/(center+i+1);
               resultIndex+=2;
           }
        }   
        if(maxHeight%2!=0){
            if(pixelsNumber%(center*2+1)==0 && pixelsNumber/(center*2+1)<=maxWidth){
               result[resultIndex]=center*2+1;
               result[resultIndex+1]=pixelsNumber/(center*2+1);
               resultIndex+=2;
           }
        }
      
        return result;
                
    }
    
    public static int[] checkposibility(int[] couples,lineState[] linesStates){
        int[] result=new int[4];
        for(int i=0;i<couples.length;i+=2){
            if(couples[i]!=0){
                  for(int j=0;j<=linesStates.length-couples[i];j++){
                  boolean B=true;
                  int k=0;
                  while(k<couples[i]&& B){
                      if(linesStates[j+k].columnNumber<couples[i+1]){
                          B=false;
                       }
                      k++;
                  }
                   if(B){
                       System.out.println("coupes "+couples[i]+" "+couples[i+1]);

                    result[0]=couples[i];//lines number
                    result[1]=couples[i+1];//column number
                    result[2]=j;//lines start index
                    result[3]=linesStates[j].columnStart;//column Start

                            
                    return result;
                   }
                
            }
            }  
        }
        result[0]=0;
        return result;
    }
    
    public static SugmentsPositions[] getPosotion(int[] sugmentPixels,int maxWidth, int maxHeight,int sugmentNumber){
        SugmentsPositions[] result=new SugmentsPositions[10000];
        int resultIndex=0;
        int[] handledSugments=new int[sugmentNumber];
        lineState[] linesStates=new lineState[maxHeight];
        // initialize the lines states
        for(int n=0;n<maxHeight;n++){
            linesStates[n]=new lineState(n, maxWidth);
        }
        // initialize handled sugments
        for(int j=0;j<sugmentNumber;j++){
            handledSugments[j]=0;
        }
        
        for(int i=0;i<sugmentNumber;i++){
            boolean B=false;
            int index=0;
            
            while(index<sugmentNumber && !B){
                if(handledSugments[index]==0){
                    int[] couples=getCouples(sugmentPixels[index], maxWidth, maxHeight);
                    index++;
                    int[] posibility=checkposibility(couples, linesStates);
                    if(posibility[0]!=0){
                        B=true;
                        for(int b=0;b<posibility[0];b++){
                            result[resultIndex]=new SugmentsPositions(posibility[2]+b,index,posibility[3],posibility[3]+posibility[1]-1);
                            linesStates[posibility[2]+b].columnNumber-=posibility[1];
                            if(linesStates[posibility[2]+b].columnStart==posibility[3]){
                                 linesStates[posibility[2]+b].columnStart+=posibility[1];

                            }else{
                                 linesStates[posibility[2]+b].columnEnd-=posibility[1];

                            }
                            handledSugments[index-1]=1;
                            resultIndex++;
                        }
                    }     
                } 
                index++;
            }
            if(!B){
                // if there isn't a sugment can be square or rectangle
                int index2=0;
                boolean B2=false;
                while(index2<sugmentNumber && !B2){
                    
                    if(handledSugments[index2]==0){
                        B2=true;
                    }
                    index2++;
                }
                int sugmentId=index2-1;
                int pixels=0;
                int line=0;
                while(pixels<sugmentPixels[sugmentId] && line<maxHeight){
                    if(linesStates[line].columnNumber<=sugmentPixels[sugmentId]-pixels){
                           result[resultIndex]=new SugmentsPositions(line,sugmentId+1,linesStates[line].columnStart,linesStates[line].columnStart+linesStates[line].columnNumber-1);
                           pixels+=linesStates[line].columnNumber;
                           resultIndex++;
                           line++;
                    }
                    else{
                           result[resultIndex]=new SugmentsPositions(line,sugmentId+1,linesStates[line].columnStart,linesStates[line].columnStart+sugmentPixels[sugmentId]-pixels-1);
                           pixels=sugmentPixels[sugmentId];
                           line++;
                           resultIndex++;    
                    }
                }
            }
        }
        
        
        return result;
    }

    public static void main(String[] args) {
        
        int[] r=getCouples(320,80,20);
        for(int i=0;i<r.length;i+=2){
            System.out.println("  "+r[i]+" "+r[i+1]);
        }

         Scanner scanner = new Scanner(System.in);
         System.out.print("please enter the Image width  ");
        // Read points number
      
         int Imagewidth=scanner.nextInt();
         System.out.print("please enter the image height ");
        // Read points number
         int ImageHeight=scanner.nextInt();
         
           int pointsNumber=ImageHeight*Imagewidth;
         
         System.out.print("please enter the sugments  number  ");
        // Read sugment number
         int numClusters =scanner.nextInt();
        
       
         double [] percentages=new double[numClusters];
         // Read the sugments percentages
         for(int i=0;i<numClusters;i++){
             System.out.print("Percentage of cluster "+(i+1)+" ");
             percentages[i]=scanner.nextInt();
         }
       
         
         // set the sugment point number based on their percentage
         int [] sugmentsPoints=new int[numClusters];
          for(int i=0;i<numClusters;i++){
             sugmentsPoints[i]=(int)(percentages[i]*pointsNumber)/100;
         }   
        int dimensions = 3; // 2D data

        RandomGenerator random = new Well19937c();

        // GMM parameters (randomly generated for example)
        double[] weights = new double[numClusters];
        MultivariateNormalDistribution[] distributions = new MultivariateNormalDistribution[numClusters];
        
        System.out.println("Clusters informations ! ");
        
        for (int i = 0; i < numClusters; i++) {
            System.out.println("Informations of cluster "+(i+1));
            weights[i] = random.nextDouble();

            double[] means = new double[dimensions];
            double[][] covariances = new double[dimensions][dimensions];

            // set means
            System.out.println("please enter means for this cluster");
            for (int j = 0; j < dimensions; j++) {
                System.out.println("mean "+(j+1)+" of cluster " + (i+1));
               means[j] = scanner.nextDouble(); // Adjust spread as needed
            }
            // set covariance 
            System.out.println("please enter covariance matrix  for this cluster");
            for (int j = 0; j < dimensions; j++) {
               
                for (int k = 0; k < dimensions; k++) {
                     System.out.println("line "+j+" column "+ k);
                     covariances[j][k] = scanner.nextDouble();
                }
            }
            
            boolean check=true;
            for(int m=0;m<dimensions;m++){
                if(covariances[m][m] <=0){
                    check=false;
                } 
            }
            double diagonalProduct=1;
            double otherBoxProduct=1;
             for(int d=0;d<dimensions;d++){
                for(int h=0;h<dimensions;h++){
                    if(d==h){
                        diagonalProduct*=covariances[h][h];
                    }
                    else{
                        otherBoxProduct*=covariances[d][h];
                    }
                }
            }
             if(diagonalProduct<=otherBoxProduct){
                 check=false;
             }
             
             if(!check){
                 System.out.println("There is an error in the covariance matrix please try again ");  
                 i--;
                 
             }
             else{
                  distributions[i] = new MultivariateNormalDistribution(means, covariances);

             } 
        }

        // Normalize weights
        double sumWeights = 0;
        for (double weight : weights) {
            sumWeights += weight;
        }
        for (int i = 0; i < weights.length; i++) {
            weights[i] /= sumWeights;
        }

        // Create the GMM
        GaussianMixtureModel gmm = new GaussianMixtureModel(weights, distributions);
        pixel[] pixels=new pixel[900000];
        int pixelsIndex=0;
        // Generate data points
        List<DoublePoint> dataPoints = new ArrayList<>();
        for (int i = 0; i < numClusters; i++) {
            for (int j = 0; j < sugmentsPoints[i]; j++) {
                double[] point = gmm.getComponents().get(i).getDistribution().sample();
                pixels[pixelsIndex]=new pixel(point[0],point[1],point[1]);  
                pixelsIndex++;
                dataPoints.add(new DoublePoint(point));
            }
            double[] separate=new double[2];
            separate[0]=-10000;
            separate[1]=61000;
            dataPoints.add(new DoublePoint(separate));
            pixels[pixelsIndex]=new pixel(-10000,-10000,-10000);  
            pixelsIndex++;

        }

     
        for(int i=0;i<10;i++){
            System.out.println("--"+pixels[i].R+" "+pixels[i].G+" "+pixels[i].B);
        }
        
        DoublePoint first =dataPoints.get(0);
        double[] Table=first.getPoint();    
        double minX=pixels[0].R;
        double maxX=pixels[0].R;
        double minY=pixels[0].G;
        double maxY=pixels[0].G;
         double minZ=pixels[0].B;
        double maxZ=pixels[0].B;
        for(int i=0;i<ImageHeight*Imagewidth+numClusters;i++){
            if(pixels[i]!=null){
                if(pixels[i].R!=-10000){
                   if(pixels[i].R>maxX){
                    maxX=pixels[i].R;
                }
                if(pixels[i].R<minX){
                    minX=pixels[i].R;
                } 
                
                if(pixels[i].G>maxY){
                    maxY=pixels[i].G;
                }
                if(pixels[i].G<minY){
                    minY=pixels[i].G;
                }
                
                   if(pixels[i].B>maxZ){
                    maxZ=pixels[i].B;
                }
                if(pixels[i].B<minZ){
                    minZ=pixels[i].B;
                }
                }
                
            }
        }
        
        
       
        for (DoublePoint point : dataPoints) {
            double[] coords = point.getPoint();
            if(coords[0]!=-10000){
                if(coords[1]>maxY){
                maxY=coords[1];
            }
             if(coords[1]<minY){
                minY=coords[1];
            }
            }
            
            //System.out.println(coords[0] + ", " + coords[1]);
        }
  
        
        double DistanceX=maxX-minX;
        double DistanceY=maxY-minY;
        double DistanceZ=maxZ-minZ;
        
         for(int i=0;i<ImageHeight*Imagewidth+numClusters;i++){
             if(pixels[i]!=null){
                 if(pixels[i].R!=-10000){
                     pixels[i].R=pixels[i].R-minX;
                     pixels[i].R= (int)(pixels[i].R*255/DistanceX);
                     
                     pixels[i].G=pixels[i].G-minY;
                     pixels[i].G= (int)(pixels[i].G*255/DistanceY);
                     
                     pixels[i].B=pixels[i].B-minZ;
                     pixels[i].B= (int)(pixels[i].B*255/DistanceZ);
                 }
             }
         }
          for(int i=0;i<10;i++){
            System.out.println("--"+pixels[i].R+" "+pixels[i].G+" "+pixels[i].B);
        }
        
         for (int i=0;i<dataPoints.size();i++) {
               DoublePoint item=dataPoints.get(i);
               double[] T=item.getPoint();
               if(T[0]!=-10000){
                    T[0]=T[0]-minX;
               T[0]=T[0]*255/DistanceX;
               dataPoints.get(i).getPoint()[0]=T[0]-(T[0]%1);
               
               T[1]=T[1]-minY;
               T[1]=T[1]*255/DistanceY;
               dataPoints.get(i).getPoint()[1]=T[1]-(T[1]%1);
               }
              
        }
         
         
        SugmentsPositions[] Positions=getPosotion(sugmentsPoints, Imagewidth, ImageHeight, numClusters);
        for(int h=0;h<Positions.length;h++){
            if(Positions[h]!=null){
                System.out.println(" "+Positions[h].lineId+" "+Positions[h].sugmentId+" "+Positions[h].columnStart+" "+Positions[h].columnEnd);

            }
        }
        
        
        JFrame frame = new JFrame("Draw Squares");
        draw panel = new draw( dataPoints,Imagewidth,ImageHeight,Positions,numClusters,pixels);
        frame.add(panel);
        frame.setSize(25 + 100, 25 + 30); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
         

    }
}
