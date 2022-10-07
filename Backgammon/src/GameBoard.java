import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import java.io.*;
public class GameBoard extends JFrame
{
	private JPanel panelGameBoard;
	private ButtonGame pause;
	private ButtonGame rollTheDice;
	private Cube cubeNumber1;
	private Cube cubeNumber2;
	private Triangle[] triangles;
	private Soldier choosenSoldier;
	private boolean eatenSoldierWhiteFree;
	private boolean eatenSoldierBlackFree;
	private boolean doubleCube;
	private boolean firstTimeUsingDoubleCube;
	private int numberRandomTotal;
	private boolean whitePlay;
	private boolean blackPlay;
	private Player player;
	private Computer computer;
	private JLabel labelOfComputer;
	private JLabel labelOfPlayer;
	private Boolean turnOfComputer;
	private Triangle finishTriangleOfPlayer;
	private int numberOfUsing;
	private Triangle finishTriangleOfComputer;
	public GameBoard(Player player1, String optionToPlay)
	{
		setSize(1600,900);
        panelGameBoard=new JPanel();
        setUndecorated(true);
        panelGameBoard.setLayout(null);
        
        doubleCube=false;
        firstTimeUsingDoubleCube=true;
        
        turnOfComputer=false;
        
        numberOfUsing=0;
        
        eatenSoldierWhiteFree=true;
        eatenSoldierBlackFree=true;
        
        numberRandomTotal=0; 
        
        this.player=player1;
        
        whitePlay=false;
        blackPlay=true;
        
        choosenSoldier=null; // משתנה שיהיה בו את החייל שנבחר להזזה

        JLabel theme=new JLabel(new ImageIcon("Pictures/backgammonThemeGame2.PNG"));
        theme.setSize(1600,900);
         
        ButtonAction  actionButton=new ButtonAction();//יצרת מאזין
        
        SoldierAction actionSoldiers=new SoldierAction();// יצירת מאזין לחיילים

        TriangleAction actionTriangle=new TriangleAction();// יצירה מאזין למשולשי הלוח
       
        pause=new ButtonGame(188,88,0,0,"Pause");
        pause.addActionListener(actionButton);//יצירת קשר בין הכפתור למאזין
       
        rollTheDice=new ButtonGame(316,77,1265,395,"rollTheDiceGameBoard");
        rollTheDice.addActionListener(actionButton);
        
        cubeNumber1=new Cube(1500,250);
        cubeNumber2=new Cube(1285,250);
        
        triangles=new Triangle[26];
        
        if(!optionToPlay.equals("new Game"))
			StartingOldGame(optionToPlay);
		
		else
		{
                                   
        triangles=new Triangle[26];
        
        triangles[0]=new Triangle(25,555,350,0);
        panelGameBoard.add(triangles[0]);
    	triangles[0].addActionListener(actionTriangle);
          
        // חלוקת המשולשים ומיקומם על הלוח
        
        int locationxHelp=1082;
        for(int i=1;i<7;i++)
        {
        	triangles[i]=new Triangle(i,locationxHelp,117,1);
        	locationxHelp=locationxHelp-85;
        	panelGameBoard.add(triangles[i]);
        	triangles[i].addActionListener(actionTriangle);
        }
        
        locationxHelp=457;
        
        for(int i=7;i<13;i++)
        {
        	triangles[i]=new Triangle(i,locationxHelp,117,1);
        	locationxHelp=locationxHelp-85;
        	panelGameBoard.add(triangles[i]);
        	triangles[i].addActionListener(actionTriangle);
        }
            
        locationxHelp=32;
        
        for(int i=13;i<19;i++)
        {
        	triangles[i]=new Triangle(i,locationxHelp,540,0);
        	locationxHelp=locationxHelp+85;
        	panelGameBoard.add(triangles[i]);
        	triangles[i].addActionListener(actionTriangle);
        }
        
        locationxHelp=658;
        
        for(int i=19;i<25;i++)
        {
        	triangles[i]=new Triangle(i,locationxHelp,539,0);
        	locationxHelp=locationxHelp+85;
        	panelGameBoard.add(triangles[i]);
        	triangles[i].addActionListener(actionTriangle);
        }
        
        // יצירת חיילים לבנים ושחורים
        
        Vector<Soldier> whiteSoldiers=new Vector<Soldier>(); 
        Vector<Soldier> blackSoldiers=new Vector<Soldier>();
        
        for(int i=0;i<16;i++)
        {
        	Soldier soldierWhite=new Soldier("white",0,0,-1);
        	whiteSoldiers.add(soldierWhite);
        	soldierWhite.addActionListener(actionSoldiers);
        }
        
        for(int i=0;i<16;i++)
        {
        	Soldier soldierBlack=new Soldier("black",0,0,-1);
        	blackSoldiers.add(soldierBlack);
        	soldierBlack.addActionListener(actionSoldiers);
        }
                
        // חלוקת השחקנים השחורים והלבנים למשולשים להתחלת המשחק
                            
        int placeOfTriangleY=triangles[1].getLocationY();
        int placeOfTriangleX=triangles[1].getLocationX();
        
        
        for(int i=0;i<2;i++)// מוסיף את הכלים הלבנים למשולש מספר 1
        {
        	whiteSoldiers.get(0).setContemporaryTriangle(1);
        	whiteSoldiers.get(0).setLocation(placeOfTriangleX,placeOfTriangleY);
        	triangles[1].addSoldier(whiteSoldiers.get(0));
        	panelGameBoard.add(whiteSoldiers.get(0),0);
        	whiteSoldiers.remove(0);
        	placeOfTriangleY=triangles[1].getLocationY();
        	
        }
        
         placeOfTriangleY=triangles[12].getLocationY();
         placeOfTriangleX=triangles[12].getLocationX();
        
        for(int i=0;i<5;i++)// מוסיף את הכלים הלבנים למשולש מספר 12
        {
        	whiteSoldiers.get(0).setContemporaryTriangle(12);
        	whiteSoldiers.get(0).setLocation(placeOfTriangleX,placeOfTriangleY);
        	triangles[12].addSoldier(whiteSoldiers.get(0));
        	panelGameBoard.add(whiteSoldiers.get(0),0);
        	whiteSoldiers.remove(0);
        	placeOfTriangleY=triangles[12].getLocationY();
        	
        }
        
        placeOfTriangleY=triangles[17].getLocationY();
        placeOfTriangleX=triangles[17].getLocationX();
        
        for(int i=0;i<3;i++)// מוסיף את הכלים הלבנים למשולש מספר 17
        {
        	whiteSoldiers.get(0).setContemporaryTriangle(17);
        	whiteSoldiers.get(0).setLocation(placeOfTriangleX,placeOfTriangleY);
        	triangles[17].addSoldier(whiteSoldiers.get(0));
        	panelGameBoard.add(whiteSoldiers.get(0),0);
        	whiteSoldiers.remove(0);
        	placeOfTriangleY=triangles[17].getLocationY();
        	
        }
        
        placeOfTriangleY=triangles[19].getLocationY();
        placeOfTriangleX=triangles[19].getLocationX();
        
        for(int i=0;i<5;i++)// מוסיף את הכלים הלבנים למשולש מספר 19
        {
        	whiteSoldiers.get(0).setContemporaryTriangle(19);
        	whiteSoldiers.get(0).setLocation(placeOfTriangleX,placeOfTriangleY);
        	triangles[19].addSoldier(whiteSoldiers.get(0));
        	panelGameBoard.add(whiteSoldiers.get(0),0);
        	whiteSoldiers.remove(0);
        	placeOfTriangleY=triangles[19].getLocationY();
        	
        }
        
        placeOfTriangleY=triangles[24].getLocationY();
        placeOfTriangleX=triangles[24].getLocationX();
        
        for(int i=0;i<2;i++)// מוסיף את הכלים הלבנים למשולש מספר 24
        {
        	blackSoldiers.get(0).setContemporaryTriangle(24);
        	blackSoldiers.get(0).setLocation(placeOfTriangleX,placeOfTriangleY);
        	triangles[24].addSoldier(blackSoldiers.get(0));
        	panelGameBoard.add(blackSoldiers.get(0),0);
        	blackSoldiers.remove(0);
        	placeOfTriangleY=triangles[24].getLocationY();
        	
        }
        
        placeOfTriangleY=triangles[13].getLocationY();
        placeOfTriangleX=triangles[13].getLocationX();
        
        for(int i=0;i<5;i++)// מוסיף את הכלים הלבנים למשולש מספר 13
        {
        	blackSoldiers.get(0).setContemporaryTriangle(13);
        	blackSoldiers.get(0).setLocation(placeOfTriangleX,placeOfTriangleY);
        	triangles[13].addSoldier(blackSoldiers.get(0));
        	panelGameBoard.add(blackSoldiers.get(0),0);
        	blackSoldiers.remove(0);
        	placeOfTriangleY=triangles[13].getLocationY();
        	
        }
        
        placeOfTriangleY=triangles[8].getLocationY();
        placeOfTriangleX=triangles[8].getLocationX();
        
        for(int i=0;i<3;i++)// מוסיף את הכלים הלבנים למשולש מספר 8
        {
        	blackSoldiers.get(0).setContemporaryTriangle(8);
        	blackSoldiers.get(0).setLocation(placeOfTriangleX,placeOfTriangleY);
        	triangles[8].addSoldier(blackSoldiers.get(0));
        	panelGameBoard.add(blackSoldiers.get(0),0);
        	blackSoldiers.remove(0);
        	placeOfTriangleY=triangles[8].getLocationY();
        	
        }
        
        placeOfTriangleY=triangles[6].getLocationY();
        placeOfTriangleX=triangles[6].getLocationX();
        
        for(int i=0;i<5;i++)// מוסיף את הכלים הלבנים למשולש מספר 6
        {
        	blackSoldiers.get(0).setContemporaryTriangle(6);
        	blackSoldiers.get(0).setLocation(placeOfTriangleX,placeOfTriangleY);
        	triangles[6].addSoldier(blackSoldiers.get(0));
        	panelGameBoard.add(blackSoldiers.get(0),0);
        	blackSoldiers.remove(0);
        	placeOfTriangleY=triangles[6].getLocationY();
        	
        }
		}
        
        String colorOfComputer="";
        
        if(player.getColorOfPlayer().equals("white"))
        	colorOfComputer="black";
        else
        	colorOfComputer="white";
        
        if(colorOfComputer.equals("white"))
        	turnOfComputer=true;
        
        int numberRandomOfCube1=0;
        int numberRandomOfCube2=0;
        if((cubeNumber1!=null)&&(cubeNumber1!=null))
        {
        	numberRandomOfCube1=cubeNumber1.getNumberRandom();
        	numberRandomOfCube2=cubeNumber2.getNumberRandom();
        }
        else
        {
        	cubeNumber1=new Cube(1500,250);
            cubeNumber2=new Cube(1285,250);
        }
        
        
         Computer computer1=new Computer(colorOfComputer,triangles,numberRandomOfCube1,numberRandomOfCube2,finishTriangleOfComputer);        
        
        this.computer=computer1;
        
        System.out.println("the color is:"+colorOfComputer);
        
        System.out.println("the color of the computer is:"+computer.getColor());
 
        
        
        labelOfComputer=new JLabel();
        labelOfPlayer=new JLabel();
        
        labelOfComputer.setText("Computer");
        labelOfPlayer.setText(player.getNameOfPlayer());
        
        labelOfComputer.setLocation(1362,700);
        labelOfPlayer.setLocation(1362,525);
        
        labelOfComputer.setSize(200,30);
        labelOfPlayer.setSize(500,35);
        
        labelOfComputer.setFont(new Font("Harabara Mais Demo",Font.BOLD,30));
        labelOfPlayer.setFont(new Font("Harabara Mais Demo",Font.BOLD,30));
        
        if(player.getColorOfPlayer().equals("black"))
        {
        	labelOfComputer.setForeground(Color.white);
            labelOfPlayer.setForeground(Color.black);
        }
        else
        {
        	labelOfComputer.setForeground(Color.black);
            labelOfPlayer.setForeground(Color.white);
        }
        
        
        finishTriangleOfPlayer=new Triangle(0,1260,576,-1);
        finishTriangleOfPlayer.setSize(325,78);
        finishTriangleOfPlayer.addActionListener(actionTriangle);
        panelGameBoard.add(finishTriangleOfPlayer);
        
        
        finishTriangleOfComputer=new Triangle(0,1260,755,-1);
        finishTriangleOfComputer.setSize(325,108);
        panelGameBoard.add(finishTriangleOfPlayer);
        
        
        
        if(computer.getColor().equals("white"))
        	computerTurnAndPlay();
        
        for(int i=0;i<triangles[0].getVectorOfSoldier().size();i++)
        {
           if(triangles[0].getVectorOfSoldier().get(i).getColor().equals("white"))
              eatenSoldierWhiteFree=false;
           
           if(triangles[0].getVectorOfSoldier().get(i).getColor().equals("black"))
               eatenSoldierBlackFree=false;
        }
        
        
        panelGameBoard.add(labelOfComputer);
        panelGameBoard.add(labelOfPlayer);
        panelGameBoard.add(cubeNumber1);
        panelGameBoard.add(cubeNumber2);
        panelGameBoard.add(pause);
        panelGameBoard.add(rollTheDice);
        panelGameBoard.add(theme);
        add(panelGameBoard);
        setVisible(true); 

	}
	
