
public class Player implements java.io.Serializable
{
  private String nameOfPlayer;
  private String color;
  private boolean guideLine;
  private boolean startTakeOutSoldierFromTheBoard;
  private boolean finishTakingOutSoldiers;
  
  public Player(String name, String choosenColor, int guide)
  {
	  this.nameOfPlayer=name;
	  this.color=choosenColor;
	  
	  if(guide==1)
		  this.guideLine=true;
	  if(guide==0)
		  this.guideLine=false;
	  
	  this.startTakeOutSoldierFromTheBoard=false;
	  this.finishTakingOutSoldiers=false;
  }
  
  public String getNameOfPlayer()
  {
	  return this.nameOfPlayer;
  }
  
  public String getColorOfPlayer()
  {
	  return this.color;
  }
 
  public boolean getUseGuide()
  {
	  return this.guideLine;
  }
  
  public void setUseGuide(boolean usingGuide)
  {
	  this.guideLine=usingGuide;
  }
  
  public boolean getStartTakeOutSoldierFromTheBoard()
  {
	  return this.startTakeOutSoldierFromTheBoard;
  }
  
  public void setStartTakeOutSoldierFromTheBoard()
  {
	  this.startTakeOutSoldierFromTheBoard=true;
  }
  
  public boolean getFinishTakingOutSoldiers()
  {
	  return this.finishTakingOutSoldiers;
  }
  
  public void setFinishTakingOutSoldiers()
  {
	  this.finishTakingOutSoldiers=true;
  }
  
}
