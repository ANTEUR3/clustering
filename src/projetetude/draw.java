/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetetude;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class draw extends JPanel {
    private int generalSquareSize;
    private sugmentArea[] widthHeight;

    public draw(int generalSquareSize, sugmentArea[] widthHeight) {
        this.generalSquareSize = generalSquareSize;
        this.widthHeight = widthHeight;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the general square
        g.setColor(Color.RED);
        g.drawRect(0, 0, 500, 500);

        // Draw the smaller squares based on percentages
        for (sugmentArea wh : widthHeight) {
            double width = wh.columnNumber;
            double height = wh.lineNumber;
            
            double gDementions=Math.sqrt(generalSquareSize);
            
            double widthPercentage=(width*100)/gDementions;
            double heightPercentage=(height*100)/gDementions;
            
            
            System.out.println(widthPercentage+"--"+heightPercentage);
            
            int smallRecwidth=(int)(500*widthPercentage)/100;
            int smallRecheight=(int)(500*heightPercentage)/100;

            System.out.println(smallRecwidth+"--"+smallRecheight);


            // Calculate random position within the general square (optional)
            Random rand = new Random();
            
            double x1=(wh.columnStart*100)/gDementions;
            double y1=(wh.lineStart*100)/gDementions;
            
            int x =(int) (x1*500)/100;
            int y = (int)(y1*500)/100;
            
            System.out.println(x+"--"+y);


            Color randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            g.setColor(randomColor);
            g.fillRect(x, 500-y-smallRecheight, smallRecwidth, smallRecheight);

            g.setColor(Color.GRAY); // Small square border
            g.drawRect(x, 500-y-smallRecheight, smallRecwidth, smallRecheight);
        }
        
}
    public static void main(String[] args) {
        int generalSquareSize = 25; // Example size
        sugmentArea[] widthHeight = new sugmentArea[4];
        widthHeight[0]=new sugmentArea(1, 10, 0, 2, 0, 5);
        widthHeight[1]=new sugmentArea(2, 10, 2, 1, 0, 5);
        widthHeight[2]=new sugmentArea(2, 10, 3, 2, 0, 4);
         widthHeight[3]=new sugmentArea(2, 10, 3, 2, 4, 1);



        JFrame frame = new JFrame("Draw Squares");
        draw panel = new draw(generalSquareSize, widthHeight);
        frame.add(panel);
        frame.setSize(generalSquareSize + 100, generalSquareSize + 30); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
}