	public boolean checkingEatenSoldier(Soldier soldier1)// פעולה שמחזירה אחת אם החייל הנלחץ הוא חייל שנאכל ונמצא באמצע הלוח
	{
		System.out.println("the soldier is:");
		System.out.println(soldier1.getColor());
		Vector<Soldier> soldiersHelp=new Vector<Soldier>();
		soldiersHelp=triangles[0].getVectorOfSoldier();
		if(whitePlay)
		{
			System.out.println("white");
			if(soldiersHelp.size()>0)
			{
				System.out.println("vector is gadol min zero");

				for(int i=0;i<soldiersHelp.size();i++)
				{
					System.out.println(soldiersHelp.get(i).getColor());
					if(soldiersHelp.get(i)==soldier1)
						return true;
				}
			}
		}
		if(blackPlay)
		{
			System.out.println("black2");
			if(soldiersHelp.size()>0)
			{
				System.out.println("vector is gadol min zero");

				for(int i=0;i<soldiersHelp.size();i++)
				{
					if(soldiersHelp.get(i)==soldier1)
						return true;
				}
			}
		}
		System.out.println("False2");
	   return false;
	}
	
	public boolean checkingTriangleForEatenSoldier(Soldier soldier1) // בדיקה האם קיים מקום לחייל שנאכל במשולשים בהתאם למספר שהוגרל 
	{                                                                // על הקוביות	
		int numberRandom1=0;
		int numberRandom2=0;
		if(cubeNumber1.getNumberRandom()!=0)
		       numberRandom1=cubeNumber1.getNumberRandom();
	    if(cubeNumber2.getNumberRandom()!=0)
		       numberRandom2=cubeNumber2.getNumberRandom();
	    if((numberRandom1==0)&&(numberRandom2==0))
			return false;
	    
		Vector<Soldier> solidersHelp1;
	    
	    if(soldier1.getColor().equals("white"))
		{
	    	if((numberRandom1>0)&&(numberRandom2>0))
	    	{
	    	   solidersHelp1=(triangles[numberRandom1]).getVectorOfSoldier();
			   if((solidersHelp1.size()<=1)||((solidersHelp1.size()>=1)&&((solidersHelp1.get(solidersHelp1.size()-1)).getColor().equals("white"))))
				  return true;
			   else
			   {
				   
				   solidersHelp1=(triangles[numberRandom2]).getVectorOfSoldier();
				     if((solidersHelp1.size()<=1)||((solidersHelp1.size()>=1)&&((solidersHelp1.get(solidersHelp1.size()-1)).getColor().equals("white"))))
					     return true;
				     
				     else
				     {
				    	 if((triangles[numberRandom1].getVectorOfSoldier().size()>1)&&(triangles[numberRandom1].getVectorOfSoldier().get(0).getColor().equals("black")))
				    	  {
				    		  if((triangles[numberRandom2].getVectorOfSoldier().size()>1)&&(triangles[numberRandom2].getVectorOfSoldier().get(0).getColor().equals("black")))
					    	  {
					    		  return false;
					    	  }
				    	  }
				    	 
				    	 if(triangles[numberRandom1].getVectorOfSoldier().size()<=1)
				    	  {
				    		  if((triangles[numberRandom1].getVectorOfSoldier().size()==1)&&(triangles[numberRandom1].getVectorOfSoldier().get(0).getColor().equals("black")))
				    		  return true;
				    	  }
				    	  if(triangles[numberRandom2].getVectorOfSoldier().size()<=1)
				    	  {
				    		  if((triangles[numberRandom2].getVectorOfSoldier().size()==1)&&(triangles[numberRandom2].getVectorOfSoldier().get(0).getColor().equals("black")))
				    		  return true;
				    	  }
				     }
			   }
	        }
	    	else
	    	{
	    		if(numberRandom1>0)
	    		{
	    			solidersHelp1=(triangles[numberRandom1]).getVectorOfSoldier();
	 			    if((solidersHelp1.size()<=1)||((solidersHelp1.size()>=1)&&((solidersHelp1.get(solidersHelp1.size()-1)).getColor().equals("white"))))
	 				   return true;
	    		}
	    		if(numberRandom2>0)
	    		{
	    			solidersHelp1=(triangles[numberRandom2]).getVectorOfSoldier();
			        if((solidersHelp1.size()<=1)||((solidersHelp1.size()>=1)&&((solidersHelp1.get(solidersHelp1.size()-1)).getColor().equals("white"))))
				     return true;
	    		}
	    	}
	    	
		}
	    if(soldier1.getColor().equals("black"))
		{
	    	if((numberRandom1>0)&&(numberRandom2>0))
	    	{
	    	   solidersHelp1=(triangles[25-numberRandom1]).getVectorOfSoldier();
			   if((solidersHelp1.size()<=1)||((solidersHelp1.size()>=1)&&((solidersHelp1.get(solidersHelp1.size()-1)).getColor().equals("black"))))
				  return true;
			   else
			   {
				   
				   solidersHelp1=(triangles[25-numberRandom2]).getVectorOfSoldier();
				     if((solidersHelp1.size()<=1)||((solidersHelp1.size()>=1)&&((solidersHelp1.get(solidersHelp1.size()-1)).getColor().equals("black"))))
					     return true;
				     
				     else
				     {
				    	 if((triangles[25-numberRandom1].getVectorOfSoldier().size()>1)&&(triangles[25-numberRandom1].getVectorOfSoldier().get(0).getColor().equals("white")))
				    	  {
				    		  if((triangles[25-numberRandom2].getVectorOfSoldier().size()>1)&&(triangles[25-numberRandom2].getVectorOfSoldier().get(0).getColor().equals("white")))
					    	  {
					    		  return false;
					    	  }
				    	  }
				    	 
				    	 if(triangles[25-numberRandom1].getVectorOfSoldier().size()<=1)
				    	  {
				    		  if((triangles[25-numberRandom1].getVectorOfSoldier().size()==1)&&(triangles[25-numberRandom1].getVectorOfSoldier().get(0).getColor().equals("white")))
				    		  return true;
				    	  }
				    	  if(triangles[25-numberRandom2].getVectorOfSoldier().size()<=1)
				    	  {
				    		  if((triangles[25-numberRandom2].getVectorOfSoldier().size()==1)&&(triangles[25-numberRandom2].getVectorOfSoldier().get(0).getColor().equals("white")))
				    		  return true;
				    	  }
				     }
			   }
	        }
	    	else
	    	{
	    		if(numberRandom1>0)
	    		{
	    			solidersHelp1=(triangles[25-numberRandom1]).getVectorOfSoldier();
	 			    if((solidersHelp1.size()<=1)||((solidersHelp1.size()>=1)&&((solidersHelp1.get(solidersHelp1.size()-1)).getColor().equals("black"))))
	 				   return true;
	    		}
	    		if(numberRandom2>0)
	    		{
	    			solidersHelp1=(triangles[25-numberRandom2]).getVectorOfSoldier();
			        if((solidersHelp1.size()<=1)||((solidersHelp1.size()>=1)&&((solidersHelp1.get(solidersHelp1.size()-1)).getColor().equals("black"))))
				     return true;
	    		}
	    	}
	    	
		}
		return false;
	}
	
    public boolean checkingTriangleForSoldier(Soldier soldier1) // פעולה לבדיקת מיקום של חייל רגיל
    {
    	int numberRandom1=0;
		int numberRandom2=0;
		if(cubeNumber1.getNumberRandom()!=0)
		       numberRandom1=cubeNumber1.getNumberRandom();
	    if(cubeNumber2.getNumberRandom()!=0)
		       numberRandom2=cubeNumber2.getNumberRandom();
	    if((numberRandom1==0)&&(numberRandom2==0))
			return false;
	    
	    int contemporaryPlace=soldier1.getContemporaryTriangle();
	    
		Vector<Soldier> solidersHelp1;
	    
	    if(soldier1.getColor().equals("white"))
		{
	    	if(((numberRandom1>0)&&(numberRandom2>0))&&((contemporaryPlace+numberRandom1+numberRandom2)<25))
	    	{		
	    		solidersHelp1=(triangles[contemporaryPlace+numberRandom1]).getVectorOfSoldier();
			   if((solidersHelp1.size()<=1)||((solidersHelp1.size()>=1)&&((solidersHelp1.get(solidersHelp1.size()-1)).getColor().equals("white"))))
				  return true;
			   else
			   {
				   
				   solidersHelp1=(triangles[contemporaryPlace+numberRandom2]).getVectorOfSoldier();
				     if((solidersHelp1.size()<=1)||((solidersHelp1.size()>=1)&&((solidersHelp1.get(solidersHelp1.size()-1)).getColor().equals("white"))))
					     return true;
				     
				     else
				     {
				    	 if((triangles[contemporaryPlace+numberRandom1].getVectorOfSoldier().size()>1)&&(triangles[contemporaryPlace+numberRandom1].getVectorOfSoldier().get(0).getColor().equals("black")))
				    	  {
				    		  if((triangles[contemporaryPlace+numberRandom2].getVectorOfSoldier().size()>1)&&(triangles[contemporaryPlace+numberRandom2].getVectorOfSoldier().get(0).getColor().equals("black")))
					    	  {
					    		  return false;
					    	  }
				    	  }
				    	 
				    	 if(triangles[contemporaryPlace+numberRandom1].getVectorOfSoldier().size()<=1)
				    	  {
				    		  if((triangles[contemporaryPlace+numberRandom1].getVectorOfSoldier().size()==1)&&(triangles[contemporaryPlace+numberRandom1].getVectorOfSoldier().get(0).getColor().equals("black")))
				    		  return true;
				    	  }
				    	  if(triangles[contemporaryPlace+numberRandom2].getVectorOfSoldier().size()<=1)
				    	  {
				    		  if((triangles[contemporaryPlace+numberRandom2].getVectorOfSoldier().size()==1)&&(triangles[contemporaryPlace+numberRandom2].getVectorOfSoldier().get(0).getColor().equals("black")))
				    		  return true;
				    	  }
				     }
			   }
	        }
	    	else
	    	{
	    		if((numberRandom1>0)&&(contemporaryPlace+numberRandom1<24))
	    		{
	    			solidersHelp1=(triangles[contemporaryPlace+numberRandom1]).getVectorOfSoldier();
	 			    if((solidersHelp1.size()<=1)||((solidersHelp1.size()>=1)&&((solidersHelp1.get(solidersHelp1.size()-1)).getColor().equals("white"))))
	 				   return true;
	    		}
	    		if((numberRandom2>0)&&(contemporaryPlace+numberRandom2<24))
	    		{
	    			solidersHelp1=(triangles[contemporaryPlace+numberRandom2]).getVectorOfSoldier();
			        if((solidersHelp1.size()<=1)||((solidersHelp1.size()>=1)&&((solidersHelp1.get(solidersHelp1.size()-1)).getColor().equals("white"))))
				     return true;
	    		}
	    	}
	    	
		}	
	    if(soldier1.getColor().equals("black"))
		{
	    	if(((numberRandom1>0)&&(numberRandom2>0))&&((contemporaryPlace-(numberRandom1+numberRandom2))>0))
	    	{
         	   solidersHelp1=(triangles[contemporaryPlace-numberRandom1]).getVectorOfSoldier();
			   if((solidersHelp1.size()<=1)||((solidersHelp1.size()>=1)&&((solidersHelp1.get(solidersHelp1.size()-1)).getColor().equals("black"))))
				  return true;
			   else
			   {
				   
				   solidersHelp1=(triangles[contemporaryPlace-numberRandom2]).getVectorOfSoldier();
				     if((solidersHelp1.size()<=1)||((solidersHelp1.size()>=1)&&((solidersHelp1.get(solidersHelp1.size()-1)).getColor().equals("black"))))
					     return true;
				     
				     else
				     {
				    	 if((triangles[contemporaryPlace-numberRandom1].getVectorOfSoldier().size()>1)&&(triangles[contemporaryPlace-numberRandom1].getVectorOfSoldier().get(0).getColor().equals("white")))
				    	  {
				    		  if((triangles[contemporaryPlace-numberRandom2].getVectorOfSoldier().size()>1)&&(triangles[contemporaryPlace-numberRandom2].getVectorOfSoldier().get(0).getColor().equals("white")))
					    	  {
					    		  return false;
					    	  }
				    	  }
				    	 
				    	 if(triangles[contemporaryPlace-numberRandom1].getVectorOfSoldier().size()<=1)
				    	  {
				    		  if((triangles[contemporaryPlace-numberRandom1].getVectorOfSoldier().size()==1)&&(triangles[contemporaryPlace-numberRandom1].getVectorOfSoldier().get(0).getColor().equals("white")))
				    		  return true;
				    	  }
				    	  if(triangles[contemporaryPlace-numberRandom2].getVectorOfSoldier().size()<=1)
				    	  {
				    		  if((triangles[contemporaryPlace-numberRandom2].getVectorOfSoldier().size()==1)&&(triangles[contemporaryPlace-numberRandom2].getVectorOfSoldier().get(0).getColor().equals("white")))
				    		  return true;
				    	  }
				     }
			   }
	        }
	    	else
	    	{
	    		if((numberRandom1>0)&&(contemporaryPlace-numberRandom1>0))
	    		{
	    			solidersHelp1=(triangles[contemporaryPlace-numberRandom1]).getVectorOfSoldier();
	 			    if((solidersHelp1.size()<=1)||((solidersHelp1.size()>=1)&&((solidersHelp1.get(solidersHelp1.size()-1)).getColor().equals("black"))))
	 				   return true;
	    		}
	    		if((numberRandom2>0)&&(contemporaryPlace-numberRandom2>0))
	    		{
	    			solidersHelp1=(triangles[contemporaryPlace-numberRandom2]).getVectorOfSoldier();
			        if((solidersHelp1.size()<=1)||((solidersHelp1.size()>=1)&&((solidersHelp1.get(solidersHelp1.size()-1)).getColor().equals("black"))))
				     return true;
	    		}
	    	}
	    	
		}  
	    return false;
    }
   
