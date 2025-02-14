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
    
    public draw(int N, sugmentPosition[] sp){
        this.lineColumnNumber=N;
        this.sugmenPosition=sp;
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

        // Draw the smaller squares based on percentages
        for (int i=0;i<sugmenPosition.length;i++) {
             Color randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
             
             for(int j=0;j<sugmenPosition[i].linesColumns.length;j++){
                  int line=sugmenPosition[i].linesColumns[j].line;
                  int columnStart=sugmenPosition[i].linesColumns[j].columnStart;
                  int columnEnd=sugmenPosition[i].linesColumns[j].columnEnd;
                  System.out.println(line+"--"+columnStart+"--"+columnEnd);
                  int lineWidth= columnEnd-columnStart+1;
                  int linePercentage=(lineWidth*100)/lineColumnNumber;
                  lineWidth=(linePercentage*500)/100;
                  
                  double lineStartPercentage=line*100/lineColumnNumber;
                  double columnStartPercentage=columnStart*100/lineColumnNumber;
                  
                  
                  int lineStart=(int)lineStartPercentage*500/100;
                  int columnStart_=(int)columnStartPercentage*500/100;
                              g.setColor(randomColor);

                   g.fillRect(columnStart_, 500-lineStart -lineHeight, lineWidth, lineHeight);

                      // Small square border
                   g.fillRect(columnStart_, 500-lineStart -lineHeight, lineWidth, lineHeight);
             }

        }
        
}
    
}
