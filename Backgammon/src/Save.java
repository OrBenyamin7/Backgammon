import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
//import java.io.FileInputStream;
//import java.io.ObjectInputStream;
public class Save extends JFrame
{
    private ButtonGame menu;
    private int pageNumber;
    private ButtonGame next;
    private ButtonGame back;
    private ButtonGame[] enterSaves;
    private JLabel[] nameSaves;
    private JLabel[] dateSaves;
    private File[] listOfFiles;
    private int placeInTheVectorOfFile;
    private JPanel panelSave;
    private int numberOfPages;
    public Save(int pageNumber,int placeInTheVectorFile)
    {  	
        setSize(1600,900);
        panelSave=new JPanel();
        setUndecorated(true);
        panelSave.setLayout(null);
        JLabel theme=new JLabel(new ImageIcon("Pictures/saveTheme.PNG"));
        theme.setSize(1600,900);
        
        ButtonAction action=new ButtonAction();//יצירת מאזין
        menu=new ButtonGame(320,122,1263,732,"Menu");
        menu.addActionListener(action);//קישור בין מאזין לכפתור
        next=new ButtonGame(320,122,50,20,"next");
        next.addActionListener(action);
        back=new ButtonGame(320,122,1010,20,"back");
        back.addActionListener(action);
        
        int locationX=100;
        int locationY=350;
        
        this.pageNumber=pageNumber;
        this.placeInTheVectorOfFile=placeInTheVectorFile;
        
    	this.enterSaves=new ButtonGame[5];
    	this.nameSaves=new JLabel[5];
    	this.dateSaves=new JLabel[5];
    	
    	File folder=new File("Save");
    	listOfFiles=folder.listFiles();
    	
    	System.out.println("the number of placeInTheVectorOfFile is:"+placeInTheVectorOfFile);
    	System.out.println(listOfFiles[placeInTheVectorOfFile].getName());
    	   	
    	int i=0;
    	    	
    		while((placeInTheVectorOfFile<listOfFiles.length)&&(i<5))
        	{
        	    locationX=200;
                locationY=350+(i*100);

            		enterSaves[i]=new ButtonGame(210,55,locationX,locationY,"ContinueSaveGame");
            		panelSave.add(enterSaves[i]);
            		locationY=locationY+100;
            		enterSaves[i].addActionListener(action);
            	
            	locationX=950;
                locationY=350+(i*100);
                

                    String name=listOfFiles[placeInTheVectorOfFile].getName();
                    name=name.substring(11, name.length()-4);
            		nameSaves[i]=new JLabel(name);
            		nameSaves[i].setSize(300,50);
            		nameSaves[i].setLocation(locationX,locationY);
            		nameSaves[i].setFont(new Font("Harabara Mais Demo",Font.BOLD,45));
            		nameSaves[i].setForeground(Color.black);
            		panelSave.add(nameSaves[i]);
            		locationY=locationY+100;
            	
            	locationX=600;
                locationY=350+(i*100);
            	
                    String date=listOfFiles[placeInTheVectorOfFile].getName();
                    date=date.substring(0,11);
            		dateSaves[i]=new JLabel(date);
            		dateSaves[i].setSize(300,50);
            		dateSaves[i].setLocation(locationX,locationY);
            		dateSaves[i].setFont(new Font("Harabara Mais Demo",Font.BOLD,45));
            		dateSaves[i].setForeground(Color.black);
            		panelSave.add(dateSaves[i]);
            		locationY=locationY+100;
            		
            		i++;
            		placeInTheVectorOfFile++;
        	}   	
    	
    	this.numberOfPages=listOfFiles.length/5;
    	
    	if((listOfFiles.length%5)>0)
    		numberOfPages=numberOfPages+1;
    	
    	System.out.println("the number of placeInTheVectorOfFile is:"+placeInTheVectorOfFile);
    	
    	System.out.println("the size of the saves is:"+listOfFiles.length);
    	
    	System.out.println("the number of page is:"+pageNumber);
    	
    	
    	if(listOfFiles.length>5)
    	{
        	if(pageNumber==1)
            	panelSave.add(next);
            else
            {
            	if(pageNumber==numberOfPages)
            		panelSave.add(back);
            	else
            	{
            		panelSave.add(next);
            		panelSave.add(back);
            	}
            }
    	}
    	 
        panelSave.add(menu);
        panelSave.add(theme);
        add(panelSave);
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
            
            if(actionButton==next)
            {
             pageNumber++;
         	 System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
         	 System.out.println("the number of placeInTheVectorOfFile is:"+placeInTheVectorOfFile);
             new Save(pageNumber,placeInTheVectorOfFile);
             dispose();
            }
            
            if(actionButton==back)
            {
             pageNumber--;
             if(pageNumber>0)
             {
            	 placeInTheVectorOfFile=listOfFiles.length-placeInTheVectorOfFile;
             }
             new Save(pageNumber,placeInTheVectorOfFile);
             dispose();
            }           
            
            for(int i=0;i<enterSaves.length;i++)
            {
            	if(actionButton==enterSaves[i])
            	{
            		new GameBoard(null,listOfFiles[i+((pageNumber-1)*5)].getName());
            		dispose();
            	}
            }
        }
    }
}