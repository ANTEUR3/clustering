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
            for (int j = 0; j < 100; j++) {
                double[] point = gmm.getComponents().get(i).getDistribution().sample();
                dataPoints.add(new DoublePoint(point));
            }
                            dataPoints.add(new DoublePoint(new double[] {-100.0, -100.0}));

        }

        // Output the generated 2D data
        for (DoublePoint point : dataPoints) {
            double[] coords = point.getPoint();
            System.out.println(coords[0] + ", " + coords[1]);
        }

        //Example of getting the probability of a point.
        double[] testPoint = {1.0,1.0};
        double probability = gmm.probability(testPoint);
        //System.out.println("Probability of test point: " + probability);

    }
}
