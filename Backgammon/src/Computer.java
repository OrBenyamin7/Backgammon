import java.util.Vector;
import java.awt.*;

import javax.swing.*;

public class Computer 
{                                             
   private String color;
   private Triangle[] triangles;
   private int cubeNumber1;
   private int cubeNumber2;
   private Vector<Move> movesCubeNumber1;
   private Vector<Move> movesCubeNumber2;
   private Vector<Move> movesTwoCubes;
   private Vector<Move> movesDoubleCube;
   //
   private Vector<Move> takingOutMovesCubeNumber1;
   private Vector<Move> takingOutMovesCubeNumber2;
   private Vector<Move> takingOutMovesTwoCubes;
   private Vector<Move> takingOutMovesDoubleCube;
   //
   private Vector<Move> VectorEatingCubeNumber1;
   private Vector<Move> VectorEatingCubeNumber2;
   private int numberOfUsedCubeTotalLeft;
   private int priority1;
   private int priority2;
   private int priorityTotal;   
   private boolean alreadySet;
   private boolean EatenSoldierInTheMiddleOfTheBoardFree;
   private boolean takingOutSoldiers;
   private Triangle finishTriangleOfComputer;
   private int vectorCubeLeftInActionOfTakingOut;
   private boolean situationOfBiggerCubesForSoldier;
   private boolean alreadyDoneAMove;
   
   public Computer(String colorOfComputer,Triangle[] triangles,int numberOfCube1,int numberOfCube2,Triangle finishTriangleOfComputer)
   {	   
	   this.color=colorOfComputer;
	   
	   this.triangles=triangles;
	   
	   this.cubeNumber1=numberOfCube1;
	   
	   this.cubeNumber2=numberOfCube2;
	   
	   this.movesCubeNumber1=new Vector<Move>();
	   this.movesCubeNumber2=new Vector<Move>();
	   this.movesTwoCubes=new Vector<Move>();
	   this.movesDoubleCube=new Vector<Move>();
	   
	   //
	   this.takingOutMovesCubeNumber1=new Vector<Move>();
	   this.takingOutMovesCubeNumber2=new Vector<Move>();
	   this.takingOutMovesTwoCubes=new Vector<Move>();
	   this.takingOutMovesDoubleCube=new Vector<Move>();
	   //
	   VectorEatingCubeNumber1=new Vector<Move>();
	   VectorEatingCubeNumber2=new Vector<Move>();
	   this.numberOfUsedCubeTotalLeft=0;
	   	   
	   this.priority1=0;
	   this.priority2=0;
	   this.priorityTotal=0;
	   
	   this.alreadySet=false;
	   
	   this.EatenSoldierInTheMiddleOfTheBoardFree=true;
	   
	   this.takingOutSoldiers=false;
	   
	   this.finishTriangleOfComputer=finishTriangleOfComputer;
	   
	   this.vectorCubeLeftInActionOfTakingOut=0;
	   
	   this.situationOfBiggerCubesForSoldier=false;
	   
       this.alreadyDoneAMove=false;
	   
	   
	   // 0- מסמל תנועה של חייל למשולש ריק
      // 1- מסמל תנועה של חייל למשולש שיש בו חייל ששייך לצבע שלו
   
   }
     
   public String getColor()
   {
	   return this.color;
   }
   
   public void getttingTheNewTrianglesAndCubes(Triangle[] trianglesArry,int numberOfCube1,int numberOfCube2,Triangle finishTriangleOfComputer )
   {
	   this.triangles=trianglesArry;
	   
	   this.cubeNumber1=numberOfCube1;
	   this.cubeNumber2=numberOfCube2;
	   
	   this.priority1=0;
	   this.priority2=0;
	   this.priorityTotal=0;
	   
	   this.numberOfUsedCubeTotalLeft=0;
	   
	   this.alreadySet=false;
	   
	   this.finishTriangleOfComputer=finishTriangleOfComputer;
	   
	   this.vectorCubeLeftInActionOfTakingOut=0;
	   
	   this.situationOfBiggerCubesForSoldier=false;
	   
	   this.alreadyDoneAMove=false;
	   
	   
   } 
   
   public void setPriority(int number)
   {
	   if(number==1)
	      this.priority1=1;
	   if(number==2)
		   this.priority2=1;
	   if(number==0)
		   this.priorityTotal=1;
   }
   
   public void findingSoldierOfTheComputer()
   { 
	   Soldier soldierOption=null;
	   
	   System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
	   
	   System.out.println("the size of the triangle 25 is#####################:"+triangles[0].getVectorOfSoldier().size());
	   
	   checkingEatenSoldierInTheMiddleOfTheBoard();
	   
	   if(EatenSoldierInTheMiddleOfTheBoardFree)
	   {
		   for(int i=1;i<triangles.length-1;i++)
		   {
			   int sizeOfTheVector=triangles[i].getVectorOfSoldier().size();
			   
			   if(sizeOfTheVector>0)
			   {
				   if(triangles[i].getVectorOfSoldier().get(sizeOfTheVector-1).getColor().equals(color))
				   {
					   soldierOption=triangles[i].getVectorOfSoldier().get(sizeOfTheVector-1);
					   checkingPlaceForSoldier(soldierOption);
				   }
			   }
			   soldierOption=null;
		   }
	   }
	   else
	   {
		   for(int i=0;i<triangles[0].getVectorOfSoldier().size();i++)
		   {
			   if(triangles[0].getVectorOfSoldier().get(i).getColor().equals(color))
			   {
				   System.out.println("the eaten soldier of the Traignle:"+triangles[0].getVectorOfSoldier().get(i).getContemporaryTriangle());
				   checkingPlaceForEatenSoldierInTheBoard(triangles[0].getVectorOfSoldier().get(i));
			   }
		   }
	   }
	     
	   if((checkingIfComputerCanStartTakeOutSoldier())||(takingOutSoldiers))
	   {
		   takingOutSoldiers=true;
		   if(color.equals("white"))
		   {
			   for(int i=19;i<24;i++)
			   {
				   int sizeOfTheVector=triangles[i].getVectorOfSoldier().size();
				   
				   if(sizeOfTheVector>0)
				   {
					   if(triangles[i].getVectorOfSoldier().get(sizeOfTheVector-1).getColor().equals(color))
					   {
						   soldierOption=triangles[i].getVectorOfSoldier().get(sizeOfTheVector-1);
						   checkingPlaceForSoldier(soldierOption);
					   }
				   }
				   soldierOption=null;
			   }
		   }
		   
		   if(color.equals("black"))
		   {
			   for(int i=6;i>=1;i--)
			   {
				   int sizeOfTheVector=triangles[i].getVectorOfSoldier().size();
				   
				   if(sizeOfTheVector>0)
				   {
					   if(triangles[i].getVectorOfSoldier().get(sizeOfTheVector-1).getColor().equals(color))
					   {
						   soldierOption=triangles[i].getVectorOfSoldier().get(sizeOfTheVector-1);
						   checkingPlaceForSoldier(soldierOption);
					   }
				   }
				   soldierOption=null;
			   } 
		   }
	   }
   }
   
   public void checkingPlaceForSoldier(Soldier soldier1)
   {   
	   if(cubeNumber1==cubeNumber2)
	   {
		   System.out.println("@@@@@@@@@@@@@@@@@@@@!");
		   if(!alreadySet)
		   {
			   alreadySet=true;
			   numberOfUsedCubeTotalLeft=numberOfUsedCubeTotalLeft+cubeNumber1*4;
		   }
		   System.out.println("numberOfUsedCubeTotalLeft!!!!!!!!!!!!!!"+numberOfUsedCubeTotalLeft);
		   checkingPlaceAccordingDoubleCube(soldier1);
	   }
	   
	   else
	   {
		   int contemporaryPlace=soldier1.getContemporaryTriangle();
		   
		   Vector<Soldier>soldierHelp=new Vector<Soldier>();
		   	   
		   if((color.equals("white"))&&(((contemporaryPlace+cubeNumber1+cubeNumber2)<=25)||((contemporaryPlace+cubeNumber1)<=25)||((contemporaryPlace+cubeNumber2)<=25)))
		   {
			   if((contemporaryPlace+cubeNumber1)<25)
			   {
				   soldierHelp=triangles[contemporaryPlace+cubeNumber1].getVectorOfSoldier();
				   Move move1=new Move(triangles[contemporaryPlace],triangles[contemporaryPlace+cubeNumber1],cubeNumber1);
				   if(soldierHelp.size()==0)
				   {
					   System.out.println("1");
					   if(priority1==0)
						   movesCubeNumber1.add(move1);
				   }
				   if((soldierHelp.size()>0)&&(soldierHelp.get(soldierHelp.size()-1).getColor().equals(color)))
				   {
					   System.out.println("2");
					   if(priority1==0)
					   {
						   System.out.println("3");
						   setPriority(1);
						   movesCubeNumber1.clear();
					   }
					   movesCubeNumber1.add(move1);
				   }
				   
				   if((soldierHelp.size()==1)&&(!soldierHelp.get(soldierHelp.size()-1).getColor().equals(color)))
					   VectorEatingCubeNumber1.add(move1);
		       }
			   
			   if(takingOutSoldiers)
			   {
				   if((contemporaryPlace+cubeNumber1)==25)
				   {
					   Move move1=new Move(triangles[contemporaryPlace],finishTriangleOfComputer,cubeNumber1);
					   takingOutMovesCubeNumber1.add(move1);
			       }
			   }
			   
			   if((contemporaryPlace+cubeNumber2)<25)
			   {
				   soldierHelp=triangles[contemporaryPlace+cubeNumber2].getVectorOfSoldier();
				   Move move1=new Move(triangles[contemporaryPlace],triangles[contemporaryPlace+cubeNumber2],cubeNumber2);
				   if(soldierHelp.size()==0)
				   {
					   System.out.println("5");
					   if(priority2==0)
						   movesCubeNumber2.add(move1);
				   }
				   if((soldierHelp.size()>0)&&(soldierHelp.get(soldierHelp.size()-1).getColor().equals(color)))
				   {
					   System.out.println("6");
					   if(priority2==0)
					   {
						   System.out.println("7");
						   setPriority(2);
						   movesCubeNumber2.clear();
					   }
					   movesCubeNumber2.add(move1);
				   }
				   
				   if((soldierHelp.size()==1)&&(!soldierHelp.get(soldierHelp.size()-1).getColor().equals(color)))
					   VectorEatingCubeNumber2.add(move1);
		       }
			   
			   if(takingOutSoldiers)
			   {
				   if((contemporaryPlace+cubeNumber2)==25)
				   {
					   Move move1=new Move(triangles[contemporaryPlace],finishTriangleOfComputer,cubeNumber2);
					   takingOutMovesCubeNumber2.add(move1);
			       }
			   }
			   
			   if((contemporaryPlace+cubeNumber1+cubeNumber2)<25)
			   {
				   soldierHelp=triangles[contemporaryPlace+cubeNumber1+cubeNumber2].getVectorOfSoldier();
				   Move move1=new Move(triangles[contemporaryPlace],triangles[contemporaryPlace+cubeNumber1+cubeNumber2],(cubeNumber1+cubeNumber2));
				   if((soldierHelp.size()==0)&&((triangles[contemporaryPlace+cubeNumber1].getVectorOfSoldier().size()==0)||(triangles[contemporaryPlace+cubeNumber2].getVectorOfSoldier().size()==0)))
				   {
					   System.out.println("9");
					   if(priorityTotal==0)
						   movesTwoCubes.add(move1);
				   }
				   if((soldierHelp.size()>0)&&(soldierHelp.get(soldierHelp.size()-1).getColor().equals(color)))
				   {
					   if((triangles[contemporaryPlace+cubeNumber1].getVectorOfSoldier().size()>0)&&(triangles[contemporaryPlace+cubeNumber1].getVectorOfSoldier().get(triangles[contemporaryPlace+cubeNumber1].getVectorOfSoldier().size()-1).equals(color))||(((triangles[contemporaryPlace+cubeNumber2].getVectorOfSoldier().size()>0)&&(triangles[contemporaryPlace+cubeNumber2].getVectorOfSoldier().get(triangles[contemporaryPlace+cubeNumber2].getVectorOfSoldier().size()-1).equals(color)))))
					   {
						   System.out.println("10");
						   if(priorityTotal==0)
						   {
							   System.out.println("11");
							   setPriority(0);
							   movesTwoCubes.clear();
						   }
						   movesTwoCubes.add(move1);
					   }
				   }
		       }
			   
			   if(takingOutSoldiers)
			   {
				   if((contemporaryPlace+cubeNumber1+cubeNumber2)==25)
				   {
					   Move move1=new Move(triangles[contemporaryPlace],finishTriangleOfComputer,(cubeNumber1+cubeNumber2));
					   takingOutMovesTwoCubes.add(move1);
			       }
			   }
		   }
		   
		   if((color.equals("black"))&&((contemporaryPlace-(cubeNumber1+cubeNumber2)>=0)||((contemporaryPlace-cubeNumber1)>=0)||((contemporaryPlace-cubeNumber2)>=0)))
		   {
			   if((contemporaryPlace-cubeNumber1)>0)
			   {
				   soldierHelp=triangles[contemporaryPlace-cubeNumber1].getVectorOfSoldier();
				   Move move1=new Move(triangles[contemporaryPlace],triangles[contemporaryPlace-cubeNumber1],cubeNumber1);
				   if(soldierHelp.size()==0)
				   {
					   if(priority1==0)
						   movesCubeNumber1.add(move1);
				   }
				   if((soldierHelp.size()>0)&&(soldierHelp.get(soldierHelp.size()-1).getColor().equals(color)))
				   {
					   if(priority1==0)
					   {
						   setPriority(1);
						   movesCubeNumber1.clear();
					   }
					   movesCubeNumber1.add(move1);
				   }
				   
				   if((soldierHelp.size()==1)&&(!soldierHelp.get(soldierHelp.size()-1).getColor().equals(color)))
					   VectorEatingCubeNumber1.add(move1);
		       }
			   
			   if(takingOutSoldiers)
			   {
				   if((contemporaryPlace-cubeNumber1)==0)
				   {
					   Move move1=new Move(triangles[contemporaryPlace],finishTriangleOfComputer,cubeNumber1);
					   takingOutMovesCubeNumber1.add(move1);
			       }
			   }
			   
			   if((contemporaryPlace-cubeNumber2)>0)
			   {
				   soldierHelp=triangles[contemporaryPlace-cubeNumber2].getVectorOfSoldier();
				   Move move1=new Move(triangles[contemporaryPlace],triangles[contemporaryPlace-cubeNumber2],cubeNumber2);
				   if(soldierHelp.size()==0)
				   {
					   if(priority2==0)
						   movesCubeNumber2.add(move1);
				   }
				   if((soldierHelp.size()>0)&&(soldierHelp.get(soldierHelp.size()-1).getColor().equals(color)))
				   {
					   if(priority2==0)
					   {
						   setPriority(2);
						   movesCubeNumber2.clear();
					   }
					   movesCubeNumber2.add(move1);
				   }
				   
				   if((soldierHelp.size()==1)&&(!soldierHelp.get(soldierHelp.size()-1).getColor().equals(color)))
					   VectorEatingCubeNumber2.add(move1);
		       }
			   
			   if(takingOutSoldiers)
			   {
				   if((contemporaryPlace-cubeNumber2)==0)
				   {
					   Move move1=new Move(triangles[contemporaryPlace],finishTriangleOfComputer,cubeNumber2);
					   takingOutMovesCubeNumber2.add(move1);
			       }
			   }
			   
			   if((contemporaryPlace-(cubeNumber1+cubeNumber2))>0)
			   {
				   soldierHelp=triangles[contemporaryPlace-(cubeNumber1+cubeNumber2)].getVectorOfSoldier();
				   Move move1=new Move(triangles[contemporaryPlace],triangles[contemporaryPlace-(cubeNumber1+cubeNumber2)],(cubeNumber1+cubeNumber2));
				   if((soldierHelp.size()==0)&&((triangles[contemporaryPlace-cubeNumber1].getVectorOfSoldier().size()==0)||(triangles[contemporaryPlace-cubeNumber2].getVectorOfSoldier().size()==0)))
				   {
					   if(priorityTotal==0)
						   movesTwoCubes.add(move1);                                // תיקנתי את הבדיקה של ההכנסה במקרה של שימוש בשתי הקוביות יחד. לעשות את זה גם במקומות האחרים שיש בהם שימוש בשתי הקוביות יחד
				   }
				   if((soldierHelp.size()>0)&&(soldierHelp.get(soldierHelp.size()-1).getColor().equals(color)))
				   {
					   if((triangles[contemporaryPlace-cubeNumber1].getVectorOfSoldier().size()>0)&&(triangles[contemporaryPlace-cubeNumber1].getVectorOfSoldier().get(triangles[contemporaryPlace-cubeNumber1].getVectorOfSoldier().size()-1).equals(color))||(((triangles[contemporaryPlace-cubeNumber2].getVectorOfSoldier().size()>0)&&(triangles[contemporaryPlace-cubeNumber2].getVectorOfSoldier().get(triangles[contemporaryPlace-cubeNumber2].getVectorOfSoldier().size()-1).equals(color)))))
					   {
						   if(priorityTotal==0)
						   {
							   setPriority(0);
							   movesTwoCubes.clear();
						   }
						   movesTwoCubes.add(move1);
					   }
				   }
		       }
			   
			   if(takingOutSoldiers)
			   {
				   if((contemporaryPlace-(cubeNumber1+cubeNumber2))==0)
				   {
					   Move move1=new Move(triangles[contemporaryPlace],finishTriangleOfComputer,(cubeNumber1+cubeNumber2));
					   takingOutMovesTwoCubes.add(move1);
			       }
			   }
		   }
	   }
   }
   
