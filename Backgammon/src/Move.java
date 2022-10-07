import java.util.Vector;

import java.awt.*;

import javax.swing.*;

public class Move 
{
   private Triangle fromTriangle;
   private Triangle toTriangle;
   private int numberOfCube;
   
   public Move(Triangle triangle1,Triangle triangle2,int numberCube)
   {
	   this.fromTriangle=triangle1;
	   this.toTriangle=triangle2;
	   numberOfCube=numberCube;
   }
   
   public Triangle getFromTriangle()
   {
	   return this.fromTriangle;
   }
   
   public Triangle getToTriangle()
   {
	   return this.toTriangle;
   }
   
   public int getNumberOfCube()
   {
	   return this.numberOfCube;
   }
    
}
