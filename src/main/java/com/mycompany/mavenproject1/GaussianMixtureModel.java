/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class GaussianMixtureModel {
     private double[] weights;
    private MultivariateNormalDistribution[] distributions;
    private List<Component> components = new ArrayList<>();
    
    public GaussianMixtureModel(double[] weights, MultivariateNormalDistribution[] distributions) {
        this.weights = weights;
        this.distributions = distributions;
        
        for (int i = 0; i < weights.length; i++) {
            components.add(new Component(weights[i], distributions[i]));
        }
    }
    
    public List<Component> getComponents() {
        return components;
    }
    
    public double probability(double[] point) {
        double prob = 0.0;
        for (int i = 0; i < weights.length; i++) {
            prob += weights[i] * distributions[i].density(point);
        }
        return prob;
    }
    
    public static class Component {
        private double weight;
        private MultivariateNormalDistribution distribution;
        
        public Component(double weight, MultivariateNormalDistribution distribution) {
            this.weight = weight;
            this.distribution = distribution;
        }
        
        public double getWeight() {
            return weight;
        }
        
        public MultivariateNormalDistribution getDistribution() {
            return distribution;
        }
    }
}