/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

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
public class draw extends JPanel {
    
    public int lineColumnNumber ;
    public    List<DoublePoint> points;
   
    
    public draw(int N,List<DoublePoint> points){
        this.lineColumnNumber=N;
        this.points=points;
    }
    @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the general square
            g.setColor(Color.yellow);
            g.drawRect(100, 100, 500, 500);
            Random rand = new Random();
            
            int square=500/lineColumnNumber;
        

        // Draw the smaller squares based on percentages
        for (int i=0;i<points.size();i++) {
            DoublePoint point=this.points.get(i);
            double[] table=point.getPoint();
            int R=(int)table[0];
            int G=(int)table[1];
                       Color color=new Color(R,G,100);
                       g.setColor(color);

                   g.fillRect(100+(i%lineColumnNumber)*square,100+((int)(i/lineColumnNumber))*square,square,square);

                      // Small square border
             
            
            

        }
        
}
    
}
