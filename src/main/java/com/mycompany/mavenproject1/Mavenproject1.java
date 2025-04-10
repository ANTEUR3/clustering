/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject1;
import org.apache.commons.math3.distribution.MultivariateNormalDistribution;
import org.apache.commons.math3.ml.clustering.Cluster;
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
    
    

    public static void main(String[] args) {
        
        
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
         double [] sugmentsPoints=new double[numClusters];
          for(int i=0;i<numClusters;i++){
             sugmentsPoints[i]=(percentages[i]*pointsNumber)/100;
         }   
        int dimensions = 2; // 2D data

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

        // Generate data points
        List<DoublePoint> dataPoints = new ArrayList<>();
        for (int i = 0; i < numClusters; i++) {
            for (int j = 0; j < sugmentsPoints[i]; j++) {
                double[] point = gmm.getComponents().get(i).getDistribution().sample();
                dataPoints.add(new DoublePoint(point));
            }

        }

        // Output the generated 2D data
        
        for (DoublePoint point : dataPoints) {
            double[] T=point.getPoint();
            System.out.println("   "+T[0]+"/"+T[1]);
            //System.out.println(coords[0] + ", " + coords[1]);
        }
        
        DoublePoint first =dataPoints.get(0);
        double[] Table=first.getPoint();    
        double minX=Table[0];
        double maxX=Table[0];
        for (DoublePoint point : dataPoints) {
            double[] coords = point.getPoint();
            if(coords[0]>maxX){
                maxX=coords[0];
            }
             if(coords[0]<minX){
                minX=coords[0];
            }
            //System.out.println(coords[0] + ", " + coords[1]);
        }
        
        double minY=Table[1];
        double maxY=Table[1];
        for (DoublePoint point : dataPoints) {
            double[] coords = point.getPoint();
            if(coords[1]>maxY){
                maxY=coords[1];
            }
             if(coords[1]<minY){
                minY=coords[1];
            }
            //System.out.println(coords[0] + ", " + coords[1]);
        }
  
        
        double DistanceX=maxX-minX;
        double DistanceY=maxY-minY;
        
         for (int i=0;i<dataPoints.size();i++) {
               DoublePoint item=dataPoints.get(i);
               double[] T=item.getPoint();
               T[0]=T[0]-minX;
               T[0]=T[0]*255/DistanceX;
               dataPoints.get(i).getPoint()[0]=T[0]-(T[0]%1);
               
               T[1]=T[1]-minY;
               T[1]=T[1]*255/DistanceY;
               dataPoints.get(i).getPoint()[1]=T[1]-(T[1]%1);
        }
         
         int pixelNumberSquareRoot=(int)Math.sqrt(pointsNumber);
         
         JFrame frame = new JFrame("Draw Squares");
        draw panel = new draw(pixelNumberSquareRoot, dataPoints,Imagewidth,ImageHeight);
        frame.add(panel);
        frame.setSize(25 + 100, 25 + 30); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
         

    }
}
