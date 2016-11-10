/**
 *  this is a generic class template, which you can copy, then
 *  paste into your "default package" folder and choose "Refactor".  You then
 *  type in the class-name you want to use.
 *
 */

import java.awt.*;
import javax.swing.*;



//here you would add the "import" commands for JAVA classes and libraries

public class Table 
{
int l;
int w;
int x;
int y;
//Color c;


 // Here is the generic, no-parameter class constructor
    public Table(int inl, int inw, int inx, int iny)  //constructor 
 {
   l=inl;
   w=inw;
   x=inx;
   y=iny;
   //c= g.setColor(Color.green);
 }

/*private void (Graphics g)
g.set.Color(c)
g.fillRect(x,y,l,w,)*/
 // Here is an optional, parameter-specific  class constructor
 public Table(int inclassVariableX)  //constructor 
 {
     //in this constructor, you would initialize all of your classVariables, i.e.:
     // classVariableX=inclassVariableX;  
      l=200;
   w=200;
   x=100;
   y=100;
   
 }
 
 
 public  void drawtable(Graphics g)
 {
     g.setColor(Color.green);
     g.fillRect(x, y, w, l);

 }
    
    
    
  

}

