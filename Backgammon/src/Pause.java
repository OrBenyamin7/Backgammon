import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import java.io.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Pause extends JFrame 
{
   private ButtonGame menu;
   private ButtonGame save;
   private ButtonGame back;
   private ButtonGame info;
   private Triangle[] triangles;
   private Cube cubeNumber1;
   private Cube cubeNumber2;
   private Player player;
   private Boolean whitePlay;
   private Boolean blackPlay;
   private ButtonGame yes;
   private ButtonGame no;
   private boolean chooseGuideYes;
   private boolean chooseGuideNo;
   public Pause(Triangle[] triangles,Cube cubeNumber1,Cube cubeNumber2, Player player1,Boolean whitePlay,Boolean blackPlay)
   {
	   setSize(1000,562);
	   setLocation(300,200);
	   JPanel panelPause=new JPanel();
       setUndecorated(true);
       panelPause.setLayout(null);
              
       JLabel theme=new JLabel(new ImageIcon("Pictures/pauseTheme.PNG"));
       theme.setSize(1000,562);
       
       
       ButtonAction action=new ButtonAction();//יצרת מאזין
       
       menu=new ButtonGame(320,122,343,144,"MenuPause");
       menu.addActionListener(action);//יצירת קשר בין הכפתור למאזין
       save=new ButtonGame(320,122,343,241,"SavePause");
       save.addActionListener(action);
       back=new ButtonGame(320,122,721,430,"BackPause");
       back.addActionListener(action);
       info=new ButtonGame(115,90,615,157,"InfoPause");
       info.addActionListener(action);
       
       yes=new ButtonGame(320,122,270,445,"YesPlay1");
       yes.addActionListener(action);
       no=new ButtonGame(320,122,429,430,"NoPlay1");
       no.addActionListener(action);

       
       if(player1.getUseGuide())
       {
    	   yes.setIcon(new ImageIcon("Pictures/YesPlay2.PNG"));
    	   yes.setLocation(270,430);
    	   no.setIcon(new ImageIcon("Pictures/NoPlay1.PNG"));
    	   no.setLocation(429,430);
    	   
    	   chooseGuideYes=true;
       }
       if(!player1.getUseGuide())
       {
    	   yes.setIcon(new ImageIcon("Pictures/YesPlay1.PNG"));
    	   yes.setLocation(270,430);
    	   no.setIcon(new ImageIcon("Pictures/NoPlay2.PNG"));
    	   no.setLocation(429,440);
    	   
    	   chooseGuideNo=true;
       }
       
       this.triangles=triangles;
       this.cubeNumber1=cubeNumber1;
       this.cubeNumber2=cubeNumber2;
       this.player=player1;
       this.whitePlay=whitePlay;
       this.blackPlay=blackPlay;
       
       
       panelPause.add(menu);
       panelPause.add(save);
       panelPause.add(back);
       panelPause.add(info);
       panelPause.add(yes);
       panelPause.add(no);
       panelPause.add(theme);
       add(panelPause);
       setVisible(true);
   }
   
   private class ButtonAction implements ActionListener
   {
       public void actionPerformed(ActionEvent e)
       {
           JButton actionButton=(JButton)e.getSource();
           if(actionButton==save)
           {
        	   if(((cubeNumber1.getNumberRandom()==0)&&(cubeNumber2.getNumberRandom()==0))||((cubeNumber1.getNumberRandom()>0)&&(cubeNumber2.getNumberRandom()>0)))
        	   {
        		   try
        		   {
        			   //DateFormat dateFormat= new SimpleDateFormat("dd.MM.yyyy  HH-mm-ss");
        			   DateFormat dateFormat= new SimpleDateFormat("dd.MM.yyyy");
        			   Calendar cal=Calendar.getInstance();
        			   String textSaveName=dateFormat.format(cal.getTime())+" "+player.getNameOfPlayer()+".dat";
        			   
        			   //FileOutputStream fount= new FileOutputStream("Save/"+cal.getTime()+" "+player.getNameOfPlayer()+".dat");
        			   FileOutputStream fount= new FileOutputStream("Save/"+textSaveName);
        			   ObjectOutputStream oos= new ObjectOutputStream(fount);
        			        			   
        			   oos.writeObject(triangles);
        			   if((cubeNumber1.getNumberRandom()>0)&&(cubeNumber2.getNumberRandom()>0))
        			   {
        				   oos.writeObject(cubeNumber1);
            			   oos.writeObject(cubeNumber2);
        			   }
        			   if((cubeNumber1.getNumberRandom()==0)&&(cubeNumber2.getNumberRandom()==0))
        			   {
        				   cubeNumber1=null;
        				   cubeNumber2=null;
            			   oos.writeObject(cubeNumber1);
            			   oos.writeObject(cubeNumber2);   
        			   }
        			   oos.writeObject(player);
        			   oos.writeObject(whitePlay);
        			   oos.writeObject(blackPlay);
        			          			   
        			   oos.close();
        		   }
        		   catch (Exception exception)
        		   {
        			   exception.printStackTrace();
        		   }
        		   
        		   new Save(1,0);
        		   dispose();
        	   }
        	   else
        		   JOptionPane.showMessageDialog(null, "אין אפשרות לשמור משחק באמצע תנועה",
                  			"Error",JOptionPane.INFORMATION_MESSAGE);
           }
           
           if(actionButton==back)
        	   dispose(); 
           
           if(actionButton==menu)
           {
        	   new Menu();
        	   dispose();
           }
           
           if(actionButton==yes)
           {
           	yes.setIcon(new ImageIcon("Pictures/YesPlay2.PNG"));
           	if(chooseGuideNo)//אם כפתור המנוגד היה לחוץ הוא ישתחרר מהלחיצה(ביטול הלחיצה)
           	{
           	   no.setIcon(new ImageIcon("Pictures/NoPlay1.PNG"));
                  chooseGuideNo=false;
           	}
           	if(chooseGuideYes)//אם הכפתור היה לחוץ כבר בתנאי זה הוא ישתחרר מהלחיצה(ביטול לחיצה)
           	{
              	   yes.setIcon(new ImageIcon("Pictures/YesPlay1.PNG"));
              	   chooseGuideYes=false;
           	}
           	else
           	{
           		no.setLocation(429,430);
           		chooseGuideYes=true;
               	player.setUseGuide(true);
           	}
           }
           
           
           if(actionButton==no)
           {
           	no.setIcon(new ImageIcon("Pictures/NoPlay2.PNG"));
           	if(chooseGuideYes)//אם כפתור המנוגד היה לחוץ הוא ישתחרר מהלחיצה(ביטול הלחיצה)
           	{
           	  yes.setIcon(new ImageIcon("Pictures/YesPlay1.PNG"));
           	  chooseGuideYes=false;
           	}
           	if(chooseGuideNo)//אם הכפתור היה לחוץ כבר בתנאי זה הוא ישתחרר מהלחיצה(ביטול לחיצה)
           	{
            	   no.setIcon(new ImageIcon("Pictures/NoPlay1.PNG"));
            	   chooseGuideNo=false;
           	}
           	else
           	{
           		yes.setLocation(270,430);
               	chooseGuideNo=true;
               	player.setUseGuide(false);
           	}
           }
                      
           if(actionButton==info)
           {
        	   JOptionPane.showMessageDialog(null, "לחיצה על כפתור ה Menu תצא מהמשחק בלי שמירה",
           			"Info",JOptionPane.INFORMATION_MESSAGE);
           }
       }
   }
}
