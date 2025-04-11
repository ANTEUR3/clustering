/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author Admin
 */
public class lineState {
    public int lineId;
    public int columnNumber;
    public int columnStart;
    public int columnEnd;
    
    public lineState(int lineId,int maxWidth){
        this.lineId=lineId;
        this.columnNumber=maxWidth;
        this.columnStart=0;
        this.columnEnd=maxWidth-1;
    } 
}