    public boolean checkingTriangleMoves(Soldier soldier1) // פעולה לבדיקת אי מקום לזזת חיילים מצבע מסוים
    {
    	int countOfSoldiers=0;
    	
    	int countOfSoldiersCanNotMove=0;
    	
    	Vector<Soldier> soldierHelp;
    	
    	for(int i=1;i<triangles.length-1;i++)
    	{
    		soldierHelp=triangles[i].getVectorOfSoldier();
    		
    		if(soldierHelp.size()>0)
    		{
    			if(soldierHelp.get(soldierHelp.size()-1).getColor().equals(soldier1.getColor()))
    			{
    				countOfSoldiers++;
    				if(!checkingTriangleForSoldier(soldierHelp.get(soldierHelp.size()-1)))
    					  countOfSoldiersCanNotMove++;
    			}
    		}
    	}
    	
    	if(countOfSoldiers==countOfSoldiersCanNotMove)
    		return false;
    	
    	return true;
    }
    
	public boolean checkingSoldierTriangle(Soldier soldier1)// פעולה שמחזירה אחת אם החייל נמצא במעלה המשולש
	{
		int numberOfPlace=soldier1.getContemporaryTriangle();
		Vector<Soldier> solidersHelp=new Vector<Soldier>();
		if(soldier1.getContemporaryTriangle()==25)
			solidersHelp=triangles[0].getVectorOfSoldier();
		else
		solidersHelp=triangles[numberOfPlace].getVectorOfSoldier();
		if(solidersHelp.size()>0)
		{
			if(solidersHelp.get(solidersHelp.size()-1)==soldier1)
			{
				choosenSoldier=soldier1;
			   return  true;
			}
		}
		return false;
	}
	
	public boolean checkingNoEatenSoldier(Soldier soldier1) // פעולה שבודקת שלא נלחץ חייל מצבע שיש לו חייל אכול ולא הוחזר
	{
		if(whitePlay)
		{
            if(eatenSoldierWhiteFree)
            	return true;
		}
		if(blackPlay)
		{
			if(eatenSoldierBlackFree)
				return true;
		}
		return false;
		
    }
		