   public void printingTheVector1()
   {
	   System.out.println("the priority is:"+priority1);
	   System.out.println("The Printing is movesCubeNumber1 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	   for(int i=0;i<movesCubeNumber1.size();i++)
	   {
		   System.out.println("the start is"+movesCubeNumber1.get(i).getFromTriangle().getNumberOfTriangle());
		   System.out.println("the finish is"+movesCubeNumber1.get(i).getToTriangle().getNumberOfTriangle());
		   
	   }
   }
   
   public void printingTheVector2()
   {
	   System.out.println("The Printing is movesCubeNumber2 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	   for(int i=0;i<movesCubeNumber2.size();i++)
	   {
		   System.out.println("the start is"+movesCubeNumber2.get(i).getFromTriangle().getNumberOfTriangle());
		   System.out.println("the finish is"+movesCubeNumber2.get(i).getToTriangle().getNumberOfTriangle());
		   
	   }
   }
   
   public void printingTheVector3()
   {
	   System.out.println("the priority is:"+priorityTotal);
	   System.out.println("The Printing is movesDoubleCube !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	   for(int i=0;i<movesDoubleCube.size();i++)
	   {
		   System.out.println("the start is"+movesDoubleCube.get(i).getFromTriangle().getNumberOfTriangle());
		   System.out.println("the finish is"+movesDoubleCube.get(i).getToTriangle().getNumberOfTriangle());
		   
	   }
   }
   
   public void doingMoveOfTheComputer()   
   {
	  // if((EatenSoldierInTheMiddleOfTheBoardFree)&&(takingOutSoldiers)||(movesCubeNumber1.size()>0)||(movesCubeNumber2.size()>0)||(movesTwoCubes.size()>0)||(movesDoubleCube.size()>0)||(takingOutMovesCubeNumber1.size()>0)||(takingOutMovesCubeNumber2.size()>0)||(takingOutMovesTwoCubes.size()>0)||(takingOutMovesDoubleCube.size()>0))	   
		   if(!EatenSoldierInTheMiddleOfTheBoardFree)
		   {
			   if((cubeNumber1==cubeNumber2)&&(movesDoubleCube.size()>0))
			   {
				   movingDoubleCube();
			   }
			   else
			   {
				   if((VectorEatingCubeNumber1.size()>0)&&(VectorEatingCubeNumber2.size()==0))
				   {
					   movingAccordingTheCubes(VectorEatingCubeNumber1);
					   movesCubeNumber2.clear();
					   this.priority1=0;
					   this.priority2=0;
					   this.priorityTotal=0;
					   findingSoldierOfTheComputer();
					   if(!EatenSoldierInTheMiddleOfTheBoardFree && movesCubeNumber2.size()>0)
					   {
						   movingAccordingTheCubes(movesCubeNumber2);
					       alreadyDoneAMove=true;
					   }
					   else
					   {
						   if(cubeNumber1==cubeNumber2)
						   {
							   numberOfUsedCubeTotalLeft=0-cubeNumber1;
							   movingDoubleCube();
						   }
						   else
						   {
							   findingSoldierOfTheComputer();
							   movingAccordingTheCubes(movesCubeNumber2); 
						   }
					   }
				   }
				   else
				   {
					   if((VectorEatingCubeNumber2.size()>0)&&(VectorEatingCubeNumber1.size()==0))
					   {
						   movingAccordingTheCubes(VectorEatingCubeNumber2);
						   movesCubeNumber1.clear();
						   this.priority1=0;
						   this.priority2=0;
						   this.priorityTotal=0;
						   findingSoldierOfTheComputer();
						   if(!EatenSoldierInTheMiddleOfTheBoardFree && movesCubeNumber2.size()>0)
						   {
							   movingAccordingTheCubes(movesCubeNumber1);
						       alreadyDoneAMove=true;
						   }
					   }
					   else
					   {
						   if((movesCubeNumber1.size()>0)&&(movesCubeNumber2.size()>0))
						   {
							   System.out.println("In the if of the EatenSoldierInTheMiddleOfTheBoardFree!!!!!!!!!!@@@@@@@@@@@@@@###########");
							   movingAccordingTheCubes(movesCubeNumber1);
							   movesCubeNumber2.clear();
							   this.priority1=0;
							   this.priority2=0;
							   this.priorityTotal=0;
							   findingSoldierOfTheComputer();
							   if(!EatenSoldierInTheMiddleOfTheBoardFree && movesCubeNumber2.size()>0)
							   {
							       movingAccordingTheCubes(movesCubeNumber2);
							       alreadyDoneAMove=true;
							   }
						   }
						   else
						   {
							   if((movesCubeNumber1.size()>0)&&(movesCubeNumber2.size()==0))
							   {
								 movingAccordingTheCubes(movesCubeNumber1);
								 //EatenSoldierInTheMiddleOfTheBoardFree=true;
								 System.out.println("The boolean of the eaten soldier is::"+EatenSoldierInTheMiddleOfTheBoardFree);
								 movesCubeNumber2.clear();
								 this.priority1=0;
								 this.priority2=0;
								 this.priorityTotal=0;
							     findingSoldierOfTheComputer();
							     if(!EatenSoldierInTheMiddleOfTheBoardFree && movesCubeNumber2.size()>0)
							     {
								       movingAccordingTheCubes(movesCubeNumber2);
									   alreadyDoneAMove=true;
							     }
							   }
							   else
							   {
								   if((movesCubeNumber2.size()>0)&&(movesCubeNumber1.size()==0))
								   {
									 movingAccordingTheCubes(movesCubeNumber2);
									 //EatenSoldierInTheMiddleOfTheBoardFree=true;
								     //findingSoldierOfTheComputer();
								     //movingAccordingTheCubes(movesCubeNumber1);
									 movesCubeNumber1.clear();
									 this.priority1=0;
									 this.priority2=0;
									 this.priorityTotal=0;
								     findingSoldierOfTheComputer();
								     if(!EatenSoldierInTheMiddleOfTheBoardFree && movesCubeNumber2.size()>0)
								     {
									     movingAccordingTheCubes(movesCubeNumber1);
								         alreadyDoneAMove=true;
								     }
								   }
								   
								   else
									   JOptionPane.showMessageDialog(null, "No More Moves", "Info",
								                 JOptionPane.INFORMATION_MESSAGE);
							   }   
						   }
					   }
				   }
			   }
		   }
	   
	   else
	   if((takingOutSoldiers)||(movesCubeNumber1.size()>0)||(movesCubeNumber2.size()>0)||(movesTwoCubes.size()>0)||(movesDoubleCube.size()>0)||(takingOutMovesCubeNumber1.size()>0)||(takingOutMovesCubeNumber2.size()>0)||(takingOutMovesTwoCubes.size()>0)||(takingOutMovesDoubleCube.size()>0))
	   {
		   if(cubeNumber1==cubeNumber2)
		   {
			   System.out.println("The size of getting uot isPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP"+takingOutMovesTwoCubes.size());
			   System.out.println("the computer can start?:"+takingOutSoldiers);
			   if((!takingOutSoldiers)||((takingOutSoldiers)&&(takingOutMovesDoubleCube.size()==0)&&(movesDoubleCube.size()>0)))
			   {
				   System.out.println("GFHDFGHFDHGFDHGDFHGDFHGDFHGFHGFDHGFDHGFDHG");
				   movingDoubleCube();
			   }
			   else
			   {
				   System.out.println("GFDSGSDRTRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
				   if((takingOutSoldiers)&&(takingOutMovesDoubleCube.size()>0))
					   movingDoubleCubeInTakingOutSoldiers();
				   else
				   {
					   if((takingOutSoldiers)&&(takingOutMovesDoubleCube.size()==0))
					   {
						   updateVectorOfDoubleCubeTakingOut(0);
					   }
				   }
			   }
				   
		   }
		   else
		   {
			   if(takingOutSoldiers)
			   {
				   if(((takingOutMovesTwoCubes.size()==0)&&(takingOutMovesCubeNumber1.size()==0)&&(takingOutMovesCubeNumber2.size()==0))&&(movesCubeNumber1.size()>0)&&(movesCubeNumber2.size()>0))
				   {
					   movingAccordingToEachCube();
				   }
				   else
				   if((takingOutSoldiers)&&((takingOutMovesTwoCubes.size()==0)&&(takingOutMovesCubeNumber1.size()==0)||(takingOutMovesCubeNumber2.size()==0)))
				   {
					   System.out.println("In the if!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!111111111111111111111111");
					   if((takingOutSoldiers)&&(takingOutMovesCubeNumber1.size()==0))
					   {
						   int sizeOfTheVector;
						   
						   Soldier soldierOption;
						   
						   boolean foundSoldier=false;
						   
						   if(color.equals("white"))
						   {
							   System.out.println("In the if!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!22222222222222222222222222");
							   int i=19;
							   while((i<25)&&(!foundSoldier))
							   {
								   System.out.println("the i is:"+i);
								   sizeOfTheVector=triangles[i].getVectorOfSoldier().size();
								   
								   if(sizeOfTheVector>0)
								   {
									   if(triangles[i].getVectorOfSoldier().get(sizeOfTheVector-1).getColor().equals(color))
									   {
										   soldierOption=triangles[i].getVectorOfSoldier().get(sizeOfTheVector-1);
										   checkingPlaceForSoldierInCaseOfTakingOutAndBiggerCubes(soldierOption);
										   foundSoldier=true;
										   System.out.println("In the if!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!33333333333333333333333333");
									   }
								   }
								   i++;
							   }
							   if((takingOutMovesCubeNumber1.size()>0)&&(takingOutMovesCubeNumber2.size()>0))
							   {
								   movingAccordingTheCubes(takingOutMovesCubeNumber1);
								   situationOfBiggerCubesForSoldier=false;
								   movingAccordingTheCubes(takingOutMovesCubeNumber2);   
							   }
							   else
							   {
								   if((takingOutMovesCubeNumber2.size()==0)&&(movesCubeNumber2.size()>0)&&(takingOutMovesCubeNumber1.size()>0))
								   {
									   movingAccordingTheCubes(movesCubeNumber2);
									   situationOfBiggerCubesForSoldier=false;
									   movingAccordingTheCubes(takingOutMovesCubeNumber1);   
								   }
								   else
								   {
									   if((takingOutMovesCubeNumber1.size()==0)&&(movesCubeNumber1.size()>0)&&(takingOutMovesCubeNumber2.size()>0))
									   {
										   movingAccordingTheCubes(movesCubeNumber1);
										   situationOfBiggerCubesForSoldier=false;
										   movingAccordingTheCubes(takingOutMovesCubeNumber2);   
									   }    
								   }  
							   }
							}
						   
						   if(color.equals("black"))
						   {
							   System.out.println("In the if!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!22222222222222222222222222");
							   int i=6;
							   while((i>0)&&(!foundSoldier))
							   {
								   System.out.println("the i is:"+i);
								   sizeOfTheVector=triangles[i].getVectorOfSoldier().size();
								   
								   if(sizeOfTheVector>0)
								   {
									   if(triangles[i].getVectorOfSoldier().get(sizeOfTheVector-1).getColor().equals(color))
									   {
										   soldierOption=triangles[i].getVectorOfSoldier().get(sizeOfTheVector-1);
										   checkingPlaceForSoldierInCaseOfTakingOutAndBiggerCubes(soldierOption);
										   //i=25;
										   foundSoldier=true;
										   System.out.println("In the if!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!33333333333333333333333333");
									   }
								   }
								   i--;
							   }
							   if((takingOutMovesCubeNumber1.size()>0)&&(takingOutMovesCubeNumber2.size()>0))
							   {
								   movingAccordingTheCubes(takingOutMovesCubeNumber1);
								   movingAccordingTheCubes(takingOutMovesCubeNumber2);   
							   }
							   else
							   {
								   if((takingOutMovesCubeNumber2.size()==0)&&(movesCubeNumber2.size()>0)&&(takingOutMovesCubeNumber1.size()>0))
								   {
									   movingAccordingTheCubes(movesCubeNumber2);
									   movingAccordingTheCubes(takingOutMovesCubeNumber1);   
								   }
								   else
								   {
									   if((takingOutMovesCubeNumber1.size()==0)&&(movesCubeNumber1.size()>0)&&(takingOutMovesCubeNumber2.size()>0))
									   {
										   movingAccordingTheCubes(movesCubeNumber1);
										   movingAccordingTheCubes(takingOutMovesCubeNumber2);   
									   }    
								   }  
							   }
						   }
					   }
					   
					   else
					   {
						   if((takingOutSoldiers)&&(takingOutMovesCubeNumber2.size()==0))
						   {
							   System.out.println("In the if!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!55555555555555555555555");
							   int sizeOfTheVector;
							   
							   Soldier soldierOption;
							   
							   boolean foundSoldier=false;
							   
							   if(color.equals("white"))
							   {
								   int i=19;
								   while((i<25)&&(!foundSoldier))
								   {
									   sizeOfTheVector=triangles[i].getVectorOfSoldier().size();
									   System.out.println("In the if!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!66666666666666666666");
									   if(sizeOfTheVector>0)
									   {
										   System.out.println("In the if!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!77777777777777777777");
										   if(triangles[i].getVectorOfSoldier().get(sizeOfTheVector-1).getColor().equals(color))
										   {
											   soldierOption=triangles[i].getVectorOfSoldier().get(sizeOfTheVector-1);
											   checkingPlaceForSoldierInCaseOfTakingOutAndBiggerCubes(soldierOption);
											   //i=25;
											   foundSoldier=true;
											   System.out.println("In the if!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!8888888888888888888888");
										   }
									   } 
									   i++;
								   }
								   if((takingOutMovesCubeNumber1.size()>0)&&(takingOutMovesCubeNumber2.size()>0))
								   {
									   movingAccordingTheCubes(takingOutMovesCubeNumber1);
									   situationOfBiggerCubesForSoldier=false;
									   movingAccordingTheCubes(takingOutMovesCubeNumber2);   
								   }
								   else
								   {
									   if((takingOutMovesCubeNumber2.size()==0)&&(movesCubeNumber2.size()>0)&&(takingOutMovesCubeNumber1.size()>0))
									   {
										   movingAccordingTheCubes(movesCubeNumber2);
										   situationOfBiggerCubesForSoldier=false;
										   movingAccordingTheCubes(takingOutMovesCubeNumber1);   
									   }
									   else
									   {
										   if((takingOutMovesCubeNumber1.size()==0)&&(movesCubeNumber1.size()>0)&&(takingOutMovesCubeNumber2.size()>0))
										   {
											   movingAccordingTheCubes(movesCubeNumber1);
											   situationOfBiggerCubesForSoldier=false;
											   movingAccordingTheCubes(takingOutMovesCubeNumber2);   
										   }    
									   }  
								   }
								   //
							   }
							   
							   if(color.equals("black"))
							   {
								   int i=6;
								   while((i>0)&&(!foundSoldier))
								   {
									   sizeOfTheVector=triangles[i].getVectorOfSoldier().size();
									   System.out.println("In the if!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!66666666666666666666");
									   if(sizeOfTheVector>0)
									   {
										   System.out.println("In the if!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!77777777777777777777");
										   if(triangles[i].getVectorOfSoldier().get(sizeOfTheVector-1).getColor().equals(color))
										   {
											   soldierOption=triangles[i].getVectorOfSoldier().get(sizeOfTheVector-1);
											   checkingPlaceForSoldierInCaseOfTakingOutAndBiggerCubes(soldierOption);
											   //i=25;
											   foundSoldier=true;
											   System.out.println("In the if!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!8888888888888888888888");
										   }
									   } 
									   i--;
								   }
								   situationOfBiggerCubesForSoldier=true;
								   System.out.println("In the if!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!999999999999999999999");
								   
								   if((takingOutMovesCubeNumber1.size()>0)&&(takingOutMovesCubeNumber2.size()>0))
								   {
									   movingAccordingTheCubes(takingOutMovesCubeNumber1);
									   situationOfBiggerCubesForSoldier=false;
									   movingAccordingTheCubes(takingOutMovesCubeNumber2);   
								   }
								   else
								   {
									   if((takingOutMovesCubeNumber2.size()==0)&&(movesCubeNumber2.size()>0)&&(takingOutMovesCubeNumber1.size()>0))
									   {
										   movingAccordingTheCubes(movesCubeNumber2);
										   situationOfBiggerCubesForSoldier=false;
										   movingAccordingTheCubes(takingOutMovesCubeNumber1);   
									   }
									   else
									   {
										   if((takingOutMovesCubeNumber1.size()==0)&&(movesCubeNumber1.size()>0)&&(takingOutMovesCubeNumber2.size()>0))
										   {
											   movingAccordingTheCubes(movesCubeNumber1);
											   situationOfBiggerCubesForSoldier=false;
											   movingAccordingTheCubes(takingOutMovesCubeNumber2);   
										   }    
									   }  
								   }
								   
							   }
						   }   
					   }
				   }
				   else
				   {
					   if((takingOutMovesCubeNumber1.size()>0)&&(takingOutMovesCubeNumber2.size()>0)&&(takingOutMovesTwoCubes.size()>0))
					   {
						   int placeRandomBetweenOptionOfVector=(int)((2)*Math.random()+1);
						   
						   if(placeRandomBetweenOptionOfVector==2)
						   {
							   movingAccordingTheCubes(takingOutMovesTwoCubes);
						   }
						   if((placeRandomBetweenOptionOfVector==1)||(takingOutMovesTwoCubes.size()==0))
							   movingAccordingToEachCube();
					   }
					   else
					   {
						   if((takingOutMovesCubeNumber1.size()>0)&&(takingOutMovesCubeNumber2.size()>0)&&(takingOutMovesTwoCubes.size()==0))
							   movingAccordingToEachCube();
						   else
						   {
							   if(((takingOutMovesCubeNumber1.size()==0)||(takingOutMovesCubeNumber2.size()==0))&&(takingOutMovesTwoCubes.size()>0))
								   movingAccordingTheCubes(takingOutMovesTwoCubes);
							   else
							   {
								   if(((takingOutMovesCubeNumber1.size()>0)&&(takingOutMovesCubeNumber2.size()==0))&&(takingOutMovesTwoCubes.size()==0))
								   {
									   movingAccordingTheCubes(takingOutMovesCubeNumber1);
									   vectorCubeLeftInActionOfTakingOut=2;
									   findingSoldierOfTheComputer();
								   }
								   else
								   {
									   if(((takingOutMovesCubeNumber1.size()==0)&&(takingOutMovesCubeNumber2.size()>0))&&(takingOutMovesTwoCubes.size()==0))
									   {
										   movingAccordingTheCubes(takingOutMovesCubeNumber2);
										   vectorCubeLeftInActionOfTakingOut=1;
										   findingSoldierOfTheComputer();
									   }
								   }
							   }
						   }
					   }
				   }
			   }
		   }
	   //}
			   if((finishTriangleOfComputer.getVectorOfSoldier().size()!=15)&&(!alreadyDoneAMove))
			   {
				   if((!takingOutSoldiers)||((takingOutSoldiers)&&(vectorCubeLeftInActionOfTakingOut!=0)))
				   {
					   if((takingOutSoldiers)&&(vectorCubeLeftInActionOfTakingOut!=0))
					   {
						   if((vectorCubeLeftInActionOfTakingOut==1)&&(movesCubeNumber1.size()>0))
							   movingAccordingTheCubes(movesCubeNumber1);
						   if((vectorCubeLeftInActionOfTakingOut==2)&&(movesCubeNumber2.size()>0))
							   movingAccordingTheCubes(movesCubeNumber2);
					   }
					   else
					   {
						   if(!movingInCaseOfEatingTheOpponentSoldier())
						   {
							   if((movesCubeNumber1.size()>0)&&(movesCubeNumber2.size()>0)&&(movesTwoCubes.size()>0))
							   {
								   int placeRandomBetweenOptionOfVector=(int)((2)*Math.random()+1);
								   
								   if(placeRandomBetweenOptionOfVector==2)
								   {
									   movingAccordingTheCubes(movesTwoCubes);
								   }
								   if(placeRandomBetweenOptionOfVector==1)//||(movesTwoCubes.size()==0))
								   { //movingAccordingToEachCube();///////////////////////////////
									 movingAccordingTheCubes(movesCubeNumber1);
									 this.priority1=0;
									 this.priority2=0;
									 this.priorityTotal=0;
								     findingSoldierOfTheComputer();
								     movingAccordingTheCubes(movesCubeNumber2);
								   }
							   }
							   else
							   {
								   if((movesTwoCubes.size()==0)&&((movesCubeNumber1.size()>0)&&(movesCubeNumber2.size()>0)))
								   {
									   //movingAccordingToEachCube();///////////////
									   this.priority1=0;
									   this.priority2=0;
									   this.priorityTotal=0;
									   findingSoldierOfTheComputer();
									   movingAccordingTheCubes(movesCubeNumber2);
								   }
								   else
								   {
									   if((movesTwoCubes.size()>0)&&((movesCubeNumber1.size()==0)||(movesCubeNumber2.size()==0)))
										   movingAccordingTheCubes(movesTwoCubes);
									   /*
									   else
									   {
										   if(!EatenSoldierInTheMiddleOfTheBoardFree)
										   {
											   if((movesCubeNumber1.size()>0)&&(movesCubeNumber2.size()==0))
											   {
												 movingAccordingTheCubes(movesCubeNumber1);
												 EatenSoldierInTheMiddleOfTheBoardFree=true;
												 movesCubeNumber2.clear();
											     findingSoldierOfTheComputer();
											     movingAccordingTheCubes(movesCubeNumber2);
											   }
											   else
											   {
												   if((movesCubeNumber2.size()>0)&&(movesCubeNumber1.size()==0))
												   {
													 movingAccordingTheCubes(movesCubeNumber2);
													 EatenSoldierInTheMiddleOfTheBoardFree=true;
												     findingSoldierOfTheComputer();
												     movingAccordingTheCubes(movesCubeNumber1);
												   }
												   
												   else
													   JOptionPane.showMessageDialog(null, "No More Moves", "Info",
												                 JOptionPane.INFORMATION_MESSAGE);
											   }
										   }
										   }
										   */
										   //else
											  //JOptionPane.showMessageDialog(null, "No More Moves", "Info",
								                 //JOptionPane.INFORMATION_MESSAGE);
									   //}
								   }
							   }
						   }
					   }
				   }
			   }
		   }
	 //  }
	   
	   situationOfBiggerCubesForSoldier=false;
	   movesCubeNumber1.clear();
	   movesCubeNumber2.clear();
	   movesTwoCubes.clear();
	   movesDoubleCube.clear();
	   VectorEatingCubeNumber1.clear();
	   VectorEatingCubeNumber2.clear();
	   takingOutMovesCubeNumber1.clear();
	   takingOutMovesCubeNumber2.clear();
	   takingOutMovesTwoCubes.clear();
	   takingOutMovesDoubleCube.clear();
   }
   
   public boolean checkingCubeNumbersCreatingBase(Triangle triangle1,int numberOfCube, int numberOfCase)
   {
	   Vector<Soldier>vectorHelp=new Vector<Soldier>();
	   if(color.equals("white"))
	   {
		   if(triangle1.getNumberOfTriangle()-numberOfCube>0)
		   {
			   vectorHelp=triangles[triangle1.getNumberOfTriangle()-numberOfCube].getVectorOfSoldier();			   
			   if(vectorHelp.size()>0)
			   {
				   if(numberOfCase==0)
				   {
					   if(vectorHelp.get(vectorHelp.size()-1).getColor().equals(color))
						   return true;
				   }
				   
				   if(numberOfCase==1)
				   {
					   if((vectorHelp.get(vectorHelp.size()-1).getColor().equals(color))&&(vectorHelp.size()>2))
						   return true;
				   }
			   }
		   }
	   }
	   
	   if(color.equals("black"))
	   {
		   if(triangle1.getNumberOfTriangle()+numberOfCube<25)
		   {
			   vectorHelp=triangles[triangle1.getNumberOfTriangle()+numberOfCube].getVectorOfSoldier();
			   if(vectorHelp.size()>0)
			   {
				   if(numberOfCase==0)
				   {
					   if(vectorHelp.get(vectorHelp.size()-1).getColor().equals(color))
						   return true;
				   }
				   
				   if(numberOfCase==1)
				   {
					   if((vectorHelp.get(vectorHelp.size()-1).getColor().equals(color))&&(vectorHelp.size()>2))
						   return true;
				   }
			   }
		   }
	   }
	   		
	   return false;
   }
   
   public void movingAccordingToEachCube()
   {
	   if(EatenSoldierInTheMiddleOfTheBoardFree)
	   {
		   if((!takingOutSoldiers)||((takingOutSoldiers)&&((takingOutMovesCubeNumber1.size()==0)||(takingOutMovesCubeNumber2.size()==0))))
		   {
			   int sizeofTheVectorMoveCubeNumber1=movesCubeNumber1.size();
		   	   
			   //System.out.println("the size of the vector move is:"+sizeofTheVectorMoveCubeNumber1);
			   
			   int placeRandom=(int)((sizeofTheVectorMoveCubeNumber1)*Math.random());
			   
			   Move chooseMove;	
			   
			   if(sizeofTheVectorMoveCubeNumber1==1)
				   placeRandom=0;
			   
			   //System.out.println("the place in the move vector is:"+placeRandom);
			   
			   chooseMove=movesCubeNumber1.get(placeRandom);
			   	   
			   Soldier chooseSoldier;
			   
			   int sizeOfTheVector;
			   	   
			   sizeOfTheVector=triangles[chooseMove.getFromTriangle().getNumberOfTriangle()].getVectorOfSoldier().size();
			   
			   chooseSoldier=triangles[chooseMove.getFromTriangle().getNumberOfTriangle()].getVectorOfSoldier().get(sizeOfTheVector-1);
			   
			   triangles[chooseMove.getFromTriangle().getNumberOfTriangle()].removeSoldier();
			   
			   chooseSoldier.setContemporaryTriangle(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getNumberOfTriangle());
			   
			   chooseSoldier.setLocation(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getLocationX(),triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getLocationY());
			   
			   //triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getVectorOfSoldier().add(chooseSoldier);
			   
			   triangles[chooseMove.getToTriangle().getNumberOfTriangle()].addSoldier(chooseSoldier);
			   	   
			   			   
			   if(checkingCubeNumbersCreatingBase(triangles[chooseMove.getToTriangle().getNumberOfTriangle()],cubeNumber2,0))
			   {
				   Triangle triangle;
				   if(color.equals("white"))
				       triangle=triangles[chooseMove.getToTriangle().getNumberOfTriangle()-cubeNumber2];
				   else
				       triangle=triangles[chooseMove.getToTriangle().getNumberOfTriangle()+cubeNumber2];
				   
				   chooseSoldier=triangle.getVectorOfSoldier().get(triangle.getVectorOfSoldier().size()-1);
				   
				   triangle.removeSoldier();
				   
				   chooseSoldier.setContemporaryTriangle(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getNumberOfTriangle());
				   
				   chooseSoldier.setLocation(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getLocationX(),triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getLocationY());
				   
				   triangles[chooseMove.getToTriangle().getNumberOfTriangle()].addSoldier(chooseSoldier);
			   }
			   else
			   {
				   int sizeofTheVectorMoveCubeNumber2=movesCubeNumber2.size();
			   	   
				   System.out.println("the size of the vector move is22222222222222222:"+sizeofTheVectorMoveCubeNumber2);
				   
				   placeRandom=(int)((sizeofTheVectorMoveCubeNumber2)*Math.random());
				   
				   Move chooseMove2;	
				   
				   if(sizeofTheVectorMoveCubeNumber2==1)
					   placeRandom=0;
				   
				   System.out.println("the place in the move vector is22222222222222222222222222:"+placeRandom);
				   
				   chooseMove2=movesCubeNumber2.get(placeRandom); 
				   	   
				   Soldier chooseSoldier2;
				   
				   int sizeOfTheVector2;
				   	   
				   sizeOfTheVector2=triangles[chooseMove2.getFromTriangle().getNumberOfTriangle()].getVectorOfSoldier().size();
				   
				   				   
				   System.out.println("the size after -1 is:22222222222222222222222222222222222222222:"+(sizeOfTheVector2-1));
				   
				   System.out.println("From::"+chooseMove2.getFromTriangle().getNumberOfTriangle());
				   
				   System.out.println("To::"+chooseMove2.getToTriangle().getNumberOfTriangle());
				   
				   chooseSoldier2=triangles[chooseMove2.getFromTriangle().getNumberOfTriangle()].getVectorOfSoldier().get(sizeOfTheVector2-1);
				   
				   triangles[chooseMove2.getFromTriangle().getNumberOfTriangle()].removeSoldier();
				   
				   chooseSoldier.setContemporaryTriangle(triangles[chooseMove2.getToTriangle().getNumberOfTriangle()].getNumberOfTriangle());
				   
				   chooseSoldier2.setLocation(triangles[chooseMove2.getToTriangle().getNumberOfTriangle()].getLocationX(),triangles[chooseMove2.getToTriangle().getNumberOfTriangle()].getLocationY());	
				   
				   //triangles[chooseMove2.getToTriangle().getNumberOfTriangle()].getVectorOfSoldier().add(chooseSoldier);
				   
				   triangles[chooseMove2.getToTriangle().getNumberOfTriangle()].addSoldier(chooseSoldier);
			   }
		   }
		   else
		   {
               int sizeofTheVectorMoveCubeNumber1=takingOutMovesCubeNumber1.size();
		   	   
			   System.out.println("the size of the vector move is:"+sizeofTheVectorMoveCubeNumber1);
			   
			   int placeRandom=(int)((sizeofTheVectorMoveCubeNumber1)*Math.random());
			   
			   Move chooseMove;	
			   
			   if(sizeofTheVectorMoveCubeNumber1==1)
				   placeRandom=0;
			   
			   //System.out.println("the place in the move vector is:"+placeRandom);
			   
			   chooseMove=takingOutMovesCubeNumber1.get(placeRandom);
			   	   
			   Soldier chooseSoldier;
			   
			   int sizeOfTheVector;
			   	   
			   sizeOfTheVector=triangles[chooseMove.getFromTriangle().getNumberOfTriangle()].getVectorOfSoldier().size();
			   
			   chooseSoldier=triangles[chooseMove.getFromTriangle().getNumberOfTriangle()].getVectorOfSoldier().get(sizeOfTheVector-1);
			   
			   triangles[chooseMove.getFromTriangle().getNumberOfTriangle()].removeSoldier();
			   
			   //chooseSoldier.setContemporaryTriangle(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getNumberOfTriangle());
			   
			   chooseSoldier.setLocation(finishTriangleOfComputer.getLocationX(),finishTriangleOfComputer.getLocationY());
			   
			   //triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getVectorOfSoldier().add(chooseSoldier);
			   
			   finishTriangleOfComputer.addSoldier(chooseSoldier);
			   
			   
			   
			   int sizeofTheVectorMoveCubeNumber2=takingOutMovesCubeNumber2.size();
		   	   
			   //System.out.println("the size of the vector move is:"+sizeofTheVectorMoveCubeNumber2);
			   
			   placeRandom=(int)((sizeofTheVectorMoveCubeNumber2)*Math.random());
			   
			   Move chooseMove2;	
			   
			   if(sizeofTheVectorMoveCubeNumber2==1)
				   placeRandom=0;
			   
			   //System.out.println("the place in the move vector is:"+placeRandom);
			   
			   chooseMove2=takingOutMovesCubeNumber2.get(placeRandom); // עושה בעיות לבדוק מה קורה אם הווקטור הוא אפס וצריך להיות שימוש בקובייה השנייה
			   	   
			   Soldier chooseSoldier2;
			   
			   int sizeOfTheVector2;
			   	   
			   sizeOfTheVector2=triangles[chooseMove2.getFromTriangle().getNumberOfTriangle()].getVectorOfSoldier().size();
			   
			   chooseSoldier2=triangles[chooseMove2.getFromTriangle().getNumberOfTriangle()].getVectorOfSoldier().get(sizeOfTheVector2-1);
			   
			   triangles[chooseMove2.getFromTriangle().getNumberOfTriangle()].removeSoldier();
			   
			   //chooseSoldier.setContemporaryTriangle(triangles[chooseMove2.getToTriangle().getNumberOfTriangle()].getNumberOfTriangle());
			   
			   chooseSoldier2.setLocation(finishTriangleOfComputer.getLocationX(),finishTriangleOfComputer.getLocationY());	
			   
			   //triangles[chooseMove2.getToTriangle().getNumberOfTriangle()].getVectorOfSoldier().add(chooseSoldier);
			   
			   finishTriangleOfComputer.addSoldier(chooseSoldier);
		   }
	   }
	   else
	   {
		   int sizeofTheVectorMoveCubeNumber1=movesCubeNumber1.size();
	   	   
		   //System.out.println("the size of the vector move is:"+sizeofTheVectorMoveCubeNumber1);
		   
		   int placeRandom=(int)((sizeofTheVectorMoveCubeNumber1)*Math.random());
		   
		   Move chooseMove;	
		   
		   if(sizeofTheVectorMoveCubeNumber1==1)
			   placeRandom=0;
		   
		   //System.out.println("the place in the move vector is:"+placeRandom);
		   
		   chooseMove=movesCubeNumber1.get(placeRandom);
		   	   
		   Soldier chooseSoldier;
		   
		   int sizeOfTheVector;
		   	   
		   sizeOfTheVector=triangles[0].getVectorOfSoldier().size();
		   
		   chooseSoldier=triangles[0].getVectorOfSoldier().get(sizeOfTheVector-1);
		   
		   triangles[0].removeSoldier();
		   
		   chooseSoldier.setContemporaryTriangle(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getNumberOfTriangle());
		   
		   chooseSoldier.setLocation(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getLocationX(),triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getLocationY());
		   
		   //triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getVectorOfSoldier().add(chooseSoldier);
		   
		   triangles[chooseMove.getToTriangle().getNumberOfTriangle()].addSoldier(chooseSoldier);
		   	   
		   EatenSoldierInTheMiddleOfTheBoardFree=true;
		   
		   if(checkingCubeNumbersCreatingBase(triangles[chooseMove.getToTriangle().getNumberOfTriangle()],cubeNumber2,0))
		   {
			   Triangle triangle;
			   if(color.equals("white"))
			       triangle=triangles[chooseMove.getToTriangle().getNumberOfTriangle()-cubeNumber2];
			   else
			       triangle=triangles[chooseMove.getToTriangle().getNumberOfTriangle()+cubeNumber2];
			   
			   chooseSoldier=triangle.getVectorOfSoldier().get(triangle.getVectorOfSoldier().size()-1);
			   
			   triangle.removeSoldier();
			   
			   chooseSoldier.setContemporaryTriangle(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getNumberOfTriangle());
			   
			   chooseSoldier.setLocation(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getLocationX(),triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getLocationY());
			   
			   triangles[chooseMove.getToTriangle().getNumberOfTriangle()].addSoldier(chooseSoldier);
		   }
		   else
		   {
			   findingSoldierOfTheComputer();
			   
			   int sizeofTheVectorMoveCubeNumber2=movesCubeNumber2.size();
		   	   
			   //System.out.println("the size of the vector move is:"+sizeofTheVectorMoveCubeNumber2);
			   
			   placeRandom=(int)((sizeofTheVectorMoveCubeNumber2)*Math.random());
			   
			   Move chooseMove2;	
			   
			   if(sizeofTheVectorMoveCubeNumber2==1)
				   placeRandom=0;
			   
			   //System.out.println("the place in the move vector is:"+placeRandom);
			   
			   chooseMove2=movesCubeNumber2.get(placeRandom);  
			   	   
			   Soldier chooseSoldier2;
			   
			   int sizeOfTheVector2;
			   	   
			   sizeOfTheVector2=triangles[chooseMove2.getFromTriangle().getNumberOfTriangle()].getVectorOfSoldier().size();
			   
			   chooseSoldier2=triangles[chooseMove2.getFromTriangle().getNumberOfTriangle()].getVectorOfSoldier().get(sizeOfTheVector2-1);
			   
			   triangles[chooseMove2.getFromTriangle().getNumberOfTriangle()].removeSoldier();
			   
			   chooseSoldier.setContemporaryTriangle(triangles[chooseMove2.getToTriangle().getNumberOfTriangle()].getNumberOfTriangle());
			   
			   chooseSoldier2.setLocation(triangles[chooseMove2.getToTriangle().getNumberOfTriangle()].getLocationX(),triangles[chooseMove2.getToTriangle().getNumberOfTriangle()].getLocationY());	
			   
			   //triangles[chooseMove2.getToTriangle().getNumberOfTriangle()].getVectorOfSoldier().add(chooseSoldier);
			   
			   triangles[chooseMove2.getToTriangle().getNumberOfTriangle()].addSoldier(chooseSoldier);
		   }
	   }
   }
   
   public int movingAccordingTheCubes(Vector<Move> moveVectorHelp)
   {
	   if(EatenSoldierInTheMiddleOfTheBoardFree)
	   {
		   if((!takingOutSoldiers)||((takingOutSoldiers)&&(situationOfBiggerCubesForSoldier)||(moveVectorHelp.size()==0)||(moveVectorHelp==movesCubeNumber1)||(moveVectorHelp==movesCubeNumber2)))//(takingOutMovesDoubleCube.size()==0)))
		   {
			   if(situationOfBiggerCubesForSoldier)
			   {
				   System.out.println("doing the exiting soldier in case of not cube according the place");
				   int returnFromExitingSoldierOperation=exitingSoldiers(moveVectorHelp);
				   return returnFromExitingSoldierOperation;
			   }
			   else
			   {
				   int sizeofTheVectorMove=moveVectorHelp.size();
		      	   
				   System.out.println("the size of the vector move is!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:"+sizeofTheVectorMove);
				   
				   int placeRandom=(int)((sizeofTheVectorMove)*Math.random());
				   
				   Move chooseMove;	
				   
				   if(sizeofTheVectorMove==1)
					   placeRandom=0;
				   
				   System.out.println("the place in the move vector is:"+placeRandom);
				   
				   chooseMove=moveVectorHelp.get(placeRandom);
				   
				   System.out.println("From:"+chooseMove.getFromTriangle().getNumberOfTriangle());
				   
				   System.out.println("To:"+chooseMove.getToTriangle().getNumberOfTriangle());
				   	   
				   Soldier chooseSoldier;
				   
				   int sizeOfTheVector;
				   
				   System.out.println("SDFGSDFGDFSGSDFGSDFGSDFGSDFGSDFG!!!!!!!!!"+chooseMove.getFromTriangle().getNumberOfTriangle());
				   	   
				   sizeOfTheVector=triangles[chooseMove.getFromTriangle().getNumberOfTriangle()].getVectorOfSoldier().size();
				   
				   System.out.println("the size of the vector triangle is is:"+sizeOfTheVector);
				   
				   System.out.println("the number vector triangle is is:"+chooseMove.getFromTriangle().getNumberOfTriangle());
				   	   
				   chooseSoldier=triangles[chooseMove.getFromTriangle().getNumberOfTriangle()].getVectorOfSoldier().get(sizeOfTheVector-1); 
				   
				   triangles[chooseMove.getFromTriangle().getNumberOfTriangle()].removeSoldier();
				   
				   chooseSoldier.setContemporaryTriangle(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getNumberOfTriangle());
				   
				   chooseSoldier.setLocation(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getLocationX(),triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getLocationY());
				   	   
				   triangles[chooseMove.getToTriangle().getNumberOfTriangle()].addSoldier(chooseSoldier);
				   
				   return placeRandom;
			   }
		   }
		   
		   else
		   {
			   int returnFromExitingSoldierOperation=exitingSoldiers(moveVectorHelp);
			   return returnFromExitingSoldierOperation;
		   }
	   }
	   else
	   {
		   int sizeofTheVectorMove=moveVectorHelp.size();
      	   
		   System.out.println("the size of the vector move is:"+sizeofTheVectorMove);
		   
		   int placeRandom=(int)((sizeofTheVectorMove)*Math.random());
		   
		   Move chooseMove;	
		   
		   if(sizeofTheVectorMove==1)
			   placeRandom=0;
		   
		   System.out.println("the place in the move vector is:"+placeRandom);
		   
		   chooseMove=moveVectorHelp.get(placeRandom);
		   
		   System.out.println("From:"+chooseMove.getFromTriangle().getNumberOfTriangle());
		   
		   System.out.println("To:"+chooseMove.getToTriangle().getNumberOfTriangle());
		   
		   int numberOfCubeForEating=checkingForEatingSoldierInUsingTheTwoCubes(chooseMove.getFromTriangle().getNumberOfTriangle());
		   
		   System.out.println("the numberOfCubeForEating is:"+numberOfCubeForEating);
		   
		   if(numberOfCubeForEating==0)
		   {
			   Soldier chooseSoldier;
			   
			   int sizeOfTheVector;
			   
			   System.out.println("SDFGSDFGDFSGSDFGSDFGSDFGSDFGSDFG!!!!!!!!!"+chooseMove.getFromTriangle().getNumberOfTriangle());
			   	   
			   sizeOfTheVector=triangles[0].getVectorOfSoldier().size();
			   
			   System.out.println("the size of the vector triangle is is:"+sizeOfTheVector);
			   
			   System.out.println("the number vector triangle is is:"+chooseMove.getFromTriangle().getNumberOfTriangle());
			   	   
			   chooseSoldier=triangles[0].getVectorOfSoldier().get(sizeOfTheVector-1);
			   
			   //triangles[0].removeSoldier();
			   
			    int placeOfSoldierInVector=triangles[0].getPlaceInVectorOfSoldier(chooseSoldier);
		        System.out.println("the number is:");
		        System.out.println(placeOfSoldierInVector);
		        Vector <Soldier> soldiersHelp2=new Vector<Soldier>();
		        soldiersHelp2=triangles[0].getVectorOfSoldier();
		        soldiersHelp2.remove(placeOfSoldierInVector);
		        triangles[0].setVectorOfSoldier(soldiersHelp2);       
			   
			   chooseSoldier.setContemporaryTriangle(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getNumberOfTriangle());
			   
			   chooseSoldier.setLocation(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getLocationX(),triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getLocationY());
			   	   
			   triangles[chooseMove.getToTriangle().getNumberOfTriangle()].addSoldier(chooseSoldier);
			   
			   int countOfSoldiers=0;
			   
			   for(int i=0;i<triangles[0].getVectorOfSoldier().size();i++)
			   {
				   if(triangles[0].getVectorOfSoldier().get(i).getColor().equals(color))
					   countOfSoldiers++;
			   }
			   
			   System.out.println("the number of soldier of computer in the middle of the board is:"+countOfSoldiers);
			   
			   if(countOfSoldiers==0)
				   EatenSoldierInTheMiddleOfTheBoardFree=true;
			   
			   System.out.println("the number that is returned is:"+placeRandom);
			   
			   return placeRandom;
		   }
		   else
		   {
			   placeRandom=movingInCaseEatingPlayerSoldierByEatenSoldierOfTheComputer(moveVectorHelp,placeRandom,numberOfCubeForEating);
			   return placeRandom;
		   }
	   }
   }
   
   public boolean movingInCaseOfEatingTheOpponentSoldier()
   {
	   if((VectorEatingCubeNumber1.size()==0)&&(VectorEatingCubeNumber2.size()==0))
		   return false;
	   else
	   {
		   if(VectorEatingCubeNumber1.size()>0)
		   {
			   int sizeofTheVectorMoveCubeNumber1=VectorEatingCubeNumber1.size();
			   
			   int placeRandom=(int)((sizeofTheVectorMoveCubeNumber1)*Math.random());
			   
			   Move chooseMove;	
			   
			   if(sizeofTheVectorMoveCubeNumber1==1)
				   placeRandom=0;
			   
			   chooseMove=VectorEatingCubeNumber1.get(placeRandom);
			   
			   if(checkingCubeNumbersCreatingBase(chooseMove.getToTriangle(),cubeNumber2,1))
			   {
				   // התנועה של שחקן לפי קובייה 1 אכילה
				   int sizeOfTheVector=triangles[chooseMove.getFromTriangle().getNumberOfTriangle()].getVectorOfSoldier().size();
				   
				   Soldier chooseSoldier=triangles[chooseMove.getFromTriangle().getNumberOfTriangle()].getVectorOfSoldier().get(sizeOfTheVector-1);
				   
				   triangles[chooseMove.getFromTriangle().getNumberOfTriangle()].removeSoldier();
				   
				   triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getVectorOfSoldier().get(0).setContemporaryTriangle(25);
				   
				   triangles[0].addSoldier(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getVectorOfSoldier().get(0));
				   
				   triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getVectorOfSoldier().get(0).setLocation(triangles[0].getLocationX(),triangles[0].getLocationY());
				   
				   triangles[chooseMove.getToTriangle().getNumberOfTriangle()].removeSoldier();
				   
				   chooseSoldier.setContemporaryTriangle(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getNumberOfTriangle());
				   
				   chooseSoldier.setLocation(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getLocationX(),triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getLocationY());
				   				   
				   triangles[chooseMove.getToTriangle().getNumberOfTriangle()].addSoldier(chooseSoldier);
				   
				   // התנועה של שחקן לפי קובייה 2 בניית בית
				   
				   Triangle triangle;
				   
				   if(color.equals("white"))
				       triangle=triangles[chooseMove.getToTriangle().getNumberOfTriangle()-cubeNumber2];
				   else
					   triangle=triangles[chooseMove.getToTriangle().getNumberOfTriangle()+cubeNumber2];
				   
				   System.out.println("the number of the triangle before is:"+chooseMove.getToTriangle().getNumberOfTriangle());

				   System.out.println("the number of the triangle is:"+triangle.getNumberOfTriangle());
				   
				   chooseSoldier=triangle.getVectorOfSoldier().get(triangle.getVectorOfSoldier().size()-1);
				   
				   triangle.removeSoldier();
				   
				   chooseSoldier.setContemporaryTriangle(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getNumberOfTriangle());
				   
				   chooseSoldier.setLocation(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getLocationX(),triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getLocationY());
				   
				   triangles[chooseMove.getToTriangle().getNumberOfTriangle()].addSoldier(chooseSoldier);
				   
				   return true;
			   }
		   }
		   
		   if(VectorEatingCubeNumber2.size()>0)
		   {
               int sizeofTheVectorMoveCubeNumber1=VectorEatingCubeNumber2.size();
			   
			   int placeRandom=(int)((sizeofTheVectorMoveCubeNumber1)*Math.random());
			   
			   Move chooseMove;	
			   
			   if(sizeofTheVectorMoveCubeNumber1==1)
				   placeRandom=0;
			   
			   chooseMove=VectorEatingCubeNumber2.get(placeRandom);
			   
			   if(checkingCubeNumbersCreatingBase(chooseMove.getToTriangle(),cubeNumber1,1))
			   {
				   
				// התנועה של שחקן לפי קובייה 2 אכילה
				   int sizeOfTheVector=triangles[chooseMove.getFromTriangle().getNumberOfTriangle()].getVectorOfSoldier().size();
				   
				   Soldier chooseSoldier=triangles[chooseMove.getFromTriangle().getNumberOfTriangle()].getVectorOfSoldier().get(sizeOfTheVector-1);
				   
				   triangles[chooseMove.getFromTriangle().getNumberOfTriangle()].removeSoldier();
				   
                   triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getVectorOfSoldier().get(0).setContemporaryTriangle(25);
                   
                   triangles[0].addSoldier(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getVectorOfSoldier().get(0));
				   
				   triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getVectorOfSoldier().get(0).setLocation(triangles[0].getLocationX(),triangles[0].getLocationY());
				   
				   triangles[chooseMove.getToTriangle().getNumberOfTriangle()].removeSoldier();
				   
				   chooseSoldier.setContemporaryTriangle(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getNumberOfTriangle());
				   
				   chooseSoldier.setLocation(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getLocationX(),triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getLocationY());
				   
				   triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getVectorOfSoldier().add(chooseSoldier);
				   
				   triangles[chooseMove.getToTriangle().getNumberOfTriangle()].addSoldier(chooseSoldier);
				   
				   // התנועה של שחקן לפי קובייה 1 בניית בית
				   
				   Triangle triangle=triangles[chooseMove.getToTriangle().getNumberOfTriangle()-cubeNumber1];
				   
				   chooseSoldier=triangle.getVectorOfSoldier().get(triangle.getVectorOfSoldier().size()-1); 
				   
				   triangle.removeSoldier();
				   
				   chooseSoldier.setContemporaryTriangle(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getNumberOfTriangle());
				   
				   chooseSoldier.setLocation(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getLocationX(),triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getLocationY());
				   
				   triangles[chooseMove.getToTriangle().getNumberOfTriangle()].addSoldier(chooseSoldier);
				     
				   return true;
			   }
		   }
	   }
	   return false;   
   }
   
   public void checkingPlaceAccordingDoubleCube(Soldier soldier1)
   {	   
       int contemporaryPlace=soldier1.getContemporaryTriangle();
       	   
	   Vector<Soldier>soldierHelp1=new Vector<Soldier>();
	   Vector<Soldier>soldierHelp2=new Vector<Soldier>();
	   Vector<Soldier>soldierHelp3=new Vector<Soldier>();
	   Vector<Soldier>soldierHelp4=new Vector<Soldier>();
	   
	   System.out.println("1!pppppppppppppppppppppPPPPP"+takingOutSoldiers);
	   
	   if(color.equals("white"))
	   {
		       if((contemporaryPlace+cubeNumber1)<25)
			      soldierHelp1=triangles[contemporaryPlace+cubeNumber1].getVectorOfSoldier();
			   if((contemporaryPlace+(cubeNumber1*2))<25)
			      soldierHelp2=triangles[contemporaryPlace+(cubeNumber1*2)].getVectorOfSoldier();
			   if((contemporaryPlace+(cubeNumber1*3))<25)
			      soldierHelp3=triangles[contemporaryPlace+(cubeNumber1*3)].getVectorOfSoldier();
			   if((contemporaryPlace+(cubeNumber1*4))<25)
			      soldierHelp4=triangles[contemporaryPlace+(cubeNumber1*4)].getVectorOfSoldier();
	   }
	   
	   if(color.equals("black"))
	   {
		       if((contemporaryPlace-cubeNumber1)>0)
			      soldierHelp1=triangles[contemporaryPlace-cubeNumber1].getVectorOfSoldier();
			   if((contemporaryPlace-(cubeNumber1*2))>0)
			      soldierHelp2=triangles[contemporaryPlace-(cubeNumber1*2)].getVectorOfSoldier();
			   if((contemporaryPlace-(cubeNumber1*3))>0)
			      soldierHelp3=triangles[contemporaryPlace-(cubeNumber1*3)].getVectorOfSoldier();
			   if((contemporaryPlace-(cubeNumber1*4))>0)
			      soldierHelp4=triangles[contemporaryPlace-(cubeNumber1*4)].getVectorOfSoldier();
	   }
	   
	   if((color.equals("white"))&&(((contemporaryPlace+cubeNumber1*4)<=25)||(((contemporaryPlace+cubeNumber1*3)<=25)||((contemporaryPlace+cubeNumber1*2)<=25)||((contemporaryPlace+cubeNumber1)<=25))))
	   {
		   if((contemporaryPlace+cubeNumber1)<25)
		   {
			   System.out.println("2!");
			   Move move1=new Move(triangles[contemporaryPlace],triangles[contemporaryPlace+cubeNumber1],cubeNumber1);
			   if(soldierHelp1.size()==0)
			   {
				   if(priorityTotal==0)
				   {
					   System.out.println("3!");
					   movesDoubleCube.add(move1);
				   }
			   }
			   if((soldierHelp1.size()>0)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals(color)))
			   {
				   System.out.println("4!");
				   if(priorityTotal==0)
				   {
					   System.out.println("5!");
					   setPriority(0);
					   movesDoubleCube.clear();
				   }
				   movesDoubleCube.add(move1);
			   }
	       }
		   
		   if(takingOutSoldiers)
		   {
			   System.out.println("2!AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			   if((contemporaryPlace+cubeNumber1)==25)
			   {
				   Move move1=new Move(triangles[contemporaryPlace],finishTriangleOfComputer,cubeNumber1);
				   System.out.println("2!000000000000000000000");
				   takingOutMovesDoubleCube.add(move1);
		       }
		   }
		   
		   if((contemporaryPlace+(cubeNumber1*2))<25)
		   {
			   System.out.println("6!");
			   Move move1=new Move(triangles[contemporaryPlace],triangles[contemporaryPlace+(cubeNumber1*2)],(cubeNumber1*2));
			   if((soldierHelp2.size()==0)&&(soldierHelp1.size()==0))//
			   {
				   System.out.println("7!");
				   if(priorityTotal==0)
				   {
					   System.out.println("8!");
					   movesDoubleCube.add(move1);
				   }
			   }
			   if((soldierHelp2.size()>0)&&(soldierHelp2.get(soldierHelp2.size()-1).getColor().equals(color)))
			   {
				   if(((soldierHelp1.size()>0)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals(color)))||(soldierHelp1.size()==0))//
				   {
					   System.out.println("9!");
					   if(priorityTotal==0)
					   {
						   System.out.println("10!");
						   setPriority(0);
						   movesDoubleCube.clear();
					   }
					   movesDoubleCube.add(move1);
				   }
			   }
	       }
		   
		   if(takingOutSoldiers)
		   {
			   if((contemporaryPlace+(cubeNumber1*2))==25)
			   {
				   Move move1=new Move(triangles[contemporaryPlace],finishTriangleOfComputer,(cubeNumber1*2));
				   System.out.println("3!000000000000000000000");
				   takingOutMovesDoubleCube.add(move1);
		       }
		   }
		   
		   if((contemporaryPlace+(cubeNumber1*3))<25)
		   {
			   System.out.println("11!");
			   Move move1=new Move(triangles[contemporaryPlace],triangles[contemporaryPlace+(cubeNumber1*3)],(cubeNumber1*3));
			   if((soldierHelp3.size()==0)&&(soldierHelp2.size()==0)&&(soldierHelp1.size()==0))//
			   {
				   System.out.println("12!");
				   if(priorityTotal==0)
				   {
					   System.out.println("13!");
					   movesDoubleCube.add(move1);
				   }
			   }
			   if((soldierHelp3.size()>0)&&(soldierHelp3.get(soldierHelp3.size()-1).getColor().equals(color)))
			   {
				   if(((soldierHelp2.size()>0)&&(soldierHelp2.get(soldierHelp2.size()-1).getColor().equals(color)))||(soldierHelp2.size()==0))//
				   {
					   if(((soldierHelp1.size()>0)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals(color)))||(soldierHelp1.size()==0))//
					   {
						   System.out.println("14!");
						   if(priorityTotal==0)
						   {
							   System.out.println("15!");
							   setPriority(0);
							   movesDoubleCube.clear();
						   }
						   movesDoubleCube.add(move1);
					   }
				   }
			   }
	       }
		   
		   if(takingOutSoldiers)
		   {
			   if((contemporaryPlace+(cubeNumber1*3))==25)
			   {
				   Move move1=new Move(triangles[contemporaryPlace],finishTriangleOfComputer,(cubeNumber1*3));
				   System.out.println("4!000000000000000000000");
				   takingOutMovesDoubleCube.add(move1);
		       }
		   }
		   
		   if((contemporaryPlace+(cubeNumber1*4))<25)
		   {
			   System.out.println("16!");
			   Move move1=new Move(triangles[contemporaryPlace],triangles[contemporaryPlace+(cubeNumber1*4)],(cubeNumber1*4));
			   if((soldierHelp4.size()==0)&&(soldierHelp3.size()==0)&&(soldierHelp2.size()==0)&&(soldierHelp1.size()==0))
			   {
				   System.out.println("17!");
				   if(priorityTotal==0)
				   {
					   System.out.println("18!");
					   movesDoubleCube.add(move1);
				   }
			   }
			   if((soldierHelp4.size()>0)&&(soldierHelp4.get(soldierHelp4.size()-1).getColor().equals(color)))
			   {
				   if(((soldierHelp3.size()>0)&&(soldierHelp3.get(soldierHelp3.size()-1).getColor().equals(color)))||(soldierHelp3.size()==0))//
				   {
					   if(((soldierHelp2.size()>0)&&(soldierHelp2.get(soldierHelp2.size()-1).getColor().equals(color)))||(soldierHelp2.size()==0))//
					   {
						   if(((soldierHelp1.size()>0)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals(color)))||(soldierHelp1.size()==0))//
						   {
							   System.out.println("19!");
							   if(priorityTotal==0)
							   {
								   System.out.println("20!");
								   setPriority(0);
								   movesDoubleCube.clear();
							   }
							   movesDoubleCube.add(move1);
						   }
					   }
				   }
			   }
	       }
		   
		   if(takingOutSoldiers)
		   {
			   if((contemporaryPlace+(cubeNumber1*4))==25)
			   {
				   Move move1=new Move(triangles[contemporaryPlace],finishTriangleOfComputer,(cubeNumber1*4));
				   System.out.println("5!000000000000000000000");
				   takingOutMovesDoubleCube.add(move1);
		       }
		   }
	   }
	   
	   if((color.equals("black"))&&(((contemporaryPlace-cubeNumber1*4)>=0)||(((contemporaryPlace-cubeNumber1*3)>=0)||((contemporaryPlace-cubeNumber1*2)>=0)||((contemporaryPlace-cubeNumber1)>=0))))
	   {
		   if((contemporaryPlace-cubeNumber1)>0)
		   {
			   Move move1=new Move(triangles[contemporaryPlace],triangles[contemporaryPlace-cubeNumber1],cubeNumber1);
			   if(soldierHelp1.size()==0)
			   {
				   if(priorityTotal==0)
					   movesDoubleCube.add(move1);
			   }
			   if((soldierHelp1.size()>0)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals(color)))
			   {
				   if(priorityTotal==0)
				   {
					   setPriority(0);
					   movesDoubleCube.clear();
				   }
				   movesDoubleCube.add(move1);
			   }
	       }
		   
		   if(takingOutSoldiers)
		   {
			   if((contemporaryPlace-(cubeNumber1))==0)
			   {
				   Move move1=new Move(triangles[contemporaryPlace],finishTriangleOfComputer,cubeNumber1);
				   takingOutMovesDoubleCube.add(move1);
		       }
		   }
		   
		   if((contemporaryPlace-(cubeNumber1*2))>0)
		   {
			   Move move1=new Move(triangles[contemporaryPlace],triangles[contemporaryPlace-(cubeNumber1*2)],(cubeNumber1*2));
			   if(soldierHelp2.size()==0)
			   {
				   if(priorityTotal==0)
					   movesDoubleCube.add(move1);
			   }
			   if((soldierHelp2.size()>0)&&(soldierHelp2.get(soldierHelp2.size()-1).getColor().equals(color)))
			   {
				   if(((soldierHelp1.size()>0)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals(color)))||(soldierHelp1.size()==0))//
				   {
					   if(priorityTotal==0)
					   {
						   setPriority(0);
						   movesDoubleCube.clear();
					   }
					   movesDoubleCube.add(move1);
				   }
			   }
	       }
		   
		   if(takingOutSoldiers)
		   {
			   if((contemporaryPlace-(cubeNumber1*2))==0)
			   {
				   Move move1=new Move(triangles[contemporaryPlace],finishTriangleOfComputer,(cubeNumber1*2));
				   takingOutMovesDoubleCube.add(move1);
		       }
		   }
		   
		   if((contemporaryPlace-(cubeNumber1*3))>0)
		   {
			   Move move1=new Move(triangles[contemporaryPlace],triangles[contemporaryPlace-(cubeNumber1*3)],(cubeNumber1*3));
			   if(soldierHelp3.size()==0)
			   {
				   if(priorityTotal==0)
					   movesDoubleCube.add(move1);
			   }
			   if((soldierHelp3.size()>0)&&(soldierHelp3.get(soldierHelp3.size()-1).getColor().equals(color)))
			   {
				   if(((soldierHelp2.size()>0)&&(soldierHelp2.get(soldierHelp2.size()-1).getColor().equals(color)))||(soldierHelp2.size()==0))//
				   {
					   if(((soldierHelp1.size()>0)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals(color)))||(soldierHelp1.size()==0))//
					   {
						   if(priorityTotal==0)
						   {
							   setPriority(0);
							   movesDoubleCube.clear();
						   }
						   movesDoubleCube.add(move1);
					   }
				   }
			   }
	       }
		   
