
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
	  
	  downOrUp=placeOnBoard; // המספר 1 יסמן שהמשולש בחלק העליון של הלוח והמספר 0 יסמן שהמשולש בחלק התחתון של הלוח

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
 
  public void addSoldier(Soldier soliderNumber1)// פעולה שמוסיפה כלי לווקטור של המשולש
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
  
  public int getNumberOfTriangle()//  פעולה שמחזירה את מספר המשולש לפי המיספור של כל הלוח 
  {
	  return this.numberOfTriangle;
  }
   
  public void removeSoldier()// פעולה שמוציאה את הכלי האחרון שנוסף למשולש
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
  
  public boolean freeTriangle()// פעולה שמחזירה אמת אם המשולש פנוי כלומר אין כלים בו ושקר אם הוא תפוס כלומר יש בו כלים
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

