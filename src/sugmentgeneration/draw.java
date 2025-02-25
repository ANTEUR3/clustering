/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sugmentgeneration;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class draw extends JPanel {
    
    public int lineColumnNumber ;
    public sugmentPosition[] sugmenPosition;
    public RGB[][] colors;
    
    public draw(int N, sugmentPosition[] sp,RGB[][] c){
        this.lineColumnNumber=N;
        this.sugmenPosition=sp;
        this.colors=c;
    }
    @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the general square
            g.setColor(Color.yellow);
            g.drawRect(0, 0, 500, 500);
            Random rand = new Random();
            
            int lineHeightPercentage=100/lineColumnNumber;
            int lineHeight=(lineHeightPercentage*500)/100;
            int lineWidth_Percentage=100/lineColumnNumber;
            int lineWidth_=(lineWidth_Percentage*500)/100;

        // Draw the smaller squares based on percentages
        for (int i=0;i<sugmenPosition.length;i++) {
             
             int RGBLine=0;
             while(RGBLine != sugmenPosition[i].id-1){
                 RGBLine++;
             }
             int indiceColumn=0;
             for(int j=0;j<sugmenPosition[i].linesColumns.length;j++){
                   
                  int line=sugmenPosition[i].linesColumns[j].line;
                  int columnStart=sugmenPosition[i].linesColumns[j].columnStart;
                  int columnEnd=sugmenPosition[i].linesColumns[j].columnEnd;
                  
                 
                  
                   double lineStartPercentage=line*100/lineColumnNumber;
                   int lineStart=(int)lineStartPercentage*500/100;
                    double columnStartPercentage=columnStart*100/lineColumnNumber;
                   int columnStart_=(int)columnStartPercentage*500/100;

                  int lineWidth= columnEnd-columnStart+1;
                  for(int h=0;h<lineWidth;h++){
                       Color color=new Color(colors[RGBLine][indiceColumn].R, colors[RGBLine][indiceColumn].G, colors[RGBLine][indiceColumn].B);
                       g.setColor(color);
indiceColumn++;
                   g.fillRect(columnStart_+lineWidth_*h, 500-lineStart -lineHeight, lineWidth_, lineHeight);

                      // Small square border
                   g.fillRect(columnStart_+lineWidth_*h, 500-lineStart -lineHeight, lineWidth_, lineHeight);                      
                  }
                  
                  
                  

                  
                 
             }

        }
        
}
    
}
