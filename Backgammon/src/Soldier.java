
import java.awt.*;

import javax.swing.*;

public class Soldier extends JButton
{
   private String color;
   private int contemporaryTriangle;
   
   public Soldier(String color,int locationX,int locationY,int placeOfTriangle)
   {
	 
     setSize(58,58);
     this.color=color;
     setIcon(new ImageIcon("Pictures/"+color+"Solider1.PNG")); 
   
     setLocation(locationX,locationY);
     
     contemporaryTriangle=placeOfTriangle;
     
     
     setContentAreaFilled(false);
     setBorderPainted(false);
   
   }
   
   public String getColor()// פעולה שמחזירה את הצבע של הכלי-שחור או לבן לפי התכונה
   {
	   return this.color;
   }
   public void setContemporaryTriangle(int placeOfTriangle)
   {
	   this.contemporaryTriangle=placeOfTriangle;
   }
   
   public int getContemporaryTriangle()
   {
	   return this.contemporaryTriangle;
   }
}
