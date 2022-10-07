import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class Menu extends JFrame
{
    private ButtonGame play;
    private ButtonGame save;
    private ButtonGame rules;
    private ButtonGame games;
    private ButtonGame exit;
    public Menu()
    {
        setSize(1600,900);
        JPanel panelMenu=new JPanel();
        setUndecorated(true);
        panelMenu.setLayout(null);
        JLabel theme=new JLabel(new ImageIcon("Pictures/backgammonTheme.PNG"));
        theme.setSize(1600,900);
        
        ButtonAction action=new ButtonAction();//יצרת מאזין
        play=new ButtonGame(320,122,645,265,"PlayMain");
        play.addActionListener(action);//יצירת קשר בין הכפתור למאזין
        save=new ButtonGame(320,122,644,424,"SaveMain");
        save.addActionListener(action);
        rules=new ButtonGame(320,122,644,574,"RulesMain");
        rules.addActionListener(action);
        games=new ButtonGame(320,122,644,732,"GamesMain");
        games.addActionListener(action);
        exit=new ButtonGame(320,122,1263,732,"ExitMain");
        exit.addActionListener(action);
        
        panelMenu.add(play);//הוספת הכפתורים לפאנל
        panelMenu.add(save);
        panelMenu.add(rules);
        panelMenu.add(games);
        panelMenu.add(exit);
        panelMenu.add(theme);
        add(panelMenu);//הוספת הפאנל לפריים
        setVisible(true);
    }
    
    
    private class ButtonAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JButton actionButton=(JButton)e.getSource();
            
            if(actionButton==save)
            {
              //Player player1=new Player(null,null,0);
              //new GameBoard(player1,"game");
            	//new Save(1,0);
            	new Save(1,0);
              dispose();
            }
            
            if(actionButton==rules)
            {
              new Rules(1);
              dispose();
            }
            if(actionButton==games)
            {
              new Games(1,0);
              dispose();
            }
            if(actionButton==exit)
            {
             new Exit();
             dispose();
            }
            if(actionButton==play)
            {
             new Play();
             dispose();
            }
        }
    }
}