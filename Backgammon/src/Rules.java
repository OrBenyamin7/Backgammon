import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
public class Rules extends JFrame
{
    private ButtonGame menu;
    private ButtonGame next;
    private ButtonGame back;
    private JLabel theme; 
    private int placeTheme;
    public Rules(int place)
    {
        setSize(1600,900);
        JPanel panelRules=new JPanel();
        setUndecorated(true);
        panelRules.setLayout(null);
        
        placeTheme=place;
        
        theme=new JLabel();    
        theme.setIcon(new ImageIcon("Pictures/rulesTheme"+placeTheme+".PNG"));
        theme.setSize(1600,900);
       
        ButtonAction action=new ButtonAction();
        
        //כפתורים
        menu=new ButtonGame(320,122,1263,732,"Menu");
        menu.addActionListener(action);
        next=new ButtonGame(320,122,395,764,"next");
        next.addActionListener(action);
        back=new ButtonGame(320,122,853,764,"back");
        back.addActionListener(action);
        
        if(placeTheme==1)
        	panelRules.add(next);
        else
        {
        	if(placeTheme==4)
        		panelRules.add(back);
        	else
        	{
        		panelRules.add(next);
        		panelRules.add(back);
        	}
        }
        
       
        panelRules.add(theme);
        panelRules.add(menu);
        add(panelRules);
        setVisible(true);
    }

    
    //מאזין לפעולת כפתורים
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
               placeTheme++;
               new Rules(placeTheme);
               dispose();
            }
            
            if(actionButton==back)
            {
               placeTheme--;
               new Rules(placeTheme);
               dispose();
            }
            
        } 	
            	
    }    	
            
}       
    
