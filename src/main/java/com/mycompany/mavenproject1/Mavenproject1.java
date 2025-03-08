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
import javax.swing.JFrame;
/**
 *
 * @author Admin
 */


public class Mavenproject1 {
    
    

    public static void main(String[] args) {
         int numClusters = 5; // Number of Gaussian components
        int numPointsPerCluster = 10000; // Points per component
        int dimensions = 2; // 2D data

        RandomGenerator random = new Well19937c();

        // GMM parameters (randomly generated for example)
        double[] weights = new double[numClusters];
        MultivariateNormalDistribution[] distributions = new MultivariateNormalDistribution[numClusters];

        for (int i = 0; i < numClusters; i++) {
            weights[i] = random.nextDouble();

            double[] means = new double[dimensions];
            double[][] covariances = {{1,0},{0,1}};

            // Random means
            for (int j = 0; j < dimensions; j++) {
               means[j] = random.nextGaussian() * 5; // Adjust spread as needed
            }

            // Random covariance matrix (simplified, positive-definite)
            for (int j = 0; j < dimensions; j++) {
                for (int k = 0; k < dimensions; k++) {
                    //covariances[j][k] = random.nextGaussian();
                    if (j == k) {
                    //  covariances[j][k] = Math.abs(covariances[j][k]) + 20; // Ensure diagonal dominance
                    }
                }
            }

            distributions[i] = new MultivariateNormalDistribution(means, covariances);
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
            for (int j = 0; j < 2000; j++) {
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
            if(coords[01]>maxY){
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
         
         JFrame frame = new JFrame("Draw Squares");
        draw panel = new draw(100, dataPoints);
        frame.add(panel);
        frame.setSize(25 + 100, 25 + 30); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
         

    }
}