	public boolean checkingChooseTriangle(Triangle triangle1) // פעולה שבודקת אם המשולש הנבחר הוא בהתאם למספרים שהוגרלו בקוביות
	{
		int numberRandom1=0;
		int numberRandom2=0;
	    if(cubeNumber1.getNumberRandom()!=0)
	    numberRandom1=cubeNumber1.getNumberRandom();
	    if(cubeNumber2.getNumberRandom()!=0)
	    numberRandom2=cubeNumber2.getNumberRandom();
	    if(!doubleCube)
	    numberRandomTotal=numberRandom1+numberRandom2;
				
	    System.out.println("א");
 	    int contemporaryTriangle=choosenSoldier.getContemporaryTriangle();
 	    
 	    if((doubleCube)&&(firstTimeUsingDoubleCube))
 	    {
 	    	numberRandomTotal=numberRandom1+numberRandom2;
 	    	numberRandomTotal=numberRandomTotal+numberRandomTotal;
 	    	firstTimeUsingDoubleCube=false;
 	    }
        if(whitePlay) 
        {
    	    System.out.println("ב");
		if(choosenSoldier.getColor().equals("white")) // הזזה של החיילים הלבנים בהתאם לכיוון שצריך
		{
		    System.out.println("ג");
		    System.out.println(eatenSoldierWhiteFree);
	    	if(!eatenSoldierWhiteFree) // יתבצע במצב בו יש חייל לבן אכול כלומר באמצע הלוח
	    	{
	    	    System.out.println("ד");
	    	  if(!doubleCube) // יתבצע במצב בו אין דאבל בקוביות
	    	  {
	    		    System.out.println("ה");
	    		 if((numberRandom1>0)&&(numberRandom2>0))
	    		 {
	    			    System.out.println("ו");
	    		  if(triangle1.getNumberOfTriangle()<=numberRandomTotal)
			       {
				      if(triangle1.getNumberOfTriangle()==numberRandom1)
				      {  
				    	  System.out.println("ז");
					         cubeNumber1.setSituation();
					         return true;
				      }
				      if(triangle1.getNumberOfTriangle()==numberRandom2)
				      {
				    	  System.out.println("ח");
					    cubeNumber2.setSituation();
					    return true;
				      }
				      
				      if(triangle1.getNumberOfTriangle()==numberRandomTotal)
				      {
				    	  if((triangles[numberRandom1].getVectorOfSoldier().size()>1)&&(triangles[numberRandom1].getVectorOfSoldier().get(0).getColor().equals("black")))
				    	  {
				    		  if((triangles[numberRandom2].getVectorOfSoldier().size()>1)&&(triangles[numberRandom2].getVectorOfSoldier().get(0).getColor().equals("black")))
					    	  {
					    		  return false;
					    	  }
				    	  }
				    	  if(triangles[numberRandom1].getVectorOfSoldier().size()<=1)
				    	  {
				    		  if((triangles[numberRandom1].getVectorOfSoldier().size()==1)&&(triangles[numberRandom1].getVectorOfSoldier().get(0).getColor().equals("black")))
				    		     removingSoldierInTheMiddleOfTheBoard(triangles[numberRandom1]);
				    		  System.out.println("ט");
					    	  cubeNumber1.setSituation();
						      cubeNumber2.setSituation();
				    	  if(triangles[numberRandom2].getVectorOfSoldier().size()<=1)
				    	  {
				    		  if((triangles[numberRandom2].getVectorOfSoldier().size()==1)&&(triangles[numberRandom2].getVectorOfSoldier().get(0).getColor().equals("black")))
				    		     removingSoldierInTheMiddleOfTheBoard(triangles[numberRandom2]);
				    		  System.out.println("י");
					    	  cubeNumber1.setSituation();
						      cubeNumber2.setSituation();
				    	  }
				    	  return true;
				    	  }
					       return false;			    	  				    	  
				      }
			        }
	    		 }
	    		 else                      
	    		 {
		    		 if(numberRandom1>0)
		    		 {
		    			 if(triangle1.getNumberOfTriangle()==numberRandom1)
					      {  
						         cubeNumber1.setSituation();
						         System.out.println("יא");
						         return true;
					      }
		    		 }
		    		 if(numberRandom2>0)
		    		 {
		    			 if(triangle1.getNumberOfTriangle()==numberRandom2)
					      {  							
						         cubeNumber2.setSituation();
						         System.out.println("יב");
						         return true;
					      }
		    		 }
         			 System.out.println("99");
		    		 return false;
		    	  }
	    		 }
	    	  
	    	  else // יתבצע במצב בו יש חייל לבן אכול ויש דאבל בקוביות
	    	  {
	    		  if(triangle1.getNumberOfTriangle()<=numberRandomTotal)
			       {
	    			  if(triangle1.getNumberOfTriangle()==numberRandom1)
				      {  
	    				     numberRandomTotal=numberRandomTotal-numberRandom1;
					         return true;
				      }
	    			  if(triangle1.getNumberOfTriangle()==numberRandom1*2)
				      {  
	    				     numberRandomTotal=numberRandomTotal-numberRandom1*2;
					         return true;
				      }
	    			  if(triangle1.getNumberOfTriangle()==numberRandom1*3)
				      {  
	    				     numberRandomTotal=numberRandomTotal-numberRandom1*3;
					         return true;
				      }
	    			  if(triangle1.getNumberOfTriangle()==numberRandom1*4)
				      {  
	    				  if((triangles[numberRandom1].getVectorOfSoldier().size()>1)&&(triangles[numberRandom1].getVectorOfSoldier().get(0).getColor().equals("black")))
				    	  {
				    		  if((triangles[numberRandom1*2].getVectorOfSoldier().size()>1)&&(triangles[numberRandom1*2].getVectorOfSoldier().get(0).getColor().equals("black")))
					    	  {
				    			  if((triangles[numberRandom1*3].getVectorOfSoldier().size()>1)&&(triangles[numberRandom1*3].getVectorOfSoldier().get(0).getColor().equals("black")))
						    	  {
						    		  if((triangles[numberRandom1*4].getVectorOfSoldier().size()>1)&&(triangles[numberRandom1*4].getVectorOfSoldier().get(0).getColor().equals("black")))
							    	  {
							    		  return false;
							    	  }
						    	  }
					    	  }
				    	  }
				    	  if(triangles[numberRandom1+contemporaryTriangle].getVectorOfSoldier().size()<=1)
				    	  {
				    		  if((triangles[numberRandom1].getVectorOfSoldier().size()==1)&&(triangles[numberRandom1].getVectorOfSoldier().get(0).getColor().equals("black")))
				    		     removingSoldierInTheMiddleOfTheBoard(triangles[numberRandom1]);
				    		  numberRandomTotal=numberRandomTotal-numberRandom1;
				    		  if(numberRandomTotal==0)
				    		  {
				    			  System.out.println("יג");
				    			  cubeNumber1.setSituation();
				    			  cubeNumber2.setSituation();
				    		  }
				    		  return true;
				    	  }
				    	  if(triangles[numberRandom1*2].getVectorOfSoldier().size()<=1)
				    	  {
				    		  if((triangles[numberRandom1*2].getVectorOfSoldier().size()==1)&&(triangles[numberRandom1*2].getVectorOfSoldier().get(0).getColor().equals("black")))
				    		     removingSoldierInTheMiddleOfTheBoard(triangles[numberRandom1*2]);
				    		  numberRandomTotal=numberRandomTotal-numberRandom1*2;
				    		  if(numberRandomTotal==0)
				    		  {
				    			  System.out.println("יד");
				    			  cubeNumber1.setSituation();
				    			  cubeNumber2.setSituation();
				    		  }
				    		  return true;
				    	  }  
				    	  if(triangles[numberRandom1*3].getVectorOfSoldier().size()<=1)
				    	  {
				    		  if((triangles[numberRandom1*3].getVectorOfSoldier().size()==1)&&(triangles[numberRandom1*3].getVectorOfSoldier().get(0).getColor().equals("black")))
				    		     removingSoldierInTheMiddleOfTheBoard(triangles[numberRandom1*3]);
				    		  numberRandomTotal=numberRandomTotal-numberRandom1*3;
				    		  if(numberRandomTotal==0)
				    		  {
				    			  System.out.println("טו");
				    			  cubeNumber1.setSituation();
				    			  cubeNumber2.setSituation();
				    		  }
				    		  return true;
				    	  } 
				    	  if(triangles[numberRandom1*4].getVectorOfSoldier().size()<=1)
				    	  {
				    		  if((triangles[numberRandom1*4].getVectorOfSoldier().size()==1)&&(triangles[numberRandom1*4+contemporaryTriangle].getVectorOfSoldier().get(0).getColor().equals("black")))
				    		     removingSoldierInTheMiddleOfTheBoard(triangles[numberRandom1*4]);
				    		  numberRandomTotal=numberRandomTotal-numberRandom1*4;
				    		  if(numberRandomTotal==0)
				    		  {
				    			  System.out.println("טז");
				    			  cubeNumber1.setSituation();
				    			  cubeNumber2.setSituation();
				    		  }
				    		  return true;
				    	  } 			    	  
				    	 return false;
				      }
			       }
	    	  }
	    	}
	    	else // יתבצע במצב בו אין חייל מצבע לבן שנמצא במצב אכול כלומר באמצע הלוח
	    	{
		    	  System.out.println("ג1");
		    	  System.out.println("the double cube is:"+doubleCube);
	    	  if(!doubleCube) // יתבצע במצב בו אין דאבל בקוביות
	    	  {
		    	  System.out.println("ג2");
		       //if(triangle1.getNumberOfTriangle()<=(numberRandomTotal+contemporaryTriangle))
		    	  if((triangle1.getNumberOfTriangle()==(numberRandomTotal+contemporaryTriangle))||(triangle1.getNumberOfTriangle()==(numberRandom1+contemporaryTriangle))||(triangle1.getNumberOfTriangle()==(numberRandom2+contemporaryTriangle)))
		       {
			    	  System.out.println("ג3");
			      if(triangle1.getNumberOfTriangle()==numberRandom1+contemporaryTriangle)
			      {
			    	  System.out.println("יז");
				    cubeNumber1.setSituation();
				    return true;
			      }
			      if(triangle1.getNumberOfTriangle()==numberRandom2+contemporaryTriangle)
			      {
			    	  System.out.println("יח");
				    cubeNumber2.setSituation();
				    return true;
			      }
			      if(triangle1.getNumberOfTriangle()==numberRandomTotal+contemporaryTriangle)
			      {
			    	  if((triangles[numberRandom1+contemporaryTriangle].getVectorOfSoldier().size()>1)&&(triangles[numberRandom1+contemporaryTriangle].getVectorOfSoldier().get(0).getColor().equals("black")))
			    	  {
			    		  if((triangles[numberRandom2+contemporaryTriangle].getVectorOfSoldier().size()>1)&&(triangles[numberRandom2+contemporaryTriangle].getVectorOfSoldier().get(0).getColor().equals("black")))
				    	  {
				    		  return false;
				    	  }
			    	  }
			    	  if(triangles[numberRandom1+contemporaryTriangle].getVectorOfSoldier().size()<=1)
			    	  {
			    		  if((triangles[numberRandom1+contemporaryTriangle].getVectorOfSoldier().size()==1)&&(triangles[numberRandom1+contemporaryTriangle].getVectorOfSoldier().get(0).getColor().equals("black")))
			    		     removingSoldierInTheMiddleOfTheBoard(triangles[numberRandom1+contemporaryTriangle]);
			    		  System.out.println("יט");
				    	  cubeNumber1.setSituation();
					      cubeNumber2.setSituation();
			    		  return true;
			    	  }
			    	  if(triangles[numberRandom2+contemporaryTriangle].getVectorOfSoldier().size()<=1)
			    	  {
			    		  if((triangles[numberRandom2+contemporaryTriangle].getVectorOfSoldier().size()==1)&&(triangles[numberRandom2+contemporaryTriangle].getVectorOfSoldier().get(0).getColor().equals("black")))
			    		     removingSoldierInTheMiddleOfTheBoard(triangles[numberRandom2+contemporaryTriangle]);
			    		  System.out.println("כ");
				    	  cubeNumber1.setSituation();
					      cubeNumber2.setSituation();			    		  
					      return true;
			    	  }
				       return false;
			      }
		        }	
		      }
	    	  else // יתבצע במצב בו יש דאבל בקוביות
	    	  {
			      if(triangle1.getNumberOfTriangle()==numberRandom1+contemporaryTriangle)
			      {
			    	  if(triangles[numberRandom1+contemporaryTriangle].getVectorOfSoldier().size()==1)
			    		  removingSoldierInTheMiddleOfTheBoard(triangles[numberRandom1+contemporaryTriangle]);
			    	  else
			    	  {
				    	  if(triangles[numberRandom1+contemporaryTriangle].getVectorOfSoldier().size()>1)
				    		  if(triangles[numberRandom1+contemporaryTriangle].getVectorOfSoldier().get(0).getColor().equals("black"))
				    		      return false;
			    	  }
				    	  numberRandomTotal=numberRandomTotal-numberRandom1;
				    	  if(numberRandomTotal==0)
				    	  {
				    		  System.out.println("כא");
							  cubeNumber1.setSituation();
							  cubeNumber2.setSituation();
			    			  doubleCube=false;
				    	  }
					  return true;
			      }
			      if(triangle1.getNumberOfTriangle()==(numberRandom1*2)+contemporaryTriangle)
			      {
			    	  if(triangles[numberRandom1+contemporaryTriangle].getVectorOfSoldier().size()==1)
			    		  removingSoldierInTheMiddleOfTheBoard(triangles[numberRandom1+contemporaryTriangle]);
			    	  else
			    	  {
				    	  if(triangles[numberRandom1+contemporaryTriangle].getVectorOfSoldier().size()>1)
				    		  if(triangles[numberRandom1+contemporaryTriangle].getVectorOfSoldier().get(0).getColor().equals("black"))
				    		      return false;
			    	  }
			    	  numberRandomTotal=numberRandomTotal-(numberRandom1*2);
			    	  if(numberRandomTotal==0)
			    	  {
			    		  System.out.println("כב");
						  cubeNumber1.setSituation();
						  cubeNumber2.setSituation();
		    			  doubleCube=false;
			    	  }
					  return true;   
			      }
			      if(triangle1.getNumberOfTriangle()==(numberRandom1*3)+contemporaryTriangle)
			      {
			    	  if(triangles[numberRandom1+contemporaryTriangle].getVectorOfSoldier().size()==1)
			    		  removingSoldierInTheMiddleOfTheBoard(triangles[numberRandom1+contemporaryTriangle]);
			    	  else
			    	  {
				    	  if(triangles[numberRandom1+contemporaryTriangle].getVectorOfSoldier().size()>1)
				    		  if(triangles[numberRandom1+contemporaryTriangle].getVectorOfSoldier().get(0).getColor().equals("black"))
				    		      return false;
			    	  }
			    	  numberRandomTotal=numberRandomTotal-(numberRandom1*3);
			    	  if(numberRandomTotal==0)
			    	  {
			    		  System.out.println("כג");
						  cubeNumber1.setSituation();
						  cubeNumber2.setSituation();
		    			  doubleCube=false;
			    	  }
					  return true;   
			      }
			      if(triangle1.getNumberOfTriangle()==numberRandomTotal+contemporaryTriangle)
			      {
			    	  if((triangles[numberRandom1+contemporaryTriangle].getVectorOfSoldier().size()>1)&&(triangles[numberRandom1+contemporaryTriangle].getVectorOfSoldier().get(0).getColor().equals("black")))
			    	  {
			    		  if((triangles[numberRandom1*2+contemporaryTriangle].getVectorOfSoldier().size()>1)&&(triangles[numberRandom1*2+contemporaryTriangle].getVectorOfSoldier().get(0).getColor().equals("black")))
				    	  {
			    			  if((triangles[numberRandom1*3+contemporaryTriangle].getVectorOfSoldier().size()>1)&&(triangles[numberRandom1*3+contemporaryTriangle].getVectorOfSoldier().get(0).getColor().equals("black")))
					    	  {
					    		  if((triangles[numberRandom1*4+contemporaryTriangle].getVectorOfSoldier().size()>1)&&(triangles[numberRandom1*4+contemporaryTriangle].getVectorOfSoldier().get(0).getColor().equals("black")))
						    	  {
						    		  return false;
						    	  }
					    	  }
				    	  }
			    	  }
			    	  if(triangles[numberRandom1+contemporaryTriangle].getVectorOfSoldier().size()<=1)
			    	  {
			    		  if((triangles[numberRandom1+contemporaryTriangle].getVectorOfSoldier().size()==1)&&(triangles[numberRandom1+contemporaryTriangle].getVectorOfSoldier().get(0).getColor().equals("black")))
			    		     removingSoldierInTheMiddleOfTheBoard(triangles[numberRandom1+contemporaryTriangle]);
			    		  System.out.println("כד");
				    	  cubeNumber1.setSituation();
					      cubeNumber2.setSituation();
			    		  return true;
			    	  }
			    	  if(triangles[numberRandom1*2+contemporaryTriangle].getVectorOfSoldier().size()<=1)
			    	  {
			    		  if((triangles[numberRandom1*2+contemporaryTriangle].getVectorOfSoldier().size()==1)&&(triangles[numberRandom1*2+contemporaryTriangle].getVectorOfSoldier().get(0).getColor().equals("black")))
			    		     removingSoldierInTheMiddleOfTheBoard(triangles[numberRandom1*2+contemporaryTriangle]);
			    		  System.out.println("לא");
				    	  cubeNumber1.setSituation();
					      cubeNumber2.setSituation();
			    		  return true;
			    	  }  
			    	  if(triangles[numberRandom1*3+contemporaryTriangle].getVectorOfSoldier().size()<=1)
			    	  {
			    		  if((triangles[numberRandom1*3+contemporaryTriangle].getVectorOfSoldier().size()==1)&&(triangles[numberRandom1*3+contemporaryTriangle].getVectorOfSoldier().get(0).getColor().equals("black")))
			    		     removingSoldierInTheMiddleOfTheBoard(triangles[numberRandom1*3+contemporaryTriangle]);
			    		  System.out.println("לב");
				    	  cubeNumber1.setSituation();
					      cubeNumber2.setSituation();
			    		  return true;
			    	  } 
			    	  if(triangles[numberRandom1*4+contemporaryTriangle].getVectorOfSoldier().size()<=1)
			    	  {
			    		  if((triangles[numberRandom1*4+contemporaryTriangle].getVectorOfSoldier().size()==1)&&(triangles[numberRandom1*4+contemporaryTriangle].getVectorOfSoldier().get(0).getColor().equals("black")))
			    		     removingSoldierInTheMiddleOfTheBoard(triangles[numberRandom1*4+contemporaryTriangle]);
			    		  System.out.println("לג");
				    	  cubeNumber1.setSituation();
					      cubeNumber2.setSituation();
			    		  return true;
			    	  } 			    	  
			    	 return false;
			      }
	    	  }
	    	}
       	}
     }
        if(blackPlay)
        {
	    System.out.println("ח");
		if(choosenSoldier.getColor().equals("black")) // הזזה של החיילים השחורים בהתאם לכיוון שצריך
		{	
		    System.out.println("ט");
            System.out.println("The black boolean eaten soldier is 2 :"+eatenSoldierBlackFree);
		  if(!eatenSoldierBlackFree) // יתבצע במצב בו יש חייל שחור אכול כלומר באמצע הלוח
	       {
			 System.out.println("י");
			if(!doubleCube)
			{
			    System.out.println("כ");
			 if((numberRandom1>0)&&(numberRandom2>0))
		      {
				  System.out.println("ע");
			   if(triangle1.getNumberOfTriangle()==25-numberRandom1)
				{
				   cubeNumber1.setSituation();
				   return true;
				}
				if(triangle1.getNumberOfTriangle()==25-numberRandom2)
				{
				   cubeNumber2.setSituation();
				   return true;
				}
				if(triangle1.getNumberOfTriangle()==25-numberRandomTotal)
				{
					if((triangles[25-numberRandom1].getVectorOfSoldier().size()>1)&&(triangles[25-numberRandom1].getVectorOfSoldier().get(0).getColor().equals("white")))
			    	  {
			    		  if((triangles[25-numberRandom2].getVectorOfSoldier().size()>1)&&(triangles[25-numberRandom2].getVectorOfSoldier().get(0).getColor().equals("white")))
				    	  {
				    		  return false;
				    	  }
			    	  }
			    	  if(triangles[25-numberRandom1].getVectorOfSoldier().size()<=1)
			    	  {
			    		  if((triangles[25-numberRandom1].getVectorOfSoldier().size()==1)&&(triangles[25-numberRandom1].getVectorOfSoldier().get(0).getColor().equals("white")))
			    		     removingSoldierInTheMiddleOfTheBoard(triangles[25-numberRandom1]);
				    	  cubeNumber1.setSituation();
					      cubeNumber2.setSituation();
			    	  }
			    	  if(triangles[25-numberRandom2].getVectorOfSoldier().size()<=1)
			    	  {
			    		  if((triangles[25-numberRandom2].getVectorOfSoldier().size()==1)&&(triangles[25-numberRandom2].getVectorOfSoldier().get(0).getColor().equals("white")))
			    		     removingSoldierInTheMiddleOfTheBoard(triangles[25-numberRandom2]);
				    	  cubeNumber1.setSituation();
					      cubeNumber2.setSituation();
			    	  }
			    	  return true;
			    	  }
				     return false;					
				}
			 else                     
    		 {
	    		 if(numberRandom1>0)
	    		 {
	    			 if(triangle1.getNumberOfTriangle()==25-numberRandom1)
				      {  
					         cubeNumber1.setSituation();
					         return true;
				      }
	    		 }
	    		 if(numberRandom2>0)
	    		 {
	    			 if(triangle1.getNumberOfTriangle()==25-numberRandom2)
				      {  							
					         cubeNumber2.setSituation();
					         return true;
				      }
	    		 }
	    	  }
			}
			  else // יתבצע במצב של דאבל בקוביות ויש שחקן שחור אכול
			  {
				  if(triangle1.getNumberOfTriangle()==25-numberRandom1)
				    {
					      if(triangles[25-numberRandom1].getVectorOfSoldier().size()==1)
						         removingSoldierInTheMiddleOfTheBoard(triangles[25-numberRandom1]);
					      else
					      {
						      if(triangles[25-numberRandom1].getVectorOfSoldier().size()>1)
					    		  if(triangles[25-numberRandom1].getVectorOfSoldier().get(0).getColor().equals("white"))
						    	  return false;
					      }
					        numberRandomTotal=numberRandomTotal-numberRandom1;
					    	if(numberRandomTotal==0)
					    	{
								cubeNumber1.setSituation();
								cubeNumber2.setSituation();
								doubleCube=false;
					    	}
				        return true;
				    }
				    if(triangle1.getNumberOfTriangle()==25-(numberRandom1*2))
				    {
					      if(triangles[25-numberRandom1].getVectorOfSoldier().size()==1)
						         removingSoldierInTheMiddleOfTheBoard(triangles[contemporaryTriangle-numberRandom1]);
					      else
					      {
						      if(triangles[25-numberRandom1].getVectorOfSoldier().size()>1)
					    		  if(triangles[25-numberRandom1].getVectorOfSoldier().get(0).getColor().equals("white"))
						    	  return false;
					      }
					         numberRandomTotal=numberRandomTotal-(numberRandom1*2);
					    	 if(numberRandomTotal==0)
					    	 {
								 cubeNumber1.setSituation();
								 cubeNumber2.setSituation();
								 doubleCube=false;
					    	 }
				         return true; 
				    }
				    if(triangle1.getNumberOfTriangle()==25-(numberRandom1*3))
				    {
					      if(triangles[25-numberRandom1].getVectorOfSoldier().size()==1)
						         removingSoldierInTheMiddleOfTheBoard(triangles[25-numberRandom1]);
					      else
					      {
						      if(triangles[25-numberRandom1].getVectorOfSoldier().size()>1)
					    		  if(triangles[25-numberRandom1].getVectorOfSoldier().get(0).getColor().equals("white"))
						    	  return false;
					      }
					    	 numberRandomTotal=numberRandomTotal-(numberRandom1*3);
					    	 if(numberRandomTotal==0)
					    	 {
								 cubeNumber1.setSituation();
								 cubeNumber2.setSituation();
								 doubleCube=false;
					    	 }
				    	 return true; 
				    }
				    if(triangle1.getNumberOfTriangle()==25-numberRandomTotal)
				    {
				    	if((triangles[25-numberRandom1].getVectorOfSoldier().size()>1)&&(triangles[25-numberRandom1].getVectorOfSoldier().get(0).getColor().equals("white")))
				    	{
				    		  if((triangles[25-numberRandom1*2].getVectorOfSoldier().size()>1)&&(triangles[25-numberRandom1*2].getVectorOfSoldier().get(0).getColor().equals("white")))
				    		  {
				    			  if((triangles[25-numberRandom1*3].getVectorOfSoldier().size()>1)&&(triangles[25-numberRandom1*3].getVectorOfSoldier().get(0).getColor().equals("white")))
				    			  {
						    		  if((triangles[25-numberRandom1*4].getVectorOfSoldier().size()>1)&&(triangles[25-numberRandom1*4].getVectorOfSoldier().get(0).getColor().equals("white")))
						    		  {
						    			  return false;
						    		  }
				    			  }
				    		  }
				    	}
				    	if(triangles[25-numberRandom1].getVectorOfSoldier().size()<=1)
				    	  {
				    		  if((triangles[25-numberRandom1].getVectorOfSoldier().size()==1)&&(triangles[25-numberRandom1].getVectorOfSoldier().get(0).getColor().equals("white")))
				    		     removingSoldierInTheMiddleOfTheBoard(triangles[25-numberRandom1]);
					    	  cubeNumber1.setSituation();
						      cubeNumber2.setSituation();
				    		  return true;
				    	  }
				    	  if(triangles[25-numberRandom1*2].getVectorOfSoldier().size()<=1)
				    	  {
				    		  if((triangles[25-numberRandom1*2].getVectorOfSoldier().size()==1)&&(triangles[25-numberRandom1*2].getVectorOfSoldier().get(0).getColor().equals("white")))
				    		     removingSoldierInTheMiddleOfTheBoard(triangles[25-numberRandom1*2]);
					    	  cubeNumber1.setSituation();
						      cubeNumber2.setSituation();
				    		  return true;
				    	  } 
				    	  if(triangles[25-numberRandom1*3].getVectorOfSoldier().size()<=1)
				    	  {
				    		  if((triangles[25-numberRandom1*3].getVectorOfSoldier().size()==1)&&(triangles[25-numberRandom1*3].getVectorOfSoldier().get(0).getColor().equals("white")))
				    		     removingSoldierInTheMiddleOfTheBoard(triangles[25-numberRandom1*4]);
					    	  cubeNumber1.setSituation();
						      cubeNumber2.setSituation();
				    		  return true;
				    	  } 
				    	  if(triangles[25-numberRandom1*4].getVectorOfSoldier().size()<=1)
				    	  {
				    		  if((triangles[25-numberRandom1*4].getVectorOfSoldier().size()==1)&&(triangles[25-numberRandom1*4].getVectorOfSoldier().get(0).getColor().equals("white")))
				    		     removingSoldierInTheMiddleOfTheBoard(triangles[25-numberRandom1*4]);
					    	  cubeNumber1.setSituation();
						      cubeNumber2.setSituation();
				    		  return true;
				    	  } 
				      return false;
			  }
	    	}
		}
			else // יתבצע במצב בו אין חייל שחור אכול 
			{
			   if(!doubleCube) // יתבצע במצב בו אין דאבל בקוביות
			   {                                                                               // contemporaryTriangle-numberRandomTotal
				//if(triangle1.getNumberOfTriangle()<=numberRandomTotal+contemporaryTriangle) // numberRandomTotal+contemporaryTriangle
			    	if((triangle1.getNumberOfTriangle()==(contemporaryTriangle-numberRandomTotal))||(triangle1.getNumberOfTriangle()==(contemporaryTriangle-numberRandom1))||(triangle1.getNumberOfTriangle()==(contemporaryTriangle-numberRandom2)))
			    	{
				    if(triangle1.getNumberOfTriangle()==contemporaryTriangle-numberRandom1)
				    {
				    	System.out.println("22");
					   cubeNumber1.setSituation();
					   return true;
				    }
				    if(triangle1.getNumberOfTriangle()==contemporaryTriangle-numberRandom2)
				    {
				       System.out.println("23");
					   cubeNumber2.setSituation();
					   return true;
				    }
				    if(triangle1.getNumberOfTriangle()==contemporaryTriangle-numberRandomTotal)
				    {
				    	 if((triangles[contemporaryTriangle-numberRandom1].getVectorOfSoldier().size()>1)&&(triangles[contemporaryTriangle-numberRandom1].getVectorOfSoldier().get(0).getColor().equals("white")))
				    	 {
				    		 if((triangles[contemporaryTriangle-numberRandom2].getVectorOfSoldier().size()>1)&&(triangles[contemporaryTriangle-numberRandom2].getVectorOfSoldier().get(0).getColor().equals("white")))
					    	  {
					    		  return false;
					    	  }
				    	 }
				    	   if(triangles[contemporaryTriangle-numberRandom1].getVectorOfSoldier().size()<=1)
				    	    {
				    		    if((triangles[contemporaryTriangle-numberRandom1].getVectorOfSoldier().size()==1)&&(triangles[contemporaryTriangle-numberRandom1].getVectorOfSoldier().get(0).getColor().equals("white")))
				    		        removingSoldierInTheMiddleOfTheBoard(triangles[contemporaryTriangle-numberRandom1]);
				    		    cubeNumber1.setSituation();
						        cubeNumber2.setSituation();
				    		    return true;
				    	    }
				    	   if(triangles[contemporaryTriangle-numberRandom2].getVectorOfSoldier().size()<=1)
				    	    {
				    		    if((triangles[contemporaryTriangle-numberRandom2].getVectorOfSoldier().size()==1)&&(triangles[contemporaryTriangle-numberRandom2].getVectorOfSoldier().get(0).getColor().equals("white")))
				    		        removingSoldierInTheMiddleOfTheBoard(triangles[contemporaryTriangle-numberRandom2]);
				    		    cubeNumber1.setSituation();
						        cubeNumber2.setSituation();
				    		    return true;
				    	    }
				    	// }
					   return false;
					   // לבדוק את הענייו עם שימוש בשני הקוביות לשחקן שחור במצב בו אין חייל אכול באמצע הלוח מצבע שחור
				    }			    			    
			     }
			   }
			   else
			   {
				    if(triangle1.getNumberOfTriangle()==contemporaryTriangle-numberRandom1)
				    {
					      if(triangles[contemporaryTriangle-numberRandom1].getVectorOfSoldier().size()==1)
						         removingSoldierInTheMiddleOfTheBoard(triangles[contemporaryTriangle-numberRandom1]);
					      else
					      {
						      if(triangles[contemporaryTriangle-numberRandom1].getVectorOfSoldier().size()>1)
					    		  if(triangles[contemporaryTriangle-numberRandom1].getVectorOfSoldier().get(0).getColor().equals("white"))
						    	  return false;
					      }
					        numberRandomTotal=numberRandomTotal-numberRandom1;
					    	if(numberRandomTotal==0)
					    	{
								cubeNumber1.setSituation();
								cubeNumber2.setSituation();
								doubleCube=false;
					    	}
				        return true;
				    }
				    if(triangle1.getNumberOfTriangle()==contemporaryTriangle-(numberRandom1*2))
				    {
					      if(triangles[contemporaryTriangle-numberRandom1].getVectorOfSoldier().size()==1)
						         removingSoldierInTheMiddleOfTheBoard(triangles[contemporaryTriangle-numberRandom1]);
					      else
					      {
						      if(triangles[contemporaryTriangle-numberRandom1].getVectorOfSoldier().size()>1)
					    		  if(triangles[contemporaryTriangle-numberRandom1].getVectorOfSoldier().get(0).getColor().equals("white"))
						    	  return false;
					      }
					         numberRandomTotal=numberRandomTotal-(numberRandom1*2);
					    	 if(numberRandomTotal==0)
					    	 {
								 cubeNumber1.setSituation();
								 cubeNumber2.setSituation();
								 doubleCube=false;
					    	 }
				         return true; 
				    }
				    if(triangle1.getNumberOfTriangle()==contemporaryTriangle-(numberRandom1*3))
				    {
					      if(triangles[contemporaryTriangle-numberRandom1].getVectorOfSoldier().size()==1)
						         removingSoldierInTheMiddleOfTheBoard(triangles[contemporaryTriangle-numberRandom1]);
					      else
					      {
						      if(triangles[contemporaryTriangle-numberRandom1].getVectorOfSoldier().size()>1)
					    		  if(triangles[contemporaryTriangle-numberRandom1].getVectorOfSoldier().get(0).getColor().equals("white"))
						    	  return false;
					      }
					    	 numberRandomTotal=numberRandomTotal-(numberRandom1*3);
					    	 if(numberRandomTotal==0)
					    	 {
								 cubeNumber1.setSituation();
								 cubeNumber2.setSituation();
								 doubleCube=false;
					    	 }
				    	 return true; 
				    }
				    if(triangle1.getNumberOfTriangle()==contemporaryTriangle-numberRandomTotal)
				    {
				    	if((triangles[contemporaryTriangle-numberRandom1].getVectorOfSoldier().size()>1)&&(triangles[contemporaryTriangle-numberRandom1].getVectorOfSoldier().get(0).getColor().equals("white")))
				    	{
				    		  if((triangles[contemporaryTriangle-numberRandom1*2].getVectorOfSoldier().size()>1)&&(triangles[contemporaryTriangle-numberRandom1*2].getVectorOfSoldier().get(0).getColor().equals("white")))
				    		  {
				    			  if((triangles[contemporaryTriangle-numberRandom1*3].getVectorOfSoldier().size()>1)&&(triangles[contemporaryTriangle-numberRandom1*3].getVectorOfSoldier().get(0).getColor().equals("white")))
				    			  {
						    		  if((triangles[contemporaryTriangle-numberRandom1*4].getVectorOfSoldier().size()>1)&&(triangles[contemporaryTriangle-numberRandom1*4].getVectorOfSoldier().get(0).getColor().equals("white")))
						    		  {
						    			  return false;
						    		  }
				    			  }
				    		  }
				    	}
				    	if(triangles[contemporaryTriangle-numberRandom1].getVectorOfSoldier().size()<=1)
				    	  {
				    		  if((triangles[contemporaryTriangle-numberRandom1].getVectorOfSoldier().size()==1)&&(triangles[contemporaryTriangle-numberRandom1].getVectorOfSoldier().get(0).getColor().equals("white")))
				    		     removingSoldierInTheMiddleOfTheBoard(triangles[contemporaryTriangle-numberRandom1]);
					    	  cubeNumber1.setSituation();
						      cubeNumber2.setSituation();
				    		  return true;
				    	  }
				    	  if(triangles[contemporaryTriangle-numberRandom1*2].getVectorOfSoldier().size()<=1)
				    	  {
				    		  if((triangles[contemporaryTriangle-numberRandom1*2].getVectorOfSoldier().size()==1)&&(triangles[contemporaryTriangle-numberRandom1*2].getVectorOfSoldier().get(0).getColor().equals("white")))
				    		     removingSoldierInTheMiddleOfTheBoard(triangles[contemporaryTriangle-numberRandom1*2]);
					    	  cubeNumber1.setSituation();
						      cubeNumber2.setSituation();
				    		  return true;
				    	  } 
				    	  if(triangles[contemporaryTriangle-numberRandom1*3].getVectorOfSoldier().size()<=1)
				    	  {
				    		  if((triangles[contemporaryTriangle-numberRandom1*3].getVectorOfSoldier().size()==1)&&(triangles[contemporaryTriangle-numberRandom1*3].getVectorOfSoldier().get(0).getColor().equals("white")))
				    		     removingSoldierInTheMiddleOfTheBoard(triangles[contemporaryTriangle-numberRandom1*4]);
					    	  cubeNumber1.setSituation();
						      cubeNumber2.setSituation();
				    		  return true;
				    	  } 
				    	  if(triangles[contemporaryTriangle-numberRandom1*4].getVectorOfSoldier().size()<=1)
				    	  {
				    		  if((triangles[contemporaryTriangle-numberRandom1*4].getVectorOfSoldier().size()==1)&&(triangles[contemporaryTriangle-numberRandom1*4].getVectorOfSoldier().get(0).getColor().equals("white")))
				    		     removingSoldierInTheMiddleOfTheBoard(triangles[contemporaryTriangle-numberRandom1*4]);
					    	  cubeNumber1.setSituation();
						      cubeNumber2.setSituation();
						      return true;
				    	  }
				     } 
				      return false;
				    }
			   }
			}
        }
		return false;
	}
	
