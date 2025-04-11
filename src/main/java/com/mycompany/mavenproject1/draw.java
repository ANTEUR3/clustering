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
    public int width;
    public int height;
    public SugmentsPositions[] positions;
    public int sugmentsNumber;
            
            
   
    
    public draw(List<DoublePoint> points,int w,int h,SugmentsPositions[] Positions,int s){
        this.points=points;
        this.height=h;
        this.width=w;
        this.positions=Positions;
        this.sugmentsNumber=s;
    }
    @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the general square
            
            
            g.setColor(Color.yellow);
            g.drawRect(100, 0, this.width, this.height);
            
            
        

        //Draw the smaller squares based on percentages
       
        int[] sugmentsIndexes=new int[this.sugmentsNumber];
        sugmentsIndexes[0]=0;
        int index=1;
        for(int k=1;k<this.height*this.width;k++){
            DoublePoint point=this.points.get(k);
            double[] table=point.getPoint();
            if(table[0]==-10000){
                    sugmentsIndexes[index]=k+1;
                    index++;  
            }
                    
        }
       
        
        for(int i=0;i<this.positions.length;i++){
            if(positions[i]!=null){
                int line=this.positions[i].lineId;
                int columnStart=this.positions[i].columnStart;
                int columnEnd=this.positions[i].columnEnd;
                int sugmentId=this.positions[i].sugmentId-1;
                for(int j=0;j<columnEnd-columnStart+1;j++){
                    DoublePoint P=this.points.get(sugmentsIndexes[sugmentId]);
                    double[] T=P.getPoint();
                    if(T[0]!=-10000){
                         Color color=new Color((int)T[0],(int)T[1],127);
                        g.setColor(color);
                   g.fillRect((columnStart+j)+100,(line),1,1);
                
                    }
                    sugmentsIndexes[sugmentId]++;

                }
               
            }
            
                    
                      
        }
         for(int h=0;h<sugmentsNumber;h++){
            System.out.println("///"+sugmentsIndexes[h]);
        }
        
        
        
}
    
}
