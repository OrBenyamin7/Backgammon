import java.awt.*;
import javax.swing.*;
public class ButtonGame extends JButton
{
   public ButtonGame(int length,int width,int locationX,int locationY,String name)
   {
       setSize(length,width);
       setBorder(null);
       setContentAreaFilled(false);
       setIcon(new ImageIcon("Pictures/"+name+".PNG"));
       setLocation(locationX,locationY);
    }
}