    public boolean checkingChooseTriangleFreeAndColor(Triangle triangle1) // פעולה שמחזירה אמת אם המשולש פנוי או שהחיילים שיש בו הם באותו צבע
	{                                                                     // ושקר אם לא
		if(triangle1.freeTriangle())			
			return true;
		
		else
		{
			Vector<Soldier> solidersHelp=triangle1.getVectorOfSoldier();
			if(((solidersHelp.get(solidersHelp.size()-1)).getColor().equals(choosenSoldier.getColor())))
					return true;
			else
				if(solidersHelp.size()==1)
					return true;
		}
		return false;
	}
	
	public int checkingEatenSoldiersInTheMiddleOfTheBoard() // פעולה שבודקת אם יש חיילים לבנים או שחורים אכולים ומחזירה את מספר החיילים האכולים שנמצאים
	{
		int countOfReturn=0;
		if(triangles[0].getVectorOfSoldier().size()==0)
		{
			eatenSoldierWhiteFree=true;
			eatenSoldierBlackFree=true;
		}
		if(whitePlay)
		{
			int countOfWhiteSoldiers=0;
			for(int i=0;i<triangles[0].getVectorOfSoldier().size();i++)
			{
				if(((triangles[0].getVectorOfSoldier().get(i)).getColor()).equals("white"))
					countOfWhiteSoldiers++;
			}
			
			countOfReturn=countOfWhiteSoldiers;
		}
		
		if(blackPlay)
		{
			int countOfBlackSoldiers=0;
			for(int i=0;i<triangles[0].getVectorOfSoldier().size();i++)
			{
				if(((triangles[0].getVectorOfSoldier().get(i)).getColor()).equals("black"))
					countOfBlackSoldiers++;
			}
			
			System.out.println("the count of black soldiers is:"+countOfBlackSoldiers);
			
			countOfReturn=countOfBlackSoldiers;
		}
		return countOfReturn;
	}
	
