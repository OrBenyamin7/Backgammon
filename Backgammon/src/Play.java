import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Play extends JFrame 
{
	private ButtonGame white;
    private ButtonGame black;
    private ButtonGame yes;
    private ButtonGame no;
    private ButtonGame menu;
    private ButtonGame start;
    private ButtonGame clueText;
    private TextField field;
    private boolean chooseBlack;
    private boolean chooseWhite;
    private boolean chooseGuideYes;
    private boolean chooseGuideNo;
    public Play()
    {
    	setSize(1600,900);
        JPanel panelPlay=new JPanel();
        setUndecorated(true);
        panelPlay.setLayout(null);
        
        JLabel theme=new JLabel(new ImageIcon("Pictures/playTheme.PNG"));
        theme.setSize(1600,900);
        
        ButtonAction action=new ButtonAction();
        
        menu=new ButtonGame(320,122,1263,732,"Menu");
        menu.addActionListener(action);
        white=new ButtonGame(320,122,492,475,"WhitePlay");
        white.addActionListener(action);
        black=new ButtonGame(320,122,770,474,"BlackPlay");
        black.addActionListener(action);
        yes=new ButtonGame(320,122,555,595,"YesPlay1");
        yes.addActionListener(action);
        no=new ButtonGame(320,122,712,595,"NoPlay1");
        no.addActionListener(action);
        start=new ButtonGame(500,122,554,780,"StartPlay");
        start.addActionListener(action);
        clueText=new ButtonGame(115,90,1105,261,"CluePlay");
        clueText.addActionListener(action);

        
        field=new TextField(450,70,655,272);
        
        chooseBlack=false;
        chooseWhite=false;
        chooseGuideYes=false;
        chooseGuideNo=false;
        
        
        panelPlay.add(field);
        panelPlay.add(menu);
        panelPlay.add(white);
        panelPlay.add(black);
        panelPlay.add(yes);
        panelPlay.add(no);
        panelPlay.add(start);
        panelPlay.add(clueText);
        panelPlay.add(theme);
        add(panelPlay);
        setVisible(true);
    }
    
    public boolean inputChecking()// ����� ������ �� ������ ���� �� ��� ������ ���� ��� �����
    {
    	String checkingName=field.getText();
    	
    	boolean space=false; 
    	
    	if(checkingName.isEmpty())//���� ����� ����� ����� ��� ����� ��
    	{
    		return false;
    	}
    	
    	if(checkingName.length()==1)
    	{
    		if(checkingName.charAt(0)==' ')
		    	return false;
    	}
    	
    	if(checkingName.length()>24)
    	{
    		JOptionPane.showMessageDialog(null, "The name is too long", "Error",
            		JOptionPane.ERROR_MESSAGE);
    		return false;
    	}
    	else
    	{
    	   for(int i=0;i<checkingName.length()-1;i++)
    	   {
    		
    		  if(i==0)
    		   {
    			  if(!((checkingName.charAt(i)>='A')&&(checkingName.charAt(i)<='Z')))
    				return false;// ����� ����� ����� ����� ����� ������� ��� ����� ��� �� ��� �����
    		      if(checkingName.charAt(i)==' ')
    		    	return false;// ���� ����� ����� ����� ������� ����� ���� ������ ���
    		      if((checkingName.charAt(i)>='0')&&(checkingName.charAt(i)<='9'))
    		    	return false;// ���� ����� ����� ����� ���� ������ ��� ���� ��� ����
    		   }
    		  
    		  else
    		  {
    		     if(checkingName.charAt(i)==' ')
    		     {
    			    if(space)
    			     	  return false; // ����� ����� ����� ����� ��� ���� ����� ��� ���
    			    if(!space)
    				      space=true;
    		     }
    		
    		    if(checkingName.charAt(i-1)==' ')
    		    {
    			    if(!((checkingName.charAt(i)>='A')&&(checkingName.charAt(i)<='Z')))
    			    {
    				    return false; //����� ����� ����� ����� ����� ������� ���� ����� �� ��� �����
    		        }
    		    }
    	     }
    	   }
        }
    	return true;
    	
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
            
            
            if(actionButton==white)
            {
            	white.setIcon(new ImageIcon("Pictures/WhitePlay2.PNG"));//
            	if(chooseBlack)//�� ����� ������ ��� ���� ��� ������ �������(����� ������)
            	{
            	   black.setIcon(new ImageIcon("Pictures/BlackPlay.PNG"));
            	   chooseBlack=false;
            	}
            	if(chooseWhite)//�� ������ ��� ���� ��� ����� �� ��� ������ �������(����� �����)
            	{
                	white.setIcon(new ImageIcon("Pictures/WhitePlay.PNG"));
                	chooseWhite=false;
            	}
            	else
            	chooseWhite=true;
            }
            
            
            if(actionButton==black)
            {
            	black.setIcon(new ImageIcon("Pictures/BlackPlay2.PNG"));
            	if(chooseWhite)//�� ����� ������ ��� ���� ��� ������ �������(����� ������)
            	{
            	   white.setIcon(new ImageIcon("Pictures/WhitePlay.PNG"));
            	   chooseWhite=false;
            	}
            	if(chooseBlack)//�� ������ ��� ���� ��� ����� �� ��� ������ �������(����� �����)
            	{
                	black.setIcon(new ImageIcon("Pictures/BlackPlay.PNG"));
                	chooseBlack=false;
            	}
            	else
            	chooseBlack=true;
            }
            
            
            
            
            if(actionButton==yes)
            {
            	yes.setIcon(new ImageIcon("Pictures/YesPlay2.PNG"));
            	if(chooseGuideNo)//�� ����� ������ ��� ���� ��� ������ �������(����� ������)
            	{
            	   no.setIcon(new ImageIcon("Pictures/NoPlay1.PNG"));
                   chooseGuideNo=false;
            	}
            	if(chooseGuideYes)//�� ������ ��� ���� ��� ����� �� ��� ������ �������(����� �����)
            	{
               	   yes.setIcon(new ImageIcon("Pictures/YesPlay1.PNG"));
               	   chooseGuideYes=false;
            	}
            	else
            	chooseGuideYes=true;
            }
            
            
            if(actionButton==no)
            {
            	no.setIcon(new ImageIcon("Pictures/NoPlay2.PNG"));
            	if(chooseGuideYes)//�� ����� ������ ��� ���� ��� ������ �������(����� ������)
            	{
            	  yes.setIcon(new ImageIcon("Pictures/YesPlay1.PNG"));
            	  chooseGuideYes=false;
            	}
            	if(chooseGuideNo)//�� ������ ��� ���� ��� ����� �� ��� ������ �������(����� �����)
            	{
             	   no.setIcon(new ImageIcon("Pictures/NoPlay1.PNG"));
             	   chooseGuideNo=false;
            	}
            	else
            	chooseGuideNo=true;
            }
            
            if(actionButton==clueText)
            {
            	JOptionPane.showMessageDialog(null, " �� ������ �� ���(�� ���� ��� �����). "
            			+ "��� ������ ������� ������ ���."
            			+ "���� ������� ��� ����� ���� ������ ����� ����� �����."
            			+ "�� ������ ����� ��� ���� ���� ������.", "Info",
                		JOptionPane.INFORMATION_MESSAGE);
            }
            
            
            if(actionButton==start)
            {
            	boolean inputName=inputChecking();
            	
            	if(!inputName)
            		JOptionPane.showMessageDialog(null, "You did not enter legal name",
                			"Error",JOptionPane.ERROR_MESSAGE);
            		

                	
            	if((!chooseBlack)&&(!chooseWhite))//���� ����� ����� ����� ��� ���� ��� ����
            		JOptionPane.showMessageDialog(null, "You did not choose a color", "Error",
                    		JOptionPane.ERROR_MESSAGE);
            	
            	if((!chooseGuideYes)&&(!chooseGuideNo))// ���� ����� ����� ����� ��� ���� �� ������ ��� ����
            		JOptionPane.showMessageDialog(null, "You did not choose if you would like to use GuideLine",
            				"Error",JOptionPane.ERROR_MESSAGE);
            	
            	
            	if((inputName)&&((!chooseBlack)&&(chooseWhite)||((chooseBlack)&&(!chooseWhite))))
            	{
            		if((chooseGuideYes)&&(!chooseGuideNo)||(!chooseGuideYes)&&(chooseGuideNo))
            		{
            		  String choosenColor;
            		  
            		  if(chooseWhite)
            			  choosenColor="white";
            		  else
            			  choosenColor="black";
            		  
            		  int guideLine;
            		  if(chooseGuideYes)
            			  guideLine=1;
            		  else
            			  guideLine=0;
            			            		  
            		  Player player=new Player(field.getText(),choosenColor,guideLine);
            		  new GameBoard(player,"new Game");
            	      dispose();
            		}
            	}
            }
        } 	      	
    }
}
