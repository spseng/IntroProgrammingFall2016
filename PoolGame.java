/**
 *  Implements an applet which draws a recursive tree
 *	
 *	The four JButtons are used as follows:
 *	button 1: controls count1 and is size of tree
 *	button 2: controls count2 and is level of tree
 *  button 3: controls count3 - when >1, draws tree
 *	button 4: controls count4 - when 1 clears all counts
 *
 */

import java.awt.*;
import static java.awt.Color.green;
import java.awt.event.*;
import javax.swing.*;

public class PoolGame extends JApplet
    implements ActionListener
{
  JButton       button1, //variables for controlling		
  		button2, //  the four buttons
  		button3, 
  		button4;
  
  int           count1, 
  		count2, 
  		count3, 
  		count4;
  
  //variables to control state of the drawing
  int 		hpos, 			//xcoordinate of turtle
  		vpos, 			//ycoordinate of turtle
  		actualangle;            //angle of turtle
  boolean 	penupstate;		//pen is up or down
  int 		linethickness=1;//pen thickness
  Table t;

  /* This init function creates a window with four
   *	buttons.  It draws the outline of a graphics
   *	area and indicates the dimensions.
  */
  public void init()
  {
     setName("DrawTree");
    this.setSize(800,600);

    t = new Table(300,600, 50, 50);
    button1 = new JButton(" size");
    button1.setToolTipText("size");
    button1.addActionListener(this);

    button2 = new JButton("level");
    button2.setToolTipText("level");
    button2.addActionListener(this);

    button3 = new JButton(" draw ");
    button3.setToolTipText("draw");
    button3.addActionListener(this);

    button4 = new JButton(" erase ");
    button4.setToolTipText("erase");
    button4.addActionListener(this);

    JPanel panel = new JPanel();
    panel.add(button1);
    panel.add(button2);
    panel.add(button3);
    panel.add(button4);

    Container c = getContentPane();
    c.setBackground(Color.white);
    c.add(panel, BorderLayout.SOUTH);
  }

  /**
   *  Processes button events
   */
  public void actionPerformed(ActionEvent e)
  {
    JButton button = (JButton)e.getSource();

    if (button == button1)
      count1++;
    else if (button == button2)
      count2++;
    else if (button == button3)
      count3++;
    else if (button == button4)
      count4++;

    repaint();
  }

  /**
   *  Displays the numbers and tree
   *	Note that this is called everytime repaint
   *	is called.
   */
  public void paint(Graphics g)
  {
    super.paint(g);

    final int xStep = getWidth()/8;

 	if (count4>0) //reset all counters
 		{				
 		count1=count2=count3=count4=0;	
 		}
   // Display counters just above the buttons
    int y = 480;
    g.drawString(String.valueOf(count1), 2*xStep+20, y);
    g.drawString(String.valueOf(count2), 3*xStep+20, y);
    g.drawString(String.valueOf(count3), 4*xStep+20, y);
    g.drawString(String.valueOf(count4), 5*xStep+20, y);

    g.setColor(Color.red);
    g.drawRect(50, 50, 390, 390);  // draw a rectangle 150 by 50
    g.setColor(Color.blue.brighter());
    g.fillRect(50,50,390,390);
    g.setColor(Color.blue);			//draw coordinates
    g.drawString("40, 40", 40, 40);
    g.drawString("420, 40", 420, 40);
    g.drawString("40, 460", 40, 460);
    g.drawString("420,460", 420, 460);
    
    //t.drawtable(g);

 	if (count3>0) //draw tree
 		{
 		penup();				 //pick up pen
 		moveturtleto(250,420,g); //move to bottom center
 		pendown();				 //put down pen
 		tree(count2,count1,90,g);//draw tree				
 		count3=0;	
 		}
  }

 //*************************************************
 // This method will draw a recursive tree, based
 //	on the level and size, starting in the direction
 //	given by angle.
 //*************************************************
  private void tree(int level,int size,int angle,Graphics g)
  { 
  	//draw outward branch
	
      linethickness=level;
      turnturtleto(angle);
 	moveturtle(level*size*10,g);
 	//decide whether to recurse
  	if (level>1)
  		{
  		tree(level-1,size,angle-25,g);
   		tree(level-1,size,angle+25,g);
                tree(level-1,size,angle-45,g);
                tree(level-1,size,angle+45,g);
   		}
   		
   	//retrace steps back along branch	
	linethickness=1;
        drawleaf(angle, 2, Color.green, g);
        //drawleaf(angle,1,Color.yellow,g);
        turnturtleto(angle);
   	drawapple(g);
        
	moveturtle(-1*level*size*10,g);
        
  }
  private void drawapple(Graphics g)
  {
      g.setColor(Color.pink);
      g.fillOval(hpos,vpos,30,30);
      g.setColor(Color.black);
  }

private void drawleaf(int angle, int size, Color c, Graphics g)
      { 
        int[] xarray;
  	int[] yarray;
  	int i;
        int holdx, holdy, holdang;
        
        holdx=hpos;
        holdy=vpos;
        //actualangle=angle;
        holdang=actualangle;
        
  	xarray= new int[18];
  	yarray= new int[18];
    
        actualangle=actualangle+45;
   	//turnturtleto(actualangle);
        for(i=0; i<9;i=i+1)
        {
   	xarray[i]=hpos;
   	yarray[i]=vpos;
        moveturtle(5*size,g);
        actualangle=actualangle+10;
        
   	//turnturtleto(actualangle);
        }
        
        actualangle=actualangle+90;
   	//turnturtleto(actualangle);
        //moveturtle(10*size,g);
        
      //  xarray[9]=hpos;
      //  yarray[9]=vpos;
        
         for(i=9; i<18;i=i+1)
        {
   	xarray[i]=hpos;
   	yarray[i]=vpos;
       moveturtle(5*size,g);
         actualangle=actualangle+10;
   	//turnturtleto(actualangle);
        }
       // xarray[18]=hpos;
       // yarray[18]=vpos;
       
        g.setColor(c);
        
        g.fillPolygon(xarray, yarray, 18);
        
        moveturtleto(holdx,holdy,g);
        actualangle=holdang;
      }


  private void turnturtleto(int angle)
  { 
	actualangle=360-angle;
  }

 private void penup()
  { 
	penupstate=true;
  }

 private void pendown()
  { 
	penupstate=false;
  }

 private void moveturtle(int distance,Graphics g)
  { 
	int x1, y1, x2, y2;
	double pi=3.1416;
	double radians;
	x1=hpos;
	y1=vpos;
	
	//this uses trigonometry to calculate new x and y,
	//  based on distance and direction moved.
	
	//first, convert the current turtleangle to radians
	//	by multiplying by 2pi and dividing by 360
	radians= (double) (( (double) actualangle)*2.0*pi/360.0);

	//newx is found by adding cosine(radians)*distance
	x2=x1+(int)(distance*Math.cos(radians));
	//newy is found by adding sine(radians)*distance
	y2=y1+(int)(distance*Math.sin(radians));
    
 	//now that x2, and y2 known, draw the line
        if (linethickness==1)
        {
            drawturtleline(x1, y1, x2, y2,g);
        }
        else
        {
            drawthickturtleline(x1, y1, x2, y2,g);
        }
    
	//update hpos and vpos to new coordinate
        hpos=x2;
        vpos=y2;
  }

private void moveturtleto(int newx,int newy,Graphics g)
  { 
	//draw line from old coordinate (hpos,vpos)
	//	to new coordinate (newx,newy)    
    if (linethickness==1)
    {
      drawturtleline(hpos, vpos, newx, newy,g);
    }
    else
    {
        drawthickturtleline(hpos, vpos, newx, newy,g);
    }
	//update hpos and vpos to new coordinate
    hpos=newx;
    vpos=newy;
  }

private void drawturtleline(int x1, int y1,int x2,int y2,Graphics g)
  { 
    if (penupstate==false) //if pendown, draw in black
    	{
  		  g.setColor(Color.black);
  		  g.drawLine(x1, y1, x2, y2);
   		
    	}
   else				//if penup, don't draw
    	{//nothing....
     	}

   }

 private void drawthickturtleline(int x1, int y1,int x2,int y2,	Graphics g)
      { int[] xarray;
  	int[] yarray;
  	
  	xarray= new int[4];
  	yarray= new int[4];
  
   if  (linethickness==1)
   {
    if (penupstate==false)
    	{
  		  g.setColor(Color.black);
  		  g.drawLine(x1, y1, x2, y2);
   		
    	}
   else
    	{
  		  g.setColor(Color.white);
  		  g.drawLine(x1, y1, x2, y2);
   		
    	}
	}
  else //linethickness>1
   {
   	xarray[0]=x1+linethickness;
   	xarray[1]=x2+linethickness;
   	xarray[2]=x2-linethickness;
   	xarray[3]=x1-linethickness;
   	yarray[0]=y1+linethickness;
   	yarray[1]=y2+linethickness;
   	yarray[2]=y2-linethickness;
   	yarray[3]=y1-linethickness;
    if (penupstate==false)
    	{
  		  g.setColor(Color.black);
  		  g.fillPolygon(xarray, yarray,4);
   		
    	}
   else
    	{
  		  g.setColor(Color.white);
  		  g.fillPolygon(xarray, yarray,4);
  		
    	}
	}
   }

 

}

/*private void drawleaf(Color c, int size, Graphics g)
{
    int[] xarray;
    int[] yarray;
    
    xarray= new int[18];
    yarray=new int[18];
    
}*/