	public void removingSoldierInTheMiddleOfTheBoard(Triangle triangle1) // פעולה ששמה את החייל שנמצא במשולש שנבחר במשולש מספר 25
	{                                                                 // במקרה ומתקיים אכילה של חייל על חייל בצבע אחר והחייל הנאכל נמצא לבד   
		   Vector<Soldier> solidersHelp1=triangle1.getVectorOfSoldier();
		   if((solidersHelp1.size()==1)&&(!(solidersHelp1.get(0).getColor().equals(choosenSoldier.getColor())))) // יבצע את התנאי אם הווקטור במשולש הנבחר הוא אחד 
		   {                                                                                                                              // וגם אם השחקן במעלה המשולש שונה מהצבע של השחקן 
			   System.out.println("11");                                                                                                  // הנבחר להזזה
               Soldier soldier1=solidersHelp1.get(0);
               triangle1.removeSoldier();
               
               if(choosenSoldier.getContemporaryTriangle()==25)
           	   {
            	   int countOfSoldiers=checkingEatenSoldiersInTheMiddleOfTheBoard();
            	   if(((countOfSoldiers==0)&&(choosenSoldier.getContemporaryTriangle()==25))||((countOfSoldiers==1)&&(choosenSoldier.getContemporaryTriangle()!=25)))
            	   {
            		   if(whitePlay)
            			   eatenSoldierWhiteFree=true;
            		   if(blackPlay)
            			   eatenSoldierBlackFree=true;
            	   }
            	   
            	   
            	   
           	   }
               soldier1.setLocation(triangles[0].getLocationX(),triangles[0].getLocationY());
               soldier1.setContemporaryTriangle(25);
               triangles[0].addSoldier(soldier1);
               if(soldier1.getColor().equals("white"))
            	   eatenSoldierWhiteFree=false;
               if(soldier1.getColor().equals("black"))
               {
            	   eatenSoldierBlackFree=false;
            	   System.out.println("there is a black soldier in the middle of the board");
               	   System.out.println(" 2  the size of the middle triangle is:"+triangles[0].getVectorOfSoldier().size());
            	   
               }
		   }	
		   
		/*
		   Vector<Soldier> solidersHelp1=triangle1.getVectorOfSoldier();
		   Vector<Soldier> soldiersHelp2=triangles[0].getVectorOfSoldier();
		   if((solidersHelp1.size()==1)&&(((!(solidersHelp1.get(solidersHelp1.size()-1)).getColor().equals(choosenSoldier.getColor())))))
		   {
			   System.out.println("11");
                Soldier soldier1=solidersHelp1.get(0);
                //if(triangle1.getNumberOfTriangle()!=25)
            	   triangle1.removeSoldier();
            	if(choosenSoldier.getContemporaryTriangle()==25)
            	{
     			    System.out.println("12");
            	    triangles[0].removeSoldier();
            		if((choosenSoldier.getColor().equals("white"))&&(soldiersHelp2.size()>1))
            		{
         			     System.out.println("13");
            			 int countWhiteSoldiers=0;
		        		 for(int y=0;y<soldiersHelp2.size();y++)
		     			{
		     				if(soldiersHelp2.get(y).getColor().equals("white"))
		     					countWhiteSoldiers++;
		     			}
		        		 if((countWhiteSoldiers-1)==1)
		        		 {
		      			   System.out.println("14");
                	        eatenSoldierWhiteFree=true;
		        		 }
            		}
                	if((choosenSoldier.getColor().equals("black"))&&(soldiersHelp2.size()>1))
                	{
                		 int countBlackSoldiers=0;
		        		 for(int y=0;y<soldiersHelp2.size();y++)
		     			{
		     				if(soldiersHelp2.get(y).getColor().equals("black"))
		     					countBlackSoldiers++;
		     			}
		        		 if((countBlackSoldiers-1)==1)
		        		 {
			      			System.out.println("15");
                    	    eatenSoldierBlackFree=true;
		        		 }
		        		 if(countBlackSoldiers==0)
		        		 {
				      			System.out.println("16");
	                    	    eatenSoldierBlackFree=true;
			        	 } 
                	}
            	}
            	soldier1.setLocation(triangles[0].getLocationX(),triangles[0].getLocationY());
            	soldier1.setContemporaryTriangle(25);
            	triangles[0].addSoldier(soldier1);
            	if(soldier1.getColor().equals("white"))
            	    eatenSoldierWhiteFree=false;
            	if(soldier1.getColor().equals("black"))
            	{
                	eatenSoldierBlackFree=false;
                	System.out.println("The soldier is black");
                	System.out.println("the contemporary place for the black soldier is:"+soldier1.getContemporaryTriangle());
            	}
		   }
		   */
       	System.out.println("Finish!!!!!");
	 }
	
	public int deletSoldierInTheMiddleOfTheBoard() // פעולה שמוציאה חייל שנאכל במקרה ויש חייל מצבע אחר שגם נאכל והתור שייך לחייל הראשון שהוזכר
	{
		int placeOfSoldierInVector=triangles[0].getPlaceInVectorOfSoldier(choosenSoldier);
        System.out.println("the number is:");
        System.out.println(placeOfSoldierInVector);
        Vector <Soldier> soldiersHelp2=new Vector<Soldier>();
        soldiersHelp2=triangles[0].getVectorOfSoldier();
        soldiersHelp2.remove(placeOfSoldierInVector);
        triangles[0].setVectorOfSoldier(soldiersHelp2);       
        return placeOfSoldierInVector;
	}
	
	public boolean checkingIfPlayerCanTakeOutSoldiers() // פעולה שבודקת האם השחקן יכול להתחיל להוציא את החיילים מהלוח כלומר האם כל החיילים נמצאים ברבע לוח המתאים
	{
		int countOfSoldiers=0;
		Vector<Soldier> soldierHelp;
		
		if(player.getColorOfPlayer().equals("white"))
		{			
			for(int i=19;i<25;i++)
			{
				soldierHelp=triangles[i].getVectorOfSoldier();
				if(soldierHelp.size()>0)
				{
					for(int j=0;j<soldierHelp.size();j++)
					{
						if(soldierHelp.get(j).getColor().equals("white"))
							countOfSoldiers++;
					}
				}
			}
		}
		if(player.getColorOfPlayer().equals("black"))
		{			
			for(int i=1;i<7;i++)
			{
				soldierHelp=triangles[i].getVectorOfSoldier();
				if(soldierHelp.size()>0)
				{
					for(int j=0;j<soldierHelp.size();j++)
					{
						if(soldierHelp.get(j).getColor().equals("black"))
							countOfSoldiers++;
					}
				}
			}
		}
		if(countOfSoldiers==15)
		{
			player.setStartTakeOutSoldierFromTheBoard();
			return true;
		}
		
		return false;
	}
	
