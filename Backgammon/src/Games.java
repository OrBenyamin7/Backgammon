
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

//import java.io.File;
//import java.io.FileInputStream;
//import java.io.ObjectInputStream;

import java.io.*;

import java.util.Vector;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.sql.Date;


public class Games extends JFrame
{
    private ButtonGame menu;
    private int placeFile;
    private JLabel[] nameSaves;
    private JLabel[] dateSaves;
    private File[] listOfFiles;
    private int placeInTheVectorOfFile;
    public Games(int placeFile,int placeInTheVectorOfFile)
    {
        setSize(1600,900);
        JPanel panelGames=new JPanel();
        setUndecorated(true);
        panelGames.setLayout(null);
        
        JLabel theme=new JLabel(new ImageIcon("Pictures/gamesTheme.PNG"));
        theme.setSize(1600,900);
        
        ButtonAction action=new ButtonAction();
        menu=new ButtonGame(320,122,1263,732,"Menu");
        menu.addActionListener(action);
        
        int locationX;
        int locationY;
        
        
        this.placeFile=placeFile;
        this.placeInTheVectorOfFile=placeInTheVectorOfFile;
        
    	this.nameSaves=new JLabel[5];
    	this.dateSaves=new JLabel[5];
    	
    	File folder=new File("FinishGames");
    	listOfFiles=folder.listFiles();
    	
    	int i=0;
    	
    	while((placeInTheVectorOfFile<listOfFiles.length)&&(i!=5))
    	{	
        	locationX=850;
            locationY=350+(i*100);
            
             String name=listOfFiles[i].getName();
             name=name.substring(0, name.length()-4);
             nameSaves[i]=new JLabel(name);
             nameSaves[i].setSize(300,50);
        	 nameSaves[i].setLocation(locationX,locationY);
        	 nameSaves[i].setFont(new Font("Harabara Mais Demo",Font.BOLD,45));
        	 nameSaves[i].setForeground(Color.black);
        	 panelGames.add(nameSaves[i]);
        	 locationY=locationY+100;
        	
        	locationX=80;
            locationY=350+(i*100);
        	
            try
    		{
    			FileInputStream fin= new FileInputStream("FinishGames/"+listOfFiles[i].getName());
    			ObjectInputStream ois= new ObjectInputStream(fin);
    			
    			String date=(String)ois.readObject(); 
    		    ois.close();
    		       		                   
        		dateSaves[i]=new JLabel(date);
        		dateSaves[i].setSize(300,50);
        		dateSaves[i].setLocation(locationX,locationY);
        		dateSaves[i].setFont(new Font("Harabara Mais Demo",Font.BOLD,45));
        		dateSaves[i].setForeground(Color.black);
        		panelGames.add(dateSaves[i]);

    		}
            
            catch (Exception exception)
    		{
    			exception.printStackTrace();
    		}
            
    		i++;
    		placeInTheVectorOfFile++;
    	}
        
        panelGames.add(menu);
        panelGames.add(theme);
        add(panelGames);
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
        }
    }
}