		   if(takingOutSoldiers)
		   {
			   if((contemporaryPlace-(cubeNumber1*3))==0)
			   {
				   Move move1=new Move(triangles[contemporaryPlace],finishTriangleOfComputer,(cubeNumber1*3));
				   takingOutMovesDoubleCube.add(move1);
		       }
		   }
		   
		   if((contemporaryPlace-(cubeNumber1*4))>0)
		   {
			   Move move1=new Move(triangles[contemporaryPlace],triangles[contemporaryPlace-(cubeNumber1*4)],(cubeNumber1*4));
			   if(soldierHelp4.size()==0)
			   {
				   if(priorityTotal==0)
					   movesDoubleCube.add(move1);
			   }
			   if((soldierHelp4.size()>0)&&(soldierHelp4.get(soldierHelp4.size()-1).getColor().equals(color)))
			   {
				   if(((soldierHelp3.size()>0)&&(soldierHelp3.get(soldierHelp3.size()-1).getColor().equals(color)))||(soldierHelp3.size()==0))//
				   {
					   if(((soldierHelp2.size()>0)&&(soldierHelp2.get(soldierHelp2.size()-1).getColor().equals(color)))||(soldierHelp2.size()==0))//
					   {
						   if(((soldierHelp1.size()>0)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals(color)))||(soldierHelp1.size()==0))//
						   {
							   if(priorityTotal==0)
							   {
								   setPriority(0);
								   movesDoubleCube.clear();
							   }
							   movesDoubleCube.add(move1);
						   }
					   }
				   }
			   }
	       }
		   
		   if(takingOutSoldiers)
		   {
			   if((contemporaryPlace-(cubeNumber1*4))==0)
			   {
				   Move move1=new Move(triangles[contemporaryPlace],finishTriangleOfComputer,(cubeNumber1*4));
				   takingOutMovesDoubleCube.add(move1);
		       }
		   }
	   }
   }
   
   public void movingDoubleCube()
   {
	   int placeRandom=movingAccordingTheCubes(movesDoubleCube);
	   
	   int numberOfUsingCube=movesDoubleCube.get(placeRandom).getNumberOfCube();
	   
	   int usingOneCube=cubeNumber1;
	   int usingTwoCube=(cubeNumber1*2);
	   int usingThreeCube=(cubeNumber1*3);
	   int usingFourCube=(cubeNumber1*4);
	   
	   
	   if(numberOfUsedCubeTotalLeft!=0)
	   {
		   if(numberOfUsingCube==usingFourCube)
		   {
			   System.out.println("Used 4");
			   numberOfUsedCubeTotalLeft=numberOfUsedCubeTotalLeft-usingFourCube;
			  // updateVectorOfDoubleCube(usingFourCube);
			   //if(movesDoubleCube.size()>0)
			          //movingDoubleCube();
		   }
		   if(numberOfUsingCube==usingThreeCube)
		   {
			   System.out.println("Used 3");
			   numberOfUsedCubeTotalLeft=numberOfUsedCubeTotalLeft-usingThreeCube;
			   //updateVectorOfDoubleCube(usingThreeCube);
			   //if(movesDoubleCube.size()>0)
			   if(numberOfUsedCubeTotalLeft>0)
			   {
				      //movingDoubleCube(); 
				   //findingSoldierOfTheComputer();
				   //doingMoveOfTheComputer();
				   updateVectorOfDoubleCube(3);
			   }
		   }
		   if(numberOfUsingCube==usingTwoCube)
		   {
			   System.out.println("Used 2");
			   numberOfUsedCubeTotalLeft=numberOfUsedCubeTotalLeft-usingTwoCube;
			   //updateVectorOfDoubleCube(usingTwoCube);
			   //if(movesDoubleCube.size()>0)
			   if(numberOfUsedCubeTotalLeft>0)
			   {
				      //movingDoubleCube();
				   //findingSoldierOfTheComputer();
				   //doingMoveOfTheComputer();
				   updateVectorOfDoubleCube(2);
			   }
		   }
		   if(numberOfUsingCube==usingOneCube)
		   {
			   System.out.println("Used 1");
			   numberOfUsedCubeTotalLeft=numberOfUsedCubeTotalLeft-usingOneCube;
			   System.out.println("numberOfUsedCubeTotalLeft%%%%%%%%%%%%%%%%%%%"+numberOfUsedCubeTotalLeft);
			   //updateVectorOfDoubleCube(usingOneCube);
			   //if(movesDoubleCube.size()>0)
			   if(numberOfUsedCubeTotalLeft>0)
			   {
				      //movingDoubleCube();
				  // findingSoldierOfTheComputer();
				  // doingMoveOfTheComputer();
				   
				   updateVectorOfDoubleCube(1);
			   }
		   }
	   }
   } 
   
   public void updateVectorOfDoubleCube(int numberOfUsingCube)
   {
	   this.priorityTotal=0;
	   
	   movesDoubleCube.clear();
	   findingSoldierOfTheComputer();
	   	   
	   System.out.println("before delet");
	   
	   printingTheVector3();
	  
	   Vector<Move>vectorHelp=new Vector<Move>();	   

	   
	   System.out.println("the number that used is:"+numberOfUsingCube);
	   
	   //
	   	   	   	    
	   for(int i=0;i<movesDoubleCube.size();i++)
	   {
		   switch(numberOfUsingCube)
		   {
		   case 1:
			   System.out.println("The case is 1");
		   	   if((movesDoubleCube.get(i).getNumberOfCube()==(cubeNumber1*2))||(movesDoubleCube.get(i).getNumberOfCube()==(cubeNumber1*3))||(movesDoubleCube.get(i).getNumberOfCube()==cubeNumber1))
		   		   vectorHelp.add(movesDoubleCube.get(i));
		   	   break;
		   case 2:
			   System.out.println("The case is 2");
		   	   if((movesDoubleCube.get(i).getNumberOfCube()==cubeNumber1)||(movesDoubleCube.get(i).getNumberOfCube()==(cubeNumber1*2)))
		   		   vectorHelp.add(movesDoubleCube.get(i));
		   	   break;
		   case 3:
			   System.out.println("The case is 3");
		   	   if(movesDoubleCube.get(i).getNumberOfCube()==cubeNumber1)
		   		   vectorHelp.add(movesDoubleCube.get(i));
		   	   break;
		   }
	   }
	   
	   System.out.println("The Printing is vectorHelp !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	   for(int i=0;i<vectorHelp.size();i++)
	   {
		   System.out.println("the start is"+vectorHelp.get(i).getFromTriangle().getNumberOfTriangle());
		   System.out.println("the finish is"+vectorHelp.get(i).getToTriangle().getNumberOfTriangle());
		   
	   }
	   
       movesDoubleCube.clear();
	   
	   for(int i=0;i<vectorHelp.size();i++)
	   {
		   movesDoubleCube.add(vectorHelp.get(i));
	   }
	   
	   
	   System.out.println("the new vector is:");
	   
	   System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
   
	   printingTheVector3();
	   //
	   
	   
	   if((movesDoubleCube.size()>0)&&(numberOfUsedCubeTotalLeft>0))
	   {
		   System.out.println("did do the if");
		   doingMoveOfTheComputer();
	   }
	   else
		   System.out.println("did not do the if");
	   
	  // if((movesDoubleCube.size()>0)&&(tryingToFindAnotherOption<24))
	  // {
		   //tryingToFindAnotherOption++;
		   //doingMoveOfTheComputer();
	  // }
	 //  else
		  // JOptionPane.showMessageDialog(null, "No More Moves For The Computer", "Error",
           	//	JOptionPane.ERROR_MESSAGE);
		   
   }
   
   public void checkingEatenSoldierInTheMiddleOfTheBoard()  
   {
	   for(int i=0;i<triangles[0].getVectorOfSoldier().size();i++)
	   {
		   if(triangles[0].getVectorOfSoldier().get(i).getColor().equals(color))
		   {
			   EatenSoldierInTheMiddleOfTheBoardFree=false;
		   }
	   }
   }
   
   public void checkingPlaceForEatenSoldierInTheBoard(Soldier soldier1)
   {
	   if(cubeNumber1==cubeNumber2)
	   {
		   System.out.println("@@@@@@@@@@@@@@@@@@@@!");
		   if(!alreadySet)
		   {
			   alreadySet=true;
			   numberOfUsedCubeTotalLeft=cubeNumber1*4;
		   }
		   System.out.println("numberOfUsedCubeTotalLeft!!!!!!!!!!!!!!"+numberOfUsedCubeTotalLeft);
		   checkingPlaceForEatenSoldierAccordingDoubleCube(soldier1);
	   }
	   else
	   { 		   
		   Vector<Soldier>soldierHelp=new Vector<Soldier>();
		   
		   System.out.println("In the operation of the checking place for eaten soldier\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
		   	   
		   if((color.equals("white"))&&(((cubeNumber1+cubeNumber2)<25)||((cubeNumber1)<25)||((cubeNumber2)<25)))
		   {
			   System.out.println("In the if of the checking place for eaten soldier@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			   if(cubeNumber1<25)
			   {
				   soldierHelp=triangles[cubeNumber1].getVectorOfSoldier();
				   Move move1=new Move(triangles[0],triangles[cubeNumber1],cubeNumber1);
				   if(soldierHelp.size()==0)
				   {
					   System.out.println("1");
					   if(priority1==0)
						   movesCubeNumber1.add(move1);
				   }
				   if((soldierHelp.size()>0)&&(soldierHelp.get(soldierHelp.size()-1).getColor().equals(color)))
				   {
					   System.out.println("2");
					   if(priority1==0)
					   {
						   System.out.println("3");
						   setPriority(1);
						   movesCubeNumber1.clear();
					   }
					   movesCubeNumber1.add(move1);
				   }
				   
				   if((soldierHelp.size()==1)&&(soldierHelp.get(0).getColor().equals("black")))
					   VectorEatingCubeNumber1.add(move1);
		       }
			   
			   if(cubeNumber2<25)
			   {
				   soldierHelp=triangles[cubeNumber2].getVectorOfSoldier();
				   Move move1=new Move(triangles[0],triangles[cubeNumber2],cubeNumber2);
				   if(soldierHelp.size()==0)
				   {
					   System.out.println("5");
					   if(priority2==0)
						   movesCubeNumber2.add(move1);
				   }
				   if((soldierHelp.size()>0)&&(soldierHelp.get(soldierHelp.size()-1).getColor().equals(color)))
				   {
					   System.out.println("6");
					   if(priority2==0)
					   {
						   System.out.println("7");
						   setPriority(2);
						   movesCubeNumber2.clear();
					   }
					   movesCubeNumber2.add(move1);
				   }
				   
				   if((soldierHelp.size()==1)&&(soldierHelp.get(soldierHelp.size()-1).getColor().equals("black")))
					   VectorEatingCubeNumber2.add(move1);
		       }
			   
			   if(cubeNumber1+cubeNumber2<25)
			   {
				   soldierHelp=triangles[cubeNumber1+cubeNumber2].getVectorOfSoldier();
				   Move move1=new Move(triangles[0],triangles[cubeNumber1+cubeNumber2],(cubeNumber1+cubeNumber2));
				   //if(soldierHelp.size()==0)
				   if((soldierHelp.size()==0)&&((triangles[cubeNumber1].getVectorOfSoldier().size()==0)||(triangles[cubeNumber2].getVectorOfSoldier().size()==0)))
				   {
					   System.out.println("9");
					   if(priorityTotal==0)
						   movesTwoCubes.add(move1);
				   }
				   //if((soldierHelp.size()>0)&&(soldierHelp.get(soldierHelp.size()-1).getColor().equals(color)))
				   if((soldierHelp.size()>0)&&(soldierHelp.get(soldierHelp.size()-1).getColor().equals(color)))
				   {
					   if((triangles[cubeNumber1].getVectorOfSoldier().size()>0)&&(triangles[cubeNumber1].getVectorOfSoldier().get(triangles[cubeNumber1].getVectorOfSoldier().size()-1).equals(color))||(((triangles[cubeNumber2].getVectorOfSoldier().size()>0)&&(triangles[cubeNumber2].getVectorOfSoldier().get(triangles[cubeNumber2].getVectorOfSoldier().size()-1).equals(color)))))
					   {
						   System.out.println("10");
						   if(priorityTotal==0)
						   {
							   System.out.println("11");
							   setPriority(0);
							   movesTwoCubes.clear();
						   }
						   movesTwoCubes.add(move1);
					   }
				   }
		       }
		   }
		   
		   
		   if((color.equals("black"))&&((25-(cubeNumber1+cubeNumber2)>0)||((25-cubeNumber1)>0)||((25-cubeNumber2)>0)))
		   {
			   if((25-cubeNumber1)>0)
			   {
				   soldierHelp=triangles[25-cubeNumber1].getVectorOfSoldier();
				   Move move1=new Move(triangles[0],triangles[25-cubeNumber1],cubeNumber1);
				   if(soldierHelp.size()==0)
				   {
					   System.out.println("13");
					   if(priority1==0)
						   movesCubeNumber1.add(move1);
				   }
				   if((soldierHelp.size()>0)&&(soldierHelp.get(soldierHelp.size()-1).getColor().equals(color)))
				   {
					   System.out.println("14");
					   if(priority1==0)
					   {
						   System.out.println("15");
						   setPriority(1);
						   movesCubeNumber1.clear();
					   }
					   movesCubeNumber1.add(move1);
				   }
				   
				   if((soldierHelp.size()==1)&&(soldierHelp.get(soldierHelp.size()-1).getColor().equals("white")))
					   VectorEatingCubeNumber1.add(move1);
		       }
			   
			   if((25-cubeNumber2)>0)
			   {
				   soldierHelp=triangles[25-cubeNumber2].getVectorOfSoldier();
				   Move move1=new Move(triangles[0],triangles[25-cubeNumber2],cubeNumber2);
				   if(soldierHelp.size()==0)
				   {
					   System.out.println("17");
					   if(priority2==0)
						   movesCubeNumber1.add(move1);
				   }
				   if((soldierHelp.size()>0)&&(soldierHelp.get(soldierHelp.size()-1).getColor().equals(color)))
				   {
					   System.out.println("18");
					   if(priority2==0)
					   {
						   System.out.println("19");
						   setPriority(2);
						   movesCubeNumber1.clear();
					   }
					   movesCubeNumber1.add(move1);
				   }
				   
				   if((soldierHelp.size()==1)&&(soldierHelp.get(soldierHelp.size()-1).getColor().equals("white")))
					   VectorEatingCubeNumber2.add(move1);
		       }
			   
			   if((25-(cubeNumber1+cubeNumber2))>0)
			   {
				   soldierHelp=triangles[25-(cubeNumber1+cubeNumber2)].getVectorOfSoldier();
				   Move move1=new Move(triangles[0],triangles[25-(cubeNumber1+cubeNumber2)],(cubeNumber1+cubeNumber2));
				   //if(soldierHelp.size()==0)
				   if((soldierHelp.size()==0)&&((triangles[25-cubeNumber1].getVectorOfSoldier().size()==0)||(triangles[25-cubeNumber2].getVectorOfSoldier().size()==0)))
				   {
					   System.out.println("21");
					   if(priorityTotal==0)
						   movesTwoCubes.add(move1);
				   }
				   //if((soldierHelp.size()>0)&&(soldierHelp.get(soldierHelp.size()-1).getColor().equals(color)))
				   if((soldierHelp.size()>0)&&(soldierHelp.get(soldierHelp.size()-1).getColor().equals(color)))
				   {
					   if((triangles[25-cubeNumber1].getVectorOfSoldier().size()>0)&&(triangles[25-cubeNumber1].getVectorOfSoldier().get(triangles[25-cubeNumber1].getVectorOfSoldier().size()-1).equals(color))||(((triangles[25-cubeNumber2].getVectorOfSoldier().size()>0)&&(triangles[25-cubeNumber2].getVectorOfSoldier().get(triangles[25-cubeNumber2].getVectorOfSoldier().size()-1).equals(color)))))
					   {
						   System.out.println("22");
						   if(priorityTotal==0)
						   {
							   System.out.println("23");
							   setPriority(0);
							   movesTwoCubes.clear();
						   }
						   movesTwoCubes.add(move1); 
					   }
				   }
		       }
		   }
	   }
   }
   
   public void checkingPlaceForEatenSoldierAccordingDoubleCube(Soldier soldier1)
   {
	   int contemporaryPlace=soldier1.getContemporaryTriangle();
   	   
	   Vector<Soldier>soldierHelp1=new Vector<Soldier>();
	   Vector<Soldier>soldierHelp2=new Vector<Soldier>();
	   Vector<Soldier>soldierHelp3=new Vector<Soldier>();
	   Vector<Soldier>soldierHelp4=new Vector<Soldier>();
	   
	   System.out.println("1!");
	   
	   if(color.equals("white"))
	   {
		       if((cubeNumber1)<25)
			      soldierHelp1=triangles[cubeNumber1].getVectorOfSoldier();
			   if((cubeNumber1*2)<25)
			      soldierHelp2=triangles[(cubeNumber1*2)].getVectorOfSoldier();
			   if((cubeNumber1*3)<25)
			      soldierHelp3=triangles[(cubeNumber1*3)].getVectorOfSoldier();
			   if((cubeNumber1*4)<25)
			      soldierHelp4=triangles[(cubeNumber1*4)].getVectorOfSoldier();
	   }
	   
	   if(color.equals("black"))
	   {
		       if((25-cubeNumber1)>0)
			      soldierHelp1=triangles[25-cubeNumber1].getVectorOfSoldier();
			   if((25-(cubeNumber1*2))>0)
			      soldierHelp2=triangles[25-(cubeNumber1*2)].getVectorOfSoldier();
			   if((25-(cubeNumber1*3))>0)
			      soldierHelp3=triangles[25-(cubeNumber1*3)].getVectorOfSoldier();
			   if((25-(cubeNumber1*4))>0)
			      soldierHelp4=triangles[25-(cubeNumber1*4)].getVectorOfSoldier();
	   }
	   if((color.equals("white"))&&(((cubeNumber1*4)<25)||(((cubeNumber1*3)<25)||((cubeNumber1*2)<25)||((cubeNumber1)<25))))
	   {
		   if(cubeNumber1<25)
		   {
			   System.out.println("2!");
			   //soldierHelp=triangles[contemporaryPlace+cubeNumber1].getVectorOfSoldier();
			   Move move1=new Move(triangles[0],triangles[cubeNumber1],cubeNumber1);
			   if(soldierHelp1.size()==0)
			   {
				   if(priorityTotal==0)
				   {
					   System.out.println("3!");
					   movesDoubleCube.add(move1);
				   }
			   }
			   if((soldierHelp1.size()>0)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals(color)))
			   {
				   System.out.println("4!");
				   if(priorityTotal==0)
				   {
					   System.out.println("5!");
					   setPriority(0);
					   movesDoubleCube.clear();
				   }
				   movesDoubleCube.add(move1);
			   }
			   
			   if((soldierHelp1.size()==1)&&(!soldierHelp1.get(soldierHelp1.size()-1).getColor().equals(color)))
				   VectorEatingCubeNumber1.add(move1);
			   
			   
	       }
		   
		   if((cubeNumber1*2)<25)
		   {
			   System.out.println("6!");
			   //soldierHelp=triangles[contemporaryPlace+(cubeNumber1*2)].getVectorOfSoldier();
			   Move move1=new Move(triangles[0],triangles[(cubeNumber1*2)],(cubeNumber1*2));
			   if((soldierHelp2.size()==0)&&(soldierHelp1.size()==0))//
			   {
				   System.out.println("7!");
				   if(priorityTotal==0)
				   {
					   System.out.println("8!");
					   movesDoubleCube.add(move1);
				   }
			   }
			   if((soldierHelp2.size()>0)&&(soldierHelp2.get(soldierHelp2.size()-1).getColor().equals(color)))
			   {
				   if(((soldierHelp1.size()>0)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals(color)))||(soldierHelp1.size()==0))//
				   {
					   System.out.println("9!");
					   if(priorityTotal==0)
					   {
						   System.out.println("10!");
						   setPriority(0);
						   movesDoubleCube.clear();
					   }
					   movesDoubleCube.add(move1);
				   }
			   }
			   
			   if((soldierHelp2.size()==1)&&(soldierHelp2.get(soldierHelp2.size()-1).getColor().equals("black")))
			   {
				   if(((soldierHelp1.size()==1)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals("black")))||(soldierHelp1.size()==0))
				   {
					   movesDoubleCube.add(move1);
				   }
			   }
	       }
		   
		   if((contemporaryPlace+(cubeNumber1*3))<25)
		   {
			   System.out.println("11!");
			   //soldierHelp=triangles[contemporaryPlace+(cubeNumber1*3)].getVectorOfSoldier();
			   Move move1=new Move(triangles[0],triangles[(cubeNumber1*3)],(cubeNumber1*3));
			   if((soldierHelp3.size()==0)&&(soldierHelp2.size()==0)&&(soldierHelp1.size()==0))//
			   {
				   System.out.println("12!");
				   if(priorityTotal==0)
				   {
					   System.out.println("13!");
					   movesDoubleCube.add(move1);
				   }
			   }
			   if((soldierHelp3.size()>0)&&(soldierHelp3.get(soldierHelp3.size()-1).getColor().equals(color)))
			   {
				   if(((soldierHelp2.size()>0)&&(soldierHelp2.get(soldierHelp2.size()-1).getColor().equals(color)))||(soldierHelp2.size()==0))//
				   {
					   if(((soldierHelp1.size()>0)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals(color)))||(soldierHelp1.size()==0))//
					   {
						   System.out.println("14!");
						   if(priorityTotal==0)
						   {
							   System.out.println("15!");
							   setPriority(0);
							   movesDoubleCube.clear();
						   }
						   movesDoubleCube.add(move1);
					   }
				   }
			   }
			   
			   if((soldierHelp3.size()==1)&&(soldierHelp3.get(soldierHelp3.size()-1).getColor().equals("black")))
			   {
				   if(((soldierHelp2.size()==1)&&(soldierHelp2.get(soldierHelp2.size()-1).getColor().equals("black")))||(soldierHelp2.size()==0))
				   {
					   if(((soldierHelp1.size()==1)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals("black")))||(soldierHelp1.size()==0))
					   {
						   movesDoubleCube.add(move1);
					   }
				   }
			   }
	       }
		   
		   if((contemporaryPlace+(cubeNumber1*4))<25)
		   {
			   System.out.println("16!");
			   //soldierHelp=triangles[contemporaryPlace+(cubeNumber1*4)].getVectorOfSoldier();
			   Move move1=new Move(triangles[0],triangles[(cubeNumber1*4)],(cubeNumber1*4));
			   if((soldierHelp4.size()==0)&&(soldierHelp3.size()==0)&&(soldierHelp2.size()==0)&&(soldierHelp1.size()==0))
			   {
				   System.out.println("17!");
				   if(priorityTotal==0)
				   {
					   System.out.println("18!");
					   movesDoubleCube.add(move1);
				   }
			   }
			   if((soldierHelp4.size()>0)&&(soldierHelp4.get(soldierHelp4.size()-1).getColor().equals(color)))
			   {
				   if(((soldierHelp3.size()>0)&&(soldierHelp3.get(soldierHelp3.size()-1).getColor().equals(color)))||(soldierHelp3.size()==0))//
				   {
					   if(((soldierHelp2.size()>0)&&(soldierHelp2.get(soldierHelp2.size()-1).getColor().equals(color)))||(soldierHelp2.size()==0))//
					   {
						   if(((soldierHelp1.size()>0)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals(color)))||(soldierHelp1.size()==0))//
						   {
							   System.out.println("19!");
							   if(priorityTotal==0)
							   {
								   System.out.println("20!");
								   setPriority(0);
								   movesDoubleCube.clear();
							   }
							   movesDoubleCube.add(move1);
						   }
					   }
				   }
			   }
			   
			   if((soldierHelp4.size()==1)&&(soldierHelp4.get(soldierHelp4.size()-1).getColor().equals("black")))
			   {
				   if(((soldierHelp2.size()==1)&&(soldierHelp2.get(soldierHelp2.size()-1).getColor().equals("black")))||(soldierHelp2.size()==0))
				   {
					   if(((soldierHelp2.size()==1)&&(soldierHelp2.get(soldierHelp2.size()-1).getColor().equals("black")))||(soldierHelp2.size()==0))
					   {
						   if(((soldierHelp1.size()==1)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals("black")))||(soldierHelp1.size()==0))
						   {
							   movesDoubleCube.add(move1);
						   }
					   }
				   }
			   }
	       }
	   }
	   if((color.equals("black"))&&(((25-cubeNumber1*4)>0)||(((25-cubeNumber1*3)>0)||((25-cubeNumber1*2)>0)||((25-cubeNumber1)>0))))
	   {
		   if((25-cubeNumber1)>0)
		   {
			   //soldierHelp1=triangles[contemporaryPlace-cubeNumber1].getVectorOfSoldier();
			   Move move1=new Move(triangles[0],triangles[25-cubeNumber1],cubeNumber1);
			   if(soldierHelp1.size()==0)
			   {
				   if(priorityTotal==0)
					   movesDoubleCube.add(move1);
			   }
			   if((soldierHelp1.size()>0)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals(color)))
			   {
				   if(priorityTotal==0)
				   {
					   setPriority(0);
					   movesDoubleCube.clear();
				   }
				   movesDoubleCube.add(move1);
			   }
			   if((soldierHelp1.size()==1)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals("white")))
			   {
				   movesDoubleCube.add(move1);
			   }
	       }
		   
		   if((25-(cubeNumber1*2))>0)
		   {
			   //soldierHelp2=triangles[contemporaryPlace-(cubeNumber1*2)].getVectorOfSoldier();
			   Move move1=new Move(triangles[0],triangles[25-(cubeNumber1*2)],(cubeNumber1*2));
			   if(soldierHelp2.size()==0)
			   {
				   if(priorityTotal==0)
					   movesDoubleCube.add(move1);
			   }
			   if((soldierHelp2.size()>0)&&(soldierHelp2.get(soldierHelp2.size()-1).getColor().equals(color)))
			   {
				   if(((soldierHelp1.size()>0)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals(color)))||(soldierHelp1.size()==0))//
				   {
					   if(priorityTotal==0)
					   {
						   setPriority(0);
						   movesDoubleCube.clear();
					   }
					   movesDoubleCube.add(move1);
				   }
			   }
			   if((soldierHelp2.size()==1)&&(soldierHelp2.get(soldierHelp2.size()-1).getColor().equals("white")))
			   {
				   if(((soldierHelp1.size()==1)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals("white")))||(soldierHelp1.size()==0))
				   {
					   movesDoubleCube.add(move1);
				   }
			   }
	       }
		   
		   if((25-(cubeNumber1*3))>0)
		   {
			   //soldierHelp3=triangles[contemporaryPlace-(cubeNumber1*3)].getVectorOfSoldier();
			   Move move1=new Move(triangles[0],triangles[25-(cubeNumber1*3)],(cubeNumber1*3));
			   if(soldierHelp3.size()==0)
			   {
				   if(priorityTotal==0)
					   movesDoubleCube.add(move1);
			   }
			   if((soldierHelp3.size()>0)&&(soldierHelp3.get(soldierHelp3.size()-1).getColor().equals(color)))
			   {
				   if(((soldierHelp2.size()>0)&&(soldierHelp2.get(soldierHelp2.size()-1).getColor().equals(color)))||(soldierHelp2.size()==0))//
				   {
					   if(((soldierHelp1.size()>0)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals(color)))||(soldierHelp1.size()==0))//
					   {
						   if(priorityTotal==0)
						   {
							   setPriority(0);
							   movesDoubleCube.clear();
						   }
						   movesDoubleCube.add(move1);
					   }
				   }
			   }
			   if((soldierHelp3.size()==1)&&(soldierHelp3.get(soldierHelp3.size()-1).getColor().equals("white")))
			   {
				   if(((soldierHelp2.size()==1)&&(soldierHelp2.get(soldierHelp2.size()-1).getColor().equals("white")))||(soldierHelp2.size()==0))
				   {
					   if(((soldierHelp1.size()==1)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals("white")))||(soldierHelp1.size()==0))
					   {
						   movesDoubleCube.add(move1);
					   }
				   }
			   }
	       }
		   
		   if((25-(cubeNumber1*4))>0)
		   {
			   //soldierHelp4=triangles[contemporaryPlace-(cubeNumber1*4)].getVectorOfSoldier();
			   Move move1=new Move(triangles[0],triangles[25-(cubeNumber1*4)],(cubeNumber1*4));
			   if(soldierHelp4.size()==0)
			   {
				   if(priorityTotal==0)
					   movesDoubleCube.add(move1);
			   }                                                                                                                            
			   if((soldierHelp4.size()>0)&&(soldierHelp4.get(soldierHelp4.size()-1).getColor().equals(color)))
			   {
				   if(((soldierHelp3.size()>0)&&(soldierHelp3.get(soldierHelp3.size()-1).getColor().equals(color)))||(soldierHelp3.size()==0))//
				   {
					   if(((soldierHelp2.size()>0)&&(soldierHelp2.get(soldierHelp2.size()-1).getColor().equals(color)))||(soldierHelp2.size()==0))//
					   {
						   if(((soldierHelp1.size()>0)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals(color)))||(soldierHelp1.size()==0))//
						   {
							   if(priorityTotal==0)
							   {
								   setPriority(0);
								   movesDoubleCube.clear();
							   }
							   movesDoubleCube.add(move1);
						   }
					   }
				   }
			   }                                                                                                           // לעשות את הבדיקות של אכילה של שחקן אכול באמצע הלוח על שחקן רגיל גם לצבע לבן
			   if((soldierHelp4.size()==1)&&(soldierHelp4.get(soldierHelp4.size()-1).getColor().equals("white")))
			   {
				   if(((soldierHelp2.size()==1)&&(soldierHelp2.get(soldierHelp2.size()-1).getColor().equals("white")))||(soldierHelp2.size()==0))
				   {
					   if(((soldierHelp2.size()==1)&&(soldierHelp2.get(soldierHelp2.size()-1).getColor().equals("white")))||(soldierHelp2.size()==0))
					   {
						   if(((soldierHelp1.size()==1)&&(soldierHelp1.get(soldierHelp1.size()-1).getColor().equals("white")))||(soldierHelp1.size()==0))
						   {
							   movesDoubleCube.add(move1);
						   }
					   }
				   }
			   }
	       }
	   }
   }
   
   public boolean checkingIfComputerCanStartTakeOutSoldier()
   {
	   int countOfSoldiers=0;
		Vector<Soldier> soldierHelp;
		
		if(color.equals("white"))
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
		if(color.equals("black"))
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
			return true;
		return false;
   }

   public void movingDoubleCubeInTakingOutSoldiers()
   {
	   int placeRandom=movingAccordingTheCubes(takingOutMovesDoubleCube);
	   
	   int numberOfUsingCube=takingOutMovesDoubleCube.get(placeRandom).getNumberOfCube();
	   
	   int usingOneCube=cubeNumber1;
	   int usingTwoCube=(cubeNumber1*2);
	   int usingThreeCube=(cubeNumber1*3);
	   int usingFourCube=(cubeNumber1*4);
	   
	   
	   if(numberOfUsedCubeTotalLeft!=0)
	   {
		   if(numberOfUsingCube==usingFourCube)
		   {
			   System.out.println("Used 4");
			   numberOfUsedCubeTotalLeft=numberOfUsedCubeTotalLeft-usingFourCube;
			  // updateVectorOfDoubleCube(usingFourCube);
			   //if(movesDoubleCube.size()>0)
			          //movingDoubleCube();
		   }
		   if(numberOfUsingCube==usingThreeCube)
		   {
			   System.out.println("Used 3");
			   numberOfUsedCubeTotalLeft=numberOfUsedCubeTotalLeft-usingThreeCube;
			   //updateVectorOfDoubleCube(usingThreeCube);
			   //if(movesDoubleCube.size()>0)
			   if(numberOfUsedCubeTotalLeft>0)
			   {
				      //movingDoubleCube(); 
				   //findingSoldierOfTheComputer();
				   //doingMoveOfTheComputer();
				   updateVectorOfDoubleCubeTakingOut(3);
			   }
		   }
		   if(numberOfUsingCube==usingTwoCube)
		   {
			   System.out.println("Used 2");
			   numberOfUsedCubeTotalLeft=numberOfUsedCubeTotalLeft-usingTwoCube;
			   //updateVectorOfDoubleCube(usingTwoCube);
			   //if(movesDoubleCube.size()>0)
			   if(numberOfUsedCubeTotalLeft>0)
			   {
				      //movingDoubleCube();
				   //findingSoldierOfTheComputer();
				   //doingMoveOfTheComputer();
				   updateVectorOfDoubleCubeTakingOut(2);
			   }
		   }
		   if(numberOfUsingCube==usingOneCube)
		   {
			   System.out.println("Used 1");
			   numberOfUsedCubeTotalLeft=numberOfUsedCubeTotalLeft-usingOneCube;
			   System.out.println("numberOfUsedCubeTotalLeft%%%%%%%%%%%%%%%%%%%"+numberOfUsedCubeTotalLeft);
			   //updateVectorOfDoubleCube(usingOneCube);
			   //if(movesDoubleCube.size()>0)
			   if(numberOfUsedCubeTotalLeft>0)
			   {
				      //movingDoubleCube();
				  // findingSoldierOfTheComputer();
				  // doingMoveOfTheComputer();
				   
				   updateVectorOfDoubleCubeTakingOut(1);
			   }
		   }
	   }
   }
	   	   
   public void updateVectorOfDoubleCubeTakingOut(int numberOfUsingCube)
   {
	   this.priorityTotal=0;
	   takingOutMovesDoubleCube.clear();
	   findingSoldierOfTheComputer();
	   	   
	   System.out.println("before delet");
	   
	   System.out.println("the size of the 19 triangle is print number10:"+triangles[19].getVectorOfSoldier().size());
	   System.out.println("the size of the takingOutMovesDoubleCube number10:"+takingOutMovesDoubleCube.size());
	   
	   printingTheVector3();
	  
	   Vector<Move>vectorHelp=new Vector<Move>();	   

	   
	   System.out.println("the number that used is:"+numberOfUsingCube);
	   
	   //
	   	  	   	    
	   for(int i=0;i<takingOutMovesDoubleCube.size();i++)
	   {
		   switch(numberOfUsingCube)
		   {
		   case 1:
			   System.out.println("The case is 1");
		   	   if((takingOutMovesDoubleCube.get(i).getNumberOfCube()==(cubeNumber1*2))||(takingOutMovesDoubleCube.get(i).getNumberOfCube()==(cubeNumber1*3))||(takingOutMovesDoubleCube.get(i).getNumberOfCube()==cubeNumber1))
		   		   vectorHelp.add(takingOutMovesDoubleCube.get(i));
		   	   break;
		   case 2:
			   System.out.println("The case is 2");
		   	   if((takingOutMovesDoubleCube.get(i).getNumberOfCube()==cubeNumber1)||(takingOutMovesDoubleCube.get(i).getNumberOfCube()==(cubeNumber1*2)))
		   		   vectorHelp.add(takingOutMovesDoubleCube.get(i));
		   	   break;
		   case 3:
			   System.out.println("The case is 3");
		   	   if(takingOutMovesDoubleCube.get(i).getNumberOfCube()==cubeNumber1)
		   		   vectorHelp.add(takingOutMovesDoubleCube.get(i));
		   	   break;
		   }
	   }
	   
	   
	   
	   System.out.println("The Printing is vectorHelp !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	   for(int i=0;i<vectorHelp.size();i++)
	   {
		   System.out.println("the start is"+vectorHelp.get(i).getFromTriangle().getNumberOfTriangle());
		   System.out.println("the finish is"+vectorHelp.get(i).getToTriangle().getNumberOfTriangle());
		   
	   }
	   
	   takingOutMovesDoubleCube.clear();
	   
	   for(int i=0;i<vectorHelp.size();i++)
	   {
		   takingOutMovesDoubleCube.add(vectorHelp.get(i));
	   }
	   
	   vectorHelp.clear();
	   
	   
	 //  System.out.println("the new vector is:");
	   
	 //  System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
   
	   //printingTheVector3();
	   //
	   
	   
	   
	   if(takingOutMovesDoubleCube.size()==0)
	   {
		   System.out.println("546287542365743285673264568327465463825764328574653");
		   if(color.equals("white"))
		   {
			   int sizeOfTheVector;
			   
			   Soldier soldierOption;
			   
			   boolean foundSoldier=false;
			   System.out.println("In the if!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!22222222222222222222222222");
			   int i=19;
			   while((i<25)&&(!foundSoldier))
			   {
				   System.out.println("the i is:"+i);
				   sizeOfTheVector=triangles[i].getVectorOfSoldier().size();
				   
				   if(sizeOfTheVector>0)
				   {
					   if(triangles[i].getVectorOfSoldier().get(sizeOfTheVector-1).getColor().equals(color))
					   {
						   soldierOption=triangles[i].getVectorOfSoldier().get(sizeOfTheVector-1);
						   checkingPlaceForSoldierInCaseOfTakingOutAndBiggerCubes(soldierOption);
						   //i=25;
						   foundSoldier=true;
						   System.out.println("In the if!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!33333333333333333333333333");
					   }
				   }
				   i++;
			   }
		   }
		   
		   if(color.equals("black"))
		   {
			   int sizeOfTheVector;
			   
			   Soldier soldierOption;
			   
			   boolean foundSoldier=false;
			   System.out.println("In the if!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!22222222222222222222222222");
			   int i=6;
			   while((i>0)&&(!foundSoldier))
			   {
				   System.out.println("the i is:"+i);
				   sizeOfTheVector=triangles[i].getVectorOfSoldier().size();
				   
				   if(sizeOfTheVector>0)
				   {
					   if(triangles[i].getVectorOfSoldier().get(sizeOfTheVector-1).getColor().equals(color))
					   {
						   soldierOption=triangles[i].getVectorOfSoldier().get(sizeOfTheVector-1);
						   checkingPlaceForSoldierInCaseOfTakingOutAndBiggerCubes(soldierOption);
						   //i=25;
						   foundSoldier=true;
						   System.out.println("In the if!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!33333333333333333333333333");
					   }
				   }
				   i--;
			   }
		   }
		   
		   takingOutMovesDoubleCube=takingOutMovesCubeNumber1;
	   }
	   
	   System.out.println("THE SIZE IS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+takingOutMovesDoubleCube.size());
	   

	   
	   if((takingOutMovesDoubleCube.size()>0)&&(numberOfUsedCubeTotalLeft>0))
	   {
		   System.out.println("did do the if");
		   doingMoveOfTheComputer();
		   System.out.println("the size of the 19 triangle is print number4:"+triangles[19].getVectorOfSoldier().size());

	   }
	   else
		   System.out.println("did not do the if");
		   
   }
   
   public void checkingPlaceForSoldierInCaseOfTakingOutAndBiggerCubes(Soldier soldier1)
   {
	   int contemporaryPlace=soldier1.getContemporaryTriangle();
	   
	   if(color.equals("white"))
	   {
		   if(contemporaryPlace+cubeNumber1>25)
		   {
			   Move move1=new Move(triangles[contemporaryPlace],finishTriangleOfComputer,cubeNumber1);
			   takingOutMovesCubeNumber1.add(move1);
		   }
		   
		   if(contemporaryPlace+cubeNumber2>25)
		   {
			   Move move1=new Move(triangles[contemporaryPlace],finishTriangleOfComputer,cubeNumber2);
			   takingOutMovesCubeNumber2.add(move1);
		   }
	   }
	   
	   if(color.equals("black"))
	   {
		   if(contemporaryPlace-cubeNumber1<0)
		   {
			   Move move1=new Move(triangles[contemporaryPlace],finishTriangleOfComputer,cubeNumber1);
			   takingOutMovesCubeNumber1.add(move1);
		   }
		   
		   if(contemporaryPlace-cubeNumber2<0)
		   {
			   Move move1=new Move(triangles[contemporaryPlace],finishTriangleOfComputer,cubeNumber2);
			   takingOutMovesCubeNumber2.add(move1);
		   }
	   }
   }
   
   public int exitingSoldiers(Vector<Move> moveVectorHelp)
   {
	   System.out.println("Exiting soldiers");
	   
	   int sizeofTheVectorMove=moveVectorHelp.size();
  	   
	   System.out.println("the size of the vector move is:"+sizeofTheVectorMove);
	   
	   int placeRandom=(int)((sizeofTheVectorMove)*Math.random());
	   
	   Move chooseMove;	
	   
	   if(sizeofTheVectorMove==1)
		   placeRandom=0;
	   
	   System.out.println("the place in the move vector is:"+placeRandom);
	   
	   chooseMove=moveVectorHelp.get(placeRandom);         
	   
	   System.out.println("From:"+chooseMove.getFromTriangle().getNumberOfTriangle());
	   
	   System.out.println("To:"+chooseMove.getToTriangle().getNumberOfTriangle());
	   
	   System.out.println("The move is according the cube that shows:"+chooseMove.getNumberOfCube());
	   	   
	   Soldier chooseSoldier;
	   
	   int sizeOfTheVector;
	   
	  // System.out.println("SDFGSDFGDFSGSDFGSDFGSDFGSDFGSDFG!!!!!!!!!"+chooseMove.getFromTriangle().getNumberOfTriangle());
	   	   
	   sizeOfTheVector=triangles[chooseMove.getFromTriangle().getNumberOfTriangle()].getVectorOfSoldier().size();
	   
	   //System.out.println("the size of the vector triangle is is:"+sizeOfTheVector);
	   
	   //System.out.println("the number vector triangle is is:"+chooseMove.getFromTriangle().getNumberOfTriangle());
	    
	   chooseSoldier=triangles[chooseMove.getFromTriangle().getNumberOfTriangle()].getVectorOfSoldier().get(sizeOfTheVector-1);
	   
	   triangles[chooseMove.getFromTriangle().getNumberOfTriangle()].removeSoldier();
	   
	   System.out.println("the size of the 19 triangle is print number1:"+triangles[19].getVectorOfSoldier().size());
	   
	   //chooseSoldier.setContemporaryTriangle(finishTriangleOfComputer.getNumberOfTriangle());
	   
	   //chooseSoldier.setLocation(finishTriangleOfComputer.getLocationX(),finishTriangleOfComputer.getLocationY());
	   
	   chooseSoldier.setLocation(finishTriangleOfComputer.getLocationX(),finishTriangleOfComputer.getLocationY());
	   
	   finishTriangleOfComputer.addSoldier(chooseSoldier);
	   
	   return placeRandom;
   }
   
   public int checkingForEatingSoldierInUsingTheTwoCubes(int numberOfTriangle)
   {
	   int retrunNumber=0;
	   if(color.equals("white"))
	   {
		   if(numberOfTriangle==25)
			   numberOfTriangle=0;
		   if((triangles[numberOfTriangle+cubeNumber1].getVectorOfSoldier().size()==0)||(triangles[numberOfTriangle+cubeNumber1].getVectorOfSoldier().get(triangles[numberOfTriangle+cubeNumber1].getVectorOfSoldier().size()-1).getColor().equals("white")))
		   {
			   if((triangles[numberOfTriangle+cubeNumber2].getVectorOfSoldier().size()==0)||(triangles[numberOfTriangle+cubeNumber2].getVectorOfSoldier().get(triangles[numberOfTriangle+cubeNumber2].getVectorOfSoldier().size()-1).getColor().equals("white")))
				   retrunNumber= 0;
			   else
			   {
				   if((triangles[numberOfTriangle+cubeNumber2].getVectorOfSoldier().size()==1)&&(triangles[numberOfTriangle+cubeNumber2].getVectorOfSoldier().get(0).getColor().equals("black")))
					   retrunNumber= 2;
			   }
		   }
		   else
		   {
			   if((triangles[numberOfTriangle+cubeNumber1].getVectorOfSoldier().size()==1)&&(triangles[numberOfTriangle+cubeNumber1].getVectorOfSoldier().get(0).getColor().equals("black")))
				   retrunNumber= 1;
		   }
	   }
	   
	   if(color.equals("black"))
	   {
		   if((triangles[numberOfTriangle-cubeNumber1].getVectorOfSoldier().size()==0)||(triangles[numberOfTriangle-cubeNumber1].getVectorOfSoldier().get(triangles[numberOfTriangle-cubeNumber1].getVectorOfSoldier().size()-1).getColor().equals("black")))
		   {
			   if((triangles[numberOfTriangle-cubeNumber2].getVectorOfSoldier().size()==0)||(triangles[numberOfTriangle-cubeNumber2].getVectorOfSoldier().get(triangles[numberOfTriangle-cubeNumber2].getVectorOfSoldier().size()-1).getColor().equals("black")))
				   retrunNumber= 0;
			   else
			   {
				   if((triangles[numberOfTriangle-cubeNumber2].getVectorOfSoldier().size()==1)&&(triangles[numberOfTriangle-cubeNumber2].getVectorOfSoldier().get(0).getColor().equals("white")))
					   retrunNumber= 2;
			   }
		   }
		   else
		   {
			   if((triangles[numberOfTriangle-cubeNumber1].getVectorOfSoldier().size()==1)&&(triangles[numberOfTriangle-cubeNumber1].getVectorOfSoldier().get(0).getColor().equals("white")))
			   {
				   retrunNumber= 1;
			   }
		   }
	   }
	   return retrunNumber;
   }
   
   public int movingInCaseEatingPlayerSoldierByEatenSoldierOfTheComputer(Vector<Move>moveVectorHelp,int placeRandom,int numberOfCubeForEating)
   {
	   if(numberOfCubeForEating==1)
	   {
		   if(color.equals("white"))
		   {
			   System.out.println("In the cube number 1 for color white********************");
			   triangles[0].addSoldier(triangles[cubeNumber1].getVectorOfSoldier().get(0));
			   triangles[cubeNumber1].getVectorOfSoldier().get(0).setContemporaryTriangle(25);
			   triangles[cubeNumber1].getVectorOfSoldier().get(0).setLocation(triangles[0].getLocationX(),triangles[0].getLocationY());
			   triangles[cubeNumber1].removeSoldier();
		   }
		   
		   if(color.equals("black"))
		   {
			   System.out.println("In the cube number 1 for color black*****************");
			   triangles[0].addSoldier(triangles[25-cubeNumber1].getVectorOfSoldier().get(0));
			   triangles[25-cubeNumber1].getVectorOfSoldier().get(0).setContemporaryTriangle(25);
			   triangles[25-cubeNumber1].getVectorOfSoldier().get(0).setLocation(triangles[0].getLocationX(),triangles[0].getLocationY());
			   triangles[25-cubeNumber1].removeSoldier();
			   
		   }
	   }
	   
	   if(numberOfCubeForEating==2)
	   {
		   if(color.equals("white"))
		   {
			   System.out.println("In the cube number 2 for color white*****************************");
			   triangles[0].addSoldier(triangles[cubeNumber2].getVectorOfSoldier().get(0));
			   triangles[cubeNumber2].getVectorOfSoldier().get(0).setContemporaryTriangle(25);
			   triangles[cubeNumber2].getVectorOfSoldier().get(0).setLocation(triangles[0].getLocationX(),triangles[0].getLocationY());
			   triangles[cubeNumber2].removeSoldier();
		   }
		   
		   if(color.equals("black"))
		   {
			   System.out.println("In the cube number 2 for color black******************************");
			   triangles[0].addSoldier(triangles[25-cubeNumber2].getVectorOfSoldier().get(0));
			   triangles[25-cubeNumber2].getVectorOfSoldier().get(0).setContemporaryTriangle(25);
			   triangles[25-cubeNumber2].getVectorOfSoldier().get(0).setLocation(triangles[0].getLocationX(),triangles[0].getLocationY());
			   triangles[25-cubeNumber2].removeSoldier();
		   }
	   }
	   	   
	    
	   Soldier chooseSoldier;
	   
	   int sizeOfTheVector;
	   
	   Move chooseMove=moveVectorHelp.get(placeRandom);
	   
	   System.out.println("SDFGSDFGDFSGSDFGSDFGSDFGSDFGSDFG!!!!!!!!!"+chooseMove.getFromTriangle().getNumberOfTriangle());
	   	   
	   sizeOfTheVector=triangles[0].getVectorOfSoldier().size();
	   
       System.out.println("the size of the vector triangle is is:"+sizeOfTheVector);
 	   
	   System.out.println("the number vector triangle is is:"+chooseMove.getFromTriangle().getNumberOfTriangle());
	   
	   int placeOfFound=0;
	   
	   if(sizeOfTheVector>1)
	   {
	      boolean found=false;
	      int i=0;
	      
	      while((i<sizeOfTheVector)&&(!found))
	      {
	         chooseSoldier=triangles[0].getVectorOfSoldier().get(i);
	         if(triangles[0].getVectorOfSoldier().get(i).getColor().equals(color))
	         {
	        	 found=true;
	        	 placeOfFound=i;
	         }
	      }    
	   }
	   chooseSoldier=triangles[0].getVectorOfSoldier().get(placeOfFound);
	   
	   int placeOfSoldierInVector=triangles[0].getPlaceInVectorOfSoldier(chooseSoldier);
       System.out.println("the place in the vector is:");
       System.out.println(placeOfSoldierInVector);
       Vector <Soldier> soldiersHelp2=new Vector<Soldier>();
       soldiersHelp2=triangles[0].getVectorOfSoldier();
       soldiersHelp2.remove(placeOfSoldierInVector);
       triangles[0].setVectorOfSoldier(soldiersHelp2);       
       //return placeOfSoldierInVector;
	     
	   //triangles[0].removeSoldier();
	   
	   chooseSoldier.setContemporaryTriangle(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getNumberOfTriangle());
	   
	   chooseSoldier.setLocation(triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getLocationX(),triangles[chooseMove.getToTriangle().getNumberOfTriangle()].getLocationY());
	   	   
	   triangles[chooseMove.getToTriangle().getNumberOfTriangle()].addSoldier(chooseSoldier);
	   
	   int countOfSoldiers=0;
	   
	   for(int i=0;i<triangles[0].getVectorOfSoldier().size();i++)
	   {
		   if(triangles[0].getVectorOfSoldier().get(i).getColor().equals(color))
			   countOfSoldiers++;
	   }
	   
	   System.out.println("the number of soldier of computer in the middle of the board is:"+countOfSoldiers);
	   
	   if(countOfSoldiers==0)
		   EatenSoldierInTheMiddleOfTheBoardFree=true;
	   
	   //EatenSoldierInTheMiddleOfTheBoardFree=true;
	   
	   
	   return placeRandom;
   }
}








