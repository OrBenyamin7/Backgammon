//import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Exit extends JFrame
{
	private ButtonGame yes;
    private ButtonGame no;
    public Exit()
    {
    	setSize(1600,900);
        JPanel panelExit=new JPanel();
        setUndecorated(true);
        panelExit.setLayout(null);
        
        JLabel theme=new JLabel(new ImageIcon("Pictures/exitTheme.PNG"));
        theme.setSize(1600,900);
        
        ButtonAction action=new ButtonAction();//יצרת מאזין
        yes=new ButtonGame(320,122,908,324,"YesExit");
        yes.addActionListener(action);
        no=new ButtonGame(320,122,378,321,"NoExit");
        no.addActionListener(action);
        
        panelExit.add(yes);//הוספת הכפתורים לפאנל
        panelExit.add(no);
        panelExit.add(theme);
        add(panelExit);//הוספת הפאנל לפריים
        setVisible(true);
    }
    
    private class ButtonAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JButton actionButton=(JButton)e.getSource();
            if(actionButton==yes)
            	System.exit(0);
            if(actionButton==no)
            {
            	new Menu();
                dispose();
            }
        }
    }
    
}
