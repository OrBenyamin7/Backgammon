import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner; // למחוק אחרי זה
public class Cube extends JLabel
{
   private int numberRandom;
   private Timer timerOfNumbers;
   private int countOfTimer;
   public Cube(int locationX,int locationY)
   {
	   numberRandom=0;
	   setSize(66,68);
	   setLocation(locationX,locationY);
	   timerOfNumbers=new Timer(100,new myTimerListener());
	   countOfTimer=0;
   }
   
   public void setSituation()
   {
	   numberRandom=0;
	   setIcon(null);
   }
   
   public void setNumberRandom()
   {
	   timerOfNumbers=new Timer(100,new myTimerListener());
	   countOfTimer=0;
	   timerOfNumbers.start();
	   int random=(int)((6*Math.random()))+1;
	   numberRandom=random;
	   //System.out.println("Enter:");
	   //Scanner in=new Scanner(System.in); // למחוק אחרי זה
	   //numberRandom=in.nextInt();
	   setIcon(new ImageIcon("Pictures/Cube"+numberRandom+".PNG"));
   }
   
   public int getNumberRandom()
   {
	   return this.numberRandom;
   }
  
   private class myTimerListener implements ActionListener
   {
       public void actionPerformed(ActionEvent e)
       {
           Timer timerSource=(Timer)e.getSource();
           if(timerSource==timerOfNumbers)
           {
           	   setIcon(new ImageIcon("Pictures/Cube"+countOfTimer+".PNG"));
           	   countOfTimer++;
           	   if(countOfTimer>7)
               	   setIcon(new ImageIcon("Pictures/Cube"+numberRandom+".PNG"));
           }
       }
   }
}
