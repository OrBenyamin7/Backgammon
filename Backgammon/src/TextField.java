import java.awt.*;
import javax.swing.*;
public class TextField extends JTextField
{
  public TextField(int length,int width,int locationX,int locationY)
  {
	  setSize(length,width);
	  setLocation(locationX,locationY);
	  setOpaque(false);
	  setBorder(null); 
	  setFont(new Font("Harabara Mais Demo",Font.BOLD,36));
  }
}
