
import java.util.Vector;

import java.awt.*;

import javax.swing.*;

public class Triangle extends JButton
{
  private Vector<Soldier> soldiers;
  private int numberOfTriangle;
  private int locationX;
  private int locationY;
  private int downOrUp;
  
  public Triangle(int numberPlace,int placeX,int placeY,int placeOnBoard)
  {
	  
	  soldiers=new Vector<Soldier>();
	  
	  this.numberOfTriangle=numberPlace;
	  
	  this.locationX=placeX;
	  
	  this.locationY=placeY;
	  
	  setSize(70,299); //67
	  setLocation(locationX,locationY);
	  
	  downOrUp=placeOnBoard; // ����� 1 ���� ������� ���� ������ �� ���� ������ 0 ���� ������� ���� ������ �� ����

	  locationX=locationX+5;
	  
	  if(downOrUp==0)
		  locationY=locationY+240;
	  
	  if(placeOnBoard==-1)
		  downOrUp=-1;
	  
	  setContentAreaFilled(false);
	  setBorderPainted(false);
  }
  
  public int getLocationX() 
  {
	  return this.locationX;
  }
  
  public int getLocationY()
  {
	  return this.locationY;
  }
  
  public void setLocationY(int newLocationY)
  {
	  this.locationY=newLocationY;
  }
 
  public void addSoldier(Soldier soliderNumber1)// ����� ������� ��� ������� �� ������
  {
	  soldiers.add(soliderNumber1);
	  if(downOrUp==-1)
	  {
		  locationX=locationX+15;
	  }
	  else
	  {
		  if(downOrUp==1)
			  locationY=locationY+60;
			 else
				  locationY=locationY-60;  
	  }
  }
  
  public int getNumberOfTriangle()//  ����� ������� �� ���� ������ ��� ������� �� �� ���� 
  {
	  return this.numberOfTriangle;
  }
   
  public void removeSoldier()// ����� ������� �� ���� ������ ����� ������
  {
	  soldiers.remove(soldiers.size()-1);
		  if(downOrUp==1)
			  locationY=locationY-60;
		  else
			  locationY=locationY+60;  
  }
  
  public int getPlaceInVectorOfSoldier(Soldier soldier1)
  {
	  int placeOfSoldier=0;
	  for(int i=0;i<soldiers.size();i++)
	  {
		  if(soldiers.get(i)==soldier1)
		    placeOfSoldier=i;
	  }
	  return placeOfSoldier;
  }
  
  public boolean freeTriangle()// ����� ������� ��� �� ������ ���� ����� ��� ���� �� ���� �� ��� ���� ����� �� �� ����
  {
	  if(soldiers.size()>0)
		  return false;
	  else
		  return true;
  }
  
  public Vector<Soldier> getVectorOfSoldier()
  {
	  return this.soldiers;
  } 
  
  public void setVectorOfSoldier(Vector <Soldier> soldiers)
  {
	  this.soldiers=soldiers;
  }
}

