/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author Admin
 */
public class SugmentsPositions {
    public int lineId;
    public int sugmentId;
    public int columnStart;
    public int columnEnd;

    
    
    public SugmentsPositions(int Lid,int Sid,int cs , int ce){
        this.lineId=Lid;
        this.sugmentId=Sid;
        this.columnStart=cs;
        this.columnEnd=ce;
       
    }
    
}