	public boolean checkingTheHighestSoldierForTakingOut()
	{
		if(player.getColorOfPlayer().equals("white"))
		{
			Vector<Soldier> vectorHelp=new Vector<Soldier>();
			
			int i=19;
			boolean found=false;
			while((i!=25)&&(!found))
			{
				vectorHelp=triangles[i].getVectorOfSoldier();
				if(vectorHelp.size()>0)
					found=true;
				i=i+1;
			}
			
			if(found)
			{
				if(vectorHelp.get(vectorHelp.size()-1)==choosenSoldier)
					return true;
			}
		}
		
		if(player.getColorOfPlayer().equals("black"))
		{
			Vector<Soldier> vectorHelp=new Vector<Soldier>();
			
			int i=6;
			boolean found=false;
			while((i!=0)&&(!found))
			{
				vectorHelp=triangles[i].getVectorOfSoldier();
				if(vectorHelp.size()>0)
					found=true;
				i=i-1;
			}
			
			if(found)
			{
				if(vectorHelp.get(vectorHelp.size()-1)==choosenSoldier)
					return true;
			}
		}
		
		return false;
	}
	
	public boolean CheckingSoldiersToMoveToTheEndTriangle()
	{
		if(player.getColorOfPlayer().equals("white"))
		{
			if(!doubleCube)
			{
				int contemporaryPlaceOfSoldier=choosenSoldier.getContemporaryTriangle();
				
				if(contemporaryPlaceOfSoldier+cubeNumber1.getNumberRandom()==25)
				{
					cubeNumber1.setSituation();
					return true;
				}
				
				else
				{
					if(contemporaryPlaceOfSoldier+cubeNumber2.getNumberRandom()==25)
					{
						cubeNumber2.setSituation();
						return true;
					}
					
					else
					{
						System.out.println("YYYYYYYYYYYYYY");
						if(checkingTheHighestSoldierForTakingOut())
						{
							System.out.println("AAAAAAAAAAAAAAAAAAAA");
							if(cubeNumber1.getNumberRandom()!=0)
							{
								System.out.println("BBBBBBBBBBBBBBBB");
								cubeNumber1.setSituation();
								return true;
							}
							
							if(cubeNumber2.getNumberRandom()!=0)
							{
								System.out.println("CCCCCCCCCCC");
								cubeNumber2.setSituation();
								return true;
							}
						}
					}
				}
			}
			
			else
			{
					int contemporaryPlaceOfSoldier=choosenSoldier.getContemporaryTriangle();
					
					if(contemporaryPlaceOfSoldier+cubeNumber1.getNumberRandom()==25)
					{
						/*
						numberOfUsing++;
						if(numberOfUsing==4)
						{
							cubeNumber1.setSituation();
							cubeNumber2.setSituation();
						}
						*/
						numberRandomTotal=numberRandomTotal-cubeNumber1.getNumberRandom();
						if(numberRandomTotal==0)
						{
							cubeNumber1.setSituation();
							cubeNumber2.setSituation();
						}
						return true;
					}
					
					else
					{
						if(contemporaryPlaceOfSoldier+cubeNumber2.getNumberRandom()==25)
						{
							/*
							numberOfUsing++;
							if(numberOfUsing==4)
							{
								cubeNumber1.setSituation();
								cubeNumber2.setSituation();
							}
							*/
							numberRandomTotal=numberRandomTotal-cubeNumber1.getNumberRandom();
							if(numberRandomTotal==0)
							{
								cubeNumber1.setSituation();
								cubeNumber2.setSituation();
							}
							return true;
						}
						
						else
						{
							System.out.println("YYYYYYYYYYYYYY");
							if(checkingTheHighestSoldierForTakingOut())
							{
								System.out.println("AAAAAAAAAAAAAAAAAAAA");
								if(cubeNumber1.getNumberRandom()!=0)
								{
									/*
									System.out.println("BBBBBBBBBBBBBBBB");
									numberOfUsing++;
									if(numberOfUsing==4)
									{
										cubeNumber1.setSituation();
										cubeNumber2.setSituation();
									}
									*/
									numberRandomTotal=numberRandomTotal-cubeNumber1.getNumberRandom();
									if(numberRandomTotal==0)
									{
										cubeNumber1.setSituation();
										cubeNumber2.setSituation();
									}
									return true;
								}
								
								if(cubeNumber2.getNumberRandom()!=0)
								{
									/*
									System.out.println("CCCCCCCCCCC");
									numberOfUsing++;
									if(numberOfUsing==4)
									{
										cubeNumber1.setSituation();
										cubeNumber2.setSituation();
									}
									*/
									numberRandomTotal=numberRandomTotal-cubeNumber1.getNumberRandom();
									if(numberRandomTotal==0)
									{
										cubeNumber1.setSituation();
										cubeNumber2.setSituation();
									}
									return true;
								}
							}
						}
					}
				}
			}
		if(player.getColorOfPlayer().equals("black"))
		{
			if(!doubleCube)
			{
				int contemporaryPlaceOfSoldier=choosenSoldier.getContemporaryTriangle();
				
				if(contemporaryPlaceOfSoldier-cubeNumber1.getNumberRandom()==0)
				{
					cubeNumber1.setSituation();
					return true;
				}
				
				else
				{
					if(contemporaryPlaceOfSoldier-cubeNumber2.getNumberRandom()==0)
					{
						cubeNumber2.setSituation();
						return true;
					}
					
					else
					{
						if(checkingTheHighestSoldierForTakingOut())
						{
							if(cubeNumber1.getNumberRandom()!=0)
							{
								cubeNumber1.setSituation();
								return true;
							}
							
							if(cubeNumber2.getNumberRandom()!=0)
							{
								cubeNumber2.setSituation();
								return true;
							}
						}
					}
				}
			}
			else
			{
				int contemporaryPlaceOfSoldier=choosenSoldier.getContemporaryTriangle();
				
				if(contemporaryPlaceOfSoldier-cubeNumber1.getNumberRandom()==0)
				{
					/*
					numberOfUsing++;
					if(numberOfUsing==4)
					{
						cubeNumber1.setSituation();
						cubeNumber2.setSituation();
					}
					*/
					numberRandomTotal=numberRandomTotal-cubeNumber1.getNumberRandom();
					if(numberRandomTotal==0)
					{
						cubeNumber1.setSituation();
						cubeNumber2.setSituation();
					}
					return true;
				}
				
				else
				{
					if(contemporaryPlaceOfSoldier-cubeNumber2.getNumberRandom()==0)
					{
						/*
						numberOfUsing++;
						if(numberOfUsing==4)
						{
							cubeNumber1.setSituation();
							cubeNumber2.setSituation();
						}
						*/
						numberRandomTotal=numberRandomTotal-cubeNumber1.getNumberRandom();
						if(numberRandomTotal==0)
						{
							cubeNumber1.setSituation();
							cubeNumber2.setSituation();
						}
						return true;
					}
					
					else
					{
						if(checkingTheHighestSoldierForTakingOut())
						{
							if(cubeNumber1.getNumberRandom()!=0)
							{
								/*
								numberOfUsing++;
								if(numberOfUsing==4)
								{
									cubeNumber1.setSituation();
									cubeNumber2.setSituation();
								}
								*/
								numberRandomTotal=numberRandomTotal-cubeNumber1.getNumberRandom();
								if(numberRandomTotal==0)
								{
									cubeNumber1.setSituation();
									cubeNumber2.setSituation();
								}
								return true;
							}
							
							if(cubeNumber2.getNumberRandom()!=0)
							{
								/*
								numberOfUsing++;
								if(numberOfUsing==4)
								{
									cubeNumber1.setSituation();
									cubeNumber2.setSituation();
								}
								*/
								numberRandomTotal=numberRandomTotal-cubeNumber1.getNumberRandom();
								if(numberRandomTotal==0)
								{
									cubeNumber1.setSituation();
									cubeNumber2.setSituation();
								}
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public void StartingOldGame(String optionToPlay)
	{
		try
		{
			//FileInputStream fin= new FileInputStream(optionToPlay+".dat");
			FileInputStream fin= new FileInputStream("Save/"+optionToPlay);
			ObjectInputStream ois= new ObjectInputStream(fin);
			
			this.triangles=(Triangle[])ois.readObject();
			this.cubeNumber1=(Cube)ois.readObject();
			this.cubeNumber2=(Cube)ois.readObject();
			this.player=(Player)ois.readObject();
			this.whitePlay=(Boolean)ois.readObject();
			this.blackPlay=(Boolean)ois.readObject();
			
			
	        SoldierAction actionSoldiers=new SoldierAction();// יצירת מאזין לחיילים
			
	        TriangleAction actionTriangle=new TriangleAction();// יצירה מאזין למשולשי הלוח
	        
	        for(int i=0;i<25;i++)
			{
				Vector<Soldier> soldierHelp=triangles[i].getVectorOfSoldier();
				for(int j=0;j<soldierHelp.size();j++)
				{
					soldierHelp.get(j).addActionListener(actionSoldiers);
					panelGameBoard.add(soldierHelp.get(j));
				}
				triangles[i].addActionListener(actionTriangle);
				panelGameBoard.add(triangles[i]);
			}	   
	           if((cubeNumber1!=null)&&(cubeNumber2!=null))
	           {
	               if((cubeNumber1.getNumberRandom()==cubeNumber2.getNumberRandom())&&(cubeNumber1.getNumberRandom()>0))
	               {
	            	   doubleCube=true;
	            	   firstTimeUsingDoubleCube=true;
	            	   numberOfUsing=0;
	               }   
	           }
               	        
	        System.out.println("Finish the laoding of the saving");
	        
	        turnOfComputer=true;
	        	
	        
		    ois.close();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}
		
	public void setTurnOfWhiteAndBlack()
	{
		if(whitePlay)
        {
     	   System.out.println("whitePlay:"+whitePlay);
     	   System.out.println("blackPlay:"+blackPlay);
     	   whitePlay=false;
     	   blackPlay=true;
     	   
        }
        else
        {
     	   System.out.println("whitePlay:"+whitePlay);
     	   System.out.println("blackPlay:"+blackPlay);
     	   whitePlay=true;
     	   blackPlay=false; 	   
        }
	}
	
	public void computerTurnAndPlay()
	{
		if((turnOfComputer)&&((cubeNumber1.getNumberRandom()==0)&&(cubeNumber2.getNumberRandom()==0)))
    	{
    		cubeNumber1.setNumberRandom();
            cubeNumber2.setNumberRandom();
            computer.getttingTheNewTrianglesAndCubes(triangles,cubeNumber1.getNumberRandom(),cubeNumber2.getNumberRandom(),finishTriangleOfComputer);
            turnOfComputer=false;
            if(cubeNumber1.getNumberRandom()==cubeNumber2.getNumberRandom())
            {
         	   doubleCube=true;
         	   firstTimeUsingDoubleCube=true;
            }
            setTurnOfWhiteAndBlack();
            
            computer.findingSoldierOfTheComputer();
            //computer.printingTheVector1();  
            //computer.printingTheVector2();
            //computer.printingTheVector3();
            computer.doingMoveOfTheComputer();
            
     	    //System.out.println("the size of the 19 triangle is print number2:"+triangles[19].getVectorOfSoldier().size());
            
            System.out.println("the cubes are 1:"+cubeNumber1.getNumberRandom());
            
            System.out.println("the cubes are 2:"+cubeNumber2.getNumberRandom());
            
            cubeNumber1.setSituation();
            cubeNumber2.setSituation();
            
            for(int i=0;i<triangles[0].getVectorOfSoldier().size();i++)
            {
            	if(triangles[0].getVectorOfSoldier().get(i).getColor().equals("white"))
            		eatenSoldierWhiteFree=false;
            		
            	if(triangles[0].getVectorOfSoldier().get(i).getColor().equals("black"))
            		eatenSoldierBlackFree=false;
            }     
            doubleCube=false;
            repaint();
            
            if(finishTriangleOfComputer.getVectorOfSoldier().size()==15)
            {
            	if(player.getColorOfPlayer().equals("white"))
            	{
            		new FinishScreen("Black",player.getNameOfPlayer());
            		dispose();
            	}
            	
            	if(player.getColorOfPlayer().equals("black"))
            	{
            		new FinishScreen("White",player.getNameOfPlayer());
            		dispose();
            	}
            }
            
    	}
	}
	
	public class ButtonAction implements ActionListener  
    {
        public void actionPerformed(ActionEvent e)
        {
            JButton actionButton=(JButton)e.getSource();
            if(actionButton==pause)
            {
            	new Pause(triangles,cubeNumber1,cubeNumber2,player,whitePlay,blackPlay);
            }
            
           // if((actionButton==rollTheDice)||(turnOfComputer))
            if(actionButton==rollTheDice)
            {
            	if((cubeNumber1.getNumberRandom()==0)&&(cubeNumber2.getNumberRandom()==0)) // יגריל מספרים חדשים רק אם היה שימוש בתור של השחקן כלומר אם
            	{                                                                          // היה שימוש במספרים שהוגרלו
            	   cubeNumber1.setNumberRandom();
                   cubeNumber2.setNumberRandom();
                                    
                   
                   setTurnOfWhiteAndBlack();
                   turnOfComputer=true;
                		   
                   
                   if(cubeNumber1.getNumberRandom()==cubeNumber2.getNumberRandom())
                   {
                	   doubleCube=true;
                	   firstTimeUsingDoubleCube=true;
                	   numberOfUsing=0;
                	   numberRandomTotal=(cubeNumber1.getNumberRandom()*4);
                   }
            	}
            }
        }
    }
	
	public class SoldierAction implements ActionListener // חיילי משחק
    {
        public void actionPerformed(ActionEvent e)
        {
            if(triangles[0].getVectorOfSoldier().size()>0)
            {
            	for(int i=0;i<triangles[0].getVectorOfSoldier().size();i++)
            	{
            		System.out.println("The soldier is+"+i+"  "+triangles[0].getVectorOfSoldier().get(i).getColor());
            	}
            }
	       	            
            Soldier actionSoldier=(Soldier)e.getSource(); 
            
            checkingIfPlayerCanTakeOutSoldiers();
            
            if(((whitePlay)&&(actionSoldier.getColor().equals("white")))||((blackPlay)&&(actionSoldier.getColor().equals("black"))))
            if(checkingNoEatenSoldier(actionSoldier))
            {
                if(checkingSoldierTriangle(actionSoldier)) // יראה כאילו החייל נלחץ רק במצב בו הוגרל מספר בקוביות וכאשר נשאר מספר שלא שומש באחת הקוביות
                {
            	    if((cubeNumber1.getNumberRandom()!=0)||(cubeNumber2.getNumberRandom()!=0))
            	     {      
            	    	if(blackPlay)
            	    	{
            	    		System.out.println("black");
            	           if(actionSoldier.getColor().equals("black"))
            	        	   actionSoldier.setIcon(new ImageIcon("Pictures/BlackSolider2.PNG"));
            	           
            	           GuideLine guideLine=new GuideLine(triangles,cubeNumber1.getNumberRandom(),cubeNumber2.getNumberRandom(),player.getColorOfPlayer(),choosenSoldier,panelGameBoard);
            	           
            	           panelGameBoard=guideLine.getPanelBaord();
            	    	}
            	    	if(whitePlay)
            	    	{
            	    		System.out.println("white");
            	           if(actionSoldier.getColor().equals("white"))
            	        	   actionSoldier.setIcon(new ImageIcon("Pictures/WhiteSolider2.PNG"));
            	           
            	           new GuideLine(triangles,cubeNumber1.getNumberRandom(),cubeNumber2.getNumberRandom(),player.getColorOfPlayer(),choosenSoldier,panelGameBoard);
            	    	}
            	    	if((!checkingTriangleForSoldier(actionSoldier))&&(!player.getStartTakeOutSoldierFromTheBoard()))// במצב זה יתבצע דילוג על התור של השחקן שהחייל שייך לו
            	    	{
            	    	if((!checkingTriangleMoves(actionSoldier))&&(!player.getStartTakeOutSoldierFromTheBoard()))
            	    	{
            	    		
            	    		System.out.println("TTTTTTTTTTTTT");
            	    		JOptionPane.showMessageDialog(null, "No More Moves", "Info",
                                		JOptionPane.INFORMATION_MESSAGE);
                 			   cubeNumber1.setSituation();
                               cubeNumber2.setSituation();
                               doubleCube=false;
                               if(actionSoldier.getColor().equals("white"))                                    //
                	        	   actionSoldier.setIcon(new ImageIcon("Pictures/WhiteSolider1.PNG"));         //
                               if(actionSoldier.getColor().equals("black"))                                      // 
                	        	   actionSoldier.setIcon(new ImageIcon("Pictures/BlackSolider1.PNG"));         //
            	    	}
            	    	}
                     }
                }
            }
            if(!checkingNoEatenSoldier(actionSoldier)) // יתבצע במידה ויש חייל שנאכל
            {
            	System.out.println("30");
            	if(checkingEatenSoldier(actionSoldier))
            	{
                	System.out.println("31");
            		if((cubeNumber1.getNumberRandom()!=0)||(cubeNumber2.getNumberRandom()!=0))
           	        {  
            		   if(checkingTriangleForEatenSoldier(actionSoldier));
            		   {
               			System.out.println("------------------------------------------");
            			   System.out.println("True for checking Traingle For Eaten Soldier");
            			   if(blackPlay)
            		           if(actionSoldier.getColor().equals("black"))
            		    	        actionSoldier.setIcon(new ImageIcon("Pictures/BlackSolider2.PNG"));
            			   if(whitePlay)
            		           if(actionSoldier.getColor().equals("white"))
            		    	        actionSoldier.setIcon(new ImageIcon("Pictures/WhiteSolider2.PNG"));
            		       choosenSoldier=actionSoldier;
            		   }
            		   if(!checkingTriangleForEatenSoldier(actionSoldier)) // יתבצע במצב בו אין מקום שבו אפשר לשים את החייל שנאכל.
            		   {                                                    // במצב זה יתבצע דילוג על התור של השחקן שהחייל הנאכל שייך לו
            			   JOptionPane.showMessageDialog(null, "No More Moves", "Info",
                           		JOptionPane.INFORMATION_MESSAGE);
            			   cubeNumber1.setSituation();
                           cubeNumber2.setSituation();
                           doubleCube=false;
                           if(actionSoldier.getColor().equals("white"))                                   //
            	        	   actionSoldier.setIcon(new ImageIcon("Pictures/WhiteSolider1.PNG"));      // 
                           if(actionSoldier.getColor().equals("black"))                                  //
            	        	   actionSoldier.setIcon(new ImageIcon("Pictures/BlackSolider1.PNG"));      //
            		   }
                    }
                }
            }
        }
    }            
	
	public class TriangleAction implements ActionListener // משולשי הלוח
    {
        public void actionPerformed(ActionEvent e)
        {
        	boolean foundInTheTriangle=false;
        	Vector <Soldier> checkingSoldierVector=new Vector<Soldier>();
            Triangle actionTriangle=(Triangle)e.getSource();
            if(choosenSoldier!=null) // יתבצע במידה ונבחר חייל להזזה
            {
            	if(actionTriangle!=finishTriangleOfPlayer)
            	{
            	for(int i=1;i<triangles.length;i++)
                {
                	if(triangles[i]==actionTriangle) // מציאת המשולש שעליו נלחץ
                	{
                		checkingSoldierVector=triangles[i].getVectorOfSoldier();          		
                		for(int j=0;j<checkingSoldierVector.size();j++) // בדיקה האם החייל שנלחץ נמצא במשולש שנבחר להעביר אליו את המשולש
                		{
                			if(checkingSoldierVector.get(j)==choosenSoldier)
                				foundInTheTriangle=true;
                		}
                		if(!foundInTheTriangle) // העברת החייל שנבחר למשולש יעד
                		{
                			System.out.println("1");
                			if(checkingChooseTriangleFreeAndColor(triangles[i]))
                			{
                    			System.out.println("2");

                				if(checkingChooseTriangle(triangles[i]))
                				{
                        			System.out.println("3");

                        			System.out.println("the boolean is true");
                			         if(choosenSoldier.getContemporaryTriangle()==25)
                			         {
                             			  System.out.println("4");
                			              int countOfSoldiers=checkingEatenSoldiersInTheMiddleOfTheBoard();
                			              if(countOfSoldiers==1)
                			              {
                			            	  if(whitePlay)
                			            		  eatenSoldierWhiteFree=true;
                			            	  if(blackPlay)
                			            		  eatenSoldierBlackFree=true;
                			              }
                			            	 removingSoldierInTheMiddleOfTheBoard(triangles[i]); 
                			            	 deletSoldierInTheMiddleOfTheBoard();
                     			     }
                			         else
                			         {
                 			            triangles[choosenSoldier.getContemporaryTriangle()].removeSoldier();
                			            removingSoldierInTheMiddleOfTheBoard(triangles[i]);
                			         }
                			         choosenSoldier.setContemporaryTriangle(triangles[i].getNumberOfTriangle());
                					 System.out.println("new contemporary place for last choosen soldier:"+choosenSoldier.getContemporaryTriangle());
                			         choosenSoldier.setLocation(triangles[i].getLocationX(),triangles[i].getLocationY());
                		             triangles[i].addSoldier(choosenSoldier);
                		        }
                				System.out.println("100");
                			}
                		}
                		if(foundInTheTriangle) // במידה והחייל שנלחץ נמצא במשולש שנלחץ אז תתבטל הלחיצה/בחירה של החייל
                		{
                			if(choosenSoldier.getColor().equals("black"))
                				choosenSoldier.setIcon(new ImageIcon("Pictures/BlackSolider1.PNG"));
                	        if(choosenSoldier.getColor().equals("white"))
                	            choosenSoldier.setIcon(new ImageIcon("Pictures/WhiteSolider1.PNG"));
                            //choosenSoldier=null;
                		}
                	}
                }
            }
            	else
            	{
            		
                	System.out.println(" The player can start to take soldiers out88888888888888888888888");
            		
            		if((checkingIfPlayerCanTakeOutSoldiers())||(player.getStartTakeOutSoldierFromTheBoard()))          		
            		{
						System.out.println("DDDDDDDDDDDDDDDDDD");
            			if(CheckingSoldiersToMoveToTheEndTriangle())
            			{
    						System.out.println("EEEEEEEEEEEEE");
            		    	triangles[choosenSoldier.getContemporaryTriangle()].removeSoldier();
            				finishTriangleOfPlayer.addSoldier(choosenSoldier);
                			choosenSoldier.setLocation(finishTriangleOfPlayer.getLocationX(),finishTriangleOfPlayer.getLocationY());
            			}
            		}
            	}
                if(choosenSoldier.getColor().equals("black"))
                	choosenSoldier.setIcon(new ImageIcon("Pictures/BlackSolider1.PNG"));
                if(choosenSoldier.getColor().equals("white"))
                	choosenSoldier.setIcon(new ImageIcon("Pictures/WhiteSolider1.PNG"));                                                                                           
                
                System.out.println("The white boolean eaten soldier is 1:"+eatenSoldierWhiteFree);
                System.out.println("The black boolean eaten soldier is 1:"+eatenSoldierBlackFree);
                
                if(triangles[0].getVectorOfSoldier().size()==0)     // 
                	triangles[0].setLocationY(590);                 //
                   
                
            	//System.out.println(" 3  the size of the middle triangle is:"+triangles[0].getVectorOfSoldier().size());
            	
            	
            	
            	if((turnOfComputer)&&((cubeNumber1.getNumberRandom()==0)&&(cubeNumber2.getNumberRandom()==0)))
            	    computerTurnAndPlay();
            	
                if(finishTriangleOfPlayer.getVectorOfSoldier().size()==15)
                {
                	if(player.getColorOfPlayer().equals("white"))
                	{
                		new FinishScreen("White",player.getNameOfPlayer());
                		dispose();
                	}
                	
                	if(player.getColorOfPlayer().equals("black"))
                	{
                		new FinishScreen("Black",player.getNameOfPlayer());
                		dispose();
                	}
                }
                /*
                if(finishTriangleOfComputer.getVectorOfSoldier().size()==15)
                {
                	if(player.getColorOfPlayer().equals("white"))
                	{
                		new FinishScreen("Black");
                		dispose();
                	}
                	
                	if(player.getColorOfPlayer().equals("black"))
                	{
                		new FinishScreen("White");
                		dispose();
                	}
                }
                */
            	System.out.println(" The size of the finish triangle is of player"+finishTriangleOfPlayer.getVectorOfSoldier().size());
            	
            	System.out.println(" The size of the finish triangle is of computer"+finishTriangleOfComputer.getVectorOfSoldier().size());
                
                if(player.getStartTakeOutSoldierFromTheBoard())
                {
                	System.out.println(" The player can start to take soldiers out99999999999999999999999999");
                }
            }
            choosenSoldier=null;
            if((cubeNumber1.getNumberRandom()==0)&&(cubeNumber2.getNumberRandom()==0))
            	doubleCube=false;
        }
    }
}		