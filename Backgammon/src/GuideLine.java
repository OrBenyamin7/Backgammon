import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GuideLine 
{
   private Triangle[] triangles;
   private JLabel [] labelOfOptionTriangle;
   private int cubeNumber1;
   private int cubeNumber2;
   private String colorOfPlayer;
   private Soldier choosenSoldierToMove;
   private JPanel panelOfBaord;
   
   public GuideLine(Triangle[] trianglesArry,int numberCube1,int numberCube2,String color,Soldier choosenSoldier,JPanel panelBoard)
   {
	   this.triangles=trianglesArry;
	   this.cubeNumber1=numberCube1;
	   this.cubeNumber2=numberCube2;
	   this.colorOfPlayer=color;
	   this.choosenSoldierToMove=choosenSoldier;
	   this.panelOfBaord=panelBoard;
	   
	   labelOfOptionTriangle=new JLabel[24];
	   
	   System.out.println(" doing the guide line ");
	   
	   for(int i=0;i<labelOfOptionTriangle.length;i++)
	   {
		   labelOfOptionTriangle[i]=new JLabel(" Hi ");
		   labelOfOptionTriangle[i].setSize(100,100);
		   //labelOfOptionTriangle[i].setLocation(triangles[i+1].getLocationX(),triangles[i+1].getLocationY());
		   labelOfOptionTriangle[i].setLocation(500,500);
		   panelBoard.add(labelOfOptionTriangle[i]);
	   }  
   }
   
   public JPanel getPanelBaord()
   {
	   return panelOfBaord;
   }
}
