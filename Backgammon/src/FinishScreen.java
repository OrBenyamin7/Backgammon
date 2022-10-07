import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.io.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FinishScreen extends JFrame 
{
  private String colorWin;
  private ButtonGame save;
  private ButtonGame newGame;
  private ButtonGame menu;
  private String nameOfPlayer;
  private String date;
  
  public FinishScreen(String colorWin,String nameOfPlayer)
  {
	  setSize(1600,900);
      JPanel panelFinishScreen=new JPanel();
      setUndecorated(true);
      panelFinishScreen.setLayout(null);
      
      this.colorWin=colorWin;
      
      JLabel theme=new JLabel(new ImageIcon("Pictures/"+"FinishScreen"+colorWin+".PNG"));
      theme.setSize(1600,900);
	  
	  
	  this.colorWin=colorWin;
	  this.nameOfPlayer=nameOfPlayer;
	  
	  
	  ButtonAction action=new ButtonAction();//יצרת מאזין
      this.save=new ButtonGame(320,122,602,525,"saveFinishScreen");
      save.addActionListener(action);
      this.newGame=new ButtonGame(540,122,491,707,"newGameFinishScreen");
      newGame.addActionListener(action);
      this.menu=new ButtonGame(320,122,602,341,"menuFinishScreen");
      menu.addActionListener(action);
      
      
      panelFinishScreen.add(save);//הוספת הכפתורים לפאנל
      panelFinishScreen.add(newGame);
      panelFinishScreen.add(menu);
      panelFinishScreen.add(theme);
      add(panelFinishScreen);//הוספת הפאנל לפריים
      setVisible(true);
	  
  }
  
  private class ButtonAction implements ActionListener
  {
      public void actionPerformed(ActionEvent e)
      {
          JButton actionButton=(JButton)e.getSource();
          if(actionButton==menu)
          	{
        	  new Menu();
        	  dispose();
          	}
          if(actionButton==save)
          {
       		   try
       		   {
       			   FileOutputStream fount= new FileOutputStream("FinishGames/"+nameOfPlayer+".dat");
       			   ObjectOutputStream oos= new ObjectOutputStream(fount);
       			   
       			   DateFormat dateFormat= new SimpleDateFormat("dd.MM.yyyy");
 			       Calendar cal=Calendar.getInstance();
 			       date=dateFormat.format(cal.getTime());			 
       			   oos.writeObject(date);
       			   
       			   oos.close();
       		   }
       		   catch (Exception exception)
       		   {
       			   exception.printStackTrace();
       		   }
       		   
       		   new Games(1,0);
       		   dispose();
          }
          if(actionButton==newGame)
          {
        	  new Play();
        	  dispose();
          }
      }
  }
}
