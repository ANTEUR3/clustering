/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetetude;

/**
 *
 * @author Admin
 */
public class sugmentArea {
    public int sugmentIndex;
    public double dataNumber;
    public double lineStart;
    public double lineNumber;
    public double columnStart;
    public double columnNumber;
    
    
    
    public sugmentArea(int index,double DN, double LS,double LN,double CS, double CN){
        this.sugmentIndex=index;
        this.dataNumber=DN;
        this.lineStart=LS;
        this.lineNumber=LN;
        this.columnStart=CS;
        this.columnNumber=CN;
    }
    
    public void displayInformation(){
        System.out.println(" sugment number "+(this.sugmentIndex+1));
        System.out.println(" data number "+(this.dataNumber));

        System.out.println("lines ");
        for(int j=0;j<this.lineNumber;j++){
            System.out.println(this.lineStart+j);
        }
        System.out.println("columns ");
        for(int j=0;j<this.columnNumber;j++){
            System.out.println(this.columnStart+j);
        }
        
        System.out.println(this.columnNumber);
        System.out.println("-----------------------------------");
    }
